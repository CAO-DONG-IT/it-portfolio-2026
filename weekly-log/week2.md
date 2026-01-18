# week2

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

------
