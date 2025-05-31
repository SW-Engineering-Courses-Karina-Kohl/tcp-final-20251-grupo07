package app.telas;

import javax.swing.*;
import java.awt.*;

public class TelaJogo {

    public static JPanel criar(CardLayout cardLayout, JPanel painelCartoes, int numvidas) {
        // Painel principal da tela de jogo (mesma cor do painelRegras)
        JPanel painelJogo = new JPanel();
        painelJogo.setBackground(new Color(247, 231, 166)); // amarelo claro igual tela de regras
        painelJogo.setLayout(new GridBagLayout()); // centraliza painel interno

        // Painel interno para conteúdo do jogo (caixa central, mesma dimensão que a tela de regras)
        JPanel caixaJogo = new JPanel();
        caixaJogo.setPreferredSize(new Dimension(800, 500));
        caixaJogo.setBackground(new Color(218, 201, 164));
        caixaJogo.setBorder(BorderFactory.createDashedBorder(Color.GRAY));
        caixaJogo.setLayout(new BorderLayout(10, 10));

        // Topo: título + botão voltar para regras
        JPanel topo = new JPanel(new BorderLayout());
        topo.setOpaque(false);
        JLabel titulo = new JLabel("JOGO DA FORCA");
        titulo.setFont(new Font("Departure Mono", Font.BOLD | Font.ITALIC, 20));
        topo.add(titulo, BorderLayout.WEST);

        JButton botaoRegras = new JButton("Regras");
        botaoRegras.setFocusable(false);
        botaoRegras.setMargin(new Insets(2, 8, 2, 8));
        botaoRegras.setBackground(Color.WHITE);
        botaoRegras.setFont(new Font("Departure Mono", Font.BOLD, 12));

        botaoRegras.addActionListener(e -> cardLayout.show(painelCartoes, "REGRAS"));
        topo.add(botaoRegras, BorderLayout.EAST);

        // Painel central do jogo (onde ficará a imagem e o restante do jogo)
        JPanel painelCentral = new JPanel();
        painelCentral.setOpaque(false);
        painelCentral.setLayout(null); // Para posicionamento absoluto da imagem e botões do jogo

        // Exibe a imagem de acordo com o número de vidas
        ImageIcon imagemOriginal;
        switch (numvidas) {
            case 7 -> imagemOriginal = new ImageIcon("imagens/forca.png");
            case 6 -> imagemOriginal = new ImageIcon("imagens/boneco_cabeca.png");
            case 5 -> imagemOriginal = new ImageIcon("imagens/boneco_torso.png");
            case 4 -> imagemOriginal = new ImageIcon("imagens/boneco_braco1.png");
            case 3 -> imagemOriginal = new ImageIcon("imagens/boneco_braco2.png");
            case 2 -> imagemOriginal = new ImageIcon("imagens/boneco_perna1.png");
            case 1 -> imagemOriginal = new ImageIcon("imagens/boneco_completo.png");
            default -> imagemOriginal = new ImageIcon("imagens/boneco_completo.png");
        }

        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        JLabel labelImagem = new JLabel(new ImageIcon(imagemRedimensionada));
        labelImagem.setBounds(50, 130, 200, 150);
        painelCentral.add(labelImagem);

        // Aqui você pode adicionar os botões com letras e a palavra oculta, por exemplo:
        // Exemplo de botão letra
        JButton btnA = new JButton("A");
        btnA.setBounds(300, 100, 50, 50);
        painelCentral.add(btnA);

        // Pode adicionar mais componentes aqui...

        // Monta a caixa do jogo
        caixaJogo.add(topo, BorderLayout.NORTH);
        caixaJogo.add(painelCentral, BorderLayout.CENTER);

        // Adiciona a caixa ao painel principal da tela de jogo
        painelJogo.add(caixaJogo);

        return painelJogo;
    }
}
