package app.UI;

import javax.swing.*;

import java.awt.*;

public class ComponentesUI {

    public static JButton botaoReiniciar(Runnable aoReiniciar) {
        JButton reiniciar = new JButton("REINICIAR");
        reiniciar.setBackground(Estilos.AZUL);
        reiniciar.setForeground(Color.BLACK);
        reiniciar.addActionListener(e -> aoReiniciar.run());
        return reiniciar;
    }

    public static JButton botaoSair() {
        JButton sair = new JButton("SAIR");
        sair.setBackground(Estilos.AMARELO);
        sair.setForeground(Color.BLACK);
        sair.addActionListener(e -> System.exit(0));
        return sair;
    }

    public static JPanel painelComReiniciarESair(Runnable aoReiniciar) {
        JPanel botoes = new JPanel();
        botoes.setOpaque(false); 

        JButton reiniciar = botaoReiniciar(aoReiniciar);
        JButton sair = botaoSair();

        botoes.add(reiniciar);
        botoes.add(Box.createHorizontalStrut(10)); 
        botoes.add(sair);

        return botoes;
    }
}
