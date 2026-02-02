// Asegúrate de poner la extensión correcta (.jpg, .png, .webp)
import reserva1 from '../assets/img/reserva1.avif';
import reserva2 from '../assets/img/reserva2.avif';

const reservationsData = [
  {
    id: 1,
    hotelName: "Grand Hotel & Resort",
    location: "Madrid, España",
    checkIn: "2023-12-15",
    checkOut: "2023-12-20",
    guests: 2,
    price: 450,
    status: "Confirmada",
    image: reserva1 // <--- SIN LLAVES { }
  },
  {
    id: 2,
    hotelName: "Seaside Paradise",
    location: "Barcelona, España",
    checkIn: "2024-01-10",
    checkOut: "2024-01-15",
    guests: 1,
    price: 320,
    status: "Pendiente",
    image: reserva2 // <--- SIN LLAVES { }
  }
];

export default reservationsData;
