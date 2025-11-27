
import { Link } from "react-router-dom";
import React from "react";

const Header = () => {
  return (
    <nav>
      <Link to='/'>Gestor Hotel</Link>
      <Link to='/clients'>Clientes</Link>
      <Link to='/employees'>Empleados</Link>
      <Link to='/reservations'>Reservas</Link>
    </nav>
  );
};

export default Header;
