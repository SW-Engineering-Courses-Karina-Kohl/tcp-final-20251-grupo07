package app;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(); // Contém todas as telas do jogo
    private int numvidas;

    // Construtor com todas as telas já pré determinadas
    public GUI() {
        setTitle("Jogo da Forca");
        setSize(1400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        numvidas = 0; // Somente para testar switch case, isso tem que ser implementado com a lógica do jogo

        cardPanel.setLayout(cardLayout);

        // Cria as "telas" (cada uma é um JPanel)
        JPanel telaJogo = exibir_tela_principal(numvidas);
        JPanel telaRegras = exibir_regras();
        JPanel telaPerdeu = exibir_tela_perdeu();
        JPanel telaGanhou = exibir_tela_ganhou();

        // Adiciona as "telas" ao cardPanel com nomes únicos
        cardPanel.add(telaJogo, "JOGO");
        cardPanel.add(telaRegras, "REGRAS");
        cardPanel.add(telaPerdeu, "PERDEU");
        cardPanel.add(telaGanhou, "GANHOU");

        // Adiciona o cardPanel ao JFrame
        add(cardPanel);

        // Exibe a tela inicial - tela do jogo
        cardLayout.show(cardPanel, "JOGO");
    }

    private JPanel exibir_tela_principal(int numvidas) {
    JPanel panel = new JPanel();
    panel.setBackground(new Color(227, 190, 111)); 
    panel.setLayout(null); // Precisa começar null para conseguirmos determinar posições e paramêtros especificos dos botões
    
    JButton botao_regras = new JButton("Regras");
    botao_regras.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(cardPanel, "REGRAS"); // Troca para a tela de regras
        }
    });

    ImageIcon imagemOriginal = new ImageIcon();

    switch (numvidas) {
        case 7:
            imagemOriginal = new ImageIcon("imagens/forca.png");
            break;
        case 6:
            imagemOriginal = new ImageIcon("imagens/boneco_cabeca.png");
            break;
        case 5:
            imagemOriginal = new ImageIcon("imagens/boneco_torso.png");
            break;
        case 4:
            imagemOriginal = new ImageIcon("imagens/boneco_braco1.png");
            break;
        case 3:
            imagemOriginal = new ImageIcon("imagens/boneco_braco2.png");
            break;
        case 2:
            imagemOriginal = new ImageIcon("imagens/boneco_perna1.png");
            break;
        case 1:
            imagemOriginal = new ImageIcon("imagens/boneco_completo.png");
            break;
        default:
            imagemOriginal = new ImageIcon("imagens/boneco_completo.png");
            break;
    }

    // Redimensiona a imagem (largura = 200, altura = 150)
    Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
    ImageIcon novaImagem = new ImageIcon(imagemRedimensionada);

    JLabel labelImagem = new JLabel(novaImagem);

    // Posiciona a imagem na tela (ex: 50px da esquerda, 85px do topo)
    labelImagem.setBounds(50, 130, novaImagem.getIconWidth(), novaImagem.getIconHeight()); // posição (x, y) e tamanho (w, h)

    // Adiciona ao painel
    panel.add(labelImagem);

    botao_regras.setBounds(300, 200, 100, 30);
    panel.add(botao_regras); 
    
    return panel;
}

    private JPanel exibir_regras() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.MAGENTA);

        JButton botao_voltar = new JButton("Teste prox tela - no jogo será botao voltar");
        botao_voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "PERDEU"); // Troca para a tela de configurações
            }
        });
        panel.add(botao_voltar);
        return panel;
    }

    private JPanel exibir_tela_perdeu() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.RED);

        JButton botao_novojogo = new JButton("Iniciar novo jogo");
        botao_novojogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "GANHOU"); // Troca de volta para a tela do jogo
            }
        });
        panel.add(botao_novojogo);
        return panel;
    }

     private JPanel exibir_tela_ganhou() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN);
   
        JButton botao_novojogo = new JButton("Iniciar novo Jogo");
        botao_novojogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "JOGO"); // Troca de volta para a tela do jogo
            }
        });
        panel.add(botao_novojogo);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }
}