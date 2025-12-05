import React, { useState } from 'react';

import './Reservationstep3.css';


const ReservationStep3 = () => {
    const [paymentData, setPaymentData] = useState({
        habitacion: 'Habitación Superior',
        checkIn: '',
        checkOut: '',
        huespedes: 2,
        subtotal: 252,
        impuestos: 50,
        total: 302
    });

    return (
        <div className="min-h-screen bg-gray-50">

            {/* Progress Bar */}
            <div className="bg-white border-b">
                <div className="max-w-[1280px] mx-auto px-4 py-6">
                    <div className="flex items-center justify-between max-w-2xl mx-auto">
                        <div className="flex flex-col items-center">
                            <div className="w-10 h-10 bg-orange-500 rounded-full flex items-center justify-center text-white font-bold mb-2">
                                ✓
                            </div>
                            <span className="text-sm font-medium text-gray-900">Detalles</span>
                        </div>
                        <div className="flex-1 h-0.5 bg-orange-500 mx-4"></div>
                        <div className="flex flex-col items-center">
                            <div className="w-10 h-10 bg-orange-500 rounded-full flex items-center justify-center text-white font-bold mb-2">
                                ✓
                            </div>
                            <span className="text-sm font-medium text-gray-900">Información</span>
                        </div>
                        <div className="flex-1 h-0.5 bg-orange-500 mx-4"></div>
                        <div className="flex flex-col items-center">
                            <div className="w-10 h-10 bg-orange-500 rounded-full flex items-center justify-center text-white font-bold mb-2">3</div>
                            <span className="text-sm font-medium text-gray-900">Pago</span>
                        </div>
                    </div>
                </div>
            </div>

            {/* Main Content */}
            <main className="max-w-[100rem] mx-auto px-6 py-12">        
                <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
                {/* Left Column - Payment Details */}
                <div className="lg:col-span-2">
                    <div className="bg-white rounded-lg shadow-sm p-8">
                        <h2 className="text-2xl font-bold text-gray-900 mb-6">Pago y Confirmación</h2>
                        <p className="text-gray-600 mb-8">Ingresa los detalles de pago</p>

                        <div className="space-y-6">
                            <div className="bg-yellow-50 border-l-4 border-yellow-400 p-4 rounded">
                                <h3 className="font-semibold text-gray-900 mb-2">Resumen de Reserva</h3>
                                <div className="space-y-2 text-sm text-gray-700">
                                    <div className="flex justify-between">
                                        <span>Habitación:</span>
                                        <span className="font-medium">{paymentData.habitacion}</span>
                                    </div>
                                    <div className="flex justify-between">
                                        <span>Check-in:</span>
                                        <span className="font-medium">15/12/2024</span>
                                    </div>
                                    <div className="flex justify-between">
                                        <span>Check-out:</span>
                                        <span className="font-medium">18/12/2024</span>
                                    </div>
                                    <div className="flex justify-between">
                                        <span>Huéspedes:</span>
                                        <span className="font-medium">{paymentData.huespedes} personas</span>
                                    </div>
                                </div>
                            </div>

                            <div className="border-t pt-6">
                                <h3 className="font-semibold text-gray-900 mb-4">Detalles de Pago</h3>

                                <div className="space-y-4">
                                    <div>
                                        <label className="block text-sm font-medium text-gray-700 mb-2">
                                            Nombre del Titular *
                                        </label>
                                        <input
                                            type="text"
                                            className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500 focus:border-orange-500 outline-none"
                                            placeholder="Nombre completo"
                                        />
                                    </div>

                                    <div>
                                        <label className="block text-sm font-medium text-gray-700 mb-2">
                                            Número de Tarjeta *
                                        </label>
                                        <input
                                            type="text"
                                            className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500 focus:border-orange-500 outline-none"
                                            placeholder="1234 5678 9012 3456"
                                            maxLength="19"
                                        />
                                    </div>

                                    <div className="grid grid-cols-2 gap-4">
                                        <div>
                                            <label className="block text-sm font-medium text-gray-700 mb-2">
                                                Fecha de Vencimiento *
                                            </label>
                                            <input
                                                type="text"
                                                className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500 focus:border-orange-500 outline-none"
                                                placeholder="MM/AA"
                                                maxLength="5"
                                            />
                                        </div>
                                        <div>
                                            <label className="block text-sm font-medium text-gray-700 mb-2">
                                                CVV *
                                            </label>
                                            <input
                                                type="text"
                                                className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500 focus:border-orange-500 outline-none"
                                                placeholder="123"
                                                maxLength="3"
                                            />
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="border-t pt-6">
                                <div className="flex items-start gap-3">
                                    <input
                                        type="checkbox"
                                        id="terms"
                                        className="mt-1 w-4 h-4 text-orange-500 border-gray-300 rounded focus:ring-orange-500"
                                    />
                                    <label htmlFor="terms" className="text-sm text-gray-600">
                                        Acepto los términos y condiciones y la política de privacidad del hotel
                                    </label>
                                </div>
                            </div>

                            <div className="flex gap-4 pt-4">
                                <button
                                    onClick={() => console.log('Back')}
                                    className="flex-1 px-6 py-3 border border-gray-300 rounded-lg text-gray-700 font-semibold hover:bg-gray-50 transition-colors"
                                >
                                    Atrás
                                </button>
                                <button
                                    onClick={() => console.log('Confirm payment')}
                                    className="flex-1 px-6 py-3 bg-orange-500 text-white rounded-lg font-semibold hover:bg-orange-600 transition-colors"
                                >
                                    Confirar Reserva
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                {/* Right Column - Price Summary */}
                <div>
                    <div className="bg-white rounded-lg shadow-sm p-6 sticky top-4">
                        <h3 className="text-xl font-bold text-gray-900 mb-6">Resumen de Costos</h3>

                        <div className="space-y-4 mb-6">
                            <div className="flex justify-between text-gray-700">
                                <span>Habitación (3 noches)</span>
                                <span className="font-medium">{paymentData.subtotal} €</span>
                            </div>
                            <div className="flex justify-between text-gray-700">
                                <span>Impuestos y tasas</span>
                                <span className="font-medium">{paymentData.impuestos} €</span>
                            </div>
                        </div>

                        <div className="border-t pt-4 mb-6">
                            <div className="flex justify-between items-center">
                                <span className="text-lg font-bold text-gray-900">Total</span>
                                <span className="text-2xl font-bold text-orange-500">{paymentData.total} €</span>
                            </div>
                        </div>

                        <div className="bg-blue-50 border border-blue-200 rounded-lg p-4">
                            <div className="flex gap-2">
                                <span className="text-blue-600">ℹ️</span>
                                <div>
                                    <p className="text-sm font-medium text-blue-900 mb-1">Política de Cancelación</p>
                                    <p className="text-xs text-blue-700">
                                        Cancelación gratuita hasta 48 horas antes del check-in. Después de este periodo se aplicará un cargo del 50% del total de la reserva.
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </main>
        </div>
    );
};

export default ReservationStep3;