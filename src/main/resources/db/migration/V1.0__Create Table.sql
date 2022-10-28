CREATE TABLE product
(
    id           UUID,
    name         VARCHAR(255) not null,
    CONSTRAINT pk_product_id PRIMARY KEY (id)
);

CREATE TABLE dun
(
    id         UUID,
    product_id UUID      not null,
    ean        BIGINT    not null,
    quantity   INT       not null,
    CONSTRAINT pk_dun_id PRIMARY KEY (id)
);

ALTER TABLE dun
    ADD CONSTRAINT fk_dun2product FOREIGN KEY (product_id) REFERENCES product (id);
CREATE INDEX dunEan ON dun (ean);
