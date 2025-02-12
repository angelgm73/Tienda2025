package educasturangelgm73.tienda2025;

import educasturangelgm73.tienda2025.Articulo.ComparadorPorOrden;
import educasturangelgm73.tienda2025.Articulo.ComparadorPorSeccion;
import educasturangelgm73.tienda2025.Excepciones.StockAgotado;
import educasturangelgm73.tienda2025.Excepciones.StockInsuficiente;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Tienda2025 implements Serializable {
    Scanner sc = new Scanner(System.in);
    private ArrayList<Pedido> pedidos;
    private HashMap<String, Articulo> articulos;
    private HashMap<String, Cliente> clientes;
    
    public Tienda2025() {
        pedidos = new ArrayList<>();
        articulos = new HashMap<>();
        clientes = new HashMap<>();
    }
    
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
    
    public HashMap<String, Articulo> getArticulos() {
        return articulos;
    }
    
    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }
    
    public static void main(String[] args) {
        Tienda2025 t = new Tienda2025();
        t.cargaDatos();
        t.menu();
    }
    
    //<editor-fold defaultstate="collapsed" desc="MENÚS">
    private void menu() {
        
        int opcion = 0;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tTIENDA");
            System.out.println("\t\t\t\t1 - PEDIDOS");
            System.out.println("\t\t\t\t2 - ARTICULOS");
            System.out.println("\t\t\t\t3 - CLIENTES");
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
                    menuPedidos();
                    break;
                case 2:
                    menuArticulos();
                    break;
                case 3:
                    menuClientes();
                    break;
                // No es necesario un case para 9, se sale del bucle
            }
        } while (opcion != 9);
    }
    
    private void menuPedidos() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tPEDIDOS");
            System.out.println("\t\t\t\t1 - NUEVO PEDIDO");
            System.out.println("\t\t\t\t2 - ELIMINAR PEDIDO");
            System.out.println("\t\t\t\t3 - MODIFICAR PEDIDO");
            System.out.println("\t\t\t\t4 - LISTADO DE PEDIDOS");
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
                    nuevoPedido();
                    break;
                case 2:
                    eliminarPedido();
                    break;
                case 3:
                    modificarPedido();
                    break;
                case 4:
                    listadoPedidos();
                    break;
            }
        } while (opcion != 9);
    }
    
    private void menuArticulos() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tARTICULOS");
            System.out.println("\t\t\t\t1 - NUEVO ARTICULO");
            System.out.println("\t\t\t\t2 - MODIFICAR ARTICULO");
            System.out.println("\t\t\t\t3 - ELIMINAR ARTICULO");
            System.out.println("\t\t\t\t4 - LISTADO DE ARTICULOS");
            System.out.println("\t\t\t\t5 - REPONER ARTICULO");
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
                    nuevoArticulo();
                    break;
                case 2:
                    modificarArticulo();
                    break;
                case 3:
                    eliminarArticulo();
                    break;
                case 4:
                    listadoArticulos();
                    break;
                case 5:
                    reponerArticulos();
                    break;
            }
        } while (opcion != 9);
    }
    
    private void menuClientes() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tCLIENTES");
            System.out.println("\t\t\t\t1 - NUEVO CLIENTE");
            System.out.println("\t\t\t\t2 - MODIFICAR CLIENTE");
            System.out.println("\t\t\t\t3 - ELIMINAR CLIENTE");
            System.out.println("\t\t\t\t4 - LISTADO DE CLIENTES");
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
                    nuevoCliente();
                    break;
                case 2:
                    modificarCliente();
                    break;
                case 3:
                    eliminarCliente();
                    break;
                case 4:
                    listadoClientes();
                    break;
            }
        } while (opcion != 9);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="GESTIÓN DE PEDIDOS">
    public void stock(int unidadesPed, String id) throws StockAgotado, StockInsuficiente {
        int n = articulos.get(id).getExistencias();
        if (n == 0) {
            throw new StockAgotado("Stock AGOTADO para el artículo: " + articulos.get(id).getDescripcion());
        } else if (n < unidadesPed) {
            throw new StockInsuficiente("No hay stock suficiente. Me pide " + unidadesPed + " de " + articulos.get(id).getDescripcion() + " y sólo se dispone de: " + n);
        }
    }
    
    public String generaIdpedido(String idCliente) {
        int contador = 0;
        String nuevoId;
        for (Pedido p : pedidos) {
            if (p.getClientePedido().getDni().equalsIgnoreCase(idCliente)) {
                contador++;
            }
        }
        contador++;
        nuevoId = idCliente + "-" + String.format("%03d", contador) + "/" + LocalDate.now().getYear();
        return nuevoId;
    }
    
    public void nuevoPedido() {
        ArrayList<LineaPedido> cestaCompraAux = new ArrayList<>();
        String dniT, idT, opc, pedidasS;
        int pedidas = 0;
        sc.nextLine();
        do {
            System.out.println("CLIENTE PEDIDO (DNI):");
            dniT = sc.nextLine().toUpperCase();
            if (dniT.isBlank()) break;
            if (!MetodosAux.validarDni(dniT)) {
                System.out.println("El DNI no es un DNI válido");
            }
        } while (!clientes.containsKey(dniT));
        
        if (!dniT.isBlank()){
            System.out.println("INTRODUZCA LOS ARTICULOS DEL PEDIDO UNO A UNO: ");
            do {
                System.out.println("INTRODUCE CODIGO ARTICULO (99 PARA TERMINAR): ");
                idT = sc.next();
                if (!idT.equals("99") && articulos.containsKey(idT)){
                    System.out.print("(" + articulos.get(idT).getDescripcion() + ") - UNIDADES? ");
                    do {
                        pedidasS = sc.next();
                    } while (!MetodosAux.esInt(pedidasS));
                    pedidas = Integer.parseInt(pedidasS);
          
                    try {
                        stock(pedidas, idT); // Puede lanzar StockAgotado o StockInsuficiente
                        cestaCompraAux.add(new LineaPedido(idT, pedidas));
                    } catch (StockAgotado e) {
                        System.out.println(e.getMessage());
                    } catch (StockInsuficiente e) {
                        System.out.println(e.getMessage());
                        int disponibles = articulos.get(idT).getExistencias();
                        System.out.print("QUIERES LAS " + disponibles + " UNIDADES DISPONIBLES? (S/N) ");
                        opc = sc.next();
                        if (opc.equalsIgnoreCase("S")){
                            cestaCompraAux.add(new LineaPedido(idT, disponibles));
                        }
                    }
                }
            } while (!idT.equals("99"));
            
            // Mostrar el resumen del pedido
            for (LineaPedido l : cestaCompraAux) {
                System.out.println(articulos.get(l.getIdArticulo()).getDescripcion() + " - ("+ l.getUnidades() + ")");
            }
            System.out.println("ESTE ES TU PEDIDO. PROCEDEMOS? (S/N)   ");
            opc = sc.next();
            if (opc.equalsIgnoreCase("S")){
                // Se registra el pedido y se descuentan las existencias correspondientes
                LocalDate hoy = LocalDate.now();
                pedidos.add(new Pedido(generaIdpedido(dniT), clientes.get(dniT), hoy, cestaCompraAux));
                
                for (LineaPedido l : cestaCompraAux) {
                    articulos.get(l.getIdArticulo()).setExistencias(
                        articulos.get(l.getIdArticulo()).getExistencias() - l.getUnidades());
                } 
            }
        }
    }
    
    private void eliminarPedido() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el ID del pedido a eliminar: ");
        String id = sc.next();
        Pedido pedidoToRemove = null;
        for (Pedido p : pedidos) {
            if (p.getIdPedido().equalsIgnoreCase(id)) {
                pedidoToRemove = p;
                break;
            }
        }
        if (pedidoToRemove != null) {
            // Se devuelven las existencias de cada línea del pedido
           for (LineaPedido lp : pedidoToRemove.getCestaCompra()) {
                Articulo art = articulos.get(lp.getIdArticulo());
                art.setExistencias(art.getExistencias() + lp.getUnidades());
            }
            pedidos.remove(pedidoToRemove);
            System.out.println("Pedido eliminado.");
        } else {
            System.out.println("Pedido no encontrado.");
        }
    }
    
    private void modificarPedido() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el ID del pedido a modificar: ");
        String id = sc.next();
        Pedido pedidoToModify = null;
        for (Pedido p : pedidos) {
            if (p.getIdPedido().equalsIgnoreCase(id)) {
                pedidoToModify = p;
                break;
            }
        }
        if (pedidoToModify == null) {
            System.out.println("Pedido no encontrado.");
            return;
        }
        System.out.println("Pedido encontrado:");
        System.out.println(pedidoToModify);
        System.out.print("¿Desea agregar una nueva línea de pedido? (S/N): ");
        String opc = sc.next();
        if (opc.equalsIgnoreCase("S")) {
            System.out.print("Introduce el código del artículo: ");
            String idArticulo = sc.next();
            if (!articulos.containsKey(idArticulo)) {
                System.out.println("Artículo no encontrado.");
                return;
            }
            System.out.print("Introduce el número de unidades: ");
            int unidades = sc.nextInt();
            try {
                stock(unidades, idArticulo);
                pedidoToModify.getCestaCompra().add(new LineaPedido(idArticulo, unidades));
                // Se descuenta el stock
                Articulo art = articulos.get(idArticulo);
                art.setExistencias(art.getExistencias() - unidades);
                System.out.println("Línea agregada al pedido.");
            } catch (StockAgotado | StockInsuficiente e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    private void listadoPedidos() {
        System.out.println("Listado de Pedidos:");
       pedidos.stream().sorted(Comparator.comparing(p -> totalPedido(p))) .forEach(p -> System.out.println(p + "\t - IMPORTE TOTAL: " + totalPedido(p) + " Euro"));

        
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="GESTIÓN DE ARTICULOS">
    private void nuevoArticulo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el código del nuevo artículo: ");
        String id = sc.next();
        if (articulos.containsKey(id)) {
            System.out.println("El artículo ya existe.");
            return;
        }
        sc.nextLine(); // limpiar buffer
        System.out.print("Introduce la descripción: ");
        String descripcion = sc.nextLine();
        System.out.print("Introduce el número de existencias: ");
        int existencias = sc.nextInt();
        System.out.print("Introduce el precio: ");
        double precio = sc.nextDouble();
        articulos.put(id, new Articulo(id, descripcion, existencias, precio));
        System.out.println("Artículo añadido.");
    }
    
    private void modificarArticulo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el código del artículo a modificar: ");
        String id = sc.next();
        if (!articulos.containsKey(id)) {
            System.out.println("El artículo no existe.");
            return;
        }
        Articulo art = articulos.get(id);
        System.out.println("Artículo actual: " + art);
        sc.nextLine(); // limpiar buffer
        System.out.print("Introduce la nueva descripción (dejar en blanco para no cambiar): ");
        String descripcion = sc.nextLine();
        if (!descripcion.isBlank()) {
            art.setDescripcion(descripcion);
        }
        System.out.print("Introduce las nuevas existencias (o -1 para no cambiar): ");
        int existencias = sc.nextInt();
        if (existencias != -1) {
            art.setExistencias(existencias);
        }
        System.out.print("Introduce el nuevo precio (o -1 para no cambiar): ");
        double precio = sc.nextDouble();
        if (precio != -1) {
            art.setPvp(precio);
        }
        System.out.println("Artículo modificado.");
    }
    
    private void eliminarArticulo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el codigo del articulo a eliminar: ");
        String id = sc.next();
        if (!articulos.containsKey(id)) {
            System.out.println("El articulo no existe.");
            return;
        }
        articulos.remove(id);
        System.out.println("Articulo eliminado.");
    }
    
    private void listadoArticulos() {
        System.out.println("Como desea ver sus articulos todos juntos o por secciones?:");
        int opcion = 0;
        do {
            
            System.out.println("\t\t\t\t1 - TODOS");
            System.out.println("\t\t\t\t2 - SECCION");
             System.out.println("\t\t\t\t3 - SALIR");
                     try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Introduce un número válido.");
                sc.nextLine(); 
                continue;
            }
            
            switch (opcion) {
                case 1:
                articulos.values().stream().sorted(new ComparadorPorOrden()).forEach(System.out::println);   
                  
                break;
              case 2:
            System.out.println("\n\n\n\n\n\t\t\t\t SECCIONES");
            int opcion2 = 0;
            do {
                // Mostrar las opciones de secciones
                System.out.println("\t\t\t\t1 - PERIFERICOS");
                System.out.println("\t\t\t\t2 - DISCOS DUROS");
                System.out.println("\t\t\t\t3 - IMPRESORAS");
                System.out.println("\t\t\t\t4 - MONITORES");
                System.out.println("\t\t\t\t5 - SALIR");

                try {
                    opcion2 = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Introduce un número válido.");
                    sc.nextLine();  
                    continue;
                }

                
                switch (opcion2) {
                    case 1:
                        System.out.println("Mostrando Perifericos");
                      mostrarArticulosPorSeccion(articulos, "1");
                      
                        break;

                    case 2:
                        System.out.println("Mostrando Discos Duros");
                    mostrarArticulosPorSeccion(articulos, "2");
                      
                        break;

                    case 3:
                        System.out.println("Mostrando Impresoras");
                    mostrarArticulosPorSeccion(articulos, "3");
                        break;

                    case 4:
                    System.out.println("Mostrando Monitores");
                    mostrarArticulosPorSeccion(articulos, "4");    
                      break;
                      
                    case 5:
                        System.out.println("Saliendo de secciones...");
                        break;

                    default:
                        System.out.println("Opcion no valida.");
                        break;
                }
            } while (opcion2 != 5); 
            break;
    }  } while (opcion != 3);
        
    }
        
     private void reponerArticulos(){
         System.out.println("Introduce el codigo del articulo a reponer");
         String id = sc.next();
         if(!articulos.containsKey(id)){
             System.out.println("El articulo no existe");
             return;
         }
         int existencias = articulos.get(id).getExistencias();
         System.out.println("La cantidad es actual de existencias es : " + existencias + " unidades.");
         System.out.println("Cuantas unidades desea reponer?");
         int reponer = sc.nextInt();
         System.out.println("Articulos anadidos");
         articulos.get(id).setExistencias(existencias + reponer);
         System.out.println("La cantidad se ha actualizado a " + articulos.get(id).getExistencias());
         
              }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="GESTIÓN DE CLIENTES">
    private void nuevoCliente() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el DNI del nuevo cliente: ");
        String dni = sc.next().toUpperCase();
        if (clientes.containsKey(dni)) {
            System.out.println("El cliente ya existe.");
            return;
        }
        sc.nextLine(); // limpiar buffer
        System.out.print("Introduce el nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce el teléfono: ");
        String telefono = sc.next();
        System.out.print("Introduce el email: ");
        String email = sc.next();
        clientes.put(dni, new Cliente(dni, nombre, telefono, email));
        System.out.println("Cliente añadido.");
    }
    
    private void modificarCliente() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el DNI del cliente a modificar: ");
        String dni = sc.next().toUpperCase();
        if (!clientes.containsKey(dni)) {
            System.out.println("El cliente no existe.");
            return;
        }
        Cliente cliente = clientes.get(dni);
        System.out.println("Cliente actual: " + cliente);
        sc.nextLine(); // limpiar buffer
        System.out.print("Introduce el nuevo nombre (dejar en blanco para no cambiar): ");
        String nombre = sc.nextLine();
        if (!nombre.isBlank()) {
            cliente.setNombre(nombre);
        }
        System.out.print("Introduce el nuevo teléfono (dejar en blanco para no cambiar): ");
        String telefono = sc.nextLine();
        if (!telefono.isBlank()) {
            cliente.setTelefono(telefono);
        }
        System.out.print("Introduce el nuevo email (dejar en blanco para no cambiar): ");
        String email = sc.nextLine();
        if (!email.isBlank()) {
            cliente.setEmail(email);
        }
        System.out.println("Cliente modificado.");
    }
    
    private void eliminarCliente() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el DNI del cliente a eliminar: ");
        String dni = sc.next().toUpperCase();
        if (!clientes.containsKey(dni)) {
            System.out.println("El cliente no existe.");
            return;
        }
        clientes.remove(dni);
        System.out.println("Cliente eliminado.");
    }
    
    private void listadoClientes() {
        System.out.println("Listado de Clientes:");
        for (Cliente c : clientes.values()) {
            System.out.println(c);
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="OTROS MÉTODOS">
     public static void mostrarArticulosPorSeccion(Map<String, Articulo> articulos, String seccion) {
        articulos.values().stream().filter(articulo -> articulo.getIdArticulo().startsWith(seccion)) .sorted(new Articulo.ComparadorPorSeccion()) .forEach(System.out::println);
    }
    public double totalPedido (Pedido p){
        double total=0;
                for (LineaPedido L: p.getCestaCompra()){
                    
                
                    
        total+=(articulos.get(L.getIdArticulo()).getPvp())
                *L.getUnidades();
    }      
            return total;
    }         
    public void cargaDatos() {
        // Clientes iniciales
        clientes.put("80580845T", new Cliente("80580845T", "ANA", "658111111", "ana@gmail.com"));
        clientes.put("36347775R", new Cliente("36347775R", "LOLA", "649222222", "lola@gmail.com"));
        clientes.put("63921307Y", new Cliente("63921307Y", "JUAN", "652333333", "juan@gmail.com"));
        clientes.put("02337565Y", new Cliente("02337565Y", "EDU", "634567890", "edu@gmail.com"));
              
        // Artículos iniciales
        articulos.put("1-11", new Articulo("1-11", "RATON LOGITECH ST", 14, 15));
        articulos.put("1-22", new Articulo("1-22", "TECLADO STANDARD", 9, 18));
        articulos.put("2-11", new Articulo("2-11", "HDD SEAGATE 1 TB", 16, 80));
        articulos.put("2-22", new Articulo("2-22", "SSD KINGSTOM 256GB", 9, 70));
        articulos.put("2-33", new Articulo("2-33", "SSD KINGSTOM 512GB", 0, 200));
        articulos.put("3-22", new Articulo("3-22", "EPSON PRINT XP300", 5, 80));
        articulos.put("4-11", new Articulo("4-11", "ASUS MONITOR 22", 5, 100));
        articulos.put("4-22", new Articulo("4-22", "HP MONITOR LED 28", 5, 180));
        articulos.put("4-33", new Articulo("4-33", "SAMSUNG ODISSEY G5", 12, 580));
       
        LocalDate hoy = LocalDate.now();
        pedidos.add(new Pedido("80580845T-001/2024", clientes.get("80580845T"), hoy.minusDays(1),
            new ArrayList<>(List.of(new LineaPedido("1-11", 3), new LineaPedido("4-22", 3)))));
        pedidos.add(new Pedido("80580845T-002/2024", clientes.get("80580845T"), hoy.minusDays(2),
            new ArrayList<>(List.of(new LineaPedido("4-11", 3), new LineaPedido("4-22", 2), new LineaPedido("4-33", 4)))));
        pedidos.add(new Pedido("36347775R-001/2024", clientes.get("36347775R"), hoy.minusDays(3),
            new ArrayList<>(List.of(new LineaPedido("4-22", 1), new LineaPedido("2-22", 3)))));
        pedidos.add(new Pedido("36347775R-002/2024", clientes.get("36347775R"), hoy.minusDays(5),
            new ArrayList<>(List.of(new LineaPedido("4-33", 3), new LineaPedido("2-11", 3)))));
        pedidos.add(new Pedido("63921307Y-001/2024", clientes.get("63921307Y"), hoy.minusDays(4),
            new ArrayList<>(List.of(new LineaPedido("2-11", 5), new LineaPedido("2-33", 3), new LineaPedido("4-33", 2)))));
    }
    //</editor-fold>
}
