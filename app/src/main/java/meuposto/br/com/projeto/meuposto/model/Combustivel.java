package meuposto.br.com.projeto.meuposto.model;

/**
 * Created by leo_3 on 12/02/2018.
 */

public class Combustivel {
    private int id;
    private String tipo;
    private double preco;
    private boolean sincronizado;

    public Combustivel() {
    }

    public Combustivel(String tipo, double preco) {
        this.tipo = tipo;
        this.preco = preco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(boolean sincronizado) {
        this.sincronizado = sincronizado;
    }
}
