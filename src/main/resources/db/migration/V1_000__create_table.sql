create table log (
    tx bigserial primary key,
    transaction_time timestamp with time zone not null default(current_timestamp)
);

create index log_transaction_time_idx on log(transaction_time, tx);

create table category_tx (
    id uuid primary key,
    tx bigserial references log(tx),
    created_time timestamp with time zone not null default(current_timestamp)
);

create index category_tx_created_time_idx on category_tx(created_time, id);

create table category (
    id uuid references category_tx(id),
    tx bigserial references log(tx),
    primary key(id, tx),
    valid_time timestamp with time zone not null default(current_timestamp),
    deleted boolean default(false),
    title text not null,
    subtitle text,
    description text,
    parent uuid references category_tx(id)
);

create index category_valid_time_id_tx_idx on category(valid_time, id, tx);
create index category_id_valid_time_tx_idx on category(id, valid_time, tx);
