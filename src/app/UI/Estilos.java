package app.UI;

import javax.swing.*;
import javax.swing.border.AbstractBorder;

import java.awt.*;

public class Estilos {
    public static final Color AMARELO = (new Color(247, 231, 166));
    public static final Color CINZA = (new Color(218, 201, 164));
    public static final Color VERDE = new Color(213, 222, 145);
    public static final Color AZUL = new Color(213,204,224);
    public static final Color VERMELHO = new Color(249,179,159);

    public static final Font FONTE_PADRAO = new Font("Comic Sans MS", Font.PLAIN, 18);

    public static final Dimension TAMANHO_TELA_JOGO = new Dimension(800,500);
    public static final Dimension TAMANHO_TELA_PADRAO = new Dimension(1080,600);

    public static JButton botaoArredondado(String texto) {
        JButton botao = new JButton(texto);
        botao.setFocusPainted(false);
        botao.setFont(FONTE_PADRAO);
        botao.setBackground(new Color(255, 255, 255));
        botao.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        botao.setContentAreaFilled(false);
        botao.setOpaque(true);
        botao.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        botao.setPreferredSize(new Dimension(150, 40));
        botao.setBorder(new RoundedBorder(20)); // Borda personalizada
        return botao;
    }

    // Classe interna para bordas arredondadas
    public static class RoundedBorder extends AbstractBorder {
        private final int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.GRAY);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
