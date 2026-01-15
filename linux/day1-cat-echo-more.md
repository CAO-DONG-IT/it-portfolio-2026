# Day1 cat / echo / more（JST 2026-01-06）

## 用語（JP / EN / 中文）
- リダイレクト / redirection / 重定向
- 上書き / overwrite / 覆盖写入
- 追記 / append / 追加写入
- 標準出力 / stdout / 标准输出
- 改行 / newline / 换行

## 目的
- `touch` → `echo`（上書き/追記）→ `cat` で確認する
- 改行の挙動（`echo -n`）を理解する
- `more` のページ送り操作を確認する

## 先に結論（要点）
- `>`：上書き（ファイル内容を置き換える）
- `>>`：追記（末尾に追加する）
- `echo -n`：末尾に改行を付けない（文字列を連結したいときに使う）
- `more`：長いファイルをページ送りで閲覧できる（`q` で終了）

## 実行ログ（要点のみ）
```bash
cd ~/Sandbox/Day1
```
### 複数ファイル作成
```
touch test1.txt test2.txt 1test.txt 2test.txt 1test1.txt 2test2.txt
ls -l
```

### 上書き（>）と追記（>>）
echo "Hello" > 1test.txt
echo "World" >> 1test.txt
cat 1test.txt
### 期待出力：
```
Hello
World
```
### echo の改行を制御（-n：末尾改行なし）
echo "Hello" > 2test.txt
echo -n ",World!" >> 2test.txt
cat 2test.txt
### 期待出力（例）：
```
Hello
,World!
```
echo -n "Hello," > 1test1.txt
echo "World!" >> 1test1.txt
cat 1test1.txt
### 期待出力（例）：
```
Hello,World!
```

### more（ページ送り確認）
```
cd /etc
ls -l
more hosts      # 短い（1ページ未満）
more services   # 長い（ページ送りできる）

操作：Space=次ページ / Enter=次行 / q=終了
```
