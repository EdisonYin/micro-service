-- MyISAM Foreign Keys显示不了外键，MyISAM此为5.0 以下版本使用 InnoDB 为5.0以上版本使用
drop table IF EXISTS city;
CREATE TABLE `city` (
`ID` int(11) NOT NULL AUTO_INCREMENT comment'',
`Name` char(35) NOT NULL DEFAULT '' comment'',
`CountryCode` char(3) NOT NULL DEFAULT '' comment'',
`District` char(20) NOT NULL DEFAULT '' comment'',
`Population` int(11) NOT NULL DEFAULT '0' comment'',
PRIMARY KEY (`ID`),
KEY `CountryCode` (`CountryCode`),
CONSTRAINT `city_ibfk_1` FOREIGN KEY (`CountryCode`) REFERENCES `country` (`Code`)
) ENGINE=MyISAM COMMENT='city' AUTO_INCREMENT=4080 DEFAULT CHARSET=utf8;

drop table IF EXISTS country;
CREATE TABLE `country` (
`Code` char(3) NOT NULL DEFAULT '' comment'',
`Name` char(52) NOT NULL DEFAULT '' comment'',
`Continent` enum('Asia','Europe','North America','Africa','Oceania','Antarctica','South America') NOT NULL DEFAULT 'Asia' comment'',
`Region` char(26) NOT NULL DEFAULT '' comment'',
`SurfaceArea` float(10,2) NOT NULL DEFAULT '0.00' comment'',
`IndepYear` smallint(6) DEFAULT NULL comment'',
`Population` int(11) NOT NULL DEFAULT '0' comment'',
`LifeExpectancy` float(3,1) DEFAULT NULL comment'',
`GNP` float(10,2) DEFAULT NULL comment'',
`GNPOld` float(10,2) DEFAULT NULL comment'',
`LocalName` char(45) NOT NULL DEFAULT '' comment'',
`GovernmentForm` char(45) NOT NULL DEFAULT '' comment'',
`HeadOfState` char(60) DEFAULT NULL comment'',
`Capital` int(11) DEFAULT NULL comment'',
`Code2` char(2) NOT NULL DEFAULT '' comment'',
PRIMARY KEY (`Code`)
) ENGINE=MyISAM COMMENT='country' DEFAULT CHARSET=utf8;