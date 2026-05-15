import React, { useState, useEffect } from "react";
import { Plus, Search, Edit2, Trash2, User, Mail, Phone, CreditCard, Save, X, Calendar } from "lucide-react";
import api from '../services/api';

const ClientsPage = () => {
  const [clients, setClients] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingId, setEditingId] = useState(null);
  const [showForm, setShowForm] = useState(false);

  const [formData, setFormData] = useState({
    dni: '',
    firstName: '',
    lastName: '',
    email: '',
    phone: ''
  });

  const fetchClients = async () => {
    try {
      setLoading(true);
      const response = await api.get('/clients');
      setClients(response.data);
      setError(null);
    } catch (err) {
      console.error(err);
      setError("No se pudieron cargar los clientes.");
    } finally {
      setLoading(false);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async () => {
    if (!formData.dni || !formData.email || !formData.firstName) {
      alert("Por favor, rellena los campos obligatorios (Nombre, DNI y Email).");
      return;
    }

    try {
      if (editingId) {
        await api.put(`/clients/${editingId}`, formData);
        alert("Cliente actualizado correctamente");
      } else {
        await api.post('/clients', formData);
        alert("Cliente creado correctamente");
      }

      setFormData({ dni: '', firstName: '', lastName: '', email: '', phone: '' });
      setEditingId(null);
      setShowForm(false);
      fetchClients();
    } catch (error) {
      console.error("Error:", error);
      alert("Error al guardar: " + (error.response?.data || error.message));
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm("¿Estás seguro de eliminar este cliente?")) return;

    try {
      await api.delete(`/clients/${id}`);
      alert("Cliente eliminado");
      fetchClients();
    } catch (error) {
      console.error(error);
      alert("Error al eliminar");
    }
  };

  const handleEdit = (client) => {
    setEditingId(client.id);
    setFormData({
      dni: client.dni,
      firstName: client.firstName,
      lastName: client.lastName,
      email: client.email,
      phone: client.phone
    });
    setShowForm(true);
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  useEffect(() => {
    fetchClients();
  }, []);

  return (
    <div className="min-h-screen bg-gray-50 font-sans pb-12">
      {/* Header de la Sección */}
      <div className="bg-white border-b border-gray-200 shadow-sm">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-6 flex flex-col sm:flex-row justify-between items-center gap-4">
          <div>
            <h1 className="text-2xl font-bold text-gray-900 flex items-center gap-2">
              <User className="text-amber-700" />
              Gestión de Clientes
            </h1>
            <p className="text-sm text-gray-500 mt-1">Administra la base de datos de huéspedes</p>
          </div>
          <button
            onClick={() => {
              setEditingId(null);
              setFormData({ dni: '', firstName: '', lastName: '', email: '', phone: '' });
              setShowForm(!showForm);
            }}
            className="flex items-center gap-2 bg-amber-700 hover:bg-amber-800 text-white px-5 py-2.5 rounded-lg font-medium transition-colors shadow-sm"
          >
            {showForm ? <X size={20} /> : <Plus size={20} />}
            {showForm ? "Cerrar Formulario" : "Nuevo Cliente"}
          </button>
        </div>
      </div>

      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        {/* Formulario de creación/edición */}
        {showForm && (
          <div className="bg-white rounded-xl shadow-md border border-gray-100 p-6 mb-8 animate-in fade-in slide-in-from-top-4 duration-300">
            <h2 className="text-lg font-semibold text-gray-800 mb-6 border-b pb-2">
              {editingId ? "Editar Cliente" : "Registrar Nuevo Cliente"}
            </h2>

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
              <div className="space-y-2">
                <label className="text-sm font-medium text-gray-700">Nombre</label>
                <div className="relative">
                  <User className="absolute left-3 top-2.5 h-5 w-5 text-gray-400" />
                  <input type="text" name="firstName" placeholder="Ej. Maria" value={formData.firstName} onChange={handleInputChange}
                    className="pl-10 w-full rounded-lg border border-gray-300 focus:ring-2 focus:ring-amber-500 focus:border-transparent py-2 transition-all" />
                </div>
              </div>

              <div className="space-y-2">
                <label className="text-sm font-medium text-gray-700">Apellidos</label>
                <div className="relative">
                  <User className="absolute left-3 top-2.5 h-5 w-5 text-gray-400" />
                  <input type="text" name="lastName" placeholder="Ej. García" value={formData.lastName} onChange={handleInputChange}
                    className="pl-10 w-full rounded-lg border border-gray-300 focus:ring-2 focus:ring-amber-500 focus:border-transparent py-2 transition-all" />
                </div>
              </div>

              <div className="space-y-2">
                <label className="text-sm font-medium text-gray-700">DNI / Documento</label>
                <div className="relative">
                  <CreditCard className="absolute left-3 top-2.5 h-5 w-5 text-gray-400" />
                  <input type="text" name="dni" placeholder="12345678Z" value={formData.dni} onChange={handleInputChange}
                    className="pl-10 w-full rounded-lg border border-gray-300 focus:ring-2 focus:ring-amber-500 focus:border-transparent py-2 transition-all" />
                </div>
              </div>

              <div className="space-y-2">
                <label className="text-sm font-medium text-gray-700">Email</label>
                <div className="relative">
                  <Mail className="absolute left-3 top-2.5 h-5 w-5 text-gray-400" />
                  <input type="email" name="email" placeholder="cliente@correo.com" value={formData.email} onChange={handleInputChange}
                    className="pl-10 w-full rounded-lg border border-gray-300 focus:ring-2 focus:ring-amber-500 focus:border-transparent py-2 transition-all" />
                </div>
              </div>

              <div className="space-y-2">
                <label className="text-sm font-medium text-gray-700">Teléfono</label>
                <div className="relative">
                  <Phone className="absolute left-3 top-2.5 h-5 w-5 text-gray-400" />
                  <input type="text" name="phone" placeholder="+34 600 000 000" value={formData.phone} onChange={handleInputChange}
                    className="pl-10 w-full rounded-lg border border-gray-300 focus:ring-2 focus:ring-amber-500 focus:border-transparent py-2 transition-all" />
                </div>
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

        {/* Tabla de Clientes */}
        <div className="bg-white rounded-xl shadow-sm border border-gray-200 overflow-hidden">
          {loading ? (
            <div className="p-12 text-center text-gray-500">Cargando clientes...</div>
          ) : error ? (
            <div className="p-12 text-center text-red-500 bg-red-50">{error}</div>
          ) : clients.length === 0 ? (
            <div className="p-12 text-center text-gray-500">No hay clientes registrados.</div>
          ) : (
            <div className="overflow-x-auto">
              <table className="w-full text-left border-collapse">
                <thead>
                  <tr className="bg-gray-50 border-b border-gray-100 text-xs uppercase tracking-wider text-gray-500">
                    <th className="px-6 py-4 font-semibold">ID</th>
                    <th className="px-6 py-4 font-semibold">Cliente</th>
                    <th className="px-6 py-4 font-semibold">Contacto</th>
                    <th className="px-6 py-4 font-semibold">DNI</th>
                    <th className="px-6 py-4 font-semibold">Registro</th>
                    <th className="px-6 py-4 font-semibold text-right">Acciones</th>
                  </tr>
                </thead>
                <tbody className="divide-y divide-gray-100">
                  {clients.map((client) => (
                    <tr key={client.id} className="hover:bg-amber-50/30 transition-colors group">
                      <td className="px-6 py-4 text-sm text-gray-500 font-mono">#{client.id}</td>
                      <td className="px-6 py-4">
                        <div className="flex items-center gap-3">
                          <div className="w-8 h-8 rounded-full bg-amber-100 text-amber-700 flex items-center justify-center font-bold text-xs">
                            {client.firstName?.charAt(0)}
                          </div>
                          <div>
                            <p className="font-medium text-gray-900">{client.firstName} {client.lastName}</p>
                          </div>
                        </div>
                      </td>
                      <td className="px-6 py-4">
                        <div className="flex flex-col text-sm text-gray-600 gap-1">
                          <span className="flex items-center gap-1.5"><Mail size={14} /> {client.email}</span>
                          {client.phone && <span className="flex items-center gap-1.5"><Phone size={14} /> {client.phone}</span>}
                        </div>
                      </td>
                      <td className="px-6 py-4">
                        <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-gray-100 text-gray-800">
                          {client.dni}
                        </span>
                      </td>
                      <td className="px-6 py-4 text-sm text-gray-500">
                        <div className="flex items-center gap-1.5">
                          <Calendar size={14} />
                          {client.registrationDate ? new Date(client.registrationDate).toLocaleDateString() : 'N/A'}
                        </div>
                      </td>
                      <td className="px-6 py-4 text-right">
                        <div className="flex justify-end gap-2 opacity-0 group-hover:opacity-100 transition-opacity">
                          <button onClick={() => handleEdit(client)} className="p-1.5 text-gray-500 hover:text-amber-700 hover:bg-amber-50 rounded-md transition-colors" title="Editar">
                            <Edit2 size={18} />
                          </button>
                          <button onClick={() => handleDelete(client.id)} className="p-1.5 text-gray-500 hover:text-red-600 hover:bg-red-50 rounded-md transition-colors" title="Eliminar">
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

export default ClientsPage;
