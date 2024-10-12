package edu.badpals.gestor;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        GestorAlumnos gestor = new GestorAlumnos(new GestorFichero());

        gestor.crearAlumno("83987776X","Yelko","31",5.4f,false);
        gestor.crearAlumno("59790032Z","David","25",9.7f,false);
        gestor.crearAlumno("64691962T","Evan","22",8.5f,false);
        gestor.crearAlumno("28476159C","Iván","18",3.9f,true);
        gestor.crearAlumno("21231609X","JP","19",5.1f,true);

        gestor.mostrarAlumnos();
        gestor.mostrarRepetidores();
        gestor.mostrarAprobados();

        gestor.guardarCambios();

        gestor.mostrarAlumnos();

    }
}