delete from product;
delete from product_tx;
delete from log;

--Fitness bracelet
insert into product(id, tx, valid_time, category, quantity)
values('9a0e4932-44be-11eb-b378-0242ac130002', 0, current_timestamp, '3dcd405f-d0b5-4841-b9f2-c1ef6394d989', 10);

insert into product_tx(id, tx, created_time)
values('9a0e4932-44be-11eb-b378-0242ac130002', 0, current_timestamp);

--USB Type-C Cable
insert into product(id, tx, valid_time, category, quantity)
values('df659673-dc85-49bc-8af6-f7497275a064', 0, current_timestamp, '713ec0bc-7a85-4fb6-8f9d-e2a8837b1abd', 0);

insert into product_tx(id, tx, created_time)
values('df659673-dc85-49bc-8af6-f7497275a064', 0, current_timestamp);

--Headset with box
insert into product(id, tx, valid_time, category, quantity)
values('19ef2f61-7ceb-4a69-a423-cdd513688e94', 0, current_timestamp, '3dcd405f-d0b5-4841-b9f2-c1ef6394d989', 3);

insert into product_tx(id, tx, created_time)
values('19ef2f61-7ceb-4a69-a423-cdd513688e94', 0, current_timestamp);

--Headset
insert into product(id, tx, valid_time, category, quantity)
values('ec0cb10f-f275-4861-9d04-cde3c4f5b868', 0, current_timestamp, '3dcd405f-d0b5-4841-b9f2-c1ef6394d989', 56);

insert into product_tx(id, tx, created_time)
values('ec0cb10f-f275-4861-9d04-cde3c4f5b868', 0, current_timestamp);

--Pop socket/ Phone stent
insert into product(id, tx, valid_time, category, quantity)
values('28b8a221-9248-4728-9c90-e5b54b7f7f43', 0, current_timestamp, '713ec0bc-7a85-4fb6-8f9d-e2a8837b1abd', 143);

insert into product_tx(id, tx, created_time)
values('28b8a221-9248-4728-9c90-e5b54b7f7f43', 0, current_timestamp);

insert into log(tx)
values(0);