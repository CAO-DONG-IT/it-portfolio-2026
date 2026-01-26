# CCNA Day6｜Ethernet LAN Switching（Part 2）学習ノート

## ゴール
- `ARP（IP→MAC）`と `ICMP（ping）`の役割を区別して説明できる
- スイッチが MAC アドレステーブルを「送信元MAC」から学習する流れを説明できる
- `show mac address-table` / `clear mac address-table dynamic` を使って学習状態を確認・リセットできる

---

## 1. ARP：IPv4で「IP→MAC」を解決する
- 同一セグメント内（同一L2）でIPv4通信を行う場合でも、フレーム転送には **宛先MAC** が必要。
- ARP は「宛先IPに対応する宛先MAC」を知るための仕組み。

### ARP Request / Reply の違い
- `ARP Request`：ブロードキャスト（誰がこのIP？）
- `ARP Reply`：ユニキャスト（私です。このMACです）

---

## 2. ICMP（ping）：到達性（reachability）の確認
- `ping` は `ICMP Echo Request / Echo Reply` を使って「相手に届くか」を確認する。
- ただし、最初に` ARP `で MAC が分からないと L2 で配送できないため、**ping の前段として ARP が発生する**ことが多い。

---

## 3. スイッチのMAC学習と転送
### MACアドレステーブル（CAM）
- スイッチは受信フレームの **送信元MAC** を見て、
  「MAC → 受信ポート」を自動登録（DYNAMIC）する。

### Unknown Unicast と Flood
- 宛先MACが未学習（`Unknown Unicast`）の場合、受信ポート以外へ `Flood` して届けに行く。
- 学習が進むと `Known Unicast` になり、該当ポートのみに転送される。

---

## 4. IOSコマンド（必須）
### MACテーブル確認
```text
show mac address-table
```
### 設定モードから確認する場合
```
do show mac address-table
```
### 動的MACをクリア（学習状態のリセット）
```
clear mac address-table dynamic
```

---

## 5. CLIで詰まりやすい点（再発防止）
- `show` は基本的に特権EXEC（SW#）で実行する。

- `address-table` はハイフン必須（`show mac address table` はNG）。

- 不明コマンドを入力すると DNS lookup が走り `Translating ... `が出ることがある。

  - 恒久対策：`no ip domain-lookup`
  - `ctrl+shift+6`
