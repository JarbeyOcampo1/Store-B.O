import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./InicioSesionForm.css"; 

function InicioSesionForm () {
    // Estados para manejar datos
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);
    const [usuarioLogin, setUsuarioLogin] = useState("");
    const [passwordLogin, setPasswordLogin] = useState("");

    // Usamos el hook useNavigate para navegar a otras rutas de la aplicación.
    const navigate = useNavigate(); 

    // Función para manejar el envío del formulario.
    const handleSubmit = async (event) => {
        event.preventDefault(); // Evitar recargar la pagina 
        setLoading(true); // Muestra que se está cargando
        setError(""); 
        
        try {
            // Validar que los campos no estén vacíos
            if (!usuarioLogin.trim() || !passwordLogin.trim()) {
                setError("Por favor, complete todos los campos.");
                return;
            }

            // peticion a la API para validar el inicio
            const response = await axios.post("http://localhost:8080/api/auth/login", {
                usuarioLogin, 
                passwordLogin,
            });
   
            // verificar si la respuesta es exitosa y contiene el token
            if (response.status === 200 && response.data.token){
                console.log("Inicio de sesion exitoso");
                localStorage.setItem("token", response.data.token); // guardar el token en el storage local
                localStorage.setItem("usuario", response.data.usuario); // guardar el usuario
                localStorage.setItem("cargo", response.data.cargo); // guardar el cargo
                
                // Limpiar el formulario
                setUsuarioLogin("");
                setPasswordLogin("");
                setError("");
                
                navigate("/Principal"); // Redirigir a la página principal
            }
        } catch (error) {
            console.error("Error en login:", error);
            if (error.response?.status === 401) {
                setError("Credenciales incorrectas. Verifique su usuario y contraseña.");
            } else if (error.response?.status === 500) {
                setError("Error del servidor. Intente más tarde.");
            } else if (error.code === 'NETWORK_ERROR' || !error.response) {
                setError("Error de conexión. Verifique que el servidor esté funcionando.");
            } else {
                setError("Error inesperado. Intente nuevamente.");
            }
        } finally {
            setLoading(false); // Finaliza el estado de carga
        }
    };

    return (
        <div className="login-form-container">
            {/* Formulario de inicio de sesión */}
            <form onSubmit={handleSubmit} className="login-form">
                {/* Título del formulario */}
                <h2 className="logins-title"> Inicio de sesion </h2>
                {/* Mostrar mensaje de error si existe */}
                {error && <p className="error-message">{error}</p>}
                {/* Campos del formulario para ingresar usuario y contraseña */}
                <div className="form-group">
                    <label htmlFor="usuario"> Usuario: </label>
                    <input 
                        type="text" 
                        name="usuarioLogin" 
                        placeholder="Nombre del usuario" 
                        value={usuarioLogin}
                        onChange={(e) => setUsuarioLogin(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password"> Contraseña: </label>
                    <input 
                        type="password" 
                        name="passwordLogin" 
                        placeholder="Contraseña" 
                        value={passwordLogin}
                        onChange={(e) => setPasswordLogin(e.target.value)}
                        required
                    />
                </div>
                <div className="login-actions">
                    <button type="button" className="cancel-button" onClick={() => navigate("/Logins")}> Cancelar </button>
                    <button type="submit" className="form-button" disabled={loading}> {loading ? "Cargando...":"Iniciar Sesión" } </button>
                </div>
                {/* Enlace para redirigir al usuario a la página de registro */}
                <a href="#" className="register-link" onClick={(e) => {e.preventDefault(); navigate("/Logins"); //Redirige a la pagina de registro 
                }}> Registrarse </a>
            </form>
        </div>
    );

};

export default InicioSesionForm;