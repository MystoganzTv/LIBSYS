# LIBSYS
A webbased library CRM

MYSQL CREATE STATEMENTS

CREATE DATABASE `libsys` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `admins` (
  `admin_id` varchar(100) NOT NULL,
  PRIMARY KEY (`admin_id`),
  CONSTRAINT `FK896rcns3hi5nil8sr2w9k0prj` FOREIGN KEY (`admin_id`) REFERENCES `persons` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `author` (
  `author_id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `authorities` (
  `authority_id` int(11) NOT NULL,
  `authority` varchar(50) NOT NULL,
  PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `purchase_price` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `placement_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKroi7sfrjbr6jpj6sd9dgte298` (`placement_id`),
  CONSTRAINT `FKroi7sfrjbr6jpj6sd9dgte298` FOREIGN KEY (`placement_id`) REFERENCES `placements` (`placement_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `book_author` (
  `idbook_author_book_id` int(11) NOT NULL,
  `idbook_author_author_id` int(11) NOT NULL,
  KEY `FK40x4sr78094gad7lmdb3nk9lx` (`idbook_author_author_id`),
  KEY `FKbgcrllbtw4n0y162b3n1c8aku` (`idbook_author_book_id`),
  CONSTRAINT `FK40x4sr78094gad7lmdb3nk9lx` FOREIGN KEY (`idbook_author_author_id`) REFERENCES `author` (`author_id`),
  CONSTRAINT `FKbgcrllbtw4n0y162b3n1c8aku` FOREIGN KEY (`idbook_author_book_id`) REFERENCES `books` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bosses` (
  `boss_id` varchar(100) NOT NULL,
  PRIMARY KEY (`boss_id`),
  CONSTRAINT `FKekeu2uodmcg4u5sa3msppmf5g` FOREIGN KEY (`boss_id`) REFERENCES `persons` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ebook_author` (
  `idebook_author_ebook_id` int(11) NOT NULL,
  `idebook_author_author_id` int(11) NOT NULL,
  KEY `FKh2wsh8j74bjc0ogfpmvqsceu5` (`idebook_author_author_id`),
  KEY `FKn7qgorn7n6cmtw3rwfjksa2q4` (`idebook_author_ebook_id`),
  CONSTRAINT `FKh2wsh8j74bjc0ogfpmvqsceu5` FOREIGN KEY (`idebook_author_author_id`) REFERENCES `author` (`author_id`),
  CONSTRAINT `FKn7qgorn7n6cmtw3rwfjksa2q4` FOREIGN KEY (`idebook_author_ebook_id`) REFERENCES `e_books` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `e_books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `purchase_price` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `download_link` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `categories` (
  `category_id` bigint(20) NOT NULL,
  `categorytype` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `librarians` (
  `librarian_id` varchar(100) NOT NULL,
  PRIMARY KEY (`librarian_id`),
  CONSTRAINT `FKlntx8txqci73x57tow852w6wd` FOREIGN KEY (`librarian_id`) REFERENCES `persons` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `persons` (
  `email` varchar(150) NOT NULL,
  `personal_number` varchar(12) DEFAULT NULL,
  `first_name` varchar(150) DEFAULT NULL,
  `last_name` varchar(150) DEFAULT NULL,
  `street` varchar(150) DEFAULT NULL,
  `postal_code` varchar(45) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `placements` (
  `placement_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ddk` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`placement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `removed_books` (
  `book_id` int(11) NOT NULL,
  `cause` varchar(255) DEFAULT NULL,
  `deleted_at` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `purchase_price` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `seminaries` (
  `seminary_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `endtime` varchar(255) DEFAULT NULL,
  `information` varchar(255) DEFAULT NULL,
  `occurrence` varchar(255) DEFAULT NULL,
  `starttime` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`seminary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `removed_seminaries` (
  `seminary_id` bigint(20) NOT NULL,
  `cause` varchar(255) DEFAULT NULL,
  `deleted_at` varchar(255) DEFAULT NULL,
  `endtime` varchar(255) DEFAULT NULL,
  `information` varchar(255) DEFAULT NULL,
  `occurrence` date DEFAULT NULL,
  `starttime` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`seminary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `removed_e_books` (
  `e_book_id` int(11) NOT NULL,
  `cause` varchar(255) DEFAULT NULL,
  `deleted_at` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `download_link` varchar(255) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `purchase_price` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`e_book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `removed_books` (
  `book_id` int(11) NOT NULL,
  `cause` varchar(255) DEFAULT NULL,
  `deleted_at` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `purchase_price` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `publishers` (
  `publisher_id` bigint(20) NOT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`publisher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `placements` (
  `placement_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ddk` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`placement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(65) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `user_authority` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  KEY `fk_user_authority_idx` (`user_authority`),
  CONSTRAINT `fk_user_authority` FOREIGN KEY (`user_authority`) REFERENCES `authorities` (`authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `visitors` (
  `librarycard` int(11) NOT NULL AUTO_INCREMENT,
  `visitor_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`librarycard`),
  KEY `FK5iqxn8ua7ot6m8hjsfkl1j2k6` (`visitor_id`),
  CONSTRAINT `FK5iqxn8ua7ot6m8hjsfkl1j2k6` FOREIGN KEY (`visitor_id`) REFERENCES `persons` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
