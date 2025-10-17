// Script de prueba para el sistema de login
const axios = require('axios');

const BASE_URL = 'http://localhost:8080';

// Función para probar el login
async function testLogin() {
    console.log('🧪 Iniciando pruebas del sistema de login...\n');

    try {
        // Probar login con usuario gerente
        console.log('1. Probando login con usuario GERENTE...');
        const gerenteResponse = await axios.post(`${BASE_URL}/api/auth/login`, {
            usuarioLogin: 'admin',
            passwordLogin: 'admin123'
        });
        
        console.log('✅ Login gerente exitoso:');
        console.log('   - Token:', gerenteResponse.data.token ? 'Generado ✓' : 'No generado ✗');
        console.log('   - Usuario:', gerenteResponse.data.usuario);
        console.log('   - Cargo:', gerenteResponse.data.cargo);
        console.log('');

        // Probar login con usuario empleado
        console.log('2. Probando login con usuario EMPLEADO...');
        const empleadoResponse = await axios.post(`${BASE_URL}/api/auth/login`, {
            usuarioLogin: 'empleado1',
            passwordLogin: 'empleado123'
        });
        
        console.log('✅ Login empleado exitoso:');
        console.log('   - Token:', empleadoResponse.data.token ? 'Generado ✓' : 'No generado ✗');
        console.log('   - Usuario:', empleadoResponse.data.usuario);
        console.log('   - Cargo:', empleadoResponse.data.cargo);
        console.log('');

        // Probar login con credenciales incorrectas
        console.log('3. Probando login con credenciales incorrectas...');
        try {
            await axios.post(`${BASE_URL}/api/auth/login`, {
                usuarioLogin: 'usuario_inexistente',
                passwordLogin: 'password_incorrecto'
            });
            console.log('❌ Error: Debería haber fallado con credenciales incorrectas');
        } catch (error) {
            if (error.response?.status === 401) {
                console.log('✅ Correctamente rechazado con credenciales incorrectas');
            } else {
                console.log('❌ Error inesperado:', error.message);
            }
        }
        console.log('');

        // Probar registro de nuevo usuario
        console.log('4. Probando registro de nuevo usuario...');
        const newUserResponse = await axios.post(`${BASE_URL}/api/logins`, {
            usuarioLogin: 'testuser',
            passwordLogin: 'testpass123',
            cargo: 'EMPLEADO'
        });
        
        console.log('✅ Usuario registrado exitosamente:');
        console.log('   - ID:', newUserResponse.data.loginID);
        console.log('   - Usuario:', newUserResponse.data.usuarioLogin);
        console.log('   - Cargo:', newUserResponse.data.cargo);
        console.log('');

        console.log('🎉 Todas las pruebas del sistema de login completadas exitosamente!');

    } catch (error) {
        console.error('❌ Error en las pruebas:', error.message);
        if (error.response) {
            console.error('   - Status:', error.response.status);
            console.error('   - Data:', error.response.data);
        }
    }
}

// Ejecutar las pruebas
testLogin();