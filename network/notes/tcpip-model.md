# CCNA Day3：TCP/IP Model Actually Works（要点まとめ）

## 1. Protocol と Standard の違い
- **プロトコル（Protocol）**：
  通信の手順・形式・意味（「どう話すか」のルール）。
- **標準（Standard）**：
  複数ベンダで相互接続できるように、ルールを公開仕様として統一したもの。

## 2. 誰が標準を決めるか（代表例）
- **IEEE**：Ethernet など（主に L1/L2 まわり）
- **IETF**：TCP/IP など（RFC として公開）
- **ISO**：OSI参照モデル（教育・整理のためのモデル）

## 3. レイヤ化する理由（Layered model）
- **複雑さを分割**して理解・設計しやすくする
- **変更に強い**（ある層の変更が他層へ影響しにくい）
- **相互接続性**（ベンダが違っても同じ標準ならつながる）
- **トラブルシュートがしやすい**（層ごとに切り分け）

## 4. TCP/IPモデル（この動画の整理：5層）
> 実務では「Network Access に Physical を含めて4層」とする表現もあるが、ここでは動画に合わせて5層で整理する。

### 4.1 各層の役割（超要点）
- **Application**：
  アプリ同士の通信。データの作成・解釈を行う。
- **Transport**：
  アプリ間のエンドツーエンド通信。**ポート番号**でアプリを識別する（TCP/UDP）。
- **Internet**：
  ネットワークをまたぐホスト間通信。**IPアドレス**と**ルータ**で転送する。
- **Local Network（Network Access）**：
  ローカルネットワーク内のホップバイホップ配送。**MACアドレス**と**スイッチ**で転送する。
- **Physical**：
  ビット列を電気・光・電波として媒体に流す。

## 5. OSIモデルとの対応（覚え方）
- TCP/IP **Application** ＝ OSI（アプリケーション＋プレゼンテーション＋セッション）
- TCP/IP **Transport** ＝ OSI（トランスポート）
- TCP/IP **Internet** ＝ OSI（ネットワーク）
- TCP/IP **Local Network** ＋ **Physical** ＝ OSI（データリンク＋物理）

## 6. カプセル化 / デカプセル化（Encapsulation / Decapsulation）
### 6.1 送信側（上→下）
- Application：**Data**
- Transport：TCP/UDPヘッダ付与 → **Segment**
- Internet：IPヘッダ付与 → **Packet**
- Local Network：Ethernetヘッダ＋FCS等 → **Frame**
- Physical：**Bits** として送出

### 6.2 受信側（下→上）
- Physical → Local Network → Internet → Transport → Application の順で
  **ヘッダ/トレーラを外しながら**アプリへ渡す。

## 7. PDU（Protocol Data Unit）の名称
- L4（Transport）：**Segment**
- L3（Internet）：**Packet**
- L2（Local Network）：**Frame**
- L1（Physical）：**Bits**
- アプリ側：一般に **Data**

## 8. Same-layer / Adjacent-layer の考え方
- **Same-layer interaction（同一層通信：論理）**：
  送信側の各層は「対向装置の同じ層」と会話している“ように見える”。
- **Adjacent-layer interaction（隣接層連携：実際）**：
  同一装置内で上下の層がデータを受け渡す（実装上の動き）。

## 9. 図（PC→SW→R→R→SW→Server）で押さえるポイント
- **IP（Internet層）は基本エンドツーエンド**：
  宛先IPで相手ホストを指定し、ルータが中継する。
- **MAC（Local Network層）は区間ごと（ホップごと）**：
  ルータを越えるたびにフレームは作り直されるため、**MACアドレスは区間ごとに変わる**。
- **ポート（Transport層）はアプリ識別**：
  例：Web は 80/443、FTP制御は 21 など（代表的な例）。

## 10. 切り分け（現場の見方）
- まず **Physical/Local Network（リンクが上がるか）**
- 次に **Internet（IP到達・ルーティング）**
- その次に **Transport（ポート、TCP/UDP）**
- 最後に **Application（アプリ設定/認証/名前解決等）**

## 今日のキーワード（3〜5個）
- TCP/IPモデル、カプセル化、PDU、Same-layer/Adjacent-layer、ホップバイホップ
