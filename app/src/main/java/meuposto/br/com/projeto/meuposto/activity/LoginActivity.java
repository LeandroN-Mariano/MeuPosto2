package meuposto.br.com.projeto.meuposto.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DatabaseReference;

import meuposto.br.com.projeto.meuposto.R;
import meuposto.br.com.projeto.meuposto.config.ConfiguracaoFireBase;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private TextView novocadastro;
    private View mLoginFormView;

    //Variaveis do FireBase
    private FirebaseAuth firebaseAuth;


    private DatabaseReference referenciaFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.



        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);





        /*
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){



            startActivity(new Intent(this, PrincipalActivity.class));
            Log.i("Status usuario", "Usuario logado!!" + "Id: "+firebaseAuth.getUid());
        }else{

            Log.i("Status usuario", "Nao logado!!");
        }
*/
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailView.getText().toString();
                String senha = mPasswordView.getText().toString();

                logar(email, senha);
            }
        });

        novocadastro = (TextView) findViewById(R.id.cadastroNovoIdUser);
        novocadastro.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
    cadastrarUser();
            }
        });


    }

public  void verificarUsuarioLogado(){

    firebaseAuth = ConfiguracaoFireBase.getFirebaseAutentica();
    if(firebaseAuth.getCurrentUser() != null){
        abrirTelaPrincipal();
    }
}

public void abrirTelaPrincipal(){
    Intent MainIntent = new Intent(LoginActivity.this, PrincipalActivity.class);
    startActivity(MainIntent);
}

    private  void logar(String email, String senha){

        firebaseAuth = FirebaseAuth.getInstance();




        if(!(email.isEmpty() || senha.isEmpty())) {
            firebaseAuth.signInWithEmailAndPassword(email, senha).
                    addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                finish();
                               abrirTelaPrincipal();
                         //       Toast.makeText(LoginActivity.this, "Sucesso ao Logar usuario!!", Toast.LENGTH_LONG).show();
                            } else {

                                String erroExcecao = "";
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    erroExcecao = "Senha inválida!";
                                } catch (FirebaseAuthInvalidUserException e) {
                                    erroExcecao = "Usuário não cadastrado!";
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    erroExcecao = "Erro no login!!";
                                }

                                Log.i("Create user: ", "Erro ao Logar usuario!!");
                                Toast.makeText(LoginActivity.this, "Erro: " + erroExcecao, Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }else{
            Toast.makeText(LoginActivity.this, "Preenhca todos os campos!", Toast.LENGTH_LONG).show();
        }
    }

    //Chama o tela de cadastro
    protected  void cadastrarUser(){
        startActivity(new Intent(this,CadastroUserActivity.class));

    }
    private void logOut(){

        firebaseAuth = FirebaseAuth.getInstance();

       firebaseAuth.signOut();
    }


}

