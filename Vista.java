import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {
    private Container panel;
    
    public Vista(){
        super("Hoteleria Mi Refugio");
        panel = getContentPane();
        panel.setLayout(new GridLayout(5,2,5,5));
        JLabel test1 = new JLabel("Apartado para reservas");
        panel.add(test1);
        JLabel test2 = new JLabel("Fecha:");
        panel.add(test2);
        JTextField test3 = new JTextField(10);
        panel.add(test3);
        JLabel test4 = new JLabel("Titular del arrendatario:");
        panel.add(test4);
        JTextField test5 = new JTextField(10);
        panel.add(test5);
        setSize(200,200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args){
        Vista v = new Vista();
    }
}