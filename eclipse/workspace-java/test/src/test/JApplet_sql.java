package test;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;


public class JApplet_sql extends JFrame implements ActionListener {
	private static final String window_title = "テーブル表示";
	PersistenceManager persistMgr;
	TableModel model;
	ResultSet rs;
	String s;

	JTable table;
	JTextField queryField;
	JButton queryButton;
	JTextField updateField;
	JButton updateButton;

	public static void main(String[] args)  {
		JApplet_sql obj = new JApplet_sql();
	}

	JApplet_sql() {
		
		// DBMS への接続
		persistMgr = new PersistenceManager();
		// SQL 文の発行とモデルの取得
		try {
			rs = persistMgr.executeSQL(s);
			model = new DataModel(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		// JTable の作成
		table = new JTable(model);
		JScrollPane scrollpane = new JScrollPane(table);

		// コンポーネントの作成
		queryField = new JTextField("select * from addressBook;");
		queryButton = new JButton("Select");
		queryButton.addActionListener(this);
		JPanel queryPanel = new JPanel();
		queryPanel.setLayout(new GridLayout(2, 1));
		queryPanel.add(queryField);
		queryPanel.add(queryButton);

		// executeUpdate()用のテキスト・フィールドの作成
		updateField = new JTextField(
			"INSERT INTO addressBook (name,address) VALUES ('Google', 'http://www.google.co.jp/');");
		updateButton = new JButton("Submit");
		updateButton.addActionListener(this);
		JPanel updatePanel = new JPanel();
		updatePanel.setLayout(new GridLayout(2, 1));
		updatePanel.add(updateField);
		updatePanel.add(updateButton);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.add(queryPanel);
		panel.add(updatePanel);

		// コンテント・ペインの取得
		Container cont = getContentPane();
		// コンテント・ペインに追加
		cont.add(panel, BorderLayout.NORTH);
		cont.add(scrollpane, BorderLayout.CENTER);

		// JFrame の作成
		setTitle(JApplet_sql.window_title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		
	}

	//　ボタン押したときの処理
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == queryButton) {
			String str = queryField.getText();
			try {
				rs = persistMgr.executeSQL(str);
				model = new DataModel(rs);
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(
				          null, "select文のみ入れてください\nもしくはコンソールのエラー内容を確認してください");
			    return;
			}
			table.setModel(model);
		} else if (ae.getSource() == updateButton) {
			String str = updateField.getText();
			try {
				persistMgr.executeUpdate(str);
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(
				          null, "DELETE/INSERT文のみ入れてください\nもしくはコンソールのエラー内容を確認してください");
				return;
			}
			table.setModel(model);
		}
	}
}


class PersistenceManager {
	Statement stmt;

	// DBMS への接続
	PersistenceManager() {
		try {
			// JDBC Driver の登録
			Class.forName("com.mysql.cj.jdbc.Driver");
			// データベースへの接続
			Connection con = DriverManager.getConnection(
			    "jdbc:mysql://localhost:3306/test_db?characterEncoding=UTF-8&serverTimezone=Japan", "root",
			    "root");
			// SQL ステートメント・オブジェクトの作成
			stmt = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// SQL 文の発行
	ResultSet executeSQL(String str) throws SQLException {
		return stmt.executeQuery(str);
	}
	// 挿入／削除用のメソッド
	int executeUpdate(String str) throws SQLException {
		return stmt.executeUpdate(str);
	}
	
}

class DataModel extends AbstractTableModel {
	String[] columnNames;
	Vector rows = new Vector();

	// モデルの作成
	DataModel(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();	// ResultSetのメタデータの取得

		int numberOfColumns =  metaData.getColumnCount();	// 列数を取得
		columnNames = new String[numberOfColumns];	// 列名を保持する配列の作成

		for(int column = 0; column < numberOfColumns; column++) {
			columnNames[column] = metaData.getColumnLabel(column + 1);	// 列名を取得
		}

		rows = new Vector();	// ResultSet全体のデータを保持するベクトル
		while (rs.next()) {
			Vector newRow = new Vector();	// ResultSetの一行分のデータを保持するベクトル
			for (int i = 1; i <= getColumnCount(); i++) {
				newRow.addElement(rs.getObject(i));	// 各データを取得し追加

			}
			rows.addElement(newRow);	// 各行を追加
		}
	}

	// 以下 AbstractTableModel の実装
	public String getColumnName(int column) {
		if (columnNames[column] != null) {
			return columnNames[column];
		} else {
			return "";
		}
	}
	public int getColumnCount() {
		return columnNames.length;
	}
	public int getRowCount() {
		return rows.size();
	}
	public Object getValueAt(int aRow, int aColumn) {
		Vector row = (Vector)rows.elementAt(aRow);
		return row.elementAt(aColumn);
	}
}