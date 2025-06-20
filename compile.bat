@echo off

echo Compilando arquivos .java...
echo passo 1 de 4...
for %%f in (src\categorias\*.java) do javac -d out -sourcepath src %%f
echo passo 2 de 4...
for %%f in (src\app\*.java) do javac -d out -sourcepath src %%f
echo passo 3 de 4...
for %%f in (src\app\telas\*.java) do javac -d out -sourcepath src %%f
echo passo 4 de 4...
for %%f in (src\app\UI\*.java) do javac -d out -sourcepath src %%f

if not exist dist (
    mkdir dist
)

echo Criando arquivo .jar em dist...
jar cfe dist\jogo_forca.jar app.GUI -C out .

echo Finalizado! Arquivo .jar em dist\jogo_forca.jar

pause

