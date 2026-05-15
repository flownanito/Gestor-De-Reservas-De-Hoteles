def calcular_precio_estancia():
    # Definir tipos de habitaciones y sus precios
    habitaciones = {
        "1": {"nombre": "Estándar", "precio": 50.0},
        "2": {"nombre": "Doble", "precio": 75.0},
        "3": {"nombre": "Suite", "precio": 120.0},
        "4": {"nombre": "Familiar", "precio": 90.0}
    }

    print("--- Calculadora de Precio de Estancia ---")
    
    # Seleccionar tipo de habitación
    habitacion_seleccionada = None
    while True:
        print("\nTipos de habitaciones disponibles:")
        for clave, datos in habitaciones.items():
            print(f"{clave}. {datos['nombre']} - {datos['precio']}€/noche")
        
        opcion = input("\nElige el tipo de habitación (1-4): ")
        if opcion in habitaciones:
            habitacion_seleccionada = habitaciones[opcion]
            break
        else:
            print("Error: Opción no válida. Por favor, elige un número del 1 al 4.")

    # Solicitar número de noches
    while True:
        entrada = input(f"\nHas seleccionado la habitación {habitacion_seleccionada['nombre']}. Introduce el número de noches: ")
        try:
            noches = int(entrada)
            if noches <= 0:
                print("Error: El número de noches debe ser mayor que 0. Inténtalo de nuevo.")
                continue
            break
        except ValueError:
            print("Error: Por favor, introduce un número entero válido (ej. 5).")

    precio_por_noche = habitacion_seleccionada["precio"]
    precio_base = noches * precio_por_noche
    descuento = 0.0
    mensaje_descuento = "Sin descuento (menos de 8 noches)"

    # Lógica de descuentos
    # - Más de 14 noches: 20% de descuento
    # - Más de 7 noches: 10% de descuento
    if noches > 14:
        descuento = 0.20
        mensaje_descuento = "20% por estancia mayor a 14 noches"
    elif noches > 7:
        descuento = 0.10
        mensaje_descuento = "10% por estancia mayor a 7 noches"

    cantidad_descuento = precio_base * descuento
    precio_final = precio_base - cantidad_descuento

    print("\n--- Resumen de la Estancia ---")
    print(f"Tipo de habitación: {habitacion_seleccionada['nombre']}")
    print(f"Número de noches: {noches}")
    print(f"Precio base ({precio_por_noche}€/noche): {precio_base:.2f}€")
    print(f"Descuento aplicado: {mensaje_descuento}")
    if descuento > 0:
        print(f"Cantidad descontada: -{cantidad_descuento:.2f}€")
    print(f"Precio Final: {precio_final:.2f}€")
    print("------------------------------")

if __name__ == "__main__":
    # Iniciar la calculadora
    try:
        calcular_precio_estancia()
    except KeyboardInterrupt:
        print("\n\nCálculo cancelado por el usuario. ¡Hasta luego!")
