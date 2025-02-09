/** package educasturangelgm73.tienda2025;

import educasturangelgm73.tienda2025.Excepciones.StockAgotado;
import educasturangelgm73.tienda2025.Excepciones.StockInsuficiente;
import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;


    private Scanner sc = new Scanner(System.in);
    private HashMap<String, Cliente> clientes = new HashMap<>();
    private HashMap<String, Articulo> articulos = new HashMap<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    // Expresiones regulares para validar entradas
    private static final Pattern dniPattern = Pattern.compile("\\d{8}[A-Z]");
    private static final Pattern telefonoPattern = Pattern.compile("\\d{9}");
    private static final Pattern emailPattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$");
    private static final Pattern idArticuloPattern = Pattern.compile("[1-5]-\\d{2}");
    private static final Pattern idPedidoPattern = Pattern.compile("\\d{8}[A-Z]-\\d{3}/\\d{4}");
    
    // Singleton para acceder a la instancia desde totalPedido
    private static Tienda2025 instance;
    public Tienda2025() { instance = this; }
    public static Tienda2025 getInstance() { return instance; }
    
    public static void main(String[] args) {
        Tienda2025 tienda = new Tienda2025();
        tienda.inicializarDatos();
        tienda.menu();
    }
    
    // Se intenta cargar los datos desde archivos .dat; si no existen se usan datos por defecto.
    private void inicializarDatos() {
        boolean cargado = cargarDatos();
        if (!cargado) {
            cargaDatosPorDefecto();
        }
    }
    
    // Cargar desde archivos .dat
    private boolean cargarDatos() {
        boolean exito = false;
        try (ObjectInputStream oisClientes = new ObjectInputStream(new FileInputStream("clientes.dat"));
             ObjectInputStream oisArticulos = new ObjectInputStream(new FileInputStream("articulos.dat"));
             ObjectInputStream oisPedidos = new ObjectInputStream(new FileInputStream("pedidos.dat"))) {
            clientes = (HashMap<String, Cliente>) oisClientes.readObject();
            articulos = (HashMap<String, Articulo>) oisArticulos.readObject();
            pedidos = (ArrayList<Pedido>) oisPedidos.readObject();
            exito = true;
            System.out.println("Datos cargados desde archivos.");
        } catch (Exception e) {
            System.out.println("No se pudieron cargar los datos desde archivos. Se cargarán datos por defecto.");
        }
        return exito;
    }
    
    // Datos por defecto (si no se han cargado desde fichero)
    private void cargaDatosPorDefecto() {
        // Clientes
        clientes.put("80580845T", new Cliente("80580845T", "ANA", "658111111", "ana@gmail.com"));
        clientes.put("36347775R", new Cliente("36347775R", "LOLA", "649222222", "lola@gmail.com"));
        clientes.put("63921307Y", new Cliente("63921307Y", "JUAN", "652333333", "juan@gmail.com"));
        clientes.put("02337565Y", new Cliente("02337565Y", "EDU", "634567890", "edu@gmail.com"));
        
        // Artículos
        articulos.put("1-11", new Articulo("1-11", "RATON LOGITECH ST", 14, 15));
        articulos.put("1-22", new Articulo("1-22", "TECLADO STANDARD", 9, 18));
        articulos.put("2-11", new Articulo("2-11", "HDD SEAGATE 1 TB", 16, 80));
        articulos.put("2-22", new Articulo("2-22", "SSD KINGSTOM 256GB", 9, 70));
        articulos.put("2-33", new Articulo("2-33", "SSD KINGSTOM 512GB", 0, 200));
        articulos.put("3-22", new Articulo("3-22", "EPSON PRINT XP300", 5, 80));
        articulos.put("4-11", new Articulo("4-11", "ASUS MONITOR 22", 5, 100));
        articulos.put("4-22", new Articulo("4-22", "HP MONITOR LED 28", 5, 180));
        articulos.put("4-33", new Articulo("4-33", "SAMSUNG ODISSEY G5", 12, 580));
        
        // Pedidos de ejemplo
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
    
    // Menú principal
    private void menu() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1 - GESTIÓN DE CLIENTES");
            System.out.println("2 - GESTIÓN DE ARTÍCULOS");
            System.out.println("3 - GESTIÓN DE PEDIDOS");
            System.out.println("4 - COPIA DE SEGURIDAD");
            System.out.println("9 - SALIR");
            opcion = leerEntero("Seleccione una opción: ");
            switch(opcion) {
                case 1 -> menuClientes();
                case 2 -> menuArticulos();
                case 3 -> menuPedidos();
                case 4 -> {
                    guardarDatos();
                    guardarClientesCSV();
                }
            }
        } while(opcion != 9);
    }
    
    // ******************** GESTIÓN DE CLIENTES ********************
    private void menuClientes() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE CLIENTES ---");
            System.out.println("1 - AÑADIR CLIENTE");
            System.out.println("2 - MODIFICAR CLIENTE (sólo Teléfono y Email)");
            System.out.println("3 - LISTAR CLIENTES");
            System.out.println("4 - BORRAR CLIENTE");
            System.out.println("9 - VOLVER");
            opcion = leerEntero("Seleccione una opción: ");
            switch(opcion) {
                case 1 -> nuevoCliente();
                case 2 -> modificarCliente();
                case 3 -> listarClientes();
                case 4 -> eliminarCliente();
            }
        } while(opcion != 9);
    }
    
    private void nuevoCliente() {
        String dni = leerCadena("Introduce DNI (8 dígitos y 1 letra, ej: 12345678A): ").toUpperCase();
        if(!dniPattern.matcher(dni).matches()) {
            System.out.println("Formato de DNI incorrecto.");
            return;
        }
        if(clientes.containsKey(dni)) {
            System.out.println("El cliente ya existe.");
            return;
        }
        sc.nextLine(); // limpiar buffer
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        String telefono = leerCadena("Teléfono (9 dígitos): ");
        if(!telefonoPattern.matcher(telefono).matches()) {
            System.out.println("Formato de teléfono incorrecto.");
            return;
        }
        String email = leerCadena("Email: ");
        if(!emailPattern.matcher(email).matches()) {
            System.out.println("Formato de email incorrecto.");
            return;
        }
        clientes.put(dni, new Cliente(dni, nombre, telefono, email));
        System.out.println("Cliente añadido correctamente.");
    }
    
    private void modificarCliente() {
        String dni = leerCadena("Introduce DNI del cliente a modificar: ").toUpperCase();
        Cliente c = clientes.get(dni);
        if(c == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        String nuevoTel = leerCadena("Nuevo teléfono (dejar en blanco para no cambiar): ");
        if(!nuevoTel.isBlank()) {
            if(!telefonoPattern.matcher(nuevoTel).matches()) {
                System.out.println("Formato de teléfono incorrecto.");
            } else {
                c.setTelefono(nuevoTel);
            }
        }
        sc.nextLine(); // limpiar buffer
        System.out.print("Nuevo email (dejar en blanco para no cambiar): ");
        String nuevoEmail = sc.nextLine();
        if(!nuevoEmail.isBlank()) {
            if(!emailPattern.matcher(nuevoEmail).matches()) {
                System.out.println("Formato de email incorrecto.");
            } else {
                c.setEmail(nuevoEmail);
            }
        }
        System.out.println("Cliente modificado.");
    }
    
    private void listarClientes() {
        System.out.println("--- LISTADO DE CLIENTES ---");
        clientes.values().stream()
                .sorted()
                .forEach(System.out::println);
    }
    
    private void eliminarCliente() {
        String dni = leerCadena("Introduce DNI del cliente a eliminar: ").toUpperCase();
        if(clientes.remove(dni) != null) {
            System.out.println("Cliente eliminado.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }
    
    // ******************** GESTIÓN DE ARTÍCULOS ********************
    private void menuArticulos() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE ARTÍCULOS ---");
            System.out.println("1 - AÑADIR ARTÍCULO");
            System.out.println("2 - MODIFICAR ARTÍCULO");
            System.out.println("3 - BORRAR ARTÍCULO");
            System.out.println("4 - REPONER ARTÍCULOS (con stock 0)");
            System.out.println("5 - LISTAR ARTÍCULOS");
            System.out.println("9 - VOLVER");
            opcion = leerEntero("Seleccione una opción: ");
            switch(opcion) {
                case 1 -> nuevoArticulo();
                case 2 -> modificarArticulo();
                case 3 -> eliminarArticulo();
                case 4 -> reponerArticulos();
                case 5 -> listarArticulos();
            }
        } while(opcion != 9);
    }
    
    private void nuevoArticulo() {
        String id = leerCadena("Introduce ID del artículo (ej: 1-11): ");
        if(!idArticuloPattern.matcher(id).matches()) {
            System.out.println("Formato de ID de artículo incorrecto.");
            return;
        }
        if(articulos.containsKey(id)) {
            System.out.println("El artículo ya existe.");
            return;
        }
        sc.nextLine(); // limpiar buffer
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();
        int existencias = leerEntero("Número de existencias: ");
        double pvp = leerDouble("Precio: ");
        articulos.put(id, new Articulo(id, descripcion, existencias, pvp));
        System.out.println("Artículo añadido.");
    }
    
    private void modificarArticulo() {
        String id = leerCadena("Introduce ID del artículo a modificar: ");
        Articulo a = articulos.get(id);
        if(a == null) {
            System.out.println("Artículo no encontrado.");
            return;
        }
        sc.nextLine(); // limpiar buffer
        System.out.print("Nueva descripción (dejar en blanco para no cambiar): ");
        String descripcion = sc.nextLine();
        if(!descripcion.isBlank()) a.setDescripcion(descripcion);
        int existencias = leerEntero("Nuevas existencias (ingrese -1 para no cambiar): ");
        if(existencias != -1) a.setExistencias(existencias);
        double pvp = leerDouble("Nuevo precio (ingrese -1 para no cambiar): ");
        if(pvp != -1) a.setPvp(pvp);
        System.out.println("Artículo modificado.");
    }
    
    private void eliminarArticulo() {
        String id = leerCadena("Introduce ID del artículo a borrar: ");
        if(articulos.remove(id) != null) {
            System.out.println("Artículo eliminado.");
        } else {
            System.out.println("Artículo no encontrado.");
        }
    }
    
    private void reponerArticulos() {
        System.out.println("--- Artículos con stock 0 ---");
        List<Articulo> sinStock = articulos.values().stream()
                .filter(a -> a.getExistencias() == 0)
                .collect(Collectors.toList());
        if(sinStock.isEmpty()) {
            System.out.println("No hay artículos con stock 0.");
            return;
        }
        sinStock.forEach(System.out::println);
        String id = leerCadena("Introduce ID del artículo a reponer: ");
        Articulo a = articulos.get(id);
        if(a == null) {
            System.out.println("Artículo no encontrado.");
            return;
        }
        int unidades = leerEntero("Número de unidades a añadir: ");
        a.setExistencias(a.getExistencias() + unidades);
        System.out.println("Artículo repuesto. Nuevo stock: " + a.getExistencias());
    }
    
    private void listarArticulos() {
        System.out.println("--- LISTADO DE ARTÍCULOS ---");
        System.out.println("1 - Listar TODOS");
        System.out.println("2 - Listar por SECCIÓN");
        int op = leerEntero("Seleccione una opción: ");
        List<Articulo> lista;
        if(op == 1) {
            lista = new ArrayList<>(articulos.values());
        } else if(op == 2) {
            System.out.println("Secciones: 1: PERIFÉRICOS, 2: ALMACENAMIENTO, 3: IMPRESORAS, 4: MONITORES, 5: COMPONENTES");
            String seccion = leerCadena("Introduce el número de sección: ");
            lista = articulos.values().stream()
                    .filter(a -> a.getIdArticulo().startsWith(seccion))
                    .collect(Collectors.toList());
        } else {
            System.out.println("Opción no válida.");
            return;
        }
        System.out.println("Ordenar por: 1 - ID (default)  2 - Precio Ascendente  3 - Precio Descendente");
        int ord = leerEntero("Seleccione opción de ordenamiento: ");
        if(ord == 2) {
            lista = lista.stream()
                    .sorted(Comparator.comparing(Articulo::getPvp))
                    .collect(Collectors.toList());
        } else if(ord == 3) {
            lista = lista.stream()
                    .sorted(Comparator.comparing(Articulo::getPvp).reversed())
                    .collect(Collectors.toList());
        } else {
            lista = lista.stream()
                    .sorted()
                    .collect(Collectors.toList());
        }
        lista.forEach(System.out::println);
    }
    
    // ******************** GESTIÓN DE PEDIDOS ********************
    private void menuPedidos() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE PEDIDOS ---");
            System.out.println("1 - NUEVO PEDIDO");
            System.out.println("2 - MODIFICAR PEDIDO (añadir línea)");
            System.out.println("3 - ELIMINAR PEDIDO");
            System.out.println("4 - LISTAR PEDIDOS");
            System.out.println("9 - VOLVER");
            opcion = leerEntero("Seleccione una opción: ");
            switch(opcion) {
                case 1 -> nuevoPedido();
                case 2 -> modificarPedido();
                case 3 -> eliminarPedido();
                case 4 -> listarPedidos();
            }
        } while(opcion != 9);
    }
    
    private void nuevoPedido() {
        ArrayList<LineaPedido> cesta = new ArrayList<>();
        String dni = leerCadena("Introduce DNI del cliente: ").toUpperCase();
        if(!dniPattern.matcher(dni).matches() || !clientes.containsKey(dni)) {
            System.out.println("Cliente no encontrado o DNI inválido.");
            return;
        }
        String idArt;
        do {
            idArt = leerCadena("Introduce ID de artículo (o 99 para terminar): ");
            if(idArt.equals("99")) break;
            if(!idArticuloPattern.matcher(idArt).matches() || !articulos.containsKey(idArt)) {
                System.out.println("Artículo no encontrado o ID inválido.");
                continue;
            }
            int unidades = leerEntero("Unidades: ");
            try {
                verificarStock(unidades, idArt);
                cesta.add(new LineaPedido(idArt, unidades));
            } catch(StockAgotado e) {
                System.out.println(e.getMessage());
            } catch(StockInsuficiente e) {
                System.out.println(e.getMessage());
                int disponibles = articulos.get(idArt).getExistencias();
                String resp = leerCadena("¿Desea adquirir las " + disponibles + " unidades disponibles? (S/N): ");
                if(resp.equalsIgnoreCase("S")) {
                    cesta.add(new LineaPedido(idArt, disponibles));
                }
            }
        } while(!idArt.equals("99"));
        if(cesta.isEmpty()) {
            System.out.println("Pedido cancelado, cesta vacía.");
            return;
        }
        // Mostrar resumen del pedido
        System.out.println("Resumen del pedido:");
        cesta.forEach(lp -> System.out.println(articulos.get(lp.getIdArticulo()).getDescripcion() + " - Unidades: " + lp.getUnidades()));
        String confirm = leerCadena("¿Confirmar pedido? (S/N): ");
        if(confirm.equalsIgnoreCase("S")) {
            String idPedido = generaIdPedido(dni);
            Pedido p = new Pedido(idPedido, clientes.get(dni), LocalDate.now(), cesta);
            pedidos.add(p);
            // Descontar stock
            for(LineaPedido lp : cesta) {
                Articulo a = articulos.get(lp.getIdArticulo());
                a.setExistencias(a.getExistencias() - lp.getUnidades());
            }
            System.out.println("Pedido registrado con ID: " + idPedido);
        } else {
            System.out.println("Pedido cancelado.");
        }
    }
    
    private void modificarPedido() {
        String idPedido = leerCadena("Introduce ID del pedido a modificar: ");
        if(!idPedidoPattern.matcher(idPedido).matches()) {
            System.out.println("Formato de ID de pedido incorrecto.");
            return;
        }
        Pedido pedido = pedidos.stream()
                .filter(p -> p.getIdPedido().equalsIgnoreCase(idPedido))
                .findFirst()
                .orElse(null);
        if(pedido == null) {
            System.out.println("Pedido no encontrado.");
            return;
        }
        String idArt = leerCadena("Introduce ID de artículo para añadir: ");
        if(!idArticuloPattern.matcher(idArt).matches() || !articulos.containsKey(idArt)) {
            System.out.println("Artículo no encontrado o ID inválido.");
            return;
        }
        int unidades = leerEntero("Unidades: ");
        try {
            verificarStock(unidades, idArt);
            pedido.getCestaCompra().add(new LineaPedido(idArt, unidades));
            Articulo a = articulos.get(idArt);
            a.setExistencias(a.getExistencias() - unidades);
            System.out.println("Línea añadida al pedido.");
        } catch(StockAgotado | StockInsuficiente e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void eliminarPedido() {
        String idPedido = leerCadena("Introduce ID del pedido a eliminar: ");
        if(!idPedidoPattern.matcher(idPedido).matches()) {
            System.out.println("Formato de ID de pedido incorrecto.");
            return;
        }
        Pedido p = pedidos.stream()
                .filter(pe -> pe.getIdPedido().equalsIgnoreCase(idPedido))
                .findFirst()
                .orElse(null);
        if(p == null) {
            System.out.println("Pedido no encontrado.");
            return;
        }
        // Devolver stock
        for(LineaPedido lp : p.getCestaCompra()) {
            Articulo a = articulos.get(lp.getIdArticulo());
            a.setExistencias(a.getExistencias() + lp.getUnidades());
        }
        pedidos.remove(p);
        System.out.println("Pedido eliminado.");
    }
    
    private void listarPedidos() {
        System.out.println("\n--- LISTADO DE PEDIDOS ---");
        System.out.println("1 - Ordenados por Importe total");
        System.out.println("2 - Ordenados por Fecha (detalle línea a línea)");
        System.out.println("3 - Pedidos con total superior a un importe");
        int op = leerEntero("Seleccione una opción: ");
        switch(op) {
            case 1 -> listarPedidosPorImporte();
            case 2 -> listarPedidosPorFecha();
            case 3 -> listarPedidosPorImporteMayor();
            default -> System.out.println("Opción no válida.");
        }
    }
    
    private void listarPedidosPorImporte() {
        System.out.println("--- Pedidos ordenados por Importe total ---");
        pedidos.stream()
                .sorted(Comparator.comparingDouble(Tienda2025::totalPedido))
                .forEach(p -> System.out.println(p.getIdPedido() + " - " + p.getClientePedido().getDni() + " - " + p.getClientePedido().getNombre() + " - " + df.format(totalPedido(p))));
    }
    
    private void listarPedidosPorFecha() {
        System.out.println("--- Pedidos ordenados por Fecha ---");
        pedidos.stream()
                .sorted(Comparator.comparing(Pedido::getFecha))
                .forEach(System.out::println);
    }
    
    private void listarPedidosPorImporteMayor() {
        double importe = leerDouble("Introduce el importe mínimo: ");
        System.out.println("--- Pedidos con total superior a " + df.format(importe) + " ---");
        pedidos.stream()
                .filter(p -> totalPedido(p) > importe)
                .forEach(System.out::println);
    }
    
    // Método para calcular el total de un pedido
    public static double totalPedido(Pedido p) {
        double total = 0;
        // Se toma el precio actual del artículo. (Si el artículo se borra, no se podrá calcular; se asume que existe)
        for (LineaPedido lp : p.getCestaCompra()) {
            Articulo a = Tienda2025.getInstance().articulos.get(lp.getIdArticulo());
            if (a != null) {
                total += a.getPvp() * lp.getUnidades();
            }
        }
        return total;
    }
    
    // Verificar stock para una solicitud de pedido
    private void verificarStock(int unidades, String idArticulo) throws StockAgotado, StockInsuficiente {
        Articulo a = articulos.get(idArticulo);
        if(a.getExistencias() == 0) {
            throw new StockAgotado("Stock AGOTADO para el artículo: " + a.getDescripcion());
        } else if(a.getExistencias() < unidades) {
            throw new StockInsuficiente("Stock insuficiente para " + a.getDescripcion() + ". Disponible: " + a.getExistencias());
        }
    }
    
    // Genera el ID de pedido en formato DNI-XXX/AAAA (XXX: consecutivo de 3 dígitos)
    private String generaIdPedido(String dni) {
        long count = pedidos.stream()
                .filter(p -> p.getClientePedido().getDni().equalsIgnoreCase(dni))
                .count();
        count++;
        String num = String.format("%03d", count);
        String year = String.valueOf(LocalDate.now().getYear());
        return dni + "-" + num + "/" + year;
    }
    
    // ******************** PERSISTENCIA ********************
    private void guardarDatos() {
        guardarObjeto(clientes, "clientes.dat");
        guardarObjeto(articulos, "articulos.dat");
        guardarObjeto(pedidos, "pedidos.dat");
        System.out.println("Datos guardados en archivos .dat");
    }
    
    private void guardarObjeto(Object obj, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            System.out.println("Error al guardar " + fileName + ": " + e.getMessage());
        }
    }
    
    // Guarda la colección de clientes en formato CSV en la subcarpeta /clientes/
    private void guardarClientesCSV() {
        File dir = new File("clientes");
        if(!dir.exists()) {
            dir.mkdir();
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(dir, "clientes.csv")))) {
            for(Cliente c : clientes.values()) {
                pw.println(c.getDni() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail());
            }
            System.out.println("Clientes guardados en CSV.");
        } catch(IOException e) {
            System.out.println("Error al guardar clientes.csv: " + e.getMessage());
        }
    }
    
    // ******************** MÉTODOS DE LECTURA SEGURA ********************
    private int leerEntero(String mensaje) {
        while(true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.next().trim());
            } catch(NumberFormatException e) {
                System.out.println("Entrada no válida. Intente de nuevo.");
            }
        }
    }
    
    private double leerDouble(String mensaje) {
        while(true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(sc.next().trim());
            } catch(NumberFormatException e) {
                System.out.println("Entrada no válida. Intente de nuevo.");
            }
        }
    }
    
    private String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return sc.next().trim();
    }
}
*/