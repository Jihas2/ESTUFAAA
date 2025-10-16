package Model;

import java.util.ArrayList;

public class Estufa {
    private double temperaturaMinima;
    private double temperaturaMaxima;
    private ArrayList<Temperatura> temperaturas;
    private int totalHoras;

    public Estufa(double temperaturaMinima, double temperaturaMaxima, int totalHoras) {
        this.temperaturaMinima = temperaturaMinima;
        this.temperaturaMaxima = temperaturaMaxima;
        this.totalHoras = totalHoras;
        this.temperaturas = new ArrayList<>();
    }

    public void adicionarTemperatura(double valor, int hora) {
        temperaturas.add(new Temperatura(valor, hora));
    }

    public double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(double temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(double temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public ArrayList<Temperatura> getTemperaturas() {
        return temperaturas;
    }

    public int getTotalHoras() {
        return totalHoras;
    }

    public boolean estaDentroDoPadrao(double temperatura) {
        return temperatura >= temperaturaMinima && temperatura <= temperaturaMaxima;
    }

    public boolean estaAcimaDoMaximo(double temperatura) {
        return temperatura > temperaturaMaxima;
    }

    public boolean estaAbaixoDoMinimo(double temperatura) {
        return temperatura < temperaturaMinima;
    }

    public double calcularMedia() {
        double soma = 0;
        for (Temperatura temp : temperaturas) {
            soma += temp.getValor();
        }
        return soma / temperaturas.size();
    }

    public double obterMenorTemperatura() {
        double menor = Double.MAX_VALUE;
        for (Temperatura temp : temperaturas) {
            if (temp.getValor() < menor) {
                menor = temp.getValor();
            }
        }
        return menor;
    }

    public double obterMaiorTemperatura() {
        double maior = Double.MIN_VALUE;
        for (Temperatura temp : temperaturas) {
            if (temp.getValor() > maior) {
                maior = temp.getValor();
            }
        }
        return maior;
    }

    public int contarTemperaturasAcimaMaximo() {
        int contador = 0;
        for (Temperatura temp : temperaturas) {
            if (estaAcimaDoMaximo(temp.getValor())) {
                contador++;
            }
        }
        return contador;
    }

    public int contarTemperaturasAbaixoMinimo() {
        int contador = 0;
        for (Temperatura temp : temperaturas) {
            if (estaAbaixoDoMinimo(temp.getValor())) {
                contador++;
            }
        }
        return contador;
    }

    public int contarTemperaturasDentroDoPadrao() {
        int contador = 0;
        for (Temperatura temp : temperaturas) {
            if (estaDentroDoPadrao(temp.getValor())) {
                contador++;
            }
        }
        return contador;
    }

    public double calcularVariacaoTermica() {
        return obterMaiorTemperatura() - obterMenorTemperatura();
    }
}