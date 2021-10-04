
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
WebContentフォルダ
sample.jsp
servletパッケージ
Sqlsample00.java

MySQLに接続し、AddressBookテーブルにデータを入れます
コンソールに"Y"入力でAddressBookテーブルの内容を削除します
そのデータをインポートし、HTML(jsp)で表示します

second commit
modelパッケージ
LoginLogic.java,Users.java
servletパッケージ
AddressBookIndex.java1,Login.java,Logout.java
WebContentフォルダ
index.jsp

WecContent/WEB-INF/jspフォルダ
loginResult.jsp,logout.jsp
ログイン、ログアウト機能の追加
(パスワードは1234に固定)
ログイン後にアドレス帳を表示するように変更
検索ボックス(表示のみ)追加



■実行環境
MySQL 8.0 Command Line Client
Eclipse 2021-06
Tomcat 9.0

■作者
義水　尚幸
連絡先 Email:m96hih28sxgr4w@gmail.com


