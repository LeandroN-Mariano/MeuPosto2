package meuposto.br.com.projeto.meuposto.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by leandro on 01/02/18.
 */

public class SQLfactory {
    private static SQLiteDatabase db;
    private static SQLHelper helper;

    private SQLfactory() {

    }
    public static SQLiteDatabase getInstance(Context context){

        if (db == null || !db.isOpen()){
            helper = new SQLHelper(context);
            db = helper.getWritableDatabase();
        }
        return db;
    }
    public static void closedb(){
        if (db != null || db.isOpen()){
            db.close();
            if (helper != null) helper.close();
        }
    }

}
