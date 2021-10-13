
WEB-sqlについての注意事項と説明です。

■システムの概要
MySQLの処理をjavaから操作できるようにします
結果をGUIで出力します
本システムではJavaのservlet/JSPを用いたGUI処理を作成し、
2次元の表として、アドレス帳を表示する、
検索、挿入、更新、削除を行えるようにする
入力したデータが間違っている場合、エラーがあることを表示します

■システムの目的
MySQLの処理をjava→WEBから操作できるようにしたい
コンソールではなく、GUIで見れるようにしたい
よりユーザーが入力しやすい形にしたい

■注意事項
ディレクトリは
\pleiades\eclipse\workspace-java\WEB-sql
です。
Eclipse 2021-06で実行する場合、
\pleiades\tomcat\9\libフォルダ内に
mysql-connector-java-8.0.26.jar
の追加が必要です。

second commitからログイン機能が実装されましたので、
ユーザー名とパスワードを確認して下さい
 ユーザー名 yamada
 パスワード 1234

MySQLにて、以下のコマンドを実行後、WEB-sqlを実行してください。
-- テーブル作成
create database test_db;
-- テーブル作成
create table AddressBook(
id      int 		primary key auto_increment,
name    varchar(7),
age     int,
address varchar(100));
-- 初期データの挿入
insert into addressbook values (1,'山田　花子',47,'大阪府堺市北区○○町1-2-');
insert into addressbook values (2,'大阪　太郎',71,'大阪府大阪市中央区道頓堀１丁目８?２５');
insert into addressbook values (3,'唐木　崇行',60,'大阪府茨木市□□町4-5-6');


すでにAddressBookテーブルがある場合はエラーとなりますので、
元のAddressBookテーブルをバックアップし、削除してから実行することを推奨します。

■内容
first commit
 WecContentフォルダ
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
  AddressBookIndex.java,Login.java,Logout.java
 WecContentフォルダ
  index.jsp
 WecContent/WEB-INF/jspフォルダ
  loginResult.jsp,logout.jsp
 ログイン、ログアウト機能の追加
 (パスワードは1234に固定)
 ログイン後にアドレス帳を表示するように変更
 検索ボックス(表示のみ)追加

third commit
 daoパッケージ
  AddressDAO.java
 modelパッケージ
  AdressBook.java,GetAdressListLogic.java,PostAdressLogic.java
 servretパッケージ
  AddressBookIndex.java,Login.java,Logout.java 
 WecContent/WEB-INF/jspフォルダ
  main.jsp,Result.jsp

 \pleiades\eclipse\workspace-java\WEB-sqlにEclipseへのインポートに必要な
  .classpath,.projectを追加
 ユーザー名yamadaに限定
 daoパッケージによりMySQLを処理する
 AddressBookIndex.javaのdogetにて全件取得し、main.jspで出力する
 AddressBookIndex.javaのdopostで検索を行う予定
 Result.jspに結果を表示する予定
 
four commit
 追加ファイル
 testパッケージ
  AddressbookLogicTest.java
 modelパッケージ
  Seach.java
 
 更新ファイル
 daoパッケージ
  AddressDAO.java
 modelパッケージ
  PostAdressLogic.java,Seach.java
 servretパッケージ
  AddressBookIndex.java
 WecContent/WEB-INF/jspフォルダ
  main.jsp,Result.jsp
 WecContent
  index.jsp

 検索用DAOメソッド、検索用BeansのSeach.javaとした
 サーブレット、jspも併せて修正
 test用プログラムを作成
 レイアウトを若干変更
 編集ボタンを追加

five commit
 追加ファイル
 servretパッケージ
  AddressBookEdit.java
 WecContent/WEB-INF/jspフォルダ
  edit.jsp
  
 更新ファイル
 first commitとtestパッケージ以外のファイル全て

 編集ボタンを押したら、新規作成、更新、削除できるようにした
 削除後、連番になるようにした
 データベースの方にも記録される
 レイアウトを若干変更
 


■実行環境
MySQL 8.0 Command Line Client
Eclipse 2021-06
Tomcat 9.0

■作者
義水　尚幸
連絡先 Email:m96hih28sxgr4w@gmail.com


