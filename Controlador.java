import java.awt.event.ActionEvent; import javax.swing.Action;
import javax.swing.JButton; import javax.swing.JFrame;
import javax.swing.JMenuItem; import javax.swing.JOptionPane;
import java.awt.event.ActionListener; import java.time.LocalDate;
import java.time.LocalDateTime; import java.awt.event.ItemEvent;
import java.awt.event.ItemListener; import java.time.LocalTime;
import java.security.MessageDigest;
public class Controlador implements ActionListener, ItemListener{
    private Modelo modelo;
    private Vista vista;
    private Vista.VentanaCancelacion cancelacion;
    private Vista.VentanaReserv reserva;
    private Vista.VentanaCheckIn checkIn;
    private Vista.VentanaCheckOut checkOut;
    private int dia1Reserva, dia2Reserva, mes1Reserva, mes2Reserva, anio1Reserva, anio2Reserva, diaIn, mesIn, anioIn, diaOut, mesOut, anioOut, diaCan, mesCan, anioCan, hora1In, hora1Out, hora1Can, hora2In, hora2Out, hora2Can;
    private String tipo;
    private int[] habitacion;
    private String cedula;
    private String cedulaIn;
    private String cedulaOut;
    private String cedulaCan;
    private LocalDate[] fechaReserva;
    private LocalDateTime fechaOut;
    private LocalDateTime fechaCan;
    private LocalDateTime fechaIn;
    private LocalDate fechaInRaw;
    private LocalTime horaIn;
    private LocalDate fechaOutRaw;
    private LocalTime horaOut;
    private LocalDate fechaCanRaw;
    private LocalTime horaCan;

    public Controlador(Modelo modelo, Vista vista){
        this.modelo = modelo;
        this.vista = vista;
        
        this.vista.boton1.addActionListener(this);
        this.vista.boton2.addActionListener(this);
        this.vista.boton3.addActionListener(this);
        this.vista.boton4.addActionListener(this);

        this.cancelacion.enviar.addActionListener(this);
        this.cancelacion.cancelar.addActionListener(this);
        this.cancelacion.box1.addActionListener(this);
        this.cancelacion.box2.addActionListener(this);
        this.cancelacion.box3.addActionListener(this);
        this.cancelacion.hora1.addActionListener(this);
        this.cancelacion.hora2.addActionListener(this);

        this.reserva.tipoHabitaciones.addItemListener(this);
        this.reserva.siguiente.addActionListener(this);
        this.reserva.cancelar.addActionListener(this);
        this.reserva.tipoHabitaciones.addItemListener(this);
        this.reserva.fechin1.addItemListener(this);
        this.reserva.fechin2.addItemListener(this);
        this.reserva.fechin3.addItemListener(this);
        this.reserva.fechout1.addItemListener(this);
        this.reserva.fechout2.addItemListener(this);
        this.reserva.fechout3.addItemListener(this);

        this.checkIn.enviar.addActionListener(this);
        this.checkIn.cancelar.addActionListener(this);
        this.checkIn.box1.addItemListener(this);
        this.checkIn.box2.addItemListener(this);
        this.checkIn.box3.addItemListener(this);
        this.checkIn.hora1.addItemListener(this);
        this.checkIn.hora2.addItemListener(this);

        this.checkOut.enviar.addActionListener(this);
        this.checkOut.cancelar.addActionListener(this);
        this.checkOut.box1.addItemListener(this);
        this.checkOut.box2.addItemListener(this);
        this.checkOut.box3.addItemListener(this);
        this.checkOut.hora1.addItemListener(this);
        this.checkOut.hora2.addItemListener(this);
        
    }
    public void iniciarVista(){
        vista.setTitle("Hotel mi Refugio");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setSize(300,400);
        vista.setVisible(true);
    }

