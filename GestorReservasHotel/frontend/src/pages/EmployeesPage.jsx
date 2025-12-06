import React, { useState, useEffect } from "react";
import { Plus, Search, Edit2, Trash2, User, Mail, Phone, Briefcase, Save, X } from "lucide-react";
import api from '../services/api'; // Asegúrate de importar tu instancia de axios

const EmployeesPage = () => {
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingId, setEditingId] = useState(null);
  const [positions, setPositions] = useState([]);

  // Estado para controlar si mostramos el formulario
  const [showForm, setShowForm] = useState(false);

  const [formData, setFormData] = useState({
    position: '',
    name: '',
    lastName: '',
    email: '',
    password: '',
    role: 'USER',
  });

  // --- LÓGICA (Mantenemos la que ya funcionaba) ---

  const fetchEmployees = async () => {
    try {
      setLoading(true);
      // Usamos api.get en lugar de fetch directo para aprovechar la config base
      const response = await api.get('/employees');
      setEmployees(response.data);
      setError(null);
    } catch (err) {
      console.error(err);
      setError("No se pudieron cargar los empleados.");
    } finally {
      setLoading(false);
    }
  };

  const fetchPositions = async () => {
    try {
      const response = await api.get('/positions'); // Asumiendo que tienes PositionController
      setPositions(response.data);
    } catch (err) {
      console.error("Error cargando puestos:", err);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async () => {
    if (!formData.name || !formData.lastName || !formData.email) {
      alert("Por favor, rellena los campos obligatorios.");
      return;
    }

    // Preparamos los datos (el fix del position null que hicimos)
    const dataToSend = {
      ...formData,
      position: formData.position ? { id: parseInt(formData.position) } : null
    };

    try {
      if (editingId) {
        await api.put(`/employees/${editingId}`, dataToSend);
        alert("Empleado actualizado correctamente");
      } else {
        await api.post('/employees', dataToSend);
        alert("Empleado creado correctamente");
      }

      // Limpieza y recarga
      setFormData({ position: '', name: '', lastName: '', email: '', password: '', role: 'USER' });
      setEditingId(null);
      setShowForm(false); // Cerramos el formulario al terminar
      fetchEmployees();

    } catch (error) {
      console.error("Error:", error);
      alert("Error al guardar: " + (error.response?.data || error.message));
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm("¿Estás seguro de eliminar este empleado?")) return;

    try {
      await api.delete(`/employees/${id}`);
      alert("Empleado eliminado");
      fetchEmployees();
    } catch (error) {
      console.error(error);
      alert("Error al eliminar");
    }
  };

  const handleEdit = (employee) => {
    setEditingId(employee.id);
    setFormData({
      position: employee.position?.id || '',
      name: employee.name,
      lastName: employee.lastName,
      email: employee.email,
      password: '', // Dejamos vacía por seguridad
      role: employee.role || 'USER'
    });
    setShowForm(true); // Abrimos el formulario automáticamente
    window.scrollTo({ top: 0, behavior: 'smooth' }); // Subimos para ver el form
  };

  useEffect(() => {
    fetchEmployees();
    fetchPositions();
  }, []);

  // --- RENDERIZADO (UI NUEVA) ---

  return (
    <div className="min-h-screen bg-gray-50 font-sans pb-12">

      {/* Header de la Sección */}
      <div className="bg-white border-b border-gray-200 shadow-sm">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-6 flex flex-col sm:flex-row justify-between items-center gap-4">
          <div>
            <h1 className="text-2xl font-bold text-gray-900 flex items-center gap-2">
              <Briefcase className="text-amber-700" />
              Gestión de Empleados
            </h1>
            <p className="text-sm text-gray-500 mt-1">Administra el equipo y sus permisos</p>
          </div>
          <button
            onClick={() => {
              setEditingId(null);
              setFormData({ position: '', name: '', lastName: '', email: '', password: '', role: 'USER' });
              setShowForm(!showForm);
            }}
            className="flex items-center gap-2 bg-amber-700 hover:bg-amber-800 text-white px-5 py-2.5 rounded-lg font-medium transition-colors shadow-sm"
          >
            {showForm ? <X size={20} /> : <Plus size={20} />}
            {showForm ? "Cerrar Formulario" : "Nuevo Empleado"}
          </button>
        </div>
      </div>

      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">

        {/* --- FORMULARIO (Colapsable) --- */}
        {showForm && (
          <div className="bg-white rounded-xl shadow-md border border-gray-100 p-6 mb-8 animate-in fade-in slide-in-from-top-4 duration-300">
            <h2 className="text-lg font-semibold text-gray-800 mb-6 border-b pb-2">
              {editingId ? "Editar Empleado" : "Registrar Nuevo Empleado"}
            </h2>

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
              {/* Inputs estilizados */}
              <div className="space-y-2">
                <label className="text-sm font-medium text-gray-700">Nombre</label>
                <div className="relative">
                  <User className="absolute left-3 top-2.5 h-5 w-5 text-gray-400" />
                  <input type="text" name="name" placeholder="Ej. Juan" value={formData.name} onChange={handleInputChange}
                    className="pl-10 w-full rounded-lg border border-gray-300 focus:ring-2 focus:ring-amber-500 focus:border-transparent py-2 transition-all" />
                </div>
              </div>

              <div className="space-y-2">
                <label className="text-sm font-medium text-gray-700">Apellidos</label>
                <div className="relative">
                  <User className="absolute left-3 top-2.5 h-5 w-5 text-gray-400" />
                  <input type="text" name="lastName" placeholder="Ej. Pérez" value={formData.lastName} onChange={handleInputChange}
                    className="pl-10 w-full rounded-lg border border-gray-300 focus:ring-2 focus:ring-amber-500 focus:border-transparent py-2 transition-all" />
                </div>
              </div>

              <div className="space-y-2">
                <label className="text-sm font-medium text-gray-700">Email</label>
                <div className="relative">
                  <Mail className="absolute left-3 top-2.5 h-5 w-5 text-gray-400" />
                  <input type="email" name="email" placeholder="correo@hotel.com" value={formData.email} onChange={handleInputChange}
                    className="pl-10 w-full rounded-lg border border-gray-300 focus:ring-2 focus:ring-amber-500 focus:border-transparent py-2 transition-all" />
                </div>
              </div>

              <div className="space-y-2">
                <label className="text-sm font-medium text-gray-700">Contraseña</label>
                <input type="password" name="password" placeholder="••••••" value={formData.password} onChange={handleInputChange}
                  className="w-full rounded-lg border border-gray-300 focus:ring-2 focus:ring-amber-500 focus:border-transparent py-2 px-4 transition-all" />
              </div>

              <div className="space-y-2">
                <label className="text-sm font-medium text-gray-700">Rol</label>
                <select name="role" value={formData.role} onChange={handleInputChange}
                  className="w-full rounded-lg border border-gray-300 focus:ring-2 focus:ring-amber-500 focus:border-transparent py-2 px-3 bg-white transition-all">
                  <option value="USER">Recepcionista</option>
                  <option value="ADMIN">Administrador</option>
                </select>
              </div>

              <div className="space-y-2">
                <label className="text-sm font-medium text-gray-700">Puesto</label>
                <select
                  name="position"
                  value={formData.position}
                  onChange={handleInputChange}
                  className="w-full px-4 py-2 rounded-lg border border-gray-300 focus:ring-2 focus:ring-amber-500 focus:border-transparent outline-none transition-all bg-white"
                >
                  <option value="">Selecciona un puesto</option>
                  {positions.map(pos => (
                    <option key={pos.id} value={pos.id}>
                      {pos.positionName}
                    </option>
                  ))}
                </select>
              </div>
            </div>

            <div className="mt-8 flex justify-end gap-3">
              <button onClick={() => setShowForm(false)} className="px-4 py-2 text-gray-600 hover:bg-gray-100 rounded-lg font-medium transition-colors">
                Cancelar
              </button>
              <button onClick={handleSubmit} className="px-6 py-2 bg-amber-700 hover:bg-amber-800 text-white rounded-lg font-medium shadow-sm transition-colors flex items-center gap-2">
                <Save size={18} />
                {editingId ? "Actualizar" : "Guardar"}
              </button>
            </div>
          </div>
        )}

        {/* --- LISTA (Tabla) --- */}
        <div className="bg-white rounded-xl shadow-sm border border-gray-200 overflow-hidden">
          {loading ? (
            <div className="p-12 text-center text-gray-500">Cargando datos...</div>
          ) : error ? (
            <div className="p-12 text-center text-red-500 bg-red-50">{error}</div>
          ) : employees.length === 0 ? (
            <div className="p-12 text-center text-gray-500">No hay empleados registrados.</div>
          ) : (
            <div className="overflow-x-auto">
              <table className="w-full text-left border-collapse">
                <thead>
                  <tr className="bg-gray-50 border-b border-gray-100 text-xs uppercase tracking-wider text-gray-500">
                    <th className="px-6 py-4 font-semibold">ID</th>
                    <th className="px-6 py-4 font-semibold">Empleado</th>
                    <th className="px-6 py-4 font-semibold">Contacto</th>
                    <th className="px-6 py-4 font-semibold">Rol</th>
                    <th className="px-6 py-4 font-semibold">Puesto</th>
                    <th className="px-6 py-4 font-semibold text-right">Acciones</th>
                  </tr>
                </thead>
                <tbody className="divide-y divide-gray-100">
                  {employees.map((emp) => (
                    <tr key={emp.id} className="hover:bg-amber-50/30 transition-colors group">
                      <td className="px-6 py-4 text-sm text-gray-500 font-mono">#{emp.id}</td>
                      <td className="px-6 py-4">
                        <div className="flex items-center gap-3">
                          <div className="w-8 h-8 rounded-full bg-amber-100 text-amber-700 flex items-center justify-center font-bold text-xs">
                            {emp.name.charAt(0)}
                          </div>
                          <div>
                            <p className="font-medium text-gray-900">{emp.name} {emp.lastName}</p>
                          </div>
                        </div>
                      </td>
                      <td className="px-6 py-4">
                        <div className="flex flex-col text-sm text-gray-600 gap-1">
                          <span className="flex items-center gap-1.5"><Mail size={14} /> {emp.email}</span>
                          {emp.phone && <span className="flex items-center gap-1.5"><Phone size={14} /> {emp.phone}</span>}
                        </div>
                      </td>
                      <td className="px-6 py-4">
                        <span className={`inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium ${emp.role === 'ADMIN' ? 'bg-purple-100 text-purple-800' : 'bg-green-100 text-green-800'
                          }`}>
                          {emp.role}
                        </span>
                      </td>
                      <td className="px-6 py-4 text-sm text-gray-600">
                        {emp.position?.positionName || <span className="italic text-gray-400">Sin asignar</span>}
                      </td>
                      <td className="px-6 py-4 text-right">
                        <div className="flex justify-end gap-2 opacity-0 group-hover:opacity-100 transition-opacity">
                          <button onClick={() => handleEdit(emp)} className="p-1.5 text-gray-500 hover:text-amber-700 hover:bg-amber-50 rounded-md transition-colors" title="Editar">
                            <Edit2 size={18} />
                          </button>
                          <button onClick={() => handleDelete(emp.id)} className="p-1.5 text-gray-500 hover:text-red-600 hover:bg-red-50 rounded-md transition-colors" title="Eliminar">
                            <Trash2 size={18} />
                          </button>
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default EmployeesPage;
