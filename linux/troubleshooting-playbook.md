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

### Case1：`cp -r` でコピー結果が想定と違う（フォルダごと/中身だけ）

- 現象：
  `cp -r Day1 ~/Sandbox1` を実行した結果、想定と異なる階層（`~/Sandbox1/Day1/` にならない等）になる
- 原因：
  コピー先ディレクトリの **存在有無** と、`cp` の **末尾スラッシュ有無** により挙動が変わる
- 対処：
  - 「中身だけ」をコピー：`cp -r Day1/. Day3/`
  - 「隠しファイルなし」で中身だけ：`cp -r Day1/* Day3/`
  - 事前に親を作る：`mkdir -p ~/Sandbox1 && cp -r Day1 ~/Sandbox1/`
  - 事後確認：`ls -al ~/Sandbox1` / `tree -a ~/Sandbox1`（treeがあれば）


### Case2：`cat` 実行後に入力待ちになりプロンプトが戻らない

- 現象：
  `cat` の後、入力した文字がそのまま表示され、プロンプトが戻らない
- 原因：
  `cat` が標準入力を待っている（ファイル指定なし、または `cat > file` / `cat >> file` 等）
- 対処：
  - 正常終了：`Ctrl + D`（EOF）
  - 中断：`Ctrl + C`
  - 内容確認は `cat file` / 長い場合は `more file` 等に切替


### Case3：実行ファイル（バイナリ）を `cat/more` して画面が乱れる

- 現象：
  画面が乱れる、文字化け、警告音が鳴る
- 原因：
  バイナリには制御文字が含まれ、端末表示が崩れる
- 対処：
  - 出力停止：`Ctrl + C`
  - 画面復旧：`reset`（必要なら `stty sane` → `reset`）
  - 再発防止：先に種類確認 `file <name>`、文字列だけ `strings <name> | head`


### Case4：`rm` でディレクトリを消せない

- 現象：
  `rm Sandbox1` → `Is a directory`
- 原因：
  `rm` はディレクトリ削除に `-r` が必要
- 対処：
  - ディレクトリ削除：`rm -r Sandbox1`
  - 確認してから削除：`pwd` と `ls -l` で対象パスを確認して実行


### Case5：ワイルドカードがマッチせず削除できない

- 現象：
  `rm *test` → `No such file or directory`
- 原因：
  ワイルドカード（`*`）が該当ファイルにマッチしていない
- 対処：
  - まずマッチ確認：`ls *test`
  - 実際に削除（例）：`rm *test.txt`
  - 再発防止：先に `ls <pattern>` → 次に `rm <pattern>` の順で実行


### Case6：`useradd` したのに `/home` にユーザー用ディレクトリが作られない

- 現象：
  `useradd` 実行後、`/home/<user>` が存在しない
- 原因：
  `useradd` はデフォルトでホームディレクトリを作成しない（`-m` が必要）
- 対処：
  - 新規作成：`sudo useradd -m -g <group> <user>`
  - シェル指定：`sudo useradd -m -s /bin/bash <user>`
  - 確認：`getent passwd <user>`（homeのパス）/ `id <user>`（所属確認）


### Case7：`getent groups` が失敗する（Unknown database）

- 現象：
  `getent groups` → `Unknown database: groups`
- 原因：
  DB名は `group`（単数）
- 対処：
  - 正：`getent group`
  - 末尾だけ：`getent group | tail`
  - 特定ユーザー確認：`groups <user>` / `id <user>`


### Case8：`usermod -aG` の引数順を混同する

- 現象：
  `usermod -aG` を実行したつもりでも、グループが反映されない／意図しない対象に付与してしまう
- 原因：
  引数順を混同（group と user を逆にする）
- 対処：
  - 正：`sudo usermod -aG <group> <user>`
  - 反映確認：`groups <user>` / `id <user>`
