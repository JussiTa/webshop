CREATE  TABLE users (
  user_id int(11) NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(60) NOT NULL ,
  firstname VARCHAR(10)NOT NULL,
  lastname VARCHAR(10)NOT NULL,
  address VARCHAR (20) NOT NULL,
  zipcode int (5) NOT NULL,
  town VARCHAR (20)NOT NULL,  
  email VARCHAR(60) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (user_id));

CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  user_id int(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (ROLE,user_id),
  KEY fk_userid_idx (user_id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id));
  
  CREATE  TABLE products (
  product_id int(11) NOT NULL AUTO_INCREMENT,
  productname VARCHAR(45) NOT NULL ,
  gategory VARCHAR(60) NOT NULL ,
  price DOUBLE (5,2)NOT NULL,  
  PRIMARY KEY (product_id));
  
  
  create table orders (
   order_id INT NOT NULL auto_increment,
   order_name VARCHAR(30) not null,
   username VARCHAR (30)not null,
   order_date timestamp,
   total double(5,2) not null,
   PRIMARY KEY (order_id));


 create table order_items (
   item_id INT NOT NULL auto_increment,
   item_name VARCHAR(30) default NULL,
   order_id INT default NULL,
   itemprice double,
   price double,
   pcs INT,
   PRIMARY KEY (item_id)
);  


create table billing (
   billing_id INT NOT NULL auto_increment,
   billing_name VARCHAR(30) not null,
   order_id VARCHAR (30)not null,
   expired_date timestamp,
   total double(5,2) not null,
   PRIMARY KEY (order_id));
   
create table billing_items (
   item_id INT NOT NULL auto_increment,
   item_name VARCHAR(30) default NULL,
   billing_id INT default NULL,
   itemprice double,
   price double,
   pcs INT,
   PRIMARY KEY (item_id)
);  
   
   
   
   
   
   
   
   








  

