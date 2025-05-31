package categorias;

public class Asia extends Paises {
    public Asia (String nomecategoria) {
        super(nomecategoria);
    }

     @Override
    // Retorna o nome da categoria, impressão na tela é feita por outro método.
    public String exibirDica () {
        return "Este país é da Ásia.";
    }
    
}
