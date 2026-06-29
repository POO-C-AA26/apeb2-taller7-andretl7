/**
 * Problema 3: Implemente un sistema de envío de mensajes a móviles. Existen 
 * dos tipos de mensajes que se pueden enviar entre móviles, mensajes de texto 
 * (SMS) y mensajes que contienen imágenes (MMS). Por un lado, los mensajes de 
 * texto contienen un mensaje en caracteres que se desea enviar de un móvil a 
 * otro. Por otro lado, los mensajes que contienen imágenes almacenan 
 * información sobre la imagen a enviar, la cual se representará por el nombre 
 * del fichero que la contiene. Independientemente del tipo de mensaje, cada 
 * mensaje tendrá asociado un remitente de dicho mensaje y un destinatario. 
 * Ambos estarán definidos obligatoriamente por un número de móvil, y 
 * opcionalmente se podrá guardar información sobre su nombre. Además, los 
 * métodos enviarMensaje y visualizarMensaje deben estar definidos.
 * @author Andre Tixe
 */

class Movil {
    private String numero;
    private String nombre;

    public Movil(String numero) {
        this.numero = numero;
    }

    public Movil(String numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
    }

    public String getNumero() { return numero; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return nombre != null ? nombre + " (" + numero + ")" : numero;
    }
}

class Mensaje {
    private Movil remitente;
    private Movil destinatario;

    public Mensaje(Movil remitente, Movil destinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
    }

    public String enviarMensaje() {
        return "Mensaje enviado de " + remitente + " a " + destinatario;
    }

    public String visualizarMensaje() {
        return "De: " + remitente + " -> Para: " + destinatario;
    }

    public Movil getRemitente() { return remitente; }
    public Movil getDestinatario() { return destinatario; }

    @Override
    public String toString() {
        return "Mensaje{remitente=" + remitente + ", destinatario=" + destinatario + "}";
    }
}

class SMS extends Mensaje {
    private String texto;

    public SMS(Movil remitente, Movil destinatario, String texto) {
        super(remitente, destinatario);
        this.texto = texto;
    }

    public String enviarMensaje() {
        return "SMS enviado de " + getRemitente() + " a " + getDestinatario()
                + " | Texto: " + texto;
    }

    public String visualizarMensaje() {
        return super.visualizarMensaje() + "\nTexto: " + texto;
    }

    @Override
    public String toString() {
        return "SMS{\n  " + super.visualizarMensaje() + "\n  Texto: " + texto + "\n}";
    }
}

class MMS extends Mensaje {
    private String nombreFichero;

    public MMS(Movil remitente, Movil destinatario, String nombreFichero) {
        super(remitente, destinatario);
        this.nombreFichero = nombreFichero;
    }

    public String enviarMensaje() {
        return "MMS enviado de " + getRemitente() + " a " + getDestinatario()
                + " | Imagen: " + nombreFichero;
    }

    public String visualizarMensaje() {
        return super.visualizarMensaje() + "\nImagen: " + nombreFichero;
    }

    @Override
    public String toString() {
        return "MMS{\n  " + super.visualizarMensaje() + "\n  Imagen: " + nombreFichero + "\n}";
    }
}

public class Problema_03_EjecutorMensajes {
    public static void main(String[] args) {
        Movil mov1 = new Movil("0991234567", "Andre");
        Movil mov2 = new Movil("0987654321", "Daniel");
        Movil mov3 = new Movil("0976543210");

        SMS sms1 = new SMS(mov1, mov2, "Hola Daniel, como estas?");
        SMS sms2 = new SMS(mov2, mov1, "Todo bien Andre, y tu?");
        MMS mms1 = new MMS(mov1, mov3, "foto_vacaciones.jpg");
        MMS mms2 = new MMS(mov3, mov2, "documento_tarea.png");

        System.out.println("=== ENVIANDO MENSAJES ===\n");
        System.out.println(sms1.enviarMensaje());
        System.out.println(sms2.enviarMensaje());
        System.out.println(mms1.enviarMensaje());
        System.out.println(mms2.enviarMensaje());

        System.out.println("\n=== VISUALIZANDO MENSAJES ===\n");
        System.out.println(sms1);
        System.out.println();
        System.out.println(sms2);
        System.out.println();
        System.out.println(mms1);
        System.out.println();
        System.out.println(mms2);
    }
}

/**
run:
=== ENVIANDO MENSAJES ===

SMS enviado de Andre (0991234567) a Daniel (0987654321) | Texto: Hola Daniel, como estas?
SMS enviado de Daniel (0987654321) a Andre (0991234567) | Texto: Todo bien Andre, y tu?
MMS enviado de Andre (0991234567) a 0976543210 | Imagen: foto_vacaciones.jpg
MMS enviado de 0976543210 a Daniel (0987654321) | Imagen: documento_tarea.png

=== VISUALIZANDO MENSAJES ===

SMS{
  De: Andre (0991234567) -> Para: Daniel (0987654321)
  Texto: Hola Daniel, como estas?
}

SMS{
  De: Daniel (0987654321) -> Para: Andre (0991234567)
  Texto: Todo bien Andre, y tu?
}

MMS{
  De: Andre (0991234567) -> Para: 0976543210
  Imagen: foto_vacaciones.jpg
}

MMS{
  De: 0976543210 -> Para: Daniel (0987654321)
  Imagen: documento_tarea.png
}
BUILD SUCCESSFUL (total time: 0 seconds)
 */