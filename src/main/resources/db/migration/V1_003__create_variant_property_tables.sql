create table variant_property_tx (
    id uuid primary key,
    tx bigserial references log(tx),
    created_time timestamp with time zone not null default (current_timestamp)
);

create index variant_property_tx_created_time_idx on variant_property_tx(created_time, id);

create table variant_property (
    id int,
    tx bigserial references log(tx),
    variant uuid references product_variant_tx(id),
    primary key(variant, id, tx),
    valid_time timestamp with time zone not null default (current_timestamp),
    deleted boolean default (false),
    key text not null,
    value text not null
);

create index variant_property_variant_id_valid_time_tx_idx on variant_property(variant, id, valid_time, tx);
create index variant_property_valid_time_variant_id_tx_idx on variant_property(valid_time, variant, id, tx);
