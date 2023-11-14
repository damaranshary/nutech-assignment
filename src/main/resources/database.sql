CREATE DATABASE sims_ppob;
USE sims_ppob;

DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user
(
    EMAIL      VARCHAR(100) NOT NULL,
    FIRST_NAME VARCHAR(100) NOT NULL,
    LAST_NAME  VARCHAR(100),
    PASSWORD   VARCHAR(100) NOT NULL,
    BALANCE    BIGINT       NOT NULL,
    PRIMARY KEY (EMAIL)
) ENGINE InnoDB;

INSERT INTO t_user VALUES ('damar@email.com', 'Damar', 'Galih Anshary', 'damar123', 125000);
INSERT INTO t_user VALUES ('damaruser@email.com', 'Damar','damar123', 'Anshary', 0);

SELECT * FROM t_user;

DROP TABLE IF EXISTS t_role;
CREATE TABLE t_role
(
    ID   VARCHAR(100) NOT NULL,
    NAME VARCHAR(100) NOT NULL,
    PRIMARY KEY (ID)
) ENGINE InnoDB;

INSERT INTO t_role (ID,NAME) VALUES ('ROLE_ADMIN','Role Administrator');
INSERT INTO t_role (ID,NAME) VALUES ('ROLE_USER','Role User');

DROP TABLE IF EXISTS t_user_role;
CREATE TABLE t_user_role
(
    EMAIL   VARCHAR(100) NOT NULL,
    ROLE_ID VARCHAR(100) NOT NULL,
    PRIMARY KEY (EMAIL, ROLE_ID),
    FOREIGN KEY fk_userrole_email (EMAIL) REFERENCES t_user (EMAIL),
    FOREIGN KEY fk_userrole_role (ROLE_ID) REFERENCES t_role (ID)
) ENGINE InnoDB;

INSERT INTO t_user_role VALUES ('damar@email.com', 'ROLE_ADMIN');
INSERT INTO t_user_role VALUES ('damaruser@email.com', 'ROLE_USER');

DROP TABLE IF EXISTS t_banner;
CREATE TABLE t_banner
(
    ID           BIGINT       NOT NULL AUTO_INCREMENT,
    BANNER_NAME  VARCHAR(100) NOT NULL,
    BANNER_IMAGE VARCHAR(255) NOT NULL,
    DESCRIPTION  VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID)
) ENGINE InnoDB;

INSERT INTO t_banner (BANNER_NAME, BANNER_IMAGE, DESCRIPTION) VALUES ('Banner 1', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet');
INSERT INTO t_banner (BANNER_NAME, BANNER_IMAGE, DESCRIPTION) VALUES ('Banner 2', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet');
INSERT INTO t_banner (BANNER_NAME, BANNER_IMAGE, DESCRIPTION) VALUES ('Banner 3', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet');
INSERT INTO t_banner (BANNER_NAME, BANNER_IMAGE, DESCRIPTION) VALUES ('Banner 4', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet');
INSERT INTO t_banner (BANNER_NAME, BANNER_IMAGE, DESCRIPTION) VALUES ('Banner 5', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet');

DROP TABLE IF EXISTS t_service;
CREATE TABLE t_service
(
    CODE   VARCHAR(100) NOT NULL,
    NAME   VARCHAR(100) NOT NULL,
    ICON   VARCHAR(255) NOT NULL,
    TARIFF BIGINT       NOT NULL,
    PRIMARY KEY (CODE)
) ENGINE InnoDB;

INSERT INTO t_service VALUES ('PAJAK', 'PAJAK PBB', 'https://nutech-integrasi.app/dummy.jpg', 40000);

DROP TABLE IF EXISTS t_transaction;
CREATE TABLE t_transaction
(
    INVOICE_NUMBER   VARCHAR(100) NOT NULL,
    TRANSACTION_TYPE VARCHAR(100) NOT NULL,
    DESCRIPTION      VARCHAR(255) NOT NULL,
    TOTAL_AMOUNT     BIGINT       NOT NULL,
    CREATED_ON       DATETIME     NOT NULL,
    USER_ID          VARCHAR(100) NOT NULL,
    PRIMARY KEY (INVOICE_NUMBER),
    FOREIGN KEY fk_user_transaction (USER_ID) REFERENCES t_user (EMAIL)
)


