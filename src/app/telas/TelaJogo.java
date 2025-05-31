package app.telas;
import javax.swing.*;
import java.awt.*;


Public class TelaJogo {
    Public static JPanel criar(CardLayout cardLayout, JPanel painelCartoes,int numvidas) {
    JPanel panel = new JPanel();
    panel.setBackground(new Color(227, 190, 111)); // Cor de fundo inspirada em pergaminho antigo
    panel.setLayout(null); // Usamos null para posicionamento absoluto dos elementos

    // Botão para ver as regras (só como exemplo aqui)
    JButton botaoRegras = new JButton("Regras");
    botaoRegras.setBounds(1200, 20, 100, 30);
    botaoRegras.addActionListener(e -> cardLayout.show(cardPanel, "REGRAS"));
    panel.add(botaoRegras);

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
    panel.add(labelImagem);

    // Aqui você pode adicionar os botões com letras e a palavra oculta depois

    return panel;
}
}
