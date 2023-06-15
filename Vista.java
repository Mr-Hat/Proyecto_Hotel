import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {
    private Container panel;
    
    public Vista(){
        super("Hoteleria Mi Refugio");
        panel = getContentPane();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 20));
        JLabel test1 = new JLabel("Apartado para reservas");
        panel.add(test1);
        JLabel test2 = new JLabel("Fecha:");
        panel.add(test2);
        JTextField test3 = new JTextField(10);
        panel.add(test3);
        setSize(200,200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args){
        Vista v = new Vista();
    }
}