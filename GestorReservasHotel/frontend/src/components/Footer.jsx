
import React from 'react';

const Footer = () => {
  return (
    <footer style={{ width: '100%', background: '#1a1a1a', color: '#ccc', padding: '40px 0' }}>
      <div style={{ maxWidth: '1200px', margin: '0 auto', display: 'flex', justifyContent: 'space-between' }}>

        {/* Columna 1: Logo y Eslogan (Según diseño Figma) */}
        <div style={{ width: '30%' }}>
          {/* Placeholder para el Logo */}
          <h3 style={{ color: 'white' }}>CODE HOTEL & RESORT</h3>
          <p style={{ fontSize: '0.9em' }}>
            Experiencia de lujo incomparable en el corazón de la ciudad. Tu hogar lejos de casa.
          </p>
          {/* Iconos de Redes Sociales aquí (Facebook, Twitter, Instagram) */}
        </div>

        {/* Columna 2: Enlaces */}
        <div style={{ width: '30%' }}>
          <h4 style={{ color: 'white' }}>Enlaces</h4>
          <ul style={{ listStyle: 'none', padding: 0 }}>
            <li>Sobre nosotros</li>
            <li>Habitaciones y Suites</li>
            <li>Mis Reservas</li>
            <li>Mi Perfil</li>
          </ul>
        </div>

        {/* Columna 3: Contacto */}
        <div style={{ width: '30%' }}>
          <h4 style={{ color: 'white' }}>Contacto</h4>
          <p>📍 123 Avenida Principal</p>
          <p>📞 +1 (234) 567-89</p>
          <p>📧 info@codehotel.com</p>
        </div>
      </div>

      {/* Copyright Bar */}
      <div style={{ textAlign: 'center', fontSize: '0.8em', padding: '20px 0', borderTop: '1px solid #333' }}>
        © 2025 Code Hotel & Resort. Todos los derechos reservados.
      </div>
    </footer>
  );
};

export default Footer;
