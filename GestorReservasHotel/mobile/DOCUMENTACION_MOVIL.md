# Documentación Técnica - Aplicación Móvil Android
## Gestor de Reservas de Hoteles

**Asignatura:** Desarrollo de Aplicaciones Móviles  
**Plataforma:** Android (Java)  
**IDE:** Android Studio  
**Versión:** 1.0  

---

## 1. Descripción General

Aplicación móvil Android desarrollada en **Java** que permite gestionar reservas de un hotel. Se conecta a un backend **Spring Boot** mediante **API REST** utilizando la librería **Retrofit2**.

La aplicación diferencia entre dos roles de usuario:
- **Cliente (CLIENT):** Puede buscar habitaciones, realizar reservas, gestionar su perfil y ver sus pagos.
- **Administrador (ADMIN/Empleado):** Tiene acceso completo al CRUD de clientes, reservas, habitaciones y pagos, además de todas las funcionalidades del cliente.

---

## 2. Requisitos Técnicos

| Requisito | Valor |
|---|---|
| **Lenguaje** | Java 17 |
| **Min SDK** | API 24 (Android 7.0) |
| **Target SDK** | API 34 (Android 14) |
| **Arquitectura** | Activities + Retrofit (MVC) |
| **Backend** | Spring Boot (REST API en puerto 8080) |
| **Base de datos** | PostgreSQL (gestionada por el backend) |

### Dependencias principales
- `androidx.appcompat:appcompat:1.7.0`
- `com.google.android.material:material:1.12.0`
- `androidx.constraintlayout:constraintlayout:2.1.4`
- `androidx.cardview:cardview:1.0.0`
- `com.squareup.retrofit2:retrofit:2.9.0`
- `com.squareup.retrofit2:converter-gson:2.9.0`
- `com.squareup.okhttp3:logging-interceptor:4.11.0`

---

## 3. Estructura del Proyecto

```
mobile/app/src/main/
├── AndroidManifest.xml
├── java/com/proyect/reservationmanager/
│   ├── AdminDashboardActivity.java       # Panel de administración
│   ├── api/
│   │   ├── ApiClient.java               # Cliente HTTP alternativo
│   │   ├── ApiService.java              # Interfaz Retrofit (auth + clients)
│   │   ├── ClientApiService.java        # Interfaz Retrofit (CRUD clientes)
│   │   └── RetrofitClient.java          # Singleton Retrofit
│   ├── adapter/
│   │   ├── BookingAdapter.java          # Adapter para habitaciones (reservar)
│   │   ├── CardAdapter.java             # Adapter para tarjetas de pago
│   │   ├── ClientAdapter.java           # Adapter para lista de clientes
│   │   ├── FeatureAdapter.java          # Adapter para características
│   │   ├── ReservationAdapter.java      # Adapter para lista de reservas
│   │   ├── RoomAdapter.java             # Adapter para lista de habitaciones
│   │   └── TransactionAdapter.java      # Adapter para transacciones
│   ├── model/
│   │   ├── Client.java                  # Modelo Cliente
│   │   ├── Feature.java                 # Modelo Característica
│   │   ├── LoginRequest.java            # DTO para login
│   │   ├── LoginResponse.java           # DTO respuesta login (con rol)
│   │   ├── Payment.java                 # Modelo Pago
│   │   ├── PaymentCard.java             # Modelo Tarjeta
│   │   ├── PaymentMethods.java          # Modelo Métodos de pago
│   │   ├── Position.java                # Modelo Cargo/Posición
│   │   ├── RegisterRequest.java         # DTO para registro
│   │   ├── Reservation.java             # Modelo Reserva
│   │   ├── Room.java                    # Modelo Habitación
│   │   ├── RoomState.java               # Modelo Estado Habitación
│   │   └── RoomType.java                # Modelo Tipo Habitación
│   └── ui/
│       ├── LoginActivity.java           # Pantalla de Login
│       ├── RegisterActivity.java        # Pantalla de Registro
│       ├── MainActivity.java            # Búsqueda de habitaciones
│       ├── HomeActivity.java            # Home del cliente
│       ├── MiPerfilActivity.java        # Mi Perfil (CRUD propio)
│       ├── ClientManagementActivity.java # CRUD Clientes (admin)
│       ├── addClientActivity.java       # Añadir cliente
│       ├── ReservationManagementActivity.java # CRUD Reservas
│       ├── ReservationDetailsActivity.java    # Detalles/Pago reserva
│       ├── RoomManagementActivity.java  # CRUD Habitaciones
│       ├── RoomFeaureActivity.java      # Características habitación
│       └── PaymentManagementActivity.java # Gestión de pagos
└── res/
    ├── layout/                          # Layouts Portrait
    ├── layout-land/                     # Layouts Landscape
    ├── drawable/                        # Recursos gráficos
    ├── values/                          # Colores, strings, temas
    └── values-night/                    # Tema oscuro
```

---

## 4. Actividades (Activities)

