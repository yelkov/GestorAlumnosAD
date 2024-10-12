package edu.badpals.gestor;

import java.util.HashSet;
import java.util.Set;

public class GestorAlumnos {
    Set<Alumno> alumnos = new HashSet<>();
    GestorFichero gestorFichero;

    public GestorAlumnos(GestorFichero gestorFichero){
        this.gestorFichero = gestorFichero;
        this.alumnos = this.gestorFichero.leerFichero();
    }

    public boolean validarDni(String dni){
        if(dni.length()!= 9){
            return false;
        } else{
            String parteNumerica = dni.substring(0,8);
            char letra = Character.toUpperCase(dni.charAt(8));
            try{
                Integer digitos = Integer.parseInt(parteNumerica);
                String tablaLetrasValidas = "TRWAGMYFPDXBNJZSQVHLCKE";
                Integer indice = digitos % 23;
                if(tablaLetrasValidas.charAt(indice) != letra){
                    return false;
                }
            }catch (NumberFormatException e){
                return false;
            }
            return true;
        }
    }


}
