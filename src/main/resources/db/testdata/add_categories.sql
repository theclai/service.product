insert into log(tx) values(0), (1);

-- Create categories
insert into category_tx(id, tx) values
    ('9a0e4932-44be-11eb-b378-0242ac130002',1),
    ('3dcd405f-d0b5-4841-b9f2-c1ef6394d989',0),
    ('713ec0bc-7a85-4fb6-8f9d-e2a8837b1abd',0),
    ('f0f695a2-91bb-40e0-afa3-b9e81e08b583',1);

insert into category(id, tx, deleted, title, subtitle, description, parent) values
    ('9a0e4932-44be-11eb-b378-0242ac130002',0,false,'Physical Goods',null,null,null),
    ('9a0e4932-44be-11eb-b378-0242ac130002',1,false,'Physical Goods',null,'Electronics and accessories delivered to your door', null),
    ('3dcd405f-d0b5-4841-b9f2-c1ef6394d989',0,false,'Electronics',null,null,'9a0e4932-44be-11eb-b378-0242ac130002'),
    ('713ec0bc-7a85-4fb6-8f9d-e2a8837b1abd',0,false,'Phone Accessories',null,null,'9a0e4932-44be-11eb-b378-0242ac130002'),
    ('f0f695a2-91bb-40e0-afa3-b9e81e08b583',0,false,'Yachts',null,null,'9a0e4932-44be-11eb-b378-0242ac130002'),
    ('f0f695a2-91bb-40e0-afa3-b9e81e08b583',1,true,'Yachts',null,null,'9a0e4932-44be-11eb-b378-0242ac130002');
