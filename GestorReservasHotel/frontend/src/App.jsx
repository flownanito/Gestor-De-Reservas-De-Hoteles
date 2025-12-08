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

          <Route path='/profile' element={user ? <ProfilePage user={user} /> : <Navigate to="/login" />} />
          <Route path='/clients' element={<ClientsPage />} />
          <Route path='/employees' element={<EmployeesPage />} />
          <Route
            path='/reservations'
            element={user ? <ReservationsPage user={user} /> : <Navigate to="/login" />}
          />
          <Route path='/rooms' element={<Room />} />
          <Route path="/rooms/:id" element={<RoomDetails />} />
          <Route path='/reservationdetail' element={<ReservationDetail />} />
          <Route path='/reservationStep1' element={<ReservationStep1 />} />
          <Route path='/reservationStep2' element={<ReservationStep2 />} />
          <Route path='/reservationStep3' element={<ReservationStep3 />} />
          <Route path='/reservationStep3' element={<ReservationStep3 />} />

        </Routes>
      </main>

      <Footer />
    </div>
  )
}
