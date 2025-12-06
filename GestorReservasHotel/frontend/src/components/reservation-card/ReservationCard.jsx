import "./ReservationCard.css";

const ReservationCard = ({ reservation, onCancel }) => {
  const { roomName, img, checkIn, nights, guests, totalPrice } = reservation;

  return (
    <div className="reservation-card">
      <img className="reservation-img" src={img} alt={roomName} />

      <div className="reservation-info">
        <h3>{roomName}</h3>

        <div className="reservation-details">
          <p><strong>Check In:</strong> {checkIn}</p>
          <p><strong>Duration:</strong> {nights} nights</p>
          <p><strong>Guests:</strong> {guests}</p>
        </div>

        <p className="reservation-price">{totalPrice}</p>
      </div>

      <div className="reservation-actions">
        <button className="reservation-details-btn">
          Ver detalles
        </button>

        <button className="reservation-cancel" onClick={() => onCancel(reservation.id)}>
          Cancel Reservation
        </button>
      </div>
    </div>
  );
};

export default ReservationCard;
