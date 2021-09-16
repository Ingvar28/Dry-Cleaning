CREATE DATABASE if not exists dry_cleaning;

CREATE TABLE if not exists dry_cleaning.client
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    first_name    VARCHAR(20)           NOT NULL,
    last_name     VARCHAR(40)           NOT NULL,
    phone         VARCHAR(15)           NOT NULL,
    email         VARCHAR(40)           NOT NULL,
    client_level  VARCHAR(40)           NOT NULL,
    `description` VARCHAR(255)          NULL,
    CONSTRAINT pk_client PRIMARY KEY (id)
);

CREATE TABLE if not exists dry_cleaning.clothes_category
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    category VARCHAR(255)          NULL,
    size     VARCHAR(255)          NULL,
    price    DECIMAL               NULL,
    CONSTRAINT pk_clothes_category PRIMARY KEY (id)
);

CREATE TABLE if not exists dry_cleaning.position
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    job_title VARCHAR(255)          NULL,
    duties    VARCHAR(255)          NULL,
    CONSTRAINT pk_position PRIMARY KEY (id)
);

CREATE TABLE dry_cleaning.employee
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    first_name  VARCHAR(255)          NULL,
    last_name   VARCHAR(255)          NULL,
    phone       VARCHAR(255)          NULL,
    position_id BIGINT                NOT NULL,
    CONSTRAINT pk_employee PRIMARY KEY (id)
);

ALTER TABLE dry_cleaning.employee
    ADD CONSTRAINT FK_EMPLOYEE_ON_POSITION FOREIGN KEY (position_id) REFERENCES dry_cleaning.position (id);

CREATE TABLE if not exists dry_cleaning.service_type
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    service_type VARCHAR(255)          NULL,
    price        DECIMAL               NULL,
    CONSTRAINT pk_service_type PRIMARY KEY (id)
);

CREATE TABLE if not exists dry_cleaning.payment
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    payment_method VARCHAR(255)          NULL,
    status         VARCHAR(255)          NULL,
    employee_id    BIGINT                NOT NULL,
    CONSTRAINT pk_payment PRIMARY KEY (id)
);

ALTER TABLE dry_cleaning.payment
    ADD CONSTRAINT FK_PAYMENT_ON_EMPLOYEE FOREIGN KEY (employee_id) REFERENCES dry_cleaning.employee (id);

CREATE TABLE if not exists dry_cleaning.order_cart
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    order_start_time datetime              NULL,
    order_end_time   datetime              NULL,
    client_id        BIGINT                NULL,
    payment_id       BIGINT                NOT NULL,
    service_type_id  BIGINT                NOT NULL,
    employee_id      BIGINT                NULL,
    order_status     VARCHAR(255)          NULL,
    CONSTRAINT pk_order_cart PRIMARY KEY (id)
);

ALTER TABLE dry_cleaning.order_cart
    ADD CONSTRAINT FK_ORDER_CART_ON_CLIENT FOREIGN KEY (client_id) REFERENCES dry_cleaning.client (id);

ALTER TABLE dry_cleaning.order_cart
    ADD CONSTRAINT FK_ORDER_CART_ON_EMPLOYEE FOREIGN KEY (employee_id) REFERENCES dry_cleaning.employee (id);

ALTER TABLE dry_cleaning.order_cart
    ADD CONSTRAINT FK_ORDER_CART_ON_PAYMENT FOREIGN KEY (payment_id) REFERENCES dry_cleaning.payment (id);

ALTER TABLE dry_cleaning.order_cart
    ADD CONSTRAINT FK_ORDER_CART_ON_SERVICE_TYPE FOREIGN KEY (service_type_id) REFERENCES dry_cleaning.service_type (id);

CREATE TABLE if not exists dry_cleaning.cart_item
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    order_id            BIGINT                NOT NULL,
    clothes_category_id BIGINT                NOT NULL,
    material            VARCHAR(255)          NULL,
    wash                VARCHAR(255)          NULL,
    squeeze_out         VARCHAR(255)          NULL,
    dry                 VARCHAR(255)          NULL,
    iron                VARCHAR(255)          NULL,
    white               VARCHAR(255)          NULL,
    dry_cleaning        VARCHAR(255)          NULL,
    CONSTRAINT pk_item PRIMARY KEY (id)
);

ALTER TABLE dry_cleaning.cart_item
    ADD CONSTRAINT  FK_CART_ITEM_ON_CLOTHES_CATEGORY FOREIGN KEY (clothes_category_id) REFERENCES dry_cleaning.clothes_category (id);

ALTER TABLE dry_cleaning.cart_item
    ADD CONSTRAINT FK_CART_ITEM_ON_ORDER FOREIGN KEY (order_id) REFERENCES dry_cleaning.order_cart (id);