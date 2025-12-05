import React, { useState } from 'react';
// importacion del css
import './Reservationstep2.css';

const ReservationStep2 = () => {
  const [formData, setFormData] = useState({
    nombre: '',
    apellido: '',
    telefono: '',
    email: ''
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Form submitted:', formData);
  };

  return (
    <div className="reservation-step-2 min-h-screen flex flex-col">
      {/* Progress Bar */}
      <div className="bg-white border-b w-full">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-4 sm:py-6">
          <div className="flex items-center justify-between max-w-2xl mx-auto gap-2 sm:gap-4">
            <div className="flex flex-col items-center flex-shrink-0">
              <div className="w-8 sm:w-10 h-8 sm:h-10 bg-orange-500 rounded-full flex items-center justify-center text-white font-bold mb-1 sm:mb-2 text-xs sm:text-base">
                ✓
              </div>
              <span className="text-xs sm:text-sm font-medium text-gray-900">Detalles</span>
            </div>
            <div className="flex-1 h-0.5 bg-orange-500 mx-1 sm:mx-4"></div>
            <div className="flex flex-col items-center flex-shrink-0">
              <div className="w-8 sm:w-10 h-8 sm:h-10 bg-orange-500 rounded-full flex items-center justify-center text-white font-bold mb-1 sm:mb-2 text-xs sm:text-base">2</div>
              <span className="text-xs sm:text-sm font-medium text-gray-900">Información</span>
            </div>
            <div className="flex-1 h-0.5 bg-gray-300 mx-1 sm:mx-4"></div>
            <div className="flex flex-col items-center flex-shrink-0">
              <div className="w-8 sm:w-10 h-8 sm:h-10 bg-gray-300 rounded-full flex items-center justify-center text-white font-bold mb-1 sm:mb-2 text-xs sm:text-base">3</div>
              <span className="text-xs sm:text-gray-500">Pago</span>
            </div>
          </div>
        </div>
      </div>

      {/* Main Content */}
      <main className="flex-1 w-full max-w-full mx-auto px-4 sm:px-6 lg:px-8 py-6 sm:py-8 flex flex-col justify-center items-center">
        <div className="text-center mb-6 sm:mb-8 w-full">
          <p className="text-sm sm:text-base text-gray-600">Completa la información para realizar tu reserva</p>
        </div>

        <div className="bg-white rounded-lg shadow-sm p-4 sm:p-6 lg:p-8 w-full lg:max-w-2xl">
          <h2 className="text-xl sm:text-2xl lg:text-3xl font-bold text-gray-900 mb-4 sm:mb-6 lg:mb-8 text-center">Información Personal</h2>
          <p className="text-sm sm:text-base text-gray-600 mb-6 sm:mb-8 text-center">Confirma los datos de contacto</p>

          <form onSubmit={handleSubmit} className="space-y-4 sm:space-y-6">
            <div>
              <label htmlFor="nombre" className="block text-xs sm:text-sm font-medium text-gray-700 mb-2">
                Nombre *
              </label>
              <input
                type="text"
                id="nombre"
                name="nombre"
                value={formData.nombre}
                onChange={handleChange}
                required
                className="w-full px-3 sm:px-4 py-2 sm:py-3 text-sm sm:text-base border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500 focus:border-orange-500 outline-none transition-all"
                placeholder="Ingresa tu nombre"
              />
            </div>

            <div>
              <label htmlFor="apellido" className="block text-xs sm:text-sm font-medium text-gray-700 mb-2">
                Apellido *
              </label>
              <input
                type="text"
                id="apellido"
                name="apellido"
                value={formData.apellido}
                onChange={handleChange}
                required
                className="w-full px-3 sm:px-4 py-2 sm:py-3 text-sm sm:text-base border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500 focus:border-orange-500 outline-none transition-all"
                placeholder="Ingresa tu apellido"
              />
            </div>

            <div>
              <label htmlFor="telefono" className="block text-xs sm:text-sm font-medium text-gray-700 mb-2">
                Número de Teléfono *
              </label>
              <input
                type="tel"
                id="telefono"
                name="telefono"
                value={formData.telefono}
                onChange={handleChange}
                required
                className="w-full px-3 sm:px-4 py-2 sm:py-3 text-sm sm:text-base border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500 focus:border-orange-500 outline-none transition-all"
                placeholder="+34 123 456 789"
              />
            </div>

            <div>
              <label htmlFor="email" className="block text-xs sm:text-sm font-medium text-gray-700 mb-2">
                Correo Electrónico *
              </label>
              <input
                type="email"
                id="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                required
                className="w-full px-3 sm:px-4 py-2 sm:py-3 text-sm sm:text-base border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500 focus:border-orange-500 outline-none transition-all"
                placeholder="tu@email.com"
              />
            </div>

            <div className="pt-2 sm:pt-4">
              <p className="text-xs text-gray-500 mb-4 sm:mb-6">
                * Campos requeridos (Opcional)
              </p>
            </div>

            <div className="flex flex-col sm:flex-row gap-3 sm:gap-4 pt-4">
              <button
                type="button"
                className="w-full sm:flex-1 px-4 sm:px-6 py-2.5 sm:py-3 border border-gray-300 rounded-lg text-xs sm:text-sm text-gray-700 font-semibold hover:bg-gray-50 transition-colors"
              >
                Atrás
              </button>
              <button
                type="submit"
                className="w-full sm:flex-1 px-4 sm:px-6 py-2.5 sm:py-3 bg-orange-500 text-xs sm:text-sm text-white rounded-lg font-semibold hover:bg-orange-600 transition-colors"
              >
                Continuar
              </button>
            </div>
          </form>
        </div>
      </main>
    </div>
  );
};

export default ReservationStep2;