package edu.badpals.gestor;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class GestorFichero {
    private static String RUTA_FICHERO_ALUMNOS = "src/main/resources/alumnos.xml";
    private static String RUTA_FICHERO_REPETIDORES = "src/main/resources/repetidores.xml";
    private static String RUTA_FICHERO_APROBADOS = "src/main/resources/aprobados.xml";

    public GestorFichero(){};

    public void escribirFicheros(Set<Alumno> alumnos){
        Set<Alumno> repetidores = new HashSet<>();
        Set<Alumno> aprobados = new HashSet<>();

        for( Alumno alumno : alumnos){
            if (alumno.esRepetidor()){
                repetidores.add(alumno);
            }
            if (alumno.getNotaMedia() >= 5.0){
                aprobados.add(alumno);
            }
        }

        crearXml(alumnos,RUTA_FICHERO_ALUMNOS);
        crearXml(repetidores,RUTA_FICHERO_REPETIDORES);
        crearXml(aprobados,RUTA_FICHERO_APROBADOS);
    }

    private void crearXml(Set<Alumno> alumnos,String ruta){
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document doc = implementation.createDocument(null,"alumnos",null);

            Element raiz = doc.getDocumentElement();

            for (Alumno alumno : alumnos){
                Element elementoAlumno = doc.createElement("alumno");
                raiz.appendChild(elementoAlumno);

                Element dni = doc.createElement("dni");
                elementoAlumno.appendChild(dni);
                Text dniTexto = doc.createTextNode(alumno.getDni());
                dni.appendChild(dniTexto);

                Element nombre = doc.createElement("nombre");
                elementoAlumno.appendChild(nombre);
                Text nombreTexto = doc.createTextNode(alumno.getNombre());
                nombre.appendChild(nombreTexto);

                Element edad = doc.createElement("edad");
                elementoAlumno.appendChild(edad);
                Text edadTexto = doc.createTextNode(String.valueOf(alumno.getEdad()));
                edad.appendChild(edadTexto);

                Element notaMedia = doc.createElement("notaMedia");
                elementoAlumno.appendChild(notaMedia);
                Text notaMediaTexto = doc.createTextNode(String.valueOf(alumno.getNotaMedia()));
                notaMedia.appendChild(notaMediaTexto);

                Element repite = doc.createElement("repite");
                elementoAlumno.appendChild(repite);
                Text repiteTexto = doc.createTextNode(alumno.esRepetidor()? "true":"false");
                repite.appendChild(repiteTexto);
            }
            Source source = new DOMSource(doc);
            Result resultado = new StreamResult(new File(ruta));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.transform(source,resultado);


        } catch (ParserConfigurationException | TransformerException e) {
            System.err.println("Error al crear el fichero XML en la ruta: " + ruta);
            e.printStackTrace();
        }
    }
}
