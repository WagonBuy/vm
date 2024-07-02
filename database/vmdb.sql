-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- ホスト: 127.0.0.1
-- 生成日時: 2024-07-02 10:12:39
-- サーバのバージョン： 10.4.32-MariaDB
-- PHP のバージョン: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- データベース: `vmdb`
--

-- --------------------------------------------------------

--
-- テーブルの構造 `coins`
--

CREATE TABLE `coins` (
  `coin_type` int(11) NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- テーブルのデータのダンプ `coins`
--

INSERT INTO `coins` (`coin_type`, `stock`) VALUES
(10, 50),
(50, 50),
(100, 50),
(500, 50),
(1000, 0);

-- --------------------------------------------------------

--
-- テーブルの構造 `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` int(11) NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- テーブルのデータのダンプ `products`
--

INSERT INTO `products` (`product_id`, `name`, `price`, `stock`) VALUES
(1, 'Cola', 110, 24),
(2, 'Water', 100, 47),
(3, 'Juice', 120, 30),
(4, 'Tea', 130, 30),
(5, 'Coffee', 200, 28);

-- --------------------------------------------------------

--
-- テーブルの構造 `sales`
--

CREATE TABLE `sales` (
  `sale_id` int(11) NOT NULL,
  `sale_datetime` datetime NOT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- テーブルのデータのダンプ `sales`
--

INSERT INTO `sales` (`sale_id`, `sale_datetime`, `amount`) VALUES
(1, '2024-07-01 14:03:07', 110),
(2, '2024-07-01 14:06:43', 110),
(3, '2024-07-01 14:10:21', 110),
(4, '2024-07-01 14:18:53', 110),
(5, '2024-07-01 14:20:53', 110),
(6, '2024-07-01 14:24:09', 110),
(7, '2024-07-01 14:27:36', 110),
(8, '2024-07-01 14:29:00', 110),
(9, '2024-07-01 14:29:43', 110),
(10, '2024-07-01 14:40:58', 110),
(11, '2024-07-01 14:45:21', 120),
(12, '2024-07-01 14:52:27', 110),
(13, '2024-07-01 14:54:26', 110),
(14, '2024-07-01 15:02:01', 110),
(15, '2024-07-01 15:18:49', 110),
(16, '2024-07-01 15:22:50', 110),
(17, '2024-07-01 15:22:58', 120),
(18, '2024-07-01 15:23:03', 120),
(19, '2024-07-01 15:32:07', 200),
(20, '2024-07-01 15:32:12', 200),
(21, '2024-07-01 15:32:14', 200),
(22, '2024-07-01 15:32:21', 150),
(23, '2024-07-01 15:32:22', 150),
(24, '2024-07-01 15:32:26', 200),
(25, '2024-07-01 15:32:27', 200),
(26, '2024-07-01 15:32:29', 200),
(27, '2024-07-01 15:36:25', 110),
(28, '2024-07-02 09:23:37', 100),
(29, '2024-07-02 09:23:57', 110),
(30, '2024-07-02 09:54:52', 110),
(31, '2024-07-02 09:54:55', 110),
(32, '2024-07-02 09:54:57', 110),
(33, '2024-07-02 09:55:05', 110),
(34, '2024-07-02 09:55:19', 100),
(35, '2024-07-02 09:55:49', 110),
(36, '2024-07-02 09:55:54', 200),
(37, '2024-07-02 09:55:56', 200),
(38, '2024-07-02 11:05:57', 100);

--
-- ダンプしたテーブルのインデックス
--

--
-- テーブルのインデックス `coins`
--
ALTER TABLE `coins`
  ADD PRIMARY KEY (`coin_type`);

--
-- テーブルのインデックス `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`);

--
-- テーブルのインデックス `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`sale_id`);

--
-- ダンプしたテーブルの AUTO_INCREMENT
--

--
-- テーブルの AUTO_INCREMENT `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- テーブルの AUTO_INCREMENT `sales`
--
ALTER TABLE `sales`
  MODIFY `sale_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
