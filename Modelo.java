import java.time.LocalDate; import java.time.LocalTime;
import java.time.Period; import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime; import java.io.IOException;
import java.io.PrintWriter; import java.io.BufferedReader;
import java.io.BufferedWriter; import java.io.FileReader;
import java.io.FileWriter; import java.io.File;
import java.util.*;
public class Modelo{
    private float presupuesto;
    private LocalDate fecha;
    private int habitaciones;
    private int pisos;
    private String contabilidadString = "";
    private String reservacionesString = "";
    private Map<LocalDate, ArrayList<Integer>> transferencias = new HashMap<>();
    private Map<String, Float> precios = new HashMap<>();
    private Map<String, String> nombres = new HashMap<>();
    private Map<int[], String> numHabit = new HashMap<>();  
    private Map<LocalDate[], ArrayList<int[]>> habitacionesEnFecha = new HashMap<>();
    private Map<String, int[]> reservas = new HashMap<>();
    private Map<LocalDate[], Integer> ninosEnPeriodo = new HashMap<>();
    private Map<LocalDate[], Integer> adultosEnPeriodo = new HashMap<>();
    private Map<LocalDateTime, ArrayList<String>> cancelaciones = new HashMap<>();
    private Map<LocalDateTime, ArrayList<String>> checkIns = new HashMap<>(); 
    private Map<LocalDate, ArrayList<String>> reservacion = new HashMap<>();
    private Map<LocalDateTime, ArrayList<String>> checkOuts = new HashMap<>();
    private Map<LocalDate[], Integer> pisoMasUsado = new HashMap<>();
    private Map<LocalDate, String> contabilidad = new HashMap<>();
    private Map<String, Float> ingresos = new HashMap<>();
    private Map<String, Float> gastoUsuario = new HashMap<>();
    private Map<LocalDate[], ArrayList<String>> reportes = new HashMap<>();
    private Map<String, Integer> numPersonaEnHabit = new HashMap<>();
    private Map<LocalDate, Float> ingresosCamAdd = new HashMap<>();
    private Map<LocalDate, Float> ingresosCaja = new HashMap<>();
    private Map<LocalDate[], ArrayList<Float>> porcentajeDiaria = new HashMap<>();
    private Map<LocalDate[], Float> promedioDiaria = new HashMap<>();
    private Map<LocalDate, Integer> habitacionesLibresEnUnDia = new HashMap<>();
    private Map<LocalDate, Integer> habitacionesReservadasEnUnDia = new HashMap<>();
    private Map<LocalDate, Integer> habitacionesOcupadasEnUnDia = new HashMap<>();
    public void inicializacion() throws IOException {
        BufferedReader fin = null;
        numPersonaEnHabit.put("INDIV", 1);
        numPersonaEnHabit.put("MATRI", 2);
        numPersonaEnHabit.put("DOBLE", 3);
        numPersonaEnHabit.put("CUADR", 4);
        numPersonaEnHabit.put("SUITE", 10);
        try {
            fin = new BufferedReader(new FileReader("inicializar.in"));
            String primera = fin.readLine();
            int count = 0;
            int habitacion = 1;
            int piso = 1;
            while (primera!=null){
                switch (count){
                    case 0: presupuesto = Float.parseFloat(primera);
                    break;
                    case 1: String fecha1 = primera;
                    String[] fechasplit = fecha1.split(" ");
                    int dia = Integer.parseInt(fechasplit[0]);
                    int mes = Integer.parseInt(fechasplit[1]);
                    int anio = Integer.parseInt(fechasplit[2]);
                    fecha = LocalDate.of(anio, mes, dia);
                    break;
                    case 2:
                    String[] habiroom = primera.split(" ");
                    habitaciones = Integer.parseInt(habiroom[0]);
                    pisos = Integer.parseInt(habiroom[1]);
                    break;
                    default:
                    String habiLinea[] = primera.split(" ");
                    for(int i = 1; i <= habitaciones; i++){
                        int[] habipiso = new int[2];
                        habipiso[0] = piso;
                        habipiso[1] = habitacion;
                        numHabit.put(habipiso, habiLinea[i]);
                        habitacion ++;
                    }
                    if(piso == pisos){
                        piso = 1;
                    }
                    piso++;
                }
                primera = fin.readLine();
                count++;
            }
        } finally{if(fin != null) {fin.close();}}
    }
    public void precios() throws IOException{
        BufferedReader preciosIn = null;
        try{
            preciosIn = new BufferedReader(new FileReader("precios.in"));
            String linea = preciosIn.readLine();
            String[] tipos = linea.split(" ");
            int tipoHabitaciones = Integer.parseInt(tipos[0]);
            int servicios = Integer.parseInt(tipos[1]);
            int menu = Integer.parseInt(tipos[2]);
            for(int i = 0; i< tipoHabitaciones; i++){
                linea = preciosIn.readLine();
                String[] resultado = linea.split(" ");
                float valueHabit = Float.parseFloat(resultado[1]);
                precios.put(resultado[0], valueHabit);
            }
            for(int j = 0; j<servicios;j++){
                linea = preciosIn.readLine();
                String[] resultado = linea.split(" ", 3);
                float valueServ = Float.parseFloat(resultado[1]);
                precios.put(resultado[0], valueServ);
                nombres.put(resultado[0], resultado[2]);
            }
            for(int k = 0; k<menu; k++){
                linea = preciosIn.readLine();
                String[] resultado = linea.split(" ", 3);
                float valueMenu = Float.parseFloat(resultado[1]);
                precios.put(resultado[0], valueMenu);
                nombres.put(resultado[0], resultado[2]);
            }
        } finally{
            if(preciosIn != null) {preciosIn.close();}
        }
    }
    public void newDay(){
        fecha = fecha.plusDays(1);
        if(fecha.getDayOfMonth() == 15){
            presupuesto -= 25000;
            contabilidad.put(fecha, fecha + " |" + "         |   " + 25000+ "|       " + presupuesto + "|Nomina");
        } 
    }
    public void reserva(LocalDate fechLleg, LocalDate fechSalid, String tipo, int num, int numA, int numN, String cedula, String nombre){
            if(num >= 1 || num <= 10){
                List<int[]> totalHabit = getHabitacion(tipo);
                int[] habit = buscarHabitacionDisponible(totalHabit, fechLleg, fechSalid);
                LocalDate[] fechas = new LocalDate[2];
                fechas[0] = fechLleg;
                fechas[1] = fechSalid;
                transferencias.put(fecha, new ArrayList<Integer>());
                if(habit[0] == 0 && habit[1] == 0){
                    loop: for(int numPersona : numPersonaEnHabit.values()){
                        if(numPersona >= num){
                              switch(numPersona){
                                case 1:
                                List<int[]> indivTrans = getHabitacion("INDIV");
                                habit = buscarHabitacionDisponible(indivTrans, fechLleg, fechSalid);
                                transferencias.get(fecha).add(1);
                                break loop;
                                case 2:
                                List<int[]> matriTrans = getHabitacion("MATRI");
                                habit = buscarHabitacionDisponible(matriTrans, fechLleg, fechSalid);
                                transferencias.get(fecha).add(1);
                                break loop; 
                                case 3:
                                List<int[]> dobleTrans = getHabitacion("DOBLE");
                                habit = buscarHabitacionDisponible(dobleTrans, fechLleg, fechSalid);
                                transferencias.get(fecha).add(1);
                                break loop;
                                case 4:
                                List<int[]> cuadTrans = getHabitacion("CUADR");
                                habit = buscarHabitacionDisponible(cuadTrans, fechLleg, fechSalid);
                                transferencias.get(fecha).add(1);
                                break loop;
                                case 10:
                                List<int[]> suiteTrans = getHabitacion("SUITE");
                                habit = buscarHabitacionDisponible(suiteTrans, fechLleg, fechSalid);
                                transferencias.get(fecha).add(1);
                                break loop;
                            }
                        }
                    }
                }
                if(habit[0] == 0 && habit[1] == 0){
                    System.out.println("No se ha podido ejecutar la reserva");
                }
                else{
                    reservas.put(cedula, habit);
                    Period dif = Period.between(fechLleg, fechSalid);
                    reservacion.put(fecha, new ArrayList<String>());
                    reservacion.get(fecha).add("Titular: " + cedula);
                    reservacion.get(fecha).add("Habitacion " + tipo + "del" + fechLleg + "Al" + fechSalid + "(" + dif.toString() + " dias)");
                    if(ninosEnPeriodo.containsKey(fechas)){
                       int ninos = ninosEnPeriodo.get(fechas); 
                       ninos += numN;
                        ninosEnPeriodo.put(fechas, ninos);
                    }else{
                        ninosEnPeriodo.put(fechas, numN);
                    }
                    if(adultosEnPeriodo.containsKey(fechas)){
                       int adultos = adultosEnPeriodo.get(fechas); 
                       adultos += numA;
                        adultosEnPeriodo.put(fechas, adultos);
                    }else{
                        adultosEnPeriodo.put(fechas, numA);
                    }
                }
            }
        }
    public int[] buscarHabitacionDisponible(List<int[]> lista, LocalDate fechaLleg, LocalDate fechaSalid){
        LocalDate[] fechas = new LocalDate[2];
        fechas[0] = fechaLleg;
        fechas[1] = fechaSalid;
        int[] err = new int[2];
        err[0] = 0;
        err[1] = 0;
        for(int[] habi : lista){
            for(LocalDate[] tiemp : habitacionesEnFecha.keySet()){
                if(habitacionesEnFecha.get(tiemp).contains(habi)){
                    if((fechaLleg.equals(tiemp[0]) || fechaLleg.isAfter(tiemp[0]) && (fechaSalid.equals(tiemp[1]) || fechaSalid.isBefore(tiemp[1])))){
                        for(int[] i : habitacionesEnFecha.get(tiemp)){
                            if(habi == i){
                                return habi;
                            }
                        }
                    }
                } else { return habi;}
            }
        }
        return err;
    }
    
