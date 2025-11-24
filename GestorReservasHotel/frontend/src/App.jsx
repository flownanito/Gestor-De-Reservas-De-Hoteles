
import './App.css'
import { Routes, Route } from 'react-router-dom';

const LoginPage = () => <h1>1. Login Page</h1>;
const RegisterPage = () => <h1>2. Register Page</h1>;
const Dashboard = () => <h1>3. Dashboard</h1>

export default function App() {
  return (
    <Routes>
      {/* Rutas Públicas (Login y Register) */}
      <Route path="/Login" element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage />} />

      {/* Ruta Principal / Home */}
      <Route paht="/" element={<Dashboard />} />
    </Routes>
  )
}
