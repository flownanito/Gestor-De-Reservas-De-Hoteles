import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Hotel, Menu, Phone, User, LogOut } from "lucide-react";

export default function Header({ user, onLogout }) {
  const navigate = useNavigate();
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);

  const handleLogout = () => {
    if (onLogout) onLogout();
    navigate('/login');
  };

  return (
    // 'fixed' hace que la barra se quede pegada arriba aunque hagas scroll
    <nav className="fixed top-0 left-0 right-0 z-50 bg-white/95 backdrop-blur-sm shadow-sm h-20">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 h-full">
        <div className="flex justify-between items-center h-full">

          {/* --- LOGO --- */}
          <Link to="/" className="flex items-center gap-2 group">
            <div className="bg-amber-700 p-2 rounded-lg group-hover:bg-amber-800 transition-colors">
              <Hotel className="w-6 h-6 text-white" />
            </div>
            <div className="flex flex-col">
              <span className="text-gray-900 font-bold leading-none text-lg">Grand Hotel</span>
              <span className="text-amber-700 text-sm font-medium leading-none">& Resort</span>
            </div>
          </Link>

          {/* --- MENÚ ESCRITORIO --- */}
          <div className="hidden md:flex items-center gap-8">
            <Link to="/" className="text-gray-600 hover:text-amber-700 font-medium transition-colors">Inicio</Link>
            <Link to="/rooms" className="text-gray-600 hover:text-amber-700 font-medium transition-colors">Habitaciones</Link>

            {/* Solo Admin/Empleados ven esto */}
            {user && (user.role === 'ADMIN' || user.role === 'EMPLOYEE') && (
              <>
                <Link to="/employees" className="text-gray-600 hover:text-amber-700 font-medium transition-colors">Empleados</Link>
                <Link to="/clients" className="text-gray-600 hover:text-amber-700 font-medium transition-colors">Clientes</Link>
              </>
            )}
          </div>

          {/* Botones Derecha */}
          <div className="flex items-center gap-4">

            {/* SECCIÓN USUARIO CORREGIDA */}
            {user ? (
              <div className="flex items-center gap-3 pl-4 border-l border-gray-200">

                {/* 1. AHORA ES UN LINK AL PERFIL */}
                <Link
                  to="/profile"
                  className="flex items-center gap-2 hover:bg-gray-50 py-1 px-2 rounded-lg transition-colors group"
                  title="Ir a mi perfil"
                >
                  {/* Avatar Circular con la inicial */}
                  <div className="w-8 h-8 bg-amber-100 text-amber-700 rounded-full flex items-center justify-center font-bold border border-amber-200 group-hover:bg-amber-700 group-hover:text-white transition-colors">
                    {user.name ? user.name.charAt(0).toUpperCase() : 'U'}
                  </div>

                  {/* Nombre y Rol */}
                  <div className="text-right hidden xl:block">
                      <p className="text-sm font-medium text-gray-900 group-hover:text-amber-700 transition-colors">
                        {user.name}
                      </p>
                      <p className="text-[10px] text-gray-500 uppercase tracking-wider leading-none">
                        {user.role}
                      </p>
                  </div>
                </Link>

                {/* 2. BOTÓN DE SALIR (Separado) */}
                <button
                  onClick={handleLogout}
                  className="p-2 text-gray-400 hover:text-red-600 hover:bg-red-50 rounded-full transition-all"
                  title="Cerrar Sesión"
                >
                  <LogOut size={18} />
                </button>
              </div>
            ) : (
              // Botón si NO estás logueado
              <Link to="/login" className="bg-amber-700 hover:bg-amber-800 text-white px-5 py-2.5 rounded-lg font-medium transition-colors shadow-sm flex items-center gap-2">
                <User size={18} />
                <span>Entrar / Reservar</span>
              </Link>
            )}
          </div>
        </div>
      </div>
    </nav>
  );
}
