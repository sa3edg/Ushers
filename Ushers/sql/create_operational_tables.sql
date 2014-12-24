DROP TABLE IF EXISTS users;
CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(150) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));

DROP TABLE IF EXISTS user_roles;
CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));
 
--admin & bench@admin_
insert into users values ('admin','$2a$10$E5Rd6sUAw2H.qHau1inSROmdjYbQyJsvMA.vVsNB0WQrlkT9QnPlu', '1');

insert into user_roles(username, role) values ('admin', 'ROLE_ADMIN');

--add governorates table
DROP TABLE IF EXISTS governorates;
CREATE TABLE  governorates (
  id int(20) NOT NULL AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
--end of add governorates table

--add areas table
DROP TABLE IF EXISTS areas;
CREATE TABLE  areas (
  id int(20) NOT NULL AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
--end of add areas table

--add preferred_locations table
DROP TABLE IF EXISTS preferred_locations;
CREATE TABLE  preferred_locations (
  id int(20) NOT NULL AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
--end of add preferred_locations table


DROP TABLE IF EXISTS users;
CREATE TABLE users (
                   uid  BIGINT NOT NULL ,
                   user_name    text NOT NULL,
                   name    text default NULL,
                   first_name   text default NULL,
                   middle_name   text default NULL,
                   last_name   text default NULL,
                   link   text default NULL,
                   birthday   varchar(200) default NULL,
                   gender     varchar(50) default NULL,
                   location_id int(30) default 0,
                   status     varchar(50) default NULL,
                   PRIMARY KEY  (uid)
                  ) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS ushers;
CREATE TABLE ushers (
                   usher_code varchar(50) NOT NULL,
                   usher_type varchar(50) NOT NULL ,
                   usher_caliber varchar(50) NOT NULL ,
                   first_name varchar(100) NOT NULL ,
                   middle_name varchar(100) default NULL ,
                   last_name varchar(100) default NULL ,
                   marital_status varchar(100) default NULL ,
                   has_kids TINYINT default 0 ,
                   kids_number int(2) default 0 ,
                   gender varchar(50) default NULL ,
                   birth_date DATE default NULL ,
                   age int(2) default 0 ,
                   address varchar(100) default NULL ,
                   appartment_number varchar(100) default NULL ,
                   street varchar(100) default NULL ,
                   area varchar(100) default NULL ,
                   governorate varchar(100) default NULL ,
                   preferred_location varchar(100) default NULL ,
                   preferred_shift varchar(100) default NULL ,
                   mobile_number varchar(50) default NULL ,
                   landline_number varchar(50) default NULL ,
                   height varchar(50) default NULL ,
                   weight varchar(50) default NULL ,
                   shirt_size varchar(50) default NULL ,
                   pants_ize varchar(50) default NULL ,
                   hair_type varchar(50) default NULL ,
                   languages varchar(200) default NULL ,
                   referred_by varchar(50) default NULL ,
                   university varchar(50) default NULL ,
                   university_degree varchar(50) default NULL ,
                   graduation_year varchar(4) default NULL ,
                   school varchar(50) default NULL ,
                   facebook_account varchar(100) default NULL ,
                   email_address varchar(50) default NULL ,
                   social_insurance TINYINT default 0 ,
                   social_insurance_no varchar(50) default NULL ,
                   social_insurance_date DATE default NULL ,
                   social_insurance_form_6 TINYINT default 0 ,
                   social_insurance_exit_date DATE default NULL ,
                   national_id_no varchar(50) default NULL ,
                   additional_information text default NULL,
                   photo1 BLOB ,
                   photo2 BLOB ,
                   photo3 BLOB ,
                   photo4 BLOB ,
                   PRIMARY KEY  (usher_code)
                 ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
                 
DROP TABLE IF EXISTS projects;
CREATE TABLE projects (
                   project_id varchar(50) NOT NULLAUTO_INCREMENT,
                   client varchar(50) NOT NULL ,
                   product varchar(50) default NULL ,
                   project_type varchar(50) default NULL ,
                   project_name varchar(1) default NULL ,
                   project_code varchar(50) default NULL ,
                   project_start_date DATETIME default NULL ,
                   project_end_date DATETIME default NULL ,
                   year int(4) default 2014 ,
                   PRIMARY KEY  (project_id)
                 ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
;;