import room1 from "../assets/img/room1.jpg"
import room2 from "../assets/img/room2.jpg"
import room3 from "../assets/img/room3.jpg"
import room4 from "../assets/img/room4.jpg"
import room5 from "../assets/img/room5.jpg"
import room6 from "../assets/img/room6.jpg"
import room7 from "../assets/img/room7.jpg"
import room8 from "../assets/img/room8.jpg"

const roomsData = [
  {
    id: 1,
    name: "Habitación Deluxe",
    description: "Espaciosa habitación con vista a la ciudad y todas las comodidades modernas.",
    longDescription: "Nuestra Habitación Deluxe ofrece un ambiente elegante y luminoso, ideal para quienes buscan confort y una experiencia superior durante su estancia. Su amplio ventanal permite disfrutar de una vista panorámica a la ciudad, especialmente atractiva al atardecer. El mobiliario moderno, la iluminación cálida y los acabados premium transforman este espacio en un refugio perfecto tanto para viajeros de ocio como de negocios. Incluye una acogedora zona de descanso, escritorio de trabajo, climatización individual y una amplia cama doble de alta calidad para garantizar un sueño reparador.",
    price: "150€/noche",
    img: room1,
    people: 2,
    size: 35,
    wifi: "Gratis",
    beds: 1,
    bathrooms: 1,
    parking: true,
    pets: false,
    shortPeriod: "450€",    // 150 * 3
    mediumPeriod: "1050€",  // 150 * 7
    longPeriod: "4500€"     // 150 * 30
  },
  {
    id: 2,
    name: "Habitación Superior",
    description: "Habitación confortable con cama king size y escritorio de trabajo.",
    longDescription: "La Habitación Superior combina funcionalidad y bienestar en un diseño moderno pensado para el descanso. Su cama king size de última generación garantiza una noche tranquila, mientras que el escritorio de trabajo resulta ideal para huéspedes que necesiten un espacio cómodo para estudiar o teletrabajar. La habitación cuenta con climatización independiente, iluminación regulable y un baño privado con acabados minimalistas. Es una opción perfecta para viajeros frecuentes que buscan confort y calidad al mejor precio.",
    price: "120€/noche",
    img: room2,
    people: 2,
    size: 30,
    wifi: "Gratis",
    beds: 1,
    bathrooms: 1,
    parking: true,
    pets: false,
    shortPeriod: "360€",
    mediumPeriod: "840€",
    longPeriod: "3600€"
  },
  {
    id: 3,
    name: "Suite Ejecutiva",
    description: "Suite premium con sala de estar separada y vistas panorámicas excepcionales.",
    longDescription: "Diseñada para quienes desean una experiencia superior, la Suite Ejecutiva ofrece un espacio amplio y sofisticado dividido en dos ambientes: un dormitorio con cama doble y una elegante sala de estar independiente. Sus grandes ventanales ofrecen vistas panorámicas espectaculares, ideales para relajarse durante el día o disfrutar de una cena tranquila por la noche. Equipada con baño principal y baño auxiliar, minibar premium, zona de lectura y mobiliario de diseño. Perfecta para viajes en pareja o largas estancias donde la comodidad es indispensable.",
    price: "280€/noche",
    img: room3,
    people: 3,
    size: 65,
    wifi: "Gratis",
    beds: 2,
    bathrooms: 2,
    parking: true,
    pets: false,
    shortPeriod: "840€",
    mediumPeriod: "1960€",
    longPeriod: "8400€"
  },
  {
    id: 4,
    name: "Habitación Familiar",
    description: "Perfecta para familias, con dos habitaciones conectadas y espacio amplio.",
    longDescription: "La Habitación Familiar está pensada especialmente para familias que buscan comodidad y amplitud. Ofrece dos dormitorios conectados mediante una puerta interior, lo que permite mantener privacidad sin perder cercanía entre los miembros del grupo. El espacio cuenta con múltiples áreas de almacenamiento, zona de descanso, dos baños completos y camas distribuidas de manera práctica para garantizar una estancia relajada. Gracias a su distribución y su ambiente acogedor, es la opción perfecta para familias con niños o grupos pequeños que desean sentirse como en casa.",
    price: "200€/noche",
    img: room4,
    people: 4,
    size: 50,
    wifi: "Gratis",
    beds: 3,
    bathrooms: 2,
    parking: true,
    pets: true,
    shortPeriod: "600€",
    mediumPeriod: "1400€",
    longPeriod: "6000€"
  },
  {
    id: 5,
    name: "Habitación Estándar",
    description: "Cómoda y acogedora, ideal para estancias cortas con todas las comodidades básicas.",
    longDescription: "La Habitación Estándar es una opción funcional y acogedora pensada para estancias cortas o viajeros que buscan confort a un precio accesible. Con una cama doble cómoda, un baño privado y climatización individual, esta habitación ofrece todo lo necesario para una experiencia agradable. Su diseño sencillo, junto con su iluminación cálida, crea un ambiente relajante perfecto para descansar después de un día de turismo o trabajo. Una elección práctica sin renunciar a la calidad.",
    price: "90€/noche",
    img: room5,
    people: 2,
    size: 25,
    wifi: "Gratis",
    beds: 1,
    bathrooms: 1,
    parking: false,
    pets: false,
    shortPeriod: "270€",
    mediumPeriod: "630€",
    longPeriod: "2700€"
  },
  {
    id: 6,
    name: "Suite Presidencial",
    description: "La máxima expresión de lujo con servicios exclusivos y mayordomo personal.",
    longDescription: "Nuestra Suite Presidencial representa el lujo más exclusivo del hotel. Pensada para ofrecer una experiencia de alto nivel, combina amplitud, diseño moderno y servicios premium, incluyendo atención personalizada de mayordomo. La suite cuenta con un dormitorio principal con cama king size, un gran salón independiente, comedor privado, dos baños completos y acabados de lujo en cada rincón. Las vistas desde la terraza privada ofrecen un espacio ideal para relajarse en un entorno sofisticado. Es la opción perfecta para ocasiones especiales o para quienes buscan una estancia verdaderamente excepcional.",
    price: "280€/noche",
    img: room6,
    people: 2,
    size: 35,
    wifi: "Gratis",
    beds: 2,
    bathrooms: 2,
    parking: true,
    pets: false,
    shortPeriod: "840€",
    mediumPeriod: "1960€",
    longPeriod: "8400€"
  },
  {
    id: 7,
    name: "Habitación con Jacuzzi",
    description: "Perfecta para huespedes que buscan relajarse con jacuzzi privado.",
    longDescription: "La Habitación con Jacuzzi ha sido diseñada para ofrecer una experiencia relajante. Su jacuzzi privado dentro de la habitación permite disfrutar de momentos únicos de bienestar. Cuenta con cama doble, iluminación ambiente, un diseño moderno y un baño completo con ducha independiente. Su amplia zona abierta y su ambiente íntimo la convierten en la opción perfecta para aniversarios, celebraciones especiales o fines de semana inolvidables.",
    price: "200€/noche",
    img: room7,
    people: 2,
    size: 50,
    wifi: "Gratis",
    beds: 1,
    bathrooms: 1,
    parking: true,
    pets: false,
    shortPeriod: "600€",
    mediumPeriod: "1400€",
    longPeriod: "6000€"
  },
  {
    id: 8,
    name: "Habitación con Terraza",
    description: "Cómoda y acogedora, ideal para estancias cortas con todas las comodidades básicas.",
    longDescription: "La Habitación con Terraza destaca por su acceso a una terraza privada, perfecta para disfrutar del aire libre sin salir de la habitación. Su diseño moderno y su ambiente luminoso crean un espacio acogedor ideal para parejas o viajeros que buscan tranquilidad. La terraza incluye mobiliario exterior para relajarse, desayunar o disfrutar de la noche. La habitación cuenta con cama doble, baño privado, climatización y todas las comodidades esenciales para una estancia cómoda y relajada.",
    price: "90€/noche",
    img: room8,
    people: 2,
    size: 25,
    wifi: "Gratis",
    beds: 1,
    bathrooms: 1,
    parking: false,
    pets: true,
    shortPeriod: "270€",
    mediumPeriod: "630€",
    longPeriod: "2700€"
  }
];

export default roomsData;
