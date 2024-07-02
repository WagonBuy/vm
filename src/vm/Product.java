package vm;

/**
 * Productクラスは、自動販売機で販売される商品の情報を管理します。
 * 各商品にはID、名前、価格、在庫数が含まれます。
 * @version 1.0
 */
public class Product {
	private int id; // 商品ID
	private String name; // 商品名
	private int price; // 商品の価格
	private int stock; // 商品の在庫数
	
	/**
	 * Productのコンストラクタ。
	 * 商品のID、名前、価格、在庫数を初期化します。 
	 * @param id 商品ID
	 * @param name 商品名
	 * @param price 商品の価格
	 * @param stock 商品の在庫数
	 */
	public Product(int id, String name, int price, int stock) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}
	
	/**
	 * 商品のIDを取得します。 
	 * @return 商品のID
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 商品の名前を取得します。 
	 * @return 商品の名前
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 商品の価格を取得します。 
	 * @return 商品の価格
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * 商品の在庫数を取得します。 
	 * @return 商品の在庫数
	 */
	public int getStock() {
		return stock;
	}
	
	/**
	 * 商品の在庫数を1減らします。
	 * 在庫数が0より大きい場合のみ在庫数を減らします。
	 */
	public void decrementStock() {
		if (stock > 0) {
			stock--;
		}
	}
	
	/**
	 * 商品の情報を文字列として返します。 
	 * @return 商品の情報を含む文字列
	 */
	@Override
	public String toString() {
		return id + ", 名前: " + name + ", 価格: " + price + "円, 在庫: " + stock;
	}
}
