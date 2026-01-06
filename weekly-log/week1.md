# Week1

## 2026-01-05 22:13（JST）
### 今日の目標
1)GitHubリポジトリを作成し、テンプレート（README／学習ログ等）を整備して初回コミットする

2)WSL（Ubuntu）を導入し、基本コマンド（pwd/ls/uname -a）で動作確認してログに残す

3)JDKを導入し、java/javacのバージョン確認まで行い、結果を学習ログに記録する

### 今日やったこと
- テンプレートを作成して保存（README／week1ログ／Linux/Java/AWSの骨子）
- Ubuntu（WSL）で動作確認：`pwd / ls / uname -a`
- Javaの動作確認：`java -version` / `javac -version`


【WSL動作確認ログ】
```bash
pwd
ls
uname -a
```
【Java動作確認ログ】
```bash
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
