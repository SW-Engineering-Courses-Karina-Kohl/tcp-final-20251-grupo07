package app;

import categorias.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Palavra {
    private String palavra;
    private int numLetras;
    private Categoria nome;

    public Palavra (String palavra, int numLetras, Categoria categoria){
        this.palavra = palavra;
        this.numLetras = numLetras;
        this.nome = categoria;
    }
    
    public List<Palavra> criaListaPalavras(){
        List<Palavra> listaPalavras = new ArrayList<>();

        String[] arquivos = {"america.txt", "asia.txt", "europa.txt", "aves.txt", "mamiferos.txt", "peixes.txt", "herois.txt", "viloes.txt"};

        // loop que itera sobre o array de diretórios.
        // cada arquivo define a categoria a ser adicionada à palavra.

        // exemplo para "viloes.txt": 
        // Categoria ctg = new Vilao("Personagem");
        // Palavra p = new Palavra(linha, linha.length(), ctg);
        // listaPalavras.add(p);

        for (String nomeArquivo : arquivos) {
            System.out.println("Lendo: " + nomeArquivo); // debug

            try (InputStream is = Palavra.class.getClassLoader().getResourceAsStream(nomeArquivo);
                BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

                if (is == null) {
                    System.err.println("Arquivo não encontrado: " + nomeArquivo);
                    return listaPalavras;
                }

                String linha;
                while ((linha = br.readLine()) != null) {
                    linha = linha.trim();
                    if (!linha.isEmpty()) {
                        Categoria c;

                        switch(nomeArquivo){
                            case "america.txt":
                                c = new America("Países");
                                break;

                            case "asia.txt":
                                c = new Asia("Países");
                                break;

                            case "europa.txt":
                                c = new Europa("Países");
                                break;

                            case "aves.txt":
                                c = new Aves("Animais");
                                break;

                            case "mamiferos.txt":
                                c = new Mamifero("Animais");
                                break;

                            case "peixes.txt":
                                c = new Marinho("Animais");
                                break;

                            case "herois.txt":
                                c = new SuperHeroi("Personagens");
                                break;

                            case "viloes.txt":
                                c = new Vilao("Personagens");
                                break;

                            default:
                                throw new IllegalArgumentException("Arquivo inválido: " + nomeArquivo);    
                        }

                        Palavra p = new Palavra(linha, linha.length(), c);
                        listaPalavras.add(p);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // retorna a lista completa de palavras, contendo todas as palavras de todos os arquivos, com suas respectivas categorias.
        return listaPalavras;
    }

    
    public Palavra escolhePalavraSecreta(List<Palavra> lista){
        if (lista==null || lista.isEmpty()){
            return null; // trocar para lançar exceção?
        }

        int randIndex = ThreadLocalRandom.current().nextInt(lista.size());
        return lista.get(randIndex);
    }

    public String getPalavra() {
        return palavra;
    }

    public int getNumLetras() {
        return numLetras;
    }

    public Categoria getNome() {
        return nome;
    }
}
