import React from 'react';
import Hero from '../components/Hero'; // 1. Importamos la "Portada"

import roomsData from "../data/Rooms";

import { Link } from "react-router-dom";

const Dashboard = () => {
  return (
    <div className="flex flex-col min-h-screen">

      {/* 2. Mostramos el Hero (La imagen grande con botones) */}
      <Hero />

      {/* 3. Sección de ejemplo debajo (para que veas que hay más contenido) */}
      <section className="py-16 px-4 bg-white">
        <div className="max-w-7xl mx-auto text-center">
          <h2 className="text-3xl font-bold text-gray-900 mb-4">
            Nuestras Habitaciones Destacadas
          </h2>
          <p className="text-gray-600 max-w-2xl mx-auto mb-10">
            Descubre el confort y la elegancia en cada uno de nuestros espacios diseñados para ti.
          </p>

          {/* Un grid con ejemplos de habitaciones */}
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            {roomsData.slice(0, 3).map((room) => (
              <div
                key={room.id}
                className="bg-gray-50 rounded-xl p-6 border border-gray-100 shadow-sm hover:shadow-md transition-shadow"
              >
                <img
                  src={room.img}
                  alt={room.name}
                  className="h-40 w-full object-cover rounded-lg mb-4"
                />

                <h3 className="text-xl font-bold text-gray-800 mb-2">
                  {room.name}
                </h3>

                <p className="text-gray-600 mb-2">
                  {room.description}
                </p>

                <p className="text-amber-700 font-bold">
                  {room.price}
                </p>
              </div>
            ))}
          </div>
          <div className="mt-12">
            <Link
              to="/rooms"
              className="inline-block bg-amber-700 hover:bg-amber-800 text-white font-semibold px-8 py-3 rounded-lg transition-colors duration-300 shadow-md hover:shadow-lg"
            >
              Ver todas las habitaciones
            </Link>
          </div>
        </div>
      </section>

    </div>
  );
};

export default Dashboard;
