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
import java.util.stream.Collectors;


/**
 *
 * @author Administrator
 */
  public class RepasoExamen {
    Scanner sc = new Scanner(System.in);

    private ArrayList<Pedido> pedidos;
    private HashMap<String, Articulo> articulos;
    private HashMap<String, Cliente> clientes;

    public RepasoExamen() {
        pedidos = new ArrayList();
        articulos = new HashMap();
        clientes = new HashMap();
    }


    public static void main(String[] args) {
        RepasoExamen t = new RepasoExamen();

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

    public void menu() {

        int opcion = 0;
        do {
            System.out.println("\n\n\n\n\n\t\t\tTAREA 20 03 2025");
            System.out.println("\t\t\t\t1 - Apruebo facil");
            System.out.println("\t\t\t\t2 - Apruebo pila facil");
            System.out.println("\t\t\t\t3 - Como fue tan facil?");

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
                 exportarArticulosBajoStockCSV();
                    break;
                case 2:
                leerArticulosBajoStockCSV() ;
                    break;
                case 3:
                buscarPedidosPorCliente();
                    break;

            }
        } while (opcion != 9);
    }
    public void exportarArticulosBajoStockCSV() {
    System.out.print("Introduce el límite de stock para considerar bajo (ejemplo: 10): ");
    int limiteStock = sc.nextInt();
    
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("articulos_bajo_stock.csv"))) {
        // Escribir encabezado
        bw.write("ID,Descripcion,Existencias,Precio\n");
        
        // Filtrar artículos con bajo stock y escribirlos
        for (Articulo a : articulos.values()) {
            if (a.getExistencias() <= limiteStock) {
                bw.write(a.getIdArticulo() + "," + 
                        a.getDescripcion().replace(",", ";") + "," + 
                        a.getExistencias() + "," + 
                        a.getPvp() + "\n");
            }
        }
        System.out.println("Archivo articulos_bajo_stock.csv creado correctamente.");
    } catch (IOException e) {
        System.out.println("Error al exportar artículos: " + e.getMessage());
    }
}

public void leerArticulosBajoStockCSV() {
    try (BufferedReader br = new BufferedReader(new FileReader("articulos_bajo_stock.csv"))) {
        String linea;
        // Saltar encabezado
        br.readLine();
        
        System.out.println("\n===== ARTÍCULOS CON BAJO STOCK =====");
        System.out.printf("%-8s %-30s %10s %10s\n", "ID", "DESCRIPCIÓN", "STOCK", "PRECIO");
        System.out.println("------------------------------------------------");
        
        while ((linea = br.readLine()) != null) {
            String[] campos = linea.split(",");
            System.out.printf("%-8s %-30s %10s %10.2f€\n", 
                    campos[0], campos[1], campos[2], Double.parseDouble(campos[3]));
        }
    } catch (IOException e) {
        System.out.println("Error al leer artículos: " + e.getMessage());
    }
}
public void buscarPedidosPorCliente() {
    sc.nextLine(); // Limpiar buffer
    System.out.print("Introduce el nombre del cliente (o parte del nombre): ");
    String nombreBusqueda = sc.nextLine().toUpperCase();
    
    System.out.println("\n===== PEDIDOS ENCONTRADOS =====");
    
    pedidos.stream()
        .filter(p -> p.getClientePedido().getNombre().toUpperCase().contains(nombreBusqueda))
        .sorted(Comparator.comparing(Pedido::getFechaPedido).reversed())
        .forEach(p -> {
            System.out.printf("ID: %-15s Cliente: %-15s Fecha: %-12s Importe: %.2f€\n",
                p.getIdPedido(),
                p.getClientePedido().getNombre(),
                p.getFechaPedido(),
                totalPedido(p));
            System.out.println("   Artículos:");
            p.getCestaCompra().forEach(lp -> {
                Articulo art = articulos.get(lp.getIdArticulo());
                System.out.printf("   - %-30s x%d (%.2f€/u)\n", 
                    art.getDescripcion(), 
                    lp.getUnidades(),
                    art.getPvp());
            });
            System.out.println();
        });
}
 public double totalPedido (Pedido p){
        double total=0;
                for (LineaPedido L: p.getCestaCompra()){
                    
                
                    
        total+=(articulos.get(L.getIdArticulo()).getPvp())
                *L.getUnidades();
    }      
            return total;
    }   
 //Ejercicio 1
    private void ejercicio1() {
        try (BufferedWriter bfwArticulos = new BufferedWriter(new FileWriter("articulos.csv"))) {
            for (Articulo a : articulos.values()) {
                bfwArticulos.write(a.getIdArticulo() + "," + a.getDescripcion() + "," + a.getExistencias() + "," + a.getPvp() + "\n");
            }
                System.out.println("Backup de artículos realizado con éxito");
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        
        HashMap<String, Articulo> articuloAux = new HashMap();
        try (Scanner scArticulo = new Scanner(new File("articulos.csv"))) {
            while (scArticulo.hasNextLine()) {
                String[] atributos = scArticulo.nextLine().split("[,]");
                String idArticulo = atributos[0];
                String descripcion = atributos[1];
                int existencias = Integer.parseInt(atributos[2]);
                double pvp = Double.parseDouble(atributos[3]);
                
                Articulo a = new Articulo(idArticulo, descripcion, existencias, pvp);
                articuloAux.put(idArticulo, a);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        articuloAux.values().forEach(System.out::println);
        
    }
    
    //Ejercicio 2
    private void ejercicio2() {
        System.out.println("Listado de pedidos por fecha (de más reciente a menos reciente):");
        pedidos.stream().sorted(Comparator.comparing(Pedido::getFechaPedido).reversed()).forEach(System.out::println);
    }
    
    //Ejercicio3
    private void ejercicio3() {
        String dni;
        do {
            System.out.println("Teclea DNI del Cliente:");
            dni = sc.next();
        } while (!MetodosAux.validarDni(dni));
        
        Cliente c = clientes.get(dni);
        if (c != null) {
            System.out.println("El importe total d elos pedidos del cliente con DNI " +dni+ " es: " +totalCliente(c)+ " €");
        } else {
            System.out.println("El DNI que has escrito no concuerda con ninguno de nuestros clientes");
        }
    }
    
    //Ejercicio 4
    private void ejercicio4() {
        System.out.println("Teclea el importe que deben superar los artículos:");
        int i = sc.nextInt();
        System.out.println("Los siguientes artículos superan los: " + i + "€");
        List<Articulo> articulosPorPrecio = articulos.values().stream().filter(a -> a.getPvp() > i).collect(Collectors.toList());
        articulosPorPrecio.forEach(System.out::println);
    }
    
    
    public double totalCliente(Cliente c) {
   
        return pedidos.stream().filter(p -> p.getClientePedido().equals(c)).mapToDouble(p -> totalPedido(p)).sum();
    }
    
  }