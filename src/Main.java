import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        int operacion = 0;
        int valor = 0;
        System.out.println("Hola, esto es una calculadora, que operacion quieres realizar.");
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("##############################");
            System.out.println("Menú de la calculadora");
            System.out.println("Número actual: " + calculadora.getResultado());
            System.out.println("Operaciones en curso: " + calculadora.getOperaciones());
            System.out.println("1. Ingresar digito");
            System.out.println("2. Realizar operación: sumar");
            System.out.println("3. Realizar operación: restar");
            System.out.println("4. Realizar operación: multiplicar");
            System.out.println("5. Realizar operación: dividir");
            System.out.println("6. Calcular resultado");
            System.out.println("7. Limipiar resultado");
            System.out.println("8. Salir");
            System.out.println("##############################");
            String accion = sc.nextLine();
            operacion = validarAccion(accion);
            switch (operacion) {
                case 1:
                    System.out.print("Introduce el digito: ");
                    valor = obtenerValor(sc);
                    calculadora.addOperando(String.valueOf(valor)); // to-do falta modificar levemente
                    pausa(1);
                    break;
                case 2:
                    calculadora.addOperation("sumar");
                    pausa(1);
                    break;
                case 3:
                    calculadora.addOperation("restar");
                    pausa(1);
                    break;
                case 4:
                    calculadora.addOperation("multiplicar");
                    pausa(1);
                    break;
                case 5:
                    calculadora.addOperation("dividir");
                    pausa(1);
                    break;
                case 6:
                    calculadora.realizarOperaciones();
                    System.out.println("El resultado es: " + calculadora.getResultado());
                    pausa(3);
                    break;
                case 7:
                    calculadora.limpiarOperaciones(true);
                    System.out.println("Limpiado, ahora el valor es 0.");
                    pausa(3);
                    break;
                case 8:
                    operacion = 100;
                    break;
                default:
                    System.out.println("ERROR: Introduce un valor admitido.");
                    pausa(3);
                    break;
            }
        } while(operacion != 100);
        sc.close();
    }

    private static int validarAccion(String accion) {
        int valor = 0;
        try {
            valor = Integer.parseInt(accion);
            if (valor < 1 || valor > 8) {
                System.err.println("ERROR: valor de operación invalido.");
                valor = 0;
            }
        } catch(Exception ex) {
            System.err.println("ERROR: valor no admitido.");
            valor = 0;
        }

        return valor;
    }

    private static int obtenerValor(Scanner sc) {
        int valor = 0;
        try {
            valor = sc.nextInt();
            sc.nextLine();
        }
        catch (Exception ex) {
            System.err.println("ERROR: valor no admitido.");
            valor = 0;
        }
        return valor;
    }

    private static void pausa(int cant) {
        cant *= 1000; // pasamos a milisegundos
        try {
            Thread.sleep(cant);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}