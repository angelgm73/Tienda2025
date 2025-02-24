/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package educasturangelgm73.tienda2025;

/**
 *
 * @author alu10d
 */


import javax.swing.*;
import java.awt.*;

public class TiendaGUI extends JFrame {
    private Tienda2025 tienda;

    public TiendaGUI(Tienda2025 tienda) {
        this.tienda = tienda;
        setTitle("Tienda 2025 - Panel Principal");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior con título estilizado
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(33, 150, 243));
        JLabel titulo = new JLabel("Tienda 2025");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        panelTitulo.add(titulo);
        add(panelTitulo, BorderLayout.NORTH);

        // Panel central con botones con imágenes
        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 20, 20));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnPedidos = crearBotonConIcono("Gestionar Pedidos", "iconos/pedidos.png");
        JButton btnArticulos = crearBotonConIcono("Gestionar Artículos", "iconos/articulos.png");
        JButton btnClientes = crearBotonConIcono("Gestionar Clientes", "iconos/clientes.png");
        JButton btnSalir = crearBotonConIcono("Salir", "iconos/salir.png");

        btnArticulos.addActionListener(e -> JOptionPane.showMessageDialog(this, "Aquí irá la gestión de artículos."));
        btnClientes.addActionListener(e -> JOptionPane.showMessageDialog(this, "Aquí irá la gestión de clientes."));
        btnSalir.addActionListener(e -> {
            tienda.backup();
            System.exit(0);
        });

        panelBotones.add(btnPedidos);
        panelBotones.add(btnArticulos);
        panelBotones.add(btnClientes);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.CENTER);

        // Pie de página con información
        JPanel panelPie = new JPanel();
        JLabel footer = new JLabel("Desarrollado por Germán Palomares © 2025");
        panelPie.add(footer);
        add(panelPie, BorderLayout.SOUTH);
    }

    private JButton crearBotonConIcono(String texto, String rutaIcono) {
        ImageIcon icono = new ImageIcon(rutaIcono);
        Image imagen = icono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icono = new ImageIcon(imagen);

        JButton boton = new JButton(texto, icono);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(new Color(240, 240, 240));
        boton.setFocusPainted(false);
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setVerticalTextPosition(SwingConstants.BOTTOM);
        return boton;
    }

    public static void main(String[] args) {
        Tienda2025 tienda = new Tienda2025();
        tienda.leerArchivos();
        SwingUtilities.invokeLater(() -> new TiendaGUI(tienda).setVisible(true));
    }
}
