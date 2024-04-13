CREATE TABLE `databaseProject1`.`home` (
  `HomeID` int NOT NULL AUTO_INCREMENT,
  `owner` varchar(225) DEFAULT NULL,
  `rate` varchar(45) DEFAULT NULL,
  `Floorspace` varchar(25) DEFAULT NULL,
  `Floors` int DEFAULT NULL,
  `Bathrooms` varchar(25) DEFAULT NULL,
  `Bedrooms` int DEFAULT NULL,
  `Landsize` varchar(45) DEFAULT NULL,
  `Yearconstructed` int DEFAULT NULL,
  `Hometype` varchar(50) DEFAULT NULL,
  `description` text,
  `Address_id` int not null,
  `AgentID` int not null,
  `availabilitystatus` varchar(45) DEFAULT 'Available',
  PRIMARY KEY (`HomeID`),
  UNIQUE KEY `HomeID_UNIQUE` (`HomeID`),
  KEY `AddressID_idx` (`Address_id`),
  CONSTRAINT `Address_id` FOREIGN KEY (`Address_id`) REFERENCES `address` (`Address_id`),
  CONSTRAINT `AgentID` FOREIGN KEY (`AgentID`) REFERENCES `Agent` (`AgentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `address` (
  `Address_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `zip` int NOT NULL,
  `county` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `State` varchar(45) DEFAULT 'MI',
  PRIMARY KEY (`Address_id`),
  KEY `address_idx` (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




CREATE TABLE `databaseProject1`.`hometype` (
  `HomeTypeID` int NOT NULL AUTO_INCREMENT,
  `Type` varchar(45) NOT NULL,
  PRIMARY KEY (`HomeTypeID`),
  UNIQUE KEY `HomeTypeID_UNIQUE` (`HomeTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `databaseProject1`.`homeowners` (
  `ownerId` int not null AUTO_INCREMENT,
  `SSN` varchar(16) NOT NULL,
  `OwnerName` varchar(225) NOT NULL,
  `NumOfDependents` int DEFAULT NULL,
  `Age` int DEFAULT NULL,
  `Income` decimal(5,4) DEFAULT NULL,
  `Profession` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ownerId`),
  UNIQUE KEY `SSN_UNIQUE` (`SSN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `databaseProject1`.`agent` (
  `AgentID` int NOT NULL AUTO_INCREMENT,
  `AgentFName` varchar(45) NOT NULL,
  `AgentLName` varchar(45) DEFAULT NULL,
  `contact` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) NOT NULL,
  `zip` int default 0,
  `city` varchar(45) DEFAULT NULL,
  `State` varchar(45) DEFAULT 'MI',
  PRIMARY KEY (`AgentID`),
  UNIQUE KEY `AgentID_UNIQUE` (`AgentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `homeowners` (
  `OwnerID` int NOT NULL auto_increment,
  `OwnerFName` varchar(45) NOT NULL,
  `OwnerLName` varchar(45) DEFAULT NULL,
  `dob` varchar(45) DEFAULT NULL,
  `SSN` varchar(45) DEFAULT NULL,
  `Profession` varchar(45) DEFAULT NULL,
  `contact` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `Address_id` int not null,
  PRIMARY KEY (`OwnerID`),
  UNIQUE KEY `SSN_UNIQUE` (`SSN`),
  KEY `AddressID_idx` (`Address_id`),
  CONSTRAINT `Address_id_1` FOREIGN KEY (`Address_id`) REFERENCES `address` (`Address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `listing` (
  `ListingID` int NOT NULL auto_increment,
  `Status` varchar(45) DEFAULT NULL,
  `CommissionRate` decimal(4,2) DEFAULT NULL,
  `ListingDate` datetime DEFAULT NULL,
  `HomeID` int NOT NULL,
  `AgentID` int NOT NULL,
  `OwnerID` int NOT NULL,
  `price` decimal(14,5) NOT NULL,
  PRIMARY KEY (`ListingID`),
  UNIQUE KEY `ListingID_UNIQUE` (`ListingID`),
  KEY `SSN_idx` (`OwnerID`),
  KEY `HID_idx` (`HomeID`),
  KEY `AgentID_idx` (`AgentID`),
  CONSTRAINT `AgentID_1` FOREIGN KEY (`AgentID`) REFERENCES `agent` (`AgentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `HID` FOREIGN KEY (`HomeID`) REFERENCES `home` (`HomeID`),
  CONSTRAINT `SSN` FOREIGN KEY (`OwnerID`) REFERENCES `homeowners` (`OwnerID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `appliances` (
  `ModelNumber` int NOT NULL auto_increment,
  `ApplianceName` varchar(255) NOT NULL,
  `MakeYear` year DEFAULT NULL,
  `Maker` varchar(45) DEFAULT NULL,
  `Price` decimal(14,5) DEFAULT NULL,
  `HomeID` int DEFAULT NULL,
  PRIMARY KEY (`ModelNumber`),
  KEY `HomeID_idx` (`HomeID`),
  CONSTRAINT `HomeID` FOREIGN KEY (`HomeID`) REFERENCES `home` (`HomeID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- Mock DATABASE
INSERT INTO `appliances`(ApplianceName, MakeYear, Maker, Price, HomeID) Values ( 'Water Filter', '2014', 'Absopure',100, 3);
INSERT INTO `appliances`(ApplianceName, MakeYear, Maker, Price, HomeID) Values ( 'Water Filter', '2014', 'Absopure',100, 4);
INSERT INTO `appliances`(ApplianceName, MakeYear, Maker, Price, HomeID) Values ( 'Water Filter', '2014', 'Absopure',100, 5);
INSERT INTO `appliances`(ApplianceName, MakeYear, Maker, Price, HomeID) Values ( 'Television', '2019', 'Samsung',2000, 2);
INSERT INTO `appliances`(ApplianceName, MakeYear, Maker, Price, HomeID) Values ( 'Television', '2019', 'Samsung',2000, 5);

INSERT INTO `listing` Values ( 111, 'Sold',4.5, '2023-03-23', 2,2,1,450000.999) ;
INSERT INTO `listing` Values ( 112, 'Sold',4.5, '2023-03-23', 2,2,1,480000.999) ;
INSERT INTO `listing` Values ( 113, 'Sold',4.5, '2023-03-23', 3,3,2,536100.999) ;
INSERT INTO `listing` Values ( 114, 'Available',3.5, '2023-03-23', 3,3,2,700000.999) ;
INSERT INTO `listing` Values ( 115, 'Available',4.5, '2023-03-23', 4,3,3,800000.999) ;
INSERT INTO `listing` Values ( 116, 'Available',5.5, '2023-03-23', 1,2,1,640000.999) ;
INSERT INTO `listing` Values ( 117, 'Available',5.5, '2023-03-23', 6,2,1,720000.00) ;