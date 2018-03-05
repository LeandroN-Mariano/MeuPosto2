package meuposto.br.com.projeto.meuposto;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import meuposto.br.com.projeto.meuposto.Control.DAOException;
import meuposto.br.com.projeto.meuposto.dao.PostoDAO;
import meuposto.br.com.projeto.meuposto.model.Posto;
import meuposto.br.com.projeto.meuposto.util.Util;

public class ListaPostosActivity extends AppCompatActivity {

    PostoDAO postoDAO;
    Context context;
    ListView lisViewPosto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_postos);

        lisViewPosto = (ListView) findViewById(R.id.listaPostosId);

        Intent i = getIntent();
        String nome = i.getStringExtra("Posto");
        Util.addNomeNaLista(nome);

        postoDAO = new PostoDAO(context);

        postoDAO.addPosto(new Posto("Sao cristovao","Av afonso magal","Ipiranga"));

         List<Posto> postos = (List<Posto>) new Posto("Sao cristovao","Av afonso magal","Ipiranga");
       // ListView listaDePostos = (ListView) findViewById(R.id.listaPostosId);


        ArrayAdapter adapter =
                new ArrayAdapter<Posto>(this, android.R.layout.simple_list_item_1, postos);

        lisViewPosto.setAdapter(adapter);
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


}
