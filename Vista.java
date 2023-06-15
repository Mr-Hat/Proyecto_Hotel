import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {
    private Container panel = new Container();
    public Vista(){
        super("Hoteleria Mi Refugio");
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Apartado para reservas"));
        panel.add(new JLabel("Fecha:"));
        panel.add(new JTextField());
        setSize(200,200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args){
        Vista v = new Vista();
    }
}