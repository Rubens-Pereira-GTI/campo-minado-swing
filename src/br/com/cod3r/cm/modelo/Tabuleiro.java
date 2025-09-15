package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Tabuleiro implements CampoObservers{
    private final int linhas;
    private final int colunas;
    private final int minas;

    private List<Campo> campos = new ArrayList<>();
    private List<Consumer<ResultadoEvento>> observadores = new ArrayList<>();

    public void registrarObserver(Consumer<ResultadoEvento> observador){
        observadores.add(observador);
    }

    private void notificarObservers(boolean resultado){
        observadores.stream().forEach(o -> o.accept(new ResultadoEvento(resultado)));
    }

    public void paraCadaCampo(Consumer<Campo> funcao){
        campos.forEach(funcao);
    }
    public Tabuleiro(int linhas, int colunas, int minas){
        this.linhas = linhas;
        this. colunas = colunas;
        this.minas = minas;
        
        gerarCampos();
        associarOsVizinhos();
        sortearMinas();
    }
    /*
     * Esse metodo vai encontrar o campo na lista e chamar o método abrir do campo
     * Quando encontrado o loop é encerrado, chamamos o método responsável por abrir o campo
     */
    public void abrir(int linha, int coluna){

        try {
             campos.stream()
            .filter(c -> c.getLinha()==linha && c.getColuna() == coluna)
            .findFirst()
            .ifPresent(c -> c.abrir());
        } catch (Exception e) {
            campos.forEach(c -> c.setAberto(true));
            throw e;
        }       
    }

    private void mostrarMinas(){
        campos.stream()
        .filter(c -> c.isMinado())
        .forEach(c -> c.setAberto(true));
    }

    //Esse metodo vai encontrar o campo 
    public void alternarMarcacao(int linha, int coluna){
        campos.stream()
            .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
            .findFirst()
            .ifPresent(c -> c.alternarMarcacao());
    }

    private void sortearMinas() {
        int minasArmadas=0;
        Predicate<Campo> minado = c -> c.isMinado();

        do{
            int aleatorio = (int) (Math.random() * campos.size());
            campos.get(aleatorio).minar();
//essa ultima linha se colocada no top gera um bug pq quando a segunda linha roda, minas armadas não atualiza 
//gerando um bug de uma bomba a mais, pois ele passa pelo loop sem essa atualização toda vez
            minasArmadas = (int) campos.stream().filter(minado).count();
        }while(minasArmadas < minas);
    }

    private void associarOsVizinhos() {
        for (Campo c1 : campos){
            for(Campo c2 : campos){
                c1.addVizinho(c2);
            }
        }
    }

    public void gerarCampos(){
        for(int linha=0; linha < linhas; linha++){
            for(int coluna=0; coluna < colunas; coluna++ ){
                Campo campo = new Campo(linha, coluna);
                campo.registrarObserver(this);                                
                campos.add(campo); 
            }

        }
    }

    public boolean objetivoAlcancado(){
        
        return campos.stream().allMatch(c -> c.objetivoAlcancado());
    }

    public void reiniciar(){
        campos.stream().forEach(c -> c.reiniciar());
        sortearMinas();
    }
    @Override
    public void eventoOcorreu(Campo campo, CampoEvent campoEvent) {      
        if(campoEvent == CampoEvent.EXPLODIR){
            mostrarMinas();
            notificarObservers(false);

        }else if(objetivoAlcancado()){
            notificarObservers(true);
        }
        
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

   

}
