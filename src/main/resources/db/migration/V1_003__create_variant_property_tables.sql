create table variant_property_tx (
    variant uuid references product_variant_tx(id),
    id int,
    tx bigserial references log(tx),
    created_time timestamp with time zone not null default(current_timestamp),
    primary key(variant, id)
);

create index variant_property_tx_created_time_idx on variant_property_tx(created_time, variant, id);

create table variant_property (
    variant uuid,
    id int,
    tx bigserial references log(tx),
    valid_time timestamp with time zone not null default(current_timestamp),
    deleted boolean default (false),
    key text not null,
    value text not null,
    foreign key(variant, id) references variant_property_tx(variant, id),
    primary key(variant, id, tx)
);

create index variant_property_variant_id_valid_time_tx_idx on variant_property(variant, id, valid_time, tx);
create index variant_property_valid_time_variant_id_tx_idx on variant_property(valid_time, variant, id, tx);
