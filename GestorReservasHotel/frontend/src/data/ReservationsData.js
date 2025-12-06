import room3 from "../assets/room3.jpg";
import room7 from "../assets/room7.jpg";

// datos de prueba
const reservationsData = [
  {
    id: 1,
    roomId: 3,
    roomName: "Suite Ejecutiva",
    img: room3,
    checkIn: "2025-03-10",
    checkOut: "2025-03-14",
    nights: 4,
    guests: 2,
    totalPrice: "1120€",
    status: "confirmada"
  },
  {
    id: 2,
    roomId: 7,
    roomName: "Habitación con Jacuzzi",
    img: room7,
    checkIn: "2025-04-01",
    checkOut: "2025-04-03",
    nights: 2,
    guests: 2,
    totalPrice: "400€",
    status: "pendiente"
  }
];

export default reservationsData;