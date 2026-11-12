package login;

public class TestaConexao {
    public static void main(String[] args) throws InterruptedException {
        Conexao c = new Conexao();
        c.abrirConexao();
        Thread.sleep(4000);
        c.fecharConexao();
    }
}