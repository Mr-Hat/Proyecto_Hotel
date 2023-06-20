import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Vista extends JFrame{
    private JMenuBar barra = new JMenuBar();
    public JMenuItem boton1,boton2,boton3,boton4;
    private JMenu menu = new JMenu("Transaccion");
    public Vista(){
        setJMenuBar(barra);
        menu.setMnemonic('o');
        barra.add(menu);
        boton1 = new JMenuItem("Reservaciones",'r');
        boton2 = new JMenuItem("Check-in",'i');
        boton3 = new JMenuItem("Check-out",'o');
        boton4 = new JMenuItem("Cancelacion",'C');

        menu.add(boton1);
        menu.add(boton2);
        menu.add(boton3);
        menu.add(boton4);
        
    }

    public class VentanaCancelacion extends JFrame{
        private Container panel;
        private JPanel p1,p2,p3,p4;
        public JComboBox<Integer> box1,box2,box3, hora1, hora2;
        public JButton enviar, cancelar;
        public JTextField cedula;
        public VentanaCancelacion(){
            super("Cancelacion");
            panel = getContentPane();
            panel.setLayout(new GridLayout(3,1));
            box1 = new JComboBox<Integer>();
            box2 = new JComboBox<Integer>();
            box3 = new JComboBox<Integer>();
            for(int i = 1; i <= 31; i++){
                box1.addItem(i);
            }
            for(int l = 1; l <= 12; l++){
                box2.addItem(l);
            }
            for(int k = 2023; k <= 2100; k++){
                box3.addItem(k);
            }
            for(int i = 0; i<23; i++){
                hora1.addItem(i);
            }
            for(int i = 0; i<59;i++){
                hora2.addItem(i);
            }
            p1 = new JPanel();
            p1.setLayout(new FlowLayout(FlowLayout.LEFT));
            p1.add(new JLabel("Fecha de realizacion:"));
            p1.add(box1);
            p1.add(box2);
            p1.add(box3);
            add(p1);
            p2 = new JPanel();
            p2.setLayout(new FlowLayout(FlowLayout.LEFT));
            p2.add(new JLabel("Cedula del titular"));
            cedula = new JTextField(10);
            p2.add(cedula);
            add(p2);
            p4 = new JPanel();
            p4.setLayout(new FlowLayout(FlowLayout.LEFT));
            p4.add(new JLabel("Hora de realizacion:"));
            p4.add(hora1);
            p4.add(hora2);
            add(p4);
            enviar = new JButton("Enviar cancelacion");
            cancelar = new JButton("Cancelar");
            p3 = new JPanel();
            p3.setLayout(new FlowLayout(FlowLayout.CENTER));
            p3.add(enviar);
            p3.add(cancelar);
            add(p3, BorderLayout.CENTER);
            setSize(350, 150);
            setVisible(true);
        }
    }

    public class VentanaReserv extends JFrame {
        private Container panel;
        private JPanel p2 = new JPanel();
        private JPanel p3 = new JPanel();
        private JPanel p4 = new JPanel();
        private JPanel p5 = new JPanel();
        public JTextField tituReservacion; 
        private JPanel pOption = new JPanel();
        public JComboBox<Integer> fechin1 = new JComboBox<Integer>();
        public JComboBox<Integer> fechin2 = new JComboBox<Integer>();
        public JComboBox<Integer> fechin3 = new JComboBox<Integer>();
        public JComboBox<Integer> fechout1 = new JComboBox<Integer>();
        public JComboBox<Integer> fechout2 = new JComboBox<Integer>();
        public JComboBox<Integer> fechout3 = new JComboBox<Integer>();
        public JComboBox<String> tipoHabitaciones = new JComboBox<String>();
        public JButton siguiente, cancelar;
        public VentanaReserv (){
            super("Hoteleria Mi Refugio");
            panel = getContentPane();
            panel.setLayout(new GridLayout(7,1));
            for(int i = 1 ;i <=31 ; i++){
                fechin1.addItem(i);
                fechout1.addItem(i);
            }
            for(int j = 1; j <= 12; j++){
                fechin2.addItem(j);
                fechout2.addItem(j);  
            }
            for(int k = 2023; k <= 2100; k++){
                fechin3.addItem(k);
                fechout3.addItem(k);
            }
            tipoHabitaciones.addItem("Individual");
            tipoHabitaciones.addItem("Matrimonial");
            tipoHabitaciones.addItem("Doble");
            tipoHabitaciones.addItem("Cuadruple");
            tipoHabitaciones.addItem("Suite");
            p2.add(new JLabel("Cedula del titular de la reservacion:"));
            tituReservacion = new JTextField(9);
            p2.add(tituReservacion);
            p2.setLayout(new FlowLayout(FlowLayout.LEFT));
            add(p2,BorderLayout.CENTER);
            p3.add(new JLabel("Tipo de habitacion:"));
            p3.add(tipoHabitaciones);
            p3.setLayout(new FlowLayout(FlowLayout.LEFT));
            add(p3, BorderLayout.CENTER);
            p4.add(new JLabel("Fecha de entrada:"));
            p4.add(fechin1);
            p4.add(fechin2);
            p4.add(fechin3);
            p4.setLayout(new FlowLayout(FlowLayout.LEFT));
            add(p4, BorderLayout.CENTER);
            p5.add(new JLabel("Fecha de salida:"));
            p5.add(fechout1);
            p5.add(fechout2);
            p5.add(fechout3);
            p5.setLayout(new FlowLayout(FlowLayout.LEFT));
            add(p5, BorderLayout.CENTER);
            siguiente = new JButton("Enviar reserva");
            cancelar = new JButton("Cancelar");
            pOption.add(siguiente);
            pOption.add(cancelar);
            pOption.setLayout(new FlowLayout(FlowLayout.CENTER));
            add(pOption,BorderLayout.CENTER);
            setSize(400, 250);
            setVisible(true);
        }
    }

    public class VentanaCheckIn extends JFrame{
        private Container contenedor;
        public JComboBox<Integer> box1, box2, box3, hora1, hora2;
        private JPanel p1,p2,p3,p4,p5;
        public JTextField cedula;
        public JButton enviar, cancelar;
        public VentanaCheckIn(){
            super("Check-in");
            contenedor = getContentPane();
            contenedor.setLayout(new GridLayout(4,1));
            box1 = new JComboBox<Integer>();
            box2 = new JComboBox<Integer>();
            box3 = new JComboBox<Integer>();
            for(int i = 1; i <= 31; i++){
                box1.addItem(i);
            }
            for(int l = 1; l <= 12; l++){
                box2.addItem(l);
            }
            for(int k = 2023; k <= 2100; k++){
                box3.addItem(k);
            }
            for(int i = 0; i<23; i++){
                hora1.addItem(i);
            }
            for(int i = 0; i<59;i++){
                hora2.addItem(i);
            }
            p1 = new JPanel();
            p1.setLayout(new FlowLayout(FlowLayout.LEFT));
            p1.add(new JLabel("Fecha de entrada:"));
            p1.add(box1);
            p1.add(box2);
            p1.add(box3);
            add(p1,BorderLayout.WEST);
            p2 = new JPanel();
            p2.setLayout(new FlowLayout(FlowLayout.LEFT));
            p2.add(new JLabel("Cedula del titular:"));
            p5 = new JPanel();
            p5.setLayout(new FlowLayout(FlowLayout.LEFT));
            p5.add(new JLabel("Hora de entrada"));
            p5.add(hora1);
            p5.add(hora2);
            cedula = new JTextField(10);
            p2.add(cedula);
            add(p2, BorderLayout.WEST);
            p3 = new JPanel();
            p3.setLayout(new FlowLayout(FlowLayout.LEFT));
            p3.add(new JLabel("Numero de habitacion:"));
            add(p3,BorderLayout.WEST);
            p4 = new JPanel();
            p4.setLayout(new FlowLayout(FlowLayout.CENTER));
            enviar = new JButton("Enviar check-in");
            cancelar = new JButton("Cancelar");
            p4.add(enviar);
            p4.add(cancelar);
            add(p4, BorderLayout.CENTER);
            setSize(400, 200);
            setVisible(true);
        }
    }

    public class VentanaCheckOut extends JFrame{
        private Container contenedor;
        public JComboBox<Integer> box1, box2, box3, hora1, hora2;
        private JPanel p1,p2,p3,p4;
        public JTextField cedula,monto;
        public JButton enviar, cancelar;
        public VentanaCheckOut(){
            super("Check-out");
            contenedor = getContentPane();
            contenedor.setLayout(new GridLayout(4,1));
            box1 = new JComboBox<Integer>();
            box2 = new JComboBox<Integer>();
            box3 = new JComboBox<Integer>();
            for(int i = 1; i <= 31; i++){
                box1.addItem(i);
            }
            for(int l = 1; l <= 12; l++){
                box2.addItem(l);
            }
            for(int k = 2023; k <= 2100; k++){
                box3.addItem(k);
            }
            for(int i = 0; i<23; i++){
                hora1.addItem(i);
            }
            for(int i = 0; i<59;i++){
                hora2.addItem(i);
            }
            p1 = new JPanel();
            p1.setLayout(new FlowLayout(FlowLayout.LEFT));
            p1.add(new JLabel("Fecha de salida:"));
            p1.add(box1);
            p1.add(box2);
            p1.add(box3);
            add(p1,BorderLayout.WEST);
            p2 = new JPanel();
            p2.setLayout(new FlowLayout(FlowLayout.LEFT));
            p2.add(new JLabel("Cedula del titular:"));
            cedula = new JTextField(10);
            p2.add(cedula);
            add(p2, BorderLayout.WEST);
            p3 = new JPanel();
            p3.setLayout(new FlowLayout(FlowLayout.LEFT));
            p3.add(new JLabel("Hora de entrada:"));
            p3.add(hora1);
            p3.add(hora2);
            add(p3,BorderLayout.WEST);
            p4 = new JPanel();
            p4.setLayout(new FlowLayout(FlowLayout.CENTER));
            enviar = new JButton("Enviar check-out");
            cancelar = new JButton("Cancelar");
            p4.add(enviar);
            p4.add(cancelar);
            add(p4, BorderLayout.CENTER);
            setSize(400, 200);
            setVisible(true);
        }
    }    
}
