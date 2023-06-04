drop table guest;

CREATE TABLE guest (
  aid INT NOT NULL Primary Key AUTO_INCREMENT,
  username VARCHAR,
  email VARCHAR,
  date TIMESTAMP,
  title VARCHAR,
  content VARCHAR,
  password VARCHAR
); 