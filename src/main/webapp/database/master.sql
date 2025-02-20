create table COMPANY(
company_id int primary key AUTO_INCREMENT,
company_name varchar(30),
company_area varchar(30),
company_type varchar(20)
);

create table ROLE(
role_id int primary key AUTO_INCREMENT,
role_name varchar(30)
);

create table ACCOUNT(
account_id int primary key AUTO_INCREMENT,
name varchar(30),
mobile_number int(12),
email varchar(30),
password varchar(30),
company_id int(10),
role_id int(10),
UNIQUE(company_id),
FOREIGN KEY(company_id) REFERENCES COMPANY(company_id),
FOREIGN KEY(role_id) REFERENCES ROLE(role_id)
);

create table JOB(
job_id int primary key AUTO_INCREMENT,
job_name varchar(20),
job_type varchar(20),
date_of_posting date,
expire_date date,
job_description varchar(50),
company_id int(10),
FOREIGN KEY(company_id) REFERENCES COMPANY(company_id)
);

create table ACCOUNT_JOB_APPLY(
id int primary key AUTO_INCREMENT,
account_id int(6),
job_id int(6),
date_of_apply date,
FOREIGN KEY(account_id) REFERENCES ACCOUNT(account_id),
FOREIGN KEY(job_id) REFERENCES JOB(job_id)
);

