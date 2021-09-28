
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
MySQLに接続し、AddressBookテーブルを作成します
AddressBookテーブルにデータを入れてコンソールに表示します
コンソールに"Y"入力でAddressBookテーブルの内容をテーブルごと削除します

second commit(主な追加部分）
コンソールに"Y"入力で住所検索を開始します
ユーザー入力を含む文字列で検索してコンソールに表示します
ユーザー入力文字を表示します
無い場合もコンソールに無いことを表示します

three commit(主な追加部分）
SQLの処理をSetsqlクラスとして分けました
テーブル作成、挿入、検索、削除(DROP)をそれぞれメソッドに分けました
テーブル作成が成功すればSQLの他の処理を進めるようにしました

four commit(主な追加部分）
Swing処理用として、JApplet_sql.javaを追加
2次元の表として、テーブル内容が全件出るようにした
submitボタンでselect文のテーブル内容が出るようにした
(コンソールの入力とは別となっています）

five commit(主な追加部分）
DELETE/INSERTボタンでDELETE/INSERT文のテーブル操作ができるようにした

six commit
SwingのApplet_sql.javaとMysqlTest.javaを一旦分けた
MysqlTest.javaにて、insertメソッドでコンソール入力内容をデータに入るようにした


■実行環境
MySQL 8.0 Command Line Client
Eclipse 2021-06

■作者
義水　尚幸
連絡先 Email:m96hih28sxgr4w@gmail.com


