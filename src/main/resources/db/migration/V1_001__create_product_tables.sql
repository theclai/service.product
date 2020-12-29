create table product_tx (
    like node_tx including indexes
);

create table product (
    like node including indexes,
    category uuid references category_tx(id),
    quantity int not null
);
