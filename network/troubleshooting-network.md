# Network Troubleshooting Playbook

## 即断フロー（固定順）
1) 現象整理（いつ/どこで/何が）
2) 名前解決（DNS）
3) 到達性（ICMP/経路）
4) ポート（TCP/UDP）
5) アプリ層（HTTP/HTTPS）
6) 影響範囲（自分だけ？全員？特定ネットワークだけ？）

## よく使うコマンド（例）
- DNS：`dig example.com` / `nslookup example.com`
- 到達性：`ping -c 4 <ip>` / `traceroute <ip>`
- HTTP：`curl -I https://example.com`
- ポート：`ss -tulpn` / `nc -vz <host> <port>`

## 典型ケース（追記していく）
- Case1：
- Case2：
- Case3：
