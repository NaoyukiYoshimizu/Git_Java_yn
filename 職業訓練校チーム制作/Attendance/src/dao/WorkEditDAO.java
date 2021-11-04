package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Work;

public class WorkEditDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/Attendance?characterEncoding=UTF8&serverTimezone=Asia/Tokyo";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";

	String inoutflag;// 出退勤判定
	String nowdate;// 現在年月日
	String month;// 現在の月
	String data;
	// 現在日時の取得
	Date d = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("y-MM-dd");

	// 更新
	public boolean timeupdate(Work work) {
		if (work.getTime_id() ==2) {
			// データベースへ接続
			try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
				// DELETE文の準備
				String sql = "DELETE FROM WORK WHERE ID = ? AND WORK_DAY = ? AND TIME_ID = ?";
				// DELETE文中の「?」に使用する値を設定しSQLを完成
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, work.getId());
				ps.setString(2, work.getWork_day());
				ps.setInt(3, 2);
				// DELETE文を実行
				ps.executeUpdate();
				sql = "DELETE FROM WORK WHERE ID = ? AND WORK_DAY = ? AND TIME_ID = ?";
				// DELETE文中の「?」に使用する値を設定しSQLを完成
				ps = con.prepareStatement(sql);
				ps.setInt(1, work.getId());
				ps.setString(2, work.getWork_day());
				ps.setInt(3, 0);
				// DELETE文を実行
				ps.executeUpdate();
				
				// INSERT文の準備
				sql = "INSERT INTO WORK(WORK_DAY, STAMPING_TIME, ID, TIME_ID,NOTE) VALUES(?,?,?,?,?)";
				// INSERT文中の「?」に使用する値を設定しSQLを完成
				ps = con.prepareStatement(sql);
				ps.setString(1, work.getWork_day());
				ps.setString(2, work.getStamping_time());
				ps.setInt(3, work.getId());
				ps.setInt(4, 2);
				ps.setString(5, work.getNote());
				
				// INSERT文を実行
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}else if (work.getTime_id() ==1) {
			// データベースへ接続
			try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
				String sql = "SELECT * FROM WORK WHERE ID = ? AND WORK_DAY = ? AND TIME_ID = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, work.getId());
				ps.setString(2, work.getWork_day());
				ps.setInt(3, 2);
				// 実行結果取得
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					// 一致データ
					this.data = rs.getString("STAMPING_TIME");
				}
				// DELETE文の準備
				sql = "DELETE FROM WORK WHERE ID = ? AND WORK_DAY = ? AND TIME_ID = ?";
				// DELETE文中の「?」に使用する値を設定しSQLを完成
				ps = con.prepareStatement(sql);
				ps.setInt(1, work.getId());
				ps.setString(2, work.getWork_day());
				ps.setInt(3, 2);
				// DELETE文を実行
				ps.executeUpdate();
				sql = "DELETE FROM WORK WHERE ID = ? AND WORK_DAY = ? AND TIME_ID = ?";
				// DELETE文中の「?」に使用する値を設定しSQLを完成
				ps = con.prepareStatement(sql);
				ps.setInt(1, work.getId());
				ps.setString(2, work.getWork_day());
				ps.setInt(3, 0);
				// DELETE文を実行
				ps.executeUpdate();
				sql = "DELETE FROM WORK WHERE ID = ? AND WORK_DAY = ? AND TIME_ID = ?";
				// DELETE文中の「?」に使用する値を設定しSQLを完成
				ps = con.prepareStatement(sql);
				ps.setInt(1, work.getId());
				ps.setString(2, work.getWork_day());
				ps.setInt(3, 1);
				// DELETE文を実行
				ps.executeUpdate();
				// INSERT文の準備
				sql = "INSERT INTO WORK(WORK_DAY, STAMPING_TIME, ID, TIME_ID,NOTE) VALUES(?,?,?,?,?)";
				// INSERT文中の「?」に使用する値を設定しSQLを完成
				ps = con.prepareStatement(sql);
				ps.setString(1, work.getWork_day());
				ps.setString(2, work.getStamping_time());
				ps.setInt(3, work.getId());
				ps.setInt(4, 1);
				ps.setString(5, work.getNote());
				
				// INSERT文を実行
				ps.executeUpdate();
				// INSERT文の準備
				sql = "INSERT INTO WORK(WORK_DAY, STAMPING_TIME, ID, TIME_ID,NOTE) VALUES(?,?,?,?,?)";
				// INSERT文中の「?」に使用する値を設定しSQLを完成
				ps = con.prepareStatement(sql);
				ps.setString(1, work.getWork_day());
				ps.setString(2, this.data);
				ps.setInt(3, work.getId());
				ps.setInt(4, 2);
				ps.setString(5, work.getNote());
				// INSERT文を実行
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}else if (work.getTime_id() ==99) {
			try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
				// UPDATE文の準備
				String sql = "UPDATE WORK SET NOTE = ? WHERE ID = ? AND WORK_DAY = ? AND TIME_ID = ?";
				// UPDATE文中の「?」に使用する値を設定しSQLを完成
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, work.getNote());
				ps.setInt(2, work.getId());
				ps.setString(3, work.getWork_day());
				ps.setInt(4, 1);
				// UPDATE文を実行（resultには正常終了した場合は「1」、正常終了しなかった場合は「0」が代入される）
				int result = ps.executeUpdate();
				if (result != 1) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}
}