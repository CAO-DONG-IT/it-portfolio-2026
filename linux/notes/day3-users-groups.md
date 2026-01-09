# Day3 Notes：ユーザー・グループ・権限まわり（WSL/Ubuntu）

## 1) `useradd` で `/home` が作られない理由
- `useradd` だけだと **ホームディレクトリは自動作成されない**（環境/設定による）
- ホームを作る：`useradd -m <user>`
- 例：`sudo useradd -m -s /bin/bash test2`

## 2) `-s` オプション（ログインシェル）
- `-s <shell>` は **ログイン時に使うシェル（ログインシェル）** を指定する
- 例：`-s /bin/bash`（bashを使う）
- 「端末（terminal）」は画面/アプリ、`/bin/bash` はその中で動く **シェル**（コマンド解釈するプログラム）

## 3) userdel -r のメッセージ
- `sudo userdel -r <user>`：ユーザー削除 + ホームディレクトリ等も削除
- `mail spool (/var/mail/<user>) not found` は **/var/mail が無いだけ**（致命ではない）

## 4) usermod -aG の順番（混同注意）
- `usermod -aG <group> <user>`  
  → **先にグループ、後にユーザー**
- 確認：
  - `groups <user>`
  - `id <user>`

## 5) グループ情報の見方（4項目）
- `getent group` の1行は `group_name:password:GID:members`
  - `group_name`：グループ名
  - `password`：現在ほぼ使わない（`x` が多い）
  - `GID`：グループID
  - `members`：**追加グループとして所属しているユーザー一覧**
    - 重要：ここには **主グループ（primary group）のユーザーは表示されない** ことがある

## 6) getent の注意（DB名）
- 正：`getent group`
- 誤：`getent groups` → `Unknown database` になる

## 7) ls -l の「2」「7」は何？
- ディレクトリの `drwx... 2 ...` の数字は **ハードリンク数**
  - そのディレクトリ自身 `.` と、親からの参照（親の中のその名前）で最低2
  - サブディレクトリが増えると、その分だけ親ディレクトリのリンク数が増える
- 例：`it-portfolio-2026` が `7` なのは、配下に複数のディレクトリがあるため（`.git` なども含む）
- `ls -al` は `.` と `..` も含めて表示する（構造確認に便利）

## 8) グループ変更の意味（自分の権限は？）
- ファイル/ディレクトリの所有者（`user`）が自分なら、通常は自分の権限が最優先で効く
- ただしグループを使うと、**同じグループの別ユーザーに権限を渡せる**（共同作業/運用で重要）
