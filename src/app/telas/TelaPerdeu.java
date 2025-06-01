package app.telas;

import app.UI.ComponentesUI;
import app.UI.Estilos;
import javax.swing.*;
import java.awt.*;

public class TelaPerdeu {
    public static JPanel criar(CardLayout cardLayout, JPanel painelCartoes, String palavra, Runnable aoReiniciar) {
        JPanel painel = new JPanel();
        painel.setBackground(Estilos.VERMELHO);
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(80, 50, 80, 50));

        JLabel titulo = new JLabel("VOCÊ PERDEU!");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        painel.add(Box.createVerticalStrut(40));
        painel.add(titulo);

        JLabel palavraLabel = new JLabel("A PALAVRA ERA: " + palavra.toUpperCase());
        palavraLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        palavraLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        painel.add(Box.createVerticalStrut(20));
        painel.add(palavraLabel);

        painel.add(Box.createVerticalStrut(50)); // espaço acima do título
        painel.add(titulo);
        painel.add(Box.createVerticalStrut(30)); // espaço entre título e palavra
        painel.add(palavraLabel);
        painel.add(Box.createVerticalStrut(40)); // espaço entre palavra e botões
        painel.add(ComponentesUI.painelComReiniciarESair(() -> cardLayout.show(painelCartoes, "JOGO")));
        painel.add(Box.createVerticalGlue()); // força o conteúdo a ficar mais ao centro verticalmente

        return painel;
    }
}
