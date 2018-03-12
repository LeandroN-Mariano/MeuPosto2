package meuposto.br.com.projeto.meuposto;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    //Variaveis do FireBase
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){

            Log.i("Status usuario", "Usuario logado!!");
        }else{

            Log.i("Status usuario", "Nao logado!!");
        }

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailView.getText().toString();
                String senha = mPasswordView.getText().toString();

                logar("leandro@gmail.com", "leandro123");
            }
        });


    }




    private  void logar(String email, String senha){

        firebaseAuth = FirebaseAuth.getInstance();




        if(!(email.isEmpty() || senha.isEmpty())) {
            firebaseAuth.signInWithEmailAndPassword(email, senha).
                    addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                Intent MainIntent = new Intent(LoginActivity.this, PrincipalActivity.class);
                                startActivity(MainIntent);
                                Toast.makeText(LoginActivity.this, "Sucesso ao Logar usuario!!", Toast.LENGTH_LONG).show();
                            } else {
                                Log.i("Create user: ", "Erro ao Logar usuario!!");
                                Toast.makeText(LoginActivity.this, "Erro ao Logar usuario!!", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }else{
            Toast.makeText(LoginActivity.this, "Preenhca todos os campos!", Toast.LENGTH_LONG).show();
        }
    }


    private boolean logOut(){

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){

            Toast.makeText(LoginActivity.this, "Usuario logado!!", Toast.LENGTH_LONG).show();
            return true;
        }else{
            Toast.makeText(LoginActivity.this, "Usuario de logado!!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

}

