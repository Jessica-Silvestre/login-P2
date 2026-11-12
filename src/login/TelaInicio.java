package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TelaInicio extends JFrame {
    private final JPanel tela;

    //Atributo para validar a Exclusão
    private boolean exclusaoValida;

    public TelaInicio() {
        setResizable(false);
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 560, 320);
        setLocationRelativeTo(null);

        Color fundo = new Color(240, 253, 250); 
        tela = new JPanel(new BorderLayout());
        tela.setBackground(fundo);
        setContentPane(tela);

        tela.add(Estilo.cabecalho("Menu", Estilo.CAD_TURQUESA, Color.WHITE),
                BorderLayout.NORTH);

        //Concateno o Label com o atributo
        JLabel lblUsuario = new JLabel("Bem-vindo(a)  " + Usuario.nomeUsuario,
                SwingConstants.CENTER);
        lblUsuario.setFont(Estilo.TITULO);
        lblUsuario.setForeground(Estilo.TEXTO);
        tela.add(lblUsuario, BorderLayout.CENTER);

        JButton btnExcluir = new JButton("Excluir");
        Estilo.botao(btnExcluir, Estilo.PERIGO);

        JButton btnAlterar = new JButton("Alterar Dados");
        Estilo.botao(btnAlterar, Estilo.PRIMARIA);

        JButton btnVoltar = new JButton("Voltar");
        Estilo.botao(btnVoltar, Estilo.SECUNDARIA);

        tela.add(Estilo.rodape(fundo, btnExcluir, btnAlterar, btnVoltar),
                BorderLayout.SOUTH);

        btnVoltar.addActionListener((ActionEvent e) -> {
            try {
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.setVisible(true);
                dispose();
            } catch (Exception ec) {
                System.out.println("Erro ao acessar a tela de login "
                        + ec.getMessage());
            }
        });

        btnAlterar.addActionListener((ActionEvent e) -> {
            try {
                TelaAlteracao telaAlterar = new TelaAlteracao();
                telaAlterar.setVisible(true);
                dispose();
            } catch (Exception ec) {
                System.out.println("Erro ao acessar a tela de alteração "
                        + ec.getMessage());
            }
        });

        btnExcluir.addActionListener((ActionEvent e) -> {
            try {
                // recebe as opções
                Object[] options = {"Sim", "Não"};
                int opcao = JOptionPane.showOptionDialog(null,
                        Usuario.nomeUsuario
                        + ", deseja se excluir?", "Atenção",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,
                        options, options[0]);

                if (opcao == 0) {
                    //Excluir o usuario
                    Usuario usu = new Usuario();

                    exclusaoValida
                            = usu.excluiUsuario(Usuario.usuarioSistema);

                    if (exclusaoValida == true) {
                        //Usuario excluído na base de dados
                        Estilo.sucesso(this, "Usuário deletado, "
                                + "voltar a tela de login.", 1);

                        //Abrir login novamente
                        TelaLogin tLogin = new TelaLogin();
                        tLogin.abreTela();
                        dispose();

                    } else {
                        Estilo.erro(this, "Problemas ao excluir o usuário", 4);
                    }
                }

            } catch (HeadlessException ec) {
                System.out.println("Erro ao excluir usuário "
                        + ec.getMessage());
            }
        });
    }

    public void abreTela() {
        TelaInicio telaInicio = new TelaInicio();
        telaInicio.setVisible(true);
    }
}