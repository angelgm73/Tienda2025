
package educasturangelgm73.tienda2025;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Examenpersistencia {

    Scanner sc = new Scanner(System.in);
    private ArrayList<Pedido> pedidos;
    private HashMap<String, Articulo> articulos;
    private HashMap<String, Cliente> clientes;

    public Examenpersistencia() {
        pedidos = new ArrayList();
        articulos = new HashMap();
        clientes = new HashMap();
    }


    public static void main(String[] args) {
        Examenpersistencia t = new Examenpersistencia();

        t.cargaDatos();
        t.iniciosesion();

    }
    private void iniciosesion(){
        System.out.println("Bienvenido al sistema de tienda");
        System.out.println("Deberas iniciar session para continuar");
        int opcion = 0;
        do {
            System.out.println("\n\n\n");
            System.out.println("\t\t╔════════════════════════════════════════════════╗");
            System.out.println("\t\t║        Bienvenido al sistema de tienda         ║");
            System.out.println("\t\t╠        Deberas iniciar sesion para continuar   ╣");
            System.out.println("\t\t╠════════════════════════════════════════════════╣");
            System.out.println("\t\t║  1 - Iniciar sesion                            ║");
            System.out.println("\t\t║  2 - Registrar usuario                         ║");
            System.out.println("\t\t║  3 - Eliminar usuario                          ║");
            System.out.println("\t\t║                                                ║");
            System.out.println("\t\t║                                                ║");
            System.out.println("\t\t║  9 - SALIR                                     ║");
            System.out.println("\t\t╚════════════════════════════════════════════════╝");
            System.out.print("\t\tSeleccione una opción: ");

            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\t\t⚠️  Introduce un número válido.");
                sc.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    iniciosesion1();
                    break;
                case 2:
                    registrarUsuario();
                    break;
                case 3:
                    clientesTxtLeerCon();
                    break;
                case 4:
                    clientesTxtLeerSin();
                    break;
            }
        } while (opcion != 9);


    }

    private void iniciosesion1() {
        System.out.println("Introduce tu nombre de usuario");
        sc.nextLine(); // Clear scanner buffer
        String nombre = sc.nextLine();
        System.out.println("Introduce tu contraseña");
        String contraseña = sc.nextLine();

        boolean credencialesValidas = false;

        try (Scanner fileScanner = new Scanner(new File("usuarios.csv"))) {
            while (fileScanner.hasNextLine()) {
                String[] userInfo = fileScanner.nextLine().split(",");
                if (userInfo.length >= 3 && userInfo[0].equals(nombre) && userInfo[2].equals(contraseña)) {
                    credencialesValidas = true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo usuarios.csv no existe. Registre usuarios primero.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        if (credencialesValidas) {
            System.out.println("Inicio de sesión exitoso. Bienvenido " + nombre + "!");
            menu();
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
        }
    }

    private void registrarUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce tu nombre de usuario");
        String nombre = sc.nextLine();
    //
        System.out.println("Introduce tu correo electronico ");
        String correo = sc.nextLine();

        System.out.println("Introduce tu contrasena");
        String contrasena = sc.nextLine();

        // Usar true como segundo parámetro para añadir al archivo en lugar de sobrescribirlo
        try (BufferedWriter bfwClientesCon = new BufferedWriter(new FileWriter("usuarios.csv", true))) {
            bfwClientesCon.write(nombre + "," + correo + "," + contrasena + "\n");
            System.out.println("Usuario registrado correctamente");
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    private void menu() {
        int opcion = 0;
        do {
            System.out.println("\n\n\n");
            System.out.println("\t\t╔════════════════════════════════════════════════╗");
            System.out.println("\t\t║                    TIENDA                      ║");
            System.out.println("\t\t╠════════════════════════════════════════════════╣");
            System.out.println("\t\t║  1 - Hacer csv de ambos clientes               ║");
            System.out.println("\t\t║  2 - Clientes con mas de 1000 €                ║");
            System.out.println("\t\t║  3 - Leer csv de clientes con pedidos          ║");
            System.out.println("\t\t║  4 - Leer csv de clientes sin pedidos          ║");
            System.out.println("\t\t║                                                ║");
            System.out.println("\t\t║  9 - SALIR                                     ║");
            System.out.println("\t\t╚════════════════════════════════════════════════╝");
            System.out.print("\t\tSeleccione una opción: ");

            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\t\t⚠️  Introduce un número válido.");
                sc.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    exportarTodosClientes();
                    break;
                case 2:
                 clientesTxtLeer1000();
                    break;
                case 3:
                    clientesTxtLeerCon();
                    break;
                case 4:
                    clientesTxtLeerSin();
                    break;
            }
        } while (opcion != 9);
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

    private void exportarTodosClientes() {
        try (BufferedWriter bfwClientesCon = new BufferedWriter(new FileWriter("clientescon.csv"));
             BufferedWriter bfwClientes1000 = new BufferedWriter(new FileWriter("clientes1000.csv"));
             BufferedWriter bfwClientesSin = new BufferedWriter(new FileWriter("clientessin.csv"))) {

            for (Cliente c : clientes.values()) {
                String lineaCliente = c.getDni() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail() + "\n";

                // Pasar objeto Cliente en lugar de DNI
                if (gastoTotal(c) >= 1000) {
                    bfwClientes1000.write(lineaCliente);
                }

                if (pedidoRealizado(c.getDni())) {
                    bfwClientesCon.write(lineaCliente);
                } else {
                    bfwClientesSin.write(lineaCliente);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }


    private void clientesSin() {
        try (BufferedWriter bfwClientes = new BufferedWriter(new FileWriter("clientesSin.csv"))) {
            for (Cliente c : clientes.values()) {

                if (!pedidoRealizado(c.getDni())) {
                    bfwClientes.write(c.getDni() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail() + "\n");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }

    private double gastoTotal(Cliente c) {
        double total = 0;
        for (Pedido pedido : pedidos) {
            if (pedido.getClientePedido().equals(c)) {
             total+=total+totalPedido(pedido);
            }
        }
        return total;
    }
    private boolean pedidoRealizado(String dni) {
        return
                pedidos.stream().anyMatch(p -> p.getClientePedido().getDni().equals(dni));
    }

    public void clientesTxtLeerCon() {
        // LEEMOS LOS CLIENTES DESDE EL ARCHIVO .csv A UNA COLECCION HASHMAP AUXILIAR Y LA IMPRIMIMOS
        HashMap<String, Cliente> clientesAux = new HashMap();
        try (Scanner scClientes = new Scanner(new File("clientescon.csv"))) {
            while (scClientes.hasNextLine()) {
                String[] atributos = scClientes.nextLine().split("[,]");
                Cliente c = new Cliente(atributos[0], atributos[1], atributos[2], atributos[3]);
                clientesAux.put(atributos[0], c);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        clientesAux.values().forEach(System.out::println);
    }

    public void clientesTxtLeerSin() {
        // LEEMOS LOS CLIENTES DESDE EL ARCHIVO .csv A UNA COLECCION HASHMAP AUXILIAR Y LA IMPRIMIMOS
        HashMap<String, Cliente> clientesAux = new HashMap();
        try (Scanner scClientes = new Scanner(new File("clientesSin.csv"))) {
            while (scClientes.hasNextLine()) {
                String[] atributos = scClientes.nextLine().split("[,]");
                Cliente c = new Cliente(atributos[0], atributos[1], atributos[2], atributos[3]);
                clientesAux.put(atributos[0], c);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        clientesAux.values().forEach(System.out::println);
    }
    public void clientesTxtLeer1000() {
        // LEEMOS LOS CLIENTES DESDE EL ARCHIVO .csv A UNA COLECCION HASHMAP AUXILIAR Y LA IMPRIMIMOS
        HashMap<String, Cliente> clientesAux = new HashMap();
        try (Scanner scClientes = new Scanner(new File("clientes1000.csv"))) {
            while (scClientes.hasNextLine()) {
                String[] atributos = scClientes.nextLine().split("[,]");
                Cliente c = new Cliente(atributos[0], atributos[1], atributos[2], atributos[3]);
                clientesAux.put(atributos[0], c);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        clientesAux.values().forEach(System.out::println);
    }
    public double totalPedido (Pedido p){
        double total=0;
        for (LineaPedido L: p.getCestaCompra()){



            total+=(articulos.get(L.getIdArticulo()).getPvp())
                    *L.getUnidades();
        }
        return total;
    }
}