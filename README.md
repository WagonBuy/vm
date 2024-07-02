<h1>自動販売機システム</h1>
このプロジェクトは、自動販売機のシミュレーションを行うJavaプログラムです。自動販売機は商品を販売し、お金の投入、在庫管理、売上管理、お釣りの返却などの機能を提供します。</h6>
<h2>一、クラス一覧</h2>
1. VendingMachine<br>
2. DatabaseManager<br>
3. Product<br>
4. TransactionManager<br>
5. Coin<br>
<h3>1.VendingMachine クラス</h3>
`VendingMachine`クラスは、自動販売機の機能を提供するクラスです。商品選択、投入金額の管理、お釣りの計算などの機能を実装しています。
<h4>主なメソッド</h4>
　・VendingMachine(): コンストラクタ。データベースに接続し、商品と硬貨の情報をロードします。<br>
　・isValidAmount(int amount): 指定された金額が有効かを確認します。<br>
　・insertMoney(int amount): 指定された金額を投入します。<br>
　・selectProduct(int productId): 指定された商品を選択します。<br>
　・showProducts(): 自動販売機に登録されている全ての商品を表示します。<br>
　・processTransaction(): 取引を処理します。<br>
<h3>2.DatabaseManager クラス</h3>
`DatabaseManager`クラスは、自動販売機のデータベース管理を行います。商品の読み込み、硬貨の読み込み、在庫の更新、売上の記録などの機能を提供します。
<h4>主なメソッド</h4>
　・connect(): データベースに接続します。<br>
　・loadProducts(): データベースから商品を読み込みます。<br>
　・loadCoins(): データベースから硬貨を読み込みます。<br>
　・updateProductStock(Product product): 商品の在庫を更新します。<br>
　・recordSale(int amount): 売上を記録します。<br>
　・updateCoinStock(Coin coin): 硬貨の在庫を更新します。<br>
<h3>3.Product クラス</h3>
`Product`クラスは、自動販売機で販売される商品の情報を管理します。各商品にはID、名前、価格、在庫数が含まれます。
<h4>主なメソッド</h4>
　・Product(int id, String name, int price, int stock): コンストラクタ。商品ID、名前、価格、在庫数を初期化します。<br>
　・getId(): 商品IDを取得します。<br>
　・getName(): 商品の名前を取得します。<br>
　・getPrice(): 商品の価格を取得します。<br>
　・getStock(): 商品の在庫数を取得します。<br>
　・decrementStock(): 商品の在庫数を1減らします。<br>
　・toString(): 商品の情報を文字列として返します。<br>
<h3>4.TransactionManager クラス</h3>
`TransactionManager`クラスは、自動販売機の取引管理を行います。硬貨の在庫管理、投入金額の管理、お釣りの計算などの機能を提供します。
<h4>主なメソッド</h4>
　・TransactionManager(Map<Integer, Coin> coins, DatabaseManager dbManager): コンストラクタ。硬貨の在庫とデータベース管理オブジェ<br>
　　クトを初期化します。<br>
　・insertMoney(int amount): お金を投入します。<br>
　・canReturnChange(int change): お釣りを返却できるか確認します。<br>
　・returnChange(int change): お釣りを返します。<br>
　・getTotalAmount(): 現在の投入金額を取得します。<br>
　・resetTotalAmount(): 現在の投入金額をリセットします。<br>
　・setTotalAmount(int amount): 現在の投入金額を設定します。<br>
<h3>5.Coin クラス</h3>
`Coin`クラスは、自動販売機で使用される硬貨の情報を管理します。硬貨の価値と在庫数を保持し、在庫の増減を行うメソッドを提供します。
<h4>主なメソッド</h4>
　・Coin(int value, int stock): コンストラクタ。硬貨の価値と在庫数を初期化します。<br>
　・getValue(): 硬貨の価値を取得します。<br>
　・getStock(): 在庫数を取得します。<br>
　・incrementStock(int amount): 在庫を増やします。<br>
　・decrementStock(int amount): 在庫を減らします。<br>
<h2>二、環境構築</h2>
1. MySQLデータベースをセットアップし、`vmdb`データベースを作成します。<br>
2. `vmdb.sql`をインポートします。<br>
3. プロジェクトをクローンし、Java環境をセットアップします。<br>
<h2>三、実行方法</h2>
`VendingMachine`クラスの`main`メソッドを実行して、取引を開始します。
<h2>四、ライセンス</h2>
このプロジェクトはMITライセンスのもとで公開されています。
