package app;

import app.telas.*;
import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel painelCartoes;
    private int numvidas;

    public GUI() {
        setTitle("Jogo da Forca");
        setSize(1080, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        numvidas = 7; 

        // Painel amarelo (fundo)
        JPanel painelFundo = new JPanel();
        painelFundo.setBackground(new Color(247, 231, 166));
        painelFundo.setLayout(new GridBagLayout()); // centraliza o painel de conteúdo
        add(painelFundo);

        // Painel cinza menor com CardLayout para alternar telas
        cardLayout = new CardLayout();
        painelCartoes = new JPanel(cardLayout);
        painelCartoes.setPreferredSize(new Dimension(800, 500));
        painelCartoes.setBackground(new Color(218, 201, 164));

        // Criar as telas e adicioná-las ao painel de cartões
        JPanel telaJogo = TelaJogo.criar(cardLayout, painelCartoes, numvidas);
        JPanel telaRegras = TelaRegras.criar(cardLayout, painelCartoes);
        JPanel telaPerdeu = new JPanel(); 
        JPanel telaGanhou = new JPanel(); 

        painelCartoes.add(telaJogo, "JOGO");
        painelCartoes.add(telaRegras, "REGRAS");
        painelCartoes.add(telaPerdeu, "PERDEU");
        painelCartoes.add(telaGanhou, "GANHOU");

        // Adiciona o painel com as telas dentro do painel amarelo
        painelFundo.add(painelCartoes);

        // Exibe direto a tela do jogo ao abrir o programa
        cardLayout.show(painelCartoes, "JOGO");

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}
