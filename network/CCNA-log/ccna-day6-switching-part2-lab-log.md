# CCNA Day6 Labログ｜Ethernet LAN Switching（Part 2）

## トポロジ（概要）
- Network：192.168.1.0/24
- PC1 → PC3（192.168.1.3）へ ping
- PC2 → PC4（192.168.1.4）へ ping
- SW1 / SW2 の MAC アドレステーブルの学習・クリアを確認

---

## 1) 疎通確認（PC側の証跡）

### PC1 → 192.168.1.3（PC3）
- 4/4 受信、loss 0% を確認（複数回実施）
```text
C:\>ping 192.168.1.3
Reply from 192.168.1.3: bytes=32 time=12ms TTL=128
...
Packets: Sent = 4, Received = 4, Lost = 0 (0% loss)
```
### PC2 → 192.168.1.4（PC4）
- 4/4 受信、loss 0% を確認（複数回実施）
```
C:\>ping 192.168.1.4
Reply from 192.168.1.4: bytes=32 time=12ms TTL=128
...
Packets: Sent = 4, Received = 4, Lost = 0 (0% loss)

```

---


## 2) SW1：MACアドレステーブル（学習の推移）

### 2.1 初期：空
```
SW1#show mac address-table
Vlan    Mac Address       Type        Ports
----    -----------       --------    -----
（エントリなし）
```

### 2.2 通信後：DYNAMIC が生成
```
SW1#show mac address-table
  1    0001.647b.3119    DYNAMIC     Gig0/1
  1    0004.9a6e.d870    DYNAMIC     Gig0/1
  1    0060.5c56.14d3    DYNAMIC     Fa0/2
  1    00d0.d3ad.9cab    DYNAMIC     Fa0/1
```

### 2.3 クリア：dynamic を削除 → 空に戻る
```
SW1#clear mac address-table dynamic
SW1#show mac address-table
（エントリなし）
```

---

## 3) SW2：MACアドレステーブル（学習の推移）
### 3.1 通信後：DYNAMIC が生成
```
SW2#show mac address-table
  1    0001.647b.3119    DYNAMIC     Fa0/2
  1    0004.9a6e.d870    DYNAMIC     Fa0/1
  1    0060.5c56.14d3    DYNAMIC     Gig0/1
  1    00d0.d3ad.9cab    DYNAMIC     Gig0/1
```

### 3.2 クリア：dynamic を削除 → 空に戻る
```
SW2#clear mac address-table dynamic
SW2#show mac address-table
（エントリなし）
```

---

## 4) つまずきメモ（要点のみ）
- `show mac address-table` は SW#（特権EXEC）で実行する。

- `ddress-table` のハイフンが必須。

- 不明コマンド入力で `Translating ...` が出た場合は DNS lookup が原因。恒久対策は `no ip domain-lookup`。

---

## 5) 結論

- `ping` 成功後、`SW1/SW2` の MAC アドレステーブルに `DYNAMIC` エントリが学習され、端末のMACが各ポートに紐づいた。

- `clear mac address-table dynamic` により学習結果をリセットできることを確認した。
