# Day2: /etc 検索（find/grep/pipe/redirect/tail/vim）
## 用語（JP / EN / 中文）
- 検索 / search / 搜索
- 探索 / find / 查找（ファイル探索）
- 抽出 / grep / 抽取（文字列検索）
- パイプ / pipe / 管道（|）
- リダイレクト / redirection / 重定向（> / >>）
- 末尾表示 / tail / 查看末尾
- エディタ / editor / 编辑器（vim）
## 目的
- `/etc` 配下の設定ファイルを対象に、**探索 → 抽出 → 集計 → ログ化** を一通り再現できるようにする。

## 成果物（ログ）
- `etc_search.log`：探索結果と抽出結果、行数カウントを1ファイルにまとめる。

## 実行したコマンド

### 1) find（ファイル探索）
```
find /etc -type f -name "*ssh*" -print
```
### 2) grep（キーワード抽出）
```
grep -n "ssh" /etc/ssh/ssh_config
```
### 3) 件数集計（パイプ + wc -l）
```
grep -n "ssh" /etc/ssh/ssh_config | wc -l
```
### 4) ログ化（> / >>）
```
find /etc -type f -name "*ssh*" -print > etc_search.log
grep -n "ssh" /etc/ssh/ssh_config >> etc_search.log
grep -n "ssh" /etc/ssh/ssh_config | wc -l >> etc_search.log
```
### 5) 末尾確認（tail）
```
tail -n 20 etc_search.log
```
### 6) 追記（vim）
ログ末尾に説明を追記して保存した。
```bash
vim etc_search.log
```
- ログ末尾に「何を数えたか」「何を確認したか」を追記して保存する。
### メモ（要点）
- `grep -n` の `-n` は **行番号表示**。
- `wc -l` の数値は **「ssh を含む行数」** を表す（ファイル数ではない）。
- `>` は上書き、`>>` は追記。ログは `>` で作成してから `>>` で積み上げると管理しやすい。
- 画面出力だけだと意図が伝わりづらいので、**ログに注釈（目的・結果）** を残す。
- もし `Permission denied` が出る場合は、対象パスの見直し・権限（sudo）・出力制御を検討する（詳細は別ノートで扱う）。
