import javax.swing.*;
import java.awt.*;
public class Ejemplo extends JFrame {
public Ejemplo() {
super("Título de la ventana");
setLayout(new FlowLayout());
setSize(200, 100); // pack();
setVisible(true); // show();
setDefaultCloseOperation(EXIT_ON_CLOSE);
// if ocurre algo
JOptionPane.showMessageDialog(null,
"Debe introducir datos en todos los campos",
"Error de entrada ",
JOptionPane.ERROR_MESSAGE);
}
public static void main(String[] args) {
Ejemplo f = new Ejemplo();
}
}