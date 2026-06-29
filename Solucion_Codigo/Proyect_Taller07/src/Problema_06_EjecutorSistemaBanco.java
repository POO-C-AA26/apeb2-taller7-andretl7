/**
 * Problema 6: El banco UN BANCO mantiene las cuentas de varios clientes. 
 * Los datos que describen a cada una de las cuentas consisten en el número de 
 * cuenta, el nombre del cliente y el balance actual. Escriba una clase para 
 * implementar dicha cuenta bancaria. El método constructor debe aceptar como
 * parámetros el número de cuenta y el nombre. Debe proporcionarse métodos para 
 * depositar o retirar una cantidad de dinero y obtener el balance actual.
 * El banco ofrece a sus clientes dos tipos de cuentas, una de CHEQUES y una de 
 * AHORROS. Una cuenta de cheques puede sobregirarse (el balance puede ser 
 * menor que cero), pero una cuenta de ahorros no. Al final de cada mes, se 
 * calcula el interés sobre la cantidad que tenga la cuenta de ahorros. Este
 * interés se suma al balance. Escriba clases para describir cada uno de estos
 * tipos de cuentas, haciendo un máximo uso de la herencia. La clase de la 
 * cuenta de ahorros debe proporcionar un método que sea invocado para calcular 
 * el interés. Además, el banco está pensando en implementar una cuenta PLATINO
 * que viene siendo similar a los otros dos tipos anteriores de cuentas
 * bancarias, ésta tiene el interés del 10%, sin cargos ni castigos 
 * por sobregiro.
 * @author Andre Tixe
 */

class CuentaBancaria {
    public String numeroCuenta;
    public String nombreCliente;
    public double balanceActual;

    public CuentaBancaria(String numeroCuenta, String nombreCliente) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
        this.balanceActual = 0.0;
    }

    public void depositar(double cantidad) {
        if (cantidad > 0) {
            this.balanceActual += cantidad;
        }
    }

    public void retirar(double cantidad) {
        if (cantidad > 0) {
            this.balanceActual -= cantidad;
        }
    }

    public double ejecutarTransaccionInteres(double valorInteres) {
        this.depositar(valorInteres);
        return this.balanceActual;
    }

    @Override
    public String toString() {
        return "Cuenta: " + numeroCuenta + " | Cliente: " + nombreCliente + " | Balance Actual: $" + balanceActual;
    }
}

class CuentaCheques extends CuentaBancaria {
    public CuentaCheques(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }

    @Override
    public String toString() {
        return "Cuenta Cheques -> " + super.toString();
    }
}

class CuentaAhorros extends CuentaBancaria {
    public double tasaInteresMensual;

    public CuentaAhorros(String numeroCuenta, String nombreCliente, double tasaInteresMensual) {
        super(numeroCuenta, nombreCliente);
        this.tasaInteresMensual = tasaInteresMensual;
    }

    public double calcularYSumarInteres() {
        double dineroInteres = this.balanceActual * (this.tasaInteresMensual / 100);
        return super.ejecutarTransaccionInteres(dineroInteres);
    }

    public boolean realizarRetiroSeguro(double cantidad) {
        if (this.balanceActual - cantidad >= 0) {
            super.retirar(cantidad); 
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Cuenta Ahorros (Interes: " + tasaInteresMensual + "%) -> " + super.toString();
    }
}

class CuentaPlatino extends CuentaBancaria {
    public CuentaPlatino(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }

    public double calcularYSumarInteres() {
        double dineroInteres = this.balanceActual * 0.10;
        return super.ejecutarTransaccionInteres(dineroInteres);
    }

    @Override
    public String toString() {
        return "Cuenta Platino (Interes: 10.0%) -> " + super.toString();
    }
}

public class Problema_06_EjecutorSistemaBanco {
    public static void main(String[] args) {
        CuentaCheques c1 = new CuentaCheques("CH-5521", "Andre Tixe");
        CuentaAhorros c2 = new CuentaAhorros("AH-9082", "Naomi Ramirez", 2.5); 
        CuentaPlatino c3 = new CuentaPlatino("PL-0001", "Javier Gonzalez");

        c1.depositar(100.0);
        c1.retirar(150.0); 

        c2.depositar(200.0);
        if (!c2.realizarRetiroSeguro(250.0)) {
            System.out.println("[ALERTA] Retiro denegado a " + c2.nombreCliente + ": Fondos insuficientes.");
        }
        c2.calcularYSumarInteres(); 

        c3.depositar(500.0);
        c3.retirar(600.0); 
        c3.calcularYSumarInteres(); 

        System.out.println("\n================ ESTADOS DE CUENTA FINALES ================");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }
}

/**
run:
[ALERTA] Retiro denegado a Naomi Ramirez: Fondos insuficientes.

================ ESTADOS DE CUENTA FINALES ================
Cuenta Cheques -> Cuenta: CH-5521 | Cliente: Andre Tixe | Balance Actual: $-50.0
Cuenta Ahorros (Interes: 2.5%) -> Cuenta: AH-9082 | Cliente: Naomi Ramirez | Balance Actual: $205.0
Cuenta Platino (Interes: 10.0%) -> Cuenta: PL-0001 | Cliente: Javier Gonzalez | Balance Actual: $-100.0
BUILD SUCCESSFUL (total time: 0 seconds)
 */