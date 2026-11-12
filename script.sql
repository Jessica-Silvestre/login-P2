-- Banco de dados do sistema de Login (CRUD de usuários)
CREATE DATABASE IF NOT EXISTS bd_adega;
USE bd_adega;

-- Tabela de usuários do sistema (senha guardada em MD5)
CREATE TABLE IF NOT EXISTS usuario(
    usuario varchar(10) primary key,
    nome    varchar(30) default '',
    senha   char(32)    default ''
);

-- Usuário de teste: admin / 123
INSERT IGNORE INTO usuario VALUES ('admin', 'Administrador', MD5('123'));
