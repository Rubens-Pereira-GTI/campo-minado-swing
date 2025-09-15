package br.com.cod3r.cm.modelo;

public class teste {
    
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(3, 3, 9);
        tabuleiro.registrarObserver(e -> {
            if(e.isGanhou()){
                System.out.println("ganhou");
            }else{
                System.out.println("perdeu");
            }
        });
        tabuleiro.alternarMarcacao(0, 0);
       
    }
}
