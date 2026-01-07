# Network Troubleshooting Playbook

## 即断フロー（固定順）
0. 現象整理（いつ/どこで/何が）
   - 影響：自分だけ/全員/特定ネットワーク
   - 変更点：直前に変えた設定/接続先/VPN/プロキシ

1. 名前解決（DNS）
   - 目的：ドメイン→IP が引けるか
   - コマンド：`nslookup example.com（Windows）`/ `dig example.com（Linux）`
   - 次：OK→2、NG→DNS設定/ネットワーク設定を確認

2. 到達性（ICMP/経路）
   - 目的：IPに到達できるか、どこで止まるか
   - コマンド：`ping <ip>`、`tracert <ip>（Windows）`/ `traceroute <ip>（Linux）`
   - 次：OK→3、NG→ローカルGW/回線/VPN/FWを疑う

3. ポート（TCP/UDP）
   - 目的：目的ポートが開いているか
   - コマンド：`nc -vz <host> <port>（Linux/WSL）`、`Test-NetConnection -ComputerName <host> -Port <port>（Windows）`
   - 次：OK→4、NG→FW/ACL/SG/プロキシを疑う

4. アプリ層（HTTP/HTTPS）
   - 目的：HTTPレスポンスが返るか
   - コマンド：`curl -I https://example.com（Linux）`
            ：`curl.exe -I https://example.com（Windows PowerShell）`
            ：`Invoke-WebRequest -Method Head https://example.com（Windows PowerShell）`
   - 次：2xx/3xx→アプリ到達OK、4xx/5xx→サーバ側/認証/設定を疑う

## 影響範囲の切り分け
- 自分だけ：端末設定/プロキシ/VPN/DNS/hosts
- 全員：回線/ルータ/DNSサーバ/上位NW
- 特定ネットワークだけ：拠点NW/SSID/ACL/経路

## よく使うコマンド（例）
- DNS：`dig example.com` / `nslookup example.com`
- 到達性：`ping -c 4 <ip>` / `traceroute <ip>（Linux）`
        ：`ping <ip> `/ `tracert <ip>（Windows）`
- HTTP：`curl -I https://example.com（Linux）`
      ：`curl.exe -I https://example.com（Windows PowerShell）`
- ポート：`ss -tulpn / nc -vz <host> <port>（Linux）`
      ：`Test-NetConnection -ComputerName <host> -Port <port>（Windows）`

## 典型ケース（追記していく）
### Case1：Windows PowerShellで curl が失敗する（Uri入力を求められる）
- 症状：`curl -I https://example.com` 実行時に `Uri` の入力を求められる
- 原因：PowerShellの `curl` は `Invoke-WebRequest`の別名で、引数解釈が異なる
- 確認：`Get-Command curl`（PowerShellで別名を確認）
- 対応：`curl.exe -I https://example.com` または `Invoke-WebRequest -Method Head https://example.com`
- 証拠：`HTTP/1.1 200 OK` を取得できたログを保存する

### Case2：
### Case3：
