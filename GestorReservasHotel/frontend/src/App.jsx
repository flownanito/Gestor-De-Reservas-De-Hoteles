import React, { useState } from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';

import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import Dashboard from './pages/Dashboard';
import ClientsPage from './pages/ClientsPage';
import EmployeesPage from './pages/EmployeesPage';
import ReservationsPage from './pages/ReservationsPage';
import Footer from './components/Footer';
import Header from './components/Header';
import ProfilePage from './pages/ProfilePage';

export default function App() {
  const [user, setUser] = useState(null);

  const handleLogout = () => {
    setUser(null);
  };

  // --- 1. HE BORRADO EL BLOQUE "if (!user)..." ---
  // Ahora la app se renderiza completa siempre.

  return (
    <div className="min-h-screen bg-gray-50">

      {/* 2. EL HEADER: Ahora siempre se muestra, haya usuario o no */}
      <Header user={user} onLogout={handleLogout} />

      {/* 3. MAIN con padding-top para que el Header fijo no tape nada */}
      <main className="pt-20 min-h-screen relative">
        <Routes>
          {/* Ruta Login: Si ya tengo usuario, me manda al inicio. Si no, muestra el Login */}
          <Route
            path='/login'
            element={!user ? <LoginPage onLoginSuccess={setUser} /> : <Navigate to="/" />}
          />

          <Route path='/register' element={<RegisterPage />} />

          {/* Ruta Principal (Dashboard/Hero) visible para todos */}
          <Route path='/' element={<Dashboard />} />

          {/* Rutas Protegidas (Solo si hay usuario, si no -> Login) */}
          <Route
            path='/clients'
            element={user ? <ClientsPage /> : <Navigate to="/login" />}
          />
          <Route
            path='/employees'
            element={user ? <EmployeesPage /> : <Navigate to="/login" />}
          />
          <Route
            path='/reservations'
            element={user ? <ReservationsPage /> : <Navigate to="/login" />}
          />
          <Route
            path='/profile'
            element={user ? <ProfilePage /> : <Navigate to="/login" />}
          />
        </Routes>
      </main>

      <Footer />
    </div>
  );
}
