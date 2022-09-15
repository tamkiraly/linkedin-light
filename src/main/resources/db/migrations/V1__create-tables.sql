CREATE TABLE client (
                        id              BIGINT       NOT NULL auto_increment,
                        api_key         VARCHAR(255) NOT NULL,
                        client_name     VARCHAR(255) NOT NULL,
                        email           VARCHAR(255) NOT NULL,
                        PRIMARY KEY (id),
                        UNIQUE (id)
);

CREATE TABLE position (
                          id                      BIGINT       NOT NULL auto_increment,
                          position_name           VARCHAR(255) NOT NULL,
                          position_location       VARCHAR(255) NOT NULL,
                          position_url            VARCHAR(255) NOT NULL,
                          PRIMARY KEY (id),
                          UNIQUE (id)
);