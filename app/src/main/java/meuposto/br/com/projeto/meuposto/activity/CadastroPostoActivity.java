package meuposto.br.com.projeto.meuposto.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import meuposto.br.com.projeto.meuposto.Control.DAOException;
import meuposto.br.com.projeto.meuposto.R;
import meuposto.br.com.projeto.meuposto.config.ConfiguracaoFireBase;
import meuposto.br.com.projeto.meuposto.dao.CombustivelDAO;
import meuposto.br.com.projeto.meuposto.model.Combustivel;
import meuposto.br.com.projeto.meuposto.model.Posto;
import meuposto.br.com.projeto.meuposto.util.Util;

public class CadastroPostoActivity extends AppCompatActivity {

    private EditText nome;
    private EditText endereco;
    private TextView bandeira;
    private Button btCadastrar;
    private Context context;
    static final int ACTIVITY_2_REQUEST = 1;
    private ImageButton imgButton;
    Posto posto;
    private AlertDialog alerta;


    ArrayList<Combustivel> dados;

    DatabaseReference referenciaFirebase;
    FirebaseAuth autenticacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_posto);

        // Recupera os parâmetros passados pelo atributo estatico
         dados = getIntent().getParcelableArrayListExtra("dados");

        if(dados != null) {
            System.out.println("Array de Combustiveis==> " + dados.size());
        }else {

            System.out.println("Vazio!!");
        }
        nome = (EditText) findViewById(R.id.nomePostoId);
        endereco = (EditText) findViewById(R.id.enderecoPostoId);
        bandeira = (TextView) findViewById(R.id.bandeiraPosrtoId);

        btCadastrar = (Button) findViewById(R.id.botaoCadastrarPostoId);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           try {
                  cadastrar();
                } catch (DAOException e) {
                    Log.i("Erro cadastro: ","Erro==>"+e);
                }

            }
        });

        bandeira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaBandeiras();
            }
        });

        imgButton = (ImageButton) findViewById(R.id.imgPrecosId);

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CadastroPostoActivity.this, CadastrarPrecosCombustivel.class);

                intent.putExtra("nome", nome.getText().toString());
                intent.putExtra("endereco", endereco.getText().toString());
                intent.putExtra("bandeira", bandeira.getText().toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String nome = data.getStringExtra("nome");
        String endereco = data.getStringExtra("endereco");
        String bandeira = data.getStringExtra("bandeira");

        if (nome != null) {
            this.nome.setText(nome);
            this.endereco.setText(endereco);
            this.bandeira.setText(bandeira);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        CadastrarPrecosCombustivel t= new CadastrarPrecosCombustivel();
        t.adicionarPrecos();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        String nome = intent.getStringExtra("nome");
        String endereco = intent.getStringExtra("endereco");
        String bandeira = intent.getStringExtra("bandeira");

        if (nome != null) {
            this.nome.setText(nome);
            this.endereco.setText(endereco);
            this.bandeira.setText(bandeira);
        }
    }

    protected void cadastrar() throws DAOException {

        if (nome.getText().toString().equals("") || endereco.getText().toString().equals("")
                || bandeira.getText().toString().equals("") ) {
            Util.exibirmensagem(this, "Digite todos os dados do Posto!");
        } else {
            posto = new Posto();
            posto.setNome(nome.getText().toString());
            posto.setEndereco(endereco.getText().toString());
            posto.setBandeira(bandeira.getText().toString());





            FirebaseAuth firebaseAuth = ConfiguracaoFireBase.getFirebaseAutentica();

            DatabaseReference referenciaFirebase = ConfiguracaoFireBase.getFirebase();

//Array de combustiveis
            ArrayList<Combustivel> combustivelList = new ArrayList<>();


//Setando a lista no posto
            posto.setCombustivel(dados);


            referenciaFirebase.child("postos").push().setValue(posto);







         //   posto.salvar();



            //Limpar campos
            nome.setText("");
            endereco.setText("");
            bandeira.setText("");

            //Mostrar uma mensagem perguntando se o usuario deseja cadastrar precos do posto

        }
    }






    private void listaBandeiras() {
        //Lista para bandeiras
        ArrayList<String> bandeiras = new ArrayList<String>();
        bandeiras.add("Petrobras");
        bandeiras.add("Shell");
        bandeiras.add("Ipiranga");
        bandeiras.add("Bandeira branca");
        bandeiras.add("Outros");

        //adapter utilizando um layout customizado (TextView)
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.alerta2, bandeiras);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Escolha a bandeira:");
        //define o diálogo como uma lista, passa o adapter.
        builder.setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(CadastroPostoActivity.this, "posição selecionada= " + arg1, Toast.LENGTH_SHORT).show();

                switch (arg1){
                    case 0:
                        bandeira.setText("Petrobras");
                        break;
                    case 1:
                        bandeira.setText("Shell");
                        break;
                    case 2:
                        bandeira.setText("Ipiranga");
                        break;
                    case 3:
                        bandeira.setText("Bandeira branca");
                        break;
                    case 4:
                        bandeira.setText("Outros");
                        break;
                }
                alerta.dismiss();
            }
        });

        alerta = builder.create();
        alerta.show();
    }
}
