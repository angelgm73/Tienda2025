
package educasturangelgm73.tienda2025;

/**
 *
 * @author alu10d
 */

import java.util.Comparator;

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

    @Override
    public String toString() {
        return String.format("Articulo [ID: %s, Descripcion: %s, Existencias: %d, PVP: %.2f]",
                idArticulo, descripcion, existencias, pvp);
    }

   
    public static class ComparadorPorDescripcion implements Comparator<Articulo> {
        @Override
        public int compare(Articulo a1, Articulo a2) {
            return a1.getDescripcion().compareToIgnoreCase(a2.getDescripcion());
        }
    }

    
    public static class ComparadorPorPrecio implements Comparator<Articulo> {
        @Override
        public int compare(Articulo a1, Articulo a2) {
            return Double.compare(a1.getPvp(), a2.getPvp());
        }
    }
    public static class ComparadorPorExistencias implements Comparator<Articulo> {
        @Override 
        public int compare (Articulo a1, Articulo a2){
           return Integer.compare(a1.getExistencias(), a2.getExistencias());
        }
    }
     public static class ComparadorPorOrden implements Comparator<Articulo> {
        @Override
        public int compare(Articulo a1, Articulo a2) {
               return a1.getIdArticulo().compareTo(a2.getIdArticulo());
        }
}
}       
