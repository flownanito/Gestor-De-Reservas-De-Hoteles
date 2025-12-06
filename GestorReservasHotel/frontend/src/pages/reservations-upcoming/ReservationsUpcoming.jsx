import reservationsData from "../../data/ReservationsData";
import ReservationCard from "../../components/reservation-card/ReservationCard";

import "./ReservationsUpcoming.css";

const ReservationsUpcoming = () => {
  const handleCancel = (id) => {
    console.log("Cancel reservation:", id);
  };

  return (
    <div className="my-reservations">
      <div className="my-reservations-tittle">
        <h1><b>Mis Reservas</b></h1>
      </div>
      {reservationsData.map((res) => (
        <ReservationCard
          key={res.id}
          reservation={res}
          onCancel={handleCancel}
        />
      ))}
    </div>
  );
};

export default ReservationsUpcoming;
