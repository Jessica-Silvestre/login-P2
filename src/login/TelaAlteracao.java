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

public class TelaAlteracao extends JFrame {
    // atributos 
    private final JPanel tela;
    private final JTextField txtNome;
    private final JPasswordField passAtual;
    private final JPasswordField passSenha;
    private final JPasswordField confPassSenha;

    private boolean atualizacaoValida;

    public TelaAlteracao() {
        setResizable(false);
        setTitle("Alteração");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 540, 500);
        setLocationRelativeTo(null);

        Color fundo = new Color(250, 245, 255); 

        tela = new JPanel(new BorderLayout());
        tela.setBackground(fundo);
        setContentPane(tela);

        tela.add(Estilo.cabecalho("Informar campos para alteração",
                Estilo.LOGIN_LILAS, Color.WHITE), BorderLayout.NORTH);

        txtNome = new JTextField();
        passAtual = new JPasswordField();
        passSenha = new JPasswordField();
        confPassSenha = new JPasswordField();
        Estilo.campo(txtNome, Estilo.LOGIN_LILAS);
        Estilo.campo(passAtual, Estilo.LOGIN_LILAS);
        Estilo.campo(passSenha, Estilo.LOGIN_LILAS);
        Estilo.campo(confPassSenha, Estilo.LOGIN_LILAS);

        JPanel form = Estilo.formulario(4, fundo);
        form.add(Estilo.rotulo("Nome:"));
        form.add(txtNome);
        form.add(Estilo.rotulo("Senha Atual:"));
        form.add(passAtual);
        form.add(Estilo.rotulo("Nova Senha:"));
        form.add(passSenha);
        form.add(Estilo.rotulo("Confirmar Senha:"));
        form.add(confPassSenha);
        tela.add(form, BorderLayout.CENTER);

        JButton btnCancelar = new JButton("Cancelar");
        Estilo.botao(btnCancelar, Estilo.SECUNDARIA);

        JButton btnAlterar = new JButton("Alterar");
        Estilo.botao(btnAlterar, Estilo.LOGIN_LILAS);

        tela.add(Estilo.rodape(fundo, btnCancelar, btnAlterar),
                BorderLayout.SOUTH);

        btnCancelar.addActionListener((ActionEvent e) -> {
            TelaInicio telaIni = new TelaInicio();
            telaIni.setVisible(true);
            dispose();
        });

        //Botão de alteração
        btnAlterar.addActionListener((ActionEvent e) -> {
            try {
                Usuario usu = new Usuario();

                String senhaAtual = new String(passAtual.getPassword());
                String novaSenha = new String(passSenha.getPassword());
                String confSenha = new String(confPassSenha.getPassword());

                //Validações de alterar
                //configurar a senha e usuario
                usu.setSenha(novaSenha);
                usu.setUsuario(Usuario.usuarioSistema);

                if ("".equals(txtNome.getText())) {
                    //Nome vazio
                    Estilo.erro(this, "Campo nome precisa ser informado!", 0);
                    txtNome.grabFocus();

                } else if ("".equals(novaSenha)) {
                    //Senha vazia
                    Estilo.erro(this, "Campo senha precisa ser informado!", 1);
                    passSenha.grabFocus();

                } else if (usu.verificaUsuario(usu.getUsuario(), senhaAtual) == false) {
                    //Senha atual não confere
                    Estilo.erro(this, "Senha inválida!", 2);
                    passAtual.grabFocus();

                } else if (!novaSenha.equals(confSenha)) {
                    Estilo.erro(this, "Campos de Senha e Confirmação não são iguais!", 3);
                    passSenha.grabFocus();

                } else {
                    //Efetivação a alteração do usuario
                    atualizacaoValida
                            = usu.alteraUsuario(txtNome.getText(),
                                    usu.getUsuario(),
                                    novaSenha);

                    if (atualizacaoValida == true) {
                        Estilo.sucesso(this, "Dado(s) do usuário alterado(s), "
                                + "retornar a tela de login.", 3);

                        //Voltar para a tela de login
                        TelaLogin tLogin = new TelaLogin();
                        tLogin.abreTela();
                        dispose();
                    } else {
                        Estilo.erro(this, "Problemas ao alterar o usuário", 4);
                    }
                }

            } catch (HeadlessException ec) {
                System.out.println("Erro ao alterar usuário " + ec.getMessage());
            }
        });
    }
}