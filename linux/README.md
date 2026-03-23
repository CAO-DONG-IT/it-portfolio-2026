# Linux（運用基礎）

## 目的
インフラ運用・保守の入口として必要な Linux 基礎を学習し、コマンド操作、状態確認、基本的な切り分けを、後から説明できる形で記録することを目的としている。

## 面接で説明できる代表成果物（まずはここ）
- IPアドレスとホスト名の基礎整理  
  `linux/notes/ip_address_and_hostname.md`
- トラブルシューティング・プレイブック  
  `linux/troubleshooting-playbook.md`
- 日時・シンボリックリンクの確認ログ  
  `linux/notes/date-and-symlink.md`

## 現在の公開進捗
- 基本コマンド
- 検索系コマンド
- ユーザー／グループ
- 権限（chmod / chown）
- 日時・タイムゾーン
- シンボリックリンク
- IPアドレスとホスト名

現時点では、深い構築よりも、運用・保守で必要になる基礎確認、コマンド操作、状態把握、基本的なトラブル切り分けを優先している。

## 学習の進め方
- 実際にコマンドを実行して挙動を確認する
- 確認した内容は `linux/notes/` に Markdown で整理する
- 詰まった点や再発防止したい内容は `troubleshooting-playbook.md` に残す
- 必要に応じて `weekly-log/` から当日の証跡へ辿れるようにする

## ノート
- `linux/notes/day1-cat-echo-more.md`
- `linux/notes/day1-cp-mv-rm-wildcard.md`
- `linux/notes/day2-etc-search.md`
- `linux/notes/day3-users-groups.md`
- `linux/notes/date-and-symlink.md`
- `linux/notes/ip_address_and_hostname.md`

## トラブルシューティング
- `linux/troubleshooting-playbook.md`

例：
- シンボリックリンクの循環参照
- `ifconfig` が使えない環境で `ip addr` に切り替えた確認
- 絶対パスと相対パスの違いによる挙動差の整理

## スクリーンショット
- `linux/screenshots/`

## この学習ラインで意識していること
- まず状態を確認してから判断すること
- コマンドの結果をそのまま受け取るのではなく、意味を整理すること
- 詰まった点を「現象 / 原因 / 対処」で残し、後から見返せるようにすること

## 補足
- 個人情報（氏名、住所、メールアドレス等）は含めない
- 結果だけでなく、再現手順と判断の根拠を残す
- 追加したノートや証跡は、必要に応じて `weekly-log/` からも辿れるようにする
