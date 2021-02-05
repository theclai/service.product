create table product_variant_tx (
    id uuid primary key,
    tx bigserial not null references log(tx),
    created_time timestamp with time zone not null default(current_timestamp)
);

create index product_variant_created_time_tx_idx on product_variant_tx(created_time, id);

create type product_variant_form as enum ('digital', 'physical', 'billing', 'lending');

create table product_variant (
    id uuid not null references product_variant_tx(id),
    tx bigserial not null references log(tx),
    primary key(id, tx),
    valid_time timestamp with time zone not null default(current_timestamp),
    deleted boolean default (false),
    title text not null,
    subtitle text,
    sku varchar(1024),
    description text,
    product uuid references product_tx(id),
    quantity int not null,
    price_currency_code char(3) not null,
    price_units bigint not null,
    price_nanos int not null,
    form product_variant_form,
    width int not null,
    length int not null,
    height int not null,
    weight int not null
);

create index product_variant_valid_time_id_tx_idx on product_variant(valid_time, id, tx);
create index product_variant_id_valid_time_tx_idx on product_variant(id, valid_time, tx);
