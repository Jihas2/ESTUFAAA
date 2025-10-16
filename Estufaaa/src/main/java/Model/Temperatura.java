package Model;

public class Temperatura {
    private int id;
    private double valor;
    private int hora;
    private String dataHora;

    public Temperatura(double valor, int hora) {
        this.valor = valor;
        this.hora = hora;
    }

    public Temperatura(int id, double valor, int hora, String dataHora) {
        this.id = id;
        this.valor = valor;
        this.hora = hora;
        this.dataHora = dataHora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}