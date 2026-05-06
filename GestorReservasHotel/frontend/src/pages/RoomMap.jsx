import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../services/api';
import {
    XCircle, CheckCircle2, Info, MapPin, Star, Users, DollarSign, Bed
} from 'lucide-react';

const RoomMap = () => {
    const navigate = useNavigate();
    const [rooms, setRooms] = useState([]);
    const [loading, setLoading] = useState(true);
    const [selectedRoom, setSelectedRoom] = useState(null);

    // Cambiar precios de las habitaciones 
    const MOCK_ROOMS = [
        { id: 1, roomNumber: "101", roomState: { stateName: 'Libre' }, roomType: { typeName: 'Individual', basePrice: 80, capacity: 1 }, x: 55, y: 40 },
        { id: 2, roomNumber: "102", roomState: { stateName: 'Ocupada' }, roomType: { typeName: 'Doble', basePrice: 120, capacity: 2 }, x: 185, y: 40 },
        { id: 3, roomNumber: "103", roomState: { stateName: 'Libre' }, roomType: { typeName: 'Suite', basePrice: 250, capacity: 2 }, x: 315, y: 40 },
        { id: 4, roomNumber: "104", roomState: { stateName: 'Libre' }, roomType: { typeName: 'Libre', basePrice: 180, capacity: 4 }, x: 445, y: 40 },
        { id: 5, roomNumber: "105", roomState: { stateName: 'Limpieza' }, roomType: { typeName: 'Individual', basePrice: 85, capacity: 1 }, x: 55, y: 200 },
        { id: 6, roomNumber: "106", roomState: { stateName: 'Ocupada' }, roomType: { typeName: 'Doble', basePrice: 130, capacity: 2 }, x: 185, y: 200 },
        { id: 7, roomNumber: "107", roomState: { stateName: 'Libre' }, roomType: { typeName: 'Deluxe', basePrice: 160, capacity: 2 }, x: 315, y: 200 },
        { id: 8, roomNumber: "108", roomState: { stateName: 'Libre' }, roomType: { typeName: 'Individual', basePrice: 75, capacity: 1 }, x: 445, y: 200 },
    ];

    useEffect(() => {
        const fetchRooms = async () => {
            try {
                console.log("Fetching rooms from backend...");
                const response = await api.get('/rooms');
                console.log("Backend response:", response.data);
                if (response.data && Array.isArray(response.data) && response.data.length > 0) {
                    // Mapear habitaciones reales a los huecos del SVG
                    const mapped = response.data.slice(0, 8).map((r, i) => ({
                        ...r,
                        x: MOCK_ROOMS[i].x,
                        y: MOCK_ROOMS[i].y
                    }));
                    setRooms(mapped);
                } else {
                    console.warn("Backend empty or 200 invalid, using fallback.");
                    setRooms(MOCK_ROOMS);
                }
            } catch (error) {
                console.error("Critical API Error, using fallback:", error);
                setRooms(MOCK_ROOMS);
            } finally {
                setLoading(false);
            }
        };
        fetchRooms();
    }, []);

    const handleStatusChange = async (roomId, newState) => {
        try {
            console.log(`Changing room ${roomId} status to ${newState}...`);
            await api.put(`/rooms/${roomId}/status/${newState}`);

            // Actualizar el estado local inmediatamente
            setRooms(prevRooms => prevRooms.map(r =>
                r.id === roomId ? { ...r, roomState: { ...r.roomState, stateName: newState } } : r
            ));

            // Si la habitación seleccionada es la que estamos cambiando, actualizarla también
            if (selectedRoom && selectedRoom.id === roomId) {
                setSelectedRoom(prev => ({ ...prev, roomState: { ...prev.roomState, stateName: newState } }));
            }

            console.log("Status updated successfully");

            // Si el nuevo estado es 'Ocupada', redirigimos automáticamente a reservas
            if (newState === 'Ocupada') {
                navigate('/reservations', {
                    state: {
                        preselectedRoomId: roomId,
                        preselectedRoomNumber: selectedRoom.roomNumber
                    }
                });
            }
        } catch (error) {
            console.error("Error updating room status:", error);
            alert("No se pudo actualizar el estado de la habitación.");
        }
    };

    const getRoomColor = (state) => {
        const s = state?.toLowerCase() || '';
        if (s.includes('libre') || s.includes('disponible')) return '#10b981'; // emerald-500
        if (s.includes('ocupada')) return '#ef4444'; // red-500
        return '#f59e0b'; // amber-500 (limpieza)
    };

    if (loading) {
        return (
            <div className="flex items-center justify-center min-h-screen bg-gray-50">
                <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-amber-700"></div>
            </div>
        );
    }

    return (
        <div className="min-h-screen bg-gray-100 py-12 px-4 sm:px-6 lg:px-8 font-sans">
            <div className="max-w-4xl mx-auto">
                <div className="bg-white p-10 rounded-[2.5rem] shadow-2xl border border-gray-100">
                    <header className="mb-10 text-center">
                        <h1 className="text-3xl font-black text-gray-900 tracking-tighter uppercase mb-2 flex items-center justify-center gap-3">
                            <MapPin className="text-amber-700 w-8 h-8" />
                            Mapa de Planta Hotelera
                        </h1>
                        <p className="text-gray-400 font-bold text-xs uppercase tracking-[0.3em]">Vista Operativa – Planta 1</p>
                    </header>

                    {/* SVG Map Container */}
                    <div className="relative bg-slate-50 p-6 rounded-3xl border border-slate-200 shadow-inner">
                        <svg viewBox="0 0 600 300" className="w-full drop-shadow-sm filter">
                            {/* Pasillo Principal */}
                            <rect x="0" y="130" width="600" height="40" className="fill-slate-200" rx="4" />
                            <text x="300" y="155" textAnchor="middle" className="fill-slate-400 text-[10px] font-black uppercase tracking-[0.5em]">Pasillo de Servicios</text>

                            {/* Entrada */}
                            <g transform="translate(270, 270)">
                                <rect width="60" height="30" className="fill-slate-800" rx="8" />
                                <text x="30" y="20" textAnchor="middle" className="fill-white text-[8px] font-black uppercase tracking-widest">Entrada</text>
                            </g>

                            {/* Renderizado de Habitaciones */}
                            {rooms.map((room) => (
                                <g
                                    key={room.id}
                                    onClick={() => setSelectedRoom(room)}
                                    className="cursor-pointer group"
                                >
                                    {/* Mueble / Icono (Sustituido por rectángulo con borde estilo plano) */}
                                    <rect
                                        x={room.x}
                                        y={room.y}
                                        width="100"
                                        height="60"
                                        fill={getRoomColor(room.roomState?.stateName)}
                                        className="transition-all duration-300 stroke-white stroke-2 group-hover:filter group-hover:brightness-110"
                                        rx="12"
                                    />

                                    {/* Símbolo de Cama simplificado en el plano */}
                                    <path
                                        d={`M ${room.x + 20} ${room.y + 25} h 60 v 10 h -60 Z`}
                                        className="fill-white/30"
                                    />

                                    {/* Texto de la habitación */}
                                    <text
                                        x={room.x + 55}
                                        y={room.y > 100 ? room.y + 85 : room.y - 15}
                                        textAnchor="middle"
                                        className="fill-slate-800 text-lg font-black tracking-tighter"
                                    >
                                        {room.roomNumber}
                                    </text>

                                    {/* Indicador de Estado pequeño al lado del número */}
                                    <circle
                                        cx={room.x + 35}
                                        cy={room.y > 100 ? room.y + 79 : room.y - 21}
                                        r="4"
                                        fill={getRoomColor(room.roomState?.stateName)}
                                    />
                                </g>
                            ))}
                        </svg>

                        {rooms.length === 0 && (
                            <div className="absolute inset-0 flex items-center justify-center bg-white/80">
                                <p className="text-red-500 font-bold p-4 bg-red-50 rounded-xl border border-red-100">
                                    ERROR CRÍTICO: No hay datos de habitaciones (Backend o Mock).
                                </p>
                            </div>
                        )}
                    </div>

                    <footer className="mt-10 flex flex-wrap items-center justify-center gap-8 bg-gray-50 p-6 rounded-2xl border border-gray-100">
                        <div className="flex items-center gap-2">
                            <div className="w-4 h-4 rounded-full bg-emerald-500 shadow-sm"></div>
                            <span className="text-[10px] font-black text-gray-500 uppercase tracking-widest">Disponible</span>
                        </div>
                        <div className="flex items-center gap-2">
                            <div className="w-4 h-4 rounded-full bg-red-500 shadow-sm"></div>
                            <span className="text-[10px] font-black text-gray-500 uppercase tracking-widest">Ocupada</span>
                        </div>
                        <div className="flex items-center gap-2">
                            <div className="w-4 h-4 rounded-full bg-amber-500 shadow-sm"></div>
                            <span className="text-[10px] font-black text-gray-500 uppercase tracking-widest">En Mantenimiento</span>
                        </div>
                    </footer>
                </div>

                {/* Modal Overlay al seleccionar habitación */}
                {selectedRoom && (
                    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-900/40 backdrop-blur-md animate-in fade-in duration-300">
                        <div className="bg-white rounded-[3rem] shadow-2xl max-w-sm w-full overflow-hidden border-[12px] border-white transform transition-all scale-100">
                            <div className="relative">
                                <div className="bg-slate-900 p-12 text-center text-white">
                                    <Bed className="w-20 h-20 mx-auto mb-4 text-amber-500" />
                                    <h2 className="text-5xl font-black">{selectedRoom.roomNumber}</h2>
                                    <p className="text-xs font-black uppercase tracking-[0.2em] text-slate-400 mt-2">{selectedRoom.roomType?.typeName}</p>
                                </div>
                                <div
                                    className="absolute bottom-0 left-0 right-0 h-2"
                                    style={{ backgroundColor: getRoomColor(selectedRoom.roomState?.stateName) }}
                                ></div>
                            </div>

                            <div className="p-10 space-y-6">
                                <div className="grid grid-cols-2 gap-4">
                                    <div className="bg-slate-50 p-4 rounded-2xl border border-slate-100">
                                        <p className="text-[9px] font-black text-slate-400 uppercase mb-1 flex items-center gap-1">
                                            <Star size={10} /> Calidad
                                        </p>
                                        <p className="text-xs font-black text-slate-700">{selectedRoom.roomType?.typeName}</p>
                                    </div>
                                    <div className="bg-slate-50 p-4 rounded-2xl border border-slate-100">
                                        <p className="text-[9px] font-black text-slate-400 uppercase mb-1 flex items-center gap-1">
                                            <Users size={10} /> Máx.
                                        </p>
                                        <p className="text-xs font-black text-slate-700">{selectedRoom.roomType?.capacity} Prs.</p>
                                    </div>
                                </div>

                                <div className="bg-amber-50 p-5 rounded-2xl border border-amber-100 flex items-center justify-between">
                                    <div className="flex items-center gap-2">
                                        <DollarSign className="text-amber-700" size={18} />
                                        <span className="text-xl font-black text-amber-900 tracking-tighter">{selectedRoom.roomType?.basePrice}€</span>
                                    </div>
                                    <span className="text-[9px] font-black text-amber-700 uppercase tracking-widest">Noche</span>
                                </div>

                                <div className="space-y-2">
                                    <p className="text-[10px] font-black text-slate-400 uppercase tracking-widest">Cambiar Estado</p>
                                    <div className="flex gap-2">
                                        <button
                                            onClick={() => handleStatusChange(selectedRoom.id, 'Libre')}
                                            className={`flex-1 py-2 rounded-xl text-[10px] font-black uppercase transition-all border-2 ${selectedRoom.roomState?.stateName === 'Libre' ? 'bg-emerald-500 border-emerald-500 text-white' : 'bg-transparent border-emerald-100 text-emerald-600 hover:bg-emerald-50'}`}
                                        >
                                            Libre
                                        </button>
                                        <button
                                            onClick={() => handleStatusChange(selectedRoom.id, 'Ocupada')}
                                            className={`flex-1 py-2 rounded-xl text-[10px] font-black uppercase transition-all border-2 ${selectedRoom.roomState?.stateName === 'Ocupada' ? 'bg-red-500 border-red-500 text-white' : 'bg-transparent border-red-100 text-red-600 hover:bg-red-50'}`}
                                        >
                                            Ocupada
                                        </button>
                                        <button
                                            onClick={() => handleStatusChange(selectedRoom.id, 'Mantenimiento')}
                                            className={`flex-1 py-2 rounded-xl text-[10px] font-black uppercase transition-all border-2 ${selectedRoom.roomState?.stateName === 'Mantenimiento' ? 'bg-amber-500 border-amber-500 text-white' : 'bg-transparent border-amber-100 text-amber-600 hover:bg-amber-50'}`}
                                        >
                                            Manten.
                                        </button>
                                    </div>
                                </div>

                                <div className="flex flex-col gap-3 pt-4">
                                    {selectedRoom.roomState?.stateName === 'Libre' && (
                                        <button
                                            onClick={() => navigate('/reservations', {
                                                state: {
                                                    preselectedRoomId: selectedRoom.id,
                                                    preselectedRoomNumber: selectedRoom.roomNumber
                                                }
                                            })}
                                            className="w-full bg-slate-900 hover:bg-black text-white font-black py-5 rounded-[2rem] transition-all shadow-xl active:scale-95 text-sm tracking-widest uppercase"
                                        >
                                            Gestionar Reserva
                                        </button>
                                    )}
                                    <button
                                        onClick={() => setSelectedRoom(null)}
                                        className="w-full bg-slate-100 text-slate-400 font-bold py-4 rounded-[2rem] hover:bg-slate-200 transition-all uppercase text-[10px] tracking-widest"
                                    >
                                        Cerrar Vista
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
};

export default RoomMap;
