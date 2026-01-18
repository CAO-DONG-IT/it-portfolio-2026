# CCNA Day 4: Cisco IOS CLI 操作ログ (2026-01-18)

## 1. ホスト名設定 [ホスト名設定 / Hostname Configuration / 主机名配置]
グローバルコンフィギュレーションモードから、各デバイスにホスト名を割り当て。

```
# Router の設定
Router> enable
Router# configure terminal
Router(config)# hostname R1
R1(config)# exit

# Switch の設定
Switch> enable
Switch# configure terminal
Switch(config)# hostname SW1
SW1(config)# exit
```

## 2. パスワード設定と暗号化 [パスワード暗号化 / Password Encryption / 密码加密]
`enable password`（平文）と `enable secret`（ハッシュ化）の違いを検証し、全体のセキュリティを強化。

```
# 特権モードパスワードの設定
R1(config)# enable password CCNA
R1(config)# enable secret Cisco

# 全パスワードの強制暗号化サービスを有効化
R1(config)# service password-encryption
```
## 設定の確認と保存 [設定保存 / Save Configuration / 保存配置]
`show running-config` で設定状況を確認し、`write` コマンドで `NVRAM` に保存。
```
# 暗号化状態の確認
R1# show running-config
!
enable secret 5 $1$mERr$YlCkLMcTYWwkF1Ccndtll.
enable password 7 08026F6028
!

# 設定の保存（再起動後の消失防止）
R1# write
Building configuration...
[OK]
```
