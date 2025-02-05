
package educasturangelgm73.tienda2025;

/**
 *
 * @author alu10d
 */

public class Articulo {
    
    private String idArticulo;
    private String descripcion;
    private int existencias;
    private double pvp;

    public Articulo(String idArticulo, String descripcion, int existencias, double pvp) {
        this.idArticulo = idArticulo;
        this.descripcion = descripcion;
        this.existencias = existencias;
        this.pvp = pvp;
    }

    public String getIdArticulo() {
        return idArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getExistencias() {
        return existencias;
    }

    public double getPvp() {
        return pvp;
    }
    

    public void setIdArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }
    
}
