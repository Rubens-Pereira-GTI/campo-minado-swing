package br.com.cod3r.cm.modelo;

public class ResultadoEvento {
    
    private final boolean ganhou;

    boolean isGanhou(){
        return ganhou;
    }

    ResultadoEvento(boolean ganhou){
        this.ganhou = ganhou;
    }
}
