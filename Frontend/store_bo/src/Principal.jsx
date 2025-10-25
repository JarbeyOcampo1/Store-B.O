import { FaFacebook, FaInstagram, FaTwitter } from "react-icons/fa";
import "./Principal.css";
import { useState, useEffect } from "react";
import logo1 from "./components/images/logo1.png";
import closeSession from "../closeSession";
import { useNavigate, Link } from "react-router-dom";

/* Datos del carrusel */
const slides = [
  {
    title: "INFORMACIÓN DE BODEGAS",
    text: "Lo que sabemos es una gota, lo que ignoramos es un océano.",
    img: "https://grupoinmobiliariogold.com/wp-content/uploads/2021/09/ARRIENDO-BODEGA-EN-MEDELLIN-SECTOR-SAN-JOAQUIN-1-1170x633.jpeg",
    button: "Explora"
  },
  {
    title: "INFORMACIÓN DE CLIENTES",
    text: "La victoria más difícil es la victoria sobre uno mismo.",
    img: "https://plazainmobiliaria.com/wp-content/uploads/2020/10/Bod04.jpg",
    button: "Explora"
  },
  {
    title: "INFORMES",
    text: "Si he visto más, es poniéndome sobre los hombros de Gigantes.",
    img: "https://www.ayc.com.co/portal/shared/rs.php?rsid=2503",
    button: "Explora"
  }
];

function Principal() {
    /* Estado para controlar el índice actual del carrusel */
    const [currentIndex, setCurrentIndex] = useState(0);

    const navigate = useNavigate();

    /* Efecto para cambiar automáticamente las imágenes del carrusel */
    useEffect(() => {
        const interval = setInterval(() => {
            setCurrentIndex((prevIndex) => (prevIndex + 1) % slides.length);
        }, 4000);

        return () => clearInterval(interval); // Limpia el intervalo al desmontar el componente
    }, []);

    /* Desestructuración del slide actual */
    const { title, text, img, button } = slides[currentIndex];

    /* Función para cerrar sesión */
    const handleLogout = closeSession();

    const handleRedirect = () => {
        if (currentIndex === 0) navigate("/Cliente");
        if (currentIndex === 1) navigate("/Bodega");
    };

    return (
        <div>
            {/* Contenedor principal (header con logo y navegación) */}
            <div className="Container_Principal">
                <div className="logo-container">
                    <img src={logo1} className="principal-logo-image" alt="Logo Store_B.O"/>
                </div>
            {/* Barra de navegación */}
            <nav className="navbar">
                <ul>
                    <li><Link to="/Cliente">Clientes</Link></li>
                    <li><Link to="/Bodega">Bodegas</Link></li>
                    <li>Informes</li>
                    <li><button onClick={handleLogout} className="salir-btn"> Salir</button></li>
                </ul>
            </nav>
            </div>
            {/* Título animado */}
            <div className="title-container">
                <h1 className="title">
                    <span>S</span><span>T</span><span>O</span><span>R</span><span>E</span>&nbsp;&nbsp;&nbsp;<span>B</span><span>.</span><span>O</span>
                </h1>
            </div>
            {/* Carrusel automático */}
            <div className="carousel">
                <div className="carousel-text">
                    <h1>{title}</h1>
                    <p>{text}</p>
                    <button className="cta-btn" onClick={handleRedirect}>{button}</button>
                </div>
            <div className="carousel-image">
                <img src={img} alt={title} />
            </div>
            </div>
            {/* Pie de página */}
            <footer className="footer">
                <h2>Síguenos en nuestras redes sociales:</h2>
                {/* Iconos de redes sociales */}
                <div className="social-icons">
                    <a href="https://facebook.com" target="_blank" rel="noopener noreferrer"><FaFacebook /></a>
                    <a href="https://instagram.com" target="_blank" rel="noopener noreferrer"><FaInstagram /></a>
                    <a href="https://twitter.com" target="_blank" rel="noopener noreferrer"><FaTwitter /></a>
                </div>
            </footer>
        </div>
  );
};

export default Principal;
