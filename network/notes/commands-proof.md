# 基本コマンド再現ログ（JST 2026-01-07）

目的：ネットワークの一次切り分け（L3〜L7）を、最小コマンドで再現できる形で残す。  
（面接では「どの層でOK/NGか」を短時間で説明できることを重視する）

## 用語（JP / EN / 中文）
- 疎通確認 / reachability test / 连通性确认
- 名前解決 / DNS resolution / 域名解析
- 経路確認 / route tracing / 路由追踪
- HTTPヘッダ / HTTP headers / HTTP头
- 切り分け / isolation / 分层排查

---

## 1) 疎通確認（IP直）
- コマンド：`ping 1.1.1.1`
- 期待：応答が返る（損失 0% が理想）
- 結果：送信=4 受信=4 損失=0（0%）、時間=7〜8ms、TTL=56
- 判断：**L3（IP疎通）OK**

## 2) DNS確認（名前解決）
- コマンド：`nslookup example.com`
- 期待：DNSサーバと A/AAAA レコードが返る
- 結果：DNS=8.8.8.8（dns.google）、example.com → 104.18.27.120 / 104.18.26.120（IPv6も返却）
- 判断：**DNS OK**（名前解決できる）

## 3) 経路確認（traceroute/tracert）
- コマンド：`tracert 1.1.1.1`（Windows） / `traceroute 1.1.1.1`（Linux）
- 期待：最終ホップで宛先に到達する
- 結果：最終hopで 1.1.1.1 に到達（途中に `*` があるが最終到達）
- 補足：途中の `*` は「ルータがICMPに応答しない／制限している」等で起きるため、**最終到達が重要**
- 判断：**経路 OK**（到達している）

## 4) HTTP確認（ヘッダのみ）
- コマンド：`curl.exe -I https://example.com`（Windows）
- 期待：`HTTP/1.1 200` などのステータス行が返る
- 結果：`HTTP/1.1 200 OK`（Server: cloudflare）
- 判断：**L7（HTTP）OK**

---

## まとめ（切り分け結論）
- IP疎通：OK
- DNS：OK
- 経路：OK（途中 `*` は許容、最終到達を確認）
- HTTP：OK（200）

## 次に確認するもの（学習TODO）
- IPv4 / サブネット（CIDR）の理解（ローカルIP設定の読み解き）
- 「疎通NG時」の優先確認：`ipconfig`/`ip a`、デフォルトGW、DNS設定、ファイアウォール、プロキシ
