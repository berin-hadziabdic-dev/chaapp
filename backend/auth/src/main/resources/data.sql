insert into users(username,password_,email) values ('test','test','test@gmail.com');

insert into authority(authority_id, authority_description) values(0,'not_verified');
insert into authority(authority_id, authority_description) values(1,'verified');

insert into user_authority(authority_id,username,authority_key) values(0,'test',0);