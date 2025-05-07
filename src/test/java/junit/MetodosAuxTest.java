
package junit;

import educasturangelgm73.tienda2025.MetodosAux;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Angel Gonzalez
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
  //EJERCICIO 1.1 
    @Test
    public void testEsInt() {
       /* CON assertAll Junit nos indicará todos los errores que se produzcan
        en cualquier assert individualmente */
       
        assertAll(
            () -> assertTrue(MetodosAux.esInt("5")),
            () -> assertTrue(MetodosAux.esInt("46")),
            () -> assertTrue(MetodosAux.esInt("55")),
            () -> assertFalse(MetodosAux.esInt("8.8")),
            () -> assertFalse(MetodosAux.esInt("-55.5")),
            () -> assertFalse(MetodosAux.esInt("Hola"))
        );
        
     
    }

    /**
     * EJERCICIO 1.2
     */
    @Test
    public void testEsDouble() {
        assertAll(
            () -> assertTrue(MetodosAux.esDouble("8")),
            () -> assertTrue(MetodosAux.esDouble("8.8")),
            () -> assertTrue(MetodosAux.esDouble("55")),
            () -> assertTrue(MetodosAux.esDouble("-55.5")),
            () -> assertFalse(MetodosAux.esDouble("HOLA")),
            () -> assertFalse(MetodosAux.esDouble("E"))
        );
    }
   /**
    * EJERCICIO 1.3
    */
    @Test
    public void testValidarDNI() {
        assertAll(
           () -> assertTrue(MetodosAux.validarDni("90015161S"),"DNI Valido" ),
           () -> assertTrue(MetodosAux.validarDni("90463229C"), "DNI válido"),
           () -> assertFalse(MetodosAux.validarDni("72825528R"),"No es DNI válido"),
           () -> assertFalse(MetodosAux.validarDni("90463229X")," No es DNI válido")
        );
    }
}