# CCNA Day5｜Ethernet LAN Switching Part 1 学習ノート

## 1. Ethernet フレームの全体像

Ethernet における通信は **フレーム（Frame）** 単位で行われる。  
スイッチは IP アドレスを見ず、**Ethernet フレームのみ**を処理する。

```
[ Preamble ][ SFD ]
[ Destination MAC ][ Source MAC ][ Type ]
[ Payload ]
[ FCS ]
```

---

## 2. Preamble と SFD（フレーム開始までの準備）

### Preamble（プリアンブル）

- 長さ：**7バイト**
- ビット列：`10101010` の繰り返し
- 役割：
  - 送信側と受信側の **クロック同期**
  - 受信準備のための物理層制御情報

### SFD（Start Frame Delimiter）

- 長さ：**1バイト**
- ビット列：`10101011`
- 役割：
  - 「ここから Ethernet フレーム本体が始まる」ことを示す

### なぜ一組として扱われるか

- `Preamble` で同期を取り、`SFD` で開始位置を確定するため。
- どちらも **フレーム内容のデータではない**。
- `NIC` 内部では **連続したハードウェア処理**として実装されている。

---

## 3. MAC アドレス（[日]MACアドレス / [英]MAC Address / [中]MAC地址）

- 長さ：**48bit（6バイト）**
- 構成：
  - 前半 24bit：OUI（ベンダ識別）
  - 後半 24bit：デバイス固有番号
- 通常は **BIA（Burned-In Address）** として出荷時に設定される。

例：
```
E8:BA:70:11:28:74
```

---

## 4. Type / Length フィールドの正しい理解

### 役割

Type / Length フィールド（2バイト）は、以下のどちらかを示す。

- **Length**：ペイロード長（IEEE 802.3）
- **Type（EtherType）**：上位プロトコルの種類（Ethernet II）

### よく使われる EtherType

| プロトコル | EtherType |
| --- | --- |
| IPv4 | 0x0800 |
| IPv6 | 0x86DD |
| ARP  | 0x0806 |

※ Type フィールドは **プロトコル識別子**であり、  
※ IPv6 アドレス（128bit）を格納する領域ではない。

---

## 5. IPv6 アドレスはどこに存在するか

- IPv6 の送信元 / 宛先アドレスは **Ethernet ペイロード内（IPv6 ヘッダ）**に存在する。

```
[ Ethernet Header ]
└ Type = 0x86DD（IPv6）
[ IPv6 Header ]
└ 送信元 IPv6 アドレス
└ 宛先 IPv6 アドレス
```
---
## 6. FCS（Frame Check Sequence）

- 長さ：**4バイト**
- 使用方式：**CRC（Cyclic Redundancy Check）**
- 役割：
  - フレームが転送中に破損していないかを検出

### 重要ポイント

- **検出のみを行う**
- 修復・再送制御は行わない
- エラー検出時、フレームは破棄される

再送制御は TCP など **上位レイヤの責務**。

---

## 7. スイッチと MAC アドレステーブル

### MAC アドレステーブル（転送表）

- スイッチが **送信元 MAC アドレス**をもとに自動学習する。
- 登録内容：
```
MAC アドレス → 受信ポート
```

- 動的エントリは **一定時間で削除**される。

---

## 8. Unicast Frame の動作

### Unicast Frame とは

- **宛先 MAC アドレスが 1 台の端末を指すフレーム**。

### 2 種類の Unicast

| 種類 | 説明 | 動作 |
| --- | --- | --- |
| Known Unicast | MAC テーブルに登録済み | 該当ポートのみに転送 |
| Unknown Unicast | MAC テーブル未登録 | 受信ポート以外へ Flood |

※ Unknown Unicast は **異常ではなく正常な学習動作**。

---

## 9. スイッチの基本転送フロー

1. フレーム受信  
2. **送信元 MAC を学習**  
3. 宛先 MAC をテーブル検索  
 - 登録あり → `Unicast` 転送  
 - 登録なし → `Flood`  
4. `FCS` をチェックし、問題なければ転送

---

## 10. レイヤごとの責務整理（重要）

- **Layer1**
  - Preamble / SFD（同期・開始検出）
- **Layer2**
  - MAC アドレス
  - EtherType
  - FCS
- **Layer3**
  - IP アドレス（IPv4 / IPv6）

👉 スイッチは **Layer2 まで**しか処理しない。

---

## 11. 一文まとめ（復習・面接用）

- Ethernet スイッチは MAC アドレステーブルを用いてフレームを転送し、Type フィールドで上位プロトコルを識別するが、IP アドレスの中身は解釈しない。

---

## 学習メモ

- 「Preamble＝準備、SFD＝合図」
- 「Type はラベル、アドレスはペイロード内」
- 「FCS は検出のみ、修復なし」

---
