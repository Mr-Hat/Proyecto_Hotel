import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Vista extends JFrame {
    private Container panel;
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();
    private JPanel p4 = new JPanel();
    private JPanel p5 = new JPanel();
    private JPanel p6 = new JPanel();
    private JComboBox<Integer> caja1 = new JComboBox<Integer>();
    private JComboBox<Integer> caja2 = new JComboBox<Integer>();
    private JComboBox<Integer> caja3 = new JComboBox<Integer>();
    private JComboBox<Integer> fechin1 = new JComboBox<Integer>();
    private JComboBox<Integer> fechin2 = new JComboBox<Integer>();
    private JComboBox<Integer> fechin3 = new JComboBox<Integer>();
    private JComboBox<Integer> fechout1 = new JComboBox<Integer>();
    private JComboBox<Integer> fechout2 = new JComboBox<Integer>();
    private JComboBox<Integer> fechout3 = new JComboBox<Integer>();
    public Vista (){
        super("Hoteleria Mi Refugio");
        panel = getContentPane();
        panel.setLayout(new GridLayout(7,1));
        for(int i = 1 ;i<=31; i++){
            caja1.addItem(i);
            fechin1.addItem(i);
            fechout1.addItem(i);
        }
        for(int j = 1; j <= 12; j++){
            caja2.addItem(j); 
            fechin2.addItem(j);
            fechout2.addItem(j);  
        }
        for(int k = 2023; k <= 2100; k++){
            caja3.addItem(k);
            fechin3.addItem(k);
            fechout3.addItem(k);
        }
        p1.add(new JLabel("Fecha de realizacion de la reserva:"));
        p1.add(caja1);
        p1.add(caja2);
        p1.add(caja3);
        caja1.addItemListener(new OyenteItem());
        caja2.addItemListener(new OyenteItem());
        caja3.addItemListener(new OyenteItem());
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(p1,BorderLayout.CENTER);
        p2.add(new JLabel("Cedula del titular de la reservacion:"));
        p2.add(new JTextField(9));
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(p2,BorderLayout.CENTER);
        p3.add(new JLabel("Tipo de habitacion:"));
        p3.add(new JTextField(9));
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(p3, BorderLayout.CENTER);
        p4.add(new JLabel("Fecha de entrada:"));
        p4.add(new JTextField(9));
        p4.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(p4, BorderLayout.CENTER);
        p5.add(new JLabel("Fecha de salida:"));
        p5.add(new JTextField(9));
        p5.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(p5, BorderLayout.CENTER);
        p6.add(new JLabel("Cantidad de dias de estadia en el hotel:"));
        p6.add(new JTextField(9));
        p6.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(p6, BorderLayout.CENTER);
        setSize(400, 250);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        Vista v = new Vista();
    }

    class OyenteItem implements ItemListener{
        public void itemStateChanged(ItemEvent evento){
        }
    }
}