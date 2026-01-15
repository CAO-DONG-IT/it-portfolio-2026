# Network（CCNAベース）

## 目的
インフラ運用・保守に必要なネットワーク基礎（CCNA範囲）を体系的に学び、Linuxの障害切り分けにも接続する。

## 主教材（YouTube：Jeremy's IT Lab）
本ディレクトリの Network 学習は、YouTube「Jeremy's IT Lab」の **CCNA 200-301（Free CCNA）** を主教材として、自習ログと成果物（証跡）を残す。

- YouTubeチャンネル：https://www.youtube.com/@JeremysITLab
- 再生リスト（Free CCNA v1.1 200-301 | Complete Course 2025）：https://www.youtube.com/playlist?list=PLxbwE86jKRgMpuZuLBivzlM8s2Dk5lXBQ

## 学習の進め方（証跡が追える形にする）
- 視聴方針：**英語版（主）＋同内容の日本語版を交互に視聴**し、理解のズレを減らす
- ノート：動画の要点は `network/notes/` に Markdown で整理する
- 画像（ノート用）：動画のスクリーンショットは `network/notes/images/` に保存し、ノートから参照する
- 実験の証跡：Packet Tracer などのトポロジ／実行結果は `network/screenshots/` に保存する
- ログ連携：週次ログ（`weekly-log/`）の「証拠リンク」欄から、当日のノート／スクリーンショットへ辿れるようにする

（例）
- ノート：`network/notes/ccna-day3-tcpip-model.md`
- ノート画像：`network/notes/images/day3_encapsulation.jpg`
- 実験証跡：`network/screenshots/day2_lab_connecting-devices_topology.jpg`

## 学習範囲（予定）
- TCP/IP基礎（DNS / HTTP / TLS / TCP / UDP）
- ルーティング基礎（スタティック / デフォルト）
- サブネット（CIDR / サブネットマスク / 計算）
- VLAN / NAT / ACL（概要レベル）
- トラブルシューティング（ping / traceroute / dig / curl / ss）

## 成果物（まず見る場所）
- `network/troubleshooting-network.md`：最短切り分け手順（層で迷わないための固定フロー）
- `network/subnetting-practice.md`：計算練習と解法テンプレ

## ノート（基礎の整理）
- `network/notes/network-basics.md`：ネットワーク基礎（用語・CIDR・一次切り分けの要点）
- `network/notes/osi-tcpip-arp_tcpip-model_merged.md`：OSI/TCP-IP/ARP（混同防止の統合ノート）
- `network/notes/commands-proof.md`：一次切り分けコマンドの再現ログ（証跡）

## 運用ルール（公開リポジトリ）
- 個人情報（氏名、住所、メール等）は含めない
- 結果だけでなく「再現手順」と「判断（OK/NG）」を残す
- 追加したノート／証跡は `weekly-log/` からも辿れるようにする
