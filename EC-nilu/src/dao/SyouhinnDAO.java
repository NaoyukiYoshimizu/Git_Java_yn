package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Syouhinn;

public class SyouhinnDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/shop";
	private final String DB_USER = "root";
	private final String DB_PASS = "1Root2";
	long user_id,kanri_id;
	String goods,goods_detail;
	int selling_price,cost_price,stock;
	String nsin;
	
	// 全件取得
	public List<Syouhinn> findByAll() {
		List<Syouhinn> syouhinnList = new ArrayList<>();
		// SQL文の作成
		String sql = "";
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			sql = "SELECT * FROM syouhinn WHERE USER_ID = 1";
			PreparedStatement ps = con.prepareStatement(sql);
			// 実行結果取得
			ResultSet rs = ps.executeQuery();
			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			while (rs.next()) {
				this.kanri_id = rs.getLong("kanri_id");
				this.goods = rs.getString("goods");
				this.goods_detail = rs.getString("goods_detail");
				this.selling_price = rs.getInt("selling_price");
				this.cost_price = rs.getInt("cost_price");
				this.stock = rs.getInt("stock");
				this.user_id = rs.getLong("user_id");
				this.nsin = rs.getString("nsin");
				Syouhinn syouhinn = new Syouhinn(kanri_id,goods,goods_detail,selling_price,cost_price,stock,user_id,nsin);
				syouhinnList.add(syouhinn);
			}

		} catch (SQLException e) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(e);
			e.printStackTrace();
		}
		return syouhinnList;
	}
	// カートを見る
	public  List<Syouhinn>  findByIncart(Syouhinn syouhinn) {
		List<Syouhinn> incartList = new ArrayList<>();
		// SQL文の作成
		String sql = "";
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			sql = "SELECT * FROM syouhinn WHERE USER_ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, syouhinn.getUser_id());
			ResultSet rs = ps.executeQuery();
			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			while (rs.next()) {
				this.kanri_id = rs.getLong("kanri_id");
				this.goods = rs.getString("goods");
				this.goods_detail = rs.getString("goods_detail");
				this.selling_price = rs.getInt("selling_price");
				this.cost_price = rs.getInt("cost_price");
				this.stock = rs.getInt("stock");
				this.user_id = rs.getLong("user_id");
				this.nsin = rs.getString("nsin");
				
				syouhinn = new Syouhinn(kanri_id,goods,goods_detail,selling_price,cost_price,stock,user_id,nsin);
				incartList.add(syouhinn);
			}

		} catch (SQLException e) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(e);
			e.printStackTrace();
		}
		return incartList;
	}
	// 詳細
	public void detail(Syouhinn syouhinn) {
		// SQL文の作成
		String sql = "";
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			sql = "SELECT * FROM syouhinn WHERE KANRI_ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, syouhinn.getKanri_id());
			ResultSet rs = ps.executeQuery();
			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			while (rs.next()) {
				syouhinn.setKanri_id(rs.getLong("kanri_id"));
				syouhinn.setGoods(rs.getString("goods"));
				syouhinn.setGoods_detail(rs.getString("goods_detail"));
				syouhinn.setSelling_price(rs.getInt("selling_price"));
				syouhinn.setCost_price(rs.getInt("cost_price"));
				syouhinn.setStock(rs.getInt("stock"));
				syouhinn.setNsin(rs.getString("nsin"));
			}
		} catch (SQLException e) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(e);
			e.printStackTrace();
		}
	}
	// 配達用
	public void findBydata(Syouhinn syouhinn) {
		// SQL文の作成
		String sql = "";
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			sql = "SELECT * FROM syouhinn WHERE NSIN = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, syouhinn.getNsin());
			ResultSet rs = ps.executeQuery();
			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			while (rs.next()) {
				this.nsin = rs.getString("nsin");
			}
			syouhinn.setNsin(this.nsin);

		} catch (SQLException e) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(e);
			e.printStackTrace();
		}
	}
	//削除
	public boolean delete(Syouhinn syouhinn) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

		// DELETE文の準備
		String sql = "DELETE FROM syouhinn WHERE KANRI_ID=?";
		PreparedStatement ps = con.prepareStatement(sql);
		// DELETE文中の「?」に使用する値を設定しSQLを完成
		ps.setLong(1, syouhinn.getKanri_id());
		// DELETE文を実行（resultには正常終了した場合は「1」、正常終了しなかった場合は「0」が代入される）
		int result = ps.executeUpdate();
		if (result == 1) {
			return true;
		}else 
			return false;
		} catch (SQLException e) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(e);
			e.printStackTrace();
		}
		return false;
	}
	// 新規作成
	public boolean create(Syouhinn syouhinn) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// データベースから商品情報を呼び出す
			String sql = "SELECT * FROM syouhinn WHERE KANRI_ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, syouhinn.getKanri_id());
			this.user_id = syouhinn.getUser_id();
			ResultSet rs = ps.executeQuery();
			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			while (rs.next()) {
				this.goods = rs.getString("goods");
				this.goods_detail = "";
				this.selling_price = rs.getInt("selling_price");
				this.cost_price = rs.getInt("cost_price");
				this.stock = 1;
				this.nsin = rs.getString("nsin");
			}
			
			// INSERT文の準備
			sql = "INSERT INTO syouhinn (goods,selling_price,cost_price,stock,goods_detail,user_id,nsin) VALUES (?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, this.goods);
			ps.setInt(2, this.selling_price);
			ps.setInt(3, this.cost_price);
			ps.setInt(4, this.stock);
			ps.setString(5, this.goods_detail);
			ps.setLong(6, this.user_id);
			ps.setString(7, this.nsin);
			
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
	//購入されたときの更新
	public boolean update(Syouhinn syouhinn) {
		// SQL文の作成
		String sql = "";
		// データベースへ接続
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// データベースから在庫数を呼び出す
			sql = "SELECT * FROM syouhinn WHERE NSIN = ? ORDER BY KANRI_ID LIMIT 1";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, syouhinn.getNsin());
			ResultSet rs = ps.executeQuery();
			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			while (rs.next()) {
				this.stock = rs.getInt("stock");
				this.kanri_id = rs.getLong("kanri_id");
			}
			// UPDATE文の準備
			sql = "UPDATE syouhinn SET STOCK=? WHERE KANRI_ID=?";
			ps = con.prepareStatement(sql);
			// UPDATE文中の「?」に使用する値を設定しSQLを完成
			ps.setInt(1, this.stock-1);
			ps.setLong(2, this.kanri_id);
			// UPDATE文を実行（resultには正常終了した場合は「1」、正常終了しなかった場合は「0」が代入される）
			int result = ps.executeUpdate();
			if (result != 1) {
				return false;
			}
			// データベースから在庫数を呼び出す
			sql = "SELECT * FROM syouhinn WHERE NSIN = ? ORDER BY KANRI_ID DESC LIMIT 1";
			ps = con.prepareStatement(sql);
			ps.setString(1, syouhinn.getNsin());
			rs = ps.executeQuery();
			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			while (rs.next()) {
				this.kanri_id = rs.getLong("kanri_id");
			}
			// UPDATE文の準備
			sql = "UPDATE syouhinn SET STOCK=? WHERE KANRI_ID=?";
			ps = con.prepareStatement(sql);
			// UPDATE文中の「?」に使用する値を設定しSQLを完成
			ps.setInt(1, 0);
			ps.setLong(2, this.kanri_id);
			// UPDATE文を実行（resultには正常終了した場合は「1」、正常終了しなかった場合は「0」が代入される）
			result = ps.executeUpdate();
			if (result != 1) {
				return false;
			}
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
