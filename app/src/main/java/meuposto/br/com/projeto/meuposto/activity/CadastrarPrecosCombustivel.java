package meuposto.br.com.projeto.meuposto.activity;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import meuposto.br.com.projeto.meuposto.R;
import meuposto.br.com.projeto.meuposto.model.Combustivel;

public class CadastrarPrecosCombustivel extends AppCompatActivity {

    private EditText gasolina;
    private EditText gasolinaAdiv;
    private EditText etanol;
    private EditText diesel;
    private EditText dieselS10;
    private Button adicionar;
    private double latitude ;
    private double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_precos_combustivel);

        gasolina = (EditText)findViewById(R.id.precoGasolina);
        gasolinaAdiv = (EditText)findViewById(R.id.gasolinaAdtId);
        etanol = (EditText)findViewById(R.id.etanolId);
        diesel = (EditText)findViewById(R.id.diesel);
        dieselS10 = (EditText)findViewById(R.id.dieselS10Id);
        adicionar = (Button)findViewById(R.id.btAdicionarPrecos);

        carregarDadosMApsActivity();
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<Combustivel> dados= adicionarPrecos();


                Intent returnIntent = new Intent(CadastrarPrecosCombustivel.this,CadastroPostoActivity.class);
               // Seta num campo estático da ActivityB
                returnIntent.putParcelableArrayListExtra("dados", (ArrayList<? extends Parcelable>) dados);



                returnIntent.putExtra("latidude",  latitude);
                returnIntent.putExtra("longitude",  longitude);



                startActivityForResult(returnIntent,1);

                finish();


            }
        });
    }


    public ArrayList<Combustivel> adicionarPrecos(){

        ArrayList<Combustivel> combustivels = new ArrayList<>();
        Combustivel gasolina = new Combustivel("Gasolina",this.gasolina.getText().toString());
        Combustivel gasAditivada = new Combustivel("Gasolina Aditivada",gasolinaAdiv.getText().toString());
        Combustivel etanol = new Combustivel("Etanol",this.etanol.getText().toString());
        Combustivel diesel = new Combustivel("Diesel",this.diesel.getText().toString());
        Combustivel diselS10 = new Combustivel("Disel S10",this.dieselS10.getText().toString());

        combustivels.add(gasolina);
        combustivels.add(gasAditivada);
        combustivels.add(etanol);
        combustivels.add(diesel);
        combustivels.add(diselS10);

        return combustivels;
    }


    //Após digitar os preços, chamar a tela principal de cadastro
    private void cadastrarPosto(LatLng latLng) {

        Intent returnIntent = new Intent(CadastrarPrecosCombustivel.this,CadastroPostoActivity.class);

        Bundle params = new Bundle();

        // Seta num campo estático da ActivityB


        double latidude = latLng.latitude;
        double logitude = latLng.longitude;

        returnIntent.putExtra("latidude",  latidude);
        returnIntent.putExtra("longitude",  logitude);

        //  returnIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(returnIntent);

    }


    public void carregarDadosMApsActivity() {


        Intent intent = getIntent();
        if(intent.getSerializableExtra("latidude") != null) {
            latitude = (double) intent.getSerializableExtra("latidude");
            longitude = (double) intent.getSerializableExtra("longitude");
        }

    }
}
