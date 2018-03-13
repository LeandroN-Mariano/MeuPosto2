package meuposto.br.com.projeto.meuposto.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import meuposto.br.com.projeto.meuposto.Control.DAOException;
import meuposto.br.com.projeto.meuposto.model.Posto;
import meuposto.br.com.projeto.meuposto.util.Util;

/**
 * Created by leo_3 on 12/02/2018.
 */

public class PostoDAO extends DAO<Posto> {
    private Context context;
    private SQLiteDatabase db;
    private  static List<Posto>postoList = new ArrayList<>();
    public PostoDAO(Context context) {
        this.context = context;

    }

    @Override
    public void insert(Posto obj) throws DAOException {
        db = SQLfactory.getInstance(context);
try {

    System.out.println("Valores....");
    System.out.println(Util.getUsuarioLogado());
    System.out.println(Util.getUsuarioLogado().getEmail());

    ContentValues values= new ContentValues();
    values.put("nome", obj.getNome());
    values.put("endereco", obj.getEndereco());
    values.put("bandeira", obj.getBandeira());


    System.out.println("Posto: " + db.insert("posto",null,values));


}catch (Exception e){
//            e.printStackTrace();
    throw new DAOException("Erro ao inserir Posto");
}
    }

    @Override
    public void update(Posto obj) throws DAOException {

    }

    @Override
    public void delete(Integer id) throws DAOException {

    }

    @Override
    public Posto selectById(Integer id) throws DAOException {
        return null;
    }

    @Override
    public List<Posto> selectByName(String name) throws DAOException {
        return null;
    }

    @Override
    public List<Posto> selectAll() throws DAOException {
        db.execSQL("SELECT * FROM posto");
        return null;
    }

    public void addPosto(Posto posto) {


        postoList.add(posto);
    }

    public List<Posto> getPostoList() {
        return postoList;
    }
}
