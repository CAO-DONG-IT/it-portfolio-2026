# Day7: systemctl / SSH（WSL・JST 2026-01-11）

## 1. 失敗（原因の特定）
- 現象：`systemctl stop sshd` を実行すると停止できない
- エラー：`Failed to stop sshd.service: Interactive authentication required.`
- 原因：
  - `systemctl stop` は管理者権限が必要（sudoなしのため拒否）
  - Ubuntu/Debian系ではユニット名が `sshd.service` ではなく `ssh.service`（`status sshd` は `ssh.service` に解決される）

## 2. 修正（正しいコマンド）
### 2.1 ユニット名の確認
```
systemctl status sshd
```
### 2.2 停止（sudo + 正しいユニット名）
```
sudo systemctl stop ssh
```
### 2.3 起動（再開して状態確認）
```
sudo systemctl start ssh
systemctl status ssh --no-pager
```

## 3. 検証（結果の証跡）
### 3.1 ログ（起動/停止の履歴）
```
journalctl -u ssh --no-pager | tail -n 60
```
## 4. メモ（学び）
- `systemd`が有効でも、操作（`stop/start/restart`）は`sudo`が必要なケースがある

- サービス名（ユニット名）はディストリビューションにより異なる（例：`ssh` vs `sshd`）
