import React, { useState } from 'react';
import './Reservationstep1.css';

const NuevaReserva = () => {
  const [formData, setFormData] = useState({
    tipoHabitacion: 'Habitación Estándar',
    checkIn: '15/12/2025',
    checkOut: '18/12/2025',
    huespedes: '1',
    solicitudes: ''
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = () => {
    console.log('Form submitted:', formData);
  };

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Main Content */}
      <main className="max-w-[100rem] mx-auto px-4 py-8">
        {/* Title Section */}
        <div className="mb-12">
          <h1 className="text-4xl font-bold text-gray-900 mb-3">Nueva Reserva</h1>
          <p className="text-gray-600">Completa la información para realizar tu reserva</p>
        </div>

        {/* Progress Bar */}
        <div className="flex items-center justify-center mb-16">
          <div className="flex items-center w-full max-w-3xl">
            {/* Step 1 - Active */}
            <div className="flex flex-col items-center flex-1">
              <div className="w-14 h-14 bg-orange-600 rounded-full flex items-center justify-center text-white font-bold text-xl mb-3 shadow-lg">
                1
              </div>
              <span className="text-sm font-semibold text-gray-900">Detalles</span>
            </div>

            {/* Line 1 */}
            <div className="flex-1 h-0.5 bg-gray-300 -mt-8 mx-2"></div>

            {/* Step 2 */}
            <div className="flex flex-col items-center flex-1">
              <div className="w-14 h-14 bg-gray-300 rounded-full flex items-center justify-center text-white font-bold text-xl mb-3">
                2
              </div>
              <span className="text-sm text-gray-500">Información</span>
            </div>

            {/* Line 2 */}
            <div className="flex-1 h-0.5 bg-gray-300 -mt-8 mx-2"></div>

            {/* Step 3 */}
            <div className="flex flex-col items-center flex-1">
              <div className="w-14 h-14 bg-gray-300 rounded-full flex items-center justify-center text-white font-bold text-xl mb-3">
                3
              </div>
              <span className="text-sm text-gray-500">Pago</span>
            </div>
          </div>
        </div>

        {/* Form Card */}
        <div className="bg-white rounded-3xl shadow-lg p-10 border border-gray-100">
          <div className="mb-8">
            <h2 className="text-2xl font-bold text-gray-900 mb-2">Detalles de la reserva</h2>
            <p className="text-gray-600 text-sm">Selecciona las fechas y tipo de habitación</p>
          </div>

          <div className="space-y-6">
            {/* Tipo de habitación */}
            <div>
              <label className="block text-sm font-semibold text-gray-900 mb-3">
                Tipo de habitación
              </label>
              <select
                name="tipoHabitacion"
                value={formData.tipoHabitacion}
                onChange={handleChange}
                className="w-full px-4 py-3 border border-gray-300 rounded-lg text-gray-700 text-sm focus:ring-2 focus:ring-orange-500 focus:border-orange-500 outline-none bg-white appearance-none cursor-pointer"
                style={{
                  backgroundImage: `url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='12' height='12' viewBox='0 0 12 12'%3E%3Cpath fill='%23666' d='M6 9L1 4h10z'/%3E%3C/svg%3E")`,
                  backgroundRepeat: 'no-repeat',
                  backgroundPosition: 'right 1rem center'
                }}
              >
                <option>Habitación Estándar</option>
                <option>Habitación Superior</option>
                <option>Suite</option>
                <option>Suite Presidencial</option>
              </select>
            </div>

            {/* Check-in and Check-out */}
            <div className="grid grid-cols-2 gap-6">
              <div>
                <label className="block text-sm font-semibold text-gray-900 mb-3">
                  Check-in
                </label>
                <input
                  type="text"
                  name="checkIn"
                  value={formData.checkIn}
                  onChange={handleChange}
                  placeholder="15/12/2025"
                  className="w-full px-4 py-3 border border-gray-300 rounded-lg text-gray-700 text-sm focus:ring-2 focus:ring-orange-500 focus:border-orange-500 outline-none"
                />
              </div>
              <div>
                <label className="block text-sm font-semibold text-gray-900 mb-3">
                  Check-out
                </label>
                <input
                  type="text"
                  name="checkOut"
                  value={formData.checkOut}
                  onChange={handleChange}
                  placeholder="18/12/2025"
                  className="w-full px-4 py-3 border border-gray-300 rounded-lg text-gray-700 text-sm focus:ring-2 focus:ring-orange-500 focus:border-orange-500 outline-none"
                />
              </div>
            </div>

            {/* Número de Huéspedes */}
            <div>
              <label className="block text-sm font-semibold text-gray-900 mb-3">
                Número de Huéspedes
              </label>
              <select
                name="huespedes"
                value={formData.huespedes}
                onChange={handleChange}
                className="w-full px-4 py-3 border border-gray-300 rounded-lg text-gray-700 text-sm focus:ring-2 focus:ring-orange-500 focus:border-orange-500 outline-none bg-white appearance-none cursor-pointer"
                style={{
                  backgroundImage: `url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='12' height='12' viewBox='0 0 12 12'%3E%3Cpath fill='%23666' d='M6 9L1 4h10z'/%3E%3C/svg%3E")`,
                  backgroundRepeat: 'no-repeat',
                  backgroundPosition: 'right 1rem center'
                }}
              >
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
              </select>
            </div>

            {/* Solicitudes especiales */}
            <div>
              <label className="block text-sm font-semibold text-gray-900 mb-3">
                Solicitudes especiales (Opcional)
              </label>
              <textarea
                name="solicitudes"
                value={formData.solicitudes}
                onChange={handleChange}
                rows="4"
                placeholder="Ej: Cama extra, vistas al mar, piso alto,..."
                className="w-full px-4 py-3 border border-gray-300 rounded-lg text-gray-700 text-sm focus:ring-2 focus:ring-orange-500 focus:border-orange-500 outline-none resize-none"
              ></textarea>
            </div>

            {/* Button */}
            <div className="flex justify-end pt-4">
              <button
                onClick={handleSubmit}
                className="px-10 py-3 bg-orange-600 text-white rounded-full font-semibold text-sm hover:bg-orange-700 transition-all shadow-md hover:shadow-lg transform hover:scale-105"
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

export default NuevaReserva;