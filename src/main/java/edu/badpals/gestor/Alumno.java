package edu.badpals.gestor;

public class Alumno {
    private String dni;
    private String nombre;
    private Integer edad;
    private Float notaMedia;
    private boolean repite;

    public Alumno(){};

    public Alumno(String dni){
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Float getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(Float notaMedia) {
        this.notaMedia = notaMedia;
    }

    public void repite(boolean repiteCurso){
        this.repite = repiteCurso;
    }

    public boolean esRepetidor(){
        return this.repite;
    }

}
