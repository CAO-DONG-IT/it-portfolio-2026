# Linux Troubleshooting Playbook

## 基本コマンド（用途 + 例）
- pwd / ls / cd（場所確認・移動）
- cat / less / head / tail（内容確認）
- grep（ログ検索）
- ps / top（プロセス確認）
- df / du（ディスク確認）
- systemctl / service（サービス状態）
- journalctl（システムログ）
- ss -tulpn（ポート監視）※権限が必要なら sudo

## 切り分け手順（固定順）
1) 現象確認：エラー内容／ログ／発生時間
2) サービス：起動しているか（systemctl status）
3) リソース：CPU/メモリ/ディスク（top, free, df）
4) ネットワーク：待ち受け/疎通（ss, curl）
5) 権限：ユーザー/ファイル権限（whoami, ls -l）

## 典型ケース（後で追記）
- Case1：
- Case2：
- Case3：
