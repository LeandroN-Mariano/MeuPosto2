package meuposto.br.com.projeto.meuposto;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import meuposto.br.com.projeto.meuposto.Control.DAOException;
import meuposto.br.com.projeto.meuposto.dao.UserDao;
import meuposto.br.com.projeto.meuposto.model.User;
import meuposto.br.com.projeto.meuposto.util.Util;

public class CadastroUserActivity extends Activity {

    private EditText nome;
    private EditText senha;
    private EditText email;
    private EditText veiculo;
    private TextView combustivel;
    private Button btCadastrar;
    private AlertDialog alerta;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = (EditText) findViewById(R.id.txtNomeID);
        senha = (EditText) findViewById(R.id.txtSenhadId);
        email = (EditText) findViewById(R.id.txtemailId);
        veiculo = (EditText) findViewById(R.id.txtModVeiculoId);
        combustivel = (TextView) findViewById(R.id.combustivelID);
        btCadastrar = (Button) findViewById(R.id.btCadastrarID);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cadastrar();
                } catch (DAOException e) {
                    e.printStackTrace();
                }
            }
        });

        combustivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    listaBandeiras();
                    Toast.makeText(CadastroUserActivity.this,"Clicou", Toast.LENGTH_SHORT).show();

                } catch (DAOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Cadastro do usuario .

    protected void cadastrar() throws DAOException {

        if (nome.getText().toString().equals("") || email.getText().toString().equals("")
                || senha.getText().toString().equals("") || veiculo.getText().toString().equals("") || combustivel.getText().toString().equals("")) {
            Util.exibirmensagem(this, "Digite os dados do usuário!");
        } else {
            User user = new User();
            user.setEmail(email.getText().toString());
            user.setNome(nome.getText().toString());
            user.setPassword(senha.getText().toString());
            user.setVeiculo(veiculo.getText().toString());
            user.setCombustivel(combustivel.getText().toString());

            user.setSincronizado(true);


            UserDao dao = new UserDao(this);
            dao.insert(user);
            Util.exibirmensagem(this, "Dados salvos com sucesso!");

            //Limpar campos
            nome.setText("");
            email.setText("");
            senha.setText("");
            veiculo.setText("");
            combustivel.setText("");
        }
    }

    private void listaBandeiras() throws DAOException {


        //Lista para bandeiras
        ArrayList<String> combustiveis = new ArrayList<String>();
        combustiveis.add("Gasolina");
        combustiveis.add("Gas. Aditivada");
        combustiveis.add("Etanol");
        combustiveis.add("Diesel");
        combustiveis.add("Diesel S10");

        //adapter utilizando um layout customizado (TextView)
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.alerta2, combustiveis);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Escolha o tipo de combustível");
        //define o diálogo como uma lista, passa o adapter.
        builder.setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(CadastroUserActivity.this, "posição selecionada= " + arg1, Toast.LENGTH_SHORT).show();

                switch (arg1) {
                    case 0:
                        combustivel.setText("Gasolina");
                        break;
                    case 1:
                        combustivel.setText("Gas. Aditivada");
                        break;
                    case 2:
                        combustivel.setText("Etanol");
                        break;
                    case 3:
                        combustivel.setText("Diesel");
                        break;
                    case 4:
                        combustivel.setText("Diesel S10");
                        break;
                }
                alerta.dismiss();
            }
        });
        alerta = builder.create();
        alerta.show();
    }
}
