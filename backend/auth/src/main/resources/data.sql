insert into users(username,password_,email) values ('test','test','test@gmail.com');

insert into authority(authority_id, authority_description) values(0,'registered_user');
insert into authority(authority_id, authority_description) values(1,'email_verified_user');

insert into user_authority(authority_id,username,authority_key) values(0,'test',0);