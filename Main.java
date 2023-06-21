import java.io.IOException;
public class Main {
    public static void main(String[] args)throws IOException{
        Modelo m = new Modelo();
        Vista v = new Vista();
        Controlador c = new Controlador(m, v);
        c.iniciarVista();
        c.iniciarModelo();
    }
}
