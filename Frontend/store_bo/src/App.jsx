import { BrowserRouter, Routes, Route } from 'react-router-dom'
import InicioSesionForm from './components/componentsLogin/InicioSesionForm.jsx'
import Principal from './Principal.jsx'
import Logins from './components/componentsLogin/Logins.jsx'
import Cliente from './components/componentsCliente/Cliente.jsx'
import Bodega from './components/componentsBodega/Bodega.jsx'

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<InicioSesionForm />} />
        <Route path='/Principal' element={<Principal />} />
        <Route path='/Logins' element={<Logins />} />
        <Route path='/Cliente' element={<Cliente />} />
        <Route path='/Bodega' element={<Bodega />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
