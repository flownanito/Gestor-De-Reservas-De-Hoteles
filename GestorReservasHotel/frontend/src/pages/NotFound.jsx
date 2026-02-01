import { Link } from "react-router-dom";

export default function NotFound() {
  return (
    <div className="flex flex-col items-center justify-center h-screen text-center gap-4">
  <h1 className="text-6xl font-bold">404</h1>

  <h2 className="text-2xl font-semibold">
    Página no encontrada
  </h2>

  <p className="text-gray-600 max-w-md">
    La ruta que está intentando visitar no existe o fue movida.
  </p>

  <Link
    to="/"
    className="mt-6 inline-block bg-white text-amber-700 font-medium px-6 py-3 rounded-xl shadow-lg hover:bg-gray-100 transition"
  >
    Volver al inicio
  </Link>
</div>

  );
}
