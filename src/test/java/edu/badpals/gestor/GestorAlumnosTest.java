package edu.badpals.gestor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorAlumnosTest {
    @Test
    public void testDniErroneos(){
        GestorAlumnos gestor = new GestorAlumnos(new GestorFichero());
        String dniErroneo = "12345678A";
        String dniVacio = " ";
        String dniMalNumeros = "123-5678A";
        String dniLargo = "12345678AQASF";

        assertFalse(gestor.validarDni(dniErroneo));
        assertFalse(gestor.validarDni(dniVacio));
        assertFalse(gestor.validarDni(dniMalNumeros));
        assertFalse(gestor.validarDni(dniLargo));
    }

    @Test
    public void testDniValidos(){
        GestorAlumnos gestor = new GestorAlumnos(new GestorFichero());
        String dniOk1 = "83987776X";
        String dni0k2 = "59790032Z";
        String dni0k3 = "64691962T";
        String dni0k4 = "28476159C";

        assertTrue(gestor.validarDni(dniOk1));
        assertTrue(gestor.validarDni(dni0k2));
        assertTrue(gestor.validarDni(dni0k3));
        assertTrue(gestor.validarDni(dni0k4));
    }

}