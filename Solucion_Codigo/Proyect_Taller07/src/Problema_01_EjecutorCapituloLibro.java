
import java.util.ArrayList;

/**
 * Problema 01: Dibuje un diagrama de clases que muestre la estructura de un 
 * capítulo de libro; un capítulo está compuesto por varias secciones, cada 
 * una de las cuales comprende varios párrafos y figuras. Un párrafo incluye 
 * varias sentencias, cada una de las cuales contiene varias palabras.
 * @author Andre Tixe
 */

class Palabra {
    public String valor;
    public Palabra(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}

class Sentencia {
    public ArrayList<Palabra> palabras;
    
    public Sentencia() {
        this.palabras = new ArrayList<>();
    }
    public void agregarPalabra(Palabra p) {
        this.palabras.add(p);
    }
    
    @Override
    public String toString() {
        String result = "    Sentencia: ";
        for (Palabra p : palabras) {
            result += p.valor + " ";
        }
        return result;
    }
}

class Componente {
    public String tipo;
    public Componente(String tipo) {
        this.tipo = tipo;
    }   
}

class Parrafo extends Componente {
    public ArrayList<Sentencia> sentencias;
    
    public Parrafo() {
        super("parrafo");
        this.sentencias = new ArrayList<>();
    }
    
    public void agregarSentencia(Sentencia s) {
        this.sentencias.add(s);
    }

    @Override
    public String toString() {
        String result = "  Parrafo:\n";
        for (Sentencia s : sentencias) {
            result += s + "\n";
        }
        return result;
    }
}

class Figura extends Componente {
    public String descripcion;
    public String url;

    public Figura(String descripcion, String url) {
        super("figura");
        this.descripcion = descripcion;
        this.url = url;
    }
    
    @Override
    public String toString() {
        return "  Figura:\n    descripcion=" + descripcion + " | url=" + url;
    }
}

class Seccion {
    public ArrayList<Componente> componentes;
    public String titulo;

    public Seccion(String titulo) {
        this.componentes = new ArrayList<>();
        this.titulo = titulo;
    }
    
    public void agregarComponente(Componente c) {
        this.componentes.add(c);
    }
    
    @Override
    public String toString() {
        String result = " Seccion: " + titulo + "\n";
        for (Componente c : componentes) {
            result += c + "\n";
        }
        return result;
    }
}

class Capitulo {
    public ArrayList<Seccion> secciones;
    public String titulo;
    public int numero;

    public Capitulo(String titulo, int numero) {
        this.secciones = new ArrayList<>();
        this.titulo = titulo;
        this.numero = numero;
    }
    
    public void agregarSeccion(Seccion s) {
        this.secciones.add(s);
    }

    @Override
    public String toString() {
        String result = "Capitulo " + numero + ": " + titulo + "\n";
        for (Seccion s : secciones) {
            result += s + "\n";
        }
        return result;
    }  
}

public class Problema_01_EjecutorCapituloLibro {
    public static void main(String[] args) {
        // Palabras
        Palabra p1 = new Palabra("Java");
        Palabra p2 = new Palabra("es");
        Palabra p3 = new Palabra("potente");
        Palabra p4 = new Palabra("orientado");
        Palabra p5 = new Palabra("a");
        Palabra p6 = new Palabra("objetos");

        // Sentencia 1
        Sentencia s1 = new Sentencia();
        s1.agregarPalabra(p1); // "Java"
        s1.agregarPalabra(p2);
        s1.agregarPalabra(p3);

        // Sentencia 2 - reutiliza "Java"
        Sentencia s2 = new Sentencia();
        s2.agregarPalabra(p1); // misma palabra "Java"
        s2.agregarPalabra(p2);
        s2.agregarPalabra(p4);
        s2.agregarPalabra(p5);
        s2.agregarPalabra(p6);
        
         // Parrafo
        Parrafo parrafo1 = new Parrafo();
        parrafo1.agregarSentencia(s1);
        parrafo1.agregarSentencia(s2);
        
        // Figura
        Figura figura1 = new Figura("Diagrama de clases", "img/diagrama.png");
        
        // Seccion
        Seccion seccion1 = new Seccion("Introduccion a POO");
        seccion1.agregarComponente(parrafo1);
        seccion1.agregarComponente(figura1);
        
        // Capitulo
        Capitulo capitulo1 = new Capitulo("Programacion Orientada a Objetos", 1);
        capitulo1.agregarSeccion(seccion1);
        System.out.println(capitulo1);
    }
}

/**
run:
Capitulo 1: Programacion Orientada a Objetos
 Seccion: Introduccion a POO
  Parrafo:
    Sentencia: Java es potente 
    Sentencia: Java es orientado a objetos 

  Figura:
    descripcion=Diagrama de clases | url=img/diagrama.png


BUILD SUCCESSFUL (total time: 0 seconds)
 */

