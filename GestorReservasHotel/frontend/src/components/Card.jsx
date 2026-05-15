import { Link } from "react-router-dom";

function Card({ id, name, description, price, img, people, size, wifi, availableRooms, totalRooms }) {
  return (
    <div className={`bg-white rounded-xl shadow-md overflow-hidden border border-gray-100 flex flex-col h-full
  ${availableRooms === 0 ? "opacity-70" : "hover:shadow-xl transition-shadow duration-300"}
`}>

      {/* --- SECCIÓN DE IMAGEN --- */}
      <div
        className="h-56 w-full bg-cover bg-center relative bg-gray-200"
        style={{
          backgroundImage: img ? `url(${img})` : "none"
        }}
      >
        {/* Etiqueta de Precio (Badge) + Estado/Disponinilidad */}
        {price && (
          <div className="absolute top-3 right-3 flex flex-col items-end gap-2">

            {/* Precio */}
            <span className="bg-amber-700 text-white text-xs font-bold px-3 py-1.5 rounded-full shadow-sm">
              {price}
            </span>

            {/* Estado disponibilidad */}
            {availableRooms !== null && availableRooms !== undefined && (
              <>
                {availableRooms === 0 && (
                  <span className="bg-red-600 text-white text-xs font-bold px-3 py-1.5 rounded-full shadow-sm">
                    Completo
                  </span>
                )}
                {availableRooms > 0 && availableRooms <= 5 && (
                  <span className="bg-red-100 text-red-700 text-xs font-bold px-3 py-1.5 rounded-full shadow-sm border border-red-200">
                    Quedan pocas
                  </span>
                )}
              </>
            )}
          </div>
        )}

        {/* Placeholder si no hay imagen */}
        {!img && (
          <div className="flex items-center justify-center h-full text-gray-400 font-medium bg-gray-100">
            Sin imagen
          </div>
        )}
      </div>

      {/* --- CUERPO DE LA TARJETA --- */}
      <div className="p-6 flex flex-col flex-grow">

        {/* Título */}
        <h3 className="text-xl font-bold text-gray-900 mb-2 truncate" title={name}>
          {name}
        </h3>

        {/* Descripción (Limitada a 3 líneas para que no rompa el diseño) */}
        <p className="text-gray-600 text-sm mb-4 line-clamp-3 flex-grow leading-relaxed">
          {description}
        </p>

        {/* Fila de Iconos/Información */}
        <div className="flex items-center justify-between text-sm text-gray-500 mb-6 pt-4 border-t border-gray-100">

          <div className="flex items-center gap-1.5" title="Huéspedes">
            <span className="text-lg">👤</span>
            <span className="font-medium">{people}</span>
          </div>

          <div className="flex items-center gap-1.5" title="Tamaño">
            <span className="text-lg">📐</span>
            <span className="font-medium">{size} m²</span>
          </div>

          <div className="flex items-center gap-1.5" title="WiFi">
            <span className="text-lg">🛜</span>
            <span className="font-medium">{wifi}</span>
          </div>

        </div>

        {/* Botón de Acción (Es un Link disfrazado de botón) */}
        <Link
          to={availableRooms === 0 ? "#" : `/rooms/${id}`}
          onClick={(e) => availableRooms === 0 && e.preventDefault()}
          className={`w-full block text-center font-bold py-3 px-4 rounded-lg transition-colors shadow-sm active:scale-95 transform duration-100
    ${availableRooms === 0
              ? "bg-gray-300 text-gray-600 cursor-not-allowed"
              : "bg-amber-700 hover:bg-amber-800 text-white"
            }`}
        >
          {availableRooms === 0 ? "No disponible" : "Ver Detalles"}
        </Link>

      </div>
    </div>
  );
}

export default Card;
