import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Principal from './components/componentsPrincipal/Principal.jsx';
import InicioSesionForm from './components/componentsLogin/InicioSesionForm.jsx';
import Logins from './components/componentsLogin/Logins.jsx';

function App() {

  return (
  // Configuración de las rutas de la aplicación
  // Se utiliza BrowserRouter para manejar el enrutamiento
  // Se definen las rutas y los componentes que se renderizarán para cada una
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<InicioSesionForm />} />
        <Route path="/Logins" element={<Logins />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App
