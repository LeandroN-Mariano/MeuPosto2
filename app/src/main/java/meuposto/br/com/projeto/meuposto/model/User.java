package meuposto.br.com.projeto.meuposto.model;

/**
 * Created by leandro on 29/01/18.
 */

public class User  {
    private int id;
    private String email;
    private String nome;
    private String password;
    private String veiculo;
    private String combustivel;

    private boolean sincronizado;

    public User() {
        this.sincronizado = false;
    }

    public User(String email, String nome, String password,String veiculo,String combustivel) {
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.veiculo = veiculo;
        this.combustivel = combustivel;
        this.sincronizado = false;
    }

    public User(int id, String email, String nome, String password, String veiculo,String combustivel) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.veiculo = veiculo;
        this.combustivel = combustivel;
        this.sincronizado = false;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(boolean sincronizado) {
        this.sincronizado = sincronizado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }
}