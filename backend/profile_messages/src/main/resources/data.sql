insert into profile(username,email,area_code,phone_number)
values('test','test@email.com', 444, 12345678),
      ('test2','test2@email.com',878,12345678);

insert into contact_details(id,owning_username,contact_username)
values(0,'test','test2'),
      (1,'test2','test');

insert into conversation(conversation_id)
values(0);

insert into conversation_record(id,conversation_id,owning_username)
values(0,0,'test');

insert into message(id,message,conversation_id,sender_username)
values(0,'first message',0,'test'),
      (1,'second_message',0,'test2');
