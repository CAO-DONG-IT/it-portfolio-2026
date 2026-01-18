# CCNA Day4｜Intro to the CLI（2026-01-18）

## 目的
- **CLI / Command Line Interface** の基本操作を理解し、Cisco機器の初期設定ができる状態にする。
- **モード（prompt）** と **設定ファイル（running / startup）** を混同しない。
- パスワード設定（enable password / enable secret）と暗号化（type 7 / type 5）を区別できるようにする。

## 用語（JP / EN / 中文）
- [日/英/中] **CLI / Command Line Interface / 命令行界面**
- [日/英/中] **GUI / Graphical User Interface / 图形界面**
- [日/英/中] **コンソールポート / console port / 控制台口**
- [日/英/中] **ターミナルエミュレータ / terminal emulator / 终端仿真器（PuTTY等）**
- [日/英/中] **ユーザーEXECモード / user EXEC mode / 用户EXEC模式**
- [日/英/中] **特権EXECモード / privileged EXEC mode / 特权EXEC模式**
- [日/英/中] **グローバル設定モード / global configuration mode / 全局配置模式**
- [日/英/中] **実行中設定 / running-config / 运行配置**
- [日/英/中] **起動時設定 / startup-config / 启动配置**
- [日/英/中] **暗号化タイプ / encryption type / 加密类型（type 7 / type 5）**

---

## 1) 何ができるようになるか（結論）
- Console接続でCLIに入り、**モードを切り替えながら**設定できる。
- `show running-config` / `show startup-config` で設定を確認できる。
- `copy running-config startup-config` で設定を保存できる。
- `enable password` と `enable secret` の違い、および暗号化の見え方（type 7 / type 5）を説明できる。

---

## 2) 接続（初期設定はConsole）
### 2.1 Console接続が必要な理由
- 初期状態ではリモート（SSH/Telnet）より先に、**Console Port** でアクセスするのが基本。

### 2.2 ケーブルと接続イメージ
- Rollover cable（例：DB9→RJ45、DB9→USB など）を利用するケースがある。

### 2.3 ターミナル設定（Ciscoデフォルト）
- Baud rate：9600
- Data bits：8
- Stop bits：1
- Parity：None
- Flow control：None  
（よく言う **9600 8N1**）

---

## 3) IOSの「モード」とプロンプト（prompt）
### 3.1 ユーザーEXECモード
- 例：`R1>`
- できること：基本的な確認コマンド（限定的）

### 3.2 特権EXECモード
- 例：`R1#`
- 移動：`R1> enable`
- できること：設定確認、保存、各種管理コマンド

### 3.3 グローバル設定モード
- 例：`R1(config)#`
- 移動：`R1# configure terminal`（短縮：`conf t`）
- 戻る：`exit`（1つ上へ） / `end`（特権EXECへ）

---

## 4) まず覚える操作（効率とミス防止）
- `?`：その場で使えるコマンド候補を表示（どのモードでも使える）
- `Tab`：コマンド補完（入力ミス削減）
- コマンドは短縮できる（例：`conf t`）が、**最初は正式形も読めるようにする**

---

## 5) 設定ファイル（running / startup）
### 5.1 役割
- `running-config`：今動いている設定（変更はここに反映）
- `startup-config`：再起動後に読み込まれる設定

### 5.2 確認コマンド
```text
R1# show running-config
R1# show startup-config
```

### 5.3 保存（running → startup）
```text
R1# copy running-config startup-config
```
（同義の例：`write` / `write memory`）

---

## 6) パスワード設定と暗号化（重要）
### 6.1 enable password（平文→type 7で隠す対象）
- 設定（グローバル設定モード）
```text
R1(config)# enable password CCNA
```
- `service password-encryption` を有効にすると、設定ファイル上で **type 7** として表示される。  
  ※type 7 は弱い（教材でも「比較的簡単に解読可能」と説明される）。

### 6.2 service password-encryption（表示の暗号化）
```text
R1(config)# service password-encryption
```
- **現在のパスワード** と **今後設定するパスワード** を暗号化表示（type 7）にする。
- **enable secret には影響しない**（enable secret は常に暗号化される）。

無効化（将来の暗号化表示を止める）
```text
R1(config)# no service password-encryption
```
- 既に暗号化された表示が自動で平文に戻るわけではない点に注意。

### 6.3 enable secret（より強い暗号化）
```text
R1(config)# enable secret Cisco
```
- 教材の観点では **type 5（MD5）** として表示されることが多い。  
  ※実機/OSバージョンによって type 8 / type 9 などもあるが、Day4の範囲では「enable secret は強い」を押さえる。

### 6.4 優先順位（混同防止）
- `enable secret` を設定した場合、特権EXECへ入る際は **enable secret のパスワード** が求められる（enable password より優先）。

---

## 7) Lab（最小手順：R1/SW1 共通）
> 目的：hostname設定 → enable password → 暗号化表示 → enable secret → 保存

### 7.1 hostname
```text
(conf t)
R1(config)# hostname R1
SW1(config)# hostname SW1
```

### 7.2 enable password（CCNA）→確認
```text
R1(config)# enable password CCNA
R1(config)# end
R1# disable
R1> enable
```
- どのパスワードで入れるか確認する（この時点は CCNA）。

### 7.3 暗号化表示（type 7）→確認
```text
R1(config)# service password-encryption
R1# show running-config
```
- `enable password` が type 7 で表示されることを確認。

### 7.4 enable secret（Cisco）→確認
```text
R1(config)# enable secret Cisco
R1# show running-config
```
- `enable secret` が暗号化されて表示されることを確認（教材では type 5）。
- 再度 `disable` → `enable` して、**どのパスワードが要求されるか**を確認（enable secret が優先）。

### 7.5 保存
```text
R1# copy running-config startup-config
```

---

## 8) よくある詰まり（最短で直す）
- モードが違う（`>` / `#` / `(config)#`）  
  → プロンプトを見て、必要なモードに移動（`enable` / `conf t` / `end`）
- 設定したのに再起動で消える  
  → `copy running-config startup-config` を実行していない
- enable password と enable secret が混ざる  
  → **enable secret が優先**。どちらを使うかは「入る時の挙動」で確認する
- 暗号化表示の意味を誤解する  
  → `service password-encryption` は「表示を隠す（type 7）」で強固ではない。強いのは `enable secret` 側。

---

## 証跡
- `network/troubleshooting-network.md`
- `network/CCNA-log/ccna-day4-ios-cli-log.md`
- `network/screenshots/day4_lab_ios-cli.jpg`
