
WEB-sqlについての注意事項と説明です。

■注意事項
ディレクトリは
\pleiades\eclipse\workspace-java\WEB-sql
です。
Eclipse 2021-06で実行する場合、
\pleiades\tomcat\9\libフォルダ内に
mysql-connector-java-8.0.26.jar
の追加が必要です。

MySQLにて、以下のコマンドを実行後、sample.jspを実行してください。
create database test_db;
create table AddressBook(name varchar(7),age int,address varchar(100));

すでにAddressBookテーブルがある場合はエラーとなりますので、
元のAddressBookテーブルをバックアップし、削除してから実行することを推奨します。

■内容
first commit
Sqlsample00.java
MySQLに接続し、AddressBookテーブルにデータを入れます
コンソールに"Y"入力でAddressBookテーブルの内容を削除します
sample.jsp
そのデータをインポートし、HTML(jsp)で表示します


■実行環境
MySQL 8.0 Command Line Client
Eclipse 2021-06
Tomcat 9.0

■作者
義水　尚幸
連絡先 Email:m96hih28sxgr4w@gmail.com


