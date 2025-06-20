package app;

import app.UI.Estilos;
import app.telas.*;
import java.awt.*;
import javax.swing.*;


public class GUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel painelCartoes;

    public GUI() {
        setTitle("Jogo da Forca");
        setSize(Estilos.TAMANHO_TELA_PADRAO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Painel amarelo (fundo)
        JPanel painelFundo = new JPanel();
        painelFundo.setBackground(Estilos.AMARELO);
        painelFundo.setLayout(new GridBagLayout()); // centraliza o painel de conteúdo
        add(painelFundo);

        // Painel cinza menor com CardLayout para alternar telas
        cardLayout = new CardLayout();
        painelCartoes = new JPanel(cardLayout);
        painelCartoes.setPreferredSize(Estilos.TAMANHO_TELA_JOGO);
        painelCartoes.setBackground(Estilos.CINZA);

        // Criar as telas e adicioná-las ao painel de cartões
        JPanel telaJogo = new TelaJogo(cardLayout, painelCartoes);
        JPanel telaRegras = TelaRegras.criar(cardLayout, painelCartoes);
        JPanel telaPerdeu = TelaPerdeu.criar(cardLayout, painelCartoes);
        JPanel telaGanhou = TelaGanhou.criar(cardLayout, painelCartoes);

        painelCartoes.add(telaJogo, "JOGO");
        painelCartoes.add(telaRegras, "REGRAS");
        painelCartoes.add(telaPerdeu, "PERDEU");
        painelCartoes.add(telaGanhou, "GANHOU");

        painelFundo.add(painelCartoes);

        cardLayout.show(painelCartoes, "JOGO");

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}
