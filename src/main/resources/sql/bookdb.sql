drop database if exists DBName;
create database DBName;
use DBName;

create table t_Book
(
 bookId int unsigned primary key not null auto_increment,
 name varchar(50) not null,
 author varchar(20) not null,
 isbn char(20) not null,
 edition varchar(10) not null,
 press varchar(20) not null,
 publicData datetime not null,
 catagory varchar(50) not null,
 price decimal not null,
 description varchar(500) not null,
 pic varchar(100) not null,
 sold int unsigned not null,
 sum int unsigned not null,
 uphelfDate datetime not null,
 downShelfDate datetime not null
 );

create table t_User
(
 userId int unsigned primary key not null auto_increment,  
 name varchar(20) not null,
 pass varchar(20) not null,
 type varchar(10) not null,
 address varchar(100) not null,
 postCode varchar(6) not null,
 phoneNumber varchar(11) not null,
 email varchar(30) not null
);

create table t_Order
(
 orderId int unsigned primary key not null auto_increment,
 totalPrice decimal not null,
 status varchar(20) not null,
 orderTime datetime not null,
 payWay varchar(20) not null,
 userId int unsigned not null ,
 bookId int unsigned not null ,
 orderNumber int unsigned not null,
 foreign key(userId) referencest_User(userId) on update cascade,
 foreign key(bookId) referencest_Book(bookId) on update cascade
);

create table t_UserOrder
(
 userId int unsigned not null ,
 bookId int unsigned not null,
 orderId int unsigned not null,
 primary key(userId,bookId,orderId)
);