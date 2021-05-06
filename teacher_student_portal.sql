-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2021 at 06:31 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `teacher_student_portal`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_login`
--

CREATE TABLE `admin_login` (
  `username` varchar(50) NOT NULL,
  `ID` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin_login`
--

INSERT INTO `admin_login` (`username`, `ID`, `password`) VALUES
('Ruchi', 'admin001', '9694');

-- --------------------------------------------------------

--
-- Table structure for table `chat`
--

CREATE TABLE `chat` (
  `sender` varchar(20) NOT NULL,
  `reciver` varchar(20) NOT NULL,
  `msg` varchar(200) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `code`
--

CREATE TABLE `code` (
  `code` varchar(50) NOT NULL,
  `subject` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `code`
--

INSERT INTO `code` (`code`, `subject`) VALUES
('2cs4a', 'distributed system'),
('4cs3', 'Computer network'),
('6cs09', 'mobile computing'),
('8cs2', 'image processing'),
('8cs5', 'a1'),
('8cs6', 'a2'),
('8ec1', 's1'),
('8ec2', 's2');

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `sem` int(10) NOT NULL,
  `branch` varchar(50) NOT NULL,
  `sub1` varchar(50) DEFAULT NULL,
  `sub2` varchar(50) DEFAULT NULL,
  `sub3` varchar(50) DEFAULT NULL,
  `sub4` varchar(50) DEFAULT NULL,
  `sub5` varchar(50) DEFAULT NULL,
  `sub6` varchar(50) DEFAULT NULL,
  `sub7` varchar(50) DEFAULT NULL,
  `sub8` varchar(50) DEFAULT NULL,
  `sub9` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`sem`, `branch`, `sub1`, `sub2`, `sub3`, `sub4`, `sub5`, `sub6`, `sub7`, `sub8`, `sub9`) VALUES
(1, 'Computer Science', 'd', '', '', '', '', '', '', '', ''),
(1, 'Information Technology', '1', '3', '2', NULL, '', '', '4', '', ''),
(2, 'Computer Science', '1', '', '', '', '', '', '', '', ''),
(2, 'Information Technology', '2cs4a', '646', '46', '46', '46', '', '', '', ''),
(4, 'Computer Science', '4cs3', '', '', '', '', '', '', '', ''),
(6, 'Computer Science', '8cs1', '', '', '', '', '', '', '', ''),
(7, 'Computer Science', 'computer', '', '', '', '', '', '', '', ''),
(8, 'Computer Science', '8cs1', '8cs2', '', '', '', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `eventid` int(200) NOT NULL,
  `eventname` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`eventid`, `eventname`) VALUES
(11, 'project submission'),
(12, 'bkbiet pilani');

-- --------------------------------------------------------

--
-- Table structure for table `files`
--

CREATE TABLE `files` (
  `subject` varchar(10) NOT NULL,
  `topic` varchar(50) NOT NULL,
  `date` varchar(20) NOT NULL,
  `time` varchar(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `path` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `files`
--

INSERT INTO `files` (`subject`, `topic`, `date`, `time`, `name`, `path`) VALUES
('8cs1', 'not now', '19//5//2017', '11:59:3', '8cs1_19_5_2017_11_59_3-DEPARTMENT OF SPACE.pdf', '../files/'),
('null', 'not now', '18//5//2017', '21:37:1', 'null_18_5_2017_21_37_1-DEPARTMENT OF SPACE.pdf', '../files/');

-- --------------------------------------------------------

--
-- Table structure for table `getlocation`
--

CREATE TABLE `getlocation` (
  `uname` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `lattitude` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `longitude` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `date` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `time` varchar(60) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `getlocation`
--

INSERT INTO `getlocation` (`uname`, `password`, `lattitude`, `longitude`, `date`, `time`) VALUES
('piyush', 'piyush1234', '874', '784', '12/02/2017', '05:45');

-- --------------------------------------------------------

--
-- Table structure for table `squery`
--

CREATE TABLE `squery` (
  `qid` int(20) NOT NULL,
  `s_id` varchar(20) NOT NULL,
  `s_code` varchar(20) NOT NULL,
  `counter` int(20) NOT NULL,
  `query` varchar(500) NOT NULL,
  `tid` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `squery`
--

INSERT INTO `squery` (`qid`, `s_id`, `s_code`, `counter`, `query`, `tid`) VALUES
(1157996779, '13ebkcs082', '8cs2', 1, 'i have a query ', '9521188480'),
(1219374167, '13ebkcs098', '8cs1', 1, 'Enter Query here...', '9521188481'),
(1543538320, '13ebkcs082', '8cs1', 1, 'Enter Query here...', '9521188480'),
(1543538320, '13ebkcs082', '8cs1', 2, 'query', '9521188480'),
(1654028575, '13ebkcs082', '8cs1', 1, 'hello sir, i have a doubt', '7891283585'),
(1674338531, '13ebkcs082', '8cs2', 2, 'query', '9521188480'),
(1840546130, '9521188480', '8cs1', 1, 'Enter Query here...', '9521188481');

-- --------------------------------------------------------

--
-- Table structure for table `stuent_login`
--

CREATE TABLE `stuent_login` (
  `student_id` varchar(10) NOT NULL,
  `password` varchar(50) NOT NULL,
  `s_name` varchar(50) NOT NULL,
  `phone_no` varchar(10) NOT NULL,
  `father_name` varchar(50) NOT NULL,
  `branch` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `stuent_login`
--

INSERT INTO `stuent_login` (`student_id`, `password`, `s_name`, `phone_no`, `father_name`, `branch`, `Email`) VALUES
('13ebkcs100', '9966', 'sumit', '7891283585', 'sita ram', 'computer science', 'sumitkumarbeniwal@gmail.com'),
('13ebkcs103', 'a', 'a', '9898969566', 'a', 'computer science', 'ddd@gmail.com'),
('14ebkec098', '123', 'a', '9521188480', 'a', 'electrical communication', 'rrchoudhary@gmail.com'),
('14ebkee088', '9', 'sk`', '7891283585', 'sita ram', 'electrical engg', 'sumitkumarbeniwal@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `teacher_login`
--

CREATE TABLE `teacher_login` (
  `name` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `contact_no` varchar(10) NOT NULL,
  `mail_id` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher_login`
--

INSERT INTO `teacher_login` (`name`, `password`, `contact_no`, `mail_id`) VALUES
('gopal sir', '9966', '7788994455', 'sumitkumarbeniwal@gmail.com'),
('sk choudhary 1', '9966', '7891283585', 'sss@b.com'),
('miss ruchi', '123', '9521188480', '1');

-- --------------------------------------------------------

--
-- Table structure for table `tquery`
--

CREATE TABLE `tquery` (
  `qid` int(20) NOT NULL,
  `tid` varchar(10) NOT NULL,
  `sid` varchar(12) NOT NULL,
  `scode` varchar(10) NOT NULL,
  `query` varchar(500) NOT NULL,
  `counter` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tquery`
--

INSERT INTO `tquery` (`qid`, `tid`, `sid`, `scode`, `query`, `counter`) VALUES
(1331231836, '9521188480', '13ebkcs098', '8cs2', '22222222222222222222', 2),
(1331231836, '9521188480', '13ebkcs098', '8cs2', 'query', 3),
(1331231836, '9521188480', '13ebkcs098', '8cs2', 'query', 4),
(1331231836, '9521188480', '13ebkcs098', '8cs2', 'query', 5),
(1654028575, '7891283585', '13ebkcs082', '8cs1', 'yaa sure ', 2);

-- --------------------------------------------------------

--
-- Table structure for table `t_rest`
--

CREATE TABLE `t_rest` (
  `id` varchar(30) NOT NULL,
  `code` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_rest`
--

INSERT INTO `t_rest` (`id`, `code`) VALUES
('7788994455', '8cs1'),
('7891283585', '8cs1'),
('9521188480', '4cs2a'),
('9521188480', '8cs1'),
('9521188480', '8cs2'),
('9521188480', '8cs4a'),
('9694838807', '8cs1'),
('9988774455', '8cs1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_login`
--
ALTER TABLE `admin_login`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `chat`
--
ALTER TABLE `chat`
  ADD PRIMARY KEY (`time`),
  ADD UNIQUE KEY `date` (`date`);

--
-- Indexes for table `code`
--
ALTER TABLE `code`
  ADD PRIMARY KEY (`code`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`sem`,`branch`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`eventid`);

--
-- Indexes for table `files`
--
ALTER TABLE `files`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `getlocation`
--
ALTER TABLE `getlocation`
  ADD PRIMARY KEY (`uname`);

--
-- Indexes for table `squery`
--
ALTER TABLE `squery`
  ADD PRIMARY KEY (`qid`,`counter`);

--
-- Indexes for table `stuent_login`
--
ALTER TABLE `stuent_login`
  ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `teacher_login`
--
ALTER TABLE `teacher_login`
  ADD PRIMARY KEY (`contact_no`);

--
-- Indexes for table `tquery`
--
ALTER TABLE `tquery`
  ADD PRIMARY KEY (`qid`,`counter`);

--
-- Indexes for table `t_rest`
--
ALTER TABLE `t_rest`
  ADD PRIMARY KEY (`id`,`code`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
  MODIFY `eventid` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
