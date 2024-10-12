package edu.badpals.gestor;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Alumno alumno1 = new Alumno("111","UNO","18",6.4F,false);
        Alumno alumno2 = new Alumno("222","DOS","21",3.1F,true);
        Alumno alumno3 = new Alumno("333","TRES","16",7.2F,false);
        Alumno alumno4 = new Alumno("444","CUATRO","12",5.1F,true);

        Set<Alumno> alumnos = new HashSet<>();
        alumnos.add(alumno1);
        alumnos.add(alumno2);
        alumnos.add(alumno3);
        alumnos.add(alumno4);

        GestorFichero gestorFichero = new GestorFichero();

        gestorFichero.escribirFicheros(alumnos);

    }
}