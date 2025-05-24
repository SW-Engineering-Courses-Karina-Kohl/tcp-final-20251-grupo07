package categorias;

public class America extends Paises {
    public America (String nomecategoria) {
        super(nomecategoria);
    }

     @Override
    // Retorna o nome da categoria, impressão na tela é feita por outro método.
    public String exibirdica () {
        return "Este país é da América.";
    }
    
}
