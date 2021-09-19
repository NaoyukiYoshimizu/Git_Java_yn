
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
use test_db;
create table test(
 id   varchar(3),
 name varchar(6)
 );
insert into test values (1,'リンゴ');
insert into test values (2,'ミカン');
insert into test values (3,'ブドウ');

■内容
MySQLに接続し、testテーブルの内容をコンソールに表示する

■実行環境
MySQL 8.0 Command Line Client
Eclipse 2021-06

■作者
義水　尚幸
連絡先 Email:m96hih28sxgr4w@gmail.com


