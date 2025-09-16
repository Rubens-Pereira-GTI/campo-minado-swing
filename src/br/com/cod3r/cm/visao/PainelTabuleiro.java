package br.com.cod3r.cm.visao;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.GridLayout;
import br.com.cod3r.cm.modelo.Tabuleiro;
public class PainelTabuleiro extends JPanel {
    
    public PainelTabuleiro(Tabuleiro tabuleiro){
        setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));

        //cria os botões para cada campo
        tabuleiro.paraCadaCampo( c -> add(new BotaoCampo(c)));

        //diz se houve a derrota ou a vitoria
        tabuleiro.registrarObserver(e -> {
            //Garante que essa mensagem será executado apenas após termos 
            // renderizado a parte final do jogo que é o x mostrando que uma bomba foi ativada
            SwingUtilities.invokeLater(() -> {
                if(e.isGanhou()){
                    JOptionPane.showMessageDialog(this, "Ganhou");
                }else{
                        JOptionPane.showMessageDialog(this, "Perdeu :( ");
                }
                tabuleiro.reiniciar();
           }); 

        });

    }
}
