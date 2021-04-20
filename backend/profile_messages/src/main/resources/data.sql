insert into profile(username,email,area_code,phone_number)
values('test','test@email.com', 444, 12345678),
      ('test2','test2@email.com',878,12345678),
      ('test3','test3@email.com',878,12345678);


insert into profile_contact(profile_contact_id,owning_username,contact_username)
values(0,'test','test2'),
      (1,'test','test3');

insert into chat(chat_id)
values(0);

insert into message(id,message,chat_id,sender_username)
values(10000,'hi test2',0,'test'),
      (20000,'hi test', 0, 'test2');

insert into chat_participant(chat_participant_id,chat_id,owning_username)
values (0,0,'test'),
       (1,0,'test2');
