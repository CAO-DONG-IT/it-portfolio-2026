# Day1 cat / echo / more（精简版）（2026-01-06）

## 目的
- `touch` → `echo`（上書き/追記）→ `cat` で確認
- 改行の挙動（`echo -n`）を理解する
- `more` のページ送り操作を確認する

## 実行ログ（要点のみ）
```bash
cd ~/Sandbox/Day1

# 複数ファイル作成
touch test1.txt test2.txt 1test.txt 2test.txt 1test1.txt 2test2.txt
ls -l

# 上書き（>）と追記（>>）
echo "Hello" > 1test.txt
echo "World" >> 1test.txt
cat 1test.txt
# 出力：
# Hello
# World

# echo の改行を制御（-n：末尾改行なし）
echo "Hello" > 2test.txt
echo -n ",World!" >> 2test.txt
cat 2test.txt
# 出力（例）：
# Hello
# ,World!

echo -n "Hello," > 1test1.txt
echo "World!" >> 1test1.txt
cat 1test1.txt
# 出力（例）：
# Hello,World!

##more（ページ送り確認）
cd /etc
ls -l
more hosts      # 短い（1ページ未満）
more services   # 長い（ページ送りできる）
# 操作：Space=次ページ / Enter=次行 / q=終了

##学んだこと（結論）

>：上書き、>>：追記

echo -n：末尾改行を付けない（文字列連結に便利）

more：ページ送りで閲覧できる（qで終了）
