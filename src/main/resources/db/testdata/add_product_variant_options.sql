delete from variant_option;
delete from variant_option_tx;

insert into variant_option(variant, id, tx, valid_time, value)
values('41f29159-5634-4e23-a600-59af937ee61f','b733d139-a2f0-4f17-9f70-1edeb729aeb5', '0', current_timestamp, 'blue');

--insert into variant_option_tx(variant, id, tx)
--values('41f29159-5634-4e23-a600-59af937ee61f','b733d139-a2f0-4f17-9f70-1edeb729aeb5');