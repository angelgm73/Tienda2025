
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
            System.out.println("\t\t\t\t3 - Ordenar clientes");
            System.out.println("\t\t\t\t4 - Obtener pedidos clientes");
            System.out.println("\t\t\t\t5 - Calcular stock");
            System.out.println("\t\t\t\t6 - Obtener nombre cliente");
            System.out.println("\t\t\t\t7 - Obtener articulso por precio");


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
                    ordenarClientesGasto();
                    break;
                case 4:
                    obtenerPedidosCliente();
                    break;
                case 5:
                    calcularStockTotal();
                    break;
                case 6:
                    buscarClientePorNombre();
                    break;
                case 7:
                    obtenerArticulosPorPrecio();
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
    public void leerArchivos(){
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
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de pedidos para este cliente");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    public void ordenarClientesGasto() {
        Map<Cliente, Double> gastoPorCliente = new HashMap<>();


        for (Pedido pedido : pedidos) {
            Cliente cliente = pedido.getClientePedido();
            double totalPedido = 0;

            for (LineaPedido linea : pedido.getCestaCompra()) {
                Articulo articulo = articulos.get(linea.getIdArticulo()); // Corregido
                if (articulo != null) {
                    totalPedido += linea.getUnidades() * articulo.getPvp();
                }
            }


            gastoPorCliente.put(cliente, gastoPorCliente.getOrDefault(cliente, 0.0) + totalPedido);
        }


        List<Map.Entry<Cliente, Double>> listaOrdenada = new ArrayList<>(gastoPorCliente.entrySet());
        listaOrdenada.sort((a, b) -> Double.compare(b.getValue(), a.getValue())); // Orden descendente


        System.out.println("\nClientes ordenados por gasto total:");
        for (Map.Entry<Cliente, Double> entry : listaOrdenada) {
            System.out.printf("Cliente: %s | Gasto Total: %.2f€\n", entry.getKey().getNombre(), entry.getValue());
        }
    }
       public void obtenerPedidosCliente() {
        ArrayList<Pedido> pedidosCliente = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el DNI del cliente");
        String dni = sc.nextLine();
        if(!clientes.containsKey(dni)){
            System.out.println("El cliente no existe");
            return;
        }else




        for (Pedido pedido : pedidos) {
         if(pedido.getClientePedido().getDni().equals(dni)){
             pedidosCliente.add(pedido);
         }
        }
           if(pedidosCliente.isEmpty()){
               System.out.println("El cliente no existe");
           }else
               System.out.println("El cliente tiene " + pedidosCliente.size() + " pedidos");
       }
       public void calcularStockTotal (){
        Scanner sc = new Scanner(System.in);
           System.out.println("Ingrese el ID del articulo");
           String idArticulo = sc.nextLine();
           if (!articulos.containsKey(idArticulo)) {
               System.out.println("El articulo no existe");
               return;
           }
             int stocktotal = articulos.values().stream().filter(articulo -> articulo.getIdArticulo().equals(idArticulo)).mapToInt(Articulo::getExistencias).sum();
           System.out.println("El stock total del articulo " + idArticulo + " es: " + stocktotal);
       }
       public void buscarClientePorNombre(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del cliente");
        String nombre = sc.nextLine();
         Cliente clienteEncontrado =clientes.values().stream().filter(cliente -> cliente.getNombre().equalsIgnoreCase(nombre)).findFirst().orElse(null);
         if(clienteEncontrado != null){
             System.out.println("Cliente encontrado " + clienteEncontrado );

         }else{
             System.out.println("El cliente no existe");
         }
       }
       public void obtenerArticulosPorPrecio(){
           Scanner sc = new Scanner(System.in);
           System.out.println("Ingrese el precio minimo");
           double min = sc.nextDouble();
           System.out.println("Ingrese el precio maximo");
           double max = sc.nextDouble();
          articulos.values().stream().filter(articulo -> articulo.getPvp() >= min && articulo.getPvp() <= max).forEach(articulo -> System.out.println("El articulo con ID " + articulo.getIdArticulo()+ " tiene un precio de " + articulo.getPvp() + "€"));

       }
}

