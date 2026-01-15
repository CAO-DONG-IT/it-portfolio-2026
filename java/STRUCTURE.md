# Java 構成（現状：一時停止）

## 位置づけ
現在は Network（CCNA）を主軸に学習しているため、Java は新規学習・改修を一時停止している。
ただし、基礎的な実装力とドキュメント化の証跡として、既存成果物（ConsoleTodo）は保持する。

## ディレクトリ
- ConsoleTodo/
  - README.md（入口：実行手順・バージョン導線）
  - v0.1/（起動確認）
  - v0.2/（メニュー／追加・一覧）
  - v0.3/（予定：削除・更新）
  - v0.4/（予定：ファイル保存・読み込み）
  - v0.5/（予定：例外処理・リファクタ）
- notes/
  - java-basics.md（基礎メモ）
- screenshots/
  - README.md（証跡ルール）
  - *.png（動作確認のスクリーンショット）

## 運用ルール（最小）
- 各バージョンは「動く状態」でコミットする
- 各バージョンの README に「実行手順」と「操作例（入出力）」を残す
- 証跡（スクリーンショット）が必要な場合は screenshots/ に保存し、README から参照する
- 学習を実施した日は weekly-log に記録し、成果物パスを残す

## 入口
- Java：`java/README.md`
- ConsoleTodo：`java/ConsoleTodo/README.md`
