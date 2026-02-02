import api from '../services/api';
import React, { useState } from "react";
import { Hotel, AlertCircle } from "lucide-react";
import { useNavigate, Link } from "react-router-dom";
import hotelImage from '../assets/img/hotel-lobby.jpg';

export default function LoginPage({ onLoginSuccess }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      console.log("Enviando datos al backend...", { email, password });

      // Llamada real al backend
      const response = await api.post('/auth/login', {
        email: email,
        password: password
      });

      // Si llegamos aquí, el login fue exitoso
      console.log("Usuario recibido:", response.data);

      // Guardamos el usuario real en el estado de App.jsx
      onLoginSuccess(response.data);
      navigate('/'); // Nos vamos al dashboard

    } catch (err) {
      console.error("Error en login:", err);
      if (err.response && err.response.status === 401) {
        setError("Usuario o contraseña incorrectos.");
      } else {
        setError("Error de conexión con el servidor.");
      }
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center relative p-4 font-sans">

      {/* --- IMAGEN DE FONDO --- */}
      <div className="absolute inset-0 z-0">
        <img
          src={hotelImage}
          alt="Hotel Lobby"
          className="w-full h-full object-cover"
          onError={(e) => { e.target.style.display = 'none'; }} // Si falla, se queda el fondo negro
        />
        {/* Capa oscura para que resalte el formulario */}
        <div className="absolute inset-0 bg-black/50"></div>
      </div>

      {/* --- TARJETA DE LOGIN --- */}
      <div className="w-full max-w-md relative z-10">
        <div className="bg-white/95 backdrop-blur-sm rounded-2xl shadow-2xl p-8 border border-white/20">

          {/* Header del Formulario */}
          <div className="text-center mb-8">
            <div className="inline-flex items-center justify-center w-16 h-16 bg-amber-700 rounded-full mb-4 shadow-lg">
              <Hotel className="w-8 h-8 text-white" />
            </div>
            <h1 className="text-2xl font-bold text-gray-900 mb-2">Portal del Hotel</h1>
            <p className="text-gray-600">Acceso para clientes y personal</p>
          </div>

          {/* Mensaje de Error */}
          {error && (
            <div className="mb-4 p-3 bg-red-100 border border-red-400 text-red-700 rounded flex items-center gap-2 text-sm">
              <AlertCircle size={16} />
              {error}
            </div>
          )}

          {/* Formulario */}
          <form onSubmit={handleSubmit} className="space-y-6">
            <div className="space-y-2">
              <label htmlFor="email" className="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70 text-gray-700">
                Correo electrónico
              </label>
              <input
                id="email"
                type="email"
                placeholder="usuario@hotel.com"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
                className="flex h-10 w-full rounded-md border border-gray-300 bg-white px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-2 focus:ring-amber-600 focus:border-transparent disabled:cursor-not-allowed disabled:opacity-50 transition-all"
              />
            </div>

            <div className="space-y-2">
              <label htmlFor="password" className="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70 text-gray-700">
                Contraseña
              </label>
              <input
                id="password"
                type="password"
                placeholder="••••••••"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
                className="flex h-10 w-full rounded-md border border-gray-300 bg-white px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-2 focus:ring-amber-600 focus:border-transparent disabled:cursor-not-allowed disabled:opacity-50 transition-all"
              />
            </div>

            <button
              type="submit"
              className="w-full bg-amber-700 hover:bg-amber-800 text-white font-bold py-2.5 px-4 rounded-md shadow transition-colors duration-200 focus:outline-none focus:ring-2 focus:ring-amber-500 focus:ring-offset-2"
            >
              Iniciar sesión
            </button>
          </form>

          {/* Link a Registro */}
          <div className="mt-4 text-center">
            <span className="text-sm text-gray-600">¿No tienes cuenta? </span>
            <Link to="/register" className="text-sm font-semibold text-amber-700 hover:underline">
              Regístrate aquí
            </Link>
          </div>
        </div>

        {/* Footer inferior */}
        <div className="text-center mt-6">
          <p className="text-white font-semibold shadow-black drop-shadow-md">Grand Hotel & Resort</p>
          <p className="text-white/80 text-sm drop-shadow-md">Sistema de Gestión Hotelera</p>
        </div>
      </div>
    </div>
  );
}
