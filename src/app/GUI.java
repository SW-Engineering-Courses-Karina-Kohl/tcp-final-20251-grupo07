package app;
import app.telas.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel painelCartoes;
    private int numvidas;

    public GUI() {
        setTitle("Jogo da Forca");
        setSize(1080, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        numvidas = 0;

        // Painel fundo amarelo (sempre fixo)
        JPanel painelFundo = new JPanel();
        painelFundo.setBackground(new Color(247, 231, 166));
        painelFundo.setLayout(new GridBagLayout()); // Centraliza o painel cinza
        add(painelFundo); // adiciona no JFrame diretamente

        // Painel cinza com CardLayout (menor, sobreposto)
        cardLayout = new CardLayout();
        painelCartoes = new JPanel(cardLayout);
        painelCartoes.setPreferredSize(new Dimension(800, 500)); // menor que o painel amarelo
        painelCartoes.setBackground(new Color(218, 201, 164));

        // Telas reais (JPanel com conteúdo) para cada seção
        JPanel telaJogo = TelaJogo.criar(cardLayout, painelCartoes,numvidas); // Você pode criar esse método depois
        JPanel telaRegras = TelaRegras.criar(cardLayout, painelCartoes);
        JPanel telaPerdeu = new JPanel();
        JPanel telaGanhou = new JPanel();

        // Adiciona cada tela ao painel de cartões com sua respectiva "chave"
        painelCartoes.add(telaJogo, "JOGO");
        painelCartoes.add(telaRegras, "REGRAS");
        painelCartoes.add(telaPerdeu, "PERDEU");
        painelCartoes.add(telaGanhou, "GANHOU");

        // Adiciona o painel cinza centralizado dentro do painel amarelo
        painelFundo.add(painelCartoes); // GridBag centraliza automaticamente

        // Exibe a tela inicial
        cardLayout.show(painelCartoes, "JOGO");

        setVisible(true);
    }

    private JPanel criarTelaJogo() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new FlowLayout());

        JButton botao_regras = new JButton("Ver Regras");
        botao_regras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(painelCartoes, "REGRAS");
            }
        });

        panel.add(new JLabel("Bem-vindo ao Jogo da Forca!"));
        panel.add(botao_regras);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}
