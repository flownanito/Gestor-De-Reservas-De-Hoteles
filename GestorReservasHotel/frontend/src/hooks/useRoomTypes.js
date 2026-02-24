import { useEffect, useState } from "react";
import roomsData from "../data/Rooms";
import { fetchRoomTypes } from "../services/roomTypesApi";

export function useRoomTypes() {
  const [rooms, setRooms] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const load = async () => {
    try {
      setLoading(true);
      setError(null);

      const backend = await fetchRoomTypes();

      // backend: [{id, name, basePrice, totalRooms, availableRooms, ...}]
      // roomsData: [{id, name, img, longDescription, price(string), ...}]

      const merged = roomsData.map((local) => {
        const rt = backend.find((b) => b.id === local.id);

        // si existe en backend, mezclamos disponibilidad real
        if (rt) {
          return {
            ...local,
            // mantenemos textos e imágenes
            // pero añadimos datos reales del backend
            basePrice: rt.basePrice,
            totalRooms: rt.totalRooms,
            availableRooms: rt.availableRooms,
          };
        }

        // si no existe en backend, se queda como estaba
        return {
          ...local,
          totalRooms: null,
          availableRooms: null,
        };
      });

      setRooms(merged);
    } catch (e) {
      setError(e?.message || "Error cargando room types");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { load(); }, []);

  return { rooms, loading, error, reload: load };
}