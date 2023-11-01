import java.util.ArrayList;
import java.util.List;

class Alumno {
    private String nombre;
    private String matricula;
    private List<Inscripcion> inscripciones;

    public Alumno(String nombre, String matricula) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.inscripciones = new ArrayList<>();
    }

    public void inscribir(Materia materia) {
        Inscripcion inscripcion = new Inscripcion(this, materia);
        inscripciones.add(inscripcion);
    }

    public boolean aprobada(Materia materia) {
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getMateria() == materia && inscripcion.aprobada()) {
                return true;
            }
        }
        return false;
    }
}

class Materia {
    private String nombre;
    private List<Materia> correlativas;

    public Materia(String nombre) {
        this.nombre = nombre;
        this.correlativas = new ArrayList<>();
    }

    public void agregarCorrelativa(Materia correlativa) {
        correlativas.add(correlativa);
    }

    public boolean correlativasAprobadas(Alumno alumno) {
        for (Materia correlativa : correlativas) {
            if (!alumno.aprobada(correlativa)) {
                return false;
            }
        }
        return true;
    }
}

class Inscripcion {
    private Alumno alumno;
    private Materia materia;

    public Inscripcion(Alumno alumno, Materia materia) {
        this.alumno = alumno;
        this.materia = materia;
    }

    public boolean aprobada() {
     return obtenerPuntuacion() >= 60;
    }

    public int obtenerPuntuacion(){
        return (int) (Math.random() * 100);
    }

    public Materia getMateria() {
        return materia;
    }
}

public class Main {
    public static void main(String[] args) {
        Alumno alumno1 = new Alumno("Juan", "12345");
        Alumno alumno2 = new Alumno("Maria", "67890");

        Materia materia1 = new Materia("Algoritmos y Estructuras de Datos");
        Materia materia2 = new Materia("Paradigmas de Programación");
        Materia materia3 = new Materia("Diseño de Sistemas");

        materia2.agregarCorrelativa(materia1);
        materia3.agregarCorrelativa(materia2);

        alumno1.inscribir(materia1);
        alumno1.inscribir(materia2);
        alumno1.inscribir(materia3);

        alumno2.inscribir(materia1);
        alumno2.inscribir(materia2);
        alumno2.inscribir(materia3);

        System.out.println("alumno Juan:");
        System.out.println(alumno1.aprobada(materia1));
        System.out.println(alumno1.aprobada(materia2));
        System.out.println(alumno1.aprobada(materia3));

        System.out.println("alumna Maria:");
        System.out.println(alumno1.aprobada(materia1));
        System.out.println(alumno1.aprobada(materia2));
        System.out.println(alumno1.aprobada(materia3));
    }
}
