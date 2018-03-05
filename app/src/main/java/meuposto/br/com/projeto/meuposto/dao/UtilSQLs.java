package meuposto.br.com.projeto.meuposto.dao;

/**
 * Created by leandro on 01/02/18.
 */

public class UtilSQLs {
    public static final String TABLE_USER = "create table user(" +
            "id integer primary key AUTOINCREMENT ," +
            "nome varchar not null," +
            "password varchar not null,"+
            "sincronizado boolean not null,"+
            "email varchar not null unique,"+
            "veiculo varchar not null, " +
            "combustivel varchar not null "+
            ")";

    public static final String TABLE_POSTO = "create table posto(" +
            "id integer primary key AUTOINCREMENT ," +
            "nome varchar not null," +
            "endereco varchar not null,"+
            "bandeira varchar not null,"+
            "id_combustivel int not null, "+
            "foreign key(id_combustivel) references combustivel(id)" +
            ")";

    public static final String TABLE_COMBUSTIVEL = "create table combustivel(" +
            "id integer primary key AUTOINCREMENT ," +
            "tipo varchar not null," +
            "preco double not null,"+
            "sincronizado boolean "+
            ")";
}
