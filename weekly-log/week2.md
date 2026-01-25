# WEEK2 Weekly Log

---

## 2026-01-18（Day1）
### 今日の目標
- Network：Jeremy's IT Lab **CCNA 200-301 Day4「Intro to the CLI」** を視聴（英語版↔日本語版）し、ノート1本を作成する
- Network：CLI の基本操作（`?` / Tab補完 / モード移動 / `show` 系）を最小実作し、証跡を1点残す
- Linux：第4章（システム管理）の **日時・タイムゾーン** と **シンボリックリンク** を進め、検証ノート1本を作成する

### 今日やったこと
- 学習時間：4.0h（Network中心）
- Network（CCNA Day4 / Cisco IOS CLI）
  - ルータ/スイッチの **ホスト名設定**（`hostname`）を実施（例：R1 / SW1）
  - 特権保護：`enable password`（平文）と `enable secret`（ハッシュ化）を比較し、挙動の違いを確認した
  - パスワード暗号化表示：`service password-encryption` を有効化し、以降のパスワードが暗号化表示される状態にした
  - 設定の永続化：`write`（= running → startup）で保存し、再起動で消えないことを意識した
  - 詰まり対応：グローバル設定モードで `show` が実行できない
    - 原因：モード階層の違い（`show` は EXEC 系）
    - 対処：`do show ...` を使い、設定モードから確認できるようにした
- Linux（日時・タイムゾーン／シンボリックリンク）
  - `date` のフォーマット出力を習得（例：`+"%Y-%m-%d %H:%M:%S %Z"`）
  - 相対日時の扱いを確認（例：`date -d "-1 day"`）
  - シンボリックリンク：`/etc/timezone` を参照するリンクを作成して検証した
  - 重大学び：リンク作成時に **絶対パス** を使う必要性を確認した
  - 詰まり対応：相対パスで作成したリンクが循環し `Too many levels of symbolic links` が発生
    - 原因：相対パスの解決が想定とズレて自己参照になった
    - 対処：リンク先を絶対パスで作り直して解決した

### 予定との差分（未達）
なし

### 詰まった点（現象／原因／解決）
- 現象：グローバル設定モードで `show` コマンドが実行できない
- 原因：`show` は EXEC 系コマンドであり、設定モードの階層外
- 解決：`do show ...` を使用して同一画面で確認できるようにした
- 現象：`Too many levels of symbolic links`（循環参照）
- 原因：相対パスでリンクを作成し、パス解決が自己参照になった
- 解決：リンク先を **絶対パス** に修正して再作成した

### 明日の予定（2026-01-19）
- Network：Day4 の復習（ノートの不足を埋める）＋証跡を追加（必要なら）
- Network：`subnetting-practice.md` に練習問題を3問追記（固定手順で解く）
- Linux：`timedatectl`（確認中心）とリンクの注意点を Playbook に Case 追記（必要最小）

### 今日のキーワード（3〜5個）
- Cisco IOS CLI
- `do show`（モード階層）
- `enable password` / `enable secret`
- `service password-encryption`
- シンボリックリンク（絶対パス／循環参照）

### 証拠リンク（リポジトリ内パス）
- `weekly-log/week2.md`
- `network/CCNA-log/ccna-day4-ios-cli-log.md`
- `network/troubleshooting-network.md`
- `network/screenshots/day4_lab_ios-cli.jpg`
- `linux/notes/date-and-symlink.md`
- `linux/troubleshooting-playbook.md`

---

## 2026-01-25（Day2）
### 今日の目標
- Network：Jeremy's IT Lab **CCNA 200-301 Day5「Switching Part1」** を復習＋新規視聴し、Ethernetフレーム理解を固める
- Network：Preamble / SFD / Type・Length / FCS / Unicast フレームを整理し、ノート1本を完成させる
- Linux：第4章 **IPアドレスとホスト名** を進め、`ip` コマンド中心に確認ノート1本を作成する

### 今日やったこと
- 学習時間：4.0h（Network → Linux）
- Network（CCNA Day5 / Switching Part1）
  - Ethernetフレーム構造（Preamble / SFD / Type・Length / Payload / FCS）を整理した
  - **Preamble と SFD** を同期確立のための前処理として一体で理解した
  - **Type と Length** の違い（Ethernet II / IEEE 802.3）を値の範囲で整理した
  - **FCS** の役割（CRC による誤り検出）を確認した
  - **Unicast フレーム** の基本動作とスイッチ転送の前提を整理した
- Linux（IPアドレス／ホスト名）
  - `ifconfig` が非推奨であることを確認し、`ip addr` を使用して IP 情報を取得した
  - `lo`（ループバック）と `eth0` の役割を読み取り、表示項目を整理した
  - `eth0` 命名が環境依存であり、`ens33` 等と異なる理由を理解した
  - IPアドレスとホスト名の役割分担を整理した

### 予定との差分（未達）
なし

### 詰まった点（現象／原因／解決）
- 現象：Type フィールドと Length フィールドが混同しやすい
- 原因：Ethernet II と IEEE 802.3 で同一フィールドの解釈が異なるため
- 解決：値の範囲と上位プロトコル判別で整理し、ノートに明記した
- 現象：`ifconfig` コマンドが見つからない
- 原因：`net-tools` が標準でインストールされていない環境
- 解決：`ip addr` を使用する方針に切り替えた

### 明日の予定（2026-01-26）
- Network：CCNA Day5 Switching Part2 へ進む
- Network：MACアドレス学習後、フレーム転送を図で整理する
- Linux：IP設定変更と `hostnamectl` は確認ベースで最小実施する

### 今日のキーワード（3〜5個）
- Ethernet フレーム
- Preamble / SFD
- Type・Length
- FCS（CRC）
- `ip addr`

### 証拠リンク（リポジトリ内パス）
- `weekly-log/week2.md`
- `network/notes/ccna-day5_Switching_Part1.md`
- `linux/notes/ip_and_hostname.md`
