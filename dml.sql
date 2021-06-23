insert into application (application_id,status) values('11235435670','COMPLETE');

INSERT INTO member(member_id,first_name,gender,date_of_birth,is_head_member,application_id) values('112233','Hari','M','1985-02-02',true,'11235435670');

INSERT INTO member(member_id,first_name,gender,date_of_birth,is_head_member,application_id) values('112234','Swetha','F','1987-04-23',false,'11235435670');


INSERT INTO relationship(id,first_member,second_member,relationship) values('123','112233','112234','Wife')
