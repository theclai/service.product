create table variant_option_tx (
    variant uuid references product_variant_tx(id),
    id varchar(1024),
    tx bigserial not null references log(tx),
    created_time timestamp with time zone not null default(current_timestamp),
    primary key(variant, id)
);

create index variant_option_tx_created_time_idx on variant_option_tx(created_time, variant, id);

create table variant_option (
    variant uuid,
    id varchar(1024) not null,
    tx bigserial not null references log(tx),
    valid_time timestamp with time zone not null default(current_timestamp),
    deleted boolean default (false),
    value text not null,
    foreign key(variant, id) references variant_option_tx(variant, id),
    primary key(variant, id, tx)
);

create index variant_option_variant_id_valid_time_tx_idx on variant_option(variant, id, valid_time, tx);
create index variant_option_valid_time_variant_id_tx_idx on variant_option(valid_time, variant, id, tx);
