
package educasturangelgm73.tienda2025;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author alu10d
 */

public class Pedido implements Serializable {
    
    private String idPedido;
    private Cliente clientePedido;
    private LocalDate fechaPedido;
    private ArrayList<LineaPedido> cestaCompra;

    public Pedido(String idPedido, Cliente clientePedido, LocalDate fechaPedido, ArrayList<LineaPedido> lineaPedido) {
        this.idPedido = idPedido;
        this.clientePedido = clientePedido;
        this.fechaPedido = fechaPedido;
        this.cestaCompra = lineaPedido;
    }

    
    public String getIdPedido() {
        return idPedido;
    }

    public Cliente getClientePedido() {
        return clientePedido;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public ArrayList<LineaPedido> getCestaCompra() {
        return cestaCompra;
    }

    
    
    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public void setClientePedido(Cliente clientePedido) {
        this.clientePedido = clientePedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public void setCestaCompra(ArrayList<LineaPedido> cestaCompra) {
        this.cestaCompra = cestaCompra;
    }
    
}
