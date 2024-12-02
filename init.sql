CREATE TABLE IF NOT EXISTS Customer
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(100)        NOT NULL,
    lastName  VARCHAR(100)        NOT NULL,
    email      VARCHAR(100) UNIQUE NOT NULL,
    active     BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS VehicleType
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    type        VARCHAR(50) NOT NULL,
    description TEXT,
    dailyRate  DOUBLE      NOT NULL,
    active      BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS Rental
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    vehicleTypeId BIGINT NOT NULL,
    customerId     BIGINT NOT NULL,
    rentalDate     DATE,
    returnDate     DATE,
    active          BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (vehicleTypeId) REFERENCES VehicleType (id),
    FOREIGN KEY (customerId) REFERENCES Customer (id)
);

CREATE TABLE IF NOT EXISTS User
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    role     VARCHAR(50)         NOT NULL,
    active   BOOLEAN DEFAULT TRUE
);
