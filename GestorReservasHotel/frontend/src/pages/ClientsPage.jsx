
import React, { useState, useEffect } from "react";

const ClientsPage = () => {
  // State: Estado para guardar la lista de clientes (incialmente una lista vacía)
  const [clients, setClients] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingId, setEditingId] = useState(null);

  const [formData, setFormData] = useState({
    dni: '',
    firstName: '',
    lastName: '',
    email: '',
    phone: ''
  });

  const fetchClients = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/clients');

      if (!response.ok) {
        throw new Error('Error en la petición al servidor');
      }

      const data = await response.json();
      setClients(data);
      setError(null);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  // Manejar cambios en el formulario
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData, // Copia todo lo anterior (Spread)
      [name]: value
    });
  };

  // Función para enviar el cliente nuevo
  const handleSubmit = async () => {
    if (!formData.dni || !formData.email) {
      alert("Rellene el DNI y el Email");
      return;
    }

    let url = 'http://localhost:8080/api/clients';
    let method = 'POST';

    if (editingId) {
      method = 'PUT';
      url = `http://localhost:8080/api/clients/${editingId}`;
    }

    try {
      const response = await fetch(url, {
        method: method,
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText || "Error al crear cliente");
      }

      alert(editingId ? "Cliente actualizado" : "Cliente creado");

      setFormData({ dni: '', firstName: '', lastName: '', email: '', phone: '' });
      setEditingId(null);
      fetchClients();

    } catch (error) {
      console.error("Error: ", error);
      alert("Fallo al guardar: " + error.message);
    }
  }

  const handleDelete = async (id) => {
    if (!window.confirm("Estas seguro de que quieres eliminar este cliente")) {
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/api/clients/${id}`, {
        method: 'DELETE',
      });

      if (!response.ok) {
        throw new Error("No se pudo eliminar al cliente");
      }

      alert("Cliente eliminado");
      fetchClients();

    } catch (error) {
      console.error("Error deleting:", error);
      alert("Error al borrar: " + error.message);
    }
  };

  const handleEdit = (client) => {
    setEditingId(client.id || client.clientId);
    setFormData({
      dni: client.dni,
      firstName: client.firstName,
      lastName: client.lastName,
      email: client.email,
      phone: client.phone
    });
  };

  // Cargar datos al iniciar la página
  useEffect(() => {
    fetchClients();
  }, []); // El array vacío significa que solo se ejecuta una vez al montar

  if (loading) return <div>Cargando...</div>;
  if (error) return <div style={{ color: 'red' }}>Error: {error}</div>;

  return (
    <div>
      <h2>Gestión de Clientes</h2>

      {/* Formulario de creación */}
      <div>
        <h3>Añadir nuevo cliente</h3>

        {/* Input para el nombre */}
        <input type="text" name="firstName" placeholder="Nombre" value={formData.firstName} onChange={handleInputChange} />
        {/* Input para el apellido */}
        <input type="text" name="lastName" placeholder="Apellido" value={formData.lastName} onChange={handleInputChange} />
        {/* Input para el DNI */}
        <input type="text" name="dni" placeholder="DNI" value={formData.dni} onChange={handleInputChange} />
        {/* Input para el email */}
        <input type="email" name="email" placeholder="Email" value={formData.email} onChange={handleInputChange} />
        {/* Input para el Telefono */}
        <input type="number" name="phone" placeholder="Teléfono" value={formData.phone} onChange={handleInputChange} />

        <button onClick={handleSubmit}>
          Guardar Cliente
        </button>
      </div>

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
            <tr key={client.id || client.clientId}>
              <td>{client.id || client.clientId}</td>
              <td>{client.firstName}</td>
              <td>{client.lastName}</td>
              <td>{client.email}</td>
              <td>{client.phone}</td>
              <td>{client.registrationDate}</td>
              <td style={{ textAlign: 'center' }}>
                <button
                  onClick={() => handleDelete(client.id || client.clientId)}
                  style={{
                    backgroundColor: '#ff4444',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    cursor: 'pointer',
                    borderRadius: '4px'
                  }}
                >
                  Borrar
                </button>
                <button
                  onClick={() => handleEdit(client)}
                  style={{
                    backgroundColor: '#ff4444',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    cursor: 'pointer',
                    borderRadius: '4px'
                  }}
                >
                  Actualizar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ClientsPage;
