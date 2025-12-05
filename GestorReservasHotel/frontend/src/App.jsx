
import { Routes, Route } from 'react-router-dom';

import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import Dashboard from './pages/Dashboard';
import ClientsPage from './pages/ClientsPage';
import EmployeesPage from './pages/EmployeesPage';
import ReservationsPage from './pages/ReservationsPage';
import Header from './components/Header';
import Footer from './components/footer/Footer';
import ReservationDetail from './pages/reservationdetail/ReservationDetail';
import ReservationStep1 from './pages/reservationstep1/Reservationstep1';
import ReservationStep2 from './pages/reservationstep2/Reservationstep2';
import ReservationStep3 from './pages/reservationstep3/Reservationstep3';

export default function App() {
  return (
    <>
      <Header />

      <main>
        <Routes>
          {/* Rutas Públicas (Login y Register) */}
          <Route path='/login' element={<LoginPage />} />
          <Route path='/register' element={<RegisterPage />} />

          {/* Rutas de Gestión */}
          <Route path='/' element={<Dashboard />} />
          <Route path='/clients' element={<ClientsPage />} />
          <Route path='/employees' element={<EmployeesPage />} />
          <Route path='/reservations' element={<ReservationsPage />} />
          <Route path='/reservationdetail' element={<ReservationDetail />} />
          <Route path='/reservationstep1' element={<ReservationStep1 />} />
          <Route path='/reservationstep2' element={<ReservationStep2 />} />
          <Route path='/reservationstep3' element={<ReservationStep3 />} />
        </Routes>
      </main>

      <Footer />
    </>

  )
}
