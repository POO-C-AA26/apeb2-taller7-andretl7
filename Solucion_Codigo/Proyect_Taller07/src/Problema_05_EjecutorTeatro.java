/**
 * Dadas las siguientes clases, que expresan una relación de herencia entre 
 * las entidades:Se desea gestionar la venta de entradas para un espectáculo 
 * en un teatro. El patio de butacas del teatro se divide en varias zonas, 
 * cada una identificada por su nombre. Los datos de las zonas son los 
 * mostrados en la siguiente tabla:

NOMBRE ZONA	NÚMERO DE LOCALIDADES	PRECIO NORMA	PRECIO ABONADO
Principal	200	25$	17.5$
PalcoB	40	70$	40$
Central	400	20$	14$
Lateral	100	15.5$	10$
Para realizar la compra de una entrada, un espectador debe indicar la zona 
* que desea y presentar al vendedor el documento que justifique que tiene algún 
* tipo de descuento (estudiante, abonado o pensionista). El vendedor sacará la 
* entrada del tipo apropiado y de la zona indicada, en el momento de la compra 
* se asignará a la entrada un identificador (un número entero) que permitirá la
* identificación de la entrada en todas las operaciones que posteriormente se
* desee realizar con ella.
* Una entrada tiene como datos asociados su identificador, la zona a la que 
* pertenece y el nombre del comprador.
* Los precios de las entradas dependen de la zona y del tipo de entrada según lo 
* explicado a continuación:
* Entradas normales: su precio es el precio normal de la zona elegida sin ningún
* tipo de descuento.
* Entradas reducidas (para estudiantes o pensionistas): su precio tiene una 
* rebaja del 15% sobre el precio normal de la zona elegida.
* Entradas abonado: su precio es el precio para abonados de la zona elegida.
* La interacción entre el vendedor y la aplicación es la descrita en los 
* siguientes casos de usos
 * @author Andre Tixe
 */

class Zona {
    public String nombreZona;
    public int cantidadLocalidades;
    public double precioNormal, precioAbonado;

    public Zona(String nombreZona, int cantidadLocalidades, double precioNormal, double precioAbonado) {
        this.nombreZona = nombreZona;
        this.cantidadLocalidades = cantidadLocalidades;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
    }

    @Override
    public String toString() {
        return nombreZona + " - Disponibles: " + cantidadLocalidades;
    }
}

class Entrada {
    public int idEntrada, numeroEntradas;
    public Zona zona;
    public String nombreComprador;
    public double costoFinalEntrada;

    public Entrada(int idEntrada, int numeroEntradas, Zona zona, String nombreComprador) {
        this.idEntrada = idEntrada;
        this.numeroEntradas = numeroEntradas;
        this.zona = zona;
        this.nombreComprador = nombreComprador;
    }

    @Override
    public String toString() {
        return "ID: " + idEntrada + " | Comprador: " + nombreComprador + " | Cantidad: " + numeroEntradas + " | " + zona + " | Total: $" + costoFinalEntrada;
    }
    
    public double calcularCostoFinalEntrada(double precioEntrada) {
        this.costoFinalEntrada = (this.numeroEntradas * precioEntrada);
        this.zona.cantidadLocalidades -= this.numeroEntradas; 
        return this.costoFinalEntrada;
    }
}

class Entrada_Normal extends Entrada {

    public Entrada_Normal(int idEntrada, int numeroEntradas, Zona zona, String nombreComprador) {
        super(idEntrada, numeroEntradas, zona, nombreComprador);
    }

    public double calcularCostoFinalEntrada() {
        return super.calcularCostoFinalEntrada(zona.precioNormal);
    }

    @Override
    public String toString() {
        return "Entrada Normal -> " + super.toString();
    }
}

class Entrada_Reducida extends Entrada {
    public double porcentajeRebaja;

    public Entrada_Reducida(double porcentajeRebaja, int idEntrada, int numeroEntradas, Zona zona, String nombreComprador) {
        super(idEntrada, numeroEntradas, zona, nombreComprador);
        this.porcentajeRebaja = porcentajeRebaja;
    }

    public double calcularCostoFinalEntrada() {
        double precioDescuento = zona.precioNormal - (zona.precioNormal * (porcentajeRebaja / 100));
        return super.calcularCostoFinalEntrada(precioDescuento);
    }

    @Override
    public String toString() {
        return "Entrada Reducida (" + porcentajeRebaja + "% Desc.) -> " + super.toString();
    }
}

class Entrada_Abonado extends Entrada {

    public Entrada_Abonado(int idEntrada, int numeroEntradas, Zona zona, String nombreComprador) {
        super(idEntrada, numeroEntradas, zona, nombreComprador);
    }

    public double calcularCostoFinalEntrada() {
        return super.calcularCostoFinalEntrada(zona.precioAbonado);
    }

    @Override
    public String toString() {
        return "Entrada Abonado -> " + super.toString();
    }
}

public class Problema_05_EjecutorTeatro {
    public static void main(String[] args) {
        Zona zona1 = new Zona("Principal", 200, 25, 17.5);
        Zona zona2 = new Zona("PalcoB", 40, 70, 40);
        Zona zona3 = new Zona("Central", 400, 20, 14);
        Zona zona4 = new Zona("Lateral", 100, 15.5, 10);
        
        Entrada_Normal entNormal = new Entrada_Normal(1, 1, zona4, "Daniel");
        Entrada_Reducida entReducida = new Entrada_Reducida(15, 2, 1, zona2, "Andre");
        Entrada_Abonado entAbonado = new Entrada_Abonado(3, 2, zona3, "Raul y Enrique");
        
        entNormal.calcularCostoFinalEntrada();
        entReducida.calcularCostoFinalEntrada();
        entAbonado.calcularCostoFinalEntrada();
        
        System.out.println(entNormal);
        System.out.println(entReducida);
        System.out.println(entAbonado);
    }
}
/**
run:
Entrada Normal -> ID: 1 | Comprador: Daniel | Cantidad: 1 | Lateral - Disponibles: 99 | Total: $15.5
Entrada Reducida (15.0% Desc.) -> ID: 2 | Comprador: Andre | Cantidad: 1 | PalcoB - Disponibles: 39 | Total: $59.5
Entrada Abonado -> ID: 3 | Comprador: Raul y Enrique | Cantidad: 2 | Central - Disponibles: 398 | Total: $28.0
BUILD SUCCESSFUL (total time: 0 seconds)
 */

