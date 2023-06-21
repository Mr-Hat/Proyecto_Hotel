import javax.swing.JButton; import javax.swing.JFrame;
import java.awt.event.ActionListener; import java.time.LocalDate;
import java.awt.event.ItemEvent; import java.awt.event.ItemListener;
import java.time.LocalTime; import java.awt.event.ActionEvent;
import java.io.IOException; import javax.swing.JOptionPane;
public class Controlador implements ActionListener, ItemListener{
    private Modelo modelo;
    private Vista vista;
    private Vista.Reportes reportes;
    private Vista.VentanaCancelacion cancelacion;
    private Vista.VentanaReserv reserva;
    private Vista.VentanaCheckIn checkIn;
    private Vista.VentanaCheckOut checkOut;
    private Vista.Exportar exportar;
    private Vista.VerReserva verReserva;
    private Vista.VerContabilidad verContabilidad;
    private int dia1Reporte, dia2Reporte, mes1Reporte, mes2Reporte, anio1Reporte, anio2Reporte,adultos, limit, ninos, dia1Reserva, dia2Reserva, mes1Reserva, mes2Reserva, anio1Reserva, anio2Reserva, hora1In, hora1Out, hora1Can, hora2In, hora2Out, hora2Can;
    private String tipo,cedula, cedulaIn, cedulaOut, cedulaCan;
    private LocalDate[] fechaReserva, fechaReportes;
    private LocalTime horaIn, horaOut, horaCan;
    private boolean a, b, c, d, e, f, g, h, i, j, k, l, fechaRes, fechaRep,dia1Rep, dia2Rep, mes1Rep, mes2Rep, anio1Rep, anio2Rep, adultosRes, ninosRes, tipoRes, horaCancel, cedulaCanFlag, horaInFlag, cedulaInFlag, cedulaOutFlag, cedulaResFlag, horaOutFlag, dia1Res, dia2Res, mes1Res, mes2Res, anio1Res, anio2Res;
    public Controlador(Modelo modelo, Vista vista){
        this.modelo = modelo;
        this.vista = vista;
        this.vista.boton1.addActionListener(this);
        this.vista.boton2.addActionListener(this);
        this.vista.boton3.addActionListener(this);
        this.vista.boton4.addActionListener(this);
        this.vista.boton5.addActionListener(this);
        this.vista.boton6.addActionListener(this);
    }
    public void iniciarVista(){
        vista.setTitle("Hotel mi Refugio");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setSize(300,400);
        vista.setVisible(true);
        Vista.Reportes rp = new Vista().new Reportes();
        this.reportes = rp;
        reportes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reportes.setVisible(false);
        reportes.setSize(550,300);
        reportes.opciones.addItemListener(this);
        reportes.box1.addItemListener(this);
        reportes.box2.addItemListener(this);
        reportes.box3.addItemListener(this);
        reportes.box4.addItemListener(this);
        reportes.box5.addItemListener(this);
        reportes.box6.addItemListener(this);
        reportes.enviar.addActionListener(this);
        reportes.enviar.setEnabled(false);
        reportes.cancelar.addActionListener(this);
        reportes.setVisible(false);
        Vista.VentanaReserv nr = new Vista().new VentanaReserv();
        this.reserva = nr;
        reserva.setSize(400,250);
        reserva.tipoHabitaciones.addItemListener(this);
        reserva.siguiente.addActionListener(this);
        reserva.siguiente.setEnabled(false);
        reserva.cancelar.addActionListener(this);
        reserva.tipoHabitaciones.addItemListener(this);
        reserva.fechin1.addItemListener(this);
        reserva.fechin2.addItemListener(this);
        reserva.fechin3.addItemListener(this);
        reserva.fechout1.addItemListener(this);
        reserva.fechout2.addItemListener(this);
        reserva.fechout3.addItemListener(this);
        reserva.setVisible(false);
        reserva.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Vista.VentanaCheckIn nci = new Vista().new VentanaCheckIn();
        this.checkIn = nci;
        checkIn.setSize(400,200);
        checkIn.enviar.addActionListener(this);
        checkIn.enviar.setEnabled(false);
        checkIn.cancelar.addActionListener(this);
        checkIn.hora1.addItemListener(this);
        checkIn.hora2.addItemListener(this);
        checkIn.setVisible(false);
        checkIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Vista.VentanaCheckOut nco = new Vista().new VentanaCheckOut();
        this.checkOut = nco;
        checkOut.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkOut.setSize(400,200);
        checkOut.enviar.addActionListener(this);
        checkOut.enviar.setEnabled(false);
        checkOut.cancelar.addActionListener(this);
        checkOut.hora1.addItemListener(this);
        checkOut.hora2.addItemListener(this);
        checkOut.setVisible(false);
        Vista.VentanaCancelacion nc = new Vista().new VentanaCancelacion();
        this.cancelacion = nc;
        cancelacion.setSize(300,400);
        cancelacion.enviar.addActionListener(this);
        cancelacion.enviar.setEnabled(false);
        cancelacion.cancelar.addActionListener(this);
        cancelacion.hora1.addItemListener(this);
        cancelacion.hora2.addItemListener(this);
        cancelacion.setVisible(false);
        cancelacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Vista.Exportar exp = new Vista().new Exportar();
        this.exportar = exp;
        exportar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        exportar.setSize(300, 400);
        exportar.reservaciones.addActionListener(this);
        exportar.contabilidad.addActionListener(this);
        exportar.verRes.addActionListener(this);
        exportar.verCont.addActionListener(this);
        exportar.verRes.setEnabled(false);
        exportar.verCont.setEnabled(false);
        exportar.setVisible(false);
    }
    public void iniciarModelo()throws IOException{
        modelo.inicializacion();
        modelo.precios();
    }

