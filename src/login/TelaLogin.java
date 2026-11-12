package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaLogin extends JFrame {

    //tela Objeto JPanel (tela em si)
    private final JPanel panelTela;

    //txtUsuario Objeto JTextField (campo na tela)
    private final JTextField txtUsuario;

    //pswSenha Objeto JPasswordField (campo na tela)
    private final JPasswordField pswSenha;

    //validar se o usuario é correto
    private boolean usuarioValido;

    //Método construtor
    public TelaLogin() {
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 520, 400);
        setLocationRelativeTo(null);

        panelTela = new JPanel(new BorderLayout());
        panelTela.setBackground(Estilo.LOGIN_FUNDO);
        setContentPane(panelTela);

        //Faixa rosa com o título
        panelTela.add(Estilo.cabecalho("IDENTIFICAÇÃO",
                Estilo.LOGIN_ROSA, Color.WHITE), BorderLayout.NORTH);

        //Campos
        txtUsuario = new JTextField();
        pswSenha = new JPasswordField();
        Estilo.campo(txtUsuario, Estilo.LOGIN_BORDA);
        Estilo.campo(pswSenha, Estilo.LOGIN_BORDA);

        JPanel form = Estilo.formulario(2, Estilo.LOGIN_FUNDO);
        form.add(Estilo.rotulo("Usuário:"));
        form.add(txtUsuario);
        form.add(Estilo.rotulo("Senha:"));
        form.add(pswSenha);
        panelTela.add(form, BorderLayout.CENTER);

        //Botões
        JButton btnEntrar = new JButton("Entrar");
        Estilo.botao(btnEntrar, Estilo.LOGIN_ROSA_ESCURO);

        JButton btnCadastrar = new JButton("Cadastrar");
        Estilo.botao(btnCadastrar, Estilo.LOGIN_LILAS);

        panelTela.add(Estilo.rodape(Estilo.LOGIN_FUNDO, btnEntrar, btnCadastrar),
                BorderLayout.SOUTH);

        //Enter no teclado aciona o botão Entrar
        getRootPane().setDefaultButton(btnEntrar);

        //Ação no botão para cadastrar Usuário
        btnCadastrar.addActionListener((ActionEvent e) -> {
            TelaCadastro tCadastro = new TelaCadastro();
            tCadastro.abreTela();
            dispose();
        });

        //Ação no botão de entrar no sistema
        btnEntrar.addActionListener((ActionEvent e) -> {
            //Instancio a classe usuario
            Usuario usu = new Usuario();

            //Utilizo o setter de usuario e senha
            usu.setUsuario(txtUsuario.getText());
            usu.setSenha(new String(pswSenha.getPassword()));

            if ("".equals(txtUsuario.getText())) {
                Estilo.erro(this, "Campo usuário precisa ser informado!", 0);
                txtUsuario.grabFocus();
            } else if ("".equals(new String(pswSenha.getPassword()))) {
                Estilo.erro(this, "Campo senha precisa ser informado!", 1);
                pswSenha.grabFocus();
            } else {
                //Verifico se o usuário consta no banco de dados
                usuarioValido = usu.verificaUsuario(usu.getUsuario(),
                        usu.getSenha());

                if (usuarioValido == true) {
                    Estilo.sucesso(this, "Usuário válido em nossa base de dados", 0);

                    TelaInicio telaInicio = new TelaInicio();
                    telaInicio.abreTela();

                    dispose();
                } else {
                    Estilo.erro(this, "Usuário inválido ou inexistente", 2);

                    //Método para limpar os textos
                    limpaText();
                    txtUsuario.grabFocus();
                }
            }
        });
    }

    public void abreTela() {
        TelaLogin tela = new TelaLogin();
        tela.setVisible(true);
    }

    public void limpaText() {
        txtUsuario.setText("");
        pswSenha.setText("");
    }
}