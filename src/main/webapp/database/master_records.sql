insert into company(company_name, company_area, company_type)values('Infosys', 'hyderabad', 'it');
insert into company(company_name, company_area, company_type)values('HCL', 'hyderabad', 'it');

insert into role(role_name)values('admin');
insert into role(role_name)values('company');
insert into role(role_name)values('user');

SET @infosys_id = (select company_id from company where company_name='Infosys');
SET @role_id = (select role_id from role where role_name='admin');

insert into account(name, mobile_number, email, password, company_id, role_id)
values('John', 8899888877, 'john@gmail.com', 'john@123', @infosys_id, @role_id);

insert into job(job_name, job_type, date_of_posting, expire_date, job_description, company_id)values('Java Developer', 'IT', '2025-01-26', '2025-01-30', 'Min 1 year java experience', 1);