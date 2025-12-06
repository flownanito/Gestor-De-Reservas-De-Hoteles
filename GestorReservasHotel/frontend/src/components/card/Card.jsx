import { Link } from "react-router-dom";
import "./Card.css";

function Card({ id, name, description, price, img, people, size, wifi }) {
  return (
    <div className="card">
      <div
        className="card-image"
        style={{
          backgroundImage: img ? `url(${img})` : "none",
          backgroundColor: img ? "transparent" : "#eaeaea",
        }}
      >
        {price && <span className="price-badge">{price}</span>}

        {!img && <div className="no-image">Sin imagen</div>}
      </div>

      <div className="card-body">
        <h3 className="title">{name}</h3>

        <p className="description">{description}</p>

        <div className="info-row">
          <div className="info-item">
            <span className="icon">👤</span> {people}
          </div>

          <div className="info-item">
            <span className="icon">▢</span> {size} m²
          </div>

          <div className="info-item">
            <span className="icon">🛜</span> {wifi}
          </div>
        </div>

        <button className="details-btn">
          <Link to={`/rooms/${id}`} className="btn-link">
            Ver Detalles
          </Link>
        </button>
      </div>
    </div>
  );
}

export default Card