package app;

import java.util.List;

public class Palavra {
    private String palavra;
    private int numLetras;
    private Categoria nome;

    public Palavra (String palavra, int numLetras, Categoria categoria){
        this.palavra = palavra;
        this.numLetras = numLetras;
        this.categoria = categoria;
    }

    public List<Palavra> criaListaPalavras(){

    }

    public Palavra escolhePalavraSecreta(List<Palavra> lista){

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
