create table product_variant_tx (
    like node_tx including indexes
);

create type product_money as (
    currency_code char(3),
    units bigint,
    nanos int
);

create type product_form as enum ('digital', 'physical', 'billing', 'lending');

create table product_variant (
    like node including indexes,
    title text not null,
    subtitle text,
    sku varchar(1024),
    description text,
    product uuid references product_tx(id),
    quantity int not null,
    price product_money,
    form product_form
);
