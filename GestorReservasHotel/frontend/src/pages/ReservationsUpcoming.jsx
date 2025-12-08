import React from 'react';
import reservationsData from "../data/ReservationsData";
import ReservationCard from "../components/reservation-card/ReservationCard";

const ReservationsUpcoming = () => {
  const handleCancel = (id) => {
    console.log("Cancel reservation:", id);
  };

  return (
    <div className="min-h-screen bg-gray-50 py-10 px-4 sm:px-6 lg:px-8">
      <div className="max-w-5xl mx-auto">
        <div className="mb-8 border-b border-gray-200 pb-4">
          <h1 className="text-3xl font-bold text-gray-900">
            Mis Reservas
          </h1>
          <p className="mt-2 text-sm text-gray-600">
            Gestiona tus próximas estancias y consulta los detalles
          </p>
        </div>

        <div className="space-y-6">
          {reservationsData && reservationsData.length > 0 ? (
            reservationsData.map((res) => (
              <ReservationCard
                key={res.id}
                reservation={res}
                onCancel={handleCancel}
              />
            ))
          ) : (
            <div className="text-center py-12 bg-white rounded-lg shadow-sm">
              <p className="text-gray-500 text-lg">No tienes reservas próximas.</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default ReservationsUpcoming;
