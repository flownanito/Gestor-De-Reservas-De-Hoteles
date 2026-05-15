import { describe, it, expect } from 'vitest';
import { calculateReservationPrice } from '../utils/priceCalculator';

describe('Logic Tests: calculateReservationPrice', () => {

    it('debe calcular correctamente el precio para una reserva válida de 2 noches (AAA)', () => {
        // Arrange: preparar datos/estado
        const checkIn = '2026-03-01';
        const checkOut = '2026-03-03';
        const basePrice = 100;

        // Act: ejecutar la función
        const result = calculateReservationPrice(checkIn, checkOut, basePrice);

        // Assert: verificar resultado
        expect(result.nights).toBe(2);
        expect(result.subtotal).toBe(200);   // 2 * 100
        expect(result.impuestos).toBe(20);   // 10% de 200
        expect(result.total).toBe(220);      // 200 + 20
    });

    it('debe retornar 0 noches y precio 0 si check-out es anterior al check-in (AAA - Caso Error)', () => {
        // Arrange
        const checkIn = '2026-03-05';
        const checkOut = '2026-03-01';
        const basePrice = 150;

        // Act
        const result = calculateReservationPrice(checkIn, checkOut, basePrice);

        // Assert
        expect(result.nights).toBe(0);
        expect(result.subtotal).toBe(0);
        expect(result.total).toBe(0);
    });

    it('debe manejar precios base no proporcionados o nulos sin lanzar errores (AAA - Caso Raro)', () => {
        // Arrange
        const checkIn = '2026-03-01';
        const checkOut = '2026-03-05';
        const basePrice = null;

        // Act
        const result = calculateReservationPrice(checkIn, checkOut, basePrice);

        // Assert
        expect(result.nights).toBe(4);
        expect(result.subtotal).toBe(0);
        expect(result.impuestos).toBe(0);
        expect(result.total).toBe(0);
    });

    it('debe redondear correctamente los decimales en los impuestos y el total (AAA)', () => {
        // Arrange
        const checkIn = '2026-03-01';
        const checkOut = '2026-03-02';
        const basePrice = 15.55;

        // Act
        const result = calculateReservationPrice(checkIn, checkOut, basePrice);

        // Assert
        expect(result.nights).toBe(1);
        expect(result.subtotal).toBe(15.55);
        expect(result.impuestos).toBe(1.56); // 10% de 15.55 es 1.555, redondeado a 1.56
        expect(result.total).toBe(17.11);    // 15.55 + 1.56
    });

});
