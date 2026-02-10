# Network Troubleshooting Playbook（固定フロー）

目的：ネットワーク障害を「層で迷わず」最短で切り分ける。  
運用では **再現手順・判断（OK/NG）・次アクション** をセットで残す。

## 用語（JP / EN / 中文）
- 切り分け / isolation / 分层排查
- 到達性 / reachability / 连通性
- 名前解決 / DNS resolution / 域名解析
- 経路 / route / 路由
- ポート / port / 端口
- プロキシ / proxy / 代理
- VPN / VPN / VPN

---

## 即断フロー（固定順）
0. 現象整理（いつ / どこで / 何が）
   - 影響：自分だけ / 全員 / 特定ネットワーク
   - 変更点：直前に変えた設定（Wi-Fi / VPN / Proxy / DNS / FW）
   - まず残す：エラー文、対象URL/ホスト、再現手順（最短）

1. 名前解決（DNS）
   - 目的：ドメイン → IP が引けるか
   - コマンド：`nslookup example.com`（Windows） / `dig example.com`（Linux）
   - 次：OK → 2、NG → DNS設定 / Proxy / VPN / ネットワーク設定を確認

2. 到達性（ICMP / 経路）
   - 目的：IP に到達できるか、どこで止まるか
   - コマンド：`ping <ip>`、`tracert <ip>`（Windows） / `traceroute <ip>`（Linux）
   - 次：OK → 3、NG → ローカルGW / 回線 / VPN / FW を疑う

3. ポート（TCP/UDP）
   - 目的：目的ポートが開いているか（L4）
   - コマンド：
     - Linux/WSL：`nc -vz <host> <port>`
     - Windows：`Test-NetConnection -ComputerName <host> -Port <port>`
   - 次：OK → 4、NG → FW / ACL / SG（Security Group）/ Proxy を疑う

4. アプリ層（HTTP/HTTPS）
   - 目的：HTTPレスポンスが返るか（L7）
   - コマンド：
     - Linux：`curl -I https://example.com`
     - Windows PowerShell：`curl.exe -I https://example.com`
     - 代替：`Invoke-WebRequest -Method Head https://example.com`
   - 次：
     - 2xx/3xx → 到達OK（アプリ側は概ね正常）
     - 4xx/5xx → サーバ側 / 認証 / 設定 / 依存サービスを疑う

---

## 影響範囲の切り分け（早見）
- 自分だけ：端末設定（Proxy/VPN/DNS/hosts/FW）
- 全員：回線/ルータ/DNSサーバ/上位NW
- 特定ネットワークだけ：拠点NW/SSID/ACL/経路/セグメント

---

## よく使うコマンド（例）
- DNS：`dig example.com` / `nslookup example.com`
- 到達性：`ping -c 4 <ip>` / `traceroute <ip>`（Linux）
        ：`ping <ip>` / `tracert <ip>`（Windows）
- HTTP：`curl -I https://example.com`（Linux）
      ：`curl.exe -I https://example.com`（Windows PowerShell）
- ポート：`ss -tulpn` / `nc -vz <host> <port>`（Linux）
      ：`Test-NetConnection -ComputerName <host> -Port <port>`（Windows）

---

## 典型ケース（追記していく）

### Case1：Windows PowerShellで curl が失敗する（Uri入力を求められる）
- 現象：`curl -I https://example.com` 実行時に `Uri` の入力を求められる
- 原因：PowerShellの `curl` は `Invoke-WebRequest` の別名で、引数解釈が異なる
- 対処：`curl.exe -I https://example.com` または `Invoke-WebRequest -Method Head https://example.com`
- 証拠：`HTTP/1.1 200 OK` を取得できたログを保存する

---

### Case 2：Cisco IOS でコマンドが認識されない（% Unrecognized command）
現象：グローバルコンフィギュレーションモード （(config)#）で `show` などの確認コマンドを実行すると` % Unrecognized command `と表示される。

原因：Cisco IOS のコマンド階層による制限。`show`は特権実行モードのコマンドであり、設定モードでは直接実行できないため。

対処：`exit`でモードを戻すか、コマンドの先頭に `do` を付けて（例：`do show running-config`）強制実行する。

証拠：`do show running-config` または `write（copy run start）`が正常に受け付けられた実行ログ。
      `network/CCNA-log/ccna-day4-ios-cli-log.md`を参照

---

### Case3：`R1>` で `conf t` が実行できない
- 現象：
  - `R1>conf t` 実行時に `% Invalid input detected` が表示される。
- 原因：
  - ユーザEXECモード（`R1>`）では設定コマンドを実行できない。
- 対処：
  - `enable` で特権EXECモード（`R1#`）へ移行してから `conf t` を実行する。

---

### Case 4：`copy running-config` が `% Incomplete command.`
- 現象：
  - `copy running-config` のみ入力すると `% Incomplete command.` になる。
- 原因：
  - コピー先（宛先）が未指定のため。
- 対処：
  - `copy running-config startup-config` を実行する。
  - 代替として `write memory` でも保存可能。

---

### Case 5：`R1(config)#` で `copy` が通らない
- 現象：
  - 設定モードで `copy running-config ...` が無効入力になる。
- 原因：
  - `copy` は特権EXECモードのコマンドであり、設定モードでは実行できない。
- 対処：
  - `end` または `Ctrl+Z` で `R1#` に戻ってから実行する。

---

### Case 6：`Translating "..."`
- 現象：
  - 不明な文字列入力後に `Translating "...".domain server ...` が表示され、止まったように見える。
- 原因：
  - 不明コマンドをホスト名として解釈し、DNS lookup を試行しているため。
- 対処：
  - `Ctrl+Shift+6` で中断する。
  - 再発防止として以下を設定する。
  ```text
  conf t
  no ip domain-lookup
  end
  ```
  
---
### Case 7：初回pingのみ timeout（25% loss）
- 現象：
  - 初回 ping で 1 回目のみ `Request timed out.`、以降は応答する。
- 原因：
  - ARP解決（宛先またはデフォルトゲートウェイのMAC学習）が先に発生するため。
- 対処：
  - 異常と断定せず、2回目以降の連続応答を確認する。
  - 必要に応じて `show arp` / `show ip interface brief` で補足確認する。

---












  
