package app;

import categorias.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.text.Normalizer;

public class Palavra {
    private String palavra;
    private int numLetras;
    private Categoria nomeCategoria;

    public Palavra(String palavra, int numLetras, Categoria nomeCategoria) {
        this.palavra = palavra;
        this.numLetras = numLetras;
        this.nomeCategoria = nomeCategoria;
    }

    public static List<Palavra> criaListaPalavras() {
        List<Palavra> listaPalavras = new ArrayList<>();

        String[] arquivos = { "america.txt", "asia.txt", "europa.txt", "aves.txt", "mamiferos.txt", "peixes.txt",
                "herois.txt", "viloes.txt", "animacao.txt" };

        // loop que itera sobre o array de diretórios.
        // cada arquivo define a categoria a ser adicionada à palavra.

        // exemplo para "viloes.txt":
        // Categoria ctg = new Vilao("Personagem");
        // Palavra p = new Palavra(linha, linha.length(), ctg);
        // listaPalavras.add(p);

        for (String nomeArquivo : arquivos) {
            String nomeCatTxt = nomeArquivo;
            nomeArquivo = "resources/"+nomeArquivo;
            System.out.println("Lendo: " + nomeArquivo); // debug
            try (InputStream is = Palavra.class.getClassLoader().getResourceAsStream(nomeArquivo);
                    BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

                if (is == null) {
                    System.err.println("Arquivo não encontrado: " + nomeArquivo);
                    return listaPalavras;
                }
                else {
                    System.out.println("Arquivo carregado com sucesso!");
                }

                String linha;
                while ((linha = br.readLine()) != null) {
                    linha = linha.trim();
                    if (!linha.isEmpty()) {
                        Categoria categoria;

                        switch (nomeCatTxt) {
                            case "america.txt":
                                categoria = new America("Países");
                                break;

                            case "asia.txt":
                                categoria = new Asia("Países");
                                break;

                            case "europa.txt":
                                categoria = new Europa("Países");
                                break;

                            case "aves.txt":
                                categoria = new Aves("Animais");
                                break;

                            case "mamiferos.txt":
                                categoria = new Mamifero("Animais");
                                break;

                            case "peixes.txt":
                                categoria = new Marinho("Animais");
                                break;

                            case "herois.txt":
                                categoria = new SuperHeroi("Personagens");
                                break;

                            case "viloes.txt":
                                categoria = new Vilao("Personagens");
                                break;

                            case "animacao.txt":
                                categoria = new Animacao("Personagens");
                                break;

                            default:
                                throw new IllegalArgumentException("Arquivo inválido: " + nomeArquivo);
                        }

                        Palavra palavra = new Palavra(linha, linha.length(), categoria);
                        listaPalavras.add(palavra);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // retorna a lista completa de palavras, contendo todas as palavras de todos os
        // arquivos, com suas respectivas categorias.
        return listaPalavras;
    }

    public static Palavra escolhePalavraSecreta(List<Palavra> lista) {
        if (lista == null || lista.isEmpty()) {
            return null; // trocar para lançar exceção?
        }

        int randIndex = ThreadLocalRandom.current().nextInt(lista.size());
        return lista.get(randIndex);
    }

    public String getStringPalavra() {
        return palavra;
    }

    public int getNumLetras() {
        return numLetras;
    }

    public Categoria getCategoria() {
        return nomeCategoria;
    }       

    public boolean contemLetra(char letra) {
        // Normaliza a palavra e verifica se a letra está na palavra.
        // Normalização coloca em lower case, remove acentos e cedilhas.
        String palavraLimpa = Normalizer.normalize(this.palavra.toLowerCase(), Normalizer.Form.NFD)
                                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                                        .replaceAll("ç", "c");

        return palavraLimpa.contains(String.valueOf(letra).toLowerCase());
    }
}
