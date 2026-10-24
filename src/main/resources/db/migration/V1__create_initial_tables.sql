-- V1: Initial database schema - CPTM Stock Management

-- 1. Users

CREATE TABLE app_user (
                          id UUID PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          password_hash VARCHAR(255) NOT NULL,
                          role VARCHAR(255) NOT NULL,
                          active BOOLEAN NOT NULL DEFAULT FALSE,
                          created_at TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,

                          CONSTRAINT chk_user_role
                              CHECK (role IN ('EMPLOYEE', 'MANAGER')));


-- 2. Wood types

CREATE TABLE wood_type (
                           id UUID PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           description VARCHAR(255),
                           active BOOLEAN NOT NULL DEFAULT FALSE);


-- 3. Stock locations

CREATE TABLE stock_location (
                                id UUID PRIMARY KEY,
                                code VARCHAR(255) NOT NULL UNIQUE,
                                name VARCHAR(255) NOT NULL,
                                description VARCHAR(255),
                                active BOOLEAN NOT NULL DEFAULT FALSE);


-- 4. Sleepers

CREATE TABLE sleeper (
                         id UUID PRIMARY KEY,
                         identifier VARCHAR(255) NOT NULL UNIQUE,

                         wood_type_id UUID NOT NULL,
                         stock_location_id UUID NOT NULL,

                         length_meters NUMERIC(10, 3),
                         width_meters NUMERIC(10, 3),
                         height_meters NUMERIC(10, 3),

                         status VARCHAR(255) NOT NULL,
                         created_at TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,

                         CONSTRAINT fk_sleeper_wood_type
                             FOREIGN KEY (wood_type_id)
                                 REFERENCES wood_type(id),

                         CONSTRAINT fk_sleeper_stock_location
                             FOREIGN KEY (stock_location_id)
                                 REFERENCES stock_location(id),

                         CONSTRAINT chk_sleeper_status
                             CHECK (status IN ('IN_STOCK', 'OUT_OF_STOCK')),

                         CONSTRAINT chk_sleeper_dimensions
                             CHECK (
                                 (length_meters IS NULL OR length_meters > 0)
                                     AND (width_meters IS NULL OR width_meters > 0)
                                     AND (height_meters IS NULL OR height_meters > 0)
                                 ));


-- 5. Stock movements

CREATE TABLE stock_movement (
                                id UUID PRIMARY KEY,

                                sleeper_id UUID NOT NULL,
                                type VARCHAR(255) NOT NULL,

                                origin_id UUID,
                                destination_id UUID,
                                performed_by_id UUID NOT NULL,

                                created_at TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
                                notes VARCHAR(255),

                                CONSTRAINT fk_movement_sleeper
                                    FOREIGN KEY (sleeper_id)
                                        REFERENCES sleeper(id),

                                CONSTRAINT fk_movement_origin
                                    FOREIGN KEY (origin_id)
                                        REFERENCES stock_location(id),

                                CONSTRAINT fk_movement_destination
                                    FOREIGN KEY (destination_id)
                                        REFERENCES stock_location(id),

                                CONSTRAINT fk_movement_user
                                    FOREIGN KEY (performed_by_id)
                                        REFERENCES app_user(id),

                                CONSTRAINT chk_movement_type
                                    CHECK (type IN ('ENTRY', 'EXIT', 'TRANSFER'))
);


-- 6. Indexes

CREATE INDEX idx_sleeper_wood_type
    ON sleeper(wood_type_id);

CREATE INDEX idx_sleeper_location
    ON sleeper(stock_location_id);

CREATE INDEX idx_sleeper_status
    ON sleeper(status);

CREATE INDEX idx_movement_sleeper_created
    ON stock_movement(sleeper_id, created_at DESC);

CREATE INDEX idx_movement_user
    ON stock_movement(performed_by_id);