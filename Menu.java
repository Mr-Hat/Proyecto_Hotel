import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JMenuBar barra = new JMenuBar();
    private JMenuItem boton1,boton2,boton3,boton4;
    private JMenu menu = new JMenu("Transaccion");
    public Menu(){
        super("Hotel mi refugio");
        setJMenuBar(barra);
        menu.setMnemonic('o');
        barra.add(menu);
        boton1 = new JMenuItem("Reservaciones",'r');
        boton2 = new JMenuItem("Check-in",'i');
        boton3 = new JMenuItem("Check-out",'o');
        boton4 = new JMenuItem("Cancelacion",'C');
        boton1.addActionListener(new OyenteMenu());
        menu.add(boton1);
        boton2.addActionListener(new OyenteMenu());
        menu.add(boton2);
        boton3.addActionListener(new OyenteMenu());
        menu.add(boton3);
        boton4.addActionListener(new OyenteMenu());
        menu.add(boton4);
        setSize(300,70);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }

    public static void main(String[] args){
        Menu nm = new Menu();
    }

    class OyenteMenu implements ActionListener{
            public void actionPerformed(ActionEvent evento){
                String accion = evento.getActionCommand();
                if(evento.getSource() instanceof JMenuItem){
                    if("Reservaciones".equals(accion)){
                        VentanaReserv nr = new VentanaReserv();
                    }
                    else if("Check-in".equals(accion)){
                        VentanaCheckIn nci = new VentanaCheckIn();
                    }
                    else if("Check-out".equals(accion)){
                        VentanaCheckOut nco = new VentanaCheckOut();
                    }else{
                        VentanaCancelacion nc = new VentanaCancelacion();
                    }
                }    
            }
        }
}
