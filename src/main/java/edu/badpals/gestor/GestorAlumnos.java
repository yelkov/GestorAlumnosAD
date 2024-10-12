package edu.badpals.gestor;

import java.util.HashSet;
import java.util.Set;

public class GestorAlumnos {
    Set<Alumno> alumnos = new HashSet<>();
    GestorFichero gestorFichero;
    boolean cambiosGuardados;

    public GestorAlumnos(GestorFichero gestorFichero){
        this.gestorFichero = gestorFichero;
        this.alumnos = this.gestorFichero.leerFichero();
        this.cambiosGuardados = true;
    }


    public void guardarCambios(){
        gestorFichero.escribirFicheros(this.alumnos);
        System.out.println("\nSe han guardado los cambios.\n");
        setCambiosGuardados(true);
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
    public void crearAlumno(String dni, String nombre, String edad, Float notaMedia, boolean repite){
        Alumno alumno = new Alumno(dni,nombre,edad,notaMedia,repite);

        if (alumnos.contains(alumno)) {
            System.out.println("\nEl alumno ya está presente en el registro.\n");
        } else {
            alumnos.add(alumno);
            System.out.println("\nSe ha creado un nuevo alumno.\n");
            setCambiosGuardados(false);
        }
    }

    public void mostrarAlumnos(){
        System.out.println("\nLista de Todos los alumnos:\n");
        this.alumnos.forEach(System.out::println);
        if(!esCambiosGuardados()){
            System.out.println("* Los cambios introducidos todavía no han sido guardados.");
        }
    }

    public void mostrarRepetidores(){
        System.out.println("\nLista de Repetidores:\n");
        this.alumnos.stream().filter(Alumno::esRepetidor).forEach(System.out::println);
        if(!esCambiosGuardados()){
            System.out.println("* Los cambios introducidos todavía no han sido guardados.");
        }
    }

    public void mostrarAprobados(){
        System.out.println("\nLista de Aprobados:\n");
        this.alumnos.stream().filter(a -> a.getNotaMedia()>= 5.0).forEach(System.out::println);
        if(!esCambiosGuardados()){
            System.out.println("* Los cambios introducidos todavía no han sido guardados.");
        }
    }

    private void setCambiosGuardados(boolean cambiosGuardados) {
        this.cambiosGuardados = cambiosGuardados;
    }

    private boolean esCambiosGuardados(){
        return this.cambiosGuardados;
    }

}
