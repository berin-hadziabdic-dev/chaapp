insert into profile(username,email,area_code,phone_number)
values('test','test@email.com', 444, 12345678),
      ('test2','test2@email.com',878,12345678),
      ('test3','test3@email.com',878,12345678);


insert into profile_contact(profile_contact_id,owning_username,contact_username)
values(0,'test','test2'),
      (1,'test','test3'),
      (2,'test2','test3');

insert into chat(chat_id)
values(100),(101);

insert into message(id,message,chat_id,sender_username)
values(10000,'hi test2',100,'test'),
      (10001,'hi test', 100, 'test2'),
      (10002,'hi hi test 3 from test1',101,'test1'),
      (10003, 'hi hi test 1 from test 3',101,'test3');

insert into chat_participant(chat_participant_id,chat_id,owning_username)
values (100,100,'test'),
       (101,100,'test2'),
       (102,101,'test'),
       (103,101,'test3');
