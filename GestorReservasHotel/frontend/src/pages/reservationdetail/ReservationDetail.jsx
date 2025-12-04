import React, { useState } from "react";

// importacion del Footer y Header
import Footer from "../../components/footer/Footer";
import Header from "../../components/Header";

// importacion del css
import './ReservationDetail.css';

// (ln 11-32 de ClientePages.jsx)Implementar el useEfect para que se ejecuten al cargar los componentes

const ReservationDetail = () => {
  const [checkIn, setCheckIn] = useState('');
  const [checkOut, setCheckOut] = useState('');
  const [guests, setGuests] = useState(2);

  return (
    <div className="reservation-detail-root min-h-screen bg-white">
      <>
      <Header />
      {/* Main Content */}
      <main className="max-w-7xl mx-auto px-6 py-12">
        <h1 className="text-4xl font-bold text-gray-900 mb-12">Detalles</h1>
        
        <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
          {/* Left Column - Images and Details */}
          <div className="lg:col-span-2 space-y-8">
            {/* Image Gallery */}
            <div className="grid grid-cols-3 gap-3 h-80 image-gallery">
              <div className="col-span-2 row-span-2 relative rounded-lg overflow-hidden">
                <img 
                  src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='800' height='600'%3E%3Crect fill='%233b3027' width='800' height='600'/%3E%3C/svg%3E" 
                  alt="Habitación" 
                  className="w-full h-full object-cover"
                />
                <div className="absolute bottom-4 right-4">
                  <button className="w-10 h-10 bg-white rounded-full flex items-center justify-center shadow-lg z-10">
                    <span className="text-lg">▶️</span>
                  </button>
                </div>
                <div className="absolute bottom-4 left-4 text-white">
                  <p className="text-xs uppercase tracking-wide mb-1">HABITACIÓN SUPERIOR</p>
                  <p className="text-sm">Disponible 4 noches</p>
                </div>
              </div>
              <div className="space-y-3">
                <div className="bg-gray-200 h-full rounded-lg"></div>
                <div className="bg-gray-200 h-full rounded-lg"></div>
              </div>
              <div className="space-y-3">
                <div className="bg-gray-200 h-full rounded-lg"></div>
                <div className="bg-gray-200 h-full rounded-lg relative flex items-center justify-center">
                  <span className="text-2xl font-bold text-gray-700">+2</span>
                  <p className="text-xs text-gray-600 absolute bottom-2">Imágenes</p>
                </div>
              </div>
            </div>

            {/* Room Title and Info */}
            <div>
              <h2 className="text-3xl font-bold text-gray-900 mb-2">Habitación Superior</h2>
              <p className="text-gray-600">Habitación Superior • Deliciosa</p>
            </div>

            {/* Amenities */}
            <div className="grid grid-cols-4 gap-6">
              <div className="flex flex-col items-center text-center">
                <div className="w-14 h-14 bg-gray-50 border border-gray-200 rounded-xl flex items-center justify-center mb-3">
                  <span className="text-2xl">🛏️</span>
                </div>
                <span className="text-sm text-gray-700">2 camas</span>
              </div>
              <div className="flex flex-col items-center text-center">
                <div className="w-14 h-14 bg-gray-50 border border-gray-200 rounded-xl flex items-center justify-center mb-3">
                  <span className="text-2xl">🚿</span>
                </div>
                <span className="text-sm text-gray-700">1 baño</span>
              </div>
              <div className="flex flex-col items-center text-center">
                <div className="w-14 h-14 bg-gray-50 border border-gray-200 rounded-xl flex items-center justify-center mb-3">
                  <span className="text-2xl">👥</span>
                </div>
                <span className="text-sm text-gray-700">Hospedaje</span>
              </div>
              <div className="flex flex-col items-center text-center">
                <div className="w-14 h-14 bg-gray-50 border border-gray-200 rounded-xl flex items-center justify-center mb-3">
                  <span className="text-2xl">❄️</span>
                </div>
                <span className="text-sm text-gray-700">Aire acondicionado</span>
              </div>
            </div>

            {/* Description */}
            <div>
              <h3 className="text-xl font-bold text-gray-900 mb-4">Descripción</h3>
              <p className="text-gray-600 leading-relaxed mb-4">
                Alójate en esta habitación superior en el corazón de la ciudad. Ideal para parejas o viajeros de negocios, 
                esta habitación ofrece todas las comodidades modernas que necesitas para una estancia confortable.
              </p>
              <p className="text-gray-600 leading-relaxed">
                Cuenta con una cama king size, escritorio de trabajo, TV de pantalla plana, minibar y conexión WiFi de alta velocidad. 
                El baño privado incluye ducha de efecto lluvia y artículos de tocador premium.
              </p>
            </div>

            {/* Safety and Hygiene */}
            <div>
              <h3 className="text-xl font-bold text-gray-900 mb-4">Safety and Hygiene</h3>
              <div className="grid grid-cols-2 gap-4">
                <div className="flex items-center gap-3">
                  <div className="w-5 h-5 border-2 border-gray-300 rounded flex items-center justify-center flex-shrink-0">
                    <span className="text-xs">✓</span>
                  </div>
                  <span className="text-sm text-gray-700">Daily Cleaning</span>
                </div>
                <div className="flex items-center gap-3">
                  <div className="w-5 h-5 border-2 border-gray-300 rounded flex items-center justify-center flex-shrink-0">
                    <span className="text-xs">✓</span>
                  </div>
                  <span className="text-sm text-gray-700">Fire extinguishers</span>
                </div>
                <div className="flex items-center gap-3">
                  <div className="w-5 h-5 border-2 border-gray-300 rounded flex items-center justify-center flex-shrink-0">
                    <span className="text-xs">✓</span>
                  </div>
                  <span className="text-sm text-gray-700">Disinfections of the room</span>
                </div>
                <div className="flex items-center gap-3">
                  <div className="w-5 h-5 border-2 border-gray-300 rounded flex items-center justify-center flex-shrink-0">
                    <span className="text-xs">✓</span>
                  </div>
                  <span className="text-sm text-gray-700">Smoke detectors</span>
                </div>
              </div>
            </div>

            {/* Location Map */}
            <div>
              <h3 className="text-xl font-bold text-gray-900 mb-4">Ubicación</h3>
              <div className="bg-gradient-to-br from-green-100 via-blue-50 to-blue-100 h-64 rounded-lg relative overflow-visible">
                <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 map-container">
                  <div className="w-12 h-12 bg-red-500 rounded-full flex items-center justify-center map-pin">
                    <span className="text-white text-2xl">📍</span>
                  </div>
                </div>
                <div className="absolute bottom-0 left-0 right-0 h-1/3 bg-gradient-to-t from-blue-300 to-transparent"></div>
              </div>
            </div>

            {/* Reviews */}
            <div>
              <h3 className="text-xl font-bold text-gray-900 mb-1">Reseñas <span className="text-gray-400 font-normal ml-2">★ 9.2</span></h3>
              
              <div className="grid grid-cols-2 gap-x-8 gap-y-1 mb-6 mt-4">
                <div className="flex justify-between items-center">
                  <span className="text-sm text-gray-600">Amabilidad</span>
                  <div className="flex items-center gap-2 flex-1 mx-4">
                    <div className="flex-1 h-1 bg-gray-200 rounded-full overflow-hidden">
                      <div className="h-full bg-gray-800 progress-bar" style={{width: '90%'}}></div>
                    </div>
                    <span className="text-sm text-gray-600 w-6">9.0</span>
                  </div>
                </div>
                <div className="flex justify-between items-center">
                  <span className="text-sm text-gray-600">Limpieza</span>
                  <div className="flex items-center gap-2 flex-1 mx-4">
                    <div className="flex-1 h-1 bg-gray-200 rounded-full overflow-hidden">
                      <div className="h-full bg-gray-800 progress-bar" style={{width: '90%'}}></div>
                    </div>
                    <span className="text-sm text-gray-600 w-6">9.0</span>
                  </div>
                </div>
                <div className="flex justify-between items-center">
                  <span className="text-sm text-gray-600">Comunicación</span>
                  <div className="flex items-center gap-2 flex-1 mx-4">
                    <div className="flex-1 h-1 bg-gray-200 rounded-full overflow-hidden">
                      <div className="h-full bg-gray-800 progress-bar" style={{width: '90%'}}></div>
                    </div>
                    <span className="text-sm text-gray-600 w-6">9.0</span>
                  </div>
                </div>
                <div className="flex justify-between items-center">
                  <span className="text-sm text-gray-600">Location of Property</span>
                  <div className="flex items-center gap-2 flex-1 mx-4">
                    <div className="flex-1 h-1 bg-gray-200 rounded-full overflow-hidden">
                      <div className="h-full bg-gray-800 progress-bar" style={{width: '90%'}}></div>
                    </div>
                    <span className="text-sm text-gray-600 w-6">9.0</span>
                  </div>
                </div>
                <div className="flex justify-between items-center">
                  <span className="text-sm text-gray-600">Value for Money</span>
                  <div className="flex items-center gap-2 flex-1 mx-4">
                    <div className="flex-1 h-1 bg-gray-200 rounded-full overflow-hidden">
                      <div className="h-full bg-gray-800 progress-bar" style={{width: '90%'}}></div>
                    </div>
                    <span className="text-sm text-gray-600 w-6">9.0</span>
                  </div>
                </div>
              </div>

              {/* Review Cards */}
              <div className="space-y-6">
                <div className="flex gap-4 review-card">
                  <div className="w-12 h-12 bg-gray-300 rounded-full flex-shrink-0"></div>
                  <div className="flex-1">
                    <div className="flex items-center gap-2 mb-2">
                      <h4 className="font-semibold text-gray-900">James</h4>
                      <span className="text-xs text-gray-500">Hace 2 semanas</span>
                    </div>
                    <p className="text-sm text-gray-600 leading-relaxed">
                      Excelente ubicación y servicio impecable. La habitación era espaciosa y muy limpia. 
                      El personal fue muy atento durante toda nuestra estancia. Definitivamente volveremos.
                    </p>
                  </div>
                </div>

                <div className="flex gap-4 review-card">
                  <div className="w-12 h-12 bg-gray-300 rounded-full flex-shrink-0"></div>
                  <div className="flex-1">
                    <div className="flex items-center gap-2 mb-2">
                      <h4 className="font-semibold text-gray-900">Yolanda and Carlos</h4>
                      <span className="text-xs text-gray-500">Hace 1 mes</span>
                    </div>
                    <p className="text-sm text-gray-600 leading-relaxed">
                      Pasamos una estancia maravillosa. La cama era muy cómoda y las instalaciones estaban en perfecto estado. 
                      El desayuno incluido fue delicioso y variado.
                    </p>
                  </div>
                </div>

                <div className="flex gap-4 review-card">
                  <div className="w-12 h-12 bg-gray-300 rounded-full flex-shrink-0"></div>
                  <div className="flex-1">
                    <div className="flex items-center gap-2 mb-2">
                      <h4 className="font-semibold text-gray-900">Patricia & José</h4>
                      <span className="text-xs text-gray-500">Hace 2 meses</span>
                    </div>
                    <p className="text-sm text-gray-600 leading-relaxed">
                      Hermosa habitación con vistas increíbles. El check-in fue rápido y sencillo. 
                      Muy recomendable para parejas que buscan una escapada romántica.
                    </p>
                  </div>
                </div>
              </div>

              <button className="mt-8 px-6 py-2.5 border border-gray-300 rounded-lg text-sm text-gray-700 font-medium hover:bg-gray-50 transition-colors">
                Mostrar 30 Reseñas más
              </button>
            </div>
          </div>

          {/* Right Column - Booking Card */}
          <div>
            <div className="bg-white border border-gray-200 rounded-2xl p-6 sticky top-6 shadow-sm sticky-card">
              <div className="mb-6">
                <p className="text-sm text-gray-600 mb-1">Habitación Superior • Deliciosa</p>
                <p className="text-xs text-gray-500">
                  Ingresa tus fechas de viaje para ver el precio exacto de esta estancia incluyendo impuestos y tasas
                </p>
              </div>

              <div className="space-y-4 mb-6">
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">Check-in</label>
                  <input 
                    type="date" 
                    value={checkIn}
                    onChange={(e) => setCheckIn(e.target.value)}
                    className="w-full px-4 py-2.5 border border-gray-300 rounded-lg text-sm focus:ring-2 focus:ring-gray-900 focus:border-gray-900 outline-none"
                  />
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">Check-out</label>
                  <input 
                    type="date"
                    value={checkOut}
                    onChange={(e) => setCheckOut(e.target.value)}
                    className="w-full px-4 py-2.5 border border-gray-300 rounded-lg text-sm focus:ring-2 focus:ring-gray-900 focus:border-gray-900 outline-none"
                  />
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">Huéspedes</label>
                  <select 
                    value={guests}
                    onChange={(e) => setGuests(Number(e.target.value))}
                    className="w-full px-4 py-2.5 border border-gray-300 rounded-lg text-sm focus:ring-2 focus:ring-gray-900 focus:border-gray-900 outline-none"
                  >
                    <option value="1">1 huésped</option>
                    <option value="2">2 huéspedes</option>
                    <option value="3">3 huéspedes</option>
                    <option value="4">4 huéspedes</option>
                  </select>
                </div>
              </div>

              <button className="w-full bg-gray-900 text-white py-3 rounded-lg font-semibold text-sm hover:bg-gray-800 transition-colors mb-3">
                Reservar Ahora
              </button>

              <button className="w-full border border-gray-300 text-gray-700 py-3 rounded-lg font-semibold text-sm hover:bg-gray-50 transition-colors">
                Consultar más
              </button>
            </div>
          </div>
        </div>
      </main>
      </>
    </div>
  );
};

export default ReservationDetail;