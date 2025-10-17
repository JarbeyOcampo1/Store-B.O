-- Script para insertar usuarios de prueba
-- Este script se ejecutará automáticamente al iniciar la aplicación

-- Insertar usuarios de prueba
INSERT INTO logins (usuario_login, cargo, password_login) VALUES 
('admin', 'GERENTE', 'admin123'),
('gerente1', 'GERENTE', 'gerente123'),
('empleado1', 'EMPLEADO', 'empleado123'),
('empleado2', 'EMPLEADO', 'empleado456');

-- Insertar algunos clientes de prueba
INSERT INTO clientes (cedula_cliente, nombre_cliente, apellido_cliente, email_cliente, telefono_cliente, direccion_cliente, fecha_registro, estado_cliente) VALUES 
('12345678', 'Juan', 'Pérez', 'juan.perez@email.com', '3001234567', 'Calle 123 #45-67', '2024-01-15', 'ACTIVO'),
('87654321', 'María', 'García', 'maria.garcia@email.com', '3007654321', 'Carrera 45 #67-89', '2024-01-16', 'ACTIVO'),
('11223344', 'Carlos', 'López', 'carlos.lopez@email.com', '3001122334', 'Avenida 80 #12-34', '2024-01-17', 'ACTIVO');

-- Insertar algunas bodegas de prueba
INSERT INTO bodegas (nombre_bodega, ubicacion_bodega, tamano_bodega, fecha_registro_bodega, descripcion_bodega, estado_bodega, clienteid) VALUES 
('Bodega Central', 'Bogotá', 'Grande', '2024-01-15', 'Bodega principal de la empresa', 'ACTIVA', 1),
('Bodega Norte', 'Medellín', 'Mediana', '2024-01-16', 'Bodega regional del norte', 'ACTIVA', 2),
('Bodega Sur', 'Cali', 'Pequeña', '2024-01-17', 'Bodega regional del sur', 'ACTIVA', 3);