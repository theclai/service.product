create table log (
    tx bigserial primary key,
    transaction_time timestamp with time zone not null default (current_timestamp)
);

create index log_transaction_time_idx on log(transaction_time);

create table node_tx (
    id uuid primary key,
    tx bigserial references log(tx),
    created_time timestamp with time zone not null default (current_timestamp)
);

create index node_tx_tx_idx on node_tx(tx);

create table node (
    id uuid references node_tx(id),
    tx bigserial references log(tx),
    valid_time timestamp with time zone not null default (current_timestamp),
    deleted boolean default (false),
    primary key(id, tx)
);

create index node_valid_time_idx on node(valid_time);

create table category_tx (
    like node_tx including indexes
);

create table category (
    like node including indexes,
    title text not null,
    subtitle text,
    description text,
    parent uuid references category_tx(id)
);