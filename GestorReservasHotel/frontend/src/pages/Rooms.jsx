import Card from "../components/Card";
import roomsData from "../data/Rooms"; // Ajusta la ruta si es necesario

function Room() {
  return (
    <div className="min-h-screen bg-gray-50 font-sans">

      {/* Header del Catálogo */}
      <div className="bg-white py-12 px-4 shadow-sm mb-8 text-center">
        <div className="max-w-4xl mx-auto">
          <h1 className="text-4xl font-bold text-gray-900 mb-3">
            Catálogo de Habitaciones
          </h1>
          <p className="text-lg text-gray-600">
            Explora nuestras habitaciones y encuentra la perfecta para tu estadía
          </p>
        </div>
      </div>

      {/* Lista de Habitaciones (Grid) */}
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 pb-16">
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
          {roomsData.map((room) => (
            <Card
              key={room.id}
              id={room.id}
              name={room.name}
              description={room.description}
              price={room.price}
              img={room.img}
              people={room.people}
              size={room.size}
              wifi={room.wifi}
            />
          ))}
        </div>
      </div>
    </div>
  );
}

export default Room;