La aplicación cuenta con **más de 10 actividades**, superando el mínimo de 4 requerido:

### 4.1 LoginActivity
- **Propósito:** Autenticación del usuario
- **Funcionalidades:**
  - Login con email y contraseña
  - Validación de campos vacíos
  - Llamada REST `POST /api/auth/login`
  - Almacenamiento de sesión en `SharedPreferences`
  - **Diferenciación de roles:** redirige a `AdminDashboardActivity` si es empleado o a `MainActivity` si es cliente
  - Auto-login si hay sesión activa

### 4.2 RegisterActivity
- **Propósito:** Registro de nuevos clientes
- **Funcionalidades:**
  - Formulario con: Nombre, Apellidos, DNI, Email, Contraseña
  - Validación de cada campo con mensajes de error específicos
  - Llamada REST `POST /api/clients`
  - Redirección al login tras registro exitoso

### 4.3 AdminDashboardActivity
- **Propósito:** Panel central para administradores
- **Acceso:** Solo empleados (verificado por rol)
- **Funcionalidades:**
  - Muestra nombre y rol del admin
  - Acceso rápido a: Gestión Clientes, Reservas, Habitaciones, Pagos
  - Acceso a Vista Cliente y Mi Perfil
  - Cerrar Sesión (limpia todas las SharedPreferences)

### 4.4 MainActivity
- **Propósito:** Búsqueda de habitaciones disponibles
- **Funcionalidades:**
  - Selector de fechas (check-in / check-out)
  - Selector de número de huéspedes
  - Lista de habitaciones con `RecyclerView`
  - Botón para reservar cada habitación

### 4.5 ClientManagementActivity
- **Propósito:** CRUD completo de clientes (admin)
- **Funcionalidades:**
  - Lista de clientes con `RecyclerView`
  - Búsqueda por nombre o DNI
  - Botón FAB para añadir nuevo cliente

### 4.6 ReservationManagementActivity
- **Propósito:** CRUD de reservas
- **Funcionalidades:**
  - Lista de reservas
  - Filtro por estado (Confirmada, Pendiente, Cancelada)
  - Búsqueda por ID o estado

### 4.7 MiPerfilActivity
- **Propósito:** Gestión del perfil propio
- **Funcionalidades:**
  - Ver datos personales (nombre, email, teléfono, DNI)
  - Editar perfil (diálogo con formulario)
  - Cambiar contraseña
  - Eliminar cuenta (con confirmación)
  - Cerrar sesión

### 4.8+ Otras actividades
- **ReservationDetailsActivity:** Detalles de reserva y formulario de pago
- **RoomManagementActivity:** CRUD de habitaciones
- **PaymentManagementActivity:** Gestión de tarjetas y transacciones
- **HomeActivity:** Vista inicial del cliente

---

## 5. CRUD Implementados

### 5.1 CRUD de Clientes
| Operación | Método HTTP | Endpoint | Activity |
|---|---|---|---|
| **Listar** | GET | `/api/clients` | ClientManagementActivity |
| **Obtener** | GET | `/api/clients/{id}` | MiPerfilActivity |
| **Crear** | POST | `/api/clients` | RegisterActivity |
| **Actualizar** | PUT | `/api/clients/{id}` | MiPerfilActivity |
| **Eliminar** | DELETE | `/api/clients/{id}` | MiPerfilActivity |

### 5.2 CRUD de Reservas
| Operación | Actividad | Descripción |
|---|---|---|
| **Listar** | ReservationManagementActivity | RecyclerView con filtros |
| **Ver detalle** | ReservationDetailsActivity | Resumen y pago |
| **Crear** | MainActivity → ReservationDetails | Seleccionar habitación y pagar |
| **Filtrar** | ReservationManagementActivity | Por estado |

---

## 6. Gestión de Roles

### 6.1 Flujo de autenticación

```
LoginActivity
    │
    ├── Email/Password → POST /api/auth/login
    │
    ├── Respuesta exitosa → LoginResponse con "role"
    │   │
    │   ├── role = "ADMIN" / "RECEPCIONISTA" / etc.
    │   │   └── → AdminDashboardActivity
    │   │
    │   └── role = "CLIENT"
    │       └── → MainActivity
    │
    └── Error → Toast "Credenciales incorrectas"
```

### 6.2 Almacenamiento de sesión

Se utiliza `SharedPreferences` con dos almacenes:

| Almacén | Clave | Descripción |
|---|---|---|
| `UserSession` | `USER_ID` | ID del usuario |
| `UserSession` | `USER_NAME` | Nombre |
| `UserSession` | `USER_LASTNAME` | Apellido |
| `UserSession` | `USER_EMAIL` | Email |
| `UserSession` | `USER_ROLE` | Rol (CLIENT, ADMIN...) |
| `UserSession` | `IS_LOGGED_IN` | Boolean de sesión activa |
| `UserPrefs` | `client_id` | ID (compatibilidad) |

### 6.3 Diferencias por rol

