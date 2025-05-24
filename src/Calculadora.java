import java.util.ArrayList;

public class Calculadora {
    private int resultado;
    private ArrayList<String> operaciones;
    private String ultimaOperacion;

    Calculadora() {
        resultado = 0;
        operaciones = new ArrayList<>();
        operaciones.add("0"); // El primer numero por defecto siempre 0
        ultimaOperacion = "";
    }

    public int getResultado() { return resultado; }
    public void setResultado(int resultado) { this.resultado = resultado; }
    public void addOperation(String o) {
        // Si la ultima operacion era una operacion, la eliminamos para añadir la nueva.
        if (!getUltimaOperacion().isEmpty() && !operaciones.isEmpty())
            operaciones.removeLast();
        operaciones.add(o);
        setUltimaOperacion(o);
    }
    public void addOperando(String o) {
        // Si la ultima operacion era un operando, lo eliminamos para añadir el nuevo.
        if (getUltimaOperacion().isEmpty() && !operaciones.isEmpty())
            operaciones.removeLast();
        operaciones.add(o);
        setUltimaOperacion("");
    }
    public String getOperaciones() {
        String op = "";
        if (!operaciones.isEmpty()) {
            for (String o : operaciones) {
                String ope = o;
                switch (ope) {
                    case "sumar": ope = "+"; break;
                    case "restar": ope = "-"; break;
                    case "multiplicar": ope = "*"; break;
                    case "dividir": ope = "/"; break;
                    default:
                        break;
                }
                op += " " + ope;
            }
        }
        return op;
    }
    public void limpiarOperaciones(boolean resetearResultado) {
        operaciones.clear();
        setUltimaOperacion("");
        if (resetearResultado)
            setResultado(0);
    }
    public void setUltimaOperacion(String o)  { ultimaOperacion = o; }
    public String getUltimaOperacion() { return ultimaOperacion; }

    public void sumar(int valor) {
        System.out.println("Sumando: " + getResultado() + " con " + valor);
        resultado += valor;
    }
    public void restar(int valor) {
        System.out.println("Restando: " + getResultado() + " con " + valor);
        resultado -= valor;
    }
    public void multiplicar(int valor) {
        System.out.println("Multiplicando: " + getResultado() + " por " + valor);
        resultado *= valor;
    }
    public void dividir(int valor) {
        if (valor == 0) {
            System.err.println("ERROR: no puedes dividir entre 0");
            return;
        }
        System.out.println("Dividiendo: " + getResultado() + " entre " + valor);
        resultado /= valor;
    }
    public void realizarOperaciones2() {
        String operacion = "";
        boolean primeraOperacion = false;
        for (String i : operaciones) {
            if (!operacion.isEmpty())
                aplicarOperacion(operacion, i);
            operacion = switch (i) {
                case "sumar" -> "sumar";
                case "restar" -> "restar";
                case "multiplicar" -> "multiplicar";
                case "dividir" -> "dividir";
                default -> "";
            };
            if (!primeraOperacion) {
                primeraOperacion = true;
                if (operacion.isEmpty())
                    setResultado(Integer.parseInt(i));
            }
        }
        limpiarOperaciones(false);
    }

    public void realizarOperaciones() {
        // Primero resolvemos las multiplicaciones y divisiones
        ArrayList<String> nuevaLista = new ArrayList<>();
        int resultadoTemporal = Integer.parseInt(operaciones.getFirst()); // Primer número

        for (int i = 1; i < operaciones.size(); i += 2) {
            String operacion = operaciones.get(i);
            int valor = Integer.parseInt(operaciones.get(i + 1));
            /*
            switch (operacion) {
                case "multiplicar":
                case "dividir":
                    // resultadoTemporal += aplicarOperacion(operacion, operaciones.get(i + 1)); // To-Do
                    break;
                default:
                    nuevaLista.add(String.valueOf(resultadoTemporal));
                    nuevaLista.add(operacion);
                    resultadoTemporal = valor;
                    break;
            }*/

            if (operacion.equals("multiplicar")) {
                resultadoTemporal *= valor;
            } else if (operacion.equals("dividir")) {
                if (valor == 0) {
                    System.err.println("ERROR: No puedes dividir entre 0");
                    return;
                }
                resultadoTemporal /= valor;
            } else {
                nuevaLista.add(String.valueOf(resultadoTemporal));
                nuevaLista.add(operacion);
                resultadoTemporal = valor;
            }
        }
        nuevaLista.add(String.valueOf(resultadoTemporal));

        // Segundo paso: ahora procesamos sumas y restas
        resultadoTemporal = Integer.parseInt(nuevaLista.getFirst());

        for (int i = 1; i < nuevaLista.size(); i += 2) {
            String operacion = nuevaLista.get(i);
            int valor = Integer.parseInt(nuevaLista.get(i + 1));

            if (operacion.equals("sumar")) {
                resultadoTemporal += valor;
            } else if (operacion.equals("restar")) {
                resultadoTemporal -= valor;
            }
        }

        setResultado(resultadoTemporal);
        limpiarOperaciones(false);
    }

    private void aplicarOperacion(String operacion, String operando) {
        int valor = Integer.parseInt(operando);
        switch (operacion) {
            case "sumar":
                sumar(valor);
                break;
            case "restar":
                restar(valor);
                break;
            case "multiplicar":
                multiplicar(valor);
                break;
            case "dividir":
                dividir(valor);
                break;
            default:
                break;
        }
    }
}
