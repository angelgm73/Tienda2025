
package educasturangelgm73.tienda2025;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
  public class GonzalezVazquezAngel {
    Scanner sc = new Scanner(System.in);

    private ArrayList<Pedido> pedidos;
    private HashMap<String, Articulo> articulos;
    private HashMap<String, Cliente> clientes;

    public GonzalezVazquezAngel() {
        pedidos = new ArrayList();
        articulos = new HashMap();
        clientes = new HashMap();
    }


    public static void main(String[] args) {
        GonzalezVazquezAngel t = new GonzalezVazquezAngel();

        t.cargaDatos();
        t.menu();
    }


    public void cargaDatos() {
        clientes.put("80580845T", new Cliente("80580845T", "ANA ", "658111111", "ana@gmail.com"));
        clientes.put("36347775R", new Cliente("36347775R", "ANTONIO", "649222222", "antonio@gmail.com"));
        clientes.put("63921307Y", new Cliente("63921307Y", "AURORA", "652333333", "aurora@gmail.com"));
        clientes.put("53472775R", new Cliente("53472775R", "EMILIO", "649222222", "emilio@gmail.com"));
        clientes.put("43211307Y", new Cliente("43211307Y", "EVA", "652333333", "eva@gmail.com"));
        clientes.put("02337565Y", new Cliente("02337565Y", "MATEO", "634567890", "mateo@gmail.com"));
        clientes.put("35445638M", new Cliente("35445638M", "MARIA", "633478990", "maria@gmail.com"));


        articulos.put("1-11", new Articulo("1-11", "RATON LOGITECH ST ", 14, 15));
        articulos.put("1-22", new Articulo("1-22", "TECLADO STANDARD  ", 9, 18));
        articulos.put("2-11", new Articulo("2-11", "HDD SEAGATE 1 TB  ", 16, 80));
        articulos.put("2-22", new Articulo("2-22", "SSD KINGSTOM 256GB", 9, 70));
        articulos.put("2-33", new Articulo("2-33", "SSD KINGSTOM 512GB", 0, 200));
        articulos.put("3-22", new Articulo("3-22", "EPSON PRINT XP300 ", 5, 80));
        articulos.put("4-11", new Articulo("4-11", "ASUS  MONITOR  22 ", 5, 100));
        articulos.put("4-22", new Articulo("4-22", "HP MONITOR LED 28 ", 5, 180));
        articulos.put("4-33", new Articulo("4-33", "SAMSUNG ODISSEY G5", 12, 580));

        LocalDate hoy = LocalDate.now();
        pedidos.add(new Pedido("80580845T-001/2024", clientes.get("80580845T"), hoy.minusDays(1), new ArrayList<>
                (List.of(new LineaPedido("1-11", 3), new LineaPedido("4-22", 3)))));
        pedidos.add(new Pedido("80580845T-002/2024", clientes.get("80580845T"), hoy.minusDays(2), new ArrayList<>
                (List.of(new LineaPedido("4-11", 3), new LineaPedido("4-22", 2), new LineaPedido("4-33", 4)))));
        pedidos.add(new Pedido("36347775R-001/2024", clientes.get("36347775R"), hoy.minusDays(3), new ArrayList<>
                (List.of(new LineaPedido("4-22", 1), new LineaPedido("2-22", 3)))));
        pedidos.add(new Pedido("36347775R-002/2024", clientes.get("36347775R"), hoy.minusDays(5), new ArrayList<>
                (List.of(new LineaPedido("4-33", 3), new LineaPedido("2-11", 3)))));
        pedidos.add(new Pedido("63921307Y-001/2024", clientes.get("63921307Y"), hoy.minusDays(4), new ArrayList<>
                (List.of(new LineaPedido("2-11", 5), new LineaPedido("2-33", 3), new LineaPedido("4-33", 2)))));
        pedidos.add(new Pedido("53472775R-001/2025", clientes.get("53472775R"), hoy, new ArrayList<>
                (List.of(new LineaPedido("1-11", 2), new LineaPedido("2-11", 2)))));
        pedidos.add(new Pedido("43211307Y-001/2025", clientes.get("43211307Y"), hoy, new ArrayList<>
                (List.of(new LineaPedido("4-33", 1)))));

    }

    private void menu() {

        int opcion = 0;
        do {
            System.out.println("\n\n\n\n\n\t\t\tTAREA 20 03 2025");
            System.out.println("\t\t\t\t1 - Crear archivos");
            System.out.println("\t\t\t\t2 - DNI  para leer archivo");
            System.out.println("\t\t\t\t3 - Crear archivo por cliente");

            System.out.println("\t\t\t\t9 - SALIR");
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Introduce un número válido.");
                sc.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    crearArchivosPorCliente();
                    break;
                case 2:
                    leerArchivos();
                    break;
                case 3:
                    PersistenciaClientes();
                    break;

            }
        } while (opcion != 9);
    }

    public void crearArchivosPorCliente() {
        Map<Cliente, ArrayList<Pedido>> pedidosPorCliente = new HashMap<>();


        for (Pedido pedido : pedidos) {
            Cliente cliente = pedido.getClientePedido();

            if (!pedidosPorCliente.containsKey(cliente)) {
                pedidosPorCliente.put(cliente, new ArrayList<>());
            }
            pedidosPorCliente.get(cliente).add(pedido);
        }


        int contadorArchivos = 0;
        for (Map.Entry<Cliente, ArrayList<Pedido>> entry : pedidosPorCliente.entrySet()) {
            Cliente cliente = entry.getKey();
            ArrayList<Pedido> pedidosCliente = entry.getValue();

            if (!pedidosCliente.isEmpty()) {
                String nombreArchivo = "pedidosCliente_" + cliente.getNombre().trim() + ".dat";

                try (FileOutputStream fos = new FileOutputStream(nombreArchivo);
                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {

                    oos.writeObject(pedidosCliente);
                    contadorArchivos++;

                    System.out.println("Archivo creado: " + nombreArchivo);

                } catch (IOException e) {
                    System.out.println("Error al crear el archivo " + nombreArchivo + ": " + e.getMessage());
                }
            }
        }

        System.out.println("Se han creado " + contadorArchivos + " archivos de pedidos de clientes.");
    }

    public void leerArchivos() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el DNI del cliente");
        String dni = sc.nextLine();
        try {
            Cliente clienteBuscado = clientes.values().stream().filter(e -> e.getDni().equals(dni)).findFirst().get();


            String nombreArchivo = "pedidosCliente_" + clienteBuscado.getNombre().trim() + ".dat";

            try (FileInputStream fis = new FileInputStream(nombreArchivo);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {

                @SuppressWarnings("unchecked")
                ArrayList<Pedido> pedidosCliente = (ArrayList<Pedido>) ois.readObject();

                System.out.println("Pedidos del cliente " + clienteBuscado.getNombre() + ":");
                for (Pedido pedido : pedidosCliente) {
                    System.out.println(pedido.toString());
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("DNI ERRONEO ESE DNI NO EXISTE VOY A LLAMAR A LA POLICIA");
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de pedidos para este cliente");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void stockBajo(int limitestock) {
        System.out.println("Articulos con stock bajo" + limitestock);
        articulos.values().stream().filter(a -> a.getExistencias() <= limitestock).sorted(Comparator.comparing(Articulo::getExistencias)).forEach(a -> System.out.printf("%s - Stock: d%%n", a.getDescripcion(), a.getExistencias()));
    }
   public void PersistenciaClientes(){
        boolean tienepedidos;
        for (Cliente cliente : clientes.values()){
           tienepedidos = false;
           for (Pedido pedido : pedidos){
               if (pedido.getClientePedido().getDni().equals(cliente.getDni())){
                   tienepedidos = true;
                   break;
               }

           }if (tienepedidos){
               String nombreArchivo = "ClientePedido_" + cliente.getNombre().trim() + ".dat";
                try (FileOutputStream fos = new FileOutputStream(nombreArchivo);
                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {

                    oos.writeObject(nombreArchivo);


                    System.out.println("Archivo creado: " + nombreArchivo);

                } catch (IOException e) {
                    System.out.println("Error al crear el archivo " + nombreArchivo + ": " + e.getMessage());
                }
            }
        }
   }

}
