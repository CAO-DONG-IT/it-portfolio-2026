# Linux Troubleshooting Playbook

## 基本コマンド（用途 + 例）
- pwd / ls / cd（場所確認・移動）
- cat / less / head / tail（内容確認）
- grep（ログ検索）
- ps / top（プロセス確認）
- df / du（ディスク確認）
- systemctl / service（サービス状態）
- journalctl（システムログ）
- ss -tulpn（ポート監視）※権限が必要なら sudo

## 切り分け手順（固定順）
1) 現象確認：エラー内容／ログ／発生時間
2) サービス：起動しているか（systemctl status）
3) リソース：CPU/メモリ/ディスク（top, free, df）
4) ネットワーク：待ち受け/疎通（ss, curl）
5) 権限：ユーザー/ファイル権限（whoami, ls -l）

## 典型ケース（後で追記）

### Case1：`cp -r` でコピー先の構成が想定と違う

 **現象**
- `cp -r Day1 ~/Sandbox1` を実行した結果、`~/Sandbox1/Day1/` が作られず、`~/Sandbox1` 直下に `Day1` 配下のファイルが展開されたように見える

 **原因**
- コピー先 `~/Sandbox1` が **存在しない** 状態だったため、`cp` が `~/Sandbox1` を「新しいディレクトリ名」として作成し、その中身を `Day1` の内容にした

**解決**（推奨）
- 親ディレクトリを先に作ってからコピーする運用に統一する

mkdir -p ~/Sandbox1 && cp -r Day1 ~/Sandbox1/



### Case2：cat 実行後に入力待ちになりコマンドが打てない

**現象**

`cat` の後、入力した文字がそのまま表示され、プロンプトが戻らない

**原因**

`cat` が標準入力を待っている（ファイル指定なし、または `cat > file / cat >> file` 等）

**解決**

Ctrl + D：EOFで正常終了

Ctrl + C：中断

**再発防止**

内容確認は `cat file `/ 長い場合は `more`


### Case3：実行ファイル（バイナリ）を cat/more して画面が乱れる

**現象**

画面が乱れる、警告音が鳴る

**原因**

バイナリには制御文字が含まれ、端末表示が崩れる

**解決**

出力を止める
`Ctrl + C`

表示を復旧
`reset`

必要なら
`stty sane`
`reset`

**再発防止**

先に種類確認：`file <name>`

文字列だけ抽出：`strings <name> | head`

### Case4：rm でディレクトリを消せない

**現象**

`rm Sandbox1 → Is a directory`

**原因**

`rm` はディレクトリ削除に `-r` が必要

**解決**
`rm -r Sandbox1`

**再発防止**

削除前に `pwd` と `ls -l` で対象パスを確認する

### Case5：ワイルドカードがマッチせず削除できない

**現象**

`rm *test` → `No such file or directory`

**原因**

ワイルドカード（*）が該当ファイルにマッチしていない

**解決**

先にマッチ確認
`ls *test`

実際に削除（例）
`rm *test.txt`

**再発防止**

先に `ls <pattern>` でマッチ確認してから `rm <pattern>` を実行する
