-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 22, 2021 at 11:58 PM
-- Server version: 8.0.22-0ubuntu0.20.04.3
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `exam_portal`
--

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE `answers` (
  `id` bigint NOT NULL,
  `option_id` bigint NOT NULL,
  `queston_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `answers`
--

INSERT INTO `answers` (`id`, `option_id`, `queston_id`) VALUES
(1, 3, 1),
(2, 7, 2),
(3, 9, 3),
(4, 16, 4),
(5, 17, 5),
(6, 23, 6),
(7, 26, 7),
(8, 29, 8),
(9, 36, 9),
(10, 37, 10),
(11, 41, 11),
(12, 47, 12),
(13, 49, 13),
(14, 56, 14),
(15, 57, 15),
(16, 62, 16),
(17, 68, 17),
(18, 71, 18),
(19, 73, 19),
(20, 79, 20),
(21, 82, 21),
(22, 85, 22),
(23, 90, 23),
(24, 94, 24),
(25, 98, 25),
(26, 101, 26);

-- --------------------------------------------------------

--
-- Table structure for table `exams`
--

CREATE TABLE `exams` (
  `id` bigint NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `time` int NOT NULL,
  `instructions` varchar(150) DEFAULT NULL,
  `marks` int NOT NULL,
  `negative_marks` int DEFAULT NULL,
  `start_date` datetime NOT NULL,
  `title` varchar(50) NOT NULL,
  `organiser_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `exams`
--

INSERT INTO `exams` (`id`, `description`, `time`, `instructions`, `marks`, `negative_marks`, `start_date`, `title`, `organiser_id`) VALUES
(2, 'The Exam is a MCQ based Quiz Exam of Operating System', 60, 'There are a total of 10 questions asked in the exam. Each correct response fetches 4 marks and each incorrect response gets -1 negative marking.', 10, 5, '2021-01-22 23:45:00', 'Operating System', 1),
(3, 'The Exam is a MCQ based Quiz Exam of JAVA', 15, 'There are a total of 15 questions asked in the exam. Each correct response fetches 4 marks and each incorrect response gets -1 negative marking.', 12, 4, '2021-01-19 10:40:00', 'JAVA PROGRAMMING', 1),
(4, 'kshdfkj', 60, 'kkjsdhkfhkas', 4, 1, '2021-01-19 12:47:00', 'ashdfk', 2);

-- --------------------------------------------------------

--
-- Table structure for table `options`
--

CREATE TABLE `options` (
  `id` bigint NOT NULL,
  `statement` varchar(50) DEFAULT NULL,
  `queston_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `options`
--

INSERT INTO `options` (`id`, `statement`, `queston_id`) VALUES
(1, 'Windows', 1),
(2, 'Linux', 1),
(3, 'Oracle', 1),
(4, 'DOS', 1),
(5, '4', 2),
(6, '5', 2),
(7, '8', 2),
(8, '12', 2),
(9, '1950', 3),
(10, '1949', 3),
(11, '1950', 3),
(12, '1951', 3),
(13, '1994', 4),
(14, '1990', 4),
(15, '1992', 4),
(16, '1985', 4),
(17, '.txt', 5),
(18, '.xls', 5),
(19, '.ppt', 5),
(20, '.bmp', 5),
(21, 'prompt', 6),
(22, 'kernel', 6),
(23, 'shell', 6),
(24, 'command', 6),
(25, 'File attribute table', 7),
(26, 'File allocation table', 7),
(27, 'Font attribute table', 7),
(28, 'Format allocation table', 7),
(29, 'By operating system', 8),
(30, 'By compiler', 8),
(31, 'By interpreter', 8),
(32, 'By application software', 8),
(33, 'To turn off', 9),
(34, 'Install the program', 9),
(35, 'To scan', 9),
(36, 'Restarting computer', 9),
(37, 'To prevent deadlock', 10),
(38, 'To deadlock recovery', 10),
(39, 'To solve the deadlock', 10),
(40, 'None of these', 10),
(41, 'Bytecode is executed by JVM', 11),
(42, 'The applet makes the Java code secure and portable', 11),
(43, 'Use of exception handling', 11),
(44, 'Dynamic binding between objects', 11),
(45, 'Dynamic', 12),
(46, 'Architecture Neutral', 12),
(47, 'Use of pointers', 12),
(48, 'Object-oriented', 12),
(49, 'Unicode escape sequence', 13),
(50, 'Octal escape', 13),
(51, 'Hexadecimal', 13),
(52, 'Line feed', 13),
(53, 'JVM', 14),
(54, 'JRE', 14),
(55, 'JDK', 14),
(56, 'JDB', 14),
(57, 'char ch = \'\\utea\';', 15),
(58, 'char ca = \'tea\';', 15),
(59, 'char cr = \\u0223;', 15),
(60, 'char cc = \'\\itea\';', 15),
(61, 'Object', 16),
(62, 'int', 16),
(63, 'long', 16),
(64, 'void', 16),
(65, 'ABH8097', 17),
(66, 'L990023', 17),
(67, '904423', 17),
(68, '0xnf029L', 17),
(69, '0', 18),
(70, 'Not a Number', 18),
(71, 'Infinity', 18),
(72, 'Run time exception', 18),
(73, '24', 19),
(74, '20', 19),
(75, '25', 19),
(76, '23', 19),
(77, 'javap tool', 20),
(78, 'javaw command', 20),
(79, 'Javadoc tool', 20),
(80, 'javah command', 20),
(81, 'new List(false, 3)', 21),
(82, 'new List(3, true)', 21),
(83, 'new List(true, 3)', 21),
(84, 'new List(3, false)', 21),
(85, 'for ( int i = 99; i >= 0; i / 9 )', 22),
(86, 'for ( int i = 7; i <= 77; i += 7 )', 22),
(87, 'for ( int i = 20; i >= 2; - -i )', 22),
(88, 'for ( int i = 2; i <= 20; i = 2* i )', 22),
(89, 'getClass()', 23),
(90, 'getName()', 23),
(91, 'intern()', 23),
(92, 'toString()', 23),
(93, 'Serialization', 24),
(94, 'Variable Shadowing', 24),
(95, 'Abstraction', 24),
(96, 'Multi-threading', 24),
(97, 'Runnable Interface', 25),
(98, 'Marker Interface', 25),
(99, 'Abstract Interface', 25),
(100, 'CharSequence Interface', 25),
(101, 'asdfasdfasdf', 26),
(102, 'sadf', 26),
(103, 'asfd', 26),
(104, 'asdfasdfasdf', 26);

-- --------------------------------------------------------

--
-- Table structure for table `organisers`
--

CREATE TABLE `organisers` (
  `id` bigint NOT NULL,
  `email` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `organisers`
--

INSERT INTO `organisers` (`id`, `email`, `name`, `password`) VALUES
(1, 'pooja@gmail.com', 'pooja ', '$2a$10$KMCwQ81wk58TcHG.QrNJw.A4gtaBJU56gqo7c/YDFhtZ6fa3Pzfl6'),
(2, 'rahul@gmail.com', 'rahul', '$2a$10$pEM9RHO/.Qjpzt.gtzvWgOAhiXyCYpSiGo8tjox2UhARRsYk9grU.');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `id` bigint NOT NULL,
  `statement` varchar(300) NOT NULL,
  `exam_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `statement`, `exam_id`) VALUES
(1, 'Which of the following is not an operating system?', 2),
(2, 'What is the maximum length of the filename in DOS?', 2),
(3, ' When was the first operating system developed?', 2),
(4, 'When were MS windows operating systems proposed?', 2),
(5, 'Which of the following is the extension of Notepad?', 2),
(6, 'What else is a command interpreter called?', 2),
(7, 'What is the full name of FAT?', 2),
(8, ' BIOS is used?', 2),
(9, 'What is the mean of the Booting in the operating system?', 2),
(10, 'Banker\'s algorithm is used?', 2),
(11, 'Which of the following option leads to the portability and security of Java?', 3),
(12, 'Which of the following is not a Java features?', 3),
(13, 'The \\u0021 article referred to as a', 3),
(14, '_____ is used to find and fix bugs in the Java programs.', 3),
(15, 'Which of the following is a valid declaration of a char?', 3),
(16, 'What is the return type of the hashCode() method in the Object class?', 3),
(17, 'Which of the following is a valid long literal?', 3),
(18, 'What does the expression float a = 35 / 0 return?', 3),
(19, 'Evaluate the following Java expression, if x=3, y=5, and z=10:\r\n++z + y - y + z + x++', 3),
(20, 'Which of the following tool is used to generate API documentation in HTML format from doc comments in source code?', 3),
(21, 'Which of the following creates a List of 3 visible items and multiple selections abled?', 3),
(22, 'Which of the following for loop declaration is not valid?', 3),
(23, 'Which method of the Class.class is used to determine the name of a class represented by the class object as a String?', 3),
(24, 'In which process, a local variable has the same name as one of the instance variables?', 3),
(25, 'An interface with no fields or methods is known as a ______.', 3),
(26, 'sdfasd', 4);

-- --------------------------------------------------------

--
-- Table structure for table `SPRING_SESSION`
--

CREATE TABLE `SPRING_SESSION` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint NOT NULL,
  `LAST_ACCESS_TIME` bigint NOT NULL,
  `MAX_INACTIVE_INTERVAL` int NOT NULL,
  `EXPIRY_TIME` bigint NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `SPRING_SESSION`
--

INSERT INTO `SPRING_SESSION` (`PRIMARY_ID`, `SESSION_ID`, `CREATION_TIME`, `LAST_ACCESS_TIME`, `MAX_INACTIVE_INTERVAL`, `EXPIRY_TIME`, `PRINCIPAL_NAME`) VALUES
('28ff83bc-b32e-4d85-bd61-0ab2cba7317e', '9182502e-cba5-4bca-a8fb-2ad0ed8cc15c', 1611339581237, 1611339589637, 1800, 1611341389637, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `SPRING_SESSION_ATTRIBUTES`
--

CREATE TABLE `SPRING_SESSION_ATTRIBUTES` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `SPRING_SESSION_ATTRIBUTES`
--

INSERT INTO `SPRING_SESSION_ATTRIBUTES` (`SESSION_PRIMARY_ID`, `ATTRIBUTE_NAME`, `ATTRIBUTE_BYTES`) VALUES
('28ff83bc-b32e-4d85-bd61-0ab2cba7317e', 'org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN', 0xaced0005737200366f72672e737072696e676672616d65776f726b2e73656375726974792e7765622e637372662e44656661756c7443737266546f6b656e5aefb7c82fa2fbd50200034c000a6865616465724e616d657400124c6a6176612f6c616e672f537472696e673b4c000d706172616d657465724e616d6571007e00014c0005746f6b656e71007e0001787074000c582d435352462d544f4b454e7400055f6373726674002436336637336233342d316565632d343632352d393533652d393864626638393039653030);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint NOT NULL,
  `email` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `name`) VALUES
(1, 'laxmi@gmail.com', 'Laxmi'),
(2, 'suman95@gmail.com', 'Suman Sharma'),
(3, 'sumitjha12@gmail.com', 'Sumit Jha'),
(4, 'kavsin22@gmail.com', 'Kavya Sinha'),
(5, 'pooja@gmail.com', 'Pooja Meena'),
(6, 'kapil43@gmail.com', 'Kapil'),
(7, 'meghna0102@gmail.com', 'Meghna'),
(8, 'Surendra23199@gmail.com', 'Surendra Tomar'),
(9, 'kishangopal@gmail.com', 'Kishan Gopal'),
(10, 'dr1999@gmail.com', 'Devendra Rane'),
(11, 'tripathi3333@gmail.com', 'Somya Tripathi'),
(12, 'safal5499@gmail.com', 'Safalta'),
(13, 'kumaripriya@gmail.com', 'Priya Kumari'),
(14, 'solanki34@gmail.com', 'Pushpa solanki'),
(15, 'jitendra2000@gmail.com', 'Jitendra Singh'),
(16, 'karrtikshah1996@gmail.com', 'Kartikey'),
(17, 'rahul@gmail.com', 'rahul');

-- --------------------------------------------------------

--
-- Table structure for table `user_answer`
--

CREATE TABLE `user_answer` (
  `id` bigint NOT NULL,
  `answer_status` bit(1) NOT NULL,
  `option_id` bigint NOT NULL,
  `queston_id` bigint NOT NULL,
  `user_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_answer`
--

INSERT INTO `user_answer` (`id`, `answer_status`, `option_id`, `queston_id`, `user_id`) VALUES
(1, b'1', 3, 1, 3),
(2, b'1', 7, 2, 3),
(3, b'1', 3, 1, 2),
(4, b'1', 7, 2, 2),
(5, b'0', 11, 3, 2),
(6, b'1', 16, 4, 2),
(7, b'1', 17, 5, 2),
(8, b'1', 23, 6, 2),
(9, b'1', 26, 7, 2),
(10, b'1', 29, 8, 2),
(11, b'1', 36, 9, 2),
(12, b'0', 39, 10, 2),
(13, b'1', 26, 7, 3),
(14, b'1', 29, 8, 3),
(15, b'1', 36, 9, 3),
(16, b'1', 37, 10, 3),
(17, b'1', 23, 6, 3),
(18, b'1', 3, 1, 4),
(19, b'1', 7, 2, 4),
(20, b'0', 11, 3, 4),
(21, b'1', 16, 4, 4),
(22, b'1', 17, 5, 4),
(23, b'1', 37, 10, 4),
(24, b'1', 23, 6, 4),
(25, b'1', 3, 1, 5),
(26, b'1', 29, 8, 5),
(27, b'1', 37, 10, 5),
(28, b'1', 36, 9, 5),
(29, b'1', 17, 5, 5),
(30, b'1', 16, 4, 5),
(31, b'1', 3, 1, 6),
(32, b'1', 26, 7, 6),
(33, b'1', 17, 5, 6),
(34, b'1', 23, 6, 6),
(35, b'1', 3, 1, 7),
(36, b'1', 37, 10, 7),
(37, b'0', 12, 3, 7),
(38, b'0', 15, 4, 7),
(39, b'0', 18, 5, 7),
(40, b'1', 23, 6, 7),
(41, b'1', 26, 7, 7),
(42, b'0', 32, 8, 7),
(43, b'0', 34, 9, 7),
(44, b'1', 41, 11, 17),
(45, b'0', 66, 17, 17),
(46, b'0', 63, 16, 17),
(47, b'0', 1, 1, 9),
(48, b'1', 7, 2, 9),
(49, b'0', 12, 3, 9),
(50, b'1', 16, 4, 9),
(51, b'0', 2, 1, 10),
(52, b'1', 7, 2, 10),
(53, b'0', 39, 10, 10),
(54, b'0', 31, 8, 10),
(55, b'1', 36, 9, 10),
(56, b'0', 11, 3, 10),
(57, b'1', 16, 4, 10),
(58, b'0', 18, 5, 10),
(59, b'1', 23, 6, 10),
(60, b'1', 26, 7, 10);

-- --------------------------------------------------------

--
-- Table structure for table `user_exams`
--

CREATE TABLE `user_exams` (
  `id` bigint NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `exam_id` bigint NOT NULL,
  `user_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_exams`
--

INSERT INTO `user_exams` (`id`, `password`, `status`, `exam_id`, `user_id`) VALUES
(2, 'UF9nL0by', 2, 2, 2),
(3, '1MsQl0yL', 2, 2, 3),
(4, 'Z0J9r8im', 2, 2, 4),
(5, 'ALOBsdM2', 2, 2, 5),
(6, '6erAC22a', 2, 2, 6),
(7, 'bIoHN2WM', 2, 2, 7),
(8, '7b71CtLE', 0, 2, 8),
(9, '1eb5nuIR', 2, 2, 9),
(10, '2SGIPxFy', 2, 2, 10),
(11, 'dKjPM10A', 0, 2, 11),
(12, 't1VY9goz', 0, 2, 12),
(13, 'rJzT44Ma', 0, 2, 13),
(14, 'qizIKh7M', 0, 2, 14),
(15, '4Y6pzCu3', 1, 2, 15),
(17, 'sHuLtZOe', 2, 3, 1),
(18, 'v4ZLgFgE', 0, 3, 2),
(19, 'dKOVTsMm', 0, 3, 3),
(20, 'xNf9hXRo', 0, 3, 4),
(21, 'k9jyOaM6', 0, 3, 5),
(22, 'KI9rdWAX', 0, 3, 6),
(23, 'IYGB5eud', 0, 3, 7),
(24, 'jGRsOA2q', 0, 3, 8),
(25, 'eNXVQtEm', 0, 3, 9),
(26, '6fEBXJAt', 0, 3, 10),
(27, 'DXVKM7go', 0, 3, 11),
(28, 'WTqsMHEH', 0, 3, 12),
(29, 'iL7tqy05', 0, 3, 13),
(30, 'd7I5e7iR', 0, 3, 14),
(31, 'HYvx1k9Z', 0, 3, 15),
(32, 'QfCrcf0I', 0, 4, 17);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answers`
--
ALTER TABLE `answers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn0s3utny5xipstu14dx3mdmfw` (`option_id`),
  ADD KEY `FK49r3ib5y6wylrhgrrtvxfqiwn` (`queston_id`);

--
-- Indexes for table `exams`
--
ALTER TABLE `exams`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9ko3u2cywgujypg93a1txv8u4` (`organiser_id`);

--
-- Indexes for table `options`
--
ALTER TABLE `options`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK807trbgpd0fg6d8e5bwutb4c2` (`queston_id`);

--
-- Indexes for table `organisers`
--
ALTER TABLE `organisers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_symcnmf93rjrsbibwb4sm7wx5` (`email`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrk78bmt53fns7np8casqa3q44` (`exam_id`);

--
-- Indexes for table `SPRING_SESSION`
--
ALTER TABLE `SPRING_SESSION`
  ADD PRIMARY KEY (`PRIMARY_ID`),
  ADD UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  ADD KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  ADD KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`);

--
-- Indexes for table `SPRING_SESSION_ATTRIBUTES`
--
ALTER TABLE `SPRING_SESSION_ATTRIBUTES`
  ADD PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Indexes for table `user_answer`
--
ALTER TABLE `user_answer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6fc8ftk518v5685pios87gu7j` (`option_id`),
  ADD KEY `FKk4x44t5s3l8cxyfkrfth0qpfr` (`queston_id`),
  ADD KEY `FKdi5eulyfji1loprpepwmi57d8` (`user_id`);

--
-- Indexes for table `user_exams`
--
ALTER TABLE `user_exams`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK62hmmu60k8epyuhudlvyegvxm` (`exam_id`),
  ADD KEY `FKgq557c1dw3rp5oim4i0aotl51` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answers`
--
ALTER TABLE `answers`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `exams`
--
ALTER TABLE `exams`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `options`
--
ALTER TABLE `options`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;

--
-- AUTO_INCREMENT for table `organisers`
--
ALTER TABLE `organisers`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `user_answer`
--
ALTER TABLE `user_answer`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT for table `user_exams`
--
ALTER TABLE `user_exams`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answers`
--
ALTER TABLE `answers`
  ADD CONSTRAINT `FK49r3ib5y6wylrhgrrtvxfqiwn` FOREIGN KEY (`queston_id`) REFERENCES `questions` (`id`),
  ADD CONSTRAINT `FKn0s3utny5xipstu14dx3mdmfw` FOREIGN KEY (`option_id`) REFERENCES `options` (`id`);

--
-- Constraints for table `exams`
--
ALTER TABLE `exams`
  ADD CONSTRAINT `FK9ko3u2cywgujypg93a1txv8u4` FOREIGN KEY (`organiser_id`) REFERENCES `organisers` (`id`);

--
-- Constraints for table `options`
--
ALTER TABLE `options`
  ADD CONSTRAINT `FK807trbgpd0fg6d8e5bwutb4c2` FOREIGN KEY (`queston_id`) REFERENCES `questions` (`id`);

--
-- Constraints for table `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `FKrk78bmt53fns7np8casqa3q44` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`id`);

--
-- Constraints for table `SPRING_SESSION_ATTRIBUTES`
--
ALTER TABLE `SPRING_SESSION_ATTRIBUTES`
  ADD CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `SPRING_SESSION` (`PRIMARY_ID`) ON DELETE CASCADE;

--
-- Constraints for table `user_answer`
--
ALTER TABLE `user_answer`
  ADD CONSTRAINT `FK6fc8ftk518v5685pios87gu7j` FOREIGN KEY (`option_id`) REFERENCES `options` (`id`),
  ADD CONSTRAINT `FKdi5eulyfji1loprpepwmi57d8` FOREIGN KEY (`user_id`) REFERENCES `user_exams` (`id`),
  ADD CONSTRAINT `FKk4x44t5s3l8cxyfkrfth0qpfr` FOREIGN KEY (`queston_id`) REFERENCES `questions` (`id`);

--
-- Constraints for table `user_exams`
--
ALTER TABLE `user_exams`
  ADD CONSTRAINT `FK62hmmu60k8epyuhudlvyegvxm` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`id`),
  ADD CONSTRAINT `FKgq557c1dw3rp5oim4i0aotl51` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
