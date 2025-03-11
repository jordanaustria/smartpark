
CREATE TABLE parking (
    id CHAR(36) PRIMARY KEY,
    location VARCHAR(255) NOT NULL UNIQUE,
    capacity INT NOT NULL,
    occupied INT NOT NULL DEFAULT 0
);

CREATE TABLE vehicle (
    id CHAR(36) PRIMARY KEY,
    license_plate VARCHAR(20) NOT NULL UNIQUE,
    type VARCHAR(50) NOT NULL,
    owner VARCHAR(100) NOT NULL,
    parking_lot_id CHAR(36),
    FOREIGN KEY (parking_lot_id) REFERENCES parking(id) ON DELETE SET NULL
);
