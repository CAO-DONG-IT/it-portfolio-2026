# Week1

## 2026-01-05 22:13（JST）
### 今日の目標
1)GitHubリポジトリを作成し、テンプレート（README／学習ログ等）を整備して初回コミットする
2)WSL（Ubuntu）を導入し、基本コマンド（pwd/ls/uname -a）で動作確認してログに残す
3)JDKを導入し、java/javacのバージョン確認まで行い、結果を学習ログに記録する

### 今日やったこと
- テンプレートを作成して保存（README／week1ログ／Linux/Java/AWSの骨子）
- Ubuntu（WSL）で動作確認：pwd / ls / uname -a
- Javaの動作確認：java -version / javac -version

【WSL動作確認ログ】
jyuuroku@WIN-HV50B2AFSCS:~$ pwd
/home/jyuuroku
jyuuroku@WIN-HV50B2AFSCS:~$ ls
**（空＝まだファイルなし）**
jyuuroku@WIN-HV50B2AFSCS:~$ uname -a
Linux WIN-HV50B2AFSCS 6.6.87.2-microsoft-standard-WSL2 #1 SMP PREEMPT_DYNAMIC Thu Jun  5 18:30:46 UTC 2025 x86_64 x86_64 x86_64 GNU/Linux
jyuuroku@WIN-HV50B2AFSCS:~$

【Java動作確認ログ】
jyuuroku@WIN-HV50B2AFSCS:~$ java -version
openjdk version "17.0.17" 2025-10-21
OpenJDK Runtime Environment (build 17.0.17+10-Ubuntu-124.04)
OpenJDK 64-Bit Server VM (build 17.0.17+10-Ubuntu-124.04, mixed mode, sharing)
jyuuroku@WIN-HV50B2AFSCS:~$ javac -version
javac 17.0.17

### 詰まった点（現象／原因／解決）
なし

### 明日の予定（2026-01-06）
1) Linux基本コマンド（ls/cd/mkdir/touch/cp/mv/rm）を実行して、演習ログを1本作成する
2) Java v0.1：Helloまたは最小コンソール入出力が動く状態を作り、READMEに実行手順を追記する
3) 学習ログ（week1.md）を更新してコミットする（最低1回）

### 今日のキーワード（3〜5個）
- WSL
- Ubuntu
- uname  
- JDK
- javac

### 証拠リンク（リポジトリ内パス）
- README.md
- weekly-log/week1.md
- linux/troubleshooting-playbook.md
- java/README.md
- aws/README.md

## 2026-01-06
### 今日の目標


### 今日やったこと
- `touch/echo/cat` でファイル作成・書き込み（上書き/追記）・改行挙動（echo -n）を確認
- `/etc/hosts` と `/etc/services` を `more` で閲覧し、ページ送り操作を確認
- `cp/mv/rm`（ワイルドカード含む）を演習し、パス解釈と削除の注意点を整理



### 詰まった点（現象／原因／解決）
- `cp: cannot stat`：作業ディレクトリが原因でコピー元が存在しなかった → パス見直しで解決
- バイナリを `cat` して画面が乱れた → `Ctrl+C` / `reset` で復旧
- `rm` でディレクトリ削除不可 → `rm -r` が必要
- ワイルドカードがマッチしない → 事前に `ls` で確認

### 明日の予定（2026-01-07）

### 今日のキーワード（3〜5個）


### 証拠リンク（リポジトリ内パス）

