delete from category;
delete from category_tx;
delete from log;

insert into category(id, tx, valid_time, title, subtitle, description)
values('9a0e4932-44be-11eb-b378-0242ac130002','0', current_timestamp, 'Physical Goods','','Electronics and accessories delivered to your door');

insert into category_tx(id, tx, created_time)
values('9a0e4932-44be-11eb-b378-0242ac130002','0', current_timestamp);

insert into category(id, tx, valid_time, title, subtitle, description, parent)
values('3dcd405f-d0b5-4841-b9f2-c1ef6394d989','0', current_timestamp, 'Electronics','','','9a0e4932-44be-11eb-b378-0242ac130002');

insert into category_tx(id, tx, created_time)
values('3dcd405f-d0b5-4841-b9f2-c1ef6394d989','0', current_timestamp);

insert into category(id, tx, valid_time, title, subtitle, description, parent)
values('713ec0bc-7a85-4fb6-8f9d-e2a8837b1abd','0', current_timestamp, 'Phone Accessories','','','9a0e4932-44be-11eb-b378-0242ac130002');

insert into category_tx(id, tx, created_time)
values('713ec0bc-7a85-4fb6-8f9d-e2a8837b1abd','0', current_timestamp);

insert into log(tx)
values(0);