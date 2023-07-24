DROP TABLE IF EXISTS item;
CREATE TABLE item
(
    id  bigint NOT NULL AUTO_INCREMENT,
    name    varchar(50),
    price   int,
    primary key (id)
)