/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package educasturangelgm73.tienda2025;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Administrator
 */
public class Tienda2025Test {
    
    public Tienda2025Test() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getPedidos method, of class Tienda2025.
     */
    @Test
    public void testGetPedidos() {
        System.out.println("getPedidos");
        Tienda2025 instance = new Tienda2025();
        ArrayList<Pedido> expResult = null;
        ArrayList<Pedido> result = instance.getPedidos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArticulos method, of class Tienda2025.
     */
    @Test
    public void testGetArticulos() {
        System.out.println("getArticulos");
        Tienda2025 instance = new Tienda2025();
        HashMap<String, Articulo> expResult = null;
        HashMap<String, Articulo> result = instance.getArticulos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClientes method, of class Tienda2025.
     */
    @Test
    public void testGetClientes() {
        System.out.println("getClientes");
        Tienda2025 instance = new Tienda2025();
        HashMap<String, Cliente> expResult = null;
        HashMap<String, Cliente> result = instance.getClientes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Tienda2025.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Tienda2025.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stock method, of class Tienda2025.
     */
    @Test
    public void testStock() throws Exception {
        System.out.println("stock");
        int unidadesPed = 0;
        String id = "";
        Tienda2025 instance = new Tienda2025();
        instance.stock(unidadesPed, id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generaIdpedido method, of class Tienda2025.
     */
    @Test
    public void testGeneraIdpedido() {
        System.out.println("generaIdpedido");
        String idCliente = "";
        Tienda2025 instance = new Tienda2025();
        String expResult = "";
        String result = instance.generaIdpedido(idCliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nuevoPedido method, of class Tienda2025.
     */
    @Test
    public void testNuevoPedido() {
        System.out.println("nuevoPedido");
        Tienda2025 instance = new Tienda2025();
        instance.nuevoPedido();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mostrarArticulosPorSeccion method, of class Tienda2025.
     */
    @Test
    public void testMostrarArticulosPorSeccion() {
        System.out.println("mostrarArticulosPorSeccion");
        Map<String, Articulo> articulos = null;
        String seccion = "";
        Tienda2025.mostrarArticulosPorSeccion(articulos, seccion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of totalPedido method, of class Tienda2025.
     */
    @Test
    public void testTotalPedido() {
        System.out.println("totalPedido");
        Pedido p = null;
        Tienda2025 instance = new Tienda2025();
        double expResult = 0.0;
        double result = instance.totalPedido(p);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cargaDatos method, of class Tienda2025.
     */
    @Test
    public void testCargaDatos() {
        System.out.println("cargaDatos");
        Tienda2025 instance = new Tienda2025();
        instance.cargaDatos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of backupPorSeccion method, of class Tienda2025.
     */
    @Test
    public void testBackupPorSeccion() {
        System.out.println("backupPorSeccion");
        Tienda2025 instance = new Tienda2025();
        instance.backupPorSeccion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of backup method, of class Tienda2025.
     */
    @Test
    public void testBackup() {
        System.out.println("backup");
        Tienda2025 instance = new Tienda2025();
        instance.backup();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leerArchivos method, of class Tienda2025.
     */
    @Test
    public void testLeerArchivos() {
        System.out.println("leerArchivos");
        Tienda2025 instance = new Tienda2025();
        instance.leerArchivos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clientesTxtBackup method, of class Tienda2025.
     */
    @Test
    public void testClientesTxtBackup() {
        System.out.println("clientesTxtBackup");
        Tienda2025 instance = new Tienda2025();
        instance.clientesTxtBackup();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clientesTxtLeer method, of class Tienda2025.
     */
    @Test
    public void testClientesTxtLeer() {
        System.out.println("clientesTxtLeer");
        Tienda2025 instance = new Tienda2025();
        instance.clientesTxtLeer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pedidosPorImporteBackup method, of class Tienda2025.
     */
    @Test
    public void testPedidosPorImporteBackup() {
        System.out.println("pedidosPorImporteBackup");
        Tienda2025 instance = new Tienda2025();
        instance.pedidosPorImporteBackup();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leerPedidosPorImporte method, of class Tienda2025.
     */
    @Test
    public void testLeerPedidosPorImporte() {
        System.out.println("leerPedidosPorImporte");
        Tienda2025 instance = new Tienda2025();
        instance.leerPedidosPorImporte();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
