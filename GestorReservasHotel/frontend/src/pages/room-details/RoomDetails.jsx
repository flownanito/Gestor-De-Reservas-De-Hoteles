import { useParams } from "react-router-dom";
import roomsData from "../../data/Rooms"; // 🔑 IMPORTAR tus datos correctamente

import "./RoomDetails.css";

function RoomDetails() {
  const { id } = useParams(); // obtiene el id de la URL
  const room = roomsData.find((r) => r.id === parseInt(id));

  if (!room) return <p>Habitación no encontrada</p>;

  return (
    <div className="details">

      <div className="details-tittle">
        <h1><b>Detalles</b></h1>
      </div>

      <div className="content-wrapper">
        {/* LEFT SIDE → IMAGE + INFO */}
        <div className="left-column">
          <img className="details-image" src={room.img} alt={room.name} />

          <div className="room-features">
            <h1><b>{room.name}</b></h1>
            <div className="features-container">

              <div className="features-row">
                <div className="feature-item">
                  <span className="icon">🛏</span>
                  <p>{room.beds} cama(s)</p>
                </div>

                <div className="feature-item">
                  <span className="icon">🛁</span>
                  <p>{room.bathrooms} baño(s)</p>
                </div>

                <div className="feature-item">
                  <span className="icon">🚗</span>
                  <p>{room.parking ? "Parking disponible" : "Sin parking"}</p>
                </div>
              </div>

              <div className="features-row">
                <div className="feature-item">
                  <span className="icon">🐶</span>
                  <p>{room.pets ? "Mascotas permitidas" : "No se permiten mascotas"}</p>
                </div>

                <div className="feature-item">
                  <span className="icon">👥</span>
                  <p>Máx. {room.people} huéspedes</p>
                </div>

                <div className="feature-item">
                  <span className="icon">🛜</span>
                  <p>Wi-Fi: {room.wifi}</p>
                </div>
              </div>

            </div>
          </div>

          <div className="description">
            <h3><b>Descripción</b></h3>
            <p>{room.longDescription}</p>
          </div>

          <div className="safety-section">
            <h3><b>Seguridad e Higiene</b></h3>
            <ul className="safety-list">
              <li>Limpieza Diaria</li>
              <li>Desinfecciones y Esterilizaciones</li>
              <li>Extintores de Incendios</li>
              <li>Detectores de Humo</li>
            </ul>
          </div>
        </div>

        {/* RIGHT SIDE → PRICE CARD */}
        <div className="price-card">
          <h2 className="price-title">{room.name} - {room.price}</h2>

          <div className="price-periods">
            <p>Periodo corto: 3 noches {room.shortPeriod}</p>
            <p>Periodo medio: 7 noches {room.mediumPeriod}</p>
            <p>Periodio largo: 30 noches  {room.longPeriod}</p>
          </div>

          <button className="reserve-btn">Reserve Now</button>
        </div>
      </div>
    </div>

  );
}

export default RoomDetails;