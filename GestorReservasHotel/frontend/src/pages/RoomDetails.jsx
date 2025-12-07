import { useParams, useNavigate } from "react-router-dom";
import roomsData from "../data/Rooms";


function RoomDetails() {
  const { id } = useParams();
  const room = roomsData.find((r) => r.id === parseInt(id));
  const navigate = useNavigate();

  if (!room) return (
    <div className="min-h-screen flex items-center justify-center text-xl text-gray-500">
      Habitación no encontrada
    </div>
  );

  return (
    <div className="min-h-screen bg-gray-50 py-10 font-sans">

      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">

        {/* Título Principal */}
        <div className="mb-8">
          <h1 className="text-3xl font-bold text-gray-900">Detalles de la Habitación</h1>
        </div>

        {/* Layout Principal: Grid de 2 columnas (Izquierda contenido, Derecha precio) */}
        <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">

          {/* --- COLUMNA IZQUIERDA (Info) --- */}
          <div className="lg:col-span-2 space-y-8">

            {/* Imagen Principal */}
            <div className="aspect-video w-full overflow-hidden rounded-2xl shadow-md">
              <img
                className="w-full h-full object-cover hover:scale-105 transition-transform duration-500"
                src={room.img}
                alt={room.name}
              />
            </div>

            <div className="bg-white p-6 rounded-2xl shadow-sm border border-gray-100">
              <h1 className="text-3xl font-bold text-gray-900 mb-6">{room.name}</h1>

              {/* Características (Grid de iconos) */}
              <div className="grid grid-cols-2 sm:grid-cols-3 gap-4 mb-8">
                <FeatureIcon icon="🛏" text={`${room.beds} cama(s)`} />
                <FeatureIcon icon="🛁" text={`${room.bathrooms} baño(s)`} />
                <FeatureIcon icon="🚗" text={room.parking ? "Parking" : "Sin parking"} />
                <FeatureIcon icon="🐶" text={room.pets ? "Mascotas OK" : "No mascotas"} />
                <FeatureIcon icon="👥" text={`Máx. ${room.people} huéspedes`} />
                <FeatureIcon icon="🛜" text={`Wi-Fi: ${room.wifi}`} />
              </div>

              {/* Descripción */}
              <div className="mb-8">
                <h3 className="text-xl font-bold text-gray-900 mb-3">Descripción</h3>
                <p className="text-gray-600 leading-relaxed text-lg">
                  {room.longDescription}
                </p>
              </div>

              {/* Seguridad */}
              <div>
                <h3 className="text-xl font-bold text-gray-900 mb-3">Seguridad e Higiene</h3>
                <ul className="grid grid-cols-1 sm:grid-cols-2 gap-2">
                  {['Limpieza Diaria', 'Desinfecciones y Esterilizaciones', 'Extintores de Incendios', 'Detectores de Humo'].map((item, index) => (
                    <li key={index} className="flex items-center text-gray-600">
                      <span className="w-2 h-2 bg-green-500 rounded-full mr-2"></span>
                      {item}
                    </li>
                  ))}
                </ul>
              </div>
            </div>
          </div>

          {/* --- COLUMNA DERECHA (Tarjeta de Precio Flotante) --- */}
          <div className="lg:col-span-1">
            <div className="bg-white p-6 rounded-2xl shadow-lg border border-gray-100 sticky top-24">
              <h2 className="text-2xl font-bold text-gray-900 mb-2">{room.name}</h2>
              <div className="text-3xl font-bold text-amber-700 mb-6">
                {room.price} <span className="text-sm text-gray-500 font-normal">/ noche</span>
              </div>

              <div className="space-y-3 mb-8 text-sm text-gray-600 bg-gray-50 p-4 rounded-lg">
                <div className="flex justify-between">
                  <span>Periodo corto (3 noches):</span>
                  <span className="font-semibold">{room.shortPeriod}</span>
                </div>
                <div className="flex justify-between">
                  <span>Periodo medio (7 noches):</span>
                  <span className="font-semibold">{room.mediumPeriod}</span>
                </div>
                <div className="flex justify-between">
                  <span>Periodo largo (30 noches):</span>
                  <span className="font-semibold">{room.longPeriod}</span>
                </div>
              </div>

              <button
                onClick={() => navigate('/reservations', { state: { preselectedRoom: room.name } })}
                className="w-full bg-amber-700 hover:bg-amber-800 text-white font-bold py-4 px-6 rounded-xl transition-colors shadow-md hover:shadow-lg transform hover:-translate-y-0.5"
              >
                Reservar Ahora
              </button>
            </div>
          </div>

        </div>
      </div>
    </div>
  );
}

const FeatureIcon = ({ icon, text }) => (
  <div className="flex items-center gap-3 p-3 bg-gray-50 rounded-lg border border-gray-100">
    <span className="text-2xl">{icon}</span>
    <p className="text-sm font-medium text-gray-700">{text}</p>
  </div>
);

export default RoomDetails;