    public void cancelacion(String cedula, LocalTime hora){
        int[] numhabit = reservas.get(cedula);
        LocalDateTime time = LocalDateTime.of(fecha, hora);
        cancelaciones.put(time, new ArrayList<String>());
        cancelaciones.get(time).add("Titular: " + cedula);
        cancelaciones.get(time).add("Cancelo: " + gastoUsuario.get(cedula) + "Bs.F.");
        for(LocalDate[] date : habitacionesEnFecha.keySet()){
            for(int[] i : habitacionesEnFecha.get(date)){
                if(numhabit == i){
                    numHabit.remove(numhabit);
                    habitacionesEnFecha.remove(date);
                    reservas.remove(cedula);
                    break;
                }
            }
        }
    }
    public void checkIn(String cedula, LocalTime hora){
        LocalDateTime time = LocalDateTime.of(fecha, hora);
        checkIns.put(time, new ArrayList<String>());
        checkIns.get(time).add("Titular: " + cedula);
        checkIns.get(time).add("Habitacion " +getNumHabitRUT(cedula));
    }
    public void checkOut(String cedula, LocalTime hora){
        LocalDateTime time = LocalDateTime.of(fecha, hora);
        checkOuts.put(time, new ArrayList<String>());
        checkOuts.get(time).add("Titular: " + cedula);
        String a = gastoUsuario.get(cedula) + " Bs.F.";
        checkOuts.get(time).add("Gasto: " + a);
    }   
    public void reservasCanceladas(LocalDate fechaInicio, LocalDate fechaFin){
        LocalDate[] fechas = new LocalDate[2];
        fechas[0] = fechaInicio;
        fechas[1] = fechaFin;
        int count = 0;
        for(LocalDateTime time : cancelaciones.keySet()){
            if((time.toLocalDate().isEqual(fechaInicio) || time.toLocalDate().isAfter(fechaInicio)) && (time.toLocalDate().isBefore(fechaFin) || time.toLocalDate().isEqual(fechaFin))){
                count++;
            }
        }
        if(reportes.containsKey(fechas)){
            reportes.get(fechas).add("Del " + fechaInicio.toString() + " al " + fechaFin.toString() + " " + count + "\t\tReservaciones canceladas");
        } else{
           reportes.put(fechas, new ArrayList<String>());
           reportes.get(fechas).add("Del " + fechaInicio.toString() + " al " + fechaFin.toString() + " " + count + "\t\tReservaciones canceladas");
        }
    }
    public void reservasEfectivas(LocalDate fechaInicio, LocalDate fechaFin){
        LocalDate[] fechas = new LocalDate[2];
        fechas[0] = fechaInicio;
        fechas[1] = fechaFin;
        int count = 0;
        for(LocalDate time : reservacion.keySet()){
            if((time.isEqual(fechaInicio) || time.isAfter(fechaInicio)) && (time.isBefore(fechaFin) || time.isEqual(fechaFin))){
                count++;
            }
        }
        if(reportes.containsKey(fechas)){
            reportes.get(fechas).add("Del " + fechaInicio.toString() + " al " + fechaFin.toString() + " " + count + "\t\tReservaciones efectivas");
        } else{
            reportes.put(fechas, new ArrayList<String>());
            reportes.get(fechas).add("Del " + fechaInicio.toString() + " al " + fechaFin.toString() + " " + count + "\t\tReservaciones efectivas");

        }
        
    }
    public void personasAtendidas(LocalDate fechaInicio, LocalDate fechaFin){
        LocalDate[] fechas = new LocalDate[2];
        fechas[0] = fechaInicio;
        fechas[1] = fechaFin;
        int countA = 0;
        int countN = 0;
        for(LocalDate time[] : ninosEnPeriodo.keySet()){
            if((time[0].isEqual(fechaInicio) || time[0].isAfter(fechaInicio)) && (time[1].isEqual(fechaFin) || time[1].isBefore(fechaFin))){
                countN ++;
            }
        }
        for(LocalDate time[] : adultosEnPeriodo.keySet()){
            if((time[0].isEqual(fechaInicio) || time[0].isAfter(fechaInicio)) && (time[1].isEqual(fechaFin) || time[1].isBefore(fechaFin))){
                countA ++;
            }
        }
        if(reportes.containsKey(fechas)){
            reportes.get(fechas).add("Del " + fechaInicio.toString() + " al " + fechaFin.toString() + " " + countA + " Adultos atendidos\n\t\t\t\t\t" + countN + " Ninos atendidos");
        } else{
            reportes.put(fechas, new ArrayList<String>());
            reportes.get(fechas).add("Del " + fechaInicio.toString() + " al " + fechaFin.toString() + " " + countA + " Adultos atendidos\n\t\t\t\t\t" + countN + " Ninos atendidos");
        }
    }
    public void ingresosCamasAdicionales(LocalDate fechaInicio, LocalDate fechaFin){
        LocalDate[] fechas = new LocalDate[2];
        fechas[0] = fechaInicio;
        fechas[1] = fechaFin;
        float ganancia = 0;
        for(LocalDate time : ingresosCamAdd.keySet()){
            if((time.isAfter(fechaInicio) || time.isEqual(fechaInicio) && (time.isBefore(fechaFin) || time.isEqual(fechaFin)))){
                ganancia += ingresosCamAdd.get(time);
            }
        }
        if(reportes.containsKey(fechas)){
            reportes.get(fechas).add("Del " + fechaInicio + " al " + fechaFin + " " + ganancia + "\t\tIngresos por camas adicionales");
        } else{
            reportes.put(fechas, new ArrayList<String>());
            reportes.get(fechas).add("Del " + fechaInicio + " al " + fechaFin + " " + ganancia + "\t\tIngresos por camas adicionales");

        }
    }
    public void ingresosCajas(LocalDate fechaInicio, LocalDate fechaFin){
        LocalDate[] fechas = new LocalDate[2];
        fechas[0] = fechaInicio;
        fechas[1] = fechaFin;
        float ganancia = 0;
        for(LocalDate time : ingresosCaja.keySet()){
            if((time.isAfter(fechaInicio) || time.isEqual(fechaInicio) && (time.isBefore(fechaFin) || time.isEqual(fechaFin)))){
                ganancia += ingresosCaja.get(time);
            }
        }
        if(reportes.containsKey(fechas)){
            reportes.get(fechas).add("Del " + fechaInicio + " al " + fechaFin + " " + ganancia + "\t\tIngresos por uso de caja fuerte");
        } else{
            reportes.put(fechas, new ArrayList<String>());
            reportes.get(fechas).add("Del " + fechaInicio + " al " + fechaFin + " " + ganancia + "\t\tIngresos por uso de caja fuerte");

        }
    }
    public void pisoMasUso(LocalDate fechaInicio, LocalDate fechaFin){
        LocalDate[] fechas = new LocalDate[2];
        fechas[0] = fechaInicio;
        fechas[1] = fechaFin;
        int[] pisoMasUso = new int[pisos];
        for(LocalDate[] time : habitacionesEnFecha.keySet()){
            if((time[0].isAfter(fechaInicio) || time[0].isEqual(fechaInicio) && (time[1].isBefore(fechaFin) || time[1].isEqual(fechaFin)))){
                for(int[] habi : habitacionesEnFecha.get(time)){
                    pisoMasUso[habi[0]] += habi[1];
                }
            }
        }
        int uso = 0;
        for(int piso : pisoMasUso){
            if(piso <= uso){
                uso = piso;
            }
        }
        int count = 0;
        for(int piso : pisoMasUso){
            if(piso == uso){
                break;
            }
            count++;
        }
        pisoMasUsado.put(fechas, count);
    }
    public void solicitarServicio(String cedula, int num, String[] pedido, LocalDate fecha){
        float gananciaCama = 0;
        float gananciaCaja = 0;
        float ganancia = 0;
        for (int i = 0; i < num; i ++){
            String[] pedidoSplit = pedido[i].split(" ");
            if(pedidoSplit[0].equals("CAM_A")){
                float precio = precios.get(pedidoSplit[0]);
                int veces = Integer.parseInt(pedidoSplit[1]);
                gananciaCama += precio * veces;
                ganancia += gananciaCama;
            } else if(pedidoSplit[0].equals("CAJ_F")){
                float precio = precios.get(pedidoSplit[0]);
                int veces = Integer.parseInt(pedidoSplit[1]);
                gananciaCaja += precio * veces;
                ganancia += gananciaCaja;
            }
            presupuesto += ganancia;
            ingresos.put(pedidoSplit[0], ganancia);
            gastoUsuario.put(cedula, ganancia);
            contabilidad.put(fecha, " |     " + ganancia + "|          |" + presupuesto + "|" + getNombre(pedido[i]));
        }
        ingresosCamAdd.put(fecha, gananciaCama);
        ingresosCaja.put(fecha, gananciaCaja);
    }
    public void restaurante(int num, String[] pedido){
        float ganancia = 0;
        for(int i = 0; i<num; i++){
            ganancia += precios.get(pedido[i]);
        }
        presupuesto += ganancia;
        ingresos.put("Restaurant", ganancia);
        contabilidad.put(fecha, " |      " + ganancia + "|        |" + presupuesto + "|Restaurant");
    }
    public void porcentajeDiaria(LocalDate fechaInicio, LocalDate fechaFin){
        LocalDate[] fechas = new LocalDate[2];
        List<LocalDate[]> lista = new ArrayList<>();
        int diasEntre = (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        fechas[0] = fechaInicio;
        fechas[1] = fechaFin;
        for(LocalDate[] time : habitacionesEnFecha.keySet()){
            if((time[0].isEqual(fechaInicio) || time[0].isAfter(fechaInicio)) && (time[1].isEqual(fechaFin) || time[1].isBefore(fechaFin))){
                lista.add(time);
            }
        }
        LocalDate iteracionFecha = fechaInicio;
        int[] counts = new int[diasEntre];
        for(int j = 0; j<diasEntre; j++){
            int count = 0;
            for(LocalDate[] time : lista){
                LocalDate fechaTemp = time[0];
                int diff = (int) ChronoUnit.DAYS.between(time[0], time[1]);
                for(int i = 0; i<diff; i++){
                    if(fechaTemp.isEqual(iteracionFecha)){
                        fechaTemp.plusDays(1);
                        ArrayList<int[]> valor = habitacionesEnFecha.get(time);
                        count += valor.size();
                    }
                }
            }
        counts[j] = count;
        iteracionFecha.plusDays(1);
        }
        int suma = 0;
        for(int i = 0; i<diasEntre; i++){
            suma += counts[i];
        }
        String porcentaje = "Del " + fechaInicio.toString() + " al " + fechaFin.toString() + "\t\t% de ocupación diaria";
        porcentajeDiaria.put(fechas, new ArrayList<Float>());
        for(int i = 0; i<diasEntre; i++){
            float porcenDia = counts[i];
            porcenDia = porcenDia *100/suma;
            int diaString = i +1;
            porcentaje = porcentaje + "\n\t\t\t\t" + porcenDia + "%\tDia " +diaString;
            porcentajeDiaria.get(fechas).add(porcenDia);
        }
        if(reportes.containsKey(fechas)){
            reportes.get(fechas).add(porcentaje);
        }else{
            reportes.put(fechas, new ArrayList<String>());
            reportes.get(fechas).add(porcentaje);
        }
    }
    public void promedioDiara(LocalDate fechaInicio, LocalDate fechaFin){
        LocalDate[] fechas = new LocalDate[2];
        fechas[0] = fechaInicio;
        fechas[1] = fechaFin;
        int count = 0;
        for(LocalDate[] time : porcentajeDiaria.keySet()){
            if((time[0].isEqual(fechaInicio) || time[0].isAfter(fechaInicio)) && (time[1].isEqual(fechaFin) || time[1].isBefore(fechaFin))){
                ArrayList<Float> valor = porcentajeDiaria.get(time);
                count = valor.size();
                float suma = 0;
                for(Float i : valor){
                    suma += i;
                }
                suma = suma/count;
                promedioDiaria.put(fechas, suma);
                String reporte = "Del " + fechaInicio + " al " + fechaFin + " " + suma + "%\tPromedio de % de ocupacion diaria";
                if(reportes.containsKey(fechas)){
                    reportes.get(fechas).add(reporte);
                }else{
                    reportes.put(fechas, new ArrayList<String>());
                    reportes.get(fechas).add(reporte);
                }
                break;
            }
        }
    }
    public void habitaciones(LocalDate fechaInicio, LocalDate fechaFin){
        LocalDate[] fechas = new LocalDate[2];
        fechas[0] = fechaInicio;
        fechas[1] = fechaFin;
        for(LocalDate[] time : habitacionesEnFecha.keySet()){
            if((time[0].isEqual(fechaInicio) || time[0].isAfter(fechaInicio)) && (time[1].isEqual(fechaInicio) || time[1].isBefore(fechaInicio))){
                ArrayList<int[]> lista = habitacionesEnFecha.get(time);
                int reservadas = lista.size();
                habitacionesReservadasEnUnDia.put(fechaInicio, reservadas);
                String reservadasOut = "Al " + fechaInicio.toString() + "\t\t\t" + reservadas + "%\t\tHabitaciones reservadas"; 
                int libres = reservadas - habitaciones;
                String libresOut = "Al " + fechaInicio.toString() + "\t\t\t" + libres + "%\t\tHabitaciones libres"; 
                habitacionesLibresEnUnDia.put(fechaInicio, libres);
                if(reportes.containsKey(fechas)){
                    reportes.get(fechas).add(reservadasOut);
                    reportes.get(fechas).add(libresOut);
                } else{
                    reportes.put(fechas, new ArrayList<String>());
                    reportes.get(fechas).add(reservadasOut);
                    reportes.get(fechas).add(libresOut);
                }
                break;
            }
        }
        for(LocalDateTime time : checkIns.keySet()){
            LocalDate tiempo = time.toLocalDate();
            if(tiempo.isEqual(fechaInicio)){
                ArrayList<String> ocupadas = checkIns.get(time);
                int numOcupadas = ocupadas.size();
                habitacionesOcupadasEnUnDia.put(tiempo, numOcupadas);
                String ocupadasOut = "Al " + fechaInicio.toString() + "\t\t\t" + numOcupadas + "\t\tHabitacioens ocupadas";
                if(reportes.containsKey(fechas)){
                    reportes.get(fechas).add(ocupadasOut);
                } else{
                    reportes.put(fechas, new ArrayList<String>());
                    reportes.get(fechas).add(ocupadasOut);
                }
            }
        }
    }
    public List<int[]> getHabitacion(String tipo){
        List<int[]> habitacionesList = new ArrayList<>();
        for(int[] key : numHabit.keySet()){
            if(numHabit.get(key).equals(tipo)){
                habitacionesList.add(key);
            }
        }
        if(habitacionesList.size() == 0){
            int[] err = new int[2];
            err[1] = 0;
            err[0] = 0;
            habitacionesList.add(err);
        }
        return habitacionesList;
    }
    public float getPrecio(String value){
        if(precios.containsKey(value)){
            return precios.get(value);
        } else {return 0;}
    }
    public String getNombre(String value){
        if(nombres.containsKey(value)){
            return nombres.get(value);
        } else {return "Error";}
    }
    public String getNumHabitRUT(String cedula){
        int[] num = reservas.get(cedula);
        String piso = String.format("%03d", num[0]);
        String habit = String.format("%03d", num[1]);
        return piso + habit;
    }
    public String getReservacionesOut(){
        return reservacionesString;
    }
    public String getContabilidadString(){
        return contabilidadString;
    }
    public String getReporte(){
        String reporte = "";
        for(LocalDate[] time : reportes.keySet()){
            for(String a : reportes.get(time)){
                reporte = reporte + "\n" + a;
            }
        }
        return reporte;
    }
    public void reservacionesOutCreate(){
        try{
            File reserva = new File("Reservaciones.out");
            if(reserva.createNewFile()){
                System.out.println("Se ha creado el archivo Reservaciones.out");
            } else{
                System.out.println("El archivo Reservaciones.out ya existe");
            }
        } catch (IOException e){
            System.out.println("Ha ocurrido un error con la creacion del out de la reserva");
            e.printStackTrace();
        }
    }
    public void contabilidadOutCreate(){
        try{
            File contabilidad = new File("Contabilidad.out");
            if(contabilidad.createNewFile()){
                System.out.println("Se ha creado el archivo Contabilidad.out");
            } else{
                System.out.println("El archivo Contabilidad.out ya existe");
            }
        } catch (IOException e){
            System.out.println("Ha ocurrido un error con la creacion de de Contabilidad.out");
            e.printStackTrace();
        }
    }
    public void reservacionesOut()throws IOException{
        BufferedWriter archivo = null;
        PrintWriter escritor = null;
        try {
            archivo = new BufferedWriter( new FileWriter("Reservaciones.out"));
            escritor = new PrintWriter(archivo);
            for (LocalDate time : reservacion.keySet()) {
                for(String a : reservacion.get(time)){
                    escritor.println(a);
                    reservacionesString = reservacionesString + "\n" + a;
                }
            }
        } finally {
            escritor.close();
            archivo.close();
        }
    }
    public void contabilidadOut()throws IOException{
        BufferedWriter archivo = null;
        PrintWriter escritor = null;
        try {
            archivo = new BufferedWriter(new FileWriter("Contabilidad.out"));
            escritor = new PrintWriter(archivo);
            for(LocalDate time : contabilidad.keySet()){
                for(String a : reservacion.get(time)){
                    escritor.println(a);
                    contabilidadString = contabilidadString + "\n" + a;
                }
            }
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }finally{
            archivo.close();
            escritor.close();
        }
    }
}