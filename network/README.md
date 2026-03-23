# Network（CCNAベース）

## 目的
インフラ運用・保守に必要なネットワーク基礎を、CCNA 範囲をベースに体系的に学習し、Linux の障害切り分けや基本的な運用理解につなげる。

## 面接で説明できる代表成果物（まずはここ）
- Cisco IOS CLI の基本操作と設定確認  
  `network/notes/ccna-day4-ios-cli.md`
- Ethernet / Switching の基礎整理  
  `network/notes/ccna-day5-Switching-Part1.md`
- 最短切り分け手順  
  `network/troubleshooting-network.md`


## 現在の公開進捗
- CCNA 200-301 ベースで学習中
- 公開済みの進捗は Day15（Subnetting / VLSM）まで
- 基礎概念、CLI、Switching、IPv4 Addressing、Routing Fundamentals、Packet の流れ、Subnetting / VLSM を順に整理している

## 主教材（YouTube：Jeremy's IT Lab）
本ディレクトリの Network 学習は、YouTube「Jeremy's IT Lab」の **CCNA 200-301（Free CCNA）** を主教材とし、自習ログと成果物（証跡）を残している。

- YouTubeチャンネル：https://www.youtube.com/@JeremysITLab
- 再生リスト（Free CCNA v1.1 200-301 | Complete Course 2025）：https://www.youtube.com/playlist?list=PLxbwE86jKRgMpuZuLBivzlM8s2Dk5lXBQ

## 学習の進め方（証跡が追える形にする）
- 視聴方針：**英語版（主）＋同内容の日本語版を交互に視聴**し、理解のズレを減らす
- ノート：動画の要点は `network/notes/` に Markdown で整理する
- ノート用画像：`network/notes/images/` に保存し、ノートから参照する
- 実験の証跡：Packet Tracer などのトポロジや実行結果は `network/screenshots/` に保存する
- 週次ログ：`weekly-log/` の証拠リンクから当日のノートやスクリーンショットへ辿れるようにする

## ノートと証跡
### 基礎整理
- `network/notes/network-basics.md`
- `network/notes/osi-tcpip-arp_tcpip-model_merged.md`
- `network/notes/commands-proof.md`

### Day別ノート
- `network/notes/ccna-day3-tcpip-model.md`
- `network/notes/ccna-day4-ios-cli.md`
- `network/notes/ccna-day5-Switching-Part1.md`
- `network/notes/ccna-day6-Switching-Part2.md`

### Lab / 補助ログ
- `network/CCNA-log/ccna-day4-ios-cli-log.md`
- `network/CCNA-log/ccna-day6-switching-part2-lab-log.md`
- `network/screenshots/day2_lab_connecting-devices_topology.jpg`
- `network/screenshots/day4_lab_ios-cli.jpg`

## 補助ファイル
- `network/troubleshooting-network.md`：層で迷わないための固定フロー
- `network/subnetting-practice.md`：計算練習と解法テンプレート

## 学習範囲
- TCP/IP基礎
- Switching
- IPv4 Addressing
- Routing Fundamentals
- Packet の流れ
- Subnetting / VLSM
- 今後は必要に応じて VLAN / ACL / NAT なども整理予定

## 運用ルール
- 個人情報（氏名、住所、メールアドレス等）は含めない
- 結果だけでなく、再現手順と判断（OK / NG）を残す
- 追加したノートや証跡は `weekly-log/` からも辿れるようにする
