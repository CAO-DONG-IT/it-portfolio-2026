# CCNA Day12 学習ノート
## The Life of a Packet（既習範囲の統合復習）

## 1. 学習目的
- ホスト間通信で、パケットが各ホップでどのように処理されるかを説明できるようにする
- L2（Ethernet）とL3（IPv4）の役割分担を明確にする
- ARP Request/Reply と実データ転送（IPv4）の違いを整理する

---

## 2. 結論（最重要）
1. エンドツーエンドで基本的に維持されるのは **Src IP / Dst IP**（NATなし前提）
2. ホップごとに変わるのは **Src MAC / Dst MAC / FCS**
3. ルータは受信時にL2を外してL3を確認し、転送時に新しいL2ヘッダを付け直す
4. ARPは「次ホップMAC解決」のための補助プロトコルで、通常データ転送そのものではない
5. 通常データ転送時のEtherTypeはIPv4なら **0x0800**、ARPは **0x0806**

---

## 3. 通信の流れ（PC1 → PC4 例）

### 3.1 送信元ホスト（PC1）側
- 宛先IP（例：192.168.4.1）が同一セグメント外と判断
- デフォルトゲートウェイ（例：192.168.1.254）へ転送する必要がある
- ゲートウェイMACが未学習ならARPを実行

### 3.2 ARP（MAC解決）
#### ARP Request（ブロードキャスト）
- Ethernet:
  - Dst MAC = ff:ff:ff:ff:ff:ff
  - Src MAC = PC1のMAC
  - EtherType = 0x0806
- ARP本体:
  - OPER = 1（request）
  - SHA/SPA = PC1のMAC/IP
  - THA = 00:00:00:00:00:00
  - TPA = 192.168.1.254（GW）

#### ARP Reply（通常ユニキャスト）
- Ethernet:
  - Dst MAC = PC1のMAC
  - Src MAC = GWのMAC
  - EtherType = 0x0806
- ARP本体:
  - OPER = 2（reply）
  - SHA/SPA = GWのMAC/IP
  - THA/TPA = PC1のMAC/IP

---

## 4. 実データ（IPv4）フレーム送信
ARP完了後、PC1はIPv4パケットをEthernetフレームで送信する。

- Ethernet Header
  - Dst MAC = GWのMAC
  - Src MAC = PC1のMAC
  - EtherType = 0x0800（IPv4）
- Payload
  - IPv4 Packet（IPヘッダ + 上位層データ）
- Trailer
  - FCS

※IPパケットは「L2 payload」として運ばれる。

---

## 5. ルータ受信後の処理（R1視点）

1. L2受信確認（Dst MAC/FCS）
2. L2ヘッダ/トレーラを除去（デカプセル化）
3. IPv4ヘッダを確認（Dst IP、TTL、Protocolなど）
4. ルーティングテーブル参照（次ホップ決定）
5. TTLを-1し、IPv4ヘッダチェックサム再計算
6. 出力側で新しいL2ヘッダを再付与（再カプセル化）
7. 次ホップへ転送

---

## 6. IPv4ヘッダ復習（Day10連携）
- Version / IHL / Total Length
- DSCP / ECN
- Identification / Flags / Fragment Offset
- TTL
- Protocol（TCP=6, UDP=17, ICMP=1, OSPF=89）
- Header Checksum
- Src IP / Dst IP
- Options（IHL>5の場合）

### 補足
- TTLはルータ通過ごとに減少し、0で破棄
- Header ChecksumはIPv4ヘッダのみ対象
- MTU超過時はフラグメント関連フィールドが重要

---

## 7. ARPと転送の関係（混同防止）
- ARPは「IP通信前の準備（次ホップMAC確認）」
- 転送の本体はIPv4パケット
- Request/Replyの種別はARP報文内 **OPER** で判別
- EtherType 0x0806 は「ARPであること」を示す識別子

---

## 8. よくある誤解と修正
- 誤解：ルータはL2だけ見て転送する  
  - 修正：ルータはL3（IP）を見て転送先を決める
- 誤解：ARP完了前に完成した最終フレームを保持する  
  - 修正：実際は送信待ちデータを保持し、解決後にL2封装して送る
- 誤解：ARPもTCP/UDP上で動く  
  - 修正：ARPはEtherTypeで直接識別されるL2隣接プロトコル

---

## 9. つまずきと対処（Case形式）
- 現象：Dst MAC不明時の転送挙動が曖昧
- 原因：ARPとIPv4転送を同一フレーム処理として混同
- 対処：ARP（0x0806）とIPv4データ（0x0800）を分離して理解

- 現象：どの情報がホップごとに変わるか不明確
- 原因：L2/L3の責務を分けて見ていない
- 対処：L2（MAC/FCS）可変、L3（Src/Dst IP）原則維持で整理

---

## 10. 一文まとめ
- パケット転送の本質は「L3は目的地情報を維持し、L2はホップごとに貼り替える」ことである。

---

## 面接で話せる内容
- まず、同一セグメント通信と異なるセグメント通信を分けて説明できます。異なるセグメントではデフォルトゲートウェイのMAC解決が必要になるため、ARP Request/Replyが先に動作します。  
- 次に、実データ転送ではIPv4パケットをEthernetフレームに載せ、ルータ到達ごとにL2をデカプセル化→L3判定→再カプセル化する流れを説明できます。  
- 最後に、ホップごとに変化する情報（MAC/FCS/TTL）と、原則維持される情報（Src/Dst IP）を分けて説明し、トラブル時の切り分けに使える形で整理しています。
