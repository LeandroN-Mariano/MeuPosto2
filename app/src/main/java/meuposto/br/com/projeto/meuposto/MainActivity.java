package meuposto.br.com.projeto.meuposto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import meuposto.br.com.projeto.meuposto.dao.UserDao;
import meuposto.br.com.projeto.meuposto.model.User;
import meuposto.br.com.projeto.meuposto.util.Util;

public class MainActivity extends AppCompatActivity {

    private EditText login;
    private EditText senha;
    private Button botaoLogin;
    private TextView cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new UserDao(this);

        login = (EditText) findViewById(R.id.editLoginId);
        senha = (EditText)findViewById(R.id.editSenhaId);
        botaoLogin = (Button) findViewById(R.id.btLoginId);
        cadastrar = (TextView) findViewById(R.id.cadastraNovoId);

        //Vai ficar assim por enquanto pra facilitar os testes
       // login.setText("leo@mail");
        //this.senha.setText("123");

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logar(login.getText().toString(), senha.getText().toString());
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarUser();
            }
        });

    }

    protected void logar(String email, String senha) {

        User user = new UserDao(this).getUserByEmail(email.trim());

        //Limpar campos
/*
        login.setText("");
        this.senha.setText("");
*/

        //Se o usuario já existe, entra na tela principal
        if (user != null && senha.equals(user.getPassword())) {
            Util.setUsuarioLogado(user);
            System.out.println(user.getId());
            System.out.println(Util.getUsuarioLogado().getEmail());
            System.out.println(Util.getUsuarioLogado().getId());
            System.out.println(Util.getUsuarioLogado().getNome());
            startActivity(new Intent(this, PrincipalActivity.class));

        //Se nada for digitado
        } else if(email.equals("") || senha.equals("")) {
            Util.exibirmensagem(this, "Digite seu nome de usuário e senha!");
        }else{
            Util.exibirmensagem(this, "Usuário não encontrado!");

        }
    }

    //Chama o tela de cadastro
    protected  void cadastrarUser(){
        startActivity(new Intent(this,CadastroUserActivity.class));

    }
    }



