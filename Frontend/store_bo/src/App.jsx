import { BrowserRouter, Routes, Route } from 'react-router-dom'
import InicioSesionForm from './components/componentsLogin/InicioSesionForm.jsx'
import Principal from './Principal.jsx'
import Logins from './components/componentsLogin/Logins.jsx'

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<InicioSesionForm />} />
        <Route path='/Principal' element={<Principal />} />
        <Route path='/Logins' element={<Logins />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
