package app.telas;

import javax.swing.*;

import app.UI.Estilos;

import java.awt.*;

public class TelaJogo {

    public static JPanel criar(CardLayout cardLayout, JPanel painelCartoes, int numvidas) {
        JPanel painelJogo = new JPanel();
        painelJogo.setBackground(Estilos.AMARELO);
        painelJogo.setLayout(new GridBagLayout()); 

        JPanel caixaJogo = new JPanel();
        caixaJogo.setPreferredSize(Estilos.TAMANHO_TELA_JOGO);
        caixaJogo.setBackground(Estilos.CINZA);
        caixaJogo.setBorder(BorderFactory.createDashedBorder(Color.GRAY));
        caixaJogo.setLayout(null); 

        // ---------- Botão Regras ----------
        JButton botaoRegras = new JButton("Regras");
        botaoRegras.setBounds(20, 20, 100, 40);
        botaoRegras.setFocusable(false);
        botaoRegras.setBackground(new Color(213, 222, 145));
        botaoRegras.addActionListener(e -> cardLayout.show(painelCartoes, "REGRAS"));
        caixaJogo.add(botaoRegras);

        // ---------- Botão Categoria ----------
        JButton botaoCategoria = new JButton("Categoria"); //tem que exibir a classe categoria
        botaoCategoria.setBounds(130, 20, 110, 40);
        botaoCategoria.setBackground(new Color(249, 179, 159));
        botaoCategoria.setFocusable(false);
        botaoCategoria.addActionListener(e -> cardLayout.show(painelCartoes, "CATEGORIA"));
        caixaJogo.add(botaoCategoria);

        // ---------- Botão Dica ----------
        JButton botaoDica = new JButton("Dica"); // tem que exibir a dica
        botaoDica.setBounds(250, 20, 100, 40);
        botaoDica.setBackground(new Color(213, 204, 224));
        botaoDica.setFocusable(false);
        botaoDica.addActionListener(e -> cardLayout.show(painelCartoes, "DICA"));
        caixaJogo.add(botaoDica);

        // ---------- Imagem das vidas ----------
        ImageIcon imagemOriginal;
        switch (numvidas) {
            case 7 -> imagemOriginal = new ImageIcon(TelaJogo.class.getResource("/imagens/forca.png"));
            case 6 -> imagemOriginal = new ImageIcon(TelaJogo.class.getResource("/imagens/boneco_cabeca.png"));
            case 5 -> imagemOriginal = new ImageIcon(TelaJogo.class.getResource("/imagens/boneco_torso.png"));
            case 4 -> imagemOriginal = new ImageIcon(TelaJogo.class.getResource("/imagens/boneco_braco1.png"));
            case 3 -> imagemOriginal = new ImageIcon(TelaJogo.class.getResource("/imagens/boneco_braco2.png"));
            case 2 -> imagemOriginal = new ImageIcon(TelaJogo.class.getResource("/imagens/boneco_perna1.png"));
            case 1, 0 -> imagemOriginal = new ImageIcon(TelaJogo.class.getResource("/imagens/boneco_completo.png"));
            default -> imagemOriginal = new ImageIcon(TelaJogo.class.getResource("/imagens/boneco_completo.png"));
        }

        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        JLabel labelImagem = new JLabel(new ImageIcon(imagemRedimensionada));
        labelImagem.setBounds(50, 200, 200, 150);
        caixaJogo.add(labelImagem);

        painelJogo.add(caixaJogo);

        return painelJogo;
    }
}
