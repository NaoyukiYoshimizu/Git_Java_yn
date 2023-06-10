package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Delivery;

public class DeliveryDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/shop?characterEncoding=UTF8&serverTimezone=Asia/Tokyo";
	private final String DB_USER = "root";
	private final String DB_PASS = "1Root2";
	long d_id,user_id,nsin;
	String delivery,pay;

	// 新規作成
	public boolean create(Delivery delivery) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備
			String sql = "INSERT INTO DELIVERY (nsin,user_id,delivery,pay) VALUES (?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, delivery.getNsin());
			ps.setLong(2, delivery.getUser_id());
			ps.setString(3, delivery.getDelivery());
			ps.setString(4, delivery.getPay());

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
