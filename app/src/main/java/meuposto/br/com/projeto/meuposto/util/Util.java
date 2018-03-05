package meuposto.br.com.projeto.meuposto.util;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import meuposto.br.com.projeto.meuposto.model.User;

/**
 * Created by leandro on 01/02/18.
 */

public class Util {
    public static String nome;
    private static List<String> nomes = new ArrayList<>();
    private static User usuarioLogado;
    public static Integer idUsuarioLogado;

    public static void addNomeNaLista(String s) {
        nomes.add(s);
    }

    public static List<String> getNomes() {
        return nomes;
    }

    public static boolean validarEmail(String email) {
        return !email.matches("[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+");
    }

    public static void setUsuarioLogado(User usuarioLogadoo) {
        usuarioLogado = usuarioLogadoo;
    }

    public static User getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void exibirmensagem(Context context, String mensagem) {
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();
    }
}
