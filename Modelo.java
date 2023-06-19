import java.time.LocalDate; import java.time.LocalTime;
import java.time.Period; import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import java.io.IOException; import java.io.BufferedReader;
import java.io.FileReader; import java.util.*;
public class Modelo{
    private float presupuesto;
    private LocalDate fecha;
    private int habitaciones;
    private int pisos;
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
    private Map<LocalDate, String> contabilidad = new HashMap<>();
    private Map<String, Float> ingresos = new HashMap<>();
    private Map<String, Float> gastoUsuario = new HashMap<>();
    private Map<LocalDate[], String> reportes = new HashMap<>();
    private Map<String, Integer> numPersonaEnHabit = new HashMap<>();
    private Map<LocalDate, Float> ingresosCamAdd = new HashMap<>();
    private Map<LocalDate, Float> ingresosCaja = new HashMap<>();
    private Map<LocalDate[], ArrayList<String>> porcentajeDiaria = new HashMap<>();
    private Map<LocalDate[], String> promedioDiaria = new HashMap<>();
    private Map<LocalDate, ArrayList<Integer>> habitacionesEnUnDIa = new HashMap<>();
    
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
    public void reserva(LocalDate fechLleg, LocalDate fechSalid, String tipo, int num, String[] personas){
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
                    String titular = personas[0];
                    String[] titularSplit = titular.split(" ", 3);
                    String cedula = titularSplit[1];
                    reservas.put(cedula, habit);
                    Period dif = Period.between(fechLleg, fechSalid);
                    reservacion.put(fecha, new ArrayList<String>());
                    reservacion.get(fecha).add("Titular: " + cedula);
                    reservacion.get(fecha).add("Habitacion " + tipo + "del" + fechLleg + "Al" + fechSalid + "(" + dif.toString() + " dias)");
                    for(int i = 1; i<=num; i++){
                        String[] infoRes = personas[i].split(" ", 3);
                        if(infoRes[0].equals("A")){
                            int temp1 = adultosEnPeriodo.get(fechas);
                            temp1++;
                            adultosEnPeriodo.put(fechas, temp1);
                        } else if (infoRes[0].equals("N")){
                            int temp = ninosEnPeriodo.get(fechas);
                            temp++;
                            ninosEnPeriodo.put(fechas, temp);
                        }
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
        reportes.put(fechas, "Del " + fechaInicio.toString() + " al " + fechaFin.toString() + " " + count + "\t\tReservaciones canceladas");
    }
    public void ocupacionDiaria(LocalDate fechaInicio, LocalDate fechaFin){
        LocalDate[] fechas = new LocalDate[2];
        fechas[0] = fechaInicio;
        fechas[1] = fechaFin;

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
        reportes.put(fechas, "Del " + fechaInicio.toString() + " al " + fechaFin.toString() + " " + count + "\t\tReservaciones efectivas");
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
        reportes.put(fechas, "Del " + fechaInicio.toString() + " al " + fechaFin.toString() + " " + countA + " Adultos atendidos\n\t\t\t\t\t" + countN + " Ninos atendidos");
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
        reportes.put(fechas, "Del " + fechaInicio + " al " + fechaFin + " " + ganancia + "\t\tIngresos por camas adicionales");
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
        reportes.put(fechas, "Del " + fechaInicio + " al " + fechaFin + " " + ganancia + "\t\tIngresos por uso de caja fuerte");
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
        int habi = 0;
        for(LocalDate[] time : habitacionesEnFecha.keySet()){
            if((time[0].isEqual(fechaInicio) || time[0].isAfter(fechaInicio)) && (time[1].isEqual(fechaFin) || time[1].isBefore(fechaFin))){
                lista.add(time);
            }
        }
        LocalDate iteracionFecha = fechaInicio;
        int[] counts = new int[diasEntre];
        for(int j = 0; j<diasEntre; j++){
            for(LocalDate[] time : lista){
                LocalDate fechaTemp = time[0];
                long diff = ChronoUnit.DAYS.between(time[0], time[1]);
                for(int i = 0; i<diff; i++){
                    if(fechaTemp.isEqual(iteracionFecha)){
                        fechaTemp.plusDays(1);
                        ArrayList<int[]> valor = habitacionesEnFecha.get(time);
                        count += valor.size();
                    }
                }
            }
        iteracionFecha.plusDays(1);
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
}