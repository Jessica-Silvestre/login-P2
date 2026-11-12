package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaCadastro extends JFrame {
    //Declaração dos atributos 
    private final JPanel tela;
    private final JTextField txtNome;
    private final JTextField txtUsuario;
    private final JPasswordField passSenha;
    private final JPasswordField passConfSenha;

    //Validações de usuário e cadastro 
    private boolean usuarioValido;
    private boolean cadastroValido;

    //String de mensagem
    private String mensagemJOption;
    private int mensagemTipo = 0;

    //Método construtor da classe
    public TelaCadastro() {
        setResizable(false);
        setTitle("Cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 520, 480);
        setLocationRelativeTo(null);

        Color fundoCad = new Color(255, 251, 235);

        tela = new JPanel(new BorderLayout());
        tela.setBackground(fundoCad);
        setContentPane(tela);

        tela.add(Estilo.cabecalho("Informar campos para cadastro",
                Estilo.CAD_FAIXA, Estilo.CAD_TITULO), BorderLayout.NORTH);

        txtNome = new JTextField();
        txtUsuario = new JTextField();
        passSenha = new JPasswordField();
        passConfSenha = new JPasswordField();
        Estilo.campo(txtNome, Estilo.CAD_LARANJA);
        Estilo.campo(txtUsuario, Estilo.CAD_LARANJA);
        Estilo.campo(passSenha, Estilo.CAD_LARANJA);
        Estilo.campo(passConfSenha, Estilo.CAD_LARANJA);

        JPanel form = Estilo.formulario(4, fundoCad);
        form.add(Estilo.rotulo("Nome:"));
        form.add(txtNome);
        form.add(Estilo.rotulo("Usuário:"));
        form.add(txtUsuario);
        form.add(Estilo.rotulo("Senha:"));
        form.add(passSenha);
        form.add(Estilo.rotulo("Confirmar senha:"));
        form.add(passConfSenha);
        tela.add(form, BorderLayout.CENTER);

        JButton btnCancelar = new JButton("Cancelar");
        Estilo.botao(btnCancelar, Estilo.CAD_TURQUESA);

        JButton btnCadastrar = new JButton("Cadastrar");
        Estilo.botao(btnCadastrar, Estilo.CAD_LARANJA);

        tela.add(Estilo.rodape(fundoCad, btnCancelar, btnCadastrar),
                BorderLayout.SOUTH);

        // botão cancelar: volta para a tela de login
        btnCancelar.addActionListener((ActionEvent e) -> {
            TelaLogin tLogin = new TelaLogin();
            tLogin.abreTela();
            dispose();
        });

        // botão cadastrar usuário na base de dados
        btnCadastrar.addActionListener((ActionEvent e) -> {
            try {
                Usuario usu = new Usuario();

                // setters dos dados de tela
                usu.setNome(txtNome.getText());
                usu.setUsuario(txtUsuario.getText());
                usu.setSenha(new String(passSenha.getPassword()));

                String confirma = new String(passConfSenha.getPassword());

                //validar preenchimento dos dados
                if ("".equals(usu.getNome())) {
                    mensagemJOption = "Campo nome do usuário precisa ser informado";
                    mensagemTipo = 0;
                } else if ("".equals(usu.getUsuario())) {
                    mensagemJOption = "Campo usuário precisa ser informado";
                    mensagemTipo = 0;
                } else if (!usu.getSenha().equals(confirma)) {
                    mensagemJOption = "Campos senha e confirmação de senha não coincidem!";
                    mensagemTipo = 0;
                } else if ("".equals(usu.getSenha())) {
                    mensagemJOption = "A senha precisa ser informada!";
                    mensagemTipo = 0;
                } else {
                    //Verifica usuário no banco
                    usuarioValido = usu.verificaUsuario(usu.getUsuario());

                    if (usuarioValido == true) {
                        mensagemJOption = "Usuário já existe na base de dados.";
                        mensagemTipo = 0;
                    } else {
                        cadastroValido = usu.cadastraUsuario(usu.getNome(),
                                usu.getUsuario(), usu.getSenha());

                        if (cadastroValido == true) {
                            mensagemJOption = "Usuário cadastrado com sucesso.";
                            mensagemTipo = 1;
                        } else {
                            mensagemJOption = "Problemas ao inserir o usuário.";
                            mensagemTipo = 0;
                        }
                    }
                }

                // mensagem (0 = erro, 1 = sucesso)
                if (mensagemTipo == 1) {
                    Estilo.sucesso(this, mensagemJOption, 2);

                    //Volta para a tela de login
                    TelaLogin tLogin = new TelaLogin();
                    tLogin.abreTela();
                    dispose();
                } else {
                    Estilo.erro(this, mensagemJOption, 3);
                }

            } catch (HeadlessException ec) {
                System.out.println("Erro no cadastro do usuário");
            }
        });
    }

    public void abreTela() {
        TelaCadastro panelCadastro = new TelaCadastro();
        panelCadastro.setVisible(true);
    }
}