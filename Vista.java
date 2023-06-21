import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame{
    private JMenuBar barra = new JMenuBar();
    public JMenuItem boton1,boton2,boton3,boton4,boton5;
    private JMenu menu = new JMenu("Transaccion");
    public Vista(){
        super("Menu Hotel");
        setJMenuBar(barra);
        menu.setMnemonic('o');
        barra.add(menu);
        boton1 = new JMenuItem("Reservaciones",'r');
        boton2 = new JMenuItem("Check-in",'i');
        boton3 = new JMenuItem("Check-out",'o');
        boton4 = new JMenuItem("Cancelacion",'C');
        boton5 = new JMenuItem("Reportes", 'x');
        menu.add(boton1);
        menu.add(boton2);
        menu.add(boton3);
        menu.add(boton4);
        menu.add(boton5);
    }
    public class Reportes extends JFrame{
        private Container panel;
        private JPanel p1, p2, p3, p4;
        public JButton enviar, cancelar;
        public JComboBox<Integer> box1 = new JComboBox<>();
        public JComboBox<Integer> box2 = new JComboBox<>();
        public JComboBox<Integer> box3 = new JComboBox<>();
        public JComboBox<Integer> box4 = new JComboBox<>();
        public JComboBox<Integer> box5 = new JComboBox<>();
        public JComboBox<Integer> box6 = new JComboBox<>();
        public JComboBox<String> opciones = new JComboBox<>();
        public Reportes(){
            super("Reportes");
            panel = getContentPane();
            panel.setLayout(new GridLayout(3, 1));
            for(int i = 1; i<= 31; i++){
                box1.addItem(i);
                box4.addItem(i);
            }
            for(int i = 1; i <= 12; i++){
                box2.addItem(i);
                box5.addItem(i);
            }
            for(int i = 2023; i<= 2100; i++){
                box3.addItem(i);
                box6.addItem(i);
            }
            p1 = new JPanel();
            p1.setLayout(new FlowLayout(FlowLayout.LEFT));
            opciones.addItem("Numero de reservaciones canceladas");
            opciones.addItem("Numero de reservaciones efectivas");
            opciones.addItem("Adultos y ninos atendidos");
            opciones.addItem("Ingresos por concepto de camas adicionales");
            opciones.addItem("Piso con mayor uso");
            opciones.addItem("Numero de transacciones totales");
            opciones.addItem("Ingresos por uso de caja fuerte");
            opciones.addItem("Porcentaje de ocupacion diaria");
            opciones.addItem("Promedio del porcentaje de ocupacion diaria");
            opciones.addItem("Habitaciones ocupadas");
            opciones.addItem("Habitaciones reservadas");
            opciones.addItem("Habitaciones libres");
            p1.add(opciones, BorderLayout.WEST);
            p2 = new JPanel();
            p2.setLayout(new FlowLayout(FlowLayout.LEFT));
            p2.add(new JLabel("Fecha de inicio"));
            p2.add(box1);
            p2.add(box2);
            p2.add(box3);
            p3 = new JPanel();
            p3.setLayout(new FlowLayout(FlowLayout.LEFT));
            p3.add(new JLabel("Fecha de Fin"));
            p3.add(box4);
            p3.add(box5);
            p3.add(box6);
            p4 = new JPanel();
            p4.setLayout(new FlowLayout(FlowLayout.CENTER));
            enviar = new JButton("Generar Reporte");
            cancelar = new JButton("Cancelar Operacion");
            add(p1, BorderLayout.WEST);
            add(p2, BorderLayout.WEST);
            add(p3, BorderLayout.WEST);
            add(p4, BorderLayout.CENTER);
        }
    }
    public class VentanaCancelacion extends JFrame{
        private Container panel;
        private JPanel p2,p3,p4;
        public JComboBox<Integer>hora1, hora2;
        public JButton enviar, cancelar;
        public JTextField cedula;
        public VentanaCancelacion(){
            super("Cancelacion");
            panel = getContentPane();
            panel.setLayout(new GridLayout(3,1));
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
        private boolean limite;
        private JPanel p2 = new JPanel();
        private JPanel p3 = new JPanel();
        private JPanel p4 = new JPanel();
        private JPanel p5 = new JPanel();
        private JPanel p1 = new JPanel();
        public JTextField tituReservacion; 
        private JPanel pOption = new JPanel();
        public JComboBox<Integer> fechin1 = new JComboBox<Integer>();
        public JComboBox<Integer> fechin2 = new JComboBox<Integer>();
        public JComboBox<Integer> fechin3 = new JComboBox<Integer>();
        public JComboBox<Integer> fechout1 = new JComboBox<Integer>();
        public JComboBox<Integer> fechout2 = new JComboBox<Integer>();
        public JComboBox<Integer> fechout3 = new JComboBox<Integer>();
        public JComboBox<Integer> adultos = new JComboBox<Integer>();
        public JComboBox<Integer> ninos = new JComboBox<Integer>();
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
            for(int i = 0; i < 11; i++){
                adultos.addItem(i);
            }
            for(int i = 0; i < 10; i++){
                ninos.addItem(i);
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
            p1.add(new JLabel("Ingrese cuantos adultos y cuantos ninos van a ingresar"));
            p1.add(adultos);
            p1.add(ninos);
            p1.setLayout(new FlowLayout(FlowLayout.LEFT));
            add(p1,BorderLayout.CENTER);
            siguiente = new JButton("Enviar reserva");
            cancelar = new JButton("Cancelar");
            if(limite == false){
                siguiente.setEnabled(false);
            } else{
                siguiente.setEnabled(true);
            }
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
        public JComboBox<Integer> hora1, hora2;
        private JPanel p2,p3,p4,p5;
        public JTextField cedula;
        public JButton enviar, cancelar;
        public VentanaCheckIn(){
            super("Check-in");
            contenedor = getContentPane();
            contenedor.setLayout(new GridLayout(4,1));
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
        public JComboBox<Integer> hora1, hora2;
        private JPanel p2,p3,p4;
        public JTextField cedula,monto;
        public JButton enviar, cancelar;
        public VentanaCheckOut(){
            super("Check-out");
            contenedor = getContentPane();
            contenedor.setLayout(new GridLayout(4,1));
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
