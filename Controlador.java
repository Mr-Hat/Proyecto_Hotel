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
    private boolean ex, res, can, out, in, rep,a, b, c, d, e, f, g, h, i, j, k, l, fechaRes, fechaRep,dia1Rep, dia2Rep, mes1Rep, mes2Rep, anio1Rep, anio2Rep, adultosRes, ninosRes, tipoRes, horaCancel, cedulaCanFlag, horaInFlag, cedulaInFlag, cedulaOutFlag, cedulaResFlag, horaOutFlag, dia1Res, dia2Res, mes1Res, mes2Res, anio1Res, anio2Res;
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
    }
    public void iniciarModelo()throws IOException{
        modelo.inicializacion();
        modelo.precios();
    }

    public void actionPerformed(ActionEvent evento){
        if(evento.getSource() instanceof JButton){
            if(vista.boton1 == evento.getSource()){
                res = true;
                ex = rep = in = out = can = false;
                Vista.VentanaReserv nr = new Vista().new VentanaReserv();
                this.reserva = nr;
                this.reserva.setSize(300,400);
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
                if(this.reserva.siguiente == evento.getSource()){
                    this.reserva.siguiente.setEnabled(false);
                    if(!(dia1Reserva == 1 && dia2Reserva == 1 && mes1Reserva == 1 && mes2Reserva == 1 && anio1Reserva == 2023 && anio2Reserva == 2023 && adultos == 0 && ninos == 0 && tipo == "Individual")){
                        if(adultos + ninos < limit){
                            int num = adultos + ninos;
                            if(fechaRes == true && cedulaResFlag == true){
                                modelo.reserva(fechaReserva[0], fechaReserva[1], tipo, num, adultos, ninos, cedula);
                                this.reserva.siguiente.setEnabled(true);
                                JOptionPane.showMessageDialog(null,"Se ha reservado con exito.","Aviso",JOptionPane.PLAIN_MESSAGE);
                            }
                        }
                    }
                } 
            }
            else if(vista.boton2 == evento.getSource()){
                in = true;
                ex =res = rep = out = can = false;
                Vista.VentanaCheckIn nci = new Vista().new VentanaCheckIn();
                this.checkIn = nci;
                this.checkIn.setSize(300,400);
                this.checkIn.enviar.addActionListener(this);
                this.checkIn.cancelar.addActionListener(this);
                this.checkIn.hora1.addItemListener(this);
                this.checkIn.hora2.addItemListener(this);
                if(this.checkIn.enviar == evento.getSource()){
                    this.checkIn.enviar.setEnabled(false);
                    if(horaInFlag == true && cedulaInFlag == true){
                        modelo.checkIn(cedulaIn, horaIn);
                        this.checkIn.enviar.setEnabled(true);
                        JOptionPane.showMessageDialog(null,"Se ha hecho el check-in con exito.","Aviso",JOptionPane.PLAIN_MESSAGE);
                    } 
                } 
                if(hora1In != 0 && hora2In != 0){
                    horaIn = LocalTime.of(hora1In, hora2In);
                    horaInFlag = true;
                }
                cedulaIn = nci.cedula.getText();
                if(cedulaIn != null){
                    cedulaInFlag = true;
                }
            } else if(vista.boton3 == evento.getSource()){
                out = true;
                ex = res = in = rep = can = false;
                Vista.VentanaCheckOut nco = new Vista().new VentanaCheckOut();
                this.checkOut = nco;
                this.checkOut.setSize(300,400);
                this.checkOut.enviar.addActionListener(this);
                this.checkOut.cancelar.addActionListener(this);
                this.checkOut.hora1.addItemListener(this);
                this.checkOut.hora2.addItemListener(this);
                if(this.checkOut.enviar == evento.getSource()){
                this.checkOut.enviar.setEnabled(false);
                if(horaOutFlag == true && cedulaOutFlag == true){
                        modelo.checkOut(cedulaOut, horaOut);
                        this.checkOut.enviar.setEnabled(true);
                        JOptionPane.showMessageDialog(null,"Se ha hecho el check-out con exito.","Aviso",JOptionPane.PLAIN_MESSAGE);
                    } 
                } 
                if(hora1Out != 0 && hora2Out != 0){
                    horaOut = LocalTime.of(hora1Out, hora2Out);
                    horaOutFlag = true;
                }
                cedulaOut = nco.cedula.getText();
                if(cedulaOut != null){
                    cedulaOutFlag = true;
                }
            }else if(vista.boton4 == evento.getSource()){
                can = true;
                ex = res = in = out = rep = false;
                Vista.VentanaCancelacion nc = new Vista().new VentanaCancelacion();
                this.cancelacion = nc;
                this.cancelacion.setSize(300,400);
                this.cancelacion.enviar.addActionListener(this);
                this.cancelacion.cancelar.addActionListener(this);
                this.cancelacion.hora1.addItemListener(this);
                this.cancelacion.hora2.addItemListener(this);
                if(hora1Can != 0 && hora2Can != 0){
                    horaCan = LocalTime.of(hora1Can, hora2Can);
                    horaCancel = true;
                }
                cedulaCan = nc.cedula.getText();
                if(cedulaCan != null){
                    cedulaCanFlag = true;
                }
                if(this.cancelacion.enviar == evento.getSource()){
                    this.cancelacion.enviar.setEnabled(false);
                    if(horaCancel == true && cedulaCanFlag == true){
                        modelo.cancelacion(cedulaCan, horaCan);
                        this.cancelacion.enviar.setEnabled(true);
                        JOptionPane.showMessageDialog(null, "Se ha Cancelado su reserva con exito", "Aviso", JOptionPane.PLAIN_MESSAGE);
                    } 
                } 
            }else if(vista.boton5 == evento.getSource()){
                rep = true;
                ex = res = in = out = can = false;
                Vista.Reportes rp = new Vista().new Reportes();
                this.reportes = rp;
                this.reportes.setSize(300,400);
                this.reportes.opciones.addItemListener(this);
                this.reportes.box1.addItemListener(this);
                this.reportes.box2.addItemListener(this);
                this.reportes.box3.addItemListener(this);
                this.reportes.box4.addItemListener(this);
                this.reportes.box5.addItemListener(this);
                this.reportes.box6.addItemListener(this);
                this.reportes.enviar.addActionListener(this);
                this.reportes.cancelar.addActionListener(this);
                if(dia1Rep == true && dia2Rep == true && mes1Rep == true && mes2Rep == true && anio2Rep == true && anio1Rep == true){
                    LocalDate fecha1Reportes = LocalDate.of(anio1Reporte, mes1Reporte, dia1Reporte);
                    LocalDate fecha2Reportes = LocalDate.of(anio2Reporte, mes2Reporte, dia2Reporte);
                    fechaReportes[0] = fecha1Reportes;
                    fechaReportes[1] = fecha2Reportes;
                    fechaRep = true;
                }
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
            } else if(vista.boton6 == evento.getSource()){
                ex = true;
                res = in = out = rep = false;
                Vista.Exportar exp = new Vista().new Exportar();
                this.exportar = exp;
                this.exportar.setSize(300, 400);
                this.exportar.reservaciones.addActionListener(this);
                this.exportar.contabilidad.addActionListener(this);
                this.exportar.verRes.addActionListener(this);
                this.exportar.verCont.addActionListener(this);
                this.exportar.verRes.setEnabled(false);
                this.exportar.verCont.setEnabled(false);
            } else if(exportar.reservaciones == evento.getSource()){
                modelo.reservacionesOutCreate();
                this.exportar.verRes.setEnabled(true);
            } else if(exportar.contabilidad == evento.getSource()){
                modelo.contabilidadOutCreate();
                this.exportar.verCont.setEnabled(true);
            } else if(exportar.verRes == evento.getSource()){
                String textoReserva = modelo.getReservacionesOut();
                Vista.VerReserva verReser = new Vista().new VerReserva(textoReserva);
                this.verReserva = verReser;
                this.verReserva.setSize(350, 450);
            } else if(exportar.verCont == evento.getSource()){
                String textoCont = modelo.getContabilidadString();
                Vista.VerContabilidad verCont = new Vista().new VerContabilidad(textoCont);
                this.verContabilidad = verCont;
                this.verContabilidad.setSize(350,450);
            }
        } 
    }
    public void itemStateChanged(ItemEvent evento){
        if(evento.getStateChange() == ItemEvent.SELECTED){
            if(res == true){
                if(evento.getSource() == this.reserva.fechin1){
                    dia1Reserva = (int)reserva.fechin1.getSelectedItem();
                } else if (evento.getSource() == this.reserva.tipoHabitaciones){
                    String tipoRaw = (String) this.reserva.tipoHabitaciones.getSelectedItem();
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
                } else if (evento.getSource() == this.reserva.fechin2){
                    mes1Reserva = (int) this.reserva.fechin2.getSelectedItem();
                } else if (evento.getSource() == this.reserva.fechin3){
                    anio1Reserva = (int) this.reserva.fechin3.getSelectedItem();
                } else if (evento.getSource() == this.reserva.fechout1){
                    dia2Reserva = (int) this.reserva.fechout1.getSelectedItem();
                } else if (evento.getSource() == this.reserva.fechout2){
                    mes2Reserva = (int) this.reserva.fechout2.getSelectedItem();
                } else if (evento.getSource() == this.reserva.fechout3){
                    anio2Reserva = (int) this.reserva.fechout3.getSelectedItem();
                } else if(evento.getSource() == this.reserva.adultos){
                    adultos = (int) this.reserva.adultos.getSelectedItem();
                } else if(evento.getSource() == this.reserva.ninos){
                    ninos = (int) this.reserva.ninos.getSelectedItem();
                }
            }
            if(can == true){

                if (evento.getSource() == this.cancelacion.hora1){
                    hora1Can = (int) this.cancelacion.hora1.getSelectedItem();
                } else if (evento.getSource() == this.cancelacion.hora2){
                    hora2Can = (int) this.cancelacion.hora2.getSelectedItem();
                }
            }
            if(in == true){
                if (evento.getSource() == this.checkIn.hora1){
                    hora1Can = (int) this.checkIn.hora1.getSelectedItem();
                } else if (evento.getSource() == this.checkIn.hora2){
                    hora2Can = (int) this.checkIn.hora2.getSelectedItem();
                }
            }
            if(out == true){
                if (evento.getSource() == this.checkOut.hora1){
                    hora1Out = (int) this.checkOut.hora1.getSelectedItem();
                } else if (evento.getSource() == this.checkOut.hora2){
                    hora2Out = (int) this.checkOut.hora2.getSelectedItem();
                }
            }
            if(rep == true){

                if(evento.getSource() == this.reportes.box1){
                    dia1Reporte = (int) this.reportes.box1.getSelectedItem();
                    dia1Rep = true;
                } else if(evento.getSource() == this.reportes.box2){
                    mes1Reporte = (int) this.reportes.box2.getSelectedItem();
                    mes1Rep = true;
                } else if(evento.getSource() == this.reportes.box3){
                    anio1Reporte = (int) this.reportes.box3.getSelectedItem();
                    anio1Rep = true;
                } else if(evento.getSource() == this.reportes.box4){
                    dia2Reporte = (int) this.reportes.box4.getSelectedItem();
                    dia2Rep = true;
                } else if(evento.getSource() == this.reportes.box5){
                    mes2Reporte = (int) this.reportes.box5.getSelectedItem();
                    mes2Rep = true;
                } else if(evento.getSource() == this.reportes.box6){
                    anio2Reporte = (int) this.reportes.box6.getSelectedItem();
                    anio2Rep = true;
                } 
                else if(evento.getSource() == this.reportes.opciones){
                    String opcionRaw = (String) this.reportes.opciones.getSelectedItem();
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
    }