insert into profile(username,email,area_code,phone_number)
values('test','test@email.com', 444, 12345678);

insert into contact_details(id,contact_username)
values(0,'test');

insert into conversation(conversation_id,user_one,user_two)
values(0,'test','test');

insert into message(id,message,conversation_id,sender_username)
values(0,'first message',0,'test');