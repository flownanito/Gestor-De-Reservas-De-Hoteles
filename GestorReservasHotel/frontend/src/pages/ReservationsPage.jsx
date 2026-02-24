import React, { useState, useEffect } from "react";
import { createReservation } from "../services/reservationsApi";
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
    roomTypeId: 1,      // <-- importante
    checkIn: "",
    checkOut: "",
    guests: 1,

    clientData: {
      nombre: user?.name || "",
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
    try {
      const clientId = user?.id ?? 1; // mientras no tengas login real, usa 1

      // validación rápida
      if (!reservationData.roomTypeId) {
        throw new Error("Falta roomTypeId (tipo de habitación).");
      }
      if (!reservationData.checkIn || !reservationData.checkOut) {
        throw new Error("Faltan fechas de check-in/check-out.");
      }

      const payload = {
        client: { id: clientId },
        employee: null,
        roomType: { id: Number(reservationData.roomTypeId) },

        reservationDate: new Date().toISOString(), // LocalDateTime en backend
        checkInDate: reservationData.checkIn,      // LocalDate
        checkOutDate: reservationData.checkOut,    // LocalDate

        condition: "CONFIRMED",
        numberOfGuests: Number(reservationData.guests ?? 1),

        // OJO: si tu Step3 tiene "total" perfecto.
        // Si no, pon un cálculo real luego.
        totalPrice: Number(paymentData?.total ?? 0)
      };

      console.log("POST /api/reservations payload:", payload);

      await createReservation(payload);

      alert("¡Reserva realizada con éxito!");
      navigate("/"); // o /reservations/upcoming
    } catch (error) {
      console.error("Error creando reserva:", error);
      alert(`Hubo un error al reservar: ${error.message}`);
      throw error; // para que Step3 pueda capturarlo si quieres
    }
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
