-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th2 12, 2022 lúc 02:38 PM
-- Phiên bản máy phục vụ: 10.1.31-MariaDB
-- Phiên bản PHP: 7.1.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `supeo_forum`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_student`
--

CREATE TABLE `tbl_student` (
  `student_id` int(11) NOT NULL,
  `student_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_student`
--

INSERT INTO `tbl_student` (`student_id`, `student_name`) VALUES
(27, 'tuyen64'),
(28, 'tuyen98'),
(29, 'tuyen5'),
(30, 'tuyen21'),
(31, 'tuyen40'),
(32, 'tuyen11'),
(33, 'tuyen27'),
(34, 'tuyen100'),
(35, 'tuyen100'),
(36, 'tuyen67'),
(37, 'tuyen70'),
(38, 'tuyen41'),
(39, 'tuyen58'),
(40, 'tuyen70'),
(41, 'tuyen37'),
(42, 'tuyen54'),
(43, 'tuyen15'),
(44, 'tuyen42'),
(45, 'tuyen32'),
(46, 'tuyen4'),
(47, 'tuyen83'),
(48, 'tuyen96'),
(49, 'tuyen53'),
(50, 'tuyen40'),
(51, 'tuyen39'),
(52, 'tuyen89'),
(53, 'tuyen53'),
(54, 'tuyen57'),
(55, 'tuyen6'),
(56, 'tuyen85'),
(57, 'tuyen91');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_student_subject`
--

CREATE TABLE `tbl_student_subject` (
  `student_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_student_subject`
--

INSERT INTO `tbl_student_subject` (`student_id`, `subject_id`) VALUES
(27, 27),
(28, 28),
(29, 29),
(30, 30),
(31, 31),
(32, 32),
(33, 33),
(34, 34),
(35, 35),
(36, 36),
(37, 37),
(38, 38),
(39, 39),
(40, 40),
(41, 41),
(42, 42),
(43, 43),
(44, 44),
(45, 45),
(46, 46),
(47, 47),
(48, 48),
(49, 49),
(50, 50),
(51, 51),
(52, 52),
(53, 53),
(53, 54);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_subject`
--

CREATE TABLE `tbl_subject` (
  `subject_id` int(11) NOT NULL,
  `subject_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_subject`
--

INSERT INTO `tbl_subject` (`subject_id`, `subject_name`) VALUES
(27, 'ma64'),
(28, 'ma98'),
(29, 'ma5'),
(30, 'ma21'),
(31, 'ma40'),
(32, 'ma11'),
(33, 'ma27'),
(34, 'ma100'),
(35, 'ma100'),
(36, 'ma67'),
(37, 'ma70'),
(38, 'ma41'),
(39, 'ma58'),
(40, 'ma70'),
(41, 'ma37'),
(42, 'ma54'),
(43, 'ma15'),
(44, 'ma42'),
(45, 'ma32'),
(46, 'ma4'),
(47, 'ma83'),
(48, 'ma96'),
(49, 'ma53'),
(50, 'ma40'),
(51, 'ma39'),
(52, 'ma89'),
(53, 'ma53'),
(54, 'ma57'),
(55, 'ma6'),
(56, 'ma85'),
(57, 'ma91');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `tbl_student`
--
ALTER TABLE `tbl_student`
  ADD PRIMARY KEY (`student_id`);

--
-- Chỉ mục cho bảng `tbl_student_subject`
--
ALTER TABLE `tbl_student_subject`
  ADD PRIMARY KEY (`student_id`,`subject_id`),
  ADD KEY `FKbvbdfavpbohtu2vh9kp944u9w` (`subject_id`);

--
-- Chỉ mục cho bảng `tbl_subject`
--
ALTER TABLE `tbl_subject`
  ADD PRIMARY KEY (`subject_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `tbl_student`
--
ALTER TABLE `tbl_student`
  MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT cho bảng `tbl_subject`
--
ALTER TABLE `tbl_subject`
  MODIFY `subject_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `tbl_student_subject`
--
ALTER TABLE `tbl_student_subject`
  ADD CONSTRAINT `FK3xp9h6tfnv9o6m6xpwyww83kw` FOREIGN KEY (`student_id`) REFERENCES `tbl_student` (`student_id`),
  ADD CONSTRAINT `FKbvbdfavpbohtu2vh9kp944u9w` FOREIGN KEY (`subject_id`) REFERENCES `tbl_subject` (`subject_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
