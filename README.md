# HorizonLimit

天地を隔てる見えない境界線

# 開発バージョン

- Minecraft 1.20.2 fabric

# 前提MOD

- Fabric API

# コマンド

- `/hl count <time>`
  - timeの中に禁止エリアの猶予時間をtickで設定すると、禁止エリアに入った際にそのtick経過した後に、死亡します。

- `/hl set <player> <dimension> <limit> <isSkySide>`
  - 以下の通りにパラメーターを設定すると、各プレイヤーの禁止エリアの設定が可能です。
  - パラメーターの一覧
    - player: 設定対象のプレイヤー
    - dimension: 設定するディメンション
    - limit: 禁止エリアとの境界線のY座標
    - isSkySide: そのプレイヤーが空で暮らすプレイヤーであるか
      - true: 天空で暮らすプレイヤー
      - false: 地上で暮らすプレイヤー

> このMODはしう氏の企画、「天地分割クラフト」のために制作されました。
> [![動画](http://img.youtube.com/vi/jok06_fjnqY/0.jpg)](https://www.youtube.com/watch?v=jok06_fjnqY&list=PLAk_kz3mvfCt2QCbjwiKI_TGmYeFqleTm)

# 開発ルール

- コミットメッセージやPRのタイトルは先頭に変更内容と合致する[gitmoji](https://gitmoji.dev)を付けてください(
  IDEAには[拡張機能](https://plugins.jetbrains.com/plugin/12383-gitmoji-plus-commit-button)もあります)
- 作業を行う際は新たなブランチを作成して、適宜PRを作成してください(mainブランチに直接コードをプッシュしないでください)
- コードのフォーマット及びリントには、spotlessを使用しています。commitする前に`spotlessCheck`が通ることを確認してください。