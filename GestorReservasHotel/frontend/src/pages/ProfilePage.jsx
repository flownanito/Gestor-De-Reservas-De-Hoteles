import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Calendar, MapPin, CreditCard, Star, Edit, User, CheckCircle, Clock, XCircle } from "lucide-react";

// Datos de prueba para las reservas
const mockBookings = [
  {
    id: "BK001",
    room: "Suite Ejecutiva",
    checkIn: "2025-12-15",
    checkOut: "2025-12-18",
    status: "confirmed",
    total: 840
  },
  {
    id: "BK002",
    room: "Habitación Deluxe",
    checkIn: "2025-11-01",
    checkOut: "2025-11-03",
    status: "completed",
    total: 300
  },
  {
    id: "BK003",
    room: "Habitación Superior",
    checkIn: "2025-10-10",
    checkOut: "2025-10-12",
    status: "cancelled",
    total: 240
  }
];

export default function ProfilePage() {
  const navigate = useNavigate();

  // Recuperamos el usuario del localStorage o simulamos uno si no llega por props
  // (En tu App real, esto vendría del contexto o props, pero aquí aseguramos que no falle)
  const user = {
    name: "Carlos Cliente",
    email: "cliente@hotel.com",
    role: "CLIENT",
    initials: "CC"
  };

  // Estados
  const [isEditing, setIsEditing] = useState(false);
  const [activeTab, setActiveTab] = useState("info"); // 'info' o 'bookings'

  const [profileData, setProfileData] = useState({
    name: user.name,
    email: user.email,
    phone: "+34 600 123 456",
    address: "Calle Gran Vía 123, Madrid",
    preferences: "Vista al mar, piso alto, almohadas extra"
  });

  const handleSave = () => {
    // Aquí harías la petición al backend
    setIsEditing(false);
    alert("Perfil actualizado exitosamente (Simulado)");
  };

  // Función para obtener el estilo del estado de la reserva
  const getStatusBadge = (status) => {
    const styles = {
      confirmed: "bg-green-100 text-green-700 border-green-200",
      completed: "bg-gray-100 text-gray-700 border-gray-200",
      cancelled: "bg-red-100 text-red-700 border-red-200"
    };

    const labels = {
      confirmed: "Confirmada",
      completed: "Completada",
      cancelled: "Cancelada"
    };

    const icons = {
      confirmed: <CheckCircle size={14} />,
      completed: <Clock size={14} />,
      cancelled: <XCircle size={14} />
    }

    return (
      <span className={`px-2.5 py-0.5 rounded-full text-xs font-medium border flex items-center gap-1 w-fit ${styles[status] || styles.completed}`}>
        {icons[status]} {labels[status]}
      </span>
    );
  };

  return (
    <div className="py-8 px-4 sm:px-6 lg:px-8 max-w-6xl mx-auto font-sans">

      {/* Header */}
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900 mb-2">Mi Perfil</h1>
        <p className="text-gray-600">
          Gestiona tu información y revisa tu historial de reservas.
        </p>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">

        {/* --- COLUMNA IZQUIERDA: TARJETA DE PERFIL --- */}
        <div className="lg:col-span-1">
          <div className="bg-white rounded-xl shadow-sm border border-gray-200 overflow-hidden">
            <div className="p-6 text-center border-b border-gray-100">
              <div className="w-24 h-24 mx-auto mb-4 bg-amber-700 text-white rounded-full flex items-center justify-center text-3xl font-bold border-4 border-amber-100">
                {user.initials}
              </div>
              <h2 className="text-xl font-bold text-gray-900">{user.name}</h2>
              <p className="text-sm text-gray-500 uppercase tracking-wide mt-1">{user.role}</p>
            </div>

            <div className="p-6 space-y-4 bg-gray-50/50">
              <div className="flex items-center gap-3 text-gray-700">
                <Star className="w-5 h-5 text-amber-500 fill-amber-500" />
                <span className="font-medium">Cliente Preferencial</span>
              </div>
              <div className="flex items-center gap-3 text-gray-600">
                <Calendar className="w-5 h-5" />
                <span>3 reservas realizadas</span>
              </div>
              <div className="flex items-center gap-3 text-gray-600">
                <CreditCard className="w-5 h-5" />
                <span>$1,380 gastados</span>
              </div>
            </div>
          </div>
        </div>

        {/* --- COLUMNA DERECHA: PESTAÑAS Y CONTENIDO --- */}
        <div className="lg:col-span-2">

          {/* Navegación de Pestañas (Tabs) */}
          <div className="bg-white rounded-xl shadow-sm border border-gray-200 overflow-hidden mb-6">
            <div className="flex border-b border-gray-200">
              <button
                onClick={() => setActiveTab("info")}
                className={`flex-1 py-4 text-sm font-medium text-center transition-colors ${activeTab === "info"
                    ? "text-amber-700 border-b-2 border-amber-700 bg-amber-50/50"
                    : "text-gray-500 hover:text-gray-700 hover:bg-gray-50"
                  }`}
              >
                Información Personal
              </button>
              <button
                onClick={() => setActiveTab("bookings")}
                className={`flex-1 py-4 text-sm font-medium text-center transition-colors ${activeTab === "bookings"
                    ? "text-amber-700 border-b-2 border-amber-700 bg-amber-50/50"
                    : "text-gray-500 hover:text-gray-700 hover:bg-gray-50"
                  }`}
              >
                Mis Reservas
              </button>
            </div>

            {/* --- CONTENIDO PESTAÑA: INFORMACIÓN --- */}
            {activeTab === "info" && (
              <div className="p-6">
                <div className="flex justify-between items-center mb-6">
                  <div>
                    <h3 className="text-lg font-bold text-gray-900">Datos de Contacto</h3>
                    <p className="text-sm text-gray-500">Actualiza tu información personal aquí.</p>
                  </div>
                  {!isEditing && (
                    <button
                      onClick={() => setIsEditing(true)}
                      className="flex items-center gap-2 text-sm font-medium text-amber-700 hover:text-amber-800 bg-amber-50 px-3 py-1.5 rounded-lg transition-colors border border-amber-200"
                    >
                      <Edit size={16} /> Editar
                    </button>
                  )}
                </div>

                <div className="space-y-4">
                  <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                    <div className="space-y-2">
                      <label className="text-sm font-medium text-gray-700">Nombre Completo</label>
                      <input
                        type="text"
                        value={profileData.name}
                        onChange={(e) => setProfileData({ ...profileData, name: e.target.value })}
                        disabled={!isEditing}
                        className="w-full px-3 py-2 rounded-lg border border-gray-300 focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none disabled:bg-gray-100 disabled:text-gray-500 transition-all"
                      />
                    </div>
                    <div className="space-y-2">
                      <label className="text-sm font-medium text-gray-700">Correo Electrónico</label>
                      <input
                        type="email"
                        value={profileData.email}
                        onChange={(e) => setProfileData({ ...profileData, email: e.target.value })}
                        disabled={!isEditing}
                        className="w-full px-3 py-2 rounded-lg border border-gray-300 focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none disabled:bg-gray-100 disabled:text-gray-500 transition-all"
                      />
                    </div>
                    <div className="space-y-2">
                      <label className="text-sm font-medium text-gray-700">Teléfono</label>
                      <input
                        type="text"
                        value={profileData.phone}
                        onChange={(e) => setProfileData({ ...profileData, phone: e.target.value })}
                        disabled={!isEditing}
                        className="w-full px-3 py-2 rounded-lg border border-gray-300 focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none disabled:bg-gray-100 disabled:text-gray-500 transition-all"
                      />
                    </div>
                    <div className="space-y-2">
                      <label className="text-sm font-medium text-gray-700">Dirección</label>
                      <input
                        type="text"
                        value={profileData.address}
                        onChange={(e) => setProfileData({ ...profileData, address: e.target.value })}
                        disabled={!isEditing}
                        className="w-full px-3 py-2 rounded-lg border border-gray-300 focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none disabled:bg-gray-100 disabled:text-gray-500 transition-all"
                      />
                    </div>
                  </div>

                  <div className="space-y-2">
                    <label className="text-sm font-medium text-gray-700">Preferencias de Habitación</label>
                    <textarea
                      value={profileData.preferences}
                      onChange={(e) => setProfileData({ ...profileData, preferences: e.target.value })}
                      disabled={!isEditing}
                      rows="2"
                      className="w-full px-3 py-2 rounded-lg border border-gray-300 focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none disabled:bg-gray-100 disabled:text-gray-500 transition-all resize-none"
                    />
                  </div>

                  {isEditing && (
                    <div className="flex justify-end gap-3 pt-4 border-t border-gray-100 mt-4">
                      <button
                        onClick={() => setIsEditing(false)}
                        className="px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-100 rounded-lg transition-colors border border-gray-300"
                      >
                        Cancelar
                      </button>
                      <button
                        onClick={handleSave}
                        className="px-4 py-2 text-sm font-medium text-white bg-amber-700 hover:bg-amber-800 rounded-lg transition-colors shadow-sm"
                      >
                        Guardar Cambios
                      </button>
                    </div>
                  )}
                </div>
              </div>
            )}

            {/* --- CONTENIDO PESTAÑA: RESERVAS --- */}
            {activeTab === "bookings" && (
              <div className="p-6">
                <div className="flex justify-between items-center mb-6">
                  <div>
                    <h3 className="text-lg font-bold text-gray-900">Historial de Reservas</h3>
                    <p className="text-sm text-gray-500">Revisa tus estancias pasadas y futuras.</p>
                  </div>
                  <button
                    onClick={() => navigate("/rooms")}
                    className="text-sm font-medium text-white bg-amber-700 hover:bg-amber-800 px-4 py-2 rounded-lg transition-colors shadow-sm"
                  >
                    + Nueva Reserva
                  </button>
                </div>

                <div className="space-y-4">
                  {mockBookings.map((booking) => (
                    <div key={booking.id} className="border border-gray-200 rounded-lg p-5 hover:border-amber-200 hover:bg-amber-50/10 transition-all">
                      <div className="flex justify-between items-start mb-3">
                        <div>
                          <h4 className="font-bold text-gray-900 text-lg">{booking.room}</h4>
                          <span className="text-xs text-gray-500 font-mono">ID: {booking.id}</span>
                        </div>
                        {getStatusBadge(booking.status)}
                      </div>

                      <div className="grid grid-cols-2 gap-4 text-sm text-gray-600 mb-4 bg-gray-50 p-3 rounded-md">
                        <div className="flex items-center gap-2">
                          <Calendar size={16} className="text-amber-600" />
                          <span>In: <span className="font-semibold text-gray-900">{booking.checkIn}</span></span>
                        </div>
                        <div className="flex items-center gap-2">
                          <Calendar size={16} className="text-amber-600" />
                          <span>Out: <span className="font-semibold text-gray-900">{booking.checkOut}</span></span>
                        </div>
                      </div>

                      <div className="flex justify-between items-center pt-3 border-t border-gray-100">
                        <div className="font-bold text-gray-900 text-lg">
                          ${booking.total} <span className="text-xs font-normal text-gray-500">Total</span>
                        </div>
                        <div className="flex gap-2">
                          {booking.status === "confirmed" ? (
                            <>
                              <button className="px-3 py-1.5 text-xs font-medium text-gray-700 border border-gray-300 rounded hover:bg-gray-50">Modificar</button>
                              <button className="px-3 py-1.5 text-xs font-medium text-red-600 border border-red-200 rounded hover:bg-red-50">Cancelar</button>
                            </>
                          ) : (
                            <button className="px-3 py-1.5 text-xs font-medium text-amber-700 border border-amber-200 rounded hover:bg-amber-50">Ver Detalles</button>
                          )}
                        </div>
                      </div>
                    </div>
                  ))}
                </div>
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}
