import { useEffect, useMemo, useState } from 'react'

const API_BASE = 'http://localhost:8080'

function LoginForm({ onSuccess }) {
  const [usuarioLogin, setUsuarioLogin] = useState('')
  const [passwordLogin, setPasswordLogin] = useState('')
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')

  const submit = async (e) => {
    e.preventDefault()
    setLoading(true)
    setError('')
    try {
      const res = await fetch(`${API_BASE}/api/auth/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ usuarioLogin, passwordLogin }),
      })
      if (!res.ok) {
        const data = await res.json().catch(() => ({}))
        throw new Error(data.error || 'Error de autenticación')
      }
      const data = await res.json()
      onSuccess({ token: data.token, usuario: data.usuario, cargo: data.cargo })
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <form onSubmit={submit} style={{ display: 'grid', gap: 8, maxWidth: 360 }}>
      <h2>Iniciar sesión</h2>
      <input
        placeholder="Usuario"
        value={usuarioLogin}
        onChange={(e) => setUsuarioLogin(e.target.value)}
      />
      <input
        type="password"
        placeholder="Contraseña"
        value={passwordLogin}
        onChange={(e) => setPasswordLogin(e.target.value)}
      />
      <button type="submit" disabled={loading}>
        {loading ? 'Ingresando…' : 'Ingresar'}
      </button>
      {!!error && <div style={{ color: 'red' }}>{error}</div>}
    </form>
  )
}

function useAuth() {
  const [auth, setAuth] = useState(() => {
    const raw = localStorage.getItem('auth')
    return raw ? JSON.parse(raw) : null
  })

  useEffect(() => {
    if (auth) localStorage.setItem('auth', JSON.stringify(auth))
    else localStorage.removeItem('auth')
  }, [auth])

  const headers = useMemo(() => {
    if (!auth?.token) return { 'Content-Type': 'application/json' }
    return {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${auth.token}`,
    }
  }, [auth])

  return { auth, setAuth, headers }
}

function Clientes({ headers, canWrite }) {
  const [clientes, setClientes] = useState([])
  const [nuevo, setNuevo] = useState({ nombreCliente: '', cedulaCliente: '' })

  const fetchAll = async () => {
    const res = await fetch(`${API_BASE}/api/clientes`, { headers })
    const data = await res.json()
    setClientes(data)
  }

  useEffect(() => {
    fetchAll()
  }, [])

  const crear = async () => {
    const res = await fetch(`${API_BASE}/api/clientes`, {
      method: 'POST',
      headers,
      body: JSON.stringify(nuevo),
    })
    if (res.ok) fetchAll()
    else alert('No autorizado')
  }

  const eliminar = async (id) => {
    const res = await fetch(`${API_BASE}/api/clientes/${id}`, {
      method: 'DELETE',
      headers,
    })
    if (res.ok) fetchAll()
    else alert('No autorizado')
  }

  return (
    <div style={{ border: '1px solid #ddd', padding: 12, borderRadius: 8 }}>
      <h3>Clientes</h3>
      <ul>
        {clientes.map((c) => (
          <li key={c.clienteID}>
            {c.nombreCliente || 'Sin nombre'} - {c.cedulaCliente || 'Sin cédula'}
            {canWrite && (
              <button style={{ marginLeft: 8 }} onClick={() => eliminar(c.clienteID)}>
                Eliminar
              </button>
            )}
          </li>
        ))}
      </ul>
      {canWrite && (
        <div style={{ display: 'flex', gap: 8 }}>
          <input
            placeholder="Nombre"
            value={nuevo.nombreCliente}
            onChange={(e) => setNuevo({ ...nuevo, nombreCliente: e.target.value })}
          />
          <input
            placeholder="Cédula"
            value={nuevo.cedulaCliente}
            onChange={(e) => setNuevo({ ...nuevo, cedulaCliente: e.target.value })}
          />
          <button onClick={crear}>Crear</button>
        </div>
      )}
    </div>
  )
}

function App() {
  const { auth, setAuth, headers } = useAuth()
  const isGerente = auth?.cargo?.toLowerCase() === 'gerente'

  if (!auth) {
    return <LoginForm onSuccess={setAuth} />
  }

  return (
    <div style={{ display: 'grid', gap: 16, padding: 16 }}>
      <div style={{ display: 'flex', justifyContent: 'space-between' }}>
        <div>
          Sesión: <strong>{auth.usuario}</strong> ({auth.cargo})
        </div>
        <button onClick={() => setAuth(null)}>Salir</button>
      </div>
      <Clientes headers={headers} canWrite={isGerente} />
      {!isGerente && (
        <div style={{ color: '#666' }}>
          Acceso limitado. Solo puedes ver información.
        </div>
      )}
    </div>
  )
}

export default App
