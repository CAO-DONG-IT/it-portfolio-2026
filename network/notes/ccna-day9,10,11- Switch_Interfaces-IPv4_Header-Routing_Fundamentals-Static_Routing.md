# CCNA 学習ノート（Day9–Day11 統合）
## 対象：Day9 Switch Interfaces / Day10 IPv4 Header / Day11 Routing Fundamentals Part1 / Static Routing Part2

## 1. 学習ゴール
- L2スイッチのインターフェース確認・基本運用を理解する
- IPv4ヘッダの各フィールドと役割を説明できるようにする
- ルーティングの基礎（経路選択の考え方）を整理する
- 静的ルート／デフォルトルートを設定し、検証できるようにする

---

## 2. Day9：Switch Interfaces

### 2.1 主要コマンド
- `show ip interface brief`  
  - インターフェースのIP/Status/Protocolを一覧で確認
- `show interfaces status`  
  - Port/Name/VLAN/Duplex/Speed/Type の運用情報を確認
- `show interfaces <interface>`  
  - 詳細情報（CRC、runts、giants、input/output errors など）を確認

### 2.2 学習ポイント
- スイッチでは未接続ポートは基本 `down/down` 表示
- 未使用ポートは `description` 付与 + `shutdown` で管理する
- `interface range` により複数ポートを一括設定できる
- Duplex/Speed の不一致は通信品質低下の原因になるため要確認

### 2.3 実務的な確認順
1. 一覧コマンドで異常箇所を特定  
2. 対象IFの詳細でエラー統計を確認  
3. 修正後に再度 `show` で差分確認

---

## 3. Day10：IPv4 Header

### 3.1 全体像
- IPv4ヘッダはL3転送の制御情報を持つ
- ヘッダ長は可変（最小20B、最大60B）
- `IHL` はヘッダ長、`Total Length` はパケット全体長を示す

### 3.2 主要フィールド（要点）
- Version：IPバージョン（IPv4は4）
- IHL：ヘッダ長（4Byte単位）
- Total Length：ヘッダ+データの総長（Byte）
- DSCP/ECN：優先制御・輻輳通知
- Identification / Flags / Fragment Offset：フラグメント制御
- TTL：ルータ通過ごとに減算、0で破棄（ループ抑止）
- Protocol：上位プロトコル識別（ICMP=1, TCP=6, UDP=17）
- Header Checksum：IPv4ヘッダの誤り検出
- Source / Destination Address：送信元/宛先IP
- Options：任意フィールド（通常は未使用）

### 3.3 混同しやすい点
- IHL（ヘッダ長）と Total Length（全体長）は別
- Header Checksum はヘッダのみ対象（L4データは対象外）
- TTL は到達性ではなく「生存ホップ数」の制御値

---

## 4. Day11 Part1：Routing Fundamentals

### 4.1 ルーティングの基本
- ルータは宛先IPを見てルーティングテーブルを参照し転送する
- 判断要素：宛先ネットワーク、出力IF、次ホップ
- 宛先が自分のIFアドレスならローカル処理、遠隔なら転送

### 4.2 学んだ概念
- Connected Route（直結）
- Remote Network（遠隔）
- Next-hop（次ホップ）
- WANを跨ぐ通信ではL3経路選択が必須

### 4.3 切り分け観点
- 宛先への経路があるか
- 次ホップへ到達可能か
- 戻り経路（返り）が存在するか

---

## 5. Day11 Part2：Static Routing

### 5.1 静的ルートの考え方
- 管理者が手動で経路を定義する方式
- 小規模/固定経路環境で有効
- 経路数増加時は保守負荷が上がる

### 5.2 主要コマンド
- 次ホップ指定
```text
ip route <宛先ネットワーク> <サブネットマスク> <次ホップIP>
```

- 出力IF指定
```
ip route <宛先ネットワーク> <サブネットマスク> <出力IF>
```

- デフォルトルート
```
ip route 0.0.0.0 0.0.0.0 <次ホップIP or 出力IF>
```

### 5.3 検証コマンド
- `show ip route`（経路反映確認）
- `show running-config | include ip route`（設定確認）
- `ping <宛先IP>`（疎通確認）
- `traceroute <宛先IP>`（経路確認、可能な場合）

---

## 6.Labで実施した流れ（統合）
- 1.L2インターフェース状態を確認（brief/status）

- 2.必要なIF設定・有効化を実施

- 3.L3の転送要件を確認（宛先/次ホップ/返り）

- 4.静的ルート・デフォルトルートを設定

- 5.`show ip route` で反映確認

- 6.`ping`（必要に応じて `traceroute`）で疎通検証

- 7.問題発生時は「IF状態 → 経路 → 次ホップ → 返り経路」で切り分け

---

## 7. つまずきと対処（再発防止）

- 現象：モード不一致でコマンドエラー
  -対処：`show`/`copy` は原則 #（特権EXEC）で実行、必要時のみ `do`

- 現象：保存コマンド不完全
  - 対処：copy running-config startup-config または write memory

---

## 8.重要コマンド最小セット

```

show ip interface brief
show interfaces status
show interfaces <interface>
show ip route
show running-config | include ip route
ip route <network> <mask> <next-hop>
ip route 0.0.0.0 0.0.0.0 <next-hop>
copy running-config startup-config

```

---

## 9. まとめ
- Day9ではL2インターフェース運用（状態確認・未使用ポート管理）を整理した。

- Day10ではIPv4ヘッダの各フィールドと転送時の意味を学習した。

- Day11ではRouting Fundamentals（Part1）で転送判断の原理を押さえ、Static Routing（Part2）で実装・検証まで行った。

- 設定だけで終わらせず、`show` と疎通確認で検証する流れを固定化できた。

---

## 10.話せる内容

- 1.Day9（L2）：show ip interface brief / show interfaces status でポート状態を確認し、未使用ポートを description + shutdown で管理しました。

- 2.Day10（L3ヘッダ）：IPv4ヘッダの主要フィールド（IHL、Total Length、TTL、Protocol、Checksum など）の役割を理解し、パケット転送の前提知識を固めました。

- 3.Day11 Part1（基礎）：ルータがルーティングテーブルを参照して宛先ネットワークへ転送する仕組みを整理し、次ホップと返り経路の重要性を理解しました。

- 4.Day11 Part2（実装）：静的ルートとデフォルトルートを設定し、show ip route と ping/traceroute で到達性を検証しました。

- 5.再現性：学習ノート、Labログ、トラブル対処をGitHubに残し、手順と判断理由を説明できる形にしています。
