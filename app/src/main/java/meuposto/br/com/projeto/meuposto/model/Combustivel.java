package meuposto.br.com.projeto.meuposto.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by leo_3 on 12/02/2018.
 */

public class Combustivel implements Parcelable {

    private String tipo;
    private String preco;


    public Combustivel(String tipo, String preco) {
        this.tipo = tipo;
        this.preco = preco;
    }

    public Combustivel() {
    }

    protected Combustivel(Parcel in) {
        tipo = in.readString();
        preco = in.readString();
    }

    public static final Creator<Combustivel> CREATOR = new Creator<Combustivel>() {
        @Override
        public Combustivel createFromParcel(Parcel in) {
            return new Combustivel(in);
        }

        @Override
        public Combustivel[] newArray(int size) {
            return new Combustivel[size];
        }
    };

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tipo);
        dest.writeString(preco);
    }
}