| Funcionalidad | Cliente | Admin |
|---|---|---|
| Buscar habitaciones | ✅ | ✅ |
| Realizar reservas | ✅ | ✅ |
| Mi Perfil | ✅ | ✅ |
| CRUD Clientes | ❌ | ✅ |
| CRUD Habitaciones | ❌ | ✅ |
| CRUD Reservas (todas) | ❌ | ✅ |
| Gestión de Pagos | ❌ | ✅ |

---

## 7. Layouts Portrait y Landscape

La aplicación soporta ambas orientaciones con layouts específicos:

### Layouts disponibles

| Actividad | Portrait (`layout/`) | Landscape (`layout-land/`) |
|---|---|---|
| LoginActivity | ✅ Vertical centrado | ✅ Dos columnas (logo + form) |
| RegisterActivity | ✅ Scroll vertical | ✅ Dos columnas (info + form) |
| AdminDashboardActivity | ✅ Lista vertical | ✅ Dos columnas (info + botones) |
| MainActivity | ✅ Filtros arriba, lista abajo | ✅ Filtros izquierda, resultados derecha |
| MiPerfilActivity | ✅ Scroll vertical | ✅ Perfil izquierda, opciones derecha |
| ReservationDetailsActivity | ✅ Resumen y pago vertical | ✅ Resumen izquierda, pago derecha |

### Estrategia de adaptación
- **Portrait:** Diseño vertical con scroll cuando necesario
- **Landscape:** Diseño en dos columnas para aprovechar el ancho disponible
- **Mismo ID de vistas:** Los layouts landscape mantienen los mismos IDs que los portrait para que la misma Activity funcione sin cambios de código

---

## 8. Conexión con el Backend

### 8.1 Configuración de red

```java
// RetrofitClient.java
private static final String BASE_URL = "http://10.0.2.2:8080/";
```

- **`10.0.2.2`:** Dirección especial del emulador Android que apunta al `localhost` del host
- **Puerto 8080:** Puerto por defecto del backend Spring Boot
- **`usesCleartextTraffic="true"`:** Habilitado en AndroidManifest para HTTP (desarrollo)

### 8.2 Endpoints utilizados

| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/api/auth/login` | Autenticación |
| GET | `/api/clients` | Listar clientes |
| GET | `/api/clients/{id}` | Obtener cliente |
| POST | `/api/clients` | Crear cliente |
| PUT | `/api/clients/{id}` | Actualizar cliente |
| DELETE | `/api/clients/{id}` | Eliminar cliente |

---

## 9. Tecnologías y Patrones

- **Retrofit2 + Gson:** Comunicación REST y serialización JSON
- **OkHttp Logging Interceptor:** Depuración de peticiones HTTP
- **SharedPreferences:** Persistencia de sesión local
- **RecyclerView + Adapters:** Listas eficientes con el patrón ViewHolder
- **Material Design 3:** Componentes UI modernos (TextInputLayout, MaterialButton, CardView)
- **DatePickerDialog:** Selector de fechas nativo
- **AlertDialog:** Diálogos de confirmación y edición

---

## 10. Instrucciones de Ejecución

1. **Iniciar el backend:**
   ```bash
   cd GestorReservasHotel/backend
   ./mvnw spring-boot:run
   ```

2. **Abrir el proyecto en Android Studio:**
   - Abrir la carpeta `GestorReservasHotel/mobile`
   - Sincronizar Gradle

3. **Ejecutar en emulador:**
   - Seleccionar un AVD con API 24+
   - Ejecutar la app (Run)
   - El emulador se conectará al backend vía `10.0.2.2:8080`

4. **Credenciales de prueba:**
   - **Admin:** Usar credenciales de un empleado registrado en la BD
   - **Cliente:** Registrarse desde la app o usar un cliente existente

---

## 11. Diagramas

### Diagrama de navegación

```
LoginActivity ──────────────────────────────┐
    │                                        │
    ├── [CLIENT] ──> MainActivity            │
    │                   ├── Mis Reservas     │
    │                   │   └── ReservationManagementActivity
    │                   ├── Buscar           │
    │                   │   └── ReservationDetailsActivity
    │                   └── Admin Access     │
    │                       └── AdminDashboardActivity
    │                                        │
    └── [ADMIN] ──> AdminDashboardActivity   │
                        ├── ClientManagementActivity
                        ├── ReservationManagementActivity
                        ├── RoomManagementActivity
                        ├── PaymentManagementActivity
                        ├── MiPerfilActivity
                        └── Cerrar Sesión ──> LoginActivity
```

### Diagrama de clases (modelos)

```
Client          Reservation          Room
├── id          ├── reservationId    ├── id
├── dni         ├── reservationDate  ├── roomNumber
├── firstName   ├── checkInDate      ├── floor
├── lastName    ├── checkOutDate     ├── roomState ──> RoomState
├── email       ├── condition        ├── roomType ──> RoomType
├── phone       ├── numberOfGuests   └── features ──> [Feature]
├── password    └── totalPrice
└── regDate
```
