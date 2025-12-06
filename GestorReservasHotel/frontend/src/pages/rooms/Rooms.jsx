import "./Rooms.css";
import Card from "../../components/card/Card"
import roomsData from "../../data/Rooms"; // ruta según tu estructura

function Room() {
  return (
    <>
      <div className="rooms-tittle">
        <h1><b>Catálogo de Habitaciones</b></h1>
        <p>Explora nuestras habitaciones y encuentra la perfecta para tu estadía</p>
      </div>
      <div className="rooms-list">
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
    </>
  )
}

export default Room;