# Day7: systemctl / SSH（WSL・JST 2026-01-11）

## 前提
- 環境：WSL（Ubuntu/Debian系）
- 目的：SSH サービスの停止／起動を行い、状態とログで検証する

## 1. 失敗（原因の特定）
### 1.1 現象
- `systemctl stop sshd` を実行しても停止できない

### 1.2 エラー
- `Failed to stop sshd.service: Interactive authentication required.`

### 1.3 原因
- `systemctl stop` は管理者権限が必要なため、`sudo` なしだと拒否される
- Ubuntu/Debian系ではユニット名が `sshd.service` ではなく `ssh.service` の場合がある  
  - `systemctl status sshd` は `ssh.service` に解決されることがある

## 2. 修正（正しい手順）
### 2.1 ユニット名の確認
```bash
systemctl status sshd
systemctl status ssh --no-pager
```

### 2.2 停止（sudo + 正しいユニット名）
```
sudo systemctl stop ssh
systemctl status ssh --no-pager
```
### 2.3 起動（再開して状態確認）
```
sudo systemctl start ssh
systemctl status ssh --no-pager
```

## 3. 検証（結果の証跡）
### 3.1 期待する状態
- systemctl status ssh の Active: が active (running)（起動時）／inactive (dead)（停止時）になる

- journalctl -u ssh に stop/start のログが出る

### 3.2 ログ（起動/停止の履歴）
```
journalctl -u ssh --no-pager | tail -n 60
```
## 4. メモ（学び）
- `systemd`が有効でも、操作（`stop/start/restart`）は`sudo`が必要なケースがある

- サービス名（ユニット名）はディストリビューションにより異なる（例：`ssh` vs `sshd`）
