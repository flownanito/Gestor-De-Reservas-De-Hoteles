import React, { useState } from "react";

// importacion del css
import './Footer.css';

const Footer = () => {
  return (
    <>
      <footer className="bg-gray-900 text-white mt-20">
        <div className="max-w-7xl mx-auto px-6 py-16">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-12">
            <div>
              <div className="w-14 h-14 bg-white rounded-full flex items-center justify-center mb-8">
                <span className="text-2xl">🏨</span>
              </div>
              <p className="text-gray-400 text-sm leading-relaxed mb-6">
                En nuestro hotel, nos dedicamos a proporcionarte la mejor experiencia de hospedaje. 
                Contamos con habitaciones modernas, servicios de primera clase y un equipo comprometido con tu comodidad.
              </p>
              <div className="flex gap-4">
                <a href="#" className="w-8 h-8 flex items-center justify-center hover:opacity-80 transition-opacity">
                  <span className="text-xl">📘</span>
                </a>
                <a href="#" className="w-8 h-8 flex items-center justify-center hover:opacity-80 transition-opacity">
                  <span className="text-xl">🐦</span>
                </a>
                <a href="#" className="w-8 h-8 flex items-center justify-center hover:opacity-80 transition-opacity">
                  <span className="text-xl">📷</span>
                </a>
              </div>
            </div>
            <div className="grid grid-cols-2 gap-8">
              <div>
                <h4 className="font-semibold text-white mb-4">Enlaces</h4>
                <ul className="space-y-3 text-gray-400 text-sm">
                  <li><a href="#" className="hover:text-white transition-colors">Inicio</a></li>
                  <li><a href="#" className="hover:text-white transition-colors">Habitaciones</a></li>
                  <li><a href="#" className="hover:text-white transition-colors">Sobre nosotros</a></li>
                  <li><a href="#" className="hover:text-white transition-colors">Servicios</a></li>
                  <li><a href="#" className="hover:text-white transition-colors">Galería</a></li>
                  <li><a href="#" className="hover:text-white transition-colors">Ofertas Especiales</a></li>
                </ul>
              </div>
              <div>
                <h4 className="font-semibold text-white mb-4">Contacto</h4>
                <ul className="space-y-3 text-gray-400 text-sm">
                  <li className="flex items-start gap-2">
                    <span className="text-orange-400">📍</span>
                    <span>Calle Principal 123<br />28001 Madrid, España</span>
                  </li>
                  <li className="flex items-center gap-2">
                    <span className="text-orange-400">📞</span>
                    <span>+34 (234) 567-89</span>
                  </li>
                  <li className="flex items-center gap-2">
                    <span className="text-orange-400">📧</span>
                    <span>info@hotelexample.com</span>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </footer>
    </>
  );
};

export default Footer;
