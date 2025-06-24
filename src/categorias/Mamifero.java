package categorias;

public class Mamifero extends Animais {
    public Mamifero (String nomecategoria) {
        super(nomecategoria);
    }

     @Override
    // Retorna o nome da categoria, impressão na tela é feita por outro método.
    public String exibirDica () {
        return "Este animal é um mamífero.";
    }
    
}
