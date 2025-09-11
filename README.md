## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

---

# TODO 

[ ] Crie um enum chamado CampoEvento, enumere todas os eventos ao cliclar na caixa
[ ] Defina que será seu subject, e depois crie uma lista com todos os observers dentro do seu subject
[ ] crie uma interface que gere um polimorfismo entre os observers e essa interface, passe para essa inerface duas coisas, o evento e o observador. campoObserver
[ ] Mudança no código! crie uma lista dentro do subject com a function interface BiConsumer, passando para ele o evento e o observador
[ ] crie um método que adicione os observadores na lista - registrarObservers
[ ] crue um método que notifique os observadores - notifiqueObservers - notifique todos os observer através de uma expressão lambda 
[ ]  