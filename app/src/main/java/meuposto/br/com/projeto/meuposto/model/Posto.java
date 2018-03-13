package meuposto.br.com.projeto.meuposto.model;

import com.google.firebase.database.DatabaseReference;

import java.util.Date;
import java.util.List;

import meuposto.br.com.projeto.meuposto.LocationData;
import meuposto.br.com.projeto.meuposto.config.ConfiguracaoFireBase;

/**
 * Created by leo_3 on 12/02/2018.
 */

public class Posto {

    private String id;
    private String nome;
    private String endereco;
    private String bandeira;
    private List<Combustivel> combustivel;
    private String latidude;
    private String longidude;

    public Posto() {
    }

    public Posto(String id, String nome, String endereco, String bandeira, List<Combustivel> combustivel, String latidude, String longidude) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.bandeira = bandeira;
        this.combustivel = combustivel;
        this.latidude = latidude;
        this.longidude = longidude;
    }

    public void salvar(){

        DatabaseReference referenciaFirebase = ConfiguracaoFireBase.getFirebase();
        referenciaFirebase.child("postos").child(String.valueOf(new Date().getTime())).setValue(this);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Combustivel> getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(List<Combustivel> combustivel) {
        this.combustivel = combustivel;
    }

    public String getLatidude() {
        return latidude;
    }

    public void setLatidude(String latidude) {
        this.latidude = latidude;
    }

    public String getLongidude() {
        return longidude;
    }

    public void setLongidude(String longidude) {
        this.longidude = longidude;
    }
}
