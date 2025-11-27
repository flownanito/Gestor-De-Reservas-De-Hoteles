
import React, { useState, useEffect } from "react";

const ClientsPage = () => {
  // State: Estado para guardar la lista de clientes (incialmente una lista vacía)
  const [clients, setClients] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Effect: Hook que se ejecuta al cargar el componente
  useEffect(() => {
    // Funcino para cargar los datos del Backend
    const fetchClients = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/clients');

        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        setClients(data); // Actualizamos el estado con la lista de clientes
      } catch (error) {
        console.error("Error fetching clientes:", error);
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchClients();
  }, []) // El array vacío significa que solo se ejecuta una vez al montar

  if (error) {
    return <div>Error al cargar datos: {error}</div>;
  }

  // Render: Estructura de HTML puro para mostrar los datos
  if (loading) {
    return <div>Cargando datos del cliente...</div>
  }

  return (
    <div>
      <h2>Gestión de Clientes</h2>

      <table border="1" style={{ width: '100%', borderCollapse: 'collapse' }}>
        <thead>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Email</th>
            <th>Fecha Registro</th>
          </tr>
        </thead>
        <tbody>
          {clients.map(client => (
            <tr key={client.clientId}>
              <td>{client.clientId}</td>
              <td>{client.firstName}</td>
              <td>{client.lastName}</td>
              <td>{client.email}</td>
              <td>{client.registrationDate}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ClientsPage;
