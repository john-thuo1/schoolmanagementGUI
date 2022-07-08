-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2022 at 05:18 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `registration`
--

-- --------------------------------------------------------

--
-- Table structure for table `student_credentials`
--

CREATE TABLE `student_credentials` (
  `StudentId` int(11) NOT NULL,
  `FullName` varchar(255) NOT NULL,
  `Email` varchar(300) NOT NULL,
  `StudentPassword` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_credentials`
--

INSERT INTO `student_credentials` (`StudentId`, `FullName`, `Email`, `StudentPassword`) VALUES
(2, 'johnthuo', 'john@gmail.com', '1289'),
(3, 'Moses Wamae', 'mw@email.com', '1992'),
(6, 'joy wanjiru', 'joy@gmail.com', '2219'),
(7, 'lobna jbeniani', 'lobna@gmail.com', '2220'),
(8, 'natley james', 'natley@gmail.com', '2221');

-- --------------------------------------------------------

--
-- Table structure for table `student_info`
--

CREATE TABLE `student_info` (
  `RollNo` int(11) NOT NULL,
  `FullName` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Grade` varchar(255) NOT NULL,
  `CourseAssigned` varchar(255) DEFAULT NULL,
  `Year` varchar(255) NOT NULL,
  `RegistrationNumber` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_info`
--

INSERT INTO `student_info` (`RollNo`, `FullName`, `Email`, `Grade`, `CourseAssigned`, `Year`, `RegistrationNumber`) VALUES
(3, 'joy wanjiru', 'joy@gmail.com', '19', 'Computer Science', '2022', '32022'),
(4, 'lobna jbeniani', 'lobna@gmail.com', '15', 'Global Challenges', '2022', '42022'),
(5, 'natley james', 'natley@gmail.com', '11', 'Denied', '2022', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `student_credentials`
--
ALTER TABLE `student_credentials`
  ADD PRIMARY KEY (`StudentId`);

--
-- Indexes for table `student_info`
--
ALTER TABLE `student_info`
  ADD PRIMARY KEY (`RollNo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `student_credentials`
--
ALTER TABLE `student_credentials`
  MODIFY `StudentId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `student_info`
--
ALTER TABLE `student_info`
  MODIFY `RollNo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
