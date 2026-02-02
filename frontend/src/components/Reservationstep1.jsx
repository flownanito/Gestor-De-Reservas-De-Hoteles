import React, { useState, useEffect } from 'react';

const ReservationStep1 = ({ initialData, onNext }) => {
  // Inicializamos el estado con lo que venga del padre (o valores por defecto)
  const [formData, setFormData] = useState({
    tipoHabitacion: initialData?.tipoHabitacion || 'Habitación Estándar',
    checkIn: initialData?.checkIn || '',
    checkOut: initialData?.checkOut || '',
    huespedes: initialData?.huespedes || '1',
    solicitudes: initialData?.solicitudes || ''
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = () => {
    // Validaciones simples antes de avanzar
    if (!formData.checkIn || !formData.checkOut) {
      alert("Por favor selecciona las fechas");
      return;
    }
    // ¡PASAMOS LOS DATOS AL PADRE PARA AVANZAR!
    onNext(formData);
  };

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
            <p className="text-gray-500 text-sm">Selecciona las fechas y tipo de habitación</p>
          </div>

          <div className="space-y-6">
            {/* Tipo de habitación */}
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Tipo de habitación
              </label>
              <select
                name="tipoHabitacion"
                value={formData.tipoHabitacion}
                onChange={handleChange}
                className="w-full px-4 py-3 border border-gray-300 rounded-lg text-gray-700 focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none bg-white cursor-pointer transition-all"
              >
                <option>Habitación Estándar</option>
                <option>Habitación Superior</option>
                <option>Suite</option>
                <option>Suite Presidencial</option>
              </select>
            </div>

            {/* Fechas */}
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">Check-in</label>
                <input
                  type="date" // Cambiado a type="date" para que salga el calendario
                  name="checkIn"
                  value={formData.checkIn}
                  onChange={handleChange}
                  className="w-full px-4 py-3 border border-gray-300 rounded-lg text-gray-700 focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none transition-all"
                />
              </div>
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">Check-out</label>
                <input
                  type="date" // Cambiado a type="date"
                  name="checkOut"
                  value={formData.checkOut}
                  onChange={handleChange}
                  className="w-full px-4 py-3 border border-gray-300 rounded-lg text-gray-700 focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none transition-all"
                />
              </div>
            </div>

            {/* Huéspedes */}
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">Número de Huéspedes</label>
              <select
                name="huespedes"
                value={formData.huespedes}
                onChange={handleChange}
                className="w-full px-4 py-3 border border-gray-300 rounded-lg text-gray-700 focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none bg-white cursor-pointer transition-all"
              >
                {[1, 2, 3, 4, 5].map(num => (
                  <option key={num} value={num}>{num} persona{num > 1 ? 's' : ''}</option>
                ))}
              </select>
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
