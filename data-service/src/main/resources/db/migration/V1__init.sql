--
CREATE TABLE CITY (
  id bigint auto_increment,
  country VARCHAR(30),
  name VARCHAR(30),
  state VARCHAR(30),
  map VARCHAR(30)
);
--
CREATE TABLE HOTEL (
	id bigint auto_increment,
	city_id bigint,
  name VARCHAR(50),
	address VARCHAR(50),
	zip VARCHAR(30)
);
--
CREATE TABLE REVIEW(
  id bigint auto_increment,
  hotel_id bigint,
  idx INTEGER,
  check_in_date DATE,
  rating INTEGER,
  trip_type INTEGER,
  title VARCHAR(50),
  details VARCHAR(200)
);