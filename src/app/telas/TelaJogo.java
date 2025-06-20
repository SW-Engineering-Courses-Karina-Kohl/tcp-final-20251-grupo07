package app.telas;
import app.Jogo;
import app.Palavra;
import app.UI.Estilos;
import java.awt.*;
import java.awt.event.*;
import java.text.Normalizer;
import javax.swing.*;



public class TelaJogo extends JPanel {
    private Jogo jogo;
    private JLabel labelImagem;
    private JPanel wordPanel;
    private JLabel[] letterLabels;
    private JPanel caixaJogo;
    private CardLayout cardLayout;
    private JPanel painelCartoes;
    private JPanel painelLetrasTestadas;
    private JLabel letrasTestadasLabel;
    private JLabel labelCategoriaDica;





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

        // ---------- Botão Categoria e Dica  ----------
        JButton botaoCategoria = new JButton("Categoria"); //tem que exibir a classe categoria
        botaoCategoria.setBounds(130, 20, 110, 40);
        botaoCategoria.setBackground(new Color(249, 179, 159));
        botaoCategoria.setFocusable(false);
        caixaJogo.add(botaoCategoria);
        
        JButton botaoDica = new JButton("Dica"); // tem que exibir a dica
        botaoDica.setBounds(250, 20, 100, 40);
        botaoDica.setBackground(new Color(213, 204, 224));
        botaoDica.setFocusable(false);
        caixaJogo.add(botaoDica);

