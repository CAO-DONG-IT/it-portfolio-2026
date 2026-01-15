# TCP/IPモデル（要点まとめ）（JST 2026-01-07）

目的：TCP/IPモデルを「層ごとの役割」「アドレスの使い分け」「カプセル化」で混同なく説明できるように整理する。

## 用語（JP / EN / 中文）
- TCP/IPモデル / TCP/IP model / TCP/IP模型
- OSI参照モデル / OSI model / OSI模型
- カプセル化 / encapsulation / 封装
- デカプセル化 / decapsulation / 解封装
- PDU / protocol data unit / 协议数据单元
- ホップバイホップ / hop-by-hop / 逐跳
- エンドツーエンド / end-to-end / 端到端

---

## 1) まず「4層 or 5層」を固定する（混同防止）
- **実務では4層表記が多い**：Network Access に Physical を含める
- 動画や資料によっては **5層表記**（Physical を分ける）もある
- どちらでも対応関係は同じ。**このノートは「5層」表記で整理**する。

---

## 2) TCP/IP（5層）の役割（超要点）
| 層 | 役割（最短） | キーワード |
| --- | --- | --- |
| Application | アプリ同士の通信（データの意味） | HTTP/DNSなど |
| Transport | アプリ識別と信頼性（TCP/UDP） | **Port** |
| Internet | ネットワーク間の転送 | **IP / ルータ** |
| Network Access | 同一LAN内の転送 | **MAC / スイッチ** |
| Physical | 信号として送る | ケーブル/電波 |

### 覚え方（3つのアドレス）
- **MAC**：ホップバイホップ（同一LAN内）
- **IP**：エンドツーエンド（宛先ホスト）
- **Port**：アプリの入口（TCP/UDP）

---

## 3) OSIとの対応（最短）
- TCP/IP Application ＝ OSI 5〜7 相当
- TCP/IP Transport ＝ OSI 4 相当
- TCP/IP Internet ＝ OSI 3 相当
- TCP/IP Network Access + Physical ＝ OSI 1〜2 相当

---

## 4) カプセル化 / デカプセル化（必須）
### 送信側（上→下）
Data  
→（L4ヘッダ）Segment  
→（L3ヘッダ）Packet  
→（L2ヘッダ＋FCSなど）Frame  
→ Bits

### 受信側（下→上）
Bits → Frame → Packet → Segment → Data  
（ヘッダ/トレーラを外しながら上位へ渡す）

---

## 5) PDU（層ごとの呼び名）
- Application：Data（データ）
- Transport：Segment（セグメント）
- Internet：Packet（パケット）
- Network Access：Frame（フレーム）
- Physical：Bits（ビット）

---

## 6) Same-layer / Adjacent-layer（用語だけ押さえる）
- **Same-layer（論理）**：送信側の各層は「対向装置の同じ層」と会話している“ように見える”
- **Adjacent-layer（実装）**：同一装置内で上下の層がデータを受け渡す（実際の動き）

---

## 7) 実務の切り分け（最短フロー）
1. Physical / Network Access：リンクが上がっているか（ケーブル/IF/VLAN など）
2. Internet：IP到達・ルーティング（ping / 経路）
3. Transport：ポート（TCP/UDP）
4. Application：DNS/HTTP/認証/アプリ設定

### 面接での一言（短く）
- 「通信は上位から下位にカプセル化して送ります。IPはエンドツーエンド、MACはホップごと、ポートはアプリ識別です。問題が起きたらリンク→IP→ポート→アプリの順で切り分けます。」

---

## 参考：Protocol と Standard（1行）
- プロトコル：通信の手順・形式・意味（どう話すか）
- 標準：複数ベンダで相互接続できるよう公開仕様として統一したもの（IEEE/IETF/ISO 等）
