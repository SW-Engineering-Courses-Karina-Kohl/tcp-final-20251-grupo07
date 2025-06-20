package app;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Jogo {

    private int numvidas;
    private Palavra palavra; // palavra a ser adivinhada
    private Set<Character> letrasTestadas;
    private List<Palavra> listaPalavras;

    public Jogo() {
        //print test message
        System.out.println("Iniciando um novo jogo...");
        this.numvidas = 6;
        this.letrasTestadas = new HashSet<>();
        this.listaPalavras = Palavra.criaListaPalavras();
        this.trocaPalavra();
        System.out.println("Palavra atual: " + palavra.getStringPalavra());
    }

    public Palavra getPalavra()
    {
        return palavra;
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

    public String getStringPalavra() {
        // Retorna a palavra a ser adivinhada
        return this.palavra.getStringPalavra();
    }

    public final void trocaPalavra(){
        this.palavra = Palavra.escolhePalavraSecreta(listaPalavras);
    }

    public void reset() {
        // Reseta o jogo para um novo início
        this.numvidas = 6;
        this.letrasTestadas.clear();
        this.trocaPalavra();
        System.out.println("Palavra atual: " + palavra.getStringPalavra());
    }
}