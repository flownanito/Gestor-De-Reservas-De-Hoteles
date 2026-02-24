# Gestor de Reservas de un Hotel
## Descripción
Este proyecto se trata de una aplicación para gestionar las reservas de un hotel. 
Permite gestionar los registros de clientes, las habitaciones, las reservas realizadas y el procesamiento de pagos, todo dentro de una interfaz organizada.
El sistema gestiona todo el ciclo de vida de una reserva, desde el registro del cliente hasta la finalización del pago.

## Características principales
* Gestión de habitaciones (disponibilidad, tipos).
* Registro y gestión de clientes.
* Creación, modificación y cancelación de reservas.
* Control y seguimiento del estado de las reservas.
* Interfaz gráfica.

## Tecnologías usadas
Frontend:
* React/Vite
* Tailwind CSS
* JavaScript

Backend:
* Spring Boot
* PostgreSQL

## Autores
- Nauzet del Cristo Sánchez Santana.
- Mohamed Reda Zinat Harroun Betancor.
- Victoria Raaz Araujo.

## Testing

Esta sección describe cómo ejecutar y entender los tests automatizados del frontend, desarrollados con **Vitest** y **React Testing Library**.

### Requisitos / Setup

- **Node.js**: Versión LTS recomendada (v18 o superior).
- Instalación de dependencias de testing:
  ```bash
  cd frontend
  npm install
  ```
  *(Las dependencias incluyen `vitest`, `@testing-library/react`, `jsdom`, `@vitest/coverage-v8`, etc.)*

### Cómo ejecutar tests

Dispones de los siguientes scripts en el `package.json` del frontend:

- **Ejecutar todos los tests una vez:**
  ```bash
  npm run test
  ```
- **Modo observador (watch)** (ideal para programación):
  ```bash
  npm run test:watch
  ```
- **Ejecutar un archivo específico** (o patrón):
  ```bash
  npx vitest src/tests/logic.test.js
  ```
  O también: `npx vitest -t "nombre del test"`

### Cobertura (Coverage)

Para generar el reporte de cobertura de código, ejecuta:
```bash
npm run test:coverage
```
* **Métricas analizadas**: Líneas (lines), ramas (branches), funciones (functions) y sentencias (statements).
* El reporte detallado en HTML se genera automáticamente en la carpeta `frontend/coverage/`.
* *Regla interna del equipo*: Todo nuevo componente o utilidad crítica debe ir acompañado de sus respectivos tests automatizados para no mermar el porcentaje general de cobertura ni aceptar Pull Requests defectuosas.

### Estructura y convención de archivos

- Todos los tests se ubican de forma centralizada pero organizada dentro de **`frontend/src/tests/`**.
- Convención de nombres: `*.test.js` o `*.test.jsx` (dependiendo independientemente de si incluye sintaxis JSX o lógica pura).

### Qué se está testeando

El testing se ha dividido estratégicamente en 3 bloques funcionalmente independientes (un fichero por tipo), garantizando una excelente cobertura funcional en todos los flancos críticos de la aplicación:

1. **Lógica Pura (`logic.test.js`)**: Cálculos de precios, impuestos, contabilidad de noches y manejo correctivo de fechas (ej. `calculateReservationPrice`).
2. **Datos e Integración API (`api.test.js`)**: Llamadas y comunicación saliente al backend (`reservationsApi.js`). Se simulan (`mock`) las peticiones reales conectadas a servidor para corroborar respuestas correctas y gestión de errores.
3. **Interfaz (`ui.test.jsx`)**: Componentes visuales de React (ej. el navbar `Header.jsx`), asegurando renderizados correctos de layouts condicionales según el rol de sesión (admin vs. deslogueado) e interactividad (verificando la respuesta efectiva a los clicks del ratón).

### Guía para escribir tests

Todos los tests siguen de forma estricta el **Principio AAA (Arrange - Act - Assert)** para asegurar la máxima calidad técnica:

- **Arrange (Preparar)**: Preparar las condiciones iniciales, estado y variables (mocks o datos reales).
- **Act (Actuar)**: Ejecutar la función core o simular la interacción puntual del usuario.
- **Assert (Afirmar)**: Comprobar con los matchers de `expect` que el resultado es exactamente el deseado y pactado.

**Ejemplo real del proyecto (`logic.test.js`):**
```javascript
it('debe calcular correctamente el precio para una reserva válida de 2 noches (AAA)', () => {
  // Arrange: Preparar datos y crear estado base local 
  const checkIn = '2026-03-01';
  const checkOut = '2026-03-03';
  const basePrice = 100;

  // Act: Ejecutar la función o unidad que deseamos someter a estrés
  const result = calculateReservationPrice(checkIn, checkOut, basePrice);

  // Assert: Verificar estricta e inequívocamente el resultado final
  expect(result.nights).toBe(2);
  expect(result.subtotal).toBe(200);
  expect(result.impuestos).toBe(20);
  expect(result.total).toBe(220); // Impuestos añadidos correctamente
});
```

### Mocks y aislamiento

Para testear la integración con el backend sin hacer peticiones http reales (evitando tests falsamente intermitentes, lentos o inseguros), utilizamos librerías de simulaciones o "Mocks" mediante funciones base como `vi.spyOn`.

- **Cómo mockear**: Interceptamos llamadas troncales en `global.fetch`, dándole un valor de contención forzado o de error usando combinaciones de `.mockResolvedValue()` o `.mockRejectedValue()`.
- **Aislamiento impecable**: Es **obligatoriamente fundamental** limpiar y disipar los mocks tras la finalización individual de cada test para que el estado de un test no invada ni contamine al siguiente en la cola. Para ello invocamos `afterEach` en todo archivo aplicable:
  ```javascript
  import { afterEach, vi } from 'vitest';
  
  afterEach(() => {
    vi.restoreAllMocks(); // Limpia los espías exhaustivamente re-habilitando su estado neutral
  });
  ```

### Troubleshooting (Problemas típicos)

1. **`ReferenceError: fetch is not defined`**
   * *Razón*: Vitest usa un entorno virtual Node por defecto en el arranque donde `fetch` puede no existir si estás usando versiones antiguas.
   * *Solución*: En nuestro proyecto interceptamos el fetch manualmente a la medida, o en caso contrario recomendamos usar Node v18+ LTS (que ya incluye el fetch en nativo).
2. **`ReferenceError: document is not defined`** o similares de falta de DOM en React.
   * *Razón*: Tratando de renderizar un componente de visualización (UI) en un entorno de Node puro mediante CLI en la terminal.
   * *Solución*: Asegúrate de que tu fichero de raíces `vitest.config.js` estipula claramente `environment: 'jsdom'`.
3. **Faltan utilidades evaluativas como `toBeInTheDocument()`**
   * *Razón*: Los evaluadores/matchers estructurales del árbol DOM no son propiedad base nativa de Vitest puro.
   * *Solución*: Configura correctamente el `vitest.config.js` cargando globalmente la llave `setupFiles: './src/tests/setup.js'` que debe importar la carga de la librería extendida implícita: `import '@testing-library/jest-dom'`.
4. **Errores de rutas (`useNavigate() may be used only in the context of a <Router> component.`)**
   * *Razón*: Estás renderizando un subcomponente que consume el hook `<Link>` o `useNavigate` deshilachado de un contexto jerárquico de enrutamiento web padre.
   * *Solución*: Envuelve la raíz renderizada en la instancia interna local de tu test empleando un marco delimitador `<BrowserRouter>` (ver archivo explícito `ui.test.jsx`).
