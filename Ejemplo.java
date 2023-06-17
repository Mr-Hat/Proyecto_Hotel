import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejemplo extends JFrame{
    JButton rojo = new JButton("Rojo");
    JButton azul = new JButton("Azul");
    Container p;

    public Ejemplo(){
        super("Color de fondo");
        p = this.getContentPane();
        setLayout(new FlowLayout());
        add(rojo);
        add(azul);
        rojo.addActionListener(new OyenteRojo());
        azul.addActionListener(new OyenteAzul());
        setSize(200, 200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        Ejemplo ventana = new Ejemplo();
    }

    class OyenteRojo implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evento){
            p.setBackground(Color.red);
        }
    }

    class OyenteAzul implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evento){
            p.setBackground(Color.BLUE);
        }
    }
}