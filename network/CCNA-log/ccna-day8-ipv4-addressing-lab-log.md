# CCNA Day8 Labログ｜IPv4 Addressing（R1設定・確認・保存）

## 目的
- R1のIFに適切なIPv4アドレスを設定し、IFを有効化する
- `show` コマンドで状態確認し、設定を保存する
- PC間疎通試験（PC1→PC2/PC3）につなげる

---

## 1. 主要な実行ログ（抜粋）

### 1-1. モード誤りの確認（ユーザEXECでは設定不可）
```text
R1>conf t
        ^
% Invalid input detected at '^' marker.
```
- `R1>` では`conf t` 不可。`enable` が必要。

### 1-2 IF状態確認（設定後）
```
R1#show ip int br
Interface              IP-Address      OK? Method Status                Protocol
GigabitEthernet0/0     15.255.255.254  YES manual up                    up
GigabitEthernet0/1     182.98.255.254  YES manual up                    up
GigabitEthernet0/2     201.191.20.254  YES manual up                    up
Vlan1                  unassigned      YES unset  administratively down down
```

### 1-3 running-config確認
```
R1#show running-config
...
hostname R1
...
```
- ホスト名 `R1` を確認。

### 1-4 詳細IF情報確認（例：G0/0）
```
R1#show interfaces
GigabitEthernet0/0 is up, line protocol is up (connected)
  Description: ## to SW1 ##
  Internet address is 15.255.255.254/8
...
```

- IF description とIP設定を確認。


### 1-5 保存コマンドの正しい実行
```
R1#copy running-config start
Destination filename [startup-config]?
Building configuration...
[OK]

R1#write memory
Building configuration...
[OK]
```
- 永続化成功。

---

## 2. 現時点の確認結果
- R1の3インターフェース（G0/0, G0/1, G0/2）にIP設定済み

- 3インターフェースとも `up/up` を確認

- 設定保存（startup-config反映）まで完了

---

## 3. 追加予定（証跡）

- PC1 → PC2 の ping 成功スクリーンショット(network/notes/images)

- PC1 → PC3 の ping 成功スクリーンショット(network/notes/images)

- 必要に応じて `show ip route` の出力（R1）を追記













