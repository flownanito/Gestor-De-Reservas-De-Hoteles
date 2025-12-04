import React, { useState } from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';

import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import Dashboard from './pages/Dashboard';
import ClientsPage from './pages/ClientsPage';
import EmployeesPage from './pages/EmployeesPage';
import ReservationsPage from './pages/ReservationsPage';
import Header from './components/Header';
import Footer from './components/Footer';

export default function App() {
  // Guarda el usuario inicialmente null, nadie logueado
  const [user, setUser] = useState(null);

  // Cerrar sesión
  const handleLogout = () => {
    setUser(null);
  };

  // si no hay usuario mostramos el login
  if (!user) {
    return <LoginPage onLoginSuccess={setUser} />;
  }

  return (
    <div className="app-container">
      <Header user={user} onLogout={handleLogout} />

      <main>
        <Routes>
          <Route path='/login' element={<Navigate to="/" />} />
          <Route path='/register' element={<RegisterPage />} />

          <Route path='/' element={<Dashboard />} />
          <Route path='/clients' element={<ClientsPage />} />
          <Route path='/employees' element={<EmployeesPage />} />
          <Route path='/reservations' element={<ReservationsPage />} />
        </Routes>
      </main>

      <Footer />
    </div>

  )
}
