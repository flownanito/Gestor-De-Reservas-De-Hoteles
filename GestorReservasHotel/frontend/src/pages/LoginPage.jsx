
import React, { useState } from 'react';

const LoginPage = ({ onLoginSuccess }) => {
  const [credentials, setCredentials] = useState({
    email: '',
    password: ''
  });

  const handleChange = (e) => {
    setCredentials({
      ...credentials,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = () => {
    // Comprobamos si es admin
    if (credentials.email === 'admin@hotel.com' && credentials.password === '1234') {
      const mockUser = {
        email: credentials.email,
        role: 'ADMIN',
        name: 'Super Admin'
      };
      // Avisamos a App.jsx que hemos entrado
      onLoginSuccess(mockUser);
    }
    // Comprobamos si es un Recepcionista normal
    else if (credentials.email === 'user@hotel.com' && credentials.password === '1234') {
      const mockUser = {
        email: credentials.email,
        role: 'USER',
        name: 'Recepcionista'
      };
      onLoginSuccess(mockUser);
    }
    // Si no coincide, error
    else {
      alert("Error, Prueba con: admin@hotel.com / 1234");
    }
  };

  return (
    <div style={{ padding: '50px', maxWidth: '400px', margin: 'auto', textAlign: 'center' }}>
      <h2>Portal del Hotel</h2>
      <p>Acceso para clientes y personal</p>

      <div style={{ marginBottom: '10px', textAlign: 'left' }}>
        <label>Correo electrónico</label>
        <input
          type="email"
          name="email"
          placeholder="admin@hotel.com"
          value={credentials.email}
          onChange={handleChange}
          style={{ width: '100%', padding: '8px', marginTop: '5px' }}
        />
      </div>

      <div style={{ marginBottom: '20px', textAlign: 'left' }}>
        <label>Contraseña</label>
        <input
          type="password"
          name="password"
          placeholder="1234"
          value={credentials.password}
          onChange={handleChange}
          style={{ width: '100%', padding: '8px', marginTop: '5px' }}
        />
      </div>

      <button
        onClick={handleSubmit}
        style={{ width: '100%', padding: '10px', backgroundColor: '#333', color: 'white', cursor: 'pointer' }}
      >
        Iniciar sesión
      </button>
    </div>
  );
};

export default LoginPage;