        labelCategoriaDica = new JLabel();
        labelCategoriaDica.setBounds(450, 27, 300, 30);
        labelCategoriaDica.setFont(new Font("Arial", Font.BOLD, 15));
        caixaJogo.add(labelCategoriaDica);  // Adiciona só uma vez

 
        botaoCategoria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {   
                labelCategoriaDica.setText(jogo.getPalavra().getCategoria().exibirCategoria());
                labelCategoriaDica.setVisible(true);
            }
        });

        botaoDica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                labelCategoriaDica.setText(jogo.getPalavra().getCategoria().exibirDica());
                labelCategoriaDica.setVisible(true);
            }
        });

        // ---------- Imagem das vidas ----------
        Image imagemRedimensionada = generate_image();
        this.labelImagem = new JLabel(new ImageIcon(imagemRedimensionada));
        this.labelImagem.setBounds(50, 130, 200, 150);
        caixaJogo.add(this.labelImagem);
        
        // ---------- Exibição Palavras ----------
        setupWordDisplay(jogo.getStringPalavra()); 

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
                    Palavra palavraEncerrada = jogo.getPalavra(); // palavra antes do reset
                    switch (letter_in) {
                        case 1:
                            revealLetter(key.charAt(0), jogo.getStringPalavra());
                            atualizarLetrasTestadas();
                            if (todasLetrasAcertadas(jogo.getStringPalavra())) {
                                TelaGanhou.setPalavraEncerrada(palavraEncerrada);
                                cardLayout.show(painelCartoes, "GANHOU");
                                resetGame(); 
                            }
                            break;
                        case -1:
                            labelImagem.setIcon(new ImageIcon(generate_image()));
                            if (jogo.getNumvidas() <= 0) {
                                TelaPerdeu.setPalavraEncerrada(palavraEncerrada);
                                cardLayout.show(painelCartoes, "PERDEU");
                                resetGame();
                            }
                            atualizarLetrasTestadas();
                            break;
                    }
                    

                    System.out.println(key + " Letter pressed: " + letter_in);
                    System.out.println("Numero de vidas: " + jogo.getNumvidas());
                }
            });
        }
    }

    private Image generate_image(){
        ImageIcon imagemOriginal;
        switch (jogo.getNumvidas()) {
            case 6 -> imagemOriginal = new ImageIcon(TelaJogo.class.getResource("/imagens/forca.png"));
            case 5 -> imagemOriginal = new ImageIcon(TelaJogo.class.getResource("/imagens/boneco_cabeca.png"));
            case 4 -> imagemOriginal = new ImageIcon(TelaJogo.class.getResource("/imagens/boneco_torso.png"));
            case 3 -> imagemOriginal = new ImageIcon(TelaJogo.class.getResource("/imagens/boneco_braco1.png"));
            case 2 -> imagemOriginal = new ImageIcon(TelaJogo.class.getResource("/imagens/boneco_braco2.png"));
            case 1 -> imagemOriginal = new ImageIcon(TelaJogo.class.getResource("/imagens/boneco_perna1.png"));
            case 0 -> imagemOriginal = new ImageIcon(TelaJogo.class.getResource("/imagens/boneco_completo.png"));
            default -> imagemOriginal = new ImageIcon(TelaJogo.class.getResource("/imagens/boneco_completo.png"));
        }

        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        return imagemRedimensionada;
    }

    private void setupWordDisplay(String palavra) {
        wordPanel = new JPanel();
        wordPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5)); 
        wordPanel.setOpaque(false);
        wordPanel.setBounds(250, 220, 500, 100); 

        int n = palavra.length();
        letterLabels = new JLabel[n];

        for (int i = 0; i < n; i++) {
            char c = palavra.charAt(i);

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

            // Esconde o underscore caso exista espaço ou hífen na palavra.
            if (c == ' ') {
                underline.setText("   "); 
            }
            else if (c == '-'){
                underline.setText("   ");
            }

            letterBox.add(letterLabel);
            letterBox.add(Box.createVerticalStrut(-6));  // reduz espaçamento entre a letra e o sublinhado
            letterBox.add(underline);

            wordPanel.add(letterBox);
            letterLabels[i] = letterLabel;
        }

        painelLetrasTestadas = new JPanel();
        painelLetrasTestadas.setLayout(new BoxLayout(painelLetrasTestadas, BoxLayout.Y_AXIS));
        painelLetrasTestadas.setOpaque(false);
        painelLetrasTestadas.setBounds(30, 350, 700, 80);

        JLabel tituloTestadas = new JLabel("Letras Testadas:");
        tituloTestadas.setFont(new Font("SansSerif", Font.BOLD, 18));
        tituloTestadas.setAlignmentX(Component.LEFT_ALIGNMENT);

        letrasTestadasLabel = new JLabel("");
        letrasTestadasLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        letrasTestadasLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        painelLetrasTestadas.add(tituloTestadas);
        painelLetrasTestadas.add(Box.createVerticalStrut(5));
        painelLetrasTestadas.add(letrasTestadasLabel);

        caixaJogo.add(wordPanel);
        caixaJogo.add(painelLetrasTestadas);


    }

    public Palavra getPalavraAtual() {
        return jogo.getPalavra();
    }

    private void revealLetter(char guessedLetter, String palavra) {
        palavra = palavra.toUpperCase();
        String normalizada = Normalizer.normalize(palavra, Normalizer.Form.NFD);
        String palavra_limpa = normalizada.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        for (int i = 0; i < palavra_limpa.length(); i++) {
            if (palavra_limpa.charAt(i) == guessedLetter) {
                
                letterLabels[i].setText(String.valueOf(palavra.charAt(i)));
            }
        }
    }

    public boolean todasLetrasAcertadas(String palavra) {
        palavra = palavra.toUpperCase(); 
        for (int i = 0; i < palavra.length(); i++) {
            char letraEsperada = palavra.charAt(i);
            String letraExibida = letterLabels[i].getText();
            
            // Se a letra exibida estiver vazia ou diferente da letra esperada, ainda falta acertar
            //  ignora quando a letra esperada é um espaço ou um hífen
            if ((letraExibida.isEmpty() || letraExibida.charAt(0) != letraEsperada) && !(letraEsperada == ' ') && !(letraEsperada == '-')) {
                System.out.println("Letra esperada: " + letraEsperada + ", letra exibida: " + letraExibida);
                return false;
            }
        }
        return true; // todas as letras estão corretas
    }

    private void atualizarLetrasTestadas() {
        StringBuilder sb = new StringBuilder();

        for (Character c : jogo.getLetrasTestadas()) {
            sb.append(c).append(" ");
        }

        letrasTestadasLabel.setText(sb.toString());
    }

    
    private void resetGame() {
        // remover o painel de letras atual
        caixaJogo.remove(wordPanel);

        // reseta o jogo
        jogo.reset();

        // limpa letras testadas
        letrasTestadasLabel.setText("");

        labelCategoriaDica.setText("");


        // reseta imagem
        labelImagem.setIcon(new ImageIcon(generate_image()));

        // reseta a exibição das letras
        setupWordDisplay(jogo.getStringPalavra());

        // recarrega layout
        caixaJogo.revalidate();
        caixaJogo.repaint();
    }

}
