# Day1 cp / mv / rm（ワイルドカード含む）（JST 2026-01-06）

## 用語（JP / EN / 中文）
- コピー / copy / 复制
- 移動 / move / 移动
- 削除 / delete / 删除
- ワイルドカード（グロブ）/ wildcard (glob) / 通配符（glob）
- パス / path / 路径

## 目的
- `cp -r` の「コピー先パスの解釈」を理解する
- `mkdir -p` と組み合わせて意図した構成でコピーできるようにする
- `mv` とワイルドカード指定を使う
- `rm`（ファイル/ディレクトリ）と「マッチしない場合」の挙動を確認する

## 先に結論（要点）
- `cp -r <src> <dest>` で `<dest>` が **存在しない** 場合、`<dest>` は **新規ディレクトリ名として作成** され得る
- 「親ディレクトリ配下に `<src>` 名で配置」したい場合は、**親を先に作って `<親>/` を指定**するのが安全
- `rm` は **ファイル削除が基本**。ディレクトリ削除には `-r` が必要
- ワイルドカード削除は、実行前に **`ls <pattern>` で対象確認**してから行う

## 実行ログ（要点のみ）
```bash
 前提：コピー元は ~/Sandbox/Day1

# 1) 推奨：親を作ってからコピー（期待：~/Sandbox1/Day1 が作成される）
`
rm -r ~/Sandbox1
mkdir -p ~/Sandbox1 && cp -r ~/Sandbox/Day1 ~/Sandbox1/
`
# 2) 参考：コピー先の指定ミスで入れ子（Day1/Day1）になった場合の整理例
`cd ~/Sandbox1/Day1/Day1
mv *test* ~/Sandbox1/Day1
cd ..
rm -r ~/Sandbox1/Day1/Day1
`
# 3) rm：ディレクトリは -r が必要
`rm Sandbox1`      # => Is a directory
`rm -r Sandbox1`

# 4) rm：ワイルドカードがマッチしないと失敗する
`rm *test`         # => No such file or directory（マッチなし）

# 5) 削除前に対象確認（安全運用）
`ls *test.txt`    # まず一覧で確認
`rm *test.txt`    # マッチするものだけ削除
`ls *test*`        # まとめて消す前に確認
`rm *test*`


## パス解釈のメモ（cp）
- `cp -r <src> <dest>`：
  - `<dest>` が **存在しない** → `<dest>` を新規作成して内容をコピー（結果の名前が `<dest>` になる）
  - `<dest>` が **ディレクトリとして存在する** → `<dest>/<src名>` が作成される

### 運用を固定する（おすすめ）
`
mkdir -p <親> && cp -r <src> <親>/
`

## ワイルドカード（glob）のメモ
- `*`：任意の文字列（0文字以上）
- `?`：任意の1文字
- `[]`：文字クラス（例：`file[1-3].txt`）

## エラー別の切り分け（早見表）
- `cannot stat 'X'`：対象（コピー元/移動元）が存在しない（パス・スペル・カレントディレクトリを確認）
- `Is a directory`：`rm` でディレクトリを削除しようとしている → `rm -r` が必要
- `No such file or directory`：ワイルドカードが **マッチしていない** 可能性 → 先に `ls <pattern>` で確認
```
