import React, { useState, useEffect } from 'react';
import { useRoomTypes } from "../hooks/useRoomTypes";

const ReservationStep1 = ({ initialData, onNext }) => {

  const { rooms, loading, error } = useRoomTypes();

  // Inicializamos el estado con lo que venga del padre (o valores por defecto)
  const [formData, setFormData] = useState({
    roomTypeId: initialData?.roomTypeId ?? 1,
    checkIn: initialData?.checkIn || "",
    checkOut: initialData?.checkOut || "",
    guests: initialData?.guests ?? 1,
    solicitudes: initialData?.solicitudes || ""
  });

  const selectedRoom = rooms.find((r) => r.id === Number(formData.roomTypeId));
  const maxGuests = Number(selectedRoom?.people ?? 1);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = () => {
    // Validación presencia de fechas
    if (!formData.checkIn || !formData.checkOut) {
      alert("Por favor selecciona las fechas");
      return;
    }

    // Validación coherencia (Salida > Entrada)
    const start = new Date(formData.checkIn);
    const end = new Date(formData.checkOut);

    if (end <= start) {
      alert("La fecha de salida debe ser posterior a la fecha de entrada.");
      return;
    }

    // Validación capacidad máxima
    if (formData.guests > maxGuests) {
      alert(`Esta habitación permite máximo ${maxGuests} huésped(es).`);
      return;
    }

    // Si todo está correcto, avanzar
    onNext(formData);
  };

  // Obtener fecha de hoy en formato YYYY-MM-DD para el atributo min
  const today = new Date().toISOString().split('T')[0];

  return (
    <div className="min-h-screen bg-gray-50 font-sans">
      <main className="max-w-5xl mx-auto px-4 py-8">

        {/* Title Section */}
        <div className="mb-8 text-center sm:text-left">
          <h1 className="text-3xl font-bold text-gray-900 mb-2">Nueva Reserva</h1>
          <p className="text-gray-600">Completa la información para realizar tu reserva</p>
        </div>

        {/* Progress Bar (Estática para el Paso 1) */}
        <div className="flex items-center justify-center mb-12">
          <div className="flex items-center w-full max-w-3xl">
            {/* Step 1 - Active */}
            <div className="flex flex-col items-center flex-1">
              <div className="w-10 h-10 bg-amber-700 rounded-full flex items-center justify-center text-white font-bold text-lg mb-2 shadow-md ring-4 ring-amber-100">
                1
              </div>
              <span className="text-sm font-bold text-gray-900">Detalles</span>
            </div>
            <div className="flex-1 h-0.5 bg-gray-300 mx-2"></div>
            {/* Step 2 */}
            <div className="flex flex-col items-center flex-1 opacity-50">
              <div className="w-10 h-10 bg-gray-200 rounded-full flex items-center justify-center text-gray-500 font-bold text-lg mb-2">
                2
              </div>
              <span className="text-sm text-gray-500">Información</span>
            </div>
            <div className="flex-1 h-0.5 bg-gray-300 mx-2"></div>
            {/* Step 3 */}
            <div className="flex flex-col items-center flex-1 opacity-50">
              <div className="w-10 h-10 bg-gray-200 rounded-full flex items-center justify-center text-gray-500 font-bold text-lg mb-2">
                3
              </div>
              <span className="text-sm text-gray-500">Pago</span>
            </div>
          </div>
        </div>

        {/* Form Card */}
        <div className="bg-white rounded-2xl shadow-sm p-8 border border-gray-100">
          <div className="mb-6 pb-6 border-b border-gray-100">
            <h2 className="text-xl font-bold text-gray-900 mb-1">Detalles de la reserva</h2>
            {initialData.roomNumber && (
              <p className="text-amber-700 font-semibold mb-2 bg-amber-50 p-2 rounded-lg border border-amber-100 inline-block">
                Habitación Seleccionada: {initialData.roomNumber}
              </p>
            )}
            <p className="text-gray-500 text-sm">Selecciona las fechas y tipo de habitación</p>
          </div>

          <div className="space-y-6">
            {/* Tipo de habitación */}
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Tipo de habitación
              </label>

              {error && (
                <p className="text-sm text-red-600 mb-2">
                  Error cargando habitaciones: {error}
                </p>
              )}

              <select
                name="roomTypeId"
                value={formData.roomTypeId}
                onChange={(e) => {
                  const newRoomTypeId = Number(e.target.value);
                  const newSelected = rooms.find((r) => r.id === newRoomTypeId);
                  const newMax = Number(newSelected?.people ?? 1);

                  setFormData((prev) => ({
                    ...prev,
                    roomTypeId: newRoomTypeId,
                    guests: Math.min(prev.guests ?? 1, newMax), // recorta si es mayor
                  }));
                }}
                className="w-full px-4 py-3 border border-gray-300 rounded-lg text-gray-700 bg-white"
              >
                {loading && <option>Cargando...</option>}

                {!loading && rooms.map((r) => (
                  <option key={r.id} value={r.id} disabled={r.availableRooms === 0}>
                    {r.name}{r.availableRooms !== null && r.availableRooms !== undefined
                      ? ` (${r.availableRooms} disponibles)`
                      : ""}
                  </option>
                ))}
              </select>
            </div>

            {/* Fechas */}
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">Check-in</label>
                <input
                  type="date" 
                  name="checkIn"
                  min={today}
                  value={formData.checkIn}
                  onChange={handleChange}
                  className="w-full px-4 py-3 border border-gray-300 rounded-lg text-gray-700 focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none transition-all"
                />
              </div>
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">Check-out</label>
                <input
                  type="date" 
                  name="checkOut"
                  min={formData.checkIn || today}
                  value={formData.checkOut}
                  onChange={handleChange}
                  className="w-full px-4 py-3 border border-gray-300 rounded-lg text-gray-700 focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none transition-all"
                />
              </div>
            </div>

            {/* Huéspedes */}
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Número de Huéspedes
              </label>
              <select
                name="guests"
                value={formData.guests}
                onChange={(e) => setFormData({ ...formData, guests: Number(e.target.value) })}
                className="w-full px-4 py-3 border border-gray-300 rounded-lg bg-white"
              >
                {Array.from({ length: maxGuests }, (_, i) => i + 1).map((num) => (
                  <option key={num} value={num}>
                    {num} persona{num > 1 ? "s" : ""}
                  </option>
                ))}
              </select>

              <p className="mt-2 text-xs text-gray-500">
                Máximo para esta habitación: <b>{maxGuests}</b> huésped{maxGuests > 1 ? "es" : ""}
              </p>
            </div>

            {/* Solicitudes */}
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">Solicitudes especiales (Opcional)</label>
              <textarea
                name="solicitudes"
                value={formData.solicitudes}
                onChange={handleChange}
                rows="3"
                placeholder="Ej: Cama extra, vistas al mar, piso alto..."
                className="w-full px-4 py-3 border border-gray-300 rounded-lg text-gray-700 focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none resize-none transition-all"
              ></textarea>
            </div>

            {/* Botón Continuar */}
            <div className="flex justify-end pt-6 border-t border-gray-100">
              <button
                onClick={handleSubmit}
                className="px-8 py-3 bg-amber-700 text-white rounded-lg font-semibold hover:bg-amber-800 transition-all shadow-md transform hover:-translate-y-0.5"
              >
                Continuar
              </button>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
};

export default ReservationStep1;
