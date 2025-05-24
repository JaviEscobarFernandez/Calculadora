public class Calculadora {
    private int resultado;

    Calculadora() {
        resultado = 0;
    }

    public int getResultado() { return resultado; }
    public void setResultado(int resultado) { this.resultado = resultado; }

    public void sumar(int valor) { resultado += valor; }
    public void restar(int valor) { resultado -= valor; }
    public void multiplicar(int valor) { resultado *= valor; }
    public void dividir(int valor) {
        if (valor == 0) {
            System.err.println("ERROR: no puedes dividir entre 0");
            return;
        }
        resultado /= valor;
    }
}
