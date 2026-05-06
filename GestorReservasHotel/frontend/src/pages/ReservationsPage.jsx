import React, { useState, useEffect } from "react";
import { createReservation, generateInvoice } from "../services/reservationsApi";
import { useNavigate, useLocation } from "react-router-dom";

// Forced re-build to clear possible cache issues
import ReservationStep1 from "../components/Reservationstep1";
import ReservationStep2 from "../components/Reservationstep2";
import ReservationStep3 from "../components/Reservationstep3";

const ReservationsPage = ({ user }) => {
  const navigate = useNavigate();
  const location = useLocation();
  const [currentStep, setCurrentStep] = useState(1);

  // Unificamos nombres: roomTypeId + guests + checkIn/checkOut
  const [reservationData, setReservationData] = useState({
    roomTypeId: location.state?.preselectedRoomId || 1,
    checkIn: location.state?.checkIn || "",
    checkOut: location.state?.checkOut || "",
    guests: location.state?.guests || 1,
    isEditing: location.state?.isEditing || false,
    reservationId: location.state?.reservationId || null,

    clientData: {
      nombre: user?.name || user?.firstName || "",
      apellido: user?.lastName || "",
      email: user?.email || "",
      telefono: user?.phone || ""
    },

    paymentMethod: null
  });

  // Función para avanzar de paso guardando datos
  const handleNextStep = (newData) => {
    setReservationData((prev) => ({ ...prev, ...newData }));
    setCurrentStep((prev) => prev + 1);
  };

  // Función para volver atrás
  const handlePrevStep = () => {
    setCurrentStep((prev) => prev - 1);
  };

  // Función para confirmar la reserva final
  const handleSubmitFinal = async (paymentData) => {
    const clientId = user?.id ?? 1;

    if (!reservationData.roomTypeId) throw new Error("Falta roomTypeId.");
    if (!reservationData.checkIn || !reservationData.checkOut) throw new Error("Faltan fechas.");

    const payload = {
      client: { id: clientId },
      roomType: { id: Number(reservationData.roomTypeId) },
      reservationDate: new Date().toISOString(),
      checkInDate: reservationData.checkIn,
      checkOutDate: reservationData.checkOut,
      status: "CONFIRMED",
      numberOfGuests: Number(reservationData.guests ?? 1),
      totalPrice: 0,
    };

    let created;
    if (reservationData.isEditing && reservationData.reservationId) {
      // MODO EDICIÓN: Usamos PUT
      const response = await api.put(`/reservations/${reservationData.reservationId}`, payload);
      created = response.data;
    } else {
      // MODO CREACIÓN: Usamos POST
      created = await createReservation(payload);
    }

    // 2) Generar/Actualizar factura
    const invoiceDto = await generateInvoice(created.id);

    // 3) Devolver DTO al Step3 para mostrar modal
    return invoiceDto;
  };

  return (
    <div className="max-w-4xl mx-auto py-8 px-4">
      <h1 className="text-3xl font-bold text-gray-900 mb-6">Nueva Reserva</h1>

      <div className="flex justify-between mb-8 text-sm font-medium text-gray-500 border-b pb-4">
        <span className={currentStep >= 1 ? "text-amber-700" : ""}>1. Fechas y Habitación</span>
        <span className={currentStep >= 2 ? "text-amber-700" : ""}>2. Tus Datos</span>
        <span className={currentStep >= 3 ? "text-amber-700" : ""}>3. Pago y Confirmación</span>
      </div>

      <div className="bg-white p-6 rounded-xl shadow-sm border border-gray-100">
        {currentStep === 1 && (
          <ReservationStep1
            initialData={reservationData}
            onNext={handleNextStep}
          />
        )}

        {currentStep === 2 && (
          <ReservationStep2
            initialData={reservationData}  // importante: Step2 usa initialData
            onNext={handleNextStep}
            onBack={handlePrevStep}
          />
        )}

        {currentStep === 3 && (
          <ReservationStep3
            initialData={reservationData}  // importante: Step3 usa initialData
            onSubmit={handleSubmitFinal}
            onBack={handlePrevStep}
          />
        )}
      </div>
    </div>
  );
};

export default ReservationsPage;
