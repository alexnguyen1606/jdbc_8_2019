
USE estatejdbc8_2019;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `assignmentstaff` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `buildingid` bigint(20) NOT NULL,
  `staffid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_assignment_user` (`staffid`),
  KEY `fk_assignment_building` (`buildingid`),
  CONSTRAINT `fk_assignment_building` FOREIGN KEY (`buildingid`) REFERENCES `building` (`id`),
  CONSTRAINT `fk_assignment_user` FOREIGN KEY (`staffid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `building` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `numberofbasement` int(11) DEFAULT NULL,
  `buildingarea` int(11) DEFAULT NULL,
  `district` varchar(100) DEFAULT NULL,
  `ward` varchar(100) DEFAULT NULL,
  `street` varchar(100) DEFAULT NULL,
  `structure` varchar(100) DEFAULT NULL,
  `costrent` int(11) DEFAULT NULL,
  `costdescription` text,
  `servicecost` varchar(255) DEFAULT NULL,
  `carcost` varchar(255) DEFAULT NULL,
  `motorbikecost` varchar(255) DEFAULT NULL,
  `overtimecost` varchar(255) DEFAULT NULL,
  `electricitycost` varchar(255) DEFAULT NULL,
  `deposit` varchar(255) DEFAULT NULL,
  `payment` varchar(255) DEFAULT NULL,
  `timerent` varchar(255) DEFAULT NULL,
  `timedecorator` varchar(255) DEFAULT NULL,
  `managername` varchar(255) DEFAULT NULL,
  `managerphone` varchar(255) DEFAULT NULL,
  `type` text,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `companyname` varchar(255) DEFAULT NULL,
  `request` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `customerservice` (
  `id` bigint(20) NOT NULL,
  `customerid` bigint(20) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  `note` text,
  `createddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cs_customer_idx` (`customerid`),
  KEY `cs_user_idx` (`userid`),
  CONSTRAINT `cs_customer` FOREIGN KEY (`customerid`) REFERENCES `customer` (`id`),
  CONSTRAINT `cs_user` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `rentarea` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `value` int(11) NOT NULL,
  `buildingid` bigint(20) NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_rentarea_building` (`buildingid`),
  CONSTRAINT `fk_rentarea_building` FOREIGN KEY (`buildingid`) REFERENCES `building` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) NOT NULL,
  `roleid` bigint(20) NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_userrole_user` (`userid`),
  KEY `fk_userrole_role` (`roleid`),
  CONSTRAINT `fk_userrole_role` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`),
  CONSTRAINT `fk_userrole_user` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/