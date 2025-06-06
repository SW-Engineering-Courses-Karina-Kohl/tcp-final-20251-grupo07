package app.telas;
import app.Jogo;
import app.UI.Estilos;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class TelaJogo extends JPanel {
    private Jogo jogo;
    private JLabel labelImagem;
    private JPanel wordPanel;
    private JLabel[] letterLabels;
    private JPanel caixaJogo;
    private CardLayout cardLayout;
    private JPanel painelCartoes;



    public TelaJogo(CardLayout cardLayout, JPanel painelCartoes) {
        this.jogo = new Jogo();
        this.cardLayout = cardLayout;
        this.painelCartoes = painelCartoes;

        this.setBackground(Estilos.AMARELO);
        this.setLayout(new GridBagLayout()); // centraliza o painel interno

        caixaJogo = new JPanel();
        caixaJogo.setPreferredSize(Estilos.TAMANHO_TELA_JOGO);
        caixaJogo.setBackground(Estilos.CINZA);
        caixaJogo.setBorder(BorderFactory.createDashedBorder(Color.GRAY));
        caixaJogo.setLayout(null); // para usar posicionamento absoluto
        
        // ---------- Adiciona key listener ----------
        this.setupKeyBindings();

        // ---------- Botão Regras ----------
        JButton botaoRegras = new JButton("Regras");
        botaoRegras.setBounds(20, 20, 100, 40);
        botaoRegras.setFocusable(false);
        botaoRegras.setBackground(new Color(213, 222, 145));
        botaoRegras.addActionListener(e -> this.cardLayout.show(this.painelCartoes, "REGRAS"));
        caixaJogo.add(botaoRegras);

        // ---------- Botão Categoria ----------
        JButton botaoCategoria = new JButton("Categoria"); //tem que exibir a classe categoria
        botaoCategoria.setBounds(130, 20, 110, 40);
        botaoCategoria.setBackground(new Color(249, 179, 159));
        botaoCategoria.setFocusable(false);
        botaoCategoria.addActionListener(e -> this.cardLayout.show(this.painelCartoes, "CATEGORIA"));
        caixaJogo.add(botaoCategoria);

        // ---------- Botão Dica ----------
        JButton botaoDica = new JButton("Dica"); // tem que exibir a dica
        botaoDica.setBounds(250, 20, 100, 40);
        botaoDica.setBackground(new Color(213, 204, 224));
        botaoDica.setFocusable(false);
        botaoDica.addActionListener(e -> cardLayout.show(this.painelCartoes, "DICA"));
        caixaJogo.add(botaoDica);

        // ---------- Imagem das vidas ----------
        Image imagemRedimensionada = generate_image();
        this.labelImagem = new JLabel(new ImageIcon(imagemRedimensionada));
        this.labelImagem.setBounds(50, 130, 200, 150);
        caixaJogo.add(this.labelImagem);
        
        // ---------- Exibição Palavras ----------
        setupWordDisplay(jogo.getPalavra()); 

        this.add(caixaJogo);

        
    }

    public void setupKeyBindings() {
        InputMap im = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = this.getActionMap();

        for (char c = 'A'; c <= 'Z'; c++) {
            String key = String.valueOf(c);
            KeyStroke ks = KeyStroke.getKeyStroke(key);

            im.put(ks, key);
            am.put(key, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int letter_in = jogo.checaLetra(key.charAt(0));
                    switch (letter_in) {
                        case 0:
                            System.out.println(key + " Letter já testada.");
                            break;
                        case 1:
                            revealLetter(key.charAt(0), jogo.getPalavra());
                            if (todasLetrasAcertadas(jogo.getPalavra())) {
                                System.out.println("Todas as letras foram acertadas!");
                                cardLayout.show(painelCartoes, "GANHOU");
                            }
                            break;
                        case -1:
                            labelImagem.setIcon(new ImageIcon(generate_image()));
                            if (jogo.getNumvidas() <= 0) {
                                cardLayout.show(painelCartoes, "PERDEU");
                            }
                            break;
                    }
                    

                    System.out.println(key + " Letter pressed: " + letter_in);
                }
            });
        }
    }

    private Image generate_image(){
        ImageIcon imagemOriginal;
        switch (jogo.getNumvidas()) {
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
        return imagemRedimensionada;
    }

    private void setupWordDisplay(String palavra) {
        wordPanel = new JPanel();
        wordPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); 
        wordPanel.setOpaque(false);
        wordPanel.setBounds(250, 220, 700, 100); 

        int n = palavra.length();
        letterLabels = new JLabel[n];

        for (int i = 0; i < n; i++) {
            JPanel letterBox = new JPanel();
            letterBox.setLayout(new BoxLayout(letterBox, BoxLayout.Y_AXIS));
            letterBox.setOpaque(false);
            letterBox.setAlignmentY(Component.TOP_ALIGNMENT);
            letterBox.setMaximumSize(new Dimension(30, 50));
            letterBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

            JLabel letterLabel = new JLabel(" ");
            letterLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
            letterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            letterLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

            JLabel underline = new JLabel("___");
            underline.setFont(new Font("SansSerif", Font.PLAIN, 16));
            underline.setAlignmentX(Component.CENTER_ALIGNMENT);
            underline.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

            letterBox.add(letterLabel);
            letterBox.add(Box.createVerticalStrut(-6));  // reduz espaçamento entre a letra e o sublinhado
            letterBox.add(underline);

            wordPanel.add(letterBox);
            letterLabels[i] = letterLabel;
        }

        caixaJogo.add(wordPanel);
    }


    private void revealLetter(char guessedLetter, String palavra) {
        palavra = palavra.toUpperCase();
        for (int i = 0; i < palavra.length(); i++) {
            if (palavra.charAt(i) == guessedLetter) {
                
                letterLabels[i].setText(String.valueOf(guessedLetter));
            }
        }
    }

    public boolean todasLetrasAcertadas(String palavra) {
        palavra = palavra.toUpperCase(); // Certifica-se de que a palavra está em maiúsculas
        for (int i = 0; i < palavra.length(); i++) {
            char letraEsperada = palavra.charAt(i);
            String letraExibida = letterLabels[i].getText();
            
            // Se a letra exibida estiver vazia ou diferente da letra esperada, ainda falta acertar
            if (letraExibida.isEmpty() || letraExibida.charAt(0) != letraEsperada) {
                return false;
            }
        }
        return true; // todas as letras estão corretas
    }

    

}
