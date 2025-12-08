import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // 1. Importar para redirigir
import { CheckCircle } from 'lucide-react'; // Icono de éxito

const ReservationStep3 = ({ initialData, onSubmit, onBack }) => {
  const navigate = useNavigate();
  const [showSuccessModal, setShowSuccessModal] = useState(false); // 2. Estado del modal

  const [paymentData, setPaymentData] = useState({
    habitacion: initialData?.tipoHabitacion || 'Habitación Estándar',
    checkIn: initialData?.checkIn || '15/12/2025',
    checkOut: initialData?.checkOut || '18/12/2025',
    huespedes: initialData?.huespedes || 2,
    subtotal: 252,
    impuestos: 50,
    total: 302,
    cardName: '',
    cardNumber: '',
    expiry: '',
    cvv: ''
  });

  const handleChange = (e) => {
    setPaymentData({ ...paymentData, [e.target.name]: e.target.value });
  };

  const handleFinalSubmit = async () => {
    // Validación simple
    if (!paymentData.cardName || !paymentData.cardNumber) {
      alert("Por favor introduce los datos de pago");
      return;
    }

    try {
      // Aquí llamarías al onSubmit del padre que llama al Backend
      await onSubmit(paymentData);

      // 3. ¡ÉXITO! MOSTRAR EL MODAL
      setShowSuccessModal(true);

      // 4. ESPERAR 2 SEGUNDOS Y REDIRIGIR AL INICIO
      setTimeout(() => {
        navigate('/');
      }, 2000);

    } catch (error) {
      console.error("Error:", error);
      alert("Hubo un error en el pago.");
    }
  };

  return (
    <div className="min-h-screen bg-gray-50 font-sans relative">

      {/* --- MODAL DE ÉXITO (Ventana Flotante) --- */}
      {showSuccessModal && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/50 backdrop-blur-sm animate-in fade-in duration-300">
          <div className="bg-white rounded-2xl shadow-2xl p-8 max-w-sm w-full text-center transform scale-100 animate-in zoom-in-95 duration-300">
            <div className="w-20 h-20 bg-green-100 rounded-full flex items-center justify-center mx-auto mb-6">
              <CheckCircle className="w-10 h-10 text-green-600" />
            </div>
            <h2 className="text-2xl font-bold text-gray-900 mb-2">¡Pago Exitoso!</h2>
            <p className="text-gray-600 mb-6">
              Tu reserva ha sido confirmada correctamente. Te hemos enviado un email con los detalles.
            </p>
            <div className="w-full bg-gray-100 h-1.5 rounded-full overflow-hidden">
              <div className="h-full bg-green-500 animate-[progress_2s_linear_forwards]" style={{ width: '0%' }}></div>
            </div>
            <p className="text-xs text-gray-400 mt-2">Redirigiendo al inicio...</p>
          </div>
        </div>
      )}

      <main className="max-w-7xl mx-auto px-4 py-8">
        <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">

          {/* COLUMNA IZQUIERDA - DETALLES PAGO */}
          <div className="lg:col-span-2">
            <div className="bg-white rounded-xl shadow-sm p-8 border border-gray-100">
              <h2 className="text-2xl font-bold text-gray-900 mb-6">Pago y Confirmación</h2>
              <p className="text-gray-600 mb-8">Ingresa los detalles de pago</p>

              <div className="space-y-6">
                {/* Resumen Amarillo */}
                <div className="bg-amber-50 border-l-4 border-amber-400 p-4 rounded-r-lg">
                  <h3 className="font-semibold text-gray-900 mb-3">Resumen de Reserva</h3>
                  <div className="space-y-2 text-sm text-gray-700">
                    <div className="flex justify-between">
                      <span>Habitación:</span>
                      <span className="font-medium">{paymentData.habitacion}</span>
                    </div>
                    <div className="flex justify-between">
                      <span>Check-in:</span>
                      <span className="font-medium">{paymentData.checkIn}</span>
                    </div>
                    <div className="flex justify-between">
                      <span>Check-out:</span>
                      <span className="font-medium">{paymentData.checkOut}</span>
                    </div>
                    <div className="flex justify-between">
                      <span>Huéspedes:</span>
                      <span className="font-medium">{paymentData.huespedes} personas</span>
                    </div>
                  </div>
                </div>

                <div className="border-t border-gray-100 pt-6">
                  <h3 className="font-semibold text-gray-900 mb-4">Detalles de Tarjeta</h3>

                  <div className="space-y-4">
                    <div>
                      <label className="block text-sm font-medium text-gray-700 mb-2">Nombre del Titular *</label>
                      <input
                        type="text"
                        name="cardName"
                        value={paymentData.cardName}
                        onChange={handleChange}
                        className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none transition-all"
                        placeholder="Nombre completo"
                      />
                    </div>

                    <div>
                      <label className="block text-sm font-medium text-gray-700 mb-2">Número de Tarjeta *</label>
                      <input
                        type="text"
                        name="cardNumber"
                        value={paymentData.cardNumber}
                        onChange={handleChange}
                        className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none transition-all"
                        placeholder="0000 0000 0000 0000"
                        maxLength="19"
                      />
                    </div>

                    <div className="grid grid-cols-2 gap-4">
                      <div>
                        <label className="block text-sm font-medium text-gray-700 mb-2">Vencimiento *</label>
                        <input
                          type="text"
                          name="expiry"
                          value={paymentData.expiry}
                          onChange={handleChange}
                          className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none transition-all"
                          placeholder="MM/AA"
                          maxLength="5"
                        />
                      </div>
                      <div>
                        <label className="block text-sm font-medium text-gray-700 mb-2">CVV *</label>
                        <input
                          type="text"
                          name="cvv"
                          value={paymentData.cvv}
                          onChange={handleChange}
                          className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none transition-all"
                          placeholder="123"
                          maxLength="3"
                        />
                      </div>
                    </div>
                  </div>
                </div>

                <div className="border-t border-gray-100 pt-6">
                  <div className="flex items-start gap-3">
                    <input type="checkbox" id="terms" className="mt-1 w-4 h-4 text-amber-600 border-gray-300 rounded focus:ring-amber-500" />
                    <label htmlFor="terms" className="text-sm text-gray-600">
                      Acepto los términos y condiciones y la política de privacidad.
                    </label>
                  </div>
                </div>

                <div className="flex gap-4 pt-4">
                  <button
                    onClick={onBack}
                    className="flex-1 px-6 py-3 border border-gray-300 rounded-lg text-gray-700 font-semibold hover:bg-gray-50 transition-colors"
                  >
                    Atrás
                  </button>
                  <button
                    onClick={handleFinalSubmit}
                    className="flex-1 px-6 py-3 bg-amber-700 text-white rounded-lg font-semibold hover:bg-amber-800 transition-colors shadow-md transform hover:-translate-y-0.5"
                  >
                    Confirmar y Pagar
                  </button>
                </div>
              </div>
            </div>
          </div>

          {/* COLUMNA DERECHA - PRECIO */}
          <div>
            <div className="bg-white rounded-xl shadow-lg p-6 sticky top-24 border border-gray-100">
              <h3 className="text-xl font-bold text-gray-900 mb-6">Desglose de Precio</h3>

              <div className="space-y-4 mb-6 text-gray-600">
                <div className="flex justify-between">
                  <span>Subtotal</span>
                  <span className="font-medium">{paymentData.subtotal} €</span>
                </div>
                <div className="flex justify-between">
                  <span>Impuestos</span>
                  <span className="font-medium">{paymentData.impuestos} €</span>
                </div>
              </div>

              <div className="border-t border-gray-200 pt-4 mb-6">
                <div className="flex justify-between items-center">
                  <span className="text-lg font-bold text-gray-900">Total a Pagar</span>
                  <span className="text-2xl font-bold text-amber-700">{paymentData.total} €</span>
                </div>
              </div>

              <div className="bg-blue-50 border border-blue-100 rounded-lg p-4 flex gap-3 text-sm text-blue-800">
                <span className="text-xl">ℹ️</span>
                <div>
                  <p className="font-semibold mb-1">Política de Cancelación</p>
                  <p className="opacity-80 text-xs">Cancelación gratuita hasta 48h antes del check-in.</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>

      {/* Animación de la barra de progreso del modal */}
      <style>{`
                @keyframes progress {
                    from { width: 0%; }
                    to { width: 100%; }
                }
            `}</style>
    </div>
  );
};

export default ReservationStep3;
