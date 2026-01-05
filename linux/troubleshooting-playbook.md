# Linux トラブルシュート・プレイブック（基本版）

## 1. 基本コマンド一覧（用途 + 例）
### ディレクトリ／ファイル操作
- `pwd`：現在地確認（例：`pwd`）
- `ls`：一覧表示（例：`ls -la`）
- `cd`：移動（例：`cd /var/log`）

### ファイル閲覧
- `cat`：内容表示（例：`cat file.txt`）
- `less`：ページ表示（例：`less /var/log/syslog`）
- `head / tail`：先頭／末尾（例：`tail -n 50 /var/log/syslog`）

### 検索
- `grep`：文字列検索（例：`grep -n "error" /var/log/syslog`）

### プロセス／リソース
- `ps`：プロセス確認（例：`ps aux | grep nginx`）
- `top`：負荷確認（例：`top`）
- `df`：ディスク残量（例：`df -h`）
- `du`：容量内訳（例：`du -sh *`）

### サービス／ログ
- `systemctl`：サービス状態（例：`systemctl status nginx`）
- `journalctl`：ログ確認（例：`journalctl -u nginx --no-pager -n 100`）

### ネットワーク
- `ss`：ポート確認（例：`ss -tulpn`）
- `ip a`：IP確認（例：`ip a`）
- `ping`：疎通（例：`ping 8.8.8.8`）

---

## 2. トラブルシュートの基本手順（固定順）
1) **現象の確認**（いつ／何が／どの画面・ログで発生したか）
2) **サービス状態**（動いているか：`systemctl status`）
3) **ログ確認**（エラーメッセージ：`journalctl` / `/var/log`）
4) **リソース確認**（CPU／メモリ／ディスク：`top` `df -h`）
5) **ネットワーク確認**（ポート／疎通：`ss -tulpn` `ping`）
6) **権限確認**（ユーザー／ファイル権限：`ls -l`）
7) **対処→再確認→記録**（原因・手順・再発防止を書く）

---

## 3. 典型ケース（後で埋める）
### Case 1：
- 事象：
- 調査：
- 原因：
- 対応：
- 再発防止：

### Case 2：
- 事象：
- 調査：
- 原因：
- 対応：
- 再発防止：

### Case 3：
- 事象：
- 調査：
- 原因：
- 対応：
- 再発防止：

