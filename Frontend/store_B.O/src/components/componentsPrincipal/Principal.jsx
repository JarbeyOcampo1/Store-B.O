import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./Principal.css";

function Principal() {
    const [usuario, setUsuario] = useState("");
    const [cargo, setCargo] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        // Verificar si hay token en localStorage
        const token = localStorage.getItem("token");
        const usuarioStorage = localStorage.getItem("usuario");
        const cargoStorage = localStorage.getItem("cargo");

        if (!token) {
            // Si no hay token, redirigir al login
            navigate("/");
            return;
        }

        setUsuario(usuarioStorage || "");
        setCargo(cargoStorage || "");
    }, [navigate]);

    const handleLogout = () => {
        // Limpiar localStorage
        localStorage.removeItem("token");
        localStorage.removeItem("usuario");
        localStorage.removeItem("cargo");
        // Redirigir al login
        navigate("/");
    };

    const handleGestionClientes = () => {
        navigate("/clientes");
    };

    const handleGestionBodegas = () => {
        navigate("/bodegas");
    };

    const handleInformes = () => {
        navigate("/informes");
    };

    return (
        <div className="principal-container">
            <header className="principal-header">
                <h1>Store B.O - Sistema de Gestión</h1>
                <div className="user-info">
                    <span>Bienvenido, {usuario}</span>
                    <span className="cargo-badge">{cargo}</span>
                    <button onClick={handleLogout} className="logout-btn">
                        Cerrar Sesión
                    </button>
                </div>
            </header>

            <main className="principal-main">
                <div className="welcome-section">
                    <h2>Panel de Control</h2>
                    <p>Bienvenido al sistema de gestión Store B.O</p>
                    <div className="user-details">
                        <p><strong>Usuario:</strong> {usuario}</p>
                        <p><strong>Cargo:</strong> {cargo}</p>
                        <p><strong>Permisos:</strong> {cargo === 'GERENTE' ? 'Acceso completo' : 'Acceso limitado'}</p>
                    </div>
                    <p>Selecciona una opción para gestionar el sistema:</p>
                </div>

                <div className="menu-grid">
                    <div className="menu-card" onClick={handleGestionClientes}>
                        <div className="menu-icon">👥</div>
                        <h3>Gestión de Clientes</h3>
                        <p>Administrar información de clientes</p>
                    </div>

                    <div className="menu-card" onClick={handleGestionBodegas}>
                        <div className="menu-icon">🏪</div>
                        <h3>Gestión de Bodegas</h3>
                        <p>Administrar bodegas y almacenes</p>
                    </div>

                    <div className="menu-card" onClick={handleInformes}>
                        <div className="menu-icon">📊</div>
                        <h3>Informes</h3>
                        <p>Generar reportes del sistema</p>
                    </div>

                    {cargo === "GERENTE" && (
                        <div className="menu-card admin-only">
                            <div className="menu-icon">⚙️</div>
                            <h3>Administración</h3>
                            <p>Funciones exclusivas de gerente</p>
                        </div>
                    )}
                </div>
            </main>
        </div>
    );
}

export default Principal;