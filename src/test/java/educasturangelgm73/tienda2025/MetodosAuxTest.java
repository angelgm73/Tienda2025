/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package educasturangelgm73.tienda2025;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author eduardo
 */
public class MetodosAuxTest {
    
    public MetodosAuxTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        System.out.println("LO QUE HAGO AQUÍ VA DELANTE DE TODOS LOS TEST");
    }
    
    @AfterAll
    public static void tearDownClass() {
        System.out.println("LO QUE HAGO AQUÍ VA DETRÁS DE TODOS LOS TEST");
    }
    
    @BeforeEach
    public void setUp() {
        System.out.println("LO QUE HAGO AQUÍ VA DELANTE DE CADA TEST");
    }
    
    @AfterEach
    public void tearDown() {
        System.out.println("LO QUE HAGO AQUÍ VA DETRÁS DE TODOS LOS TEST");
    }

    @Test
    public void testEsInt() {
       /* CON assertAll Junit nos indicará todos los errores que se produzcan
        en cualquier assert individualmente */
       
        assertAll(
            () -> assertTrue(MetodosAux.esInt("5"),"El 5 es int"),
            () -> assertTrue(MetodosAux.esInt("-5"),"El -5 es int"),
            () -> assertFalse(MetodosAux.esInt("5.5"),"5.5 NO es int"),
            () -> assertFalse(MetodosAux.esInt("dxsfgsdrfg"),"dxsfgsdrfg NO es int")    
        );
        
      /* SIN assertAll Junit nos indicará ERROR en el test, pero sin mostrar 
        indormación detallada de que asserts han fallado
        
      assertTrue(MetodosAux.esInt("5"),"El 5 es int");
      assertTrue(MetodosAux.esInt("-5"),"El -5 es int");
      assertFalse(MetodosAux.esInt("5.5"),"5.5 NO es int");
      assertFalse(MetodosAux.esInt("dxsfgsdrfg"),"dxsfgsdrfg NO es int");
       
      LOS METODOS QUE DEVUELVEN UN VALOR BOOLEAN TAMBIÉN SE PUEDE PROBAR CON assertEquals 
      
      assertEquals(true, MetodosAux.esInt("5"),"El 5 es int");
      assertEquals(true, MetodosAux.esInt("-5"),"El -5 es int");
      assertEquals(false, MetodosAux.esInt("5.5"),"5.5 NO es int");
      assertEquals(false, MetodosAux.esInt("dxsfgsdrfg"),"dxsfgsdrfg NO es int");
      */
    }

    /**
     * Test of esDouble method, of class MetodosAux.
     */
    @Test
    public void testEsDouble() {
        assertAll(
            () -> assertTrue(MetodosAux.esDouble("5"),"El 5 es Double"),
            () -> assertTrue(MetodosAux.esDouble("-5"),"El -5 es un Double"),
            () -> assertTrue(MetodosAux.esDouble("5.5"),"El 5.5 es un Double"),
            () -> assertTrue(MetodosAux.esDouble("-5.5"),"El -5.5 es un Double"),
            () -> assertFalse(MetodosAux.esDouble("dxsfgsdrfg"), "dxsfgsdrfg NO es un double")
        );
    }
   
    @Test
    public void testValidarDNI() {
        assertAll(
           () -> assertTrue(MetodosAux.validarDni("50375889R"), "50375889R DNI válido" ),
           () -> assertTrue(MetodosAux.validarDni("88067157L"), "88067157L DNI válido"),
           () -> assertTrue(MetodosAux.validarDni("98080335D"), "98080335D DNI válido"),
           () -> assertTrue(MetodosAux.validarDni("08194445M"), "08194445M DNI válido"),
           () -> assertTrue(MetodosAux.validarDni("22443479X"),"22443479X DNI válido"),
           () -> assertFalse(MetodosAux.validarDni(""),"Vacío no es DNI válido"),
           () -> assertFalse(MetodosAux.validarDni("22443479R"),"22443479R no es DNI válido")
        );
    }
}