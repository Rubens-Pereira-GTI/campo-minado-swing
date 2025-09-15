package br.com.cod3r.cm.visao;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;

import br.com.cod3r.cm.modelo.Campo;
import br.com.cod3r.cm.modelo.Tabuleiro;

public class PainelTabuleiro extends JPanel {
    
    public PainelTabuleiro(Tabuleiro tabuleiro){
        setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));

        tabuleiro.paraCadaCampo( c -> add(new BotaoCampo(c)));

    }
}
