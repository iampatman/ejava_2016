drop schema if exists authdb;

create schema authdb;

use authdb;

create table users (
	userid varchar(32) not null,
	password varchar(128) not null,
	primary key (userid)
) engine=InnoDB default charset=utf8;

create table groups (
	groupid varchar(32) not null,
	userid varchar(32) not null,
	primary key (groupid, userid)
) engine=InnoDB default charset=utf8;

CREATE TABLE authdb.notes
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50),
    datetime TIMESTAMP,
    user VARCHAR(32),
    category VARCHAR(10),
    content VARCHAR(200),
    CONSTRAINT notes_users_userid_fk FOREIGN KEY (user) REFERENCES users (userid)
);


/*
	Create jdbcRealm
	Add jdbc connection pool and jdbc resource
	Assume jdbc resource is jdbc/authdb

	# realm name referenced in web.xml
	name: authdb-realm 
	# hard coded to jdbcRealm
	JAAS context: jdbcRealm (must be this)
	JNDI: jdbc/authdb
	User Table: users
	User Name Column: userid
	Password Column: password
	Group Table: groups
	Group Table user Name Column: userid
	Group Name Column: groupid
	# Cannot find the use of this in the source code.
	# Need to have a value, enter NONE
	Password Encryption Algorithm: AES
	# digest is use to hash password, user the same digest before updating password
	Digest Algorithm: SHA-256 
	Encoding: Hex
*/
