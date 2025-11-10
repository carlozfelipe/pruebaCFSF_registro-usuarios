-- ========================================
-- SCRIPT DE CREACIÓN DE TABLAS DEL SISTEMA
-- ========================================

DROP TABLE IF EXISTS phones;
DROP TABLE IF EXISTS users;

-- ==========================
-- TABLA: USERS
-- ==========================
CREATE TABLE users (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    token VARCHAR(200),
    is_active BOOLEAN DEFAULT TRUE
);

-- ==========================
-- TABLA: PHONES
-- ==========================
CREATE TABLE phones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(20) NOT NULL,
    citycode VARCHAR(10) NOT NULL,
    contrycode VARCHAR(10) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Índices opcionales para mejorar rendimiento
CREATE INDEX idx_user_email ON users(email);
CREATE INDEX idx_phone_user ON phones(user_id);