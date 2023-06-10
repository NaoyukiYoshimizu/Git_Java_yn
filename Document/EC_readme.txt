EC-niluについての注意事項と説明です。

要件定義
■システムの概要
買い物できるECサイト

■システムの目的
servlet/JSPを用いて、ECシステムを作成する。
データベースと連動させつつ、
アマゾンに近づけることを目標とした。

■開発メンバー・期間
5/24～

■機能
【一般ユーザー機能】
・ログイン
・ログアウト
・商品閲覧
・カート
・ユーザー情報の編集

【管理者のみ機能】
・ユーザー管理、
・在庫管理

■注意事項
画面左上のEC-niluをクリックにより、メイン画面に遷移します
ログアウト後は、ログイン画面に遷移します
Eclipse 2021-06で実行する場合、
\pleiades\tomcat\9\libフォルダ内に
mysql-connector-java-8.0.26.jar
の追加が必要です

データベースのデータについては
EC-niluフォルダにある
create table.txt
を使用してください
最終行付近のselect文によりDBと連動していることが確認できます
データベースの削除については
drop table.txt
を使用してください

■実行環境
MySQL 8.0 Command Line Client
Eclipse 2021-06
Tomcat 9.0
Google Chrome

■作者
義水　尚幸
連絡先 Email:m96hih28sxgr4w@gmail.com

