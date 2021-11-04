package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Work;

public class WorkDAO {

	private final String JDBC_URL = "jdbc:mysql://localhost:3306/Attendance?characterEncoding=UTF8&serverTimezone=Asia/Tokyo";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";

	String inoutflag;// 出退勤判定
	String nowdate;// 現在年月日
	String month;// 現在の月
	// 現在日時の取得
	Date d = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("y-MM-dd");

	// 1社員の全月取得
	public List<Work> findAll(Work work) {
		List<Work> allList = new ArrayList<>();

		// データベース接続に使用する情報
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// SELECT文の準備
			String sql = "select A.id,A.work_day,A.stamping_time as 出勤,COALESCE(A.time_id,0) as intime,COALESCE(B.stamping_time,'') as 退勤,COALESCE(B.time_id,2) as outtime,A.note "
					+ "from work as A "
					+ "left join (select DISTINCT * from work where id = ? AND time_id = 2) as B "
					+ "on A.work_day = B.work_day and A.time_id <> 2 where A.time_id <> 9 and A.id = ? group by A.work_day ORDER BY 2,3,4";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, work.getId());
			ps.setInt(2, work.getId());
			// SELECTを実行
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String work_day = rs.getString("A.WORK_DAY");
				String stamping_time = rs.getString("出勤");
				String note = rs.getString("NOTE");
				int time_id = rs.getInt("INTIME");
				String workout_stamping_time = rs.getString("退勤");
				int workout_time_id = rs.getInt("OUTTIME");

