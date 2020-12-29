create table variant_option_tx (
    like node_tx including indexes
);

create table variant_option (
    like node including indexes,
    variant uuid references product_variant_tx(id),
    key text not null,
    value text not null
);
