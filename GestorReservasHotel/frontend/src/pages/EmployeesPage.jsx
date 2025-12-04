
import React, { useState, useEffect } from "react";
import '../styles/TableStyles.css';

const EmployeesPage = () => {
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingId, setEditingId] = useState(null);

  const [formData, setFormData] = useState({
    position: '',
    name: '',
    lastName: '',
    email: '',
    password: '',
    role: 'USER',
  });

  const fetchEmployees = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/employees')

      if (!response.ok) {
        throw new Error("Error en la petición al servidor");
      }

      const data = await response.json();
      setEmployees(data);
      setError(null);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleSubmit = async () => {
    if (!formData.name || !formData.lastName || !formData.email) {
      alert("Tiene que rellenar los campos Nombre, Apellido y Email");
      return;
    }

    const dataToSend = {
      ...formData,
      position: formData.position ? { id: parseInt(formData.position) } : null
    };

    console.log("Datos nuevos:", JSON.stringify(dataToSend));

    let url = 'http://localhost:8080/api/employees';
    let method = 'POST';

    if (editingId) {
      method = 'PUT';
      url = `http://localhost:8080/api/employees/${editingId}`;
    }

    try {
      const response = await fetch(url, {
        method: method,
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(dataToSend),
      });

      if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText || "Error al crear al empleado");
      }

      alert(editingId ? "Empleado actualizado" : "Empleado creado");

      setFormData({ position: '', name: '', lastName: '', email: '', password: '', role: 'USER' });
      setEditingId(null);
      fetchEmployees();

    } catch (error) {
      console.error("Error: ", error);
      alert("Fallo al guardar: " + error.message);
    }
  }

  const handleDelete = async (id) => {
    if (!window.confirm("Estas seguro de que quieres eliminar este empleado")) {
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/api/employees/${id}`, {
        method: 'DELETE',
      });

      if (!response.ok) {
        throw new Error("No se pudo eliminar al empleado");
      }

      alert("Empleado eliminado");
      fetchEmployees();

    } catch (error) {
      console.error("Error deleting: ", error);
      alert("Error al borrar: " + error.message);
    }
  };

  const handleEdit = (employee) => {
    setEditingId(employee.id);
    setFormData({
      position: employee.position,
      name: employee.name,
      lastName: employee.lastName,
      email: employee.email,
      password: '',
      role: employee.role || 'USER'
    });
  };

  useEffect(() => {
    fetchEmployees();
  }, []);

  if (loading) return <div>Cargando...</div>;
  if (error) return <div style={{ color: 'red' }}>Error: {error}</div>;

  return (
    <div className="table-container">
      <div className="header-flex">
        <h2>Gestión de Empleados</h2>
      </div>

      <div className="form-row">
        <div className="form-group">
          <label>Nombre</label>
          <input type="text" name="name" placeholder="Ej. Ana" value={formData.name} onChange={handleInputChange} />
        </div>

        <div className="form-group">
          <label>Apellidos</label>
          <input type="text" name="lastName" placeholder="Ej. García" value={formData.lastName} onChange={handleInputChange} />
        </div>

        <div className="form-group">
          <label>Email</label>
          <input type="email" name="email" placeholder="ana@hotel.com" value={formData.email} onChange={handleInputChange} />
        </div>

        <div className="form-group">
          <label>Contraseña</label>
          <input type="password" name="password" placeholder="******" value={formData.password} onChange={handleInputChange} />
        </div>

        <div className="form-group">
          <label>Rol</label>
          <select name="role" value={formData.role} onChange={handleInputChange}>
            <option value="USER">Recepcionista</option>
            <option value="ADMIN">Administrador</option>
          </select>
        </div>

        <div className="form-group" style={{ flex: 'none' }}> {/* flex: none para que no se estire */}
          <label>ID Puesto</label>
          <input type="number" name="position" placeholder="ID" value={formData.position} onChange={handleInputChange} />
        </div>

        <button className="btn btn-primary" onClick={handleSubmit}>
          {editingId ? "Actualizar" : "Añadir"}
        </button>
      </div>

      <table className="styled-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Email</th>
            <th>Puesto</th>
            <th style={{ textAlign: 'center' }}>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {employees.map(employee => (
            <tr key={employee.id}>
              <td>{employee.id}</td>
              <td>{employee.name}</td>
              <td>{employee.lastName}</td>
              <td>{employee.email}</td>
              <td>{employee.position?.positionName || 'Sin puesto'}</td>
              <td style={{ textAlign: 'center' }}>
                <button
                  className="btn btn-danger"
                  onClick={() => handleDelete(employee.id)}
                >
                  Borrar
                </button>
                <button
                  className="btn btn-edit"
                  onClick={() => handleEdit(employee)}
                >
                  Editar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default EmployeesPage;
