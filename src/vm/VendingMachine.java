package vm;

import java.util.Map;
import java.util.Scanner;

/**
 * VendingMachineクラスは、自動販売機の機能を提供するクラスです。
 * 商品の選択、投入金額の管理、お釣りの計算などを行います。 
 * @version 1.0
 */
public class VendingMachine {
	private TransactionManager transactionManager; // トランザクション管理
	private Map<Integer, Product> products; // 商品の在庫
	private DatabaseManager dbManager; // データベース管理
	
	/**
	 * VendingMachineのコンストラクタ。
	 * データベースに接続し、商品と硬貨の情報をロードします。
	 * データベース接続が成功すると、商品の在庫と硬貨の情報をロードし、
	 * トランザクションマネージャを初期化します。
	 */
	public VendingMachine() {
		dbManager = new DatabaseManager();
		dbManager.connect();
		products = dbManager.loadProducts();
		Map<Integer, Coin> coins = dbManager.loadCoins();
		transactionManager = new TransactionManager(coins, dbManager);
	}
	
	/**
	 * 有効な金額かをチェックします。
	 * @param amount チェックする金額
	 * @return 有効な金額であればtrue、そうでなければfalse
	 */
	public boolean isValidAmount(int amount) {
		return amount == 10 || amount == 50 || amount == 100 || amount == 500 || amount == 1000;
	}
	
	/**
	 * お金を投入します。
	 * 指定された金額が有効でない場合はエラーメッセージを表示し、
	 * トランザクションマネージャに金額を追加します。
	 * @param amount 投入する金額
	 */
	public void insertMoney(int amount) {
		if (!isValidAmount(amount)) {
			System.out.println("無効な金額です。使用可能な硬貨は10円、50円、100円、500円、使用可能な紙幣は1000円のみです。");
			return;
		}
		try {
			transactionManager.insertMoney(amount);
			System.out.println("現在の投入金額: " + transactionManager.getTotalAmount() + "円");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 商品を選択します。
	 * 指定された商品IDに対応する商品を取得し、以下の条件をチェックします:
	 * 1. 商品が存在するか
	 * 2. 商品が在庫切れでないか
	 * 3. 投入金額が商品価格以上であるか
	 * 4. お釣りが返却可能か
	 * 条件を満たす場合は商品を購入し、在庫と売上を更新し、お釣りを返却します。
	 * @param productId 選択する商品のID
	 */
	public void selectProduct(int productId) {
		Product product = products.get(productId);
		
		if (product == null) {
			System.out.println("無効な商品IDです。");
			return;
		}
		
		if (product.getStock() == 0) {
			System.out.println("売り切れです。");
			return;
		}
		
		if (transactionManager.getTotalAmount() < product.getPrice()) {
			System.out.println("投入金額が不足しています。");
			System.out.println("現在の投入金額: " + transactionManager.getTotalAmount() + "円");
			return;
		}
		
		int change = transactionManager.getTotalAmount() - product.getPrice();
		if (!transactionManager.canReturnChange(change)) {
			System.out.println("お釣りが不足しています。他の商品を選択してください。");
			System.out.println("現在の投入金額: " + transactionManager.getTotalAmount() + "円");
			return;
		}
		
		product.decrementStock();
		dbManager.updateProductStock(product);
		dbManager.recordSale(product.getPrice());
		transactionManager.returnChange(change);
		
		System.out.println("購入成功: " + product.getName());
		transactionManager.setTotalAmount(change); // 購入後の残金をセット
		System.out.println("現在の投入金額: " + transactionManager.getTotalAmount() + "円");
	}
	
	/**
	 * 商品リストを表示します。
	 * 自動販売機に登録されている全ての商品を表示します。
	 */
	public void showProducts() {
		System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-");
		System.out.println("商品リスト:");
		for (Product product : products.values()) {
			System.out.println(product);
		}
	}
	
	/**
	 * 取引を処理します。
	 * ユーザーからの入力を受け取り、投入金額の処理と商品選択、
	 * 釣銭の返却を行います。
	 * 投入終了、商品選択、釣銭返却が完了するとループを抜けます。
	 */
	public void processTransaction() {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			System.out.println("お金を投入してください (投入終了 = 0): ");
			int amount = scanner.nextInt();
			if (amount == 0) {
				System.out.println("現在の投入金額: " + transactionManager.getTotalAmount() + "円");
				break;
			}
			insertMoney(amount);
		}
		
		while (true) {
			showProducts();
			System.out.println("商品番号を選択してください (釣銭返却 = 0): ");
			int productId = scanner.nextInt();
			if (productId == 0) {
				int currentAmount = transactionManager.getTotalAmount();
				if (currentAmount >= 0) {
					System.out.println("お釣り: " + currentAmount + "円");
					transactionManager.returnChange(currentAmount);
					transactionManager.resetTotalAmount();
				}
				break;
			} 
			selectProduct(productId);
		}
		
		scanner.close();
	}
	
	/**
	 * 自動販売機の取引処理を開始します。
	 * このメソッドはプログラムのエントリーポイントです。
	 * @param args コマンドライン引数
	 */
	public static void main(String[] args) {
		VendingMachine vm = new VendingMachine();
		vm.processTransaction();
	}
}
