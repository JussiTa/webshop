CREATE  TABLE users (
  user_id int(11) NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(60) NOT NULL ,
  firstname VARCHAR(10)NOT NULL,
  lastname VARCHAR(10)NOT NULL,
  address VARCHAR (20) NOT NULL,
  zipcode int (5) NOT NULL,
  town VARCHAR (20)NOT NULL,  
  PRIMARY KEY (user_id));

CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  user_id int(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,user_id),
  KEY fk_username_idx (user_id),
  CONSTRAINT fk_username FOREIGN KEY (user_id) REFERENCES users (user_id));

INSERT INTO users(id, username,password,enabled)
VALUES ('admin','$2a$10$eYg/Ke78z6gv9JFwLbSb7u9lk3u20MsCh8NgkNN215LXHDUIluYWS', true);
INSERT INTO users(id,username,password,enabled)
VALUES ('alex','$2a$10$eYg/Ke78z6gv9JFwLbSb7u9lk3u20MsCh8NgkNN215LXHDUIluYWS', true);

INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('user', 'ROLE_USER');