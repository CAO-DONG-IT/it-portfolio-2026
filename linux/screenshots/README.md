# Linux / Screenshots（証跡）

このディレクトリは、Linux学習（WSL/Ubuntu）の検証結果を「証跡」として保存するための場所です。
面接時に「動いた事実」や「切り分けの手順」を短時間で示せるよう、命名と参照ルールを統一します。

## 基本方針
- 可能な限り、コマンド出力は Markdown（ログ貼り付け）で残す
- スクリーンショットは「視覚的に伝わる価値が高いもの」に限定する
  - 例：サービス状態の変化、エラー発生と解消、設定画面の差分など

## 何を保存するか
- `systemctl status` の結果（active/inactive、エラーメッセージ含む）
- `journalctl` のログ抜粋（起動/停止、失敗理由の特定）
- 権限/所有者の確認（`ls -l`、`id`、`groups`、`getent` など）
- 典型的なエラーと解消（Permission denied、sudo 必要、unit 名の違い等）
- 構成が伝わる画面（ディレクトリ構造、設定ファイルの該当箇所の抜粋）

## 命名ルール
以下の形式で命名し、検索しやすくします。

`YYYY-MM-DD_dayX_<topic>_<command>_<result>.png`

例：
- `2026-01-11_day7_systemctl_ssh_status_active.png`
- `2026-01-11_day7_journalctl_ssh_start_stop.png`
- `2026-01-07_day3_users_groups_id_groups_ok.png`
- `2026-01-07_day3_usermod_addgroup_ok.png`

## README / Notes からの参照
各ノートやログ（例：`linux/day7/linux-systemctl-ssh-log.md`、`linux/notes/*.md`）の該当箇所から、
必要に応じてこのディレクトリのスクリーンショットへリンクします。

## 注意（公開リポジトリ運用）
- 個人情報（メール、ブラウザ通知、個人アカウント情報など）が写り込まないようにする
- IPアドレスやホスト名が不要な場合は、トリミングまたはマスクして保存する
- 画面全体ではなく、証拠として必要な範囲を切り出す（可読性優先）

