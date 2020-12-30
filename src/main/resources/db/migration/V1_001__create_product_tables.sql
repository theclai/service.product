create table product_tx (
    id uuid primary key,
    tx bigserial references log(tx),
    created_time timestamp with time zone not null default (current_timestamp)
);

create index product_tx_created_time_idx on product_tx(created_time, id);

create table product (
    id uuid,
    tx bigserial references log(tx),
    primary key(id, tx),
    valid_time timestamp with time zone not null default (current_timestamp),
    deleted boolean default (false),
    category uuid references category_tx(id),
    quantity int not null
);

create index product_valid_time_id_tx_idx on product(valid_time, id, tx);
create index product_id_valid_time_tx_idx on product(id, valid_time, tx);
