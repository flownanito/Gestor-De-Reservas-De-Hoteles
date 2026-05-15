import { describe, it, expect, vi, afterEach } from 'vitest';
import { createReservation } from '../services/reservationsApi';

describe('API Tests: reservationsApi', () => {
    // Limpiamos los mocks después de cada test para mantener el aislamiento (Principio esencial)
    afterEach(() => {
        vi.restoreAllMocks();
    });

    it('debe enviar la reserva correctamente y devolver la respuesta json (AAA)', async () => {
        // Arrange: Preparamos los datos simulados y el entorno
        const payload = { clientId: 1, roomTypeId: 2, checkIn: '2026-03-01' };
        const mockResponse = { id: 10, status: 'CONFIRMED' };

        // Espiamos global.fetch y forzamos que devuelva Ok y nuestro json
        const fetchSpy = vi.spyOn(global, 'fetch').mockResolvedValue({
            ok: true,
            json: async () => mockResponse,
        });

        // Act: Ejecutamos el servicio
        const result = await createReservation(payload);

        // Assert: Comprobamos llamadas correctas y valores retornados
        expect(fetchSpy).toHaveBeenCalledTimes(1);
        expect(fetchSpy).toHaveBeenCalledWith('http://localhost:8080/api/reservations', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });
        expect(result).toEqual(mockResponse);
    });

    it('debe lanzar un error con el status si el servidor retorna un error (AAA - Error Server)', async () => {
        // Arrange: Preparamos un caso en que la API devuelve 400
        const payload = { badData: true };
        const errorText = 'Faltan campos obligatorios';

        const fetchSpy = vi.spyOn(global, 'fetch').mockResolvedValue({
            ok: false,
            status: 400,
            text: async () => errorText,
        });

        // Act & Assert: Comprobamos que rechaza la promesa con el mensaje esperado
        await expect(createReservation(payload)).rejects.toThrowError(
            'Error creating reservation: 400 Faltan campos obligatorios'
        );
        expect(fetchSpy).toHaveBeenCalledTimes(1);
    });

    it('debe propagar el error si ocurre un fallo de red grave (AAA - Error Red)', async () => {
        // Arrange: Forzamos la caída de red
        const payload = { test: 123 };
        const fetchSpy = vi.spyOn(global, 'fetch').mockRejectedValue(new Error('Network failure'));

        // Act & Assert: Comprobamos el comportamiento
        await expect(createReservation(payload)).rejects.toThrowError('Network failure');
        expect(fetchSpy).toHaveBeenCalledTimes(1);
    });

    it('debe configurar correctamente las cabeceras application/json (AAA - Configuración)', async () => {
        // Arrange: Preparamos un mock exitoso
        const payload = { test: 'headers' };
        const fetchSpy = vi.spyOn(global, 'fetch').mockResolvedValue({
            ok: true,
            json: async () => ({ success: true }),
        });

        // Act: Ejecutamos el servicio
        await createReservation(payload);

        // Assert: Comprobamos específicamente que la petición contiene el Content-Type correcto
        expect(fetchSpy).toHaveBeenCalledWith(
            expect.any(String),
            expect.objectContaining({
                headers: { 'Content-Type': 'application/json' }
            })
        );
    });
});
