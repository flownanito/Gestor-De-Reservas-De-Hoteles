import React, { useState } from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';

import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import Dashboard from './pages/Dashboard';
import ClientsPage from './pages/ClientsPage';
import EmployeesPage from './pages/EmployeesPage';
import ReservationsPage from './pages/ReservationsPage';
import ProfilePage from './pages/ProfilePage';
import Room from './pages/Rooms';
import RoomDetails from './pages/RoomDetails';
import Header from './components/Header';
import Footer from './components/Footer';

import ReservationDetail from './pages/ReservationDetail';
import ReservationStep1 from './components/Reservationstep1';
import ReservationStep2 from './components/Reservationstep2';
import ReservationStep3 from './components/Reservationstep3';
import ReservationsUpcoming from './pages/ReservationsUpcoming';

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
          {/* Rutas Públicas */}
          <Route
            path='/login'
            element={!user ? <LoginPage onLoginSuccess={setUser} /> : <Navigate to="/" />}
          />
          <Route path='/register' element={<RegisterPage />} />

          {/* Rutas Privadas / Dashboard */}
          <Route path='/' element={<Dashboard />} />
          <Route path='/profile' element={user ? <ProfilePage user={user} /> : <Navigate to="/login" />} />

          {/* Gestión */}
          <Route path='/clients' element={<ClientsPage />} />
          <Route path='/employees' element={<EmployeesPage />} />

          {/* Reservas y Habitaciones */}
          <Route
            path='/reservations'
            element={user ? <ReservationsPage user={user} /> : <Navigate to="/login" />}
          />
          <Route path='/rooms' element={<Room />} />
          <Route path="/rooms/:id" element={<RoomDetails />} />

          {/* --- TUS RUTAS (Flujo de Reserva) --- */}
          <Route path='/reservationdetail' element={<ReservationDetail />} />
          <Route path='/reservationStep1' element={<ReservationStep1 />} />
          <Route path='/reservationStep2' element={<ReservationStep2 />} />
          <Route path='/reservationStep3' element={<ReservationStep3 />} />

          {/* --- RUTA DE TUS COMPAÑEROS (Upcoming) --- */}
          {/* He descomentado esta línea para que funcione, ya que importamos el componente arriba */}
          <Route path="/reservation-upcoming" element={<ReservationsUpcoming />} />

        </Routes>
      </main>

      <Footer />
    </div>
  )
}
