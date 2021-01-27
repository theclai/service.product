alter table category
    add column image uuid not null;

alter table product_variant
    add column order_weight int not null;

create table variant_image_tx (
    id uuid primary key,
    tx bigserial references log(tx),
    created_time timestamp with time zone not null default(current_timestamp)
);

create index variant_image_created_time_tx_idx on variant_image_tx(created_time, id);

create table variant_image (
    id uuid references variant_image_tx(id),
    tx bigserial references log(tx),
    primary key(id, tx),
    valid_time timestamp with time zone not null default(current_timestamp),
    deleted boolean default (false),
    variant uuid references product_variant_tx(id),
    order_weight int not null
);

create index variant_image_valid_time_id_tx_idx on variant_image(valid_time, id, tx);
create index variant_image_id_valid_time_tx_idx on variant_image(id, valid_time, tx);
