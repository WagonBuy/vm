package vm;

/**
 * Coinクラスは、自動販売機で使用される釣銭の情報を管理します。
 * 釣銭の種別と在庫数を保持し、在庫の増減を行うメソッドを提供します。
 * @version 1.0
 */
public class Coin {
	private int value; // 釣銭の種別
	private int stock; // 在庫数
	
	/**
	 * Coinのコンストラクタ。
	 * 釣銭の種別と在庫数を初期化します。
	 * @param value 釣銭の種別
	 * @param stock 在庫数
	 */
	public Coin(int value, int stock) {
		this.value = value;
		this.stock = stock;
	}
	
	/**
	 * 釣銭の種別を取得します。
	 * @return 釣銭の種別
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * 釣銭在庫数を取得します。
	 * @return 釣銭在庫数
	 */
	public int getStock() {
		return stock;
	}
	
	/**
	 * 釣銭在庫を増やします。
	 * @param amount 増やす釣銭在庫の数
	 */
	public void incrementStock(int amount) {
		stock += amount;
	}
	
	/**
	 * 釣銭在庫を減らします。
	 * @param amount 減らす釣銭在庫の数
	 */
	public void decrementStock(int amount) {
		if (stock >= amount) {
			stock -= amount;
		}
	}
}
