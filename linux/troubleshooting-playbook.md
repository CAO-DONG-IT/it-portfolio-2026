# Linux Troubleshooting Playbook

運用・学習で遭遇した「現象→原因→対処」を、再現可能な形で蓄積する。
（公開リポジトリ運用のため、個人情報や不要なホスト情報は記載しない）

## 使い方
- まず「切り分け手順（固定順）」で当たりを付ける
- 次に該当する Case を参照し、コマンドで確認→対処→再確認する
- 新規追加は Case フォーマット（3行）で統一する

## 切り分け手順（固定順）
1) 現象確認：エラー文／発生操作／発生時刻（分かる範囲）
2) 影響範囲：単一ユーザー／全ユーザー／特定ディレクトリ／特定サービス
3) 権限：`whoami`／`id`／`ls -l`（所有者・権限）
4) サービス：`systemctl status`（起動状態）＋ `journalctl -u`（ログ）
5) リソース：CPU/メモリ/ディスク（`top`/`free -h`/`df -h`）
6) ネットワーク：待ち受け/疎通（`ss -tulpn`/`curl`/`ping`）

## 基本コマンド（用途）
- 位置/ファイル：`pwd` `ls -al` `cd`
- 内容確認：`cat` `less` `head` `tail`
- 文字列検索：`grep -n`
- プロセス：`ps aux` `top`
- ディスク：`df -h` `du -sh`
- 権限/ユーザー：`whoami` `id` `groups` `getent`
- サービス：`systemctl status|start|stop|restart`
- ログ：`journalctl -u <unit>`
- ポート：`ss -tulpn`（必要なら `sudo`）

## 典型ケース（後で追記）

### Case1：`cp -r` でコピー結果が想定と違う（フォルダごと/中身だけ）

- 現象：
  `cp -r Day1 ~/Sandbox1` の結果、想定した階層（例：`~/Sandbox1/Day1/`）にならない
- 原因：
   コピー先パスの「存在有無」や末尾 `/` の扱いにより、`cp` の解釈が変わる
- 対処：
  `mkdir -p <親> && cp -r <src> <親>/` で運用を固定し、`ls -al` で事後確認する


### Case2：`cat` 実行後に入力待ちになりプロンプトが戻らない

- 現象：
  `cat` の後、入力した文字がそのまま表示され続け、プロンプトが戻らない
- 原因：
  `cat` が標準入力を待っている（ファイル指定なし、または `cat > file` / `cat >> file` など）
- 対処：
  正常終了は `Ctrl + D`（EOF）、中断は `Ctrl + C`。内容確認は `cat file` や `less file` を使う

### Case3：実行ファイル（バイナリ）を `cat/more` して画面が乱れる

- 現象：
  画面が乱れる／文字化け／端末が不安定になる
- 原因：
  バイナリには制御文字が含まれ、端末表示が崩れる
- 対処：
  `Ctrl + C` で停止し、必要なら `reset`（または `stty sane` → `reset`）。事前に `file <name>` で種類確認する


### Case4：`rm` でディレクトリを消せない

- 現象：
  `rm Sandbox1` → `Is a directory`
- 原因：
  `rm` はディレクトリ削除に `-r` が必要
- 対処：
 `rm -r Sandbox1`。削除前に `pwd` と `ls -l` で対象を確認する


### Case5：ワイルドカードがマッチせず削除できない

- 現象：
  `rm *test` → `No such file or directory`
- 原因：
  ワイルドカード（`*`）が該当ファイルにマッチしていない
- 対処：
 先に `ls <pattern>` で対象確認し、次に `rm <pattern>` を実行する


### Case6：`useradd` したのに `/home` にユーザー用ディレクトリが作られない

- 現象：
  `useradd` 実行後、`/home/<user>` が存在しない
- 原因：
  `useradd` はデフォルトでホームディレクトリを作成しない（`-m` が必要）
- 対処：
 `sudo useradd -m -s /bin/bash <user>`。確認は `getent passwd <user>` と `id <user>`


### Case7：`getent groups` が失敗する（Unknown database）

- 現象：
  `getent groups` → `Unknown database: groups`
- 原因：
  DB名は `group`（単数）
- 対処：
  `getent group` を使う。ユーザーの所属確認は `groups <user>` / `id <user>`

### Case8：`usermod -aG` の引数順を混同する
- 現象：`usermod -aG` を実行したつもりでも、グループが反映されない／意図しない対象に付与してしまう
- 原因：引数順を混同（group と user を逆にする）、または `-a` を付けず上書きしてしまう
- 対処：正：`sudo usermod -aG <group> <user>`。確認は `groups <user>` / `id <user>`

### Case9：`systemctl stop` が失敗する（Interactive authentication required）
- 現象：`systemctl stop <unit>` → `Interactive authentication required.`
- 原因：サービス操作（stop/start/restart）は管理者権限が必要で、`sudo` なしだと拒否される
- 対処：`sudo systemctl stop <unit>`。状態確認は `systemctl status <unit> --no-pager`、ログは `journalctl -u <unit> --no-pager | tail`

### Case10：`ssh` と `sshd` のユニット名が一致しない
- 現象：`systemctl stop sshd` で意図したサービスを操作できない（または別名に解決される）
- 原因：ディストリビューションによりユニット名が異なる（例：Ubuntu/Debian系は `ssh.service`）
- 対処：`systemctl status sshd` と `systemctl status ssh --no-pager` で実体を確認し、正しいユニット名で操作する