    public void actionPerformed(ActionEvent evento){
        if(evento.getSource() instanceof JButton){
            if(vista.boton1 == evento.getSource()){
                vista.setVisible(false);
                reserva.setVisible(true);
                cedula = reserva.tituReservacion.getText();
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
                if(reserva.siguiente == evento.getSource()){
                    reserva.siguiente.setEnabled(false);
                    if(!(dia1Reserva == 1 && dia2Reserva == 1 && mes1Reserva == 1 && mes2Reserva == 1 && anio1Reserva == 2023 && anio2Reserva == 2023 && adultos == 0 && ninos == 0 && tipo == "Individual")){
                        if(adultos + ninos < limit){
                            int num = adultos + ninos;
                            if(fechaRes == true && cedulaResFlag == true){
                                modelo.reserva(fechaReserva[0], fechaReserva[1], tipo, num, adultos, ninos, cedula);
                                reserva.siguiente.setEnabled(true);
                                JOptionPane.showMessageDialog(null,"Se ha reservado con exito.","Aviso",JOptionPane.PLAIN_MESSAGE);
                            }
                        }
                    }
                } 
                if(reserva.cancelar == evento.getSource()){
                    reserva.setVisible(false);
                    vista.setVisible(true);
                }
            }
            if(vista.boton2 == evento.getSource()){
                vista.setVisible(false);
                checkIn.setVisible(true);
                if(checkIn.enviar == evento.getSource()){
                    checkIn.enviar.setEnabled(false);
                    if(horaInFlag == true && cedulaInFlag == true){
                        modelo.checkIn(cedulaIn, horaIn);
                        checkIn.enviar.setEnabled(true);
                        JOptionPane.showMessageDialog(null,"Se ha hecho el check-in con exito.","Aviso",JOptionPane.PLAIN_MESSAGE);
                    } 
                } 
                if(hora1In != 0 && hora2In != 0){
                    horaIn = LocalTime.of(hora1In, hora2In);
                    horaInFlag = true;
                }
                cedulaIn = checkIn.cedula.getText();
                if(cedulaIn != null){
                    cedulaInFlag = true;
                }
            }
            if(checkIn.cancelar == evento.getSource()){
                checkIn.setVisible(false);
                vista.setVisible(true);
            }
            if(checkOut.cancelar == evento.getSource()){
                checkOut.setVisible(false);
                vista.setVisible(true);
            }
            if(cancelacion.cancelar == evento.getSource()){
                cancelacion.setVisible(false);
                vista.setVisible(true);
            }
            if(reportes.cancelar == evento.getSource()){
                reportes.setVisible(false);
                vista.setVisible(true);
            }
            if(exportar.cancelar == evento.getSource()){
                exportar.setVisible(false);
                vista.setVisible(true);
            }
            if(verContabilidad.volver == evento.getSource()){
                verContabilidad.setVisible(false);
                vista.setVisible(true);
            }
            if(verReserva.volver == evento.getSource()){
                verReserva.setVisible(false);
                vista.setVisible(true);
            }
            if(vista.boton3 == evento.getSource()){
                if(checkOut.enviar == evento.getSource()){
                checkOut.enviar.setEnabled(false);
                if(horaOutFlag == true && cedulaOutFlag == true){
                        modelo.checkOut(cedulaOut, horaOut);
                        checkOut.enviar.setEnabled(true);
                        JOptionPane.showMessageDialog(null,"Se ha hecho el check-out con exito.","Aviso",JOptionPane.PLAIN_MESSAGE);
                    } 
                } 
                if(hora1Out != 0 && hora2Out != 0){
                    horaOut = LocalTime.of(hora1Out, hora2Out);
                    horaOutFlag = true;
                }
                cedulaOut = checkOut.cedula.getText();
                if(cedulaOut != null){
                    cedulaOutFlag = true;
                }
            }if(vista.boton4 == evento.getSource()){
                if(hora1Can != 0 && hora2Can != 0){
                    horaCan = LocalTime.of(hora1Can, hora2Can);
                    horaCancel = true;
                }
                cedulaCan = cancelacion.cedula.getText();
                if(cedulaCan != null){
                    cedulaCanFlag = true;
                }
                if(horaCancel == true && cedulaCanFlag == true){
                    cancelacion.enviar.setEnabled(true);
                    if(cancelacion.enviar == evento.getSource()){
                        modelo.cancelacion(cedulaCan, horaCan);
                        JOptionPane.showMessageDialog(null, "Se ha Cancelado su reserva con exito", "Aviso", JOptionPane.PLAIN_MESSAGE);
                    } 
                } 
            }if(vista.boton5 == evento.getSource()){
                if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio2Rep == true && anio1Rep == true){
                    LocalDate fecha1Reportes = LocalDate.of(anio1Reporte, mes1Reporte, dia1Reporte);
                    LocalDate fecha2Reportes = LocalDate.of(anio2Reporte, mes2Reporte, dia2Reporte);
                    fechaReportes[0] = fecha1Reportes;
                    fechaReportes[1] = fecha2Reportes;
                    fechaRep = true;
                }
                if(a == true || b == true || c == true || d == true || e == true || f == true || g == true || h == true || i == true || j == true || k == true || l == true){
                    reportes.enviar.setEnabled(true);
                    if(reportes.enviar == evento.getSource()){
                        if(fechaRep == true){
                            if(a == true){
                                if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                                    modelo.reservasCanceladas(fechaReportes[0], fechaReportes[1]);
                                }
                            } else if(b == true){
                                if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                                    modelo.reservasEfectivas(fechaReportes[0], fechaReportes[1]);
                                }
                            } else if(c == true){
                                if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                                    modelo.personasAtendidas(fechaReportes[0], fechaReportes[1]);
                                }
                            } else if(d == true){
                                if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                                    modelo.ingresosCamasAdicionales(fechaReportes[0], fechaReportes[1]);
                                }
                            } else if(e == true){
                                if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                                    modelo.pisoMasUso(fechaReportes[0], fechaReportes[1]);
                                }
                            } else if(f == true){
                                if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                                    modelo.transferenciasTotales(fechaReportes[0], fechaReportes[1]);
                                }
                            } else if(g == true){
                                if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                                    modelo.ingresosCajas(fechaReportes[0], fechaReportes[1]);
                                }
                            } else if(h == true){
                                if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                                    modelo.porcentajeDiaria(fechaReportes[0], fechaReportes[1]);
                                }
                            } else if(i == true){
                                if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                                    modelo.promedioDiara(fechaReportes[0], fechaReportes[1]);
                                }
                            } else if(j == true){
                                if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                                    modelo.habitacionesOcupadas(fechaReportes[0], fechaReportes[1]);
                                }
                            } else if(k == true){
                                if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                                    modelo.habitacionesOcupadas(fechaReportes[0], fechaReportes[1]);
                                }
                            } else if(l == true){
                                if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio1Rep == true && anio2Rep == true){
                                    modelo.habitacionesLibres(fechaReportes[0], fechaReportes[1]);
                                }
                            } else{
                                JOptionPane.showMessageDialog(null, "Ingrese una opcion por favor", "ALERTA", JOptionPane.WARNING_MESSAGE);
                            }
                        } else{ JOptionPane.showMessageDialog(null, "Por favor ingrese una fecha", "ALERTA", JOptionPane.WARNING_MESSAGE);}
                    }
                }
            } if(vista.boton6 == evento.getSource()){
                vista.setVisible(false);
                exportar.setVisible(true);
            } else if(exportar.reservaciones == evento.getSource()){
                modelo.reservacionesOutCreate();
                exportar.verRes.setEnabled(true);
            } else if(exportar.contabilidad == evento.getSource()){
                modelo.contabilidadOutCreate();
                exportar.verCont.setEnabled(true);
            } else if(exportar.verRes == evento.getSource()){
                String textoReserva = modelo.getReservacionesOut();
                Vista.VerReserva verReser = new Vista().new VerReserva(textoReserva);
                verReserva = verReser;
                verReserva.setSize(350, 450);
            } else if(exportar.verCont == evento.getSource()){
                String textoCont = modelo.getContabilidadString();
                Vista.VerContabilidad verCont = new Vista().new VerContabilidad(textoCont);
                verContabilidad = verCont;
                verContabilidad.setSize(350,450);
            }
        }
    }
    public void itemStateChanged(ItemEvent evento){
        if(evento.getStateChange() == ItemEvent.SELECTED){
                if(evento.getSource() == reserva.fechin1){
                    dia1Reserva = (int)reserva.fechin1.getSelectedItem();
                } else if (evento.getSource() == reserva.tipoHabitaciones){
                    String tipoRaw = (String) reserva.tipoHabitaciones.getSelectedItem();
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
                } else if (evento.getSource() == reserva.fechin2){
                    mes1Reserva = (int) reserva.fechin2.getSelectedItem();
                } else if (evento.getSource() == reserva.fechin3){
                    anio1Reserva = (int) reserva.fechin3.getSelectedItem();
                } else if (evento.getSource() == reserva.fechout1){
                    dia2Reserva = (int) reserva.fechout1.getSelectedItem();
                } else if (evento.getSource() == reserva.fechout2){
                    mes2Reserva = (int) reserva.fechout2.getSelectedItem();
                } else if (evento.getSource() == reserva.fechout3){
                    anio2Reserva = (int) reserva.fechout3.getSelectedItem();
                } else if(evento.getSource() == reserva.adultos){
                    adultos = (int) reserva.adultos.getSelectedItem();
                } else if(evento.getSource() == reserva.ninos){
                    ninos = (int) reserva.ninos.getSelectedItem();
                }
            }
                if (evento.getSource() == cancelacion.hora1){
                    hora1Can = (int) cancelacion.hora1.getSelectedItem();
                } else if (evento.getSource() == cancelacion.hora2){
                    hora2Can = (int) cancelacion.hora2.getSelectedItem();
                }
                if (evento.getSource() == checkIn.hora1){
                    hora1Can = (int) checkIn.hora1.getSelectedItem();
                } else if (evento.getSource() == checkIn.hora2){
                    hora2Can = (int) checkIn.hora2.getSelectedItem();
                }
                if (evento.getSource() == checkOut.hora1){
                    hora1Out = (int) checkOut.hora1.getSelectedItem();
                } else if (evento.getSource() == checkOut.hora2){
                    hora2Out = (int) checkOut.hora2.getSelectedItem();
                }
                if(evento.getSource() == reportes.box1){
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
                    String opcionRaw = (String) reportes.opciones.getSelectedItem();
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