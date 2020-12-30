create table product_variant_tx (
    id uuid primary key,
    tx bigserial references log(tx),
    created_time timestamp with time zone not null default (current_timestamp)
);

create index product_variant_created_time_tx_idx on product_variant_tx(created_time, id);

create type product_money as (
    currency_code char(3),
    units bigint,
    nanos int
);

create type product_variant_form as enum ('digital', 'physical', 'billing', 'lending');

create table product_variant (
    id uuid,
    tx bigserial references log(tx),
    primary key(id, tx),
    valid_time timestamp with time zone not null default (current_timestamp),
    deleted boolean default (false),
    title text not null,
    subtitle text,
    sku varchar(1024),
    description text,
    product uuid references product_tx(id),
    quantity int not null,
    price product_money,
    form product_variant_form
);

create index product_variant_valid_time_id_tx_idx on product_variant(valid_time, id, tx);
create index product_variant_id_valid_time_tx_idx on product_variant(id, valid_time, tx);
