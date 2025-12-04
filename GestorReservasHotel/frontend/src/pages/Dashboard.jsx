import React from 'react';
import Hero from '../components/Hero'; // 1. Importamos la "Portada"

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

          {/* Un grid de ejemplo rápido */}
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            {[1, 2, 3].map((item) => (
              <div key={item} className="bg-gray-50 rounded-xl p-6 border border-gray-100 shadow-sm hover:shadow-md transition-shadow">
                <div className="h-40 bg-gray-200 rounded-lg mb-4 flex items-center justify-center text-gray-400">
                  Foto Habitación {item}
                </div>
                <h3 className="text-xl font-bold text-gray-800 mb-2">Suite Deluxe {item}</h3>
                <p className="text-amber-700 font-bold">$120 / noche</p>
              </div>
            ))}
          </div>
        </div>
      </section>

    </div>
  );
};

export default Dashboard;
