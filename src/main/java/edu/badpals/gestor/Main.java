package edu.badpals.gestor;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        GestorAlumnos gestor = new GestorAlumnos(new GestorFichero());
        Scanner sc = new Scanner(System.in);
        boolean salida = false;

        System.out.println("¡Bienvenido al Gestor de alumnos!\n");
        mostrarMenu();

        while(!salida){
            System.out.println("\nSeleccione una opción: ");
            String respuesta = sc.nextLine().toLowerCase();

            switch (respuesta){
                case "0":
                case "menu":
                case "mostrar menu":
                    mostrarMenu();
                    break;
                case "1":
                case "crear alumno":
                    procesarCreacion(gestor,sc);
                    break;
                case "2":
                case "mostrar alumnos":
                    gestor.mostrarAlumnos();
                    break;
                case "3":
                case "mostrar repetidores":
                    gestor.mostrarRepetidores();
                    break;
                case "4":
                case "mostrar aprobados":
                    gestor.mostrarAprobados();
                    break;
                case "5":
                case "guardar":
                case "guardar cambios":
                    gestor.guardarCambios();
                    break;
                case "6":
                case "salir":
                case "exit":
                    if(!gestor.esCambiosGuardados()){
                        System.out.println("\nLos cambios no se han guardado.");
                        System.out.println("¿Desea salir de todos modos? (s/N)");
                        respuesta = sc.nextLine().toLowerCase();
                        if(respuesta.equals("s") || respuesta.equals("sí") || respuesta.equals("si") ){
                            System.out.println("Saliendo del programa. ¡Adiós!");
                            salida = true;
                        }
                    }else {
                        System.out.println("Saliendo del programa. ¡Adiós!");
                        salida = true;
                    }
                    break;
                default:
                    System.out.println("Opción no válida. Por favor seleccione una opción entre 1-6 o escriba 0 para ver el menú.");
            }

        }
    }

    public static void mostrarMenu(){
        StringBuilder sb = new StringBuilder();
        sb.append("==== MENÚ DE OPCIONES ====\n")
                .append("0. Mostrar Menú\n")
                .append("1. Crear Alumno\n")
                .append("2. Mostrar Alumnos\n")
                .append("3. Mostrar Repetidores\n")
                .append("4. Mostrar Aprobados\n")
                .append("5. Guardar Cambios\n")
                .append("6. Salir/Exit\n");

        System.out.println(sb);
    }

    public static void procesarCreacion(GestorAlumnos gestor, Scanner sc){
        System.out.println("==== CREAR ALUMNO ====\n");

        String dni = pedirDni(gestor, sc);
        if (dni == null) return;

        String nombre = pedirNombre("Introduzca el nombre del alumno: ", sc);
        if (nombre == null) return;

        String edad = pedirEdad(sc);
        if (edad == null) return;

        Float notaMedia = pedirNotaMedia(sc);
        if (notaMedia == null) return;

        Boolean repite = pedirRepite(sc);
        if (repite == null) return;

        sc.nextLine();
        System.out.println("\nLos datos recogidos son los siguientes:");
        System.out.println("DNI: " + dni + ", nombre: " + nombre + ", edad: " +edad+ ", nota: " + notaMedia + ", repite: " + repite);
        System.out.println("Si es correcto y desea continuar pulse 's'. Si desea abortar creación pulse cualquier otra tecla.");
        String respuesta = sc.nextLine().toLowerCase();
        if(respuesta.equals("s")){
            gestor.crearAlumno(dni,nombre,edad,notaMedia,repite);
        }else{
            System.out.println("Creación abortada.\n");
        }
    }

    private static Boolean pedirRepite(Scanner sc) {
        int intentos = 0;
        boolean valido = false;
        boolean repite = false;

        while(!valido){
            System.out.println("¿El alumno repite curso? (true/false)");
            try{
                repite = sc.nextBoolean();
                valido = true;
            }catch (InputMismatchException e){
                System.out.println("Respuesta no válida. Recuerde introducir true si es repetidor o false en caso contrario.");
                intentos++;
                sc.nextLine();
                if(intentos == 3){
                    System.out.println("Número máximo de intentos alcanzado. Proceso abortado.");
                    return null;
                }
            }
        }
        return repite;
    }

    private static Float pedirNotaMedia(Scanner sc) {
        int intentos = 0;
        boolean valido = false;
        Float notaMedia = 0.0f;

        while(!valido){
            System.out.println("Introduzca la nota media del alumno: (formato 0,0)");
            try{
                notaMedia = sc.nextFloat();
                valido = true;
            }catch (InputMismatchException e){
                System.out.println("Nota media no válida. Recuerde introducir un número decimal (formato 0,0).");
                intentos++;
                sc.nextLine();
                if(intentos == 3){
                    System.out.println("Número máximo de intentos alcanzado. Proceso abortado.");
                    return null;
                }
            }
        }
        return notaMedia;
    }

    private static String pedirEdad(Scanner sc) {
        boolean valido = false;
        int intentos= 0;
        String edad = "";

        while(!valido){
            System.out.println("Introduzca la edad del alumno: ");
            try{
                edad = String.valueOf(sc.nextInt());
                valido = true;
            }catch (InputMismatchException e){
                System.out.println("Edad no válida. Recuerde introducir un número entero.");
                intentos++;
                sc.nextLine();
                if(intentos == 3){
                    System.out.println("Número máximo de intentos alcanzado. Proceso abortado.");
                    return null;
                }
            }
        }
        return edad;
    }

    private static String pedirNombre(String mensaje, Scanner sc) {
        System.out.println(mensaje);
        return sc.nextLine();
    }

    private static String pedirDni(GestorAlumnos gestor, Scanner sc) {
        int intentos = 0;
        boolean valido = false;
        String dni = "";

        while (!valido) {
            System.out.println("Introduzca un DNI válido:");
            dni = sc.nextLine().toUpperCase();

            if (gestor.validarDni(dni)) {
                valido = true;
            } else {
                System.out.println("DNI no válido. Recuerde introducir un DNI válido.");
                intentos++;
                if (intentos == 3) {
                    System.out.println("Número máximo de intentos alcanzado. Proceso abortado.");
                    return null;
                }
            }
        }
        return dni;
    }
}