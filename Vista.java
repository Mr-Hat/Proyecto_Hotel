import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Vista extends JFrame{
    private JMenuBar barra = new JMenuBar();
    private JMenuItem boton1,boton2,boton3,boton4;
    private JMenu menu = new JMenu("Transaccion");
    public Vista(){
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
        setSize(300,60);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }

    class VentanaCancelacion extends JFrame{
        private Container panel;
        private JPanel p1,p2,p3;
        private JComboBox<Integer> box1,box2,box3;
        private JButton enviar, cancelar;
        private JTextField cedula;
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
            enviar = new JButton("Enviar");
            cancelar = new JButton("Cancelar");
            enviar.addActionListener(new OyenteBoton2());
            cancelar.addActionListener(new OyenteBoton2());
            p3 = new JPanel();
            p3.setLayout(new FlowLayout(FlowLayout.CENTER));
            p3.add(enviar);
            p3.add(cancelar);
            add(p3, BorderLayout.CENTER);
            setSize(350, 150);
            setVisible(true);
        }
    }

    class VentanaReserv extends JFrame {
        private Container panel;
        private JPanel p1 = new JPanel();
        private JPanel p2 = new JPanel();
        private JPanel p3 = new JPanel();
        private JPanel p4 = new JPanel();
        private JPanel p5 = new JPanel();
        private JPanel p6 = new JPanel();
        private JTextField tituReservacion; 
        private JPanel pOption = new JPanel();
        private JComboBox<Integer> caja1 = new JComboBox<Integer>();
        private JComboBox<Integer> caja2 = new JComboBox<Integer>();
        private JComboBox<Integer> caja3 = new JComboBox<Integer>();
        private JComboBox<Integer> fechin1 = new JComboBox<Integer>();
        private JComboBox<Integer> fechin2 = new JComboBox<Integer>();
        private JComboBox<Integer> fechin3 = new JComboBox<Integer>();
        private JComboBox<Integer> fechout1 = new JComboBox<Integer>();
        private JComboBox<Integer> fechout2 = new JComboBox<Integer>();
        private JComboBox<Integer> fechout3 = new JComboBox<Integer>();
        private JComboBox<String> tipoHabitaciones = new JComboBox<String>();
        private JComboBox<Integer> CantDias = new JComboBox<Integer>();
        private JButton Siguiente;
        private JButton Cancelar;
        public VentanaReserv (){
            super("Hoteleria Mi Refugio");
            panel = getContentPane();
            panel.setLayout(new GridLayout(7,1));
            for(int i = 1 ;i <=31 ; i++){
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
            for(int l = 1; l <= 30 ; l++){
                CantDias.addItem(l);
            }
            tipoHabitaciones.addItem("Individual");
            tipoHabitaciones.addItem("Matrimonial");
            tipoHabitaciones.addItem("Doble");
            tipoHabitaciones.addItem("Cuadruple");
            tipoHabitaciones.addItem("Suite");
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
            tituReservacion = new JTextField(9);
            p2.add(tituReservacion);
            p2.setLayout(new FlowLayout(FlowLayout.LEFT));
            add(p2,BorderLayout.CENTER);
            p3.add(new JLabel("Tipo de habitacion:"));
            p3.add(tipoHabitaciones);
            tipoHabitaciones.addItemListener(new OyenteItem());
            p3.setLayout(new FlowLayout(FlowLayout.LEFT));
            add(p3, BorderLayout.CENTER);
            p4.add(new JLabel("Fecha de entrada:"));
            p4.add(fechin1);
            p4.add(fechin2);
            p4.add(fechin3);
            fechin1.addItemListener(new OyenteItem());
            fechin2.addItemListener(new OyenteItem());
            fechin3.addItemListener(new OyenteItem());
            p4.setLayout(new FlowLayout(FlowLayout.LEFT));
            add(p4, BorderLayout.CENTER);
            p5.add(new JLabel("Fecha de salida:"));
            p5.add(fechout1);
            p5.add(fechout2);
            p5.add(fechout3);
            fechout1.addItemListener(new OyenteItem());
            fechout2.addItemListener(new OyenteItem());
            fechout3.addItemListener(new OyenteItem());
            p5.setLayout(new FlowLayout(FlowLayout.LEFT));
            add(p5, BorderLayout.CENTER);
            p6.add(new JLabel("Cantidad de dias de estadia en el hotel:"));
            p6.add(CantDias);
            CantDias.addItemListener(new OyenteItem());
            p6.setLayout(new FlowLayout(FlowLayout.LEFT));
            add(p6, BorderLayout.CENTER);
            Siguiente = new JButton("Enviar");
            Cancelar = new JButton("Cancelar");
            Siguiente.addActionListener(new OyenteBoton1());
            Cancelar.addActionListener(new OyenteBoton1());
            pOption.add(Siguiente);
            pOption.add(Cancelar);
            pOption.setLayout(new FlowLayout(FlowLayout.CENTER));
            add(pOption,BorderLayout.CENTER);
            setSize(400, 250);
            setVisible(true);
        }
    }

    class VentanaCheckIn extends JFrame{
        private Container contenedor;
        private JComboBox<Integer> box1, box2, box3;
        private JPanel p1,p2,p3,p4;
        private JTextField cedula,nHabitacion;
        private JButton enviar, cancelar;
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
            cedula = new JTextField(10);
            p2.add(cedula);
            add(p2, BorderLayout.WEST);
            p3 = new JPanel();
            p3.setLayout(new FlowLayout(FlowLayout.LEFT));
            p3.add(new JLabel("Numero de habitacion:"));
            nHabitacion = new JTextField(10);
            p3.add(nHabitacion);
            add(p3,BorderLayout.WEST);
            p4 = new JPanel();
            p4.setLayout(new FlowLayout(FlowLayout.CENTER));
            enviar = new JButton("Enviar");
            cancelar = new JButton("Cancelar");
            enviar.addActionListener(new OyenteBoton3());
            cancelar.addActionListener(new OyenteBoton3());
            p4.add(enviar);
            p4.add(cancelar);
            add(p4, BorderLayout.CENTER);
            setSize(400, 200);
            setVisible(true);
        }
    }

    class VentanaCheckOut extends JFrame{
        private Container contenedor;
        private JComboBox<Integer> box1, box2, box3;
        private JPanel p1,p2,p3,p4;
        private JTextField cedula,monto;
        private JButton enviar, cancelar;
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
            p3.add(new JLabel("Monto a cancelar:"));
            monto = new JTextField(10);
            p3.add(monto);
            add(p3,BorderLayout.WEST);
            p4 = new JPanel();
            p4.setLayout(new FlowLayout(FlowLayout.CENTER));
            enviar = new JButton("Enviar");
            cancelar = new JButton("Cancelar");
            enviar.addActionListener(new OyenteBoton4());
            cancelar.addActionListener(new OyenteBoton4()); 
            p4.add(enviar);
            p4.add(cancelar);
            add(p4, BorderLayout.CENTER);
            setSize(400, 200);
            setVisible(true);
        }
    }    

    public static void main(String[] args){
        Vista nm = new Vista();
    }

    class OyenteItem implements ItemListener{
        public void itemStateChanged(ItemEvent evento){
        }
    }

    class OyenteBoton1 implements ActionListener{
        public void actionPerformed(ActionEvent evento){
            String accion = evento.getActionCommand();
            if(evento.getSource() instanceof JButton){
                if("Enviar".equals(accion)){
                    JOptionPane.showMessageDialog(null,"Se ha reservado con exito.","Aviso",JOptionPane.PLAIN_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"Se ha cancelado su reservacion.","Aviso",JOptionPane.PLAIN_MESSAGE);
                }
            }
        }    
    }

    class OyenteBoton2 implements ActionListener{
        public void actionPerformed(ActionEvent evento){
            String accion = evento.getActionCommand();
            if(evento.getSource() instanceof JButton){
                if("Enviar".equals(accion)){
                    JOptionPane.showMessageDialog(null,"Se ha Cancelado su reserva con exito.","Aviso", JOptionPane.PLAIN_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"No se ha cancelado su reservacion.", "Aviso", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
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

    class OyenteBoton3 implements ActionListener{
        public void actionPerformed(ActionEvent evento){
            String accion = evento.getActionCommand();
            if(evento.getSource() instanceof JButton){
                if("Enviar".equals(accion)){
                    JOptionPane.showMessageDialog(null,"Usted ha hecho un check-in.","Aviso", JOptionPane.PLAIN_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"Usted ha cancelado su check-in.", "Aviso", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }

    class OyenteBoton4 implements ActionListener{
        public void actionPerformed(ActionEvent evento){
            String accion = evento.getActionCommand();
            if(evento.getSource() instanceof JButton){
                if("Enviar".equals(accion)){
                    JOptionPane.showMessageDialog(null,"Usted ha hecho un check-out.","Aviso", JOptionPane.PLAIN_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"Usted ha cancelado su check-out.", "Aviso", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }
}
