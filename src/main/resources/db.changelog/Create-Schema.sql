CREATE SCHEMA  if not exists drycleaning;

CREATE TABLE if not exists drycleaning.client
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

ALTER TABLE  drycleaning.client
    ADD CONSTRAINT uc_client_email UNIQUE (email);

CREATE TABLE  if not exists drycleaning.clothes_category
(
    id    BIGINT AUTO_INCREMENT NOT NULL,
    type  VARCHAR(255)          NOT NULL,
    size  VARCHAR(255)          NOT NULL,
    price DECIMAL               NOT NULL,
    CONSTRAINT pk_clothes_category PRIMARY KEY (id)
);

CREATE TABLE  if not exists drycleaning.position
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    job_title VARCHAR(255)          NOT NULL,
    duties    VARCHAR(255)          NULL,
    CONSTRAINT pk_position PRIMARY KEY (id)
);

CREATE TABLE  if not exists drycleaning.employee
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    first_name  VARCHAR(255)          NOT NULL,
    last_name   VARCHAR(255)          NOT NULL,
    phone       VARCHAR(255)          NOT NULL,
    position_id BIGINT                NOT NULL,
    CONSTRAINT pk_employee PRIMARY KEY (id)
);

ALTER TABLE drycleaning.employee
    ADD CONSTRAINT FK_EMPLOYEE_ON_POSITION FOREIGN KEY (position_id) REFERENCES position (id);

CREATE TABLE if not exists drycleaning.payment
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    payment_method VARCHAR(255)          NOT NULL,
    status         VARCHAR(255)          NOT NULL,
    employee_id    BIGINT                NOT NULL,
    CONSTRAINT pk_payment PRIMARY KEY (id)
);

ALTER TABLE drycleaning.payment
    ADD CONSTRAINT FK_PAYMENT_ON_EMPLOYEE FOREIGN KEY (employee_id) REFERENCES employee (id);

CREATE TABLE if not exists drycleaning.service_type
(
    id    BIGINT AUTO_INCREMENT NOT NULL,
    type  VARCHAR(255)          NOT NULL,
    price DECIMAL               NOT NULL,
    CONSTRAINT pk_service_type PRIMARY KEY (id)
);

CREATE TABLE if not exists drycleaning.orders
(
    service_type_id  BIGINT       NOT NULL,
    order_start_time datetime     NOT NULL,
    order_end_time   datetime     NULL,
    client_id        BIGINT       NOT NULL,
    payment_id       BIGINT       NOT NULL,
    employee_id      BIGINT       NOT NULL,
    order_status     VARCHAR(255) NULL,
    CONSTRAINT pk_orders PRIMARY KEY (service_type_id)
);

ALTER TABLE drycleaning.orders
    ADD CONSTRAINT FK_ORDERS_ON_CLIENT FOREIGN KEY (client_id) REFERENCES drycleaning.client (id);

ALTER TABLE drycleaning.orders
    ADD CONSTRAINT FK_ORDERS_ON_EMPLOYEE FOREIGN KEY (employee_id) REFERENCES drycleaning.employee (id);

ALTER TABLE drycleaning.orders
    ADD CONSTRAINT FK_ORDERS_ON_PAYMENT FOREIGN KEY (payment_id) REFERENCES drycleaning.payment (id);

ALTER TABLE drycleaning.orders
    ADD CONSTRAINT FK_ORDERS_ON_SERVICE_TYPE FOREIGN KEY (service_type_id) REFERENCES drycleaning.service_type (id);

CREATE TABLE if not exists  drycleaning.items
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    orders_id           BIGINT                NOT NULL,
    clothes_category_id BIGINT                NOT NULL,
    material            VARCHAR(255)          NULL,
    wash                VARCHAR(255)          NULL,
    squeeze_out         VARCHAR(255)          NULL,
    dry                 VARCHAR(255)          NULL,
    iron                VARCHAR(255)          NULL,
    white               VARCHAR(255)          NULL,
    dry_cleaning        VARCHAR(255)          NULL,
    CONSTRAINT pk_items PRIMARY KEY (id)
);

ALTER TABLE drycleaning.items
    ADD CONSTRAINT FK_ITEMS_ON_CLOTHES_CATEGORY FOREIGN KEY (clothes_category_id) REFERENCES drycleaning.clothes_category (id);

ALTER TABLE drycleaning.items
    ADD CONSTRAINT FK_ITEMS_ON_ORDERS FOREIGN KEY (orders_id) REFERENCES drycleaning.orders (service_type_id);