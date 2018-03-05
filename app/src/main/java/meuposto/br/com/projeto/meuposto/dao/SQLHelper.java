package meuposto.br.com.projeto.meuposto.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by leo_3 on 09/02/2018.
 */

public class SQLHelper extends SQLiteOpenHelper {

    public static final String NOME = "MeuPosto.sqlite";
    public static final int VERSION = 3;

    public SQLHelper(Context context) {
        super(context, NOME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(UtilSQLs.TABLE_USER);
        db.execSQL(UtilSQLs.TABLE_COMBUSTIVEL);
        db.execSQL(UtilSQLs.TABLE_POSTO);
        Log.i("BD","Criando o banco de dados!");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i("BD","Alterando o banco de dados!");
        db.execSQL("DROP TABLE IF EXISTS combustivel");
        db.execSQL("DROP TABLE IF EXISTS posto ");
        db.execSQL(UtilSQLs.TABLE_COMBUSTIVEL);
        db.execSQL(UtilSQLs.TABLE_POSTO);
    }
}
