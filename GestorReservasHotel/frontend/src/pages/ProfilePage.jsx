import React, { useState, useEffect } from "react";
import { User, Mail, Phone, Calendar, CreditCard, Star, Edit, Loader2, AlertCircle } from "lucide-react";
import api from '../services/api';

const ProfilePage = ({ user }) => {
  const [activeTab, setActiveTab] = useState("info");
  const [isEditing, setIsEditing] = useState(false);

  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchBookings = async () => {
      try {
        setLoading(true);
        const response = await api.get(`/reservations/client/${user.id}`);
        setBookings(response.data);
        setError(null);
      } catch (err) {
        console.error("Error fetching bookings:", err);
        setError("No se pudieron cargar tus reservas. Inténtalo de nuevo más tarde.");
      } finally {
        setLoading(false);
      }
    };

    if (user?.id) {
      fetchBookings();
    }
  }, [user?.id]);

  // Cálculo de estadísticas reales
  const totalBookingsCount = bookings.length;
  const totalSpentAmount = bookings.reduce((sum, b) => sum + (b.totalPrice || 0), 0);

  const stats = {
    bookings: totalBookingsCount,
    spent: `${totalSpentAmount.toLocaleString()}€`,
    level: totalBookingsCount > 5 ? "Cliente Preferencial" : "Cliente Estándar"
  };

  // Estado del formulario (Solo datos reales de la BD)
  const [profileData, setProfileData] = useState({
    name: user.name || "",
    lastName: user.lastName || "", // ⬅️ Nuevo campo
    email: user.email || "",
    phone: user.phone || ""
  });

  const handleSave = () => {
    // Aquí conectaríamos con el endpoint PUT /api/clients/{id}
    setIsEditing(false);
    alert("Datos actualizados correctamente (Simulado)");
  };

  return (
    <div className="max-w-6xl mx-auto px-4 py-8 font-sans">

      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900">Mi Perfil</h1>
        <p className="text-gray-600 mt-1">Gestiona tu información personal y revisa tus reservas.</p>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">

        {/* --- COLUMNA IZQUIERDA: TARJETA RESUMEN --- */}
        <div className="lg:col-span-1">
          <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-6 text-center">

            <div className="w-24 h-24 bg-amber-700 text-white rounded-full flex items-center justify-center text-3xl font-bold mx-auto mb-4 border-4 border-amber-50">
              {user.name ? user.name.charAt(0).toUpperCase() : "U"}
            </div>

            <h2 className="text-xl font-bold text-gray-900">{user.name} {user.lastName}</h2>
            <p className="text-sm text-gray-500 uppercase tracking-wider mb-6">{user.role}</p>

            <div className="space-y-4 text-left border-t border-gray-100 pt-6">
              <div className="flex items-center gap-3 text-gray-700">
                <div className="p-2 bg-amber-50 rounded-lg text-amber-600"><Star size={18} /></div>
                <span className="font-medium">{stats.level}</span>
              </div>
              <div className="flex items-center gap-3 text-gray-700">
                <div className="p-2 bg-gray-50 rounded-lg text-gray-500"><Calendar size={18} /></div>
                <span>{stats.bookings} Reservas realizadas</span>
              </div>
              <div className="flex items-center gap-3 text-gray-700">
                <div className="p-2 bg-gray-50 rounded-lg text-gray-500"><CreditCard size={18} /></div>
                <span>{stats.spent} gastados</span>
              </div>
            </div>
          </div>
        </div>

        {/* --- COLUMNA DERECHA: DETALLES --- */}
        <div className="lg:col-span-2">

          {/* Navegación de Pestañas */}
          <div className="bg-white rounded-t-2xl border-b border-gray-200 flex overflow-hidden">
            <button
              onClick={() => setActiveTab("info")}
              className={`flex-1 py-4 text-sm font-semibold transition-colors ${activeTab === 'info' ? 'bg-amber-50 text-amber-700 border-b-2 border-amber-700' : 'text-gray-500 hover:bg-gray-50'}`}
            >
              Datos de Contacto
            </button>
            <button
              onClick={() => setActiveTab("bookings")}
              className={`flex-1 py-4 text-sm font-semibold transition-colors ${activeTab === 'bookings' ? 'bg-amber-50 text-amber-700 border-b-2 border-amber-700' : 'text-gray-500 hover:bg-gray-50'}`}
            >
              Historial de Reservas
            </button>
          </div>

          <div className="bg-white rounded-b-2xl shadow-sm border border-gray-100 border-t-0 p-8">

            {/* TAB: INFORMACIÓN */}
            {activeTab === "info" && (
              <div className="space-y-6">
                <div className="flex justify-between items-center mb-2">
                  <h3 className="text-lg font-bold text-gray-800">Información Personal</h3>
                  <button onClick={() => setIsEditing(!isEditing)} className="text-sm text-amber-700 font-medium flex items-center gap-1 hover:underline">
                    <Edit size={16} /> {isEditing ? "Cancelar" : "Editar"}
                  </button>
                </div>

                {/* GRID PERFECTO 2x2 */}
                <div className="grid grid-cols-1 md:grid-cols-2 gap-6">

                  {/* 1. Nombre */}
                  <div>
                    <label className="block text-xs font-medium text-gray-500 uppercase mb-1">Nombre</label>
                    <div className={`flex items-center gap-3 p-3 rounded-lg border ${isEditing ? 'border-amber-500 bg-white shadow-sm' : 'border-gray-100 bg-gray-50'} text-gray-800 transition-all`}>
                      <User size={18} className="text-gray-400" />
                      {isEditing ? (
                        <input className="w-full bg-transparent outline-none" value={profileData.name} onChange={(e) => setProfileData({ ...profileData, name: e.target.value })} />
                      ) : <span className="font-medium">{profileData.name}</span>}
                    </div>
                  </div>

                  {/* 2. Apellidos */}
                  <div>
                    <label className="block text-xs font-medium text-gray-500 uppercase mb-1">Apellidos</label>
                    <div className={`flex items-center gap-3 p-3 rounded-lg border ${isEditing ? 'border-amber-500 bg-white shadow-sm' : 'border-gray-100 bg-gray-50'} text-gray-800 transition-all`}>
                      <User size={18} className="text-gray-400" />
                      {isEditing ? (
                        <input className="w-full bg-transparent outline-none" value={profileData.lastName} onChange={(e) => setProfileData({ ...profileData, lastName: e.target.value })} />
                      ) : <span className="font-medium">{profileData.lastName}</span>}
                    </div>
                  </div>

                  {/* 3. Email */}
                  <div>
                    <label className="block text-xs font-medium text-gray-500 uppercase mb-1">Correo Electrónico</label>
                    <div className={`flex items-center gap-3 p-3 rounded-lg border ${isEditing ? 'border-amber-500 bg-white shadow-sm' : 'border-gray-100 bg-gray-50'} text-gray-800 transition-all`}>
                      <Mail size={18} className="text-gray-400" />
                      {isEditing ? (
                        <input className="w-full bg-transparent outline-none" value={profileData.email} onChange={(e) => setProfileData({ ...profileData, email: e.target.value })} />
                      ) : profileData.email}
                    </div>
                  </div>

                  {/* 4. Teléfono */}
                  <div>
                    <label className="block text-xs font-medium text-gray-500 uppercase mb-1">Teléfono</label>
                    <div className={`flex items-center gap-3 p-3 rounded-lg border ${isEditing ? 'border-amber-500 bg-white shadow-sm' : 'border-gray-100 bg-gray-50'} text-gray-800 transition-all`}>
                      <Phone size={18} className="text-gray-400" />
                      {isEditing ? (
                        <input className="w-full bg-transparent outline-none" value={profileData.phone} onChange={(e) => setProfileData({ ...profileData, phone: e.target.value })} />
                      ) : (profileData.phone || "Sin teléfono")}
                    </div>
                  </div>

                </div>

                {isEditing && (
                  <div className="flex justify-end pt-6 border-t border-gray-100">
                    <button onClick={handleSave} className="px-6 py-2 bg-amber-700 text-white rounded-lg hover:bg-amber-800 transition-colors shadow-md font-medium">
                      Guardar Cambios
                    </button>
                  </div>
                )}
              </div>
            )}

            {/* TAB: RESERVAS */}
            {activeTab === "bookings" && (
              <div className="space-y-4">
                {loading ? (
                  <div className="flex flex-col items-center justify-center py-12 text-gray-500">
                    <Loader2 className="w-8 h-8 animate-spin mb-2 text-amber-600" />
                    <p>Cargando historial...</p>
                  </div>
                ) : error ? (
                  <div className="flex items-center gap-2 p-4 bg-red-50 text-red-700 rounded-xl border border-red-100">
                    <AlertCircle size={20} />
                    <p>{error}</p>
                  </div>
                ) : bookings.length === 0 ? (
                  <div className="text-center py-12 bg-gray-50 rounded-xl border border-dashed border-gray-300">
                    <p className="text-gray-500">Aún no tienes ninguna reserva realizada.</p>
                  </div>
                ) : (
                  <div className="overflow-x-auto">
                    <table className="w-full text-left">
                      <thead>
                        <tr className="text-xs font-bold text-gray-400 uppercase tracking-wider border-b border-gray-100">
                          <th className="pb-4 pl-2">ID</th>
                          <th className="pb-4">Estancia</th>
                          <th className="pb-4">Estado</th>
                          <th className="pb-4 pr-2 text-right">Monto</th>
                        </tr>
                      </thead>
                      <tbody className="divide-y divide-gray-50">
                        {bookings.map((booking) => (
                          <tr key={booking.id} className="group hover:bg-gray-50 transition-colors">
                            <td className="py-4 pl-2 font-mono text-xs text-amber-700">#{booking.id || "N/A"}</td>
                            <td className="py-4">
                              <div className="text-sm font-medium text-gray-900">
                                {new Date(booking.checkInDate).toLocaleDateString()} - {new Date(booking.checkOutDate).toLocaleDateString()}
                              </div>
                              <div className="text-xs text-gray-500">Reserva: {new Date(booking.reservationDate).toLocaleDateString()}</div>
                            </td>
                            <td className="py-4">
                              <span className={`px-2.5 py-1 rounded-full text-xs font-semibold capitalize ${booking.condition.toLowerCase() === 'confirmed' || booking.condition.toLowerCase() === 'confirmada'
                                ? 'bg-green-100 text-green-700'
                                : booking.condition.toLowerCase() === 'completed' || booking.condition.toLowerCase() === 'completada'
                                  ? 'bg-blue-100 text-blue-700'
                                  : 'bg-gray-100 text-gray-700'
                                }`}>
                                {booking.condition}
                              </span>
                            </td>
                            <td className="py-4 pr-2 text-right font-bold text-gray-900">
                              {booking.totalPrice?.toFixed(2)}€
                            </td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>
                )}
              </div>
            )}

          </div>
        </div>
      </div>
    </div>
  );
};

export default ProfilePage;
