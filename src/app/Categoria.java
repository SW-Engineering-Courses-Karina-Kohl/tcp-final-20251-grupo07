package app;

public abstract class Categoria {
   public String nomecategoria;

   public Categoria(String var1) {
      this.nomecategoria = var1;
   }

   public abstract String exibirCategoria();

   public abstract String exibirDica();
}