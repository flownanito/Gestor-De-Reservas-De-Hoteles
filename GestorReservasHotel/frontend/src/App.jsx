import React, { useState } from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';

import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import Dashboard from './pages/Dashboard';
import ClientsPage from './pages/ClientsPage';
import EmployeesPage from './pages/EmployeesPage';
import ReservationsPage from './pages/ReservationsPage';
import ProfilePage from './pages/ProfilePage';
import Header from './components/Header';
import Footer from './components/Footer';

export default function App() {
  const [user, setUser] = useState(null);


  const handleLogout = () => {
    setUser(null);
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <Header user={user} onLogout={handleLogout} />

      <main className="pt-20 min-h-screen relative">
        <Routes>
          <Route
            path='/login'
            element={!user ? <LoginPage onLoginSuccess={setUser} /> : <Navigate to="/" />}
          />
          <Route path='/register' element={<RegisterPage />} />

          <Route path='/' element={<Dashboard />} />

          <Route path='/clients' element={user ? <ClientsPage /> : <Navigate to="/login" />} />
          <Route path='/employees' element={user ? <EmployeesPage /> : <Navigate to="/login" />} />
          <Route path='/reservations' element={user ? <ReservationsPage /> : <Navigate to="/login" />} />

          <Route path='/profile' element={user ? <ProfilePage user={user} /> : <Navigate to="/login" />} />
        </Routes>
      </main>

      <Footer />
    </div>
  )
}
