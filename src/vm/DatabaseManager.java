package vm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * DatabaseManagerクラスは、自動販売機のデータベース管理を行います。
 * 商品と釣銭のデータの読み込み、在庫の更新、売上の記録などの機能を提供します。
 * @version 1.0
 */
public class DatabaseManager {
	private Connection connection; // データベース接続用のConnectionオブジェクト
	
	/**
	 * データベースに接続します。
	 * データベースへの接続に成功すると、connectionフィールドが初期化されます。
	 */
	public void connect() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vmdb", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * データベースから商品を読み込みます。 
	 * @return 商品IDをキーとするProductオブジェクトのマップ
	 */
	public Map<Integer, Product> loadProducts() {
		Map<Integer, Product> products = new HashMap<>();
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Products");
			
			while (rs.next()) {
				int id = rs.getInt("product_id");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				products.put(id, new Product(id, name, price, stock));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	/**
	 * データベースから釣銭を読み込みます。 
	 * @return 釣銭の種類をキーとするCoinオブジェクトのマップ
	 */
	public Map<Integer, Coin> loadCoins() {
		Map<Integer, Coin> coins = new HashMap<>();
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Coins");
			
			while (rs.next()) {
				int coinType = rs.getInt("coin_type");
				int stock = rs.getInt("stock");
				coins.put(coinType, new Coin(coinType, stock));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return coins;
	}
	
	/**
	 * 商品の在庫を更新します。
	 * @param product 更新するProductオブジェクト
	 */
	public void updateProductStock(Product product) {
		try {
			PreparedStatement pstmt = connection.prepareStatement("UPDATE Products SET stock = ? WHERE product_id = ?");
			pstmt.setInt(1, product.getStock());
			pstmt.setInt(2, product.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 売上を記録します。 
	 * @param amount 売上金額
	 */
	public void recordSale(int amount) {
		try {
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Sales (sale_datetime, amount) VALUES (NOW(), ?)");
			pstmt.setInt(1, amount);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 釣銭の在庫を更新します。 
	 * @param coin 更新するCoinオブジェクト
	 */
	public void updateCoinStock(Coin coin) {
		try {
			PreparedStatement pstmt = connection.prepareStatement("UPDATE Coins SET stock = ? WHERE coin_type = ?");
			pstmt.setInt(1, coin.getStock());
			pstmt.setInt(2, coin.getValue());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
