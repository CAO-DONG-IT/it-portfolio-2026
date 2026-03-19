# WEEK3 Weekly Log

---

## 2026-03-19（Day1）
### 今日の目標
- 面接前の準備として、公開済みの GitHub 成果物を棚卸しし、説明しやすい状態に整理する
- README と各フォルダの導線を見直し、代表成果物に辿りやすい構成にする
- Network / Linux / Java / AWS の現在地を整理し、口頭説明の土台を作る

### 今日やったこと
- 学習時間：GitHub 整理・復習中心
- ルート README を面接向けに整理し、代表成果物（Network / Linux）への導線を明確化した
- AWS フォルダは未着手状態に合わせて README を修正した
- digest フォルダはテンプレートのみで未着手であることを明記した
- Java フォルダの README / STRUCTURE / ConsoleTodo 概要を現状に合わせて整理した
- Linux フォルダではノートを `notes/` 配下へ整理し、ディレクトリ構成を見直した
- Network README についても、公開進捗と代表成果物が分かりやすい形に整理した
- 面接で説明する主軸を、Network（CCNA）と Linux に絞って準備方針を固めた

### 予定との差分（未達）
- 新規学習の進行は行わず、既存成果物の整理と復習を優先した

### 詰まった点（現象／原因／解決）
- 現象：`git push` 実行時に `fetch first` で拒否された
- 原因：リモートの `main` にローカル未反映の更新があり、fast-forward できなかった
- 解決：`git pull --rebase origin main` でリモート更新を取り込み、その後 push した

- 現象：Linux ノートの移動時に `git mv` のパス指定でエラーになった
- 原因：作業ディレクトリが `linux/` 配下の状態で、相対パスを誤って指定した
- 解決：現在位置を確認したうえで、`notes/` への正しい相対パスで移動し直した

### 明日の予定（2026-03-20）
- 面接で説明する代表成果物（CCNA Day4 / Day5 / Linux IPアドレスとホスト名）を復習する
- GitHub 上の代表ファイルごとに、1分程度で説明できる要点を整理する
- 更新期間の説明と、ChatGPT をどう補助的に使っているかの答え方を固める

### 今日のキーワード（3〜5個）
- README整理
- 成果物導線


### 証拠リンク（リポジトリ内パス）
- `README.md`
- `network/README.md`
- `linux/README.md`
- `java/README.md`
- `java/STRUCTURE.md`
- `aws/README.md`
- `weekly-log/week10.md`
