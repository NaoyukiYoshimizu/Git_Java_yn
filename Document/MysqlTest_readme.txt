
MysqlTest.java についての注意事項と説明です。

■注意事項
ディレクトリは
\pleiades\eclipse\workspace-java\test\src\test
です。
javaプロジェクトtestフォルダ内に
mysql-connector-java-8.0.26.jar
の追加が必要です。

MySQLにて、以下のコマンドを実行後、MysqlTest.javaを実行してください。
create database test_db;

すでにAddressBookテーブルがある場合はエラーとなりますので、
元のAddressBookテーブルをバックアップし、削除してから実行することを推奨します。

■内容
first commit
MySQLに接続し、AddressBookテーブルを作成する
AddressBookテーブルにデータを入れてコンソールに表示する
コンソールに"Y"入力でAddressBookテーブルの内容をテーブルごと削除する

second commit(主な追加部分）
コンソールに"Y"入力で住所検索を開始
ユーザー入力を含む文字列で検索してコンソールに表示する
ユーザー入力文字を表示
無い場合もコンソールに無いことを表示


■実行環境
MySQL 8.0 Command Line Client
Eclipse 2021-06

■作者
義水　尚幸
連絡先 Email:m96hih28sxgr4w@gmail.com


