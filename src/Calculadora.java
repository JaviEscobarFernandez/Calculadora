import java.util.ArrayList;

public class Calculadora {
    private int resultado;
    private ArrayList<String> operaciones;
    private String ultimaOperacion;

    Calculadora() {
        resultado = 0;
        operaciones = new ArrayList<>();
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
    public void realizarOperaciones() {
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
