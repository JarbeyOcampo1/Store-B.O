
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function LoginsForm({onSubmit, initialLogin}) {

    // Estados para almacenar los valores
    const [usuarioLogin, setUsuarioLogin] = useState("");
    const [passwordLogin, setPasswordLogin] = useState("");
    const [cargo, setCargo] = useState("");

    // useEffect para inicializar los campos si se reciben datos iniciales 
    useEffect(() => {
        try {
            if (initialLogin) {
                // Se cargan los datos iniciales en los estados
                setUsuarioLogin(initialLogin.usuarioLogin);
                setPasswordLogin(initialLogin.passwordLogin);
                setCargo(initialLogin.cargo);
            }
        } catch (error) {
            console.error("Error al cargar los datos iniciales:", error);
        }
    },[initialLogin]); // Se ejecuta cada vez que cambia el valor de initialLogin

    // Función que se ejecuta al enviar el formulario
    const handleSubmit = (event) => {
        event.preventDefault(); // Prevenir el comportamiento por defecto del formulario
        const resData = {usuarioLogin, passwordLogin, cargo}; // Crear un objeto con los datos del formulario
        onSubmit(resData); // Llamar a la función onSubmit con los datos del formulario
        setUsuarioLogin(""); 
        setPasswordLogin("");
    };

    return (
        <div className="login-form-container">
            {/* Formulario para crear un nuevo usuario */}
            <form className="login-form" onSubmit={handleSubmit}>
                {/* Título del formulario */}
                <h1 className="logins-title"> Crear un usuario </h1>
                    {/* Campos del formulario para ingresar los datos del usuario */}
                    <div className="form-group">
                        <label> Usuario: </label>
                        <input type="text" placeholder="Nombre del usuario" value={usuarioLogin} onChange={(e) => setUsuarioLogin(e.target.value)} required/>
                    </div>
                    <div className="form-group">
                        <label> Cargo: </label>
                        <input type="text" placeholder="Cargo" value={cargo} onChange={(e) => setCargo(e.target.value)} required/>
                    </div>
                    <div className="form-group">
                        <label> Contraseña: </label>
                        <input type="password" placeholder="Contraseña del usuario" value={passwordLogin} onChange={(e) => setPasswordLogin(e.target.value)} required/>
                    </div>
                    <div className="form-group">
                        <button className="form-button" type="submit"> Registrar Usuario </button>
                    </div>
                    {/* Enlace para volver a la página de inicio */}
                    <Link className="register-link" to="/"> Volver </Link>
            </form>
        </div>
    )
}

export default LoginsForm;
