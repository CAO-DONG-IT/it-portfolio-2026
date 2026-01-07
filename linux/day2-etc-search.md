# Day2: /etc 検索（find/grep/pipe/redirect/tail/vim）

## 目的
- `/etc` 配下の設定ファイルを対象に、検索→抽出→集計→ログ化の一連を再現できるようにする。

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

### 結果メモ
`wc -l` の数値は「ssh を含む行数」を表す。

出力だけだと意図が伝わりづらいので、注釈を残す。
