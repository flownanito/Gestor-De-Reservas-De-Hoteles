import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import { describe, it, expect, vi } from 'vitest';
import { BrowserRouter } from 'react-router-dom';
import Header from '../components/Header';

describe('UI Tests: Header Component', () => {

    it('renderiza correctamente el botón Entrar cuando no hay usuario (AAA)', () => {
        // Arrange: Preparamos componente sin props de usuario enrutadas
        // Act: Renderizamos en el jsdom
        render(
            <BrowserRouter>
                <Header user={null} />
            </BrowserRouter>
        );

        // Assert: Verificamos que se muestra "Entrar / Reservar" y no el perfil
        const loginButton = screen.getByText('Entrar / Reservar');
        expect(loginButton).toBeInTheDocument();
    });

    it('muestra el nombre y la inicial del usuario cuando está logueado (AAA)', () => {
        // Arrange: Preparamos un usuario mockeado
        const mockUser = { name: 'Nauzet', role: 'CLIENT' };

        // Act: Renderizamos con el usuario
        render(
            <BrowserRouter>
                <Header user={mockUser} />
            </BrowserRouter>
        );

        // Assert: Verificamos inicial, nombre y ausencia de login
        expect(screen.getByText('N')).toBeInTheDocument(); // Inicial
        expect(screen.getByText('Nauzet')).toBeInTheDocument();
        expect(screen.queryByText('Entrar / Reservar')).not.toBeInTheDocument();
    });

    it('muestra links exclusivos de gestión si el usuario es ADMIN (AAA)', () => {
        // Arrange: Preparamos un usuario administrador
        const mockAdmin = { name: 'Admin', role: 'ADMIN' };

        // Act: Renderizamos
        render(
            <BrowserRouter>
                <Header user={mockAdmin} />
            </BrowserRouter>
        );

        // Assert: Verificamos links restringidos
        expect(screen.getByText('Empleados')).toBeInTheDocument();
        expect(screen.getByText('Clientes')).toBeInTheDocument();
        expect(screen.getByText('Mapa de Habitaciones')).toBeInTheDocument();
    });

    it('ejecuta la función onLogout al hacer clic en el botón de salir (AAA - Interacción y Mocks)', () => {
        // Arrange: Mockeamos la prop onLogout y montamos el componente
        const mockLogout = vi.fn();
        const mockUser = { name: 'TestUser', role: 'CLIENT' };

        render(
            <BrowserRouter>
                <Header user={mockUser} onLogout={mockLogout} />
            </BrowserRouter>
        );

        // Act: Encontramos el botón por su tooltip/title y disparamos click
        const logoutButton = screen.getByTitle('Cerrar Sesión');
        fireEvent.click(logoutButton);

        // Assert: Comprobamos que el espía fue llamado
        expect(mockLogout).toHaveBeenCalledTimes(1);
    });

});
