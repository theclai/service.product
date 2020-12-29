create table variant_property_tx (
    like node_tx including indexes
);

create table variant_property (
    like node including indexes,
    variant uuid references product_variant_tx(id),
    key text not null,
    value text not null,
    order_nr int
);
