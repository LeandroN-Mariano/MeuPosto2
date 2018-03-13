package meuposto.br.com.projeto.meuposto.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import meuposto.br.com.projeto.meuposto.Control.DAOException;
import meuposto.br.com.projeto.meuposto.R;
import meuposto.br.com.projeto.meuposto.dao.CombustivelDAO;
import meuposto.br.com.projeto.meuposto.dao.PostoDAO;
import meuposto.br.com.projeto.meuposto.model.Combustivel;
import meuposto.br.com.projeto.meuposto.model.Posto;
import meuposto.br.com.projeto.meuposto.util.Util;

public class CadastroPostoActivity extends AppCompatActivity {

    private EditText nome;
    private EditText endereco;
    private TextView bandeira;
    private Button btCadastrar;
    private Context context;
    Posto posto;
    private AlertDialog alerta;

    DatabaseReference referenciaFirebase;
    FirebaseAuth autenticacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_posto);

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

            List<Combustivel> combustivels = new ArrayList<>();
            Combustivel combustivel = new Combustivel();
            Combustivel combustivel2 = new Combustivel();
            Combustivel combustivel3 = new Combustivel();
            Combustivel combustivel4 = new Combustivel();
            Combustivel combustivel5 = new Combustivel();

            CombustivelDAO combustivelDAO = new CombustivelDAO(this);
            combustivel.setTipo("Gasolina");
            combustivel.setPreco(3.90);
            combustivelDAO.insert(combustivel);

            combustivel2.setTipo("Gas. aditivada");
            combustivel2.setPreco(4.10);
            combustivelDAO.insert(combustivel);

            combustivel3.setTipo("Etanol");
            combustivel3.setPreco(3.20);
            combustivelDAO.insert(combustivel);

            combustivel4.setTipo("Diesel");
            combustivel4.setPreco(3.10);
            combustivelDAO.insert(combustivel);

            combustivel5.setTipo("Diesel S10");
            combustivel5.setPreco(3.60);
            combustivelDAO.insert(combustivel);

           // posto.setCombustivel(combustivels);





            posto.salvar();

            PostoDAO postoDAO = new PostoDAO(this);
            postoDAO.insert(posto);
            Util.exibirmensagem(this, "Dados salvos com sucesso!");

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
