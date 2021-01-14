insert into product_variant_tx(id,tx) values
    ('088ee4d7-e526-4626-bd78-6cebf0194e4a',1),-- fitness-1
    ('951d0352-0e1b-43a7-b874-d4c950d5b457',0),-- usb-1
    ('d8f20c40-889c-4170-8f7a-3d19739e26ef',0),-- headset-box-1
    ('41f29159-5634-4e23-a600-59af937ee61f',0),-- headset-blue-1
    ('6aa5b22d-caa4-434c-a844-507d248d2a96',0),-- headset-white-1
    ('023bf7d0-315b-4f4e-b202-0faf9b0bda2f',0),-- headset-red-1
    ('3fa225ef-3b39-418c-8721-f3d8eb12973e',0),-- headset-black-1
    ('44487274-c689-4596-a596-5dc3d71569e3',0),-- phone-stent-pink-1
    ('d5facab3-287a-4b45-98a9-ac55e00f932c',0),-- phone-stent-white-1
    ('53a6df9a-02e4-4d4d-b1f9-02300cfa3fc4',0),-- phone-stent-red-1
    ('d12f88b5-3668-4ab8-8dbc-3a64dc9ddb97',0),-- phone-stent-blue-1
    ('6816eb2d-96d4-4aa1-8604-f46340c2d222',0), -- phone-stent-cartoon-1
    ('d1bc5271-ea6f-4ef9-9b12-ba479d738b39',1); -- phone-deleted-1

insert into product_variant(id,tx,deleted,product, title,subtitle,sku,description,quantity,price_currency_code, price_units, price_nanos,form, width, length, height, weight) values
    ('088ee4d7-e526-4626-bd78-6cebf0194e4a',0,false,'9a0e4932-44be-11eb-b378-0242ac130002','Fitness bracelet',null,'fitness-1',null,10,'IDR',1068051,0,'physical', 10, 10, 10, 10),
    ('088ee4d7-e526-4626-bd78-6cebf0194e4a',1,false,'9a0e4932-44be-11eb-b378-0242ac130002','Fitness bracelet',null,'fitness-1','Heart rate monitor ,sleep monitor,blood pressure,bluetooth call and message reminder,remote music/camera.Alarm clock,calendar,stopwatch. Language:English,German,Spanish,Italian,French,Portuguese (Brazil),Russian,Indonesian,Czech,Arabic,Thai,Dutch,Polish,Turkish,Persian,Hebrew,Malaysian,Vietnamese,Greek language.','10','IDR',1068051,0,'physical', 5, 5, 5, 5),
    ('951d0352-0e1b-43a7-b874-d4c950d5b457',0,false,'df659673-dc85-49bc-8af6-f7497275a064','USB Type-C Cable',null,'usb-1',null,10,'IDR',24500,0,'physical', 15, 15, 15, 15),
    ('d8f20c40-889c-4170-8f7a-3d19739e26ef',0,false,'19ef2f61-7ceb-4a69-a423-cdd513688e94','Headset with box',null,'headset-box-1',null,10,'IDR', 1068051,0,'physical', 20, 20, 20, 20),
    ('41f29159-5634-4e23-a600-59af937ee61f',0,false,'ec0cb10f-f275-4861-9d04-cde3c4f5b868','Headset (Blue)',null,'headset-blue-1',null,4,'IDR',26000,0,'physical', 5, 5, 5, 5),
    ('6aa5b22d-caa4-434c-a844-507d248d2a96',0,false,'ec0cb10f-f275-4861-9d04-cde3c4f5b868','Headset (White)',null, 'headset-white-1',null,47,'IDR',22000,0,'physical', 5, 5, 7, 7),
    ('023bf7d0-315b-4f4e-b202-0faf9b0bda2f',0,false,'ec0cb10f-f275-4861-9d04-cde3c4f5b868','Headset (Red)',null,'headset-red-1',null,5,'IDR',26000,0,'physical', 20, 20, 20, 20),
    ('3fa225ef-3b39-418c-8721-f3d8eb12973e',0,false,'28b8a221-9248-4728-9c90-e5b54b7f7f43','Phone stent (Black)','Pop socket / Phone stent','phone-stent-black-1',null,43,'IDR',4000,0,'physical', 20, 20, 20, 20),
    ('44487274-c689-4596-a596-5dc3d71569e3',0,false,'28b8a221-9248-4728-9c90-e5b54b7f7f43','Phone stent (Pink)','Pop socket / Phone stent','phone-stent-pink-1',null,20,'IDR',4000,0,'physical', 20, 20, 20, 20),
    ('d5facab3-287a-4b45-98a9-ac55e00f932c',0,false,'28b8a221-9248-4728-9c90-e5b54b7f7f43','Phone stent (White)','Pop socket / Phone stent','phone-stent-white-1',null,20,'IDR',4000,0,'physical', 20, 20, 20, 20),
    ('53a6df9a-02e4-4d4d-b1f9-02300cfa3fc4',0,false,'28b8a221-9248-4728-9c90-e5b54b7f7f43','Phone stent (Red)','Pop socket / Phone stent','phone-stent-red-1',null,20,'IDR',4000,0,'physical', 20, 20, 20, 20),
    ('d12f88b5-3668-4ab8-8dbc-3a64dc9ddb97',0,false,'28b8a221-9248-4728-9c90-e5b54b7f7f43','Phone stent (Blue)','Pop socket / Phone stent','phone-stent-blue-1',null,20,'IDR',4000,0,'physical', 20, 20, 20, 20),
    ('6816eb2d-96d4-4aa1-8604-f46340c2d222',0,false,'28b8a221-9248-4728-9c90-e5b54b7f7f43','Phone stent (cartoon)','Pop socket / Phone stent','phone-stent-cartoon-1',null,20,'IDR',4000,0,'physical', 20, 20, 20, 20),
    ('d1bc5271-ea6f-4ef9-9b12-ba479d738b39',0,false,'28b8a221-9248-4728-9c90-e5b54b7f7f43','Phone stent (delete test)','Pop socket / Phone stent','phone-stent-cartoon-1',null,20,'IDR',4000,0,'physical', 20, 20, 20, 20),
    ('d1bc5271-ea6f-4ef9-9b12-ba479d738b39',1,true ,'28b8a221-9248-4728-9c90-e5b54b7f7f43','Phone stent (delete test)','Pop socket / Phone stent','phone-stent-cartoon-1',null,20,'IDR',4000,0,'physical', 20, 20, 20, 20);

