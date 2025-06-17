package app;
import categorias.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Jogo {

    private int numvidas;
    private Categoria categoria; // categoria da palavra
    private Palavra palavra; // palavra a ser adivinhada
    private Set<Character> letrasTestadas;
    private List<Palavra> listaPalavras;

    public Jogo() {
        //print test message
        System.out.println("Iniciando um novo jogo test...");
        this.numvidas = 7;
        Categoria cat = new America("america");
        this.categoria = cat;
        
        this.letrasTestadas = new HashSet<>();
        this.listaPalavras = Palavra.criaListaPalavras();
        this.trocaPalavra();

    }

    public int getNumvidas() {
        return numvidas;
    }

    public void setNumvidas(int numvidas) {
        this.numvidas = numvidas;
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

    public final void trocaPalavra(){
        this.palavra = Palavra.escolhePalavraSecreta(listaPalavras);
    }

    public void reset() {
        // Reseta o jogo para um novo início
        this.numvidas = 7;
        this.letrasTestadas.clear();
        this.trocaPalavra();
    }
}