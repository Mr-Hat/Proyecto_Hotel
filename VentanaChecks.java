import javax.swing.*;
import java.awt.*;

public class VentanaChecks extends JPanel{
    public VentanaChecks(){
        VentanaReserv nv = new VentanaReserv();
        setLayout(new GridLayout(8, 2));
        add(new JLabel("Check-in",JLabel.RIGHT));
        add(new JLabel());
        add(new JLabel("Fecha de entrada: ", JLabel.RIGHT));
        add(new JLabel());
        add(new JLabel("Cedula del titular: ",JLabel.RIGHT));
        add(new JLabel());
        add(new JLabel("Habitacion del resrvante: ",JLabel.RIGHT));
        add(new JLabel());
        add(new JLabel("Check-out",JLabel.RIGHT));
        add(new JLabel());
        add(new JLabel("Fecha de salida: ",JLabel.RIGHT));
        add(new JLabel());
        add(new JLabel("Cedula del titular: ",JLabel.RIGHT));
        add(new JLabel());
        add(new JLabel("Monto a cancelar: ",JLabel.RIGHT));
        add(new JLabel());
    }
}
