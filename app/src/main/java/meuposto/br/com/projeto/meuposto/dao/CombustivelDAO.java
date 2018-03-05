package meuposto.br.com.projeto.meuposto.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import meuposto.br.com.projeto.meuposto.Control.DAOException;
import meuposto.br.com.projeto.meuposto.model.Combustivel;
import meuposto.br.com.projeto.meuposto.model.Posto;

/**
 * Created by leo_3 on 19/02/2018.
 */

public class CombustivelDAO extends DAO<Combustivel> {
    private Context context;
    private SQLiteDatabase db;

    public CombustivelDAO(Context context) {
        this.context = context;
    }

    @Override
    public void insert(Combustivel obj) throws DAOException {

        db = SQLfactory.getInstance(context);
System.out.println("Inserindo o combustivel");
        ContentValues values = new ContentValues();
        values.put("tipo", obj.getTipo());
        values.put("preco", obj.getPreco());

        System.out.println("Combustivel: " +  db.insert("combustivel",null,values));
    }

    @Override
    public void update(Combustivel obj) throws DAOException {

    }

    @Override
    public void delete(Integer id) throws DAOException {

    }

    @Override
    public Combustivel selectById(Integer id) throws DAOException {

        Combustivel combustivel = null;
        try {
            db = SQLfactory.getInstance(context);
            Cursor cursor = db.query("user", null, "id=?",null, null, null, null);

            if (cursor.moveToFirst()) {
                combustivel = new Combustivel();
                System.out.println("-----------");

                System.out.println(cursor.getLong(cursor.getColumnIndex("id")));
                System.out.println(cursor.getInt(cursor.getColumnIndex("id")));
                System.out.println(cursor.getLong(1));
                System.out.println("-----------");

                combustivel.setId(cursor.getInt(cursor.getColumnIndex("id")));
                combustivel.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                combustivel.setPreco(cursor.getDouble(cursor.getColumnIndex("preco")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SQLfactory.closedb();
        }
        return combustivel;
    }

    @Override
    public List<Combustivel> selectByName(String name) throws DAOException {
        return null;
    }

    @Override
    public List<Combustivel> selectAll() throws DAOException {

        Combustivel combustivel = null;
        List<Combustivel> combustivelList = new ArrayList<>();
        try {
            db = SQLfactory.getInstance(context);
            Cursor cursor = db.rawQuery("SELECT * FROM combustivel",null);
            if (cursor.moveToFirst()) {
                combustivel = new Combustivel();
                System.out.println("-----------");

                System.out.println(cursor.getLong(cursor.getColumnIndex("id")));
                System.out.println(cursor.getInt(cursor.getColumnIndex("id")));
                System.out.println(cursor.getLong(1));
                System.out.println("-----------");

                combustivel.setId(cursor.getInt(cursor.getColumnIndex("id")));
                combustivel.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                combustivel.setPreco(cursor.getDouble(cursor.getColumnIndex("preco")));
                combustivelList.add(combustivel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SQLfactory.closedb();
        }
        return combustivelList;
    }

}
