import React, { useState, useEffect } from 'react';
import { Navigate } from 'react-router-dom';
import api from '../services/api';
import roomsData from "../data/Rooms";
import ReservationCard from "../components/reservation-card/ReservationCard";

const ReservationsUpcoming = ({ user }) => {
  const [reservations, setReservations] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (!user) return;

    const fetchMyReservations = async () => {
      try {
        setLoading(true);
        // Llamamos al endpoint real del backend
        const response = await api.get(`/reservations/client/${user.id}`);
        
        // Mapeamos los datos del backend al formato que espera la tarjeta (ReservationCard)
        const mappedData = response.data.map(res => {
          // Buscamos la imagen en nuestro roomsData local usando el ID del tipo de habitación
          const roomInfo = roomsData.find(r => r.id === res.roomType?.id);
          
          // Calcular noches
          const start = new Date(res.checkInDate);
          const end = new Date(res.checkOutDate);
          const nights = Math.max(1, Math.round((end - start) / (1000 * 60 * 60 * 24)));

          return {
            id: res.id,
            roomName: res.roomType?.name || "Habitación",
            img: roomInfo?.img || "https://images.unsplash.com/photo-1611892440504-42a792e24d32?q=80&w=2070&auto=format&fit=crop",
            checkIn: res.checkInDate,
            nights: nights,
            guests: res.numberOfGuests,
            totalPrice: res.totalPrice ? `${res.totalPrice}€` : "Pendiente"
          };
        });

        setReservations(mappedData);
      } catch (err) {
        console.error("Error fetching reservations:", err);
        setError("No se pudieron cargar tus reservas.");
      } finally {
        setLoading(false);
      }
    };

    fetchMyReservations();
  }, [user]);

  if (!user) {
    return <Navigate to="/login" />;
  }

  const handleCancel = async (id) => {
    if (!window.confirm("¿Estás seguro de que quieres cancelar esta reserva?")) return;
    
    try {
      await api.delete(`/reservations/${id}`);
      setReservations(prev => prev.filter(r => r.id !== id));
      alert("Reserva cancelada con éxito");
    } catch (err) {
      console.error("Error cancelling reservation:", err);
      alert("No se pudo cancelar la reserva.");
    }
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

        {loading ? (
          <div className="text-center py-20">
            <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-amber-700 mx-auto"></div>
            <p className="mt-4 text-gray-500">Cargando tus reservas...</p>
          </div>
        ) : error ? (
          <div className="bg-red-50 border border-red-200 text-red-700 px-6 py-4 rounded-xl">
            {error}
          </div>
        ) : (
          <div className="space-y-6">
            {reservations.length > 0 ? (
              reservations.map((res) => (
                <ReservationCard
                  key={res.id}
                  reservation={res}
                  onCancel={handleCancel}
                />
              ))
            ) : (
              <div className="text-center py-20 bg-white rounded-3xl shadow-sm border border-gray-100">
                <div className="bg-gray-50 w-20 h-20 rounded-full flex items-center justify-center mx-auto mb-4">
                  <span className="text-4xl">📅</span>
                </div>
                <h3 className="text-xl font-bold text-gray-900 mb-2">No tienes reservas</h3>
                <p className="text-gray-500 max-w-xs mx-auto">
                  Aún no has realizado ninguna reserva. ¡Explora nuestras habitaciones y planea tu viaje!
                </p>
              </div>
            )}
          </div>
        )}
      </div>
    </div>
  );
};

export default ReservationsUpcoming;
