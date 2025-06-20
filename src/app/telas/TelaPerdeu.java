package app.telas;

import app.Palavra;
import app.UI.ComponentesUI;
import app.UI.Estilos;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;

public class TelaPerdeu {
    private static Palavra palavraEncerrada;

    public static void setPalavraEncerrada(Palavra palavra) {
    palavraEncerrada = palavra;
    }

    public static JPanel criar(CardLayout cardLayout, JPanel painelCartoes) {
        JPanel painel = new JPanel();
        painel.setBackground(Estilos.VERMELHO);
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(80, 50, 80, 50));

        JLabel titulo = new JLabel("VOCÊ PERDEU!");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        painel.add(Box.createVerticalStrut(40));
        painel.add(titulo);

        JLabel palavraLabel = new JLabel();
        palavraLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        palavraLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        painel.add(Box.createVerticalStrut(20));

        painel.add(Box.createVerticalStrut(50)); // espaço acima do título
        painel.add(titulo);
        painel.add(Box.createVerticalStrut(30)); // espaço entre título e palavra
        painel.add(palavraLabel);
        painel.add(Box.createVerticalStrut(40)); // espaço entre palavra e botões
        painel.add(ComponentesUI.painelComReiniciarESair(() -> cardLayout.show(painelCartoes, "JOGO")));
        painel.add(Box.createVerticalGlue()); // força o conteúdo a ficar mais ao centro verticalmente

            // Atualiza o label toda vez que essa tela for exibida:
        painel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                palavraLabel.setText("A palavra era: " + palavraEncerrada.getStringPalavra().toUpperCase()); // atualiza o texto
            }
        });

        return painel;
    }
}
