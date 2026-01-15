# Day3：ユーザー/グループ操作ログ（証拠）

## 実行環境
- Windows + WSL (Ubuntu)
- JST：実行日未記載（ログ出力のタイムスタンプは端末表示に従う）

## 目的
- ユーザー/グループの確認、作成、追加グループ付与、削除までを一通り検証し、出力を証跡として残す。

## 1) group の確認（getent）
```
getent group | tail

messagebus:x:101:
syslog:x:102:
systemd-resolve:x:991:
uuidd:x:103:
_ssh:x:104:
landscape:x:105:
polkitd:x:990:
admin:x:106:
netdev:x:107:jyuuroku
jyuuroku:x:1000:
```
### 誤操作：DB名の指定ミス（参考）
```
getent groups

Unknown database: groups
Try `getent --help' or `getent --usage' for more information.
```
## 2) 自分の所属グループ確認
```bash
groups

jyuuroku adm dialout cdrom floppy sudo audio dip video plugdev users netdev
```
```
id jyuuroku

uid=1000(jyuuroku) gid=1000(jyuuroku) groups=1000(jyuuroku),4(adm),20(dialout),24(cdrom),25(floppy),27(sudo),29(audio),30(dip),44(video),46(plugdev),100(users),107(netdev)
```
## 3) グループ削除（任意で実施した場合）
```
sudo groupdel Test1

groupdel: group 'Test1' does not exist
```

## 4) ユーザー作成（ホーム作成 + ログインシェル指定）
```
sudo useradd -m -s /bin/bash test2
ls -al /home

total 16
drwxr-xr-x  4 root     root     4096 Jan  9 17:29 .
drwxr-xr-x 23 root     root     4096 Jan  9 16:06 ..
drwxr-x--- 11 jyuuroku jyuuroku 4096 Jan  9 16:45 jyuuroku
drwxr-x---  2 test2    test2    4096 Jan  9 17:29 test2
```

## 5) 追加グループに参加（順番注意）
```
sudo usermod -aG jyuuroku test2
groups test2

test2 : test2 jyuuroku
```
```
getent group | tail

syslog:x:102:
systemd-resolve:x:991:
uuidd:x:103:
_ssh:x:104:
landscape:x:105:
polkitd:x:990:
admin:x:106:
netdev:x:107:jyuuroku
jyuuroku:x:1000:test2
test2:x:1001:
```

## 6) ユーザー削除（ホームも削除）
```
sudo userdel -r test2

userdel: test2 mail spool (/var/mail/test2) not found
```
```
id test2

id: ‘test2’: no such user
```
```
getent passwd | tail

systemd-network:x:998:998:systemd Network Management:/:/usr/sbin/nologin
systemd-timesync:x:996:996:systemd Time Synchronization:/:/usr/sbin/nologin
dhcpcd:x:100:65534:DHCP Client Daemon,,,:/usr/lib/dhcpcd:/bin/false
messagebus:x:101:101::/nonexistent:/usr/sbin/nologin
syslog:x:102:102::/nonexistent:/usr/sbin/nologin
systemd-resolve:x:991:991:systemd Resolver:/:/usr/sbin/nologin
uuidd:x:103:103::/run/uuidd:/usr/sbin/nologin
landscape:x:104:105::/var/lib/landscape:/usr/sbin/nologin
polkitd:x:990:990:User for polkitd:/:/usr/sbin/nologin
jyuuroku:x:1000:1000:,,,:/home/jyuuroku:/bin/bash
```

## メモ（学び）
- `usermod -aG <group> <user>`：**先にグループ、後にユーザー**。`-a` を付けないと追加グループが上書きされる可能性がある。
- `getent group` の `members` は「追加グループのメンバー」欄で、主グループ（primary group）は表示されない場合がある。
- `userdel -r` の `mail spool ... not found` は `/var/mail/<user>` が存在しないだけで致命的ではない。
