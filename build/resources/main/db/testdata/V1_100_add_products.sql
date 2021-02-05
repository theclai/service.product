insert into product_tx(id, tx) values
    ('9a0e4932-44be-11eb-b378-0242ac130002',1), --Fitness bracelet
    ('df659673-dc85-49bc-8af6-f7497275a064',0), --USB Type-C Cable
    ('19ef2f61-7ceb-4a69-a423-cdd513688e94',0), --Headset with box
    ('ec0cb10f-f275-4861-9d04-cde3c4f5b868',0), --Headset
    ('28b8a221-9248-4728-9c90-e5b54b7f7f43',0), --Pop socket/ Phone stent
    ('2779c69a-d970-45a1-80f3-21dcdab7b5d9',1); --"Deleted" product

insert into product(id, tx, deleted, category, quantity) values
    ('9a0e4932-44be-11eb-b378-0242ac130002',0,false,'3dcd405f-d0b5-4841-b9f2-c1ef6394d989', 11),  --Fitness bracelet
    ('9a0e4932-44be-11eb-b378-0242ac130002',1,false,'3dcd405f-d0b5-4841-b9f2-c1ef6394d989', 10),
    ('df659673-dc85-49bc-8af6-f7497275a064',0,false,'713ec0bc-7a85-4fb6-8f9d-e2a8837b1abd', 0),   --USB Type-C Cable
    ('19ef2f61-7ceb-4a69-a423-cdd513688e94',0,false,'3dcd405f-d0b5-4841-b9f2-c1ef6394d989', 3),   --Headset with box
    ('ec0cb10f-f275-4861-9d04-cde3c4f5b868',0,false,'3dcd405f-d0b5-4841-b9f2-c1ef6394d989', 56),  --Headset
    ('28b8a221-9248-4728-9c90-e5b54b7f7f43',0,false,'713ec0bc-7a85-4fb6-8f9d-e2a8837b1abd', 143), --Pop socket/ Phone stent
    ('2779c69a-d970-45a1-80f3-21dcdab7b5d9',0,false,'713ec0bc-7a85-4fb6-8f9d-e2a8837b1abd', 0),   --"Deleted" product
    ('2779c69a-d970-45a1-80f3-21dcdab7b5d9',1,true, '713ec0bc-7a85-4fb6-8f9d-e2a8837b1abd', 0);
