CREATE TABLE IF NOT EXISTS Customer
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100)        NOT NULL,
    last_name  VARCHAR(100)        NOT NULL,
    email      VARCHAR(100) UNIQUE NOT NULL,
    active     BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS VehicleType
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    type        VARCHAR(50) NOT NULL,
    description TEXT,
    daily_rate  DOUBLE      NOT NULL,
    active      BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS Rental
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    vehicle_type_id BIGINT NOT NULL,
    customer_id     BIGINT NOT NULL,
    rental_date     DATE,
    return_date     DATE,
    active          BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (vehicle_type_id) REFERENCES VehicleType (id),
    FOREIGN KEY (customer_id) REFERENCES Customer (id)
);

CREATE TABLE IF NOT EXISTS User
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    role     VARCHAR(50)         NOT NULL,
    active   BOOLEAN DEFAULT TRUE
);
