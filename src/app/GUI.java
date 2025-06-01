package app;

import app.UI.Estilos;
import app.telas.*;
import javax.swing.*;
import java.awt.*;
import app.Palavra;
import java.util.List;


//botoes arredondados
//fonte Departure Mono

public class GUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel painelCartoes;
    private int numvidas;
    private Palavra palavraAtual; 

    private void reiniciarJogo() {
        numvidas = 7;
        cardLayout.show(painelCartoes, "JOGO");
    }

    public GUI() {
        setTitle("Jogo da Forca");
        setSize(Estilos.TAMANHO_TELA_PADRAO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        numvidas = 7;

        Palavra gerador = new Palavra("placeholder", 0, null);
        List<Palavra> lista = gerador.criaListaPalavras();
        palavraAtual = gerador.escolhePalavraSecreta(lista);

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

        JPanel telaJogo = TelaJogo.criar(cardLayout, painelCartoes, numvidas);
        JPanel telaRegras = TelaRegras.criar(cardLayout, painelCartoes);
        JPanel telaPerdeu = TelaPerdeu.criar(cardLayout, painelCartoes, palavraAtual.getPalavra().toUpperCase(), this::reiniciarJogo);
        JPanel telaGanhou = TelaGanhou.criar(cardLayout, painelCartoes, palavraAtual.getPalavra().toUpperCase(), this::reiniciarJogo);

        painelCartoes.add(telaJogo, "JOGO");
        painelCartoes.add(telaRegras, "REGRAS");
        painelCartoes.add(telaPerdeu, "PERDEU");
        painelCartoes.add(telaGanhou, "GANHOU");

        painelFundo.add(painelCartoes);

        // Botões de teste temporários
        JPanel botoesTeste = new JPanel();
        botoesTeste.setOpaque(false);
        JButton btnVitoria = new JButton("Ver Vitória");
        btnVitoria.addActionListener(e -> cardLayout.show(painelCartoes, "GANHOU"));
        JButton btnDerrota = new JButton("Ver Derrota");
        btnDerrota.addActionListener(e -> cardLayout.show(painelCartoes, "PERDEU"));
        botoesTeste.add(btnVitoria);
        botoesTeste.add(btnDerrota);

        painelFundo.add(botoesTeste, new GridBagConstraints()); 

        cardLayout.show(painelCartoes, "JOGO");

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}
