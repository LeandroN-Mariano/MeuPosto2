package meuposto.br.com.projeto.meuposto.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by leo_3 on 13/03/2018.
 */

public final class ConfiguracaoFireBase {

    private static DatabaseReference referenciaFirebase;
    private static FirebaseAuth autenticacao;

    public static DatabaseReference getFirebase(){

        if(referenciaFirebase == null) {
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return referenciaFirebase;
    }

    public static FirebaseAuth getFirebaseAutentica(){


        if(autenticacao == null){
            autenticacao = FirebaseAuth.getInstance();
        }

        return autenticacao;
    }

}
