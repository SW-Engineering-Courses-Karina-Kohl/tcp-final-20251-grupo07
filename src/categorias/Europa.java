package categorias;

public class Europa extends Paises{
    public Europa (String nomecategoria) {
        super(nomecategoria);
    }

     @Override
    // Retorna o nome da categoria, impressão na tela é feita por outro método.
    public String exibirDica () {
        return "Este país é da Europa.";
    }
    
}
