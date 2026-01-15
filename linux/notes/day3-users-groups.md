# Day3 Notes：ユーザー・グループ・権限まわり（WSL/Ubuntu、JST 2026-01-07）

## 目的
- ユーザー／グループの基本操作で迷わないための要点整理
- 実行ログ（証拠）とは分けて、判断ポイントだけを残す

## 用語（JP / EN / 中文）
- ユーザー / user / 用户
- グループ / group / 用户组
- UID / user id / 用户ID
- GID / group id / 组ID
- 主グループ / primary group / 主组
- 追加グループ / supplementary group / 附加组
- ログインシェル / login shell / 登录Shell
- ハードリンク数 / hard link count / 硬链接数

## 1) `useradd` で `/home` が作られない理由
- `useradd` だけだと **ホームディレクトリが自動作成されない** 場合がある（環境・設定による）
- ホームを作る：`useradd -m <user>`
- 例：
```bash
sudo useradd -m -s /bin/bash test2
```

## 2) `-s` オプション（ログインシェル）
- `-s <shell> `は **ログイン時に使うシェル** を指定する
- 例：`-s /bin/bash`（bashを使う）
- 「端末（terminal）」は画面/アプリ、`/bin/bash` はその中で動く **シェル**（コマンド解釈するプログラム）

## 3) userdel -r のメッセージ
- `sudo userdel -r <user>`：ユーザー削除 + ホームディレクトリ等も削除
- `mail spool (/var/mail/<user>) not found` は **/var/mail が無いだけ**（致命ではない）

## 4) usermod -aG の順番と注意点
- 基本形：`usermod -aG <group> <user>`
   - **先にグループ、後にユーザー**

- 重要：`-a` を付けないと **追加グループが上書き**される可能性がある
- 確認コマンド：
  ```
   groups <user>
   id <user>
  ```

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
  - ディレクトリ自身 `.` と、親からの参照で最低2
  - サブディレクトリが増えるとリンク数も増える
- `ls -al` は `.` と `..` も表示するため、構造確認に便利

## 8) グループ変更の意味（権限の考え方）
- 所有者（user）が自分なら、通常は自分の権限が最優先で効く
- グループを使うと、**同じグループの別ユーザーに権限を渡せる**（共同作業・運用で重要）

##  よく使うコマンド（まとめ）
```
getent group
getent passwd
groups <user>
id <user>
sudo useradd -m -s /bin/bash <user>
sudo usermod -aG <group> <user>
sudo userdel -r <user>
sudo groupdel <group>
```
