package meuposto.br.com.projeto.meuposto.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import meuposto.br.com.projeto.meuposto.Control.DAOException;
import meuposto.br.com.projeto.meuposto.R;
import meuposto.br.com.projeto.meuposto.config.ConfiguracaoFireBase;
import meuposto.br.com.projeto.meuposto.dao.PostoDAO;
import meuposto.br.com.projeto.meuposto.model.Posto;
import meuposto.br.com.projeto.meuposto.util.Util;

public class ListaPostosActivity extends AppCompatActivity {

    PostoDAO postoDAO;
    Context context;
    ListView lisViewPosto;

    EditText nomePostEdit;
    List<Posto> listaPosto = new ArrayList<Posto>();
    ArrayAdapter<Posto> postoArrayAdapter;

    DatabaseReference referenciaFirebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_postos);

        lisViewPosto = (ListView) findViewById(R.id.listaPostosId);
        nomePostEdit = (EditText) findViewById(R.id.nomePostoEdit);
        ListaPostos(nomePostEdit.getText().toString());
        Intent i = getIntent();
        String nome = i.getStringExtra("Posto");
        Util.addNomeNaLista(nome);

        postoDAO = new PostoDAO(context);





    }

    @Override
    protected void onResume() {
        super.onResume();
        ListaPostos("");
    }

    public List<Posto> todosPostos() {
        List<Posto> postos = null;

        try {
            postos = postoDAO.selectAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return postos;
    }


    public void  eventoEdit(){

        nomePostEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String pala = nomePostEdit.getText().toString().trim();

            }
        });
    }
    public void ListaPostos(String palavra){

        Query query;

        if(palavra.equals("")) {
            query = referenciaFirebase.child("postos").orderByChild("nome");
        }else{
            query = referenciaFirebase.child("postos").orderByChild("nome").startAt(palavra).endAt(palavra+"uft8");
        }


        listaPosto.clear();

        query.addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot objSnapshot: dataSnapshot.getChildren()){
                    Posto posto = objSnapshot.getValue(Posto.class);
                    listaPosto.add(posto);


                }
                postoArrayAdapter = new ArrayAdapter<Posto>(ListaPostosActivity.this,android.R.layout.simple_list_item_1);
                lisViewPosto.setAdapter(postoArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
