package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;

public class Campo {
   
    private final int linha;
    private final int coluna;
    private boolean aberto= false;
    private boolean minado= false;
    private boolean marcado = false;
    private List<Campo> vizinhos = new ArrayList<>();

    public Campo(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }


    public boolean addVizinho(Campo vizinho){  
        //Logica para saber se o vizinho é do tipo Cruz ou diagonal 
        //System.out.println(linha +"L L " + vizinho.linha );     
        boolean vizinhoLinhaDiferente = linha != vizinho.linha;
        boolean vizinhoColunaDiferente = coluna != vizinho.coluna;
        boolean vizinhoDiagonal = vizinhoColunaDiferente && vizinhoLinhaDiferente;
        
        //se o deltaGeral der 1 é o vizinho do tipo cruz se der 2 é vizinho do tipo diagonal
        int deltaLinha = Math.abs(vizinho.linha - linha);
        int deltaColuna = Math.abs(vizinho.coluna - coluna);
        int deltaGeral = deltaColuna + deltaLinha;
        //System.out.println( deltaLinha +" DL e DC "+ deltaColuna);
        if(deltaGeral == 1 && !vizinhoDiagonal){
            vizinhos.add(vizinho);
            return true;
        }else if(deltaGeral == 2 && vizinhoDiagonal){
            vizinhos.add(vizinho);
            return true;
        }else{
            return false;
        }
        //caso caia no ele ele muda para false
         
    }

    //lógica de alternancia de marcação
    //TODO modificar o método alterar marcação para que quando ele aconteça um observer seja notificado
    public void alternarMarcacao(){
        if(!aberto){
            marcado = !marcado;
        } 
    }

    public boolean abrir(){
        if(!aberto && !marcado ){
            aberto = true;
            if(minado){
                //TODO implementar nova versão     
                //FIXME  erro que precisa ser corrigido
            }
            //vamos abrir a vizinhaça até que não esteja mais seguro
            if(vizinhancaSegura()){
                vizinhos.forEach(v -> v.abrir());    
            }
            // se cair no primeiro if é o campo foi aberto então retornamos true
            return true;
        }else{
            return false;
  
        }
    }

    public boolean vizinhancaSegura(){
        return vizinhos.stream().noneMatch(vizinho -> vizinho.minado);
    }

    public void minar(){
        minado = true;
    }

    public boolean isMarcado(){
        return marcado;
    }

    public boolean isAberto(){
        return aberto;
    }

    void setAberto(boolean aberto) {
        this.aberto = aberto;
    }


    public boolean isFechado(){
        return !aberto;
    }

    public boolean isMinado(){
        return minado;
    }


    public int getLinha() {
        return linha;
    }


    public int getColuna() {
        return coluna;
    }

    public boolean objetivoAlcancado(){
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }

    public long minasNaVizinhaca(){
        return vizinhos.stream().filter(v -> v.minado).count();
    }

    public void reiniciar(){
        aberto = false;
        minado = false;
        marcado = false;
    }

  
  
}
