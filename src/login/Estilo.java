package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Estilo {
    // ===== CORES BASE =====
    public static final Color FUNDO      = new Color(240, 244, 250);
    public static final Color PRIMARIA   = new Color(37, 99, 235);
    public static final Color SECUNDARIA = new Color(100, 116, 139);
    public static final Color PERIGO     = new Color(220, 38, 38);
    public static final Color TEXTO      = new Color(30, 41, 59);

    // ===== LOGIN  =====
    public static final Color LOGIN_FUNDO       = new Color(253, 242, 248); // rosa bem claro
    public static final Color LOGIN_ROSA        = new Color(236, 72, 153);  // rosa da faixa
    public static final Color LOGIN_ROSA_ESCURO = new Color(190, 24, 93);   // botão Entrar
    public static final Color LOGIN_LILAS       = new Color(147, 51, 234);  // botão Cadastrar
    public static final Color LOGIN_BORDA       = new Color(244, 114, 182); // borda dos campos

    // ===== CADASTRO  =====
    public static final Color CAD_FAIXA    = new Color(253, 224, 71);  // amarelo
    public static final Color CAD_TITULO   = new Color(154, 52, 18);   // marrom-laranja
    public static final Color CAD_LARANJA  = new Color(249, 115, 22);  // botão Cadastrar
    public static final Color CAD_TURQUESA = new Color(13, 148, 136);  // botão Cancelar

    // ===== TONS DOS AVISOS (tons diferente) =====
    public static final Color[] TONS_ERRO = {
        new Color(127, 29, 29),   // 0 vinho
        new Color(185, 28, 28),   // 1 vermelho escuro
        new Color(220, 38, 38),   // 2 vermelho vivo
        new Color(190, 18, 60),   // 3 carmim
        new Color(159, 18, 57),   // 4 framboesa
        new Color(200, 50, 20)    // 5 vermelho tijolo
    };
    public static final Color[] TONS_SUCESSO = {
        new Color(20, 83, 45),    // 0 verde floresta
        new Color(22, 101, 52),   // 1 verde escuro
        new Color(21, 128, 61),   // 2 verde médio
        new Color(4, 120, 87),    // 3 verde esmeralda
        new Color(5, 150, 105)    // 4 verde claro
    };

    public static final Font FONTE   = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font NEGRITO = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font TITULO  = new Font("Segoe UI", Font.BOLD, 22);

    public static void aplicarTema() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Não foi possível aplicar o tema: " + e.getMessage());
        }
    }

    // ===== AVISOS COLORIDOS =====
    public static void erro(Component pai, String msg, int tom) {
        JOptionPane.showMessageDialog(pai,
                mensagem(msg, TONS_ERRO[tom % TONS_ERRO.length]),
                "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void sucesso(Component pai, String msg, int tom) {
        JOptionPane.showMessageDialog(pai,
                mensagem(msg, TONS_SUCESSO[tom % TONS_SUCESSO.length]),
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private static JLabel mensagem(String texto, Color cor) {
        JLabel l = new JLabel(texto);
        l.setFont(NEGRITO);
        l.setForeground(cor);
        return l;
    }

    // ===== COMPONENTES =====
    public static JPanel cabecalho(String texto, Color fundo, Color corTexto) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(fundo);
        JLabel l = new JLabel(texto, SwingConstants.CENTER);
        l.setFont(TITULO);
        l.setForeground(corTexto);
        l.setBorder(new EmptyBorder(22, 10, 18, 10));
        p.add(l, BorderLayout.CENTER);
        return p;
    }

    public static JLabel titulo(String texto, Color cor) {
        JLabel l = new JLabel(texto, SwingConstants.CENTER);
        l.setFont(TITULO);
        l.setForeground(cor);
        l.setBorder(new EmptyBorder(20, 10, 5, 10));
        return l;
    }

    public static JLabel titulo(String texto) {
        return titulo(texto, PRIMARIA);
    }

    public static JLabel rotulo(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(NEGRITO);
        l.setForeground(TEXTO);
        return l;
    }

    public static void campo(JComponent c, Color borda) {
        c.setFont(FONTE);
        c.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borda),
                new EmptyBorder(6, 8, 6, 8)));
    }

    public static void campo(JComponent c) {
        campo(c, new Color(203, 213, 225));
    }

    public static void botao(JButton b, Color cor) {
        b.setFont(NEGRITO);
        b.setBackground(cor);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(10, 16, 10, 16));
    }

    public static JPanel formulario(int linhas, Color fundo) {
        JPanel p = new JPanel(new GridLayout(linhas, 2, 12, 14));
        p.setBackground(fundo);
        p.setBorder(new EmptyBorder(15, 30, 15, 30));
        return p;
    }

    public static JPanel formulario(int linhas) {
        return formulario(linhas, FUNDO);
    }

    public static JPanel rodape(Color fundo, JButton... botoes) {
        JPanel p = new JPanel(new GridLayout(1, botoes.length, 12, 0));
        p.setBackground(fundo);
        p.setBorder(new EmptyBorder(15, 30, 25, 30));
        for (JButton b : botoes) p.add(b);
        return p;
    }

    public static JPanel rodape(JButton... botoes) {
        return rodape(FUNDO, botoes);
    }
}