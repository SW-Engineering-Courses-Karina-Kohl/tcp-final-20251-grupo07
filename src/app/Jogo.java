package app;
import categorias.*;
import java.util.HashSet;
import java.util.Set;

public class Jogo {

    private int numvidas;
    private Categoria categoria; // categoria da palavra
    private Palavra palavra; // palavra a ser adivinhada
    private Set<Character> letrasTestadas;

    public Jogo(Categoria categoria, Palavra palavra) {
        this.numvidas = 7;
        this.categoria = categoria;
        this.palavra = palavra;
        this.letrasTestadas = new HashSet<>();
    }

    public Jogo() {
        //print test message
        System.out.println("Iniciando um novo jogo test...");
        this.numvidas = 7;
        Categoria cat = new America("america");
        this.categoria = cat;
        Palavra pal = new Palavra("argentina", 6, cat);
        this.palavra = pal;
        this.letrasTestadas = new HashSet<>();
    }

    public int getNumvidas() {
        return numvidas;
    }

    public int checaLetra(char letra) {
        // Verifica se a letra digitada pelo usuário está na palavra e ainda não foi testada
        if (letrasTestadas.contains(letra)) {
            return 0; // Letra já testada
        }
        if (this.palavra.contemLetra(letra)){
            letrasTestadas.add(letra);
            return 1; // Letra correta
        }
        else{
            letrasTestadas.add(letra);
            retiraVida(); // Retira uma vida se a letra não estiver na palavra
            return -1; // Letra incorreta
        }
        
    }

    public void retiraVida() {
        // Diminui o número de vidas
        this.numvidas--;
    }

    public Set<Character> getLetrasTestadas() {
        return letrasTestadas;
    }

    public String getPalavra() {
        // Retorna a palavra a ser adivinhada
        return this.palavra.getPalavra();
    }
}