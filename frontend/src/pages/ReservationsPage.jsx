import React, { useState } from 'react';
import { useLocation } from "react-router-dom";
import api from '../services/api';

// Forced re-build to clear possible cache issues
import ReservationStep1 from "../components/Reservationstep1";
import ReservationStep2 from "../components/Reservationstep2";
import ReservationStep3 from "../components/Reservationstep3";

const ReservationsPage = ({ user }) => {
  const location = useLocation();
  const [currentStep, setCurrentStep] = useState(1);
  const [reservationData, setReservationData] = useState({
    roomId: location.state?.preselectedRoomId || null,
    roomNumber: location.state?.preselectedRoomNumber || null,
    checkIn: '',
    checkOut: '',
    guests: 1,

    clientData: {
      nombre: user?.name || '',
      apellido: user?.lastName || '',
      email: user?.email || '',
      telefono: user?.phone || ''
    },

    paymentMethod: null
  });

  // Función para avanzar de paso guardando datos
  const handleNextStep = (newData) => {
    setReservationData({ ...reservationData, ...newData });
    setCurrentStep(prev => prev + 1);
  };

  // Función para volver atrás
  const handlePrevStep = () => {
    setCurrentStep(prev => prev - 1);
  };

  // Función para confirmar la reserva final
  const handleSubmitFinal = async (paymentData) => {
    // 1. Formateamos los datos para que coincidan con el modelo Reservation del backend
    const finalBooking = {
      client: { id: user?.id },
      room: { id: reservationData.roomId },
      reservationDate: new Date().toISOString().slice(0, 19),
      checkInDate: reservationData.checkIn,
      checkOutDate: reservationData.checkOut,
      condition: 'Confirmada',
      numberOfGuests: parseInt(reservationData.guests) || 1,
      totalPrice: parseFloat(paymentData.total) || 0
    };

    console.log("ENVIANDO RESERVA AL BACKEND:", finalBooking);

    try {
      // 2. Llamada real al backend
      await api.post('/reservations', finalBooking);
      console.log("¡Reserva creada con éxito!");

    } catch (error) {
      console.error("Error creando reserva:", error);
      throw error; // Re-lanzamos para que ReservationStep3 maneje el error
    }
  };

  return (
    <div className="max-w-4xl mx-auto py-8 px-4">
      <h1 className="text-3xl font-bold text-gray-900 mb-6">Nueva Reserva</h1>

      {/* Barra de Progreso simple */}
      <div className="flex justify-between mb-8 text-sm font-medium text-gray-500 border-b pb-4">
        <span className={currentStep >= 1 ? "text-amber-700" : ""}>1. Fechas y Habitación</span>
        <span className={currentStep >= 2 ? "text-amber-700" : ""}>2. Tus Datos</span>
        <span className={currentStep >= 3 ? "text-amber-700" : ""}>3. Pago y Confirmación</span>
      </div>

      {/* Renderizado Condicional de los Pasos */}
      <div className="bg-white p-6 rounded-xl shadow-sm border border-gray-100">

        {/* PASO 1 */}
        {currentStep === 1 && (
          <ReservationStep1 initialData={reservationData} onNext={handleNextStep} />
        )}

        {/* PASO 2 */}
        {currentStep === 2 && (
          <ReservationStep2 data={reservationData} onNext={handleNextStep} onBack={handlePrevStep} />
        )}

        {/* PASO 3 */}
        {currentStep === 3 && (
          <ReservationStep3 data={reservationData} onSubmit={handleSubmitFinal} onBack={handlePrevStep} />
        )}

      </div>
    </div>
  );
};

export default ReservationsPage;
