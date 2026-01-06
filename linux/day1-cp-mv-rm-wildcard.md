# Day1 cp / mv / rm（ワイルドカード含む）（2026-01-06）

## 目的
- `cp -r` のコピー先パス解釈を理解する
- `mkdir -p` と組み合わせて意図した構成でコピーする
- `mv` のワイルドカード指定を使う
- `rm`（ファイル/ディレクトリ）とマッチしない場合を確認する

## 実行ログ（要点のみ）
```bash
# 前提：コピー元は ~/Sandbox/Day1

# Sandbox1 を削除してから、親を作ってコピー（推奨形）
rm -r ~/Sandbox1
mkdir -p ~/Sandbox1 && cp -r ~/Sandbox/Day1 ~/Sandbox1/
# 期待結果：~/Sandbox1/Day1 が作成される

# もし入れ子（Day1/Day1）になった場合の整理例
cd ~/Sandbox1/Day1/Day1
mv *test* ~/Sandbox1/Day1
cd ..
rm -r ~/Sandbox1/Day1/Day1

# rm：ディレクトリは -r が必要
rm Sandbox1      # => Is a directory
rm -r Sandbox1

# rm：ワイルドカードがマッチしないと失敗する
rm *test         # => No such file or directory（マッチなし）
rm *test.txt     # マッチするものだけ削除
rm *test*        # 最後にまとめて削除

学んだこと（結論）

cp -r <src> <dest> で <dest> が存在しないと、<dest> は新規ディレクトリ名として扱われ得る

期待構成（親配下に src 名を作る）にしたい場合は、運用を固定する：

mkdir -p <親> && cp -r <src> <親>/

rm はファイル削除、ディレクトリ削除には -r

ワイルドカード削除は事前に ls <pattern> で確認すると安全


（エラー別）

cannot stat 'X'：対象（コピー元/削除対象）が存在しない

Is a directory：rm -r が必要

No such file or directory：ワイルドカードがマッチしていない可能性
