import React, { useState } from 'react';

const ReservationStep2 = ({ initialData, onNext, onBack }) => {
  // Inicializamos el estado.
  // Nota: Si en tu 'reservationData' (padre) usas 'clientData', aquí accedemos a eso.
  const data = initialData?.clientData || {};

  const [formData, setFormData] = useState({
    nombre: data.nombre || '',
    apellido: data.apellido || '',
    telefono: data.telefono || '',
    email: data.email || ''
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Validación básica
    if (!formData.nombre || !formData.email) {
        alert("Por favor rellena los campos obligatorios");
        return;
    }

    // Enviamos los datos al padre (encapsulados en clientData para mantener orden)
    onNext({ clientData: formData });
  };

  return (
    <div className="w-full max-w-2xl mx-auto">
        <div className="bg-white p-6 sm:p-8">
          <h2 className="text-2xl font-bold text-gray-900 mb-2 text-center">Información Personal</h2>
          <p className="text-gray-600 mb-8 text-center">Confirma los datos de contacto para la reserva</p>

          <form onSubmit={handleSubmit} className="space-y-6">

            {/* Grid Nombre y Apellido */}
            <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
                <div>
                    <label htmlFor="nombre" className="block text-sm font-medium text-gray-700 mb-2">
                        Nombre *
                    </label>
                    <input
                        type="text"
                        name="nombre"
                        value={formData.nombre}
                        onChange={handleChange}
                        required
                        className="w-full px-4 py-3 text-sm border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none transition-all"
                        placeholder="Tu nombre"
                    />
                </div>
                <div>
                    <label htmlFor="apellido" className="block text-sm font-medium text-gray-700 mb-2">
                        Apellido *
                    </label>
                    <input
                        type="text"
                        name="apellido"
                        value={formData.apellido}
                        onChange={handleChange}
                        required
                        className="w-full px-4 py-3 text-sm border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none transition-all"
                        placeholder="Tus apellidos"
                    />
                </div>
            </div>

            {/* Grid Teléfono y Email */}
            <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
                <div>
                    <label htmlFor="telefono" className="block text-sm font-medium text-gray-700 mb-2">
                        Teléfono *
                    </label>
                    <input
                        type="tel"
                        name="telefono"
                        value={formData.telefono}
                        onChange={handleChange}
                        required
                        className="w-full px-4 py-3 text-sm border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none transition-all"
                        placeholder="+34 600..."
                    />
                </div>
                <div>
                    <label htmlFor="email" className="block text-sm font-medium text-gray-700 mb-2">
                        Correo Electrónico *
                    </label>
                    <input
                        type="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        required
                        className="w-full px-4 py-3 text-sm border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none transition-all"
                        placeholder="nombre@ejemplo.com"
                    />
                </div>
            </div>

            <div className="pt-4 border-t border-gray-100 mt-6">
              <div className="flex flex-col sm:flex-row gap-4">
                <button
                  type="button"
                  onClick={onBack}
                  className="w-full sm:flex-1 px-6 py-3 border border-gray-300 rounded-lg text-gray-700 font-semibold hover:bg-gray-50 transition-colors"
                >
                  Atrás
                </button>
                <button
                  type="submit"
                  className="w-full sm:flex-1 px-6 py-3 bg-amber-700 text-white rounded-lg font-semibold hover:bg-amber-800 transition-colors shadow-md"
                >
                  Continuar al Pago
                </button>
              </div>
            </div>

          </form>
        </div>
    </div>
  );
};

export default ReservationStep2;