				work = new Work(work_day,stamping_time,note,id,time_id,workout_stamping_time,
						workout_time_id);
				allList.add(work);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return allList;
	}

	// 1社員の一ヶ月取得
	public List<Work> findByOwn(Work work) {
		List<Work> atdList = new ArrayList<>();

		// データベース接続に使用する情報
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// SELECT文の準備
			String sql = "select A.id,A.work_day,A.stamping_time as 出勤,COALESCE(A.time_id,0) as intime,COALESCE(B.stamping_time,'') as 退勤,COALESCE(B.time_id,2) as outtime,A.note "
					+ "from work as A "
					+ "left join (select DISTINCT * from work where id = ? AND time_id = 2) as B "
					+ "on A.work_day = B.work_day and A.time_id <> 2 where A.time_id <> 9 and A.id = ? and A.work_day like ? group by A.work_day ORDER BY 2,3,4";
			PreparedStatement ps = con.prepareStatement(sql);
			// 現在日時の取得
			Date d = new Date();
			this.month = new SimpleDateFormat("y-MM").format(d);
			ps.setInt(1, work.getId());
			ps.setInt(2, work.getId());
			ps.setString(3, month + "%");
			// SELECTを実行
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String work_day = rs.getString("A.WORK_DAY");
				String stamping_time = rs.getString("出勤");
				String note = rs.getString("NOTE");
				int time_id = rs.getInt("INTIME");
				String workout_stamping_time = rs.getString("退勤");
				int workout_time_id = rs.getInt("OUTTIME");

				work = new Work(work_day,stamping_time,note,id,time_id,workout_stamping_time,
						workout_time_id);
				atdList.add(work);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return atdList;
	}

	// 1社員の指定月取得
	public List<Work> findByMouth(Work work) {
		List<Work> selectList = new ArrayList<>();

		// データベース接続に使用する情報
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// SELECT文の準備
			String sql = "select A.id,A.work_day,A.stamping_time as 出勤,COALESCE(A.time_id,0) as intime,COALESCE(B.stamping_time,'') as 退勤,COALESCE(B.time_id,2) as outtime,A.note "
					+ "from work as A "
					+ "left join (select DISTINCT * from work where id = ? AND time_id = 2) as B "
					+ "on A.work_day = B.work_day and A.time_id <> 2 where A.time_id <> 9 and A.id = ? and A.work_day like ? group by A.work_day ORDER BY 2,3,4";			PreparedStatement ps = con.prepareStatement(sql);
			// 現在日時の取得
			Date d = new Date();
			this.month = new SimpleDateFormat("y-MM").format(d);
			if (work.getWork_day().length() != 0) {
				this.month = work.getWork_day();
			}
			ps.setInt(1, work.getId());
			ps.setInt(2, work.getId());
			ps.setString(3, this.month + "%");
			// SELECTを実行
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String work_day = rs.getString("A.WORK_DAY");
				String stamping_time = rs.getString("出勤");
				String note = rs.getString("NOTE");
				int time_id = rs.getInt("INTIME");
				String workout_stamping_time = rs.getString("退勤");
				int workout_time_id = rs.getInt("OUTTIME");

				work = new Work(work_day,stamping_time,note,id,time_id,workout_stamping_time,
						workout_time_id);
				selectList.add(work);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return selectList;
	}

	// 出退勤の順番が正しく入力されるかチェックするメソッド
	public boolean isInsert(Work work) {
		// SQL文の作成
		String sql = "";
		// データベース接続
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// 一致データがあるかどうか
			Date d = new Date();
			this.nowdate = this.sdf.format(d);
			sql = "SELECT * FROM WORK WHERE ID = ? AND (TIME_ID = 1 OR TIME_ID = 2) ORDER BY WORK_DAY DESC, TIME_ID DESC";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, work.getId());

			// 実行結果取得
			ResultSet rs = ps.executeQuery();
			// 入力したいwork_idの値を決定
			int insertCheck = 0;
			while (rs.next()) {

				if (work.getInoutflag().matches("work_in")) {// 出勤を入力したい場合
					insertCheck = 1;
				} else if (work.getInoutflag().matches("work_out")) {// 退勤を入力したい場合
					insertCheck = 2;
				}
				// 入力したいTIME_IDと、直近のTIME_IDを比較し、検討する。
				if (insertCheck == 3 - rs.getInt("TIME_ID")) {
					return true;
				} else {
					return false;
				}
			}
			if (insertCheck == 0) {
				sql = "SELECT * FROM WORK WHERE ID = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, work.getId());

				// 実行結果取得
				rs = ps.executeQuery();
				while (rs.next()) {
					return false;
				}
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 出勤しなかった日に空欄のレコードを追加するメソッド
	public void makeEmpty(Work work) {
		// SQL文の作成
		String sql = "";
		// データベース接続
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			sql = "SELECT WORK_DAY FROM WORK WHERE ID = ? ORDER BY WORK_DAY DESC, TIME_ID DESC LIMIT 1;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, work.getId());

			// 実行結果取得
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String str2 = rs.getString("WORK_DAY");
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate ldate = LocalDate.parse(str2, fmt);
				LocalDate now = LocalDate.now();
				now = now.minusDays(1);

				while (now.isAfter(ldate)) {
					ldate = ldate.plusDays(1);
					String str = ldate.format(fmt);

					// INSERT文の準備
					sql = "INSERT INTO WORK(WORK_DAY, STAMPING_TIME, ID, TIME_ID, NOTE) VALUES(?,'',?, 0,'休')";
					ps = con.prepareStatement(sql);
					ps.setString(1, str);
					ps.setInt(2, work.getId());

					// INSERT文を実行(resultには追加された行数が代入される)
					ps.executeUpdate();

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 新規作成
	public boolean create(Work work) {
		if (isInsert(work)) {
			makeEmpty(work);
			// SQL文の作成
			String sql = "";
			if (work.getInoutflag().matches("work_in")) {
				// データベース接続
				try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
					// 一致データがあるかどうか
					Date d = new Date();
					this.nowdate = this.sdf.format(d);
					sql = "SELECT * FROM WORK WHERE ID = ? AND WORK_DAY = ? AND TIME_ID = ?";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, work.getId());
					ps.setString(2, this.nowdate);
					ps.setInt(3, 1);
					// 実行結果取得
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						// 一致データがあればfalse
						this.nowdate = rs.getString("DATE");
						return false;
					}
					// INSERT文の準備
					sql = "INSERT INTO WORK(WORK_DAY, STAMPING_TIME, ID, TIME_ID) VALUES(?,?,?,?)";
					ps = con.prepareStatement(sql);
					ps.setString(1, this.nowdate);
					ps.setString(2, work.getStamping_time());
					ps.setInt(3, work.getId());
					ps.setInt(4, 1);

					// INSERT文を実行(resultには追加された行数が代入される)
					int result = ps.executeUpdate();
					if (result != 1) {
						return false;
					}
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			} else if (work.getInoutflag().matches("work_out")) {
				// データベース接続
				try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
					// 一致データがあるかどうか
					Date d = new Date();
					this.nowdate = this.sdf.format(d);
					sql = "SELECT * FROM WORK WHERE ID = ? AND WORK_DAY = ? AND TIME_ID = ?";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, work.getId());
					ps.setString(2, this.nowdate);
					ps.setInt(3, 2);
					// 実行結果取得
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						// 一致データがあればfalse
						this.nowdate = rs.getString("DATE");
						return false;
					}
					// INSERT文の準備
					sql = "INSERT INTO WORK(WORK_DAY, STAMPING_TIME, ID, TIME_ID) VALUES(?,?,?,?)";
					ps = con.prepareStatement(sql);
					ps.setString(1, this.nowdate);
					ps.setString(2, work.getStamping_time());
					ps.setInt(3, work.getId());
					ps.setInt(4, 2);

					// INSERT文を実行(resultには追加された行数が代入される)
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
		} else {
			return false;
		}
	}

	// 更新
	public boolean update(Work work) {
		// データベースへ接続
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// UPDATE文の準備
			String sql = "UPDATE WORK SET NOTE = ? WHERE ID = ? AND WORK_DAY = ?";
			// UPDATE文中の「?」に使用する値を設定しSQLを完成
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,"(申請中)"+ work.getNote());
			ps.setInt(2, work.getId());
			ps.setString(3, work.getWork_day());
			// UPDATE文を実行（resultには正常終了した場合は「1」、正常終了しなかった場合は「0」が代入される）
			int result = ps.executeUpdate();
			if (result != 1) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}