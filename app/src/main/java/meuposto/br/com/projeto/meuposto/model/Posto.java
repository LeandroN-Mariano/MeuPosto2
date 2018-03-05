package meuposto.br.com.projeto.meuposto.model;

import java.util.List;

/**
 * Created by leo_3 on 12/02/2018.
 */

public class Posto {

    private int id;
    private String nome;
    private String endereco;
    private String bandeira;
    private List<Combustivel> combustivel;
    private boolean sincronizado;
    private int id_combustivel;

    public Posto() {
    }

    public Posto(String nome, String endereco, String bandeira) {
        this.nome = nome;
        this.endereco = endereco;
        this.bandeira = bandeira;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Combustivel> getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(List<Combustivel> combustivel) {
        this.combustivel = combustivel;
    }

    public boolean isSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(boolean sincronizado) {
        this.sincronizado = sincronizado;
    }

    public int getId_combustivel() {
        return id_combustivel;
    }

    public void setId_combustivel(int id_combustivel) {
        this.id_combustivel = id_combustivel;
    }
}
