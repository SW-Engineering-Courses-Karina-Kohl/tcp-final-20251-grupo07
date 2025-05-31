package app.telas;

import javax.swing.*;
import java.awt.*;

public class TelaRegras {

    public static JPanel criar(CardLayout cardLayout, JPanel painelCartoes) {
        // Painel principal da tela de regras (fundo amarelo)
        JPanel painelRegras = new JPanel();
        painelRegras.setBackground(new Color(247, 231, 166)); // amarelo claro
        painelRegras.setLayout(new GridBagLayout()); // centraliza o painel de regras

        // Painel interno com regras (caixa central)
        JPanel caixaRegras = new JPanel();
        caixaRegras.setPreferredSize(new Dimension(800, 500));
        caixaRegras.setBackground(new Color(218, 201, 164));
        caixaRegras.setBorder(BorderFactory.createDashedBorder(Color.GRAY));
        caixaRegras.setLayout(new BorderLayout(10, 10));

        // Título e botão de fechar no topo
        JPanel topo = new JPanel(new BorderLayout());
        topo.setOpaque(false);
        JLabel titulo = new JLabel("COMO JOGAR");
        titulo.setFont(new Font("Departure Mono", Font.BOLD | Font.ITALIC, 20));
        topo.add(titulo, BorderLayout.WEST);

        JButton fechar = new JButton("X");
        fechar.setFocusable(false);
        fechar.setMargin(new Insets(2, 8, 2, 8));
        fechar.setBackground(Color.WHITE);
        fechar.setFont(new Font("Departure Mono", Font.BOLD, 12));

        // Ação do botão: voltar para a tela "JOGO"
        fechar.addActionListener(e -> cardLayout.show(painelCartoes, "JOGO"));
        topo.add(fechar, BorderLayout.EAST);

        // Lista de regras
        String regrasHTML = """
            <html>
            <ul>
                <li>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</li>
                <li>Morbi tincidunt lobortis dignissim. In condimentum mattis elit.</li>
                <li>Vestibulum ultricies non magna sit amet pellentesque.</li>
                <li>Nam sollicitudin, justo eu ullamcorper sagittis...</li>
            </ul>
            </html>
            """;
        JLabel textoRegras = new JLabel(regrasHTML);
        textoRegras.setFont(new Font("Departure Mono", Font.PLAIN, 14));

        // Adiciona componentes ao painel
        caixaRegras.add(topo, BorderLayout.NORTH);
        caixaRegras.add(textoRegras, BorderLayout.CENTER);
        painelRegras.add(caixaRegras);

        return painelRegras;
    }
}