    public void actionPerformed(ActionEvent evento){
        String accion = evento.getActionCommand();
        if(evento.getSource() instanceof JMenuItem){
            if(vista.boton1 == evento.getSource()){
                Vista.VentanaReserv nr = new Vista().new VentanaReserv();
                this.reserva = nr;
                cedula = nr.tituReservacion.getText();
                if(dia1Reserva != 0 && dia2Reserva != 0 && mes1Reserva != 0 && anio1Reserva != 0 && anio2Reserva != 0){
                    LocalDate fechaReserva1 = LocalDate.of(anio1Reserva, mes1Reserva, dia1Reserva);
                    LocalDate fechaReserva2 = LocalDate.of(anio2Reserva, mes2Reserva, dia2Reserva);
                    fechaReserva[0] = fechaReserva1;
                    fechaReserva[1] = fechaReserva2;
                }
            } else if(vista.boton2 == evento.getSource()){
                Vista.VentanaCheckIn nci = new Vista().new VentanaCheckIn();
                this.checkIn = nci;
                if(diaIn != 0 && mesIn != 0 && anioIn != 0){
                    fechaInRaw = LocalDate.of(anioIn, mesIn, diaIn);
                }
                if(hora1In != 0 && hora2In != 0){
                    horaIn = LocalTime.of(hora1In, hora2In);
                }
                if(fechaInRaw != null && horaIn != null){
                    fechaIn = LocalDateTime.of(fechaInRaw, horaIn);
                }
                cedulaIn = nci.cedula.getText();
            } else if(vista.boton3 == evento.getSource()){
                Vista.VentanaCheckOut nco = new Vista().new VentanaCheckOut();
                this.checkOut = nco;
                if(diaOut != 0 && mesOut != 0 && anioOut != 0){
                    fechaOutRaw = LocalDate.of(anioOut, mesOut, diaOut);
                }
                if(hora1Out != 0 && hora2Out != 0){
                    horaOut = LocalTime.of(hora1Out, hora2Out);
                }
                if(fechaOutRaw != null && horaOut != null){
                    fechaOut = LocalDateTime.of(fechaOutRaw, horaOut);
                }
                cedulaOut = nco.cedula.getText();
            }else if(vista.boton4 == evento.getSource()){
                Vista.VentanaCancelacion nc = new Vista().new VentanaCancelacion();
                this.cancelacion = nc;
                if(diaCan != 0 && mesCan != 0 && anioCan != 0){
                    fechaCanRaw = LocalDate.of(anioCan, mesCan, diaCan);
                }
                if(hora1Can != 0 && hora2Can != 0){
                    horaCan = LocalTime.of(hora1Can, hora2Can);
                }
                if(fechaCanRaw != null && horaCan != null){
                    fechaCan = LocalDateTime.of(fechaCanRaw, horaCan);
                }
                cedulaCan = nc.cedula.getText();
            }
        }
        if(evento.getSource() instanceof JButton){
            if("Enviar cancelacion".equals(accion)){
                JOptionPane.showMessageDialog(null,"Se ha Cancelado su reserva con exito.","Aviso", JOptionPane.PLAIN_MESSAGE);
            } else if("Enviar reserva".equals(accion)){
                
                JOptionPane.showMessageDialog(null,"Se ha reservado con exito.","Aviso",JOptionPane.PLAIN_MESSAGE);
            } else if("Enviar check-in".equals(accion)){
                JOptionPane.showMessageDialog(null,"Se ha hecho el check-in con exito.","Aviso",JOptionPane.PLAIN_MESSAGE);
            } else if("Enviar check-out".equalsIgnoreCase(accion)){
                JOptionPane.showMessageDialog(null,"Se ha hecho el check-out con exito.","Aviso",JOptionPane.PLAIN_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Se ha cancelado la operacion", "Aviso", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
    public void itemStateChanged(ItemEvent evento){
        if(evento.getStateChange() == ItemEvent.SELECTED){
            if(evento.getSource() == reserva.fechin1){
                dia1Reserva = (int)reserva.fechin1.getSelectedItem();
            } else if (evento.getSource() == reserva.fechin2){
                dia2Reserva = (int)reserva.fechout1.getSelectedItem();
            } else if (evento.getSource() == reserva.fechin2){
                mes2Reserva = (int)reserva.fechout2.getSelectedItem();
            } else if (evento.getSource() == reserva.fechin2){
                anio2Reserva = (int)reserva.fechout3.getSelectedItem();
            } else if (evento.getSource() == reserva.fechin2){
                dia2Reserva = (int)reserva.fechout1.getSelectedItem();
            } else if (evento.getSource() == reserva.fechin2){
                mes1Reserva = (int)reserva.fechin2.getSelectedItem();
            } else if (evento.getSource() == reserva.fechin2){
                anio1Reserva = (int)reserva.fechin3.getSelectedItem();
            } else if (evento.getSource() == reserva.tipoHabitaciones){
                tipo = (String) reserva.tipoHabitaciones.getSelectedItem();
            } else if (evento.getSource() == cancelacion.box1){
                diaCan = (int) cancelacion.box1.getSelectedItem();
            } else if (evento.getSource() == cancelacion.box2){
                mesCan = (int) cancelacion.box2.getSelectedItem();
            } else if (evento.getSource() == cancelacion.box3){
                anioCan = (int) cancelacion.box3.getSelectedItem();
            } else if (evento.getSource() == cancelacion.hora1){
                hora1Can = (int) cancelacion.hora1.getSelectedItem();
            } else if (evento.getSource() == cancelacion.hora2){
                hora2Can = (int) cancelacion.hora2.getSelectedItem();
            } else if (evento.getSource() == checkIn.box1){
                diaIn = (int) checkIn.box1.getSelectedItem();
            } else if (evento.getSource() == checkIn.box2){
                mesCan = (int) checkIn.box2.getSelectedItem();
            } else if (evento.getSource() == checkIn.box3){
                anioCan = (int) checkIn.box3.getSelectedItem();
            } else if (evento.getSource() == checkIn.hora1){
                hora1Can = (int) checkIn.hora1.getSelectedItem();
            } else if (evento.getSource() == checkIn.hora2){
                hora2Can = (int) checkIn.hora2.getSelectedItem();
            } else if (evento.getSource() == checkOut.box1){
                diaOut = (int) checkOut.box1.getSelectedItem();
            } else if (evento.getSource() == checkOut.box2){
                mesOut = (int) checkOut.box2.getSelectedItem();
            } else if (evento.getSource() == checkOut.box3){
                anioOut = (int) checkOut.box3.getSelectedItem();
            } else if (evento.getSource() == checkOut.hora1){
                hora1Out = (int) checkOut.hora1.getSelectedItem();
            } else if (evento.getSource() == checkOut.hora2){
                hora2Out = (int) checkOut.hora2.getSelectedItem();
            } else if (evento.getSource() == reserva.tipoHabitaciones){
                String tipoRaw = (String) reserva.tipoHabitaciones.getSelectedItem();
                switch(tipoRaw){
                        case "Individual":
                            tipo = "INDIV";
                            break;
                        case "Matrimonial":
                            tipo = "MATRI";
                            break;
                        case "Doble":
                            tipo = "DOBLE";
                            break;
                        case "Cuadruple":
                            tipo = "CUADR";
                            break;
                        case "Suite":
                            tipo = "SUITE";
                            break;
                    }
            }
        }
    }
}  
