import React from 'react';
import { useNavigate } from 'react-router-dom';
import hotelImage from '../assets/img/hotel-exterior.jpg';

const Hero = () => {
  const navigate = useNavigate();

  return (
    <section id="inicio" style={styles.section}>

      {/* --- FONDO (Imagen + Capa Oscura) --- */}
      <div style={styles.bgContainer}>
        <img
          src={hotelImage}
          alt="Hotel Exterior"
          style={styles.bgImage}
          onError={(e) => e.target.style.display = 'none'} // Si falla, se ve el fondo negro
        />
        <div style={styles.overlay}></div>
      </div>

      {/* --- CONTENIDO PRINCIPAL --- */}
      <div style={styles.content}>

        {/* Estrellas */}
        <div style={styles.starsContainer}>
          {[...Array(5)].map((_, i) => (
            // Si tienes lucide: <Star key={i} size={20} color="#f59e0b" fill="#f59e0b" />
            // Si no, usa esto:
            <span key={i} style={{ color: '#f59e0b', fontSize: '24px' }}>★</span>
          ))}
        </div>

        {/* Título y Texto */}
        <h1 style={styles.title}>
          Bienvenido al Grand Hotel & Resort
        </h1>

        <p style={styles.description}>
          Experimenta el lujo y la comodidad en nuestro hotel de 5 estrellas.
          Ubicado en el corazón de la ciudad, ofrecemos las mejores vistas y servicios exclusivos.
        </p>

        {/* Botones de Acción */}
        <div style={styles.buttonGroup}>
          <button
            style={styles.btnPrimary}
            onClick={() => navigate('/reservations')}
          >
            {/* <Calendar size={18} style={{marginRight: '8px'}} /> */}
            📅 Hacer Reserva
          </button>

          <button
            style={styles.btnOutline}
            onClick={() => navigate('/rooms')} // Asumiendo que crearás esta ruta
          >
            Ver Habitaciones
          </button>
        </div>
      </div>
    </section>
  );
};

// --- ESTILOS CSS-IN-JS (Imitando Tailwind) ---
const styles = {
  section: {
    position: 'relative',
    height: '100vh', // Ocupa toda la pantalla
    width: '100%',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    overflow: 'hidden',
    backgroundColor: '#1a1a1a',
  },
  bgContainer: {
    position: 'absolute',
    top: 0, left: 0, right: 0, bottom: 0,
    zIndex: 0
  },
  bgImage: {
    width: '100%',
    height: '100%',
    objectFit: 'cover',
  },
  overlay: {
    position: 'absolute',
    top: 0, left: 0, right: 0, bottom: 0,
    backgroundColor: 'rgba(0, 0, 0, 0.4)', // Oscurece la foto un 40%
  },
  content: {
    position: 'relative',
    zIndex: 10,
    textAlign: 'center',
    color: 'white',
    padding: '0 20px',
    maxWidth: '900px', // max-w-4xl
    marginTop: '60px', // Un poco de espacio extra para el header
  },
  starsContainer: {
    display: 'flex',
    justifyContent: 'center',
    gap: '5px',
    marginBottom: '15px'
  },
  title: {
    fontSize: '3rem',
    fontWeight: 'bold',
    marginBottom: '20px',
    lineHeight: 1.1,
    textShadow: '0 2px 10px rgba(0,0,0,0.5)'
  },
  description: {
    fontSize: '1.15rem',
    marginBottom: '40px',
    opacity: 0.9,
    maxWidth: '700px',
    marginLeft: 'auto',
    marginRight: 'auto',
    lineHeight: 1.6
  },
  buttonGroup: {
    display: 'flex',
    justifyContent: 'center',
    gap: '15px',
    marginBottom: '60px',
    flexWrap: 'wrap'
  },
  btnPrimary: {
    backgroundColor: '#b45309', // amber-700
    color: 'white',
    padding: '12px 24px',
    borderRadius: '8px',
    border: 'none',
    fontSize: '1rem',
    fontWeight: '600',
    cursor: 'pointer',
    display: 'flex',
    alignItems: 'center',
    gap: '8px',
    transition: 'transform 0.2s',
  },
  btnOutline: {
    backgroundColor: 'rgba(255, 255, 255, 0.1)',
    color: 'white',
    border: '1px solid white',
    padding: '12px 24px',
    borderRadius: '8px',
    fontSize: '1rem',
    fontWeight: '600',
    cursor: 'pointer',
    backdropFilter: 'blur(5px)',
    transition: 'background 0.2s'
  },
  cardsGrid: {
    display: 'grid',
    gridTemplateColumns: 'repeat(auto-fit, minmax(220px, 1fr))',
    gap: '20px',
    maxWidth: '800px',
    margin: '0 auto'
  },
  card: {
    backgroundColor: 'rgba(255, 255, 255, 0.1)', // Cristal esmerilado
    backdropFilter: 'blur(10px)',
    border: '1px solid rgba(255, 255, 255, 0.2)',
    padding: '20px',
    borderRadius: '12px',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    boxShadow: '0 4px 6px rgba(0,0,0,0.1)'
  },
  cardTitle: {
    fontSize: '1.1rem',
    fontWeight: 'bold',
    marginBottom: '5px',
    color: 'white'
  },
  cardText: {
    fontSize: '0.9rem',
    color: 'rgba(255,255,255,0.8)',
    margin: 0
  }
};

export default Hero;
