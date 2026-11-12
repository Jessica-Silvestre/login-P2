package login;

import java.sql.SQLException; //Tratar as exceções no banco de dados

public class Usuario {
    //Criação dos atributos
    private String usuario;
    private String nome;
    private String senha;

    //Atributos que armazenarão o resultado no banco de dados
    private boolean resultUsuario;
    private boolean resultCadastro;
    private boolean resultAlteracao;
    private boolean resultExclusao;

   
    static String nomeUsuario;
    static String usuarioSistema;

    //Criação dos getters e setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // verificação da autenticidade do usuario
    public boolean verificaUsuario(String usuario, String senha) {
        // conexão com o banco de dados
        Conexao banco = new Conexao();

        try {
            //Abrir a conexão com o banco de dados
            banco.abrirConexao();

            // retorno
            banco.stmt = banco.con.createStatement();

            //consulta no banco de dados
            banco.resultset =
                    banco.stmt.executeQuery("SELECT * FROM usuario "
                            + "WHERE usuario = '" + usuario + "'"
                            + " AND senha = md5('" + senha + "')");

            //Verificando se existe retorno de dados no banco
            if (banco.resultset.next()) {
                //Caso tenha
                resultUsuario = true;

                //Setters em Usuario e Nome
                setUsuario(banco.resultset.getString(1));
                setNome(banco.resultset.getString(2));

                //  atributos estáticos
                nomeUsuario = getNome();
                usuarioSistema = getUsuario();
            } else {
                //Caso não tenha
                resultUsuario = false;
            }

            banco.fecharConexao(); // fecha conexão com o banco de dados

        } catch (SQLException ec) {
            System.out.println("Erro ao consultar usuário " + ec.getMessage());
        }

        return resultUsuario;
    }

    //Método de verificação do usuário, 
    public boolean verificaUsuario(String usuario) {
       
        Conexao banco = new Conexao();

        try {
            banco.abrirConexao();

            banco.stmt = banco.con.createStatement();

            banco.resultset =
                    banco.stmt.executeQuery("SELECT * FROM usuario "
                            + "WHERE usuario = '" + usuario + "'");

            if (banco.resultset.next()) {
                resultUsuario = true;
            } else {
                resultUsuario = false;
            }
            banco.fecharConexao();

        } catch (SQLException ec) {
            System.out.println("Erro ao consultar usuário " + ec.getMessage());
        }

        return resultUsuario;
    }

    // cadastro de usuário em sistema
    public boolean cadastraUsuario(String nome, String usuario, String senha) {
        Conexao banco = new Conexao();
        try {
            banco.abrirConexao();
            banco.stmt = banco.con.createStatement();
            banco.stmt.execute("INSERT INTO usuario (nome, usuario, senha)"
                    + " VALUES ('" + nome + "','" + usuario + "', md5('"
                    + senha + "'))");
            resultCadastro = true;

            banco.fecharConexao();

        } catch (SQLException ec) {
            System.out.println("Erro ao inserir o usuário " + ec.getMessage());
            resultCadastro = false;
        }

        return resultCadastro;
    }

    // alteração dos dados no sistema
    public boolean alteraUsuario(String nome, String usuario, String senha) {
        Conexao banco = new Conexao();
        try {
            banco.abrirConexao();
            banco.stmt = banco.con.createStatement();
            banco.stmt.execute("UPDATE usuario SET nome = '" + nome
                    + "', senha = md5('" + senha
                    + "') WHERE usuario = '" + usuario + "'");
            resultAlteracao = true;
        } catch (SQLException ec) {
            System.out.println("Erro ao atualizar usuário " + ec.getMessage());
            resultAlteracao = false;
        }

        banco.fecharConexao();

        return resultAlteracao;
    }

    //exclusão do Usuário do sistema
    public boolean excluiUsuario(String usuario) {
        Conexao banco = new Conexao();
        try {
             banco.abrirConexao();
            banco.stmt = banco.con.createStatement();
            banco.stmt.execute("DELETE FROM usuario WHERE usuario = '"
                    + usuario + "'");
            resultExclusao = true;
        } catch (SQLException ec) {
            System.out.println("Erro ao excluir usuário " + ec.getMessage());
            resultExclusao = false;
        }

        banco.fecharConexao();

        return resultExclusao;
    }
}