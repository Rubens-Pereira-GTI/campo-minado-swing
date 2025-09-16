package br.com.cod3r.cm.visao;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import br.com.cod3r.cm.modelo.Campo;
import br.com.cod3r.cm.modelo.CampoEvent;
import br.com.cod3r.cm.modelo.CampoObservers;

public class BotaoCampo extends JButton implements CampoObservers, MouseListener{
    
    private Campo campo ;
    
    private final Color BG_PADRAO = new Color(184, 184, 184); 
    private final Color BG_MARCAR = new Color(8, 179, 247); 
    private final Color BG_EXPLODIR = new Color(189, 66, 68); 
    private final Color TEXTO_VERDE = new Color(0, 100, 0); 

    public BotaoCampo(Campo campo){
        this.campo = campo;
        setBackground(BG_PADRAO);
        setBorder(BorderFactory.createBevelBorder(0));

        addMouseListener(this);

        this.campo.registrarObserver(this);
        
    }

    @Override
    public void eventoOcorreu(Campo campo, CampoEvent campoEvent) {
    
        switch (campoEvent) {
            case ABRIR:
                aplicarEstiloAbrir();
                break;

            case MARCAR:
                aplicarEstilMarcar();
                break;

            case EXPLODIR:
                aplicarEstiloExplodir();
                break;
            default:
                aplicarEstiloPadrao();                
        }

        //garante que o componente seja repintado sempre que acontecer tivermos o restart
        SwingUtilities.invokeLater(()-> {
            repaint();
            validate();
        });
        
    }

    private void aplicarEstiloPadrao() {
        setBackground(BG_PADRAO);
        setBorder(BorderFactory.createBevelBorder(0));
        setText("");
    }

    private void aplicarEstiloExplodir() {

        setBackground(BG_EXPLODIR);
        setForeground(Color.WHITE);
        setText("X");
    }

    private void aplicarEstilMarcar() {
        setBackground(BG_MARCAR);
        setForeground(Color.black);
        setText("M");
        //setIcon();
    }

    private void aplicarEstiloAbrir() {
       
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        if(campo.isMinado()){
            setBackground(BG_EXPLODIR);
            return;
        }
        setBackground(BG_PADRAO);
        setBorder(BorderFactory.createLineBorder(Color.gray));

        switch (campo.minasNaVizinhaca()) {
            case 1:
                setForeground(TEXTO_VERDE);
                break;
            case 2:
                setForeground(Color.BLUE);
                break;
            case 3:
                setForeground(Color.YELLOW);
                break;
            case 4, 5 , 6:
                setForeground(Color.RED);
                break;
            default:
                setForeground(Color.PINK);
        }
        String valaor = !campo.vizinhancaSegura() ? campo.minasNaVizinhaca() + "" : "";
        setText(valaor);
    }

    //interface - mouseListener
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == 1){
            campo.abrir();
        }else{
            campo.alternarMarcacao();
        }

    }
    
    public void mouseClicked(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}

}
