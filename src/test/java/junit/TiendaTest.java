package junit;


import educasturangelgm73.tienda2025.*;
import educasturangelgm73.tienda2025.Tienda2025;
import educasturangelgm73.tienda2025.Excepciones.StockAgotado;
import educasturangelgm73.tienda2025.Excepciones.StockInsuficiente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author  Angel Gonzalez 
 */
public class TiendaTest {

    public TiendaTest() {
    }

    Tienda2025 t = new Tienda2025();

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach //dentro del test nos interesa entrar a las colecciones
    public void setUp() {
        t.cargaDatos();
    }

    @AfterEach
    public void tearDown() {
    }

    //nos da un cargadatos y hacer la prueba
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    /*
    *EJERCICIO 2.1 
    */
    @Test
    public void testCargaDatos() {
        assertAll(
                () -> assertEquals(8, t.getArticulos().size()),
                () -> assertEquals(5, t.getClientes().size()),
                () -> assertEquals(5, t.getPedidos().size())
        );

    }

    /*
    *EJERCICIO 2.2
    */
        @Test
    public void testTotalPedido() {
        assertAll(
                () -> assertEquals(1500, t.totalPedido(t.getPedidos().get(0))), //Mayor importe
                () -> assertEquals(285, t.totalPedido(t.getPedidos().get(3))), //Menor importe
                () -> assertEquals(390, t.totalPedido(t.getPedidos().get(2))) //Otro pedido
                
        );
    }
    /*
    *Ejercicio 2.3
    */
    @Test
    public void testTotalCliente() {
        assertAll(
                () -> assertEquals(3565, t.getArticulos().size()),
                () -> assertEquals(2370, t.getClientes().size()),
                () -> assertEquals(2160, t.getPedidos().size()),
                () -> assertEquals(580, t.getClientes().size()),
                () -> assertEquals(190, t.getPedidos().size())
        );
    }

/*
    *Ejercicio 2.4
    */

    @Test
    public void testStock() {
        assertAll(
                () -> assertThrows(StockInsuficiente.class, () -> {
                    t.stock(2, "1-11");
                }, "El Stock es insuficiente"),
                () -> assertThrows(StockAgotado.class, () -> {
                    t.stock(5, "2-33");
                }, "El Stock esta agotado")
        );
    }
}