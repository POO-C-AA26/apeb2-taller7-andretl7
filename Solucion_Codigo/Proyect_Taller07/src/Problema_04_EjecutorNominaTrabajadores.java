/**
 * Problema 4: Se desea desarrollar un sistema de nómina para los trabajadores 
 * de una empresa. Los datos personales de los trabajadores son nombre y 
 * apellidos, dirección y DNI. Además, existen diferentes tipos de trabajadores:
 * Fijos Mensuales: que cobran una cantidad fija al mes.
 * Comisionistas: cobran un porcentaje fijo por las ventas que han realizado
 * Por Horas: cobran un precio por cada una de las horas que han realizado 
 * durante el mes. El precio es fijo para las primeras 40 horas y es otro para 
 * las horas realizadas a partir de la 40 hora mensual.
 * Jefe: cobra un sueldo fijo (no hay que calcularlo). Cada empleado tiene 
 * obligatoriamente un jefe (exceptuando los jefes que no tienen ninguno). El 
 * programa debe permitir dar de alta a trabajadores, así como fijar horas o 
 * ventas realizadas e imprimir la nómina correspondiente al final de mes.
 * @author Andre Tixe
 */

class Trabajador {
    public String nombre;
    public String apellidos;
    public String direccion;
    public String dni;
    public Jefe jefe; 
    public double sueldoFinal; 

    public Trabajador(String nombre, String apellidos, String direccion, String dni, Jefe jefe) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
        this.jefe = jefe;
    }

    public double calcularSueldo(double sueldoBase) {
        this.sueldoFinal = sueldoBase;
        return this.sueldoFinal;
    }

    @Override
    public String toString() {
        String nombreJefe = (jefe != null) ? jefe.nombre + " " + jefe.apellidos : "Ninguno (Es el Jefe Supremo)";
        return "Empleado: " + nombre + " " + apellidos + " | DNI: " + dni + " | Direccion: " + direccion + " | Jefe a cargo: " + nombreJefe;
    }
}

class Jefe extends Trabajador {
    public double sueldoFijo;

    public Jefe(String nombre, String apellidos, String direccion, String dni, double sueldoFijo, Jefe jefe) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.sueldoFijo = sueldoFijo;
    }

    public double calcularSueldo() {
        return super.calcularSueldo(this.sueldoFijo);
    }

    @Override
    public String toString() {
        return "Jefe -> " + super.toString() + " | Sueldo Neto: $" + calcularSueldo();
    }
}

class FijoMensual extends Trabajador {
    public double sueldoMensual;

    public FijoMensual(String nombre, String apellidos, String direccion, String dni, double sueldoMensual, Jefe jefe) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.sueldoMensual = sueldoMensual;
    }

    public double calcularSueldo() {
        return super.calcularSueldo(this.sueldoMensual);
    }

    @Override
    public String toString() {
        return "Fijo Mensual -> " + super.toString() + " | Sueldo Neto: $" + calcularSueldo();
    }
}

class Comisionista extends Trabajador {
    public double ventasRealizadas;
    public double porcentajeComision;

    public Comisionista(String nombre, String apellidos, String direccion, String dni, double porcentajeComision, Jefe jefe) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.porcentajeComision = porcentajeComision;
        this.ventasRealizadas = 0.0;
    }

    public double calcularSueldo() {
        double comisionCalculada = this.ventasRealizadas * (this.porcentajeComision / 100);
        return super.calcularSueldo(comisionCalculada);
    }

    @Override
    public String toString() {
        return "Comisionista -> " + super.toString() + " | Ventas: $" + ventasRealizadas + " (" + porcentajeComision + "%) | Sueldo Neto: $" + calcularSueldo();
    }
}

class PorHoras extends Trabajador {
    public int horasTrabajadas;
    public double precioHoraNormal;
    public double precioHoraExtra;

    public PorHoras(String nombre, String apellidos, String direccion, String dni, double precioHoraNormal, double precioHoraExtra, Jefe jefe) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.precioHoraNormal = precioHoraNormal;
        this.precioHoraExtra = precioHoraExtra;
        this.horasTrabajadas = 0;
    }

    public double calcularSueldo() {
        double pagoTotal = 0;
        if (horasTrabajadas <= 40) {
            pagoTotal = horasTrabajadas * precioHoraNormal;
        } else {
            int horasExtras = horasTrabajadas - 40;
            pagoTotal = (40 * precioHoraNormal) + (horasExtras * precioHoraExtra);
        }
        return super.calcularSueldo(pagoTotal);
    }

    @Override
    public String toString() {
        return "Por Horas -> " + super.toString() + " | Horas Totales: " + horasTrabajadas + " | Sueldo Neto: $" + calcularSueldo();
    }
}

public class Problema_04_EjecutorNominaTrabajadores {
    public static void main(String[] args) {
        Jefe jefeMaximo = new Jefe("Carlos", "Mendoza", "Av. Loja 123", "1104567890", 2500.0, null);
        FijoMensual emp1 = new FijoMensual("Andre", "Tixe", "Calle Sucre", "1105123456", 800.0, jefeMaximo);

        Comisionista emp2 = new Comisionista("Isaac", "Lojan", "San Sebastian", "1706789123", 12.0, jefeMaximo);
        emp2.ventasRealizadas = 5000.0; 

        PorHoras emp3 = new PorHoras("Juan", "Perez", "El Valle", "1103456781", 5.0, 8.5, jefeMaximo);
        emp3.horasTrabajadas = 45;      

        System.out.println(jefeMaximo);
        System.out.println(emp1);
        System.out.println(emp2);
        System.out.println(emp3);
    }
}

/**
run:
Jefe -> Empleado: Carlos Mendoza | DNI: 1104567890 | Direccion: Av. Loja 123 | Jefe a cargo: Ninguno (Es el Jefe Supremo) | Sueldo Neto: $2500.0
Fijo Mensual -> Empleado: Andre Tixe | DNI: 1105123456 | Direccion: Calle Sucre | Jefe a cargo: Carlos Mendoza | Sueldo Neto: $800.0
Comisionista -> Empleado: Isaac Lojan | DNI: 1706789123 | Direccion: San Sebastian | Jefe a cargo: Carlos Mendoza | Ventas: $5000.0 (12.0%) | Sueldo Neto: $600.0
Por Horas -> Empleado: Juan Perez | DNI: 1103456781 | Direccion: El Valle | Jefe a cargo: Carlos Mendoza | Horas Totales: 45 | Sueldo Neto: $242.5
BUILD SUCCESSFUL (total time: 0 seconds)
 */