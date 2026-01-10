# Network（CCNAベース）

## 目的：

インフラ運用/保守に必要なネットワーク基礎（CCNA範囲）を体系的に学び、Linuxの障害切り分けにも接続する。

## 主教材（YouTube：Jeremy's IT Lab）
本ディレクトリのNetwork学習は、YouTube「Jeremy's IT Lab」の **CCNA 200-301（Free CCNA）** を主教材として、自習ログと成果物を残す。

- YouTubeチャンネル：https://www.youtube.com/@JeremysITLab
- 再生リスト（Free CCNA v1.1 200-301 | Complete Course 2025）：https://www.youtube.com/playlist?list=PLxbwE86jKRgMpuZuLBivzlM8s2Dk5lXBQ

## 学習の進め方（証拠が追える形にする）
- 視聴方針：**英語版（主）＋同内容の日本語版を交互に視聴**し、理解のズレを減らす。
- ノート：動画の要点は `network/notes/` に Markdown で整理する。
- 画像（ノート用）：動画のスクリーンショットは `network/notes/images/` に保存し、ノートから参照する。
- 実験の証跡：Packet Tracer などのトポロジ／実行結果は `network/screenshots/` に保存する。
- ログ連携：週次ログ（`weekly-log/`）の「証拠リンク」欄から、当日のノート／スクリーンショットへ辿れるようにする。

（例）
- ノート：`network/notes/ccna-day3-tcpip-model.md`
- ノート画像：`network/notes/images/day3_encapsulation.jpg`
- 実験証跡：`network/screenshots/day2_lab_connecting-devices_topology.jpg`

## 学習範囲（予定）：
- TCP/IP基礎（DNS / HTTP / TLS / TCP / UDP）
- ルーティング基礎（スタティック / デフォルト）
- サブネット（CIDR / サブネットマスク / 計算）
- VLAN / NAT / ACL（概要レベル）
- トラブルシューティング（ping / traceroute / dig / curl / ss）

## 成果物：
- `troubleshooting-network.md`：最短切り分け手順
- `subnetting-practice.md`：計算練習と解法テンプレ
