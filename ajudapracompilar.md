##Pra compilar pelo cmd

1. cd --> caminho até a pasta src no computador
2. esses comandos agrupam todos arquivos.class numa pasta chamada out:
javac -d out -sourcepath src src/categorias/Animais.java --> compilando apenas arquivo Animais
javac -d out -sourcepath src src/categorias/*.java --> compilando a pasta categorias inteira
