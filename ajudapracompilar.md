## Pra compilar pelo cmd

1. cd --> caminho até a pasta src no computador
2. esses comandos agrupam todos arquivos.class numa pasta chamada out:

## Compilando por arquivos:

- javac -d out -sourcepath src src/categorias/Animais.java
- java src/app/GUI.java --> para testar interface

## Compilando por pastas:

- javac -d out -sourcepath src src/categorias/*.java
- javac -d out -sourcepath src src/app/*.java
