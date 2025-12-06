import React, { useState } from "react";
import { Hotel, AlertCircle, CheckCircle } from "lucide-react";
import { useNavigate, Link } from "react-router-dom";
import api from '../services/api';
import hotelImage from '../assets/img/hotel-lobby.jpg';

export default function RegisterPage() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    dni: "",
    firstName: "",
    lastName: "",
    email: "",
    phone: "",
    password: ""
  });
  const [error, setError] = useState("");
  const [success, setSuccess] = useState(false);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    // Validación básica
    if (!formData.dni || !formData.email || !formData.password) {
        setError("Por favor, rellena los campos obligatorios (DNI, Email, Contraseña).");
        return;
    }

    try {
      // Reutilizamos el endpoint de crear cliente
      await api.post('/clients', formData);

      setSuccess(true);
      // Esperamos 2 segundos y redirigimos al Login
      setTimeout(() => {
        navigate('/login');
      }, 2000);

    } catch (err) {
      console.error("Error registro:", err);
      const msg = err.response?.data || "Error al registrarse. Revisa los datos.";
      setError(typeof msg === 'string' ? msg : "Error de conexión");
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center relative p-4 font-sans">

      {/* Fondo (Igual que Login) */}
      <div className="absolute inset-0 z-0">
        <img
          src={hotelImage}
          alt="Hotel Lobby"
          className="w-full h-full object-cover"
        />
        <div className="absolute inset-0 bg-black/50"></div>
      </div>

      {/* Tarjeta de Registro */}
      <div className="w-full max-w-2xl relative z-10"> {/* Más ancho que el login */}
        <div className="bg-white/95 backdrop-blur-sm rounded-2xl shadow-2xl p-8 border border-white/20">

          <div className="text-center mb-6">
            <h1 className="text-2xl font-bold text-gray-900">Crear Cuenta</h1>
            <p className="text-gray-600">Únete a Grand Hotel & Resort</p>
          </div>

          {/* Mensajes de Estado */}
          {error && (
            <div className="mb-4 p-3 bg-red-100 text-red-700 rounded flex items-center gap-2 text-sm">
              <AlertCircle size={16} /> {error}
            </div>
          )}
          {success && (
            <div className="mb-4 p-3 bg-green-100 text-green-700 rounded flex items-center gap-2 text-sm">
              <CheckCircle size={16} /> ¡Cuenta creada! Redirigiendo al login...
            </div>
          )}

          <form onSubmit={handleSubmit} className="space-y-4">

            {/* Grid de 2 columnas para aprovechar espacio */}
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">

                <div className="space-y-1">
                    <label className="text-xs font-medium text-gray-700">DNI *</label>
                    <input name="dni" value={formData.dni} onChange={handleChange} required
                        className="w-full rounded-md border border-gray-300 p-2 text-sm focus:ring-2 focus:ring-amber-600 outline-none" placeholder="12345678A" />
                </div>

                <div className="space-y-1">
                    <label className="text-xs font-medium text-gray-700">Teléfono</label>
                    <input name="phone" value={formData.phone} onChange={handleChange}
                        className="w-full rounded-md border border-gray-300 p-2 text-sm focus:ring-2 focus:ring-amber-600 outline-none" placeholder="+34 600..." />
                </div>

                <div className="space-y-1">
                    <label className="text-xs font-medium text-gray-700">Nombre *</label>
                    <input name="firstName" value={formData.firstName} onChange={handleChange} required
                        className="w-full rounded-md border border-gray-300 p-2 text-sm focus:ring-2 focus:ring-amber-600 outline-none" placeholder="Tu nombre" />
                </div>

                <div className="space-y-1">
                    <label className="text-xs font-medium text-gray-700">Apellidos *</label>
                    <input name="lastName" value={formData.lastName} onChange={handleChange} required
                        className="w-full rounded-md border border-gray-300 p-2 text-sm focus:ring-2 focus:ring-amber-600 outline-none" placeholder="Tus apellidos" />
                </div>
            </div>

            <div className="space-y-1">
                <label className="text-xs font-medium text-gray-700">Correo Electrónico *</label>
                <input type="email" name="email" value={formData.email} onChange={handleChange} required
                    className="w-full rounded-md border border-gray-300 p-2 text-sm focus:ring-2 focus:ring-amber-600 outline-none" placeholder="nombre@ejemplo.com" />
            </div>

            <div className="space-y-1">
                <label className="text-xs font-medium text-gray-700">Contraseña *</label>
                <input type="password" name="password" value={formData.password} onChange={handleChange} required
                    className="w-full rounded-md border border-gray-300 p-2 text-sm focus:ring-2 focus:ring-amber-600 outline-none" placeholder="******" />
            </div>

            <button
              type="submit"
              className="w-full bg-amber-700 hover:bg-amber-800 text-white font-bold py-3 px-4 rounded-md shadow transition-colors mt-4"
            >
              Registrarse
            </button>
          </form>

          <div className="mt-4 text-center">
             <span className="text-sm text-gray-600">¿Ya tienes cuenta? </span>
             <Link to="/login" className="text-sm font-semibold text-amber-700 hover:underline">
               Inicia sesión aquí
             </Link>
          </div>

        </div>
      </div>
    </div>
  );
}
