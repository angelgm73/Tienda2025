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
  public class MetodosEx {
    Scanner sc = new Scanner(System.in);

    private ArrayList<Pedido> pedidos;
    private HashMap<String, Articulo> articulos;
    private HashMap<String, Cliente> clientes;

    public MetodosEx() {
        pedidos = new ArrayList();
        articulos = new HashMap();
        clientes = new HashMap();
    }


    public static void main(String[] args) {
        MetodosEx t = new MetodosEx();

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
            System.out.println("\t\t\t\t2 - LeerArchivo");
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
                   persistenciaExamen();
                   break;
                    
                case 2:
                   leerArchivosCSV();
                    break;
                case 3:
                    stockBajo();
                    break;
                case 4:
                    sumaPrecio();
                    break;
                case 5:
                    productosBaratosOrdenados();
                    break;
            }
        } while (opcion != 9);
    }

    private void persistenciaExamen() {
         for (Articulo a : articulos.values()) {
            String nombreArchivo = "Articulos" + a.getIdArticulo() + a.getDescripcion() + a.getExistencias() + a.getPvp() + ".csv";
             
           try (FileOutputStream fos = new FileOutputStream(nombreArchivo);
                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {

                    oos.writeObject(nombreArchivo);


                    System.out.println("Archivo creado: " + nombreArchivo);

                } catch (IOException e) {
                    System.out.println("Error al crear el archivo " + nombreArchivo + ": " + e.getMessage());
                }
        }
    }

    private void leerArchivosCSV() {
       for (Articulo a : articulos.values()) {
            String nombreArchivo = "Articulos" + a.getIdArticulo() + a.getDescripcion() + a.getExistencias() + a.getPvp() + ".dat";
         try (FileInputStream fis = new FileInputStream(nombreArchivo);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {

               

                
            
        } catch (NoSuchElementException e) {
            System.out.println("DNI ERRONEO ESE DNI NO EXISTE VOY A LLAMAR A LA POLICIA");
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de pedidos para este cliente");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    }
       public void stockBajo() {
    System.out.println("ARTÍCULOS CON STOCK BAJO (MENOS DE 10 UNIDADES) ORDENADOS POR PRECIO DE MAYOR A MENOR:");
    
    List<Articulo> articulosStockBajo = articulos.values().stream()
        .filter(a -> a.getExistencias() < 10)
        .sorted(Comparator.comparing(Articulo::getPvp).reversed())
        .collect(Collectors.toList());
    
    // Mostrar la lista resultante
    if (articulosStockBajo.isEmpty()) {
        System.out.println("No hay artículos con stock bajo.");
    } else {
        articulosStockBajo.forEach(a -> 
            System.out.println(a.getIdArticulo() + " - " + a.getDescripcion() 
                + " - Stock: " + a.getExistencias() + " - Precio: " + a.getPvp() + "€"));
    }
  }
  public double sumaPrecio(){
    double totalInventario = articulos.values().stream().mapToDouble(Articulo::getPvp).sum();
    System.out.println("El precio total de los articulos es: " + totalInventario + " eros");
    return totalInventario; 
  }
  public void productosBaratosOrdenados() {
    System.out.println("ARTÍCULOS DE MENOS DE 50€ ORDENADOS POR DESCRIPCIÓN:");

    List<String> listaNombres = articulos.values().stream()
        .filter(a -> a.getPvp() < 50)
        .sorted(Comparator.comparing(Articulo::getDescripcion))
        .map(Articulo::getDescripcion)
        .collect(Collectors.toList());

    if (listaNombres.isEmpty()) {
        System.out.println("No hay artículos que cumplan los criterios.");
    } else {
        listaNombres.forEach(System.out::println);
    }
}
  
}
   
  
  
  
  
  
  
  
  
  
  