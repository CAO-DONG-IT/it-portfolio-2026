# Week1

## 2026-01-05 22:13（JST）
### 今日の目標
1)GitHubリポジトリを作成し、テンプレート（README／学習ログ等）を整備して初回コミットする

2)WSL（Ubuntu）を導入し、基本コマンド（`pwd/ls/uname -a`）で動作確認してログに残す

3)JDKを導入し、`java/javac`のバージョン確認まで行い、結果を学習ログに記録する

### 今日やったこと
- テンプレートを作成して保存（README／week1ログ／Linux/Java/AWSの骨子）
- Ubuntu（WSL）で動作確認：`pwd / ls / uname -a`
- Javaの動作確認：`java -version` / `javac -version`


【WSL動作確認ログ】
```
pwd
ls
uname -a
```
【Java動作確認ログ】
```
java -version
javac -version
```

### 詰まった点（現象／原因／解決）
なし

### 明日の予定（2026-01-06）
1) Linux基本コマンド（`ls/cd/mkdir/touch/cp/mv/rm`）を実行して、演習ログを1本作成する
2) Java v0.1：Helloまたは最小コンソール入出力が動く状態を作り、READMEに実行手順を追記する
3) 学習ログ（`week1.md`）を更新してコミットする（最低1回）

### 今日のキーワード（3〜5個）
- WSL
- Ubuntu
- uname  
- JDK
- javac

### 証拠リンク（リポジトリ内パス）
- `README.md`
- `weekly-log/week1.md`
- `linux/troubleshooting-playbook.md`
- `java/README.md`
- `aws/README.md`

## 2026-01-06
### 今日の目標
1) Linux：`touch/echo/cat/more` を使って「作成→書き込み→確認→閲覧」を一通り実践し、学習ログに残す  
2) Linux：`cp/mv/rm`（ワイルドカード含む）を演習し、パス解釈と削除の注意点を整理してPlaybookに反映する  
3) Java：Console Todo v0.1 を作成し、WSL上で `javac/java` によるビルド・実行確認まで完了し、リポジトリに証拠を残す

### 今日やったこと
- `touch/echo/cat` でファイル作成・書き込み（上書き/追記）・改行挙動（echo -n）を確認
- `/etc/hosts` と `/etc/services` を `more` で閲覧し、ページ送り操作を確認
- `cp/mv/rm`（ワイルドカード含む）を演習し、パス解釈と削除の注意点を整理
- Java：Console Todo v0.1 を作成（標準入力→trim→空判定→出力）し、WSL上でコンパイル/実行確認を実施


### 詰まった点（現象／原因／解決）
- `cp: cannot stat`：作業ディレクトリが原因でコピー元が存在しなかった → パス見直しで解決
- バイナリを `cat` して画面が乱れた → `Ctrl+C` / `reset` で復旧
- `rm` でディレクトリ削除不可 → `rm -r` が必要
- ワイルドカードがマッチしない → 事前に `ls` で確認
- Java：`illegal character: '\u3000'` → 全角スペース（U+3000）が混入していた → 半角スペースに置換して解決
  
### 明日の予定（2026-01-07）
1) Linux：`grep` とパイプ（`|`）の基礎を動画で確認し、/etc のファイルを対象に検索演習（例：services から `ssh` を探す）  
2) Java：Console Todo v0.2（メニュー + 追加/一覧）に着手（while/switch + ArrayList を使う）  
3) 証拠：`weekly-log/week1.md` 更新＋Java v0.2 のコミット（最低1回）
   
### 今日のキーワード（3〜5個）
- `echo（> / >> / -n）`
- `more`
- ワイルドカード（`*`）
- `cp / rm -r`
- Java：`trim` / `isEmpty` / `U+3000`

### 証拠リンク（リポジトリ内パス）
- `weekly-log/week1.md`
- `linux/troubleshooting-playbook.md`
- `linux/day1-cat-echo-more.md`
- `linux/day1-cp-mv-rm-wildcard.md`
- `java/ConsoleTodo/v0.1/Main.java`
- `java/ConsoleTodo/v0.1/README.md`

## 2026-01-07（network追加）
### 今日の目標
1) Linux：find/which/grep/|/wc/</>/>>/tail を使い、/etc を題材に「検索→集計→ログ化」を1本の成果物にする
2) Java：Console Todo v0.2（メニュー + 追加/一覧）を実装し、READMEに実行方法と操作例を残す
3) Network：OSI/TCPIP と ARP の要点を整理し、疎通・DNS・経路・HTTP確認をログ化して証拠を残す

### 今日やったこと
- Linux：/etc を題材に` find/grep/| wc -l/> >>/tail/vim` を一連で実施し、ログ化まで完了
- Java：Console Todo v0.2 を実装（メニュー + 追加/一覧）し、動作確認まで完了
- Network：OSI/TCPIP と ARP を整理し、疎通・DNS・経路・HTTP確認を実行してログ化まで完了
  ```
  - ping 1.1.1.1（疎通OK）
  - nslookup example.com（DNS OK）
  - tracert 1.1.1.1（最終到達OK）
  - curl.exe -I https://example.com（HTTP 200 OK）
  - PowerShell の curl 別名問題を把握し、curl.exe で回避
  ```

### 詰まった点（現象／原因／解決）
- `which cd` が出ない：`cd` はシェル組み込み（builtin）→ `type cd` で確認
- `find` で `Permission denied`：`/mnt` `/proc` `/sys` などの影響 → `-prune` で除外、または `2>/dev/null` で抑制
- root に切替後 `cd` が `/root`：`su -` はログインシェル → ユーザー作業は `cd /home/jyuuroku`

### 明日の予定（2026-01-08）
- ITパスポート：受験
- Linux：/etc で「検索→集計→ログ化」を実施`（linux/day3-etc-search.md）`
- Java：Console Todo 小改善1件（削除/完了/入力バリデーションのいずれか）を追加し、README追記
- Network：サブネット練習3問を追加`（network/subnetting-practice.md）`＋手順メモ作成`（network/notes/subnet-basic.md）`
- 次：サブネット練習を継続（合計10問まで）＋Todoの機能をもう1つ追加


### 今日のキーワード（3〜5個）
- prune
- builtin
- pipe（|）
- ArrayList
- ARP

### 証拠リンク（リポジトリ内パス）
```
- linux/day2-etc-search.md`
- java/ConsoleTodo/v0.2/Main.java`
- java/ConsoleTodo/v0.2/README.md
- java/screenshots/ConsoleTodo_v0.2_run.jpg
- network/notes/osi-tcpip.md
- network/notes/arp.md
- network/notes/commands-proof.md
```

## 2026-01-08（ITパスポート受験＋Network notes統合）
### 今日の目標

1) ITパスポート受験を完了する（最優先）

2) Network 学習内容を notes 1本に統合し、GitHub に証拠を残す

### 今日やったこと

- ITパスポート：受験（最優先で実施）

- Network：これまでの学習内容（用語、/n、ブロックサイズ、NW/BC/usable/hosts、切り分け観点）を1本に統合

- 既存メモを参照しつつ、誤解しやすい点（GW≠マスク、NWの定義など）をまとめ直した

- GitHub：日次ログ追記＋notes追加

### 予定との差分（未達）

- Linux：`linux/day3-etc-search.md` の実施・更新 → 未達

- Java：`Console Todo`小改善（削除/完了/入力バリデーション）＋README追記 → 未達

- Network：サブネット練習3問（`network/subnetting-practice.md`）＋手順メモ（`network/notes/subnet-basic.md`）→ 未達

### 詰まった点（現象／原因／解決）

- 学習が進まない：試験による疲労・時間制約 → 当日は「notes統合＋ログ更新」に絞って継続を優先

### 明日の予定（2026-01-09）

- Network：サブネット練習3問を追加（`network/subnetting-practice.md`）＋手順メモ追記（`network/notes/subnet-basic.md`）

- Linux：権限（`chmod`）とコピー（cp/隠しファイル含む）の要点を notes に整理（`linux/troubleshooting-playbook.md` へ追記候補）

- Java：`Console Todo v0.3`（改善1件：削除/完了/入力バリデーション）を実装し、READMEに操作例追記

### 今日のキーワード（3〜5個）

- CIDR（/n）

- ブロックサイズ

- NW/BC/usable

- デフォルトゲートウェイ

- notes統合

### 証拠リンク（リポジトリ内パス）

- `network/notes/network-basics.md`

- `weekly-log/week1.md`（本日の追記）

## 2026-01-09
### 今日の目標
1) Linux：詰まりポイントを playbook に Case 3件 追記し、コマンド例を添える（linux/troubleshooting-playbook.md）

2) Java：Console Todo v0.3（小改善1件） を実装し、README に操作例を追記（java/ConsoleTodo/v0.3/）

3) Network：サブネット練習 3問 を追加し、手順メモを 10行以内で整理（network/subnetting-practice.md / network/notes/subnet-basic.md）

4) GitHub：weekly-log/week1.md に Day4 追記＋ 日次コミット1回（Web提出OK）


### 今日やったこと
- Linux：
  - ユーザー/グループ操作（useradd -m / usermod -aG / userdel -r / groupdel / groups / id / getent group）を実機で確認
  - 権限表示の見方（リンク数など）を含め、詰まりポイントを playbook の Case として整理
- Java：
  - 基礎動画を視聴し、Scanner / 変数・字面量 / メソッド（引数あり・なし）/ switch を復習
  - Console Todo v0.3：削除機能（番号指定）＋入力バリデーション（全角/英字/範囲外）を実装
  - README更新、操作ログ作成、スクリーンショット追加
- Network：
  - 本日は見送り（学習の連続性を優先し、Linux/Javaの成果物に集中）


### 予定との差分（未達）
- Network：サブネット練習3問＋手順メモ（10行以内）→ 本日は見送り
- （必要なら明日以降にリスケ）

### 詰まった点（現象／原因／解決）
- `getent groups` が失敗：DB名は `group`（単数）→ `getent group | tail` で確認
- `useradd` しても `/home/<user>` ができない：`-m` 未指定 → `useradd -m` で作成
- `usermod -aG` の引数順が混乱：`usermod -aG <group> <user>`（順序固定）
- 入力が「１」（全角）で不正扱い：半角数字 `0/1/2/3` のみ許可する仕様に統一

### 明日の予定（2026-01-10）
- Linux：playbook Case を最終反映（必要なら追記）＋今日の論点を notes 化（任意）
- Java：Console Todo の小リファクタ（メッセージ/メソッド分割など軽微でOK）または 次バージョンの方針1行追記
- Network：体力次第で再開（サブネット練習を1〜3問だけでも実施して連続性を戻す）


### 今日のキーワード（3〜5個）
- useradd -m
- usermod -aG
- getent group
- リンク数（ディレクトリ）
- 全角／半角


### 証拠リンク（リポジトリ内パス）
```
linux/troubleshooting-playbook.md

java/ConsoleTodo/v0.3/Main.java

java/ConsoleTodo/v0.3/README.md

java/screenshots/ConsoleTodo_v0.3_run.jpg

weekly-log/week1.md
```
