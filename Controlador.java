import javax.swing.JButton; import javax.swing.JFrame;
import javax.swing.JMenuItem; import javax.swing.JOptionPane;
import java.awt.event.ActionListener; import java.time.LocalDate;
import java.awt.event.ItemEvent; import java.awt.event.ItemListener;
import java.time.LocalTime; import java.awt.event.ActionEvent;
import java.io.IOException;
public class Controlador implements ActionListener, ItemListener{
    private Modelo modelo;
    private Vista vista;
    private Vista.Reportes reportes;
    private Vista.VentanaCancelacion cancelacion;
    private Vista.VentanaReserv reserva;
    private Vista.VentanaCheckIn checkIn;
    private Vista.VentanaCheckOut checkOut;
    private int dia1Reporte, dia2Reporte, mes1Reporte, mes2Reporte, anio1Reporte, anio2Reporte,adultos, limit, ninos, dia1Reserva, dia2Reserva, mes1Reserva, mes2Reserva, anio1Reserva, anio2Reserva, hora1In, hora1Out, hora1Can, hora2In, hora2Out, hora2Can;
    private String tipo;
    private String cedula;
    private String cedulaIn;
    private String cedulaOut;
    private String cedulaCan;
    private LocalDate[] fechaReserva;
    private LocalTime horaIn;
    private LocalTime horaOut;
    private LocalTime horaCan;
    private boolean a, b, c, d, e, f, g, h, i, j, k, l, fechaRes,dia1Rep, dia2Rep, mes1Rep, mes2Rep, anio1Rep, anio2Rep, adultosRes, ninosRes, tipoRes, horaCancel, cedulaCanFlag, horaInFlag, cedulaInFlag, cedulaOutFlag, cedulaResFlag, horaOutFlag, dia1Res, dia2Res, mes1Res, mes2Res, anio1Res, anio2Res;
    public Controlador(Modelo modelo, Vista vista){
        this.modelo = modelo;
        this.vista = vista;
        
        this.vista.boton1.addActionListener(this);
        this.vista.boton2.addActionListener(this);
        this.vista.boton3.addActionListener(this);
        this.vista.boton4.addActionListener(this);

        this.reportes.opciones.addItemListener(this);
        this.reportes.box1.addItemListener(this);
        this.reportes.box2.addItemListener(this);
        this.reportes.box3.addItemListener(this);
        this.reportes.box4.addItemListener(this);
        this.reportes.box5.addItemListener(this);
        this.reportes.box6.addItemListener(this);
        this.reportes.enviar.addActionListener(this);
        this.reportes.cancelar.addActionListener(this);

        this.cancelacion.enviar.addActionListener(this);
        this.cancelacion.cancelar.addActionListener(this);
        this.cancelacion.hora1.addItemListener(this);
        this.cancelacion.hora2.addItemListener(this);

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
        this.checkIn.hora1.addItemListener(this);
        this.checkIn.hora2.addItemListener(this);

        this.checkOut.enviar.addActionListener(this);
        this.checkOut.cancelar.addActionListener(this);
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
    public void iniciarModelo()throws IOException{
        modelo.inicializacion();
        modelo.precios();
    }

    public void actionPerformed(ActionEvent evento){
        String accion = evento.getActionCommand();
        if(evento.getSource() instanceof JMenuItem){
            if(vista.boton1 == evento.getSource()){
                Vista.VentanaReserv nr = new Vista().new VentanaReserv();
                this.reserva = nr;
                cedula = nr.tituReservacion.getText();
                if(cedula != null){
                    cedulaResFlag = true;
                }
                if(dia1Res == true && mes1Res == true && anio1Res == true && dia2Res == true && mes2Res == true && anio2Res == true){
                    LocalDate fechaReserva1 = LocalDate.of(anio1Reserva, mes1Reserva, dia1Reserva);
                    LocalDate fechaReserva2 = LocalDate.of(anio2Reserva, mes2Reserva, dia2Reserva);
                    fechaReserva[0] = fechaReserva1;
                    fechaReserva[1] = fechaReserva2;
                    fechaRes = true;
                }
            } else if(vista.boton2 == evento.getSource()){
                Vista.VentanaCheckIn nci = new Vista().new VentanaCheckIn();
                this.checkIn = nci;
                if(hora1In != 0 && hora2In != 0){
                    horaIn = LocalTime.of(hora1In, hora2In);
                    horaInFlag = true;
                }
                cedulaIn = nci.cedula.getText();
                if(cedulaIn != null){
                    cedulaInFlag = true;
                }
            } else if(vista.boton3 == evento.getSource()){
                Vista.VentanaCheckOut nco = new Vista().new VentanaCheckOut();
                this.checkOut = nco;
                if(hora1Out != 0 && hora2Out != 0){
                    horaOut = LocalTime.of(hora1Out, hora2Out);
                    horaOutFlag = true;
                }
                cedulaOut = nco.cedula.getText();
                if(cedulaOut != null){
                    cedulaOutFlag = true;
                }
            }else if(vista.boton4 == evento.getSource()){
                Vista.VentanaCancelacion nc = new Vista().new VentanaCancelacion();
                this.cancelacion = nc;
                if(hora1Can != 0 && hora2Can != 0){
                    horaCan = LocalTime.of(hora1Can, hora2Can);
                    horaCancel = true;
                }
                cedulaCan = nc.cedula.getText();
                if(cedulaCan != null){
                    cedulaCanFlag = true;
                }
            }   else if(vista.boton5 == evento.getSource()){
                Vista.Reportes rp = new Vista().new Reportes();
                this.reportes = rp;
            }
        }
        if(evento.getSource() instanceof JButton){
            if(reportes.enviar == evento.getSource()){

                if(a == true){
                    if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){

                    }
                } else if(b == true){
                    if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                        
                    }
                } else if(c == true){
                    if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                        
                    }
                } else if(d == true){
                    if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                        
                    }
                } else if(e == true){
                    if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                        
                    }
                } else if(f == true){
                    if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                        
                    }
                } else if(g == true){
                    if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                        
                    }
                } else if(h == true){
                    if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                        
                    }
                } else if(i == true){
                    if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                        
                    }
                } else if(j == true){
                    if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                        
                    }
                } else if(k == true){
                    if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                        
                    }
                } else if(l == true){
                    if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                        
                    }
                } else{
                    
                }
            }
            if("Enviar cancelacion".equalsIgnoreCase(accion)){
                JOptionPane.showMessageDialog(null,"Se ha Cancelado su reserva con exito.","Aviso", JOptionPane.PLAIN_MESSAGE);
            } else if("Enviar reserva".equalsIgnoreCase(accion)){
                if(tipoRes == true && fechaRes == true){
                    if(adultos + ninos < limit){
                        int num = adultos + ninos;
                        if(fechaRes == true && cedulaResFlag == true){
                            modelo.reserva(fechaReserva[0], fechaReserva[1], tipo, num, adultos, ninos, cedula);
                            JOptionPane.showMessageDialog(null,"Se ha reservado con exito.","Aviso",JOptionPane.PLAIN_MESSAGE);
                        }
                    } else{
                        JOptionPane.showMessageDialog(null, "Los habitantes son mayores que la capacidad total de la habitacion","WARNING", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"No ha rellenado todos los campos","ALERT",JOptionPane.WARNING_MESSAGE);
                }
            } else if("Enviar check-in".equalsIgnoreCase(accion)){
                if(horaInFlag == true && cedulaInFlag == true){
                    modelo.checkIn(cedulaIn, horaIn);
                    JOptionPane.showMessageDialog(null,"Se ha hecho el check-in con exito.","Aviso",JOptionPane.PLAIN_MESSAGE);
                } else{
                    JOptionPane.showMessageDialog(null,"No ha rellenado todos los campos","ALERT",JOptionPane.WARNING_MESSAGE);
                }
            } else if("Enviar check-out".equalsIgnoreCase(accion)){
                if(horaOutFlag == true && cedulaOutFlag == true){
                    modelo.checkOut(cedulaOut, horaOut);
                    JOptionPane.showMessageDialog(null,"Se ha hecho el check-out con exito.","Aviso",JOptionPane.PLAIN_MESSAGE);
                } else{
                    JOptionPane.showMessageDialog(null,"No ha rellenado todos los campos","ALERT",JOptionPane.WARNING_MESSAGE);
                }
            } else if("Enviar cancelacion".equalsIgnoreCase(accion)){
                if(horaCancel == true && cedulaCanFlag == true){
                    modelo.cancelacion(cedulaCan, horaCan);
                    JOptionPane.showMessageDialog(null, "Se ha Cancelado su reserva con exito", "Aviso", JOptionPane.PLAIN_MESSAGE);
                } else{
                    JOptionPane.showMessageDialog(null,"No ha rellenado todos los campos","ALERT",JOptionPane.WARNING_MESSAGE);
                }
            } else{
                JOptionPane.showMessageDialog(null, "Se ha cancelado la operacion", "Aviso", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
    public void itemStateChanged(ItemEvent evento){
        if(evento.getStateChange() == ItemEvent.SELECTED){
            if(evento.getSource() == reserva.fechin1){
                dia1Reserva = (int)reserva.fechin1.getSelectedItem();
                dia1Res = true;
            } else if (evento.getSource() == reserva.fechin2){
                mes1Reserva = (int)reserva.fechin2.getSelectedItem();
                mes1Res = true;
            } else if (evento.getSource() == reserva.fechin3){
                anio1Reserva = (int)reserva.fechin3.getSelectedItem();
                anio1Res = true;
            } else if (evento.getSource() == reserva.fechout1){
                dia2Reserva = (int)reserva.fechout1.getSelectedItem();
                dia2Res = true;
            } else if (evento.getSource() == reserva.fechout2){
                mes2Reserva = (int)reserva.fechout2.getSelectedItem();
                mes2Res = true;
            } else if (evento.getSource() == reserva.fechout3){
                anio2Reserva = (int) reserva.fechout3.getSelectedItem();
                anio2Res = true;
            } else if(evento.getSource() == reserva.adultos){
                adultos = (int)reserva.adultos.getSelectedItem();
                adultosRes = true;
            } else if(evento.getSource() == reserva.ninos){
                ninos = (int) reserva.ninos.getSelectedItem();
                ninosRes = true;
            } else if (evento.getSource() == cancelacion.hora1){
                hora1Can = (int) cancelacion.hora1.getSelectedItem();
            } else if (evento.getSource() == cancelacion.hora2){
                hora2Can = (int) cancelacion.hora2.getSelectedItem();
            } else if (evento.getSource() == checkIn.hora1){
                hora1Can = (int) checkIn.hora1.getSelectedItem();
            } else if (evento.getSource() == checkIn.hora2){
                hora2Can = (int) checkIn.hora2.getSelectedItem();
            } else if (evento.getSource() == checkOut.hora1){
                hora1Out = (int) checkOut.hora1.getSelectedItem();
            } else if (evento.getSource() == checkOut.hora2){
                hora2Out = (int) checkOut.hora2.getSelectedItem();
            } else if (evento.getSource() == reserva.tipoHabitaciones){
                String tipoRaw = (String) reserva.tipoHabitaciones.getSelectedItem();
                tipoRes = true;
                switch(tipoRaw){
                        case "Individual":
                            tipo = "INDIV";
                            limit = 1;
                            break;
                        case "Matrimonial":
                            tipo = "MATRI";
                            limit = 2;
                            break;
                        case "Doble":
                            tipo = "DOBLE";
                            limit = 3;
                            break;
                        case "Cuadruple":
                            tipo = "CUADR";
                            limit = 4;
                            break;
                        case "Suite":
                            tipo = "SUITE";
                            limit = 10;
                            break;
                    }
            } else if(evento.getSource() == reportes.box1){
                dia1Reporte = (int) reportes.box1.getSelectedItem();
                dia1Rep = true;
            } else if(evento.getSource() == reportes.box2){
                mes1Reporte = (int) reportes.box2.getSelectedItem();
                mes1Rep = true;
            } else if(evento.getSource() == reportes.box3){
                anio1Reporte = (int) reportes.box3.getSelectedItem();
                anio1Rep = true;
            } else if(evento.getSource() == reportes.box4){
                dia2Reporte = (int) reportes.box4.getSelectedItem();
                dia2Rep = true;
            } else if(evento.getSource() == reportes.box5){
                mes2Reporte = (int) reportes.box5.getSelectedItem();
                mes2Rep = true;
            } else if(evento.getSource() == reportes.box6){
                anio2Reporte = (int) reportes.box6.getSelectedItem();
                anio2Rep = true;
            } 
             else if(evento.getSource() == reportes.opciones){
                String opcionRaw = (String) reserva.tipoHabitaciones.getSelectedItem();
                switch(opcionRaw){
                    case "Numero de reservaciones canceladas":
                        a = true;
                        b = c = d = e = f = g = i = h = j = l = k = false;
                        break;
                    case "Numero de reservaciones efectivas":
                        a = c = d = e = f = g = i = h = j = l = k = false;
                        b = true;
                        break;
                    case "Adultos y ninos atendidos":
                        a = b = d = e = f = g = i = h = j = l = k = false;
                        c = true;
                        break;
                    case "Ingresos por concepto de camas adicionales":
                        d = true;
                        a = c = b = e = f = g = i = h = j = l = k = false;
                        break;
                    case "Piso con mayor uso":
                        e = true;
                        a = c = d = b = f = g = i = h = j = l = k = false;
                        break;
                    case "Numero de transacciones totales":
                        f = true;
                        a = c = d = e = b = g = i = h = j = l = k = false;
                        break;
                    case "Ingresos por uso de caja fuerte":
                        g = true;
                        a = c = d = e = f = b = i = h = j = l = k = false;
                        break;
                    case "Porcentaje de ocupacion diaria":
                        h = true;
                        a = c = d = e = f = g = i = b = j = l = k = false;
                        break;
                    case "Promedio del porcentaje de ocupacion diaria":
                        i = true;
                        a = c = d = e = f = g = b = h = j = l = k = false;
                        break;
                    case "Habitaciones ocupadas":
                        j = true;
                        a = c = d = e = f = g = i = h = b = l = k = false;
                        break;
                    case "Habitaciones reservadas":
                        k = true;
                        a = c = d = e = f = g = i = h = j = l = b = false;
                        break;
                    case "Habitaciones libres":
                        l = true;
                        a = c = d = e = f = g = i = h = j = b = k = false;
                        break;
                }
            }
        }
    }
}  
