import React, { useMemo, useState } from 'react';
import { useNavigate } from 'react-router-dom'; // 1. Importar para redirigir
import { CheckCircle } from 'lucide-react'; // Icono de éxito
import { useRoomTypes } from "../hooks/useRoomTypes";

const ReservationStep3 = ({ initialData, onSubmit, onBack }) => {
  const navigate = useNavigate();
  const [showSuccessModal, setShowSuccessModal] = useState(false); // 2. Estado del modal

  const [invoice, setInvoice] = useState(null);
  const [submitting, setSubmitting] = useState(false);

  const { rooms, loading } = useRoomTypes();

  const roomTypeId = Number(initialData?.roomTypeId);
  const selectedRoom = rooms.find((r) => r.id === roomTypeId);

  const checkIn = initialData?.checkIn || "";
  const checkOut = initialData?.checkOut || "";
  const guests = Number(initialData?.guests ?? 1);

  // Calcula noches y precios
  const { nights, subtotal, impuestos, total } = useMemo(() => {
    const toDate = (s) => (s ? new Date(`${s}T00:00:00`) : null);
    const inD = toDate(checkIn);
    const outD = toDate(checkOut);

    let n = 0;
    if (inD && outD && outD > inD) {
      n = Math.ceil((outD - inD) / (1000 * 60 * 60 * 24));
    }

    // Precio por noche: usa basePrice del backend si existe
    const pricePerNight = Number(selectedRoom?.basePrice ?? 0);

    const sub = n * pricePerNight;

    // impuestos ejemplo: 10% (ajústalo a tu gusto)
    const tax = Math.round(sub * 0.1 * 100) / 100;
    const tot = Math.round((sub + tax) * 100) / 100;

    return { nights: n, subtotal: sub, impuestos: tax, total: tot };
  }, [checkIn, checkOut, selectedRoom]);

  // Estado de pago SOLO para tarjeta
  const [paymentData, setPaymentData] = useState({
    cardName: "",
    cardNumber: "",
    expiry: "",
    cvv: "",
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

    // Validaciones básicas de fechas/selección
    if (!roomTypeId || !selectedRoom) {
      alert("Selecciona una habitación válida");
      return;
    }
    if (!checkIn || !checkOut || nights <= 0) {
      alert("Revisa las fechas (check-out debe ser posterior a check-in)");
      return;
    }

    try {
      setSubmitting(true);

      // Ahora onSubmit debe devolver el DTO (InvoiceResponse)
      const dto = await onSubmit({
        ...paymentData,
        subtotal,
        impuestos,
        total,
      });

      setInvoice(dto);
      setShowSuccessModal(true);

      setTimeout(() => {
        navigate("/");
      }, 2000);
    } catch (error) {
      console.error("Error:", error);
      alert(`Hubo un error: ${error.message}`);
    } finally {
      setSubmitting(false);
    }
  };

  const subtotalShown = invoice ? Number(invoice.subtotal) : subtotal;
  const taxShown = invoice ? Number(invoice.taxAmount) : impuestos;
  const totalShown = invoice ? Number(invoice.total) : total;

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
              {invoice ? (
                <>
                  Reserva <b>#{invoice.reservationId}</b> — {invoice.nights} noche(s) — Total:{" "}
                  <b>{Number(invoice.total).toFixed(2)} €</b>
                </>
              ) : (
                "Tu reserva ha sido confirmada correctamente. Te hemos enviado un email con los detalles."
              )}
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
                      <span className="font-medium">{selectedRoom?.name ?? "—"}</span>
                    </div>
                    <div className="flex justify-between">
                      <span>Check-in:</span>
                      <span className="font-medium">{checkIn || "—"}</span>
                    </div>
                    <div className="flex justify-between">
                      <span>Check-out:</span>
                      <span className="font-medium">{checkOut || "—"}</span>
                    </div>
                    <div className="flex justify-between">
                      <span>Huéspedes:</span>
                      <span className="font-medium">{guests} persona{guests > 1 ? "s" : ""}</span>
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
                    disabled={submitting}
                    className="flex-1 px-6 py-3 bg-amber-700 text-white rounded-lg font-semibold hover:bg-amber-800 transition-colors shadow-md transform hover:-translate-y-0.5 disabled:opacity-60"
                  >
                    {submitting ? "Procesando..." : "Confirmar y Pagar"}
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
                  <span className="font-medium">{subtotalShown.toFixed(2)} €</span>
                </div>
                <div className="flex justify-between">
                  <span>Impuestos</span>
                  <span className="font-medium">{taxShown.toFixed(2)} €</span>
                </div>
              </div>

              <div className="border-t border-gray-200 pt-4 mb-6">
                <div className="flex justify-between items-center">
                  <span className="text-lg font-bold text-gray-900">Total a Pagar</span>
                  <span className="text-2xl font-bold text-amber-700">{totalShown.toFixed(2)} €</span>
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
