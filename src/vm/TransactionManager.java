package vm;

import java.util.HashMap;
import java.util.Map;

/**
 * TransactionManagerクラスは、自動販売機の取引管理を行います。
 * 釣銭の在庫管理、投入金額の管理、お釣りの計算などの機能を提供します。
 * @version 1.0
 */
public class TransactionManager {
	private int totalAmount = 0; // 現在の投入金額
	private Map<Integer, Coin> coins; // 釣銭の在庫
	private DatabaseManager dbManager; // データベース管理
	
	/**
	 * TransactionManagerのコンストラクタ。
	 * 釣銭の在庫とデータベース管理オブジェクトを初期化します。 
	 * @param coins 釣銭の在庫を表すマップ
	 * @param dbManager データベース管理オブジェクト
	 */
	public TransactionManager(Map<Integer, Coin> coins, DatabaseManager dbManager) {
		this.coins = coins;
		this.dbManager = dbManager;
	}
	
	/**
	 * お金を投入します。
	 * @param amount 投入する金額
	 * @throws IllegalArgumentException 投入金額が上限を超えた場合
	 */
	public void insertMoney(int amount) {
		if (totalAmount + amount > 1990) {
			throw new IllegalArgumentException("投入金額が上限を超えています。");
		}
		totalAmount += amount;
		Coin coin = coins.get(amount);
		if (coin != null) {
			coin.incrementStock(1);
			dbManager.updateCoinStock(coin);
		}
	}
	
	/**
	 * 釣銭を返却できるか確認します。 
	 * @param change 返却する釣銭の金額
	 * @return 釣銭を返却できる場合はtrue、そうでない場合はfalse
	 */
	public boolean canReturnChange(int change) {
		Map<Integer, Coin> tempCoins = new HashMap<>(coins);
		
		while (change > 0) {
			if (change >= 1000 && tempCoins.get(1000).getStock() > 0) {
				change -= 1000;
				tempCoins.get(1000).decrementStock(1);
			} else if (change >= 500 && tempCoins.get(500).getStock() > 0) {
				change -= 500;
				tempCoins.get(500).decrementStock(1);
			} else if (change >= 100 && tempCoins.get(100).getStock() > 0) {
				change -= 100;
				tempCoins.get(100).decrementStock(1);
			} else if (change >= 50 && tempCoins.get(50).getStock() > 0) {
				change -= 50;
				tempCoins.get(50).decrementStock(1);
			} else if (change >= 10 && tempCoins.get(10).getStock() > 0) {
				change -= 10;
				tempCoins.get(10).decrementStock(1);
			} else {
				return false; // 釣銭が不足している
			}
		}
		return true;
	}
	
	/**
	 * 釣銭を返します。 
	 * @param change 返却する釣銭の金額
	 */
	public void returnChange(int change) {
		while (change > 0) {
			if (change >= 1000 && coins.get(1000).getStock() > 0) {
				change -= 1000;
				coins.get(1000).decrementStock(1);
				dbManager.updateCoinStock(coins.get(1000));
			} else if (change >= 500 && coins.get(500).getStock() > 0) {
				change -= 500;
				coins.get(500).decrementStock(1);
				dbManager.updateCoinStock(coins.get(500));
			} else if (change >= 100 && coins.get(100).getStock() > 0) {
				change -= 100;
				coins.get(100).decrementStock(1);
				dbManager.updateCoinStock(coins.get(100));
			} else if (change >= 50 && coins.get(50).getStock() > 0) {
				change -= 50;
				coins.get(50).decrementStock(1);
				dbManager.updateCoinStock(coins.get(50));
			} else if (change >= 10 && coins.get(10).getStock() > 0) {
				change -= 10;
				coins.get(10).decrementStock(1);
				dbManager.updateCoinStock(coins.get(10));
			} else {
				break;
			}
		}
	}
	
	/**
	 * 現在の投入金額を取得します。 
	 * @return 現在の投入金額
	 */
	public int getTotalAmount() {
		return totalAmount;
	}
	
	/**
	 * 現在の投入金額をリセットします。
	 */
	public void resetTotalAmount() {
		totalAmount = 0;
	}
	
	/**
	 * 現在の投入金額を設定します。 
	 * @param amount 設定する金額
	 */
	public void setTotalAmount(int amount) {
		totalAmount = amount;
	}
}
