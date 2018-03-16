package meuposto.br.com.projeto.meuposto.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
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
    private ArrayList<Combustivel> combustivel;

    private LatLng latLng;
    private double latidude;
    private double longidude;


    public Posto() {
    }

    public Posto(String id, String nome, String endereco, String bandeira, ArrayList<Combustivel> combustivel, double latidude, double longidude) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.bandeira = bandeira;
        this.combustivel = combustivel;
        this.latidude = latidude;
        this.longidude = longidude;
    }

    public void salvar(){

        FirebaseAuth firebaseAuth = ConfiguracaoFireBase.getFirebaseAutentica();
        DatabaseReference referenciaFirebase = ConfiguracaoFireBase.getFirebase();
        referenciaFirebase.child("postos").child(firebaseAuth.getCurrentUser().getUid()).setValue(this);
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
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

    public void setCombustivel(ArrayList<Combustivel> combustivel) {
        this.combustivel = combustivel;
    }



    public double getLatidude() {
        return latidude;
    }

    public void setLatidude(double latidude) {
        this.latidude = latidude;
    }

    public double getLongidude() {
        return longidude;
    }

    public void setLongidude(double longidude) {
        this.longidude = longidude;
    }
}
