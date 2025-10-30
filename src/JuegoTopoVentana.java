import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class JuegoTopoVentana extends JFrame {

    private int puntos = 0;
    private int vidas = 3;
    private int nivel = 1;
    private int delay = 1000;
    private int topoActual = -1;
    private int totalToques = 0;
    private int aciertos = 0;

    private JButton[] botones;
    private Timer timer;
    private Random random = new Random();

    private ImageIcon imgTopo;
    private ImageIcon imgVacio;

    private JButton btnReiniciar;

    public JuegoTopoVentana() {
        initComponents();

        // Ventana más grande y más horizontal
        setSize(550, 550);
        setResizable(false);
        setLocationRelativeTo(null);

        cargarImagenes();
        configurarBotones();
        seleccionarModo();
        iniciarJuego();
    }

    private void cargarImagenes() {
        try {
            java.net.URL urlV = getClass().getResource("/imagenes/vacio.jpg");
            java.net.URL urlT = getClass().getResource("/imagenes/topo.jpg");

            if (urlV == null || urlT == null) {
                imgVacio = new ImageIcon(new BufferedImage(120, 120, BufferedImage.TYPE_INT_ARGB));
                imgTopo = new ImageIcon(new BufferedImage(120, 120, BufferedImage.TYPE_INT_ARGB));
            } else {
                ImageIcon rawVacio = new ImageIcon(urlV);
                imgVacio = new ImageIcon(rawVacio.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));

                ImageIcon rawTopo = new ImageIcon(urlT);
                imgTopo = new ImageIcon(rawTopo.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
            }
        } catch (Exception ex) {
            imgVacio = new ImageIcon(new BufferedImage(120, 120, BufferedImage.TYPE_INT_ARGB));
            imgTopo = new ImageIcon(new BufferedImage(120, 120, BufferedImage.TYPE_INT_ARGB));
            ex.printStackTrace();
        }
    }

    private void configurarBotones() {
        botones = new JButton[]{btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8};

        for (int i = 0; i < botones.length; i++) {
            botones[i].setIcon(imgVacio);
            botones[i].setText("");
            botones[i].setFocusable(false);

            final int index = i;
            botones[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pegarTopo(index);
                }
            });
        }
    }

    private void seleccionarModo() {
        String[] opciones = {"Entrenamiento", "Clásico", "Infernal"};
        String modo = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione un modo de juego:",
                "Modo de Juego",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (modo == null) modo = "Clásico";

        if (modo.equals("Entrenamiento")) {
            vidas = Integer.MAX_VALUE;
        } else if (modo.equals("Clásico")) {
            vidas = 3;
        } else if (modo.equals("Infernal")) {
            vidas = 1;
            delay = 700;
        }
        actualizarVidas();
    }

    private void iniciarJuego() {
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTopo();
            }
        });
        timer.start();
    }

    private void mostrarTopo() {
        if (topoActual >= 0) {
            botones[topoActual].setIcon(imgVacio);
            topoActual = -1;
            vidas--;
            actualizarVidas();
            verificarVidas();
        }

        topoActual = random.nextInt(9);
        botones[topoActual].setIcon(imgTopo);

        totalToques++;

        if (totalToques % 10 == 0) {
            subirNivel();
        }
    }

    private void pegarTopo(int index) {
        if (index == topoActual) {
            puntos++;
            aciertos++;
            lblPuntos.setText("Puntos: " + puntos);

            botones[topoActual].setIcon(imgVacio);
            topoActual = -1;
        }
    }

    private void subirNivel() {
        if (nivel < 10) {
            timer.stop(); // pausa
            nivel++;
            delay -= 100;
            if (delay < 300) delay = 300;

            JOptionPane.showMessageDialog(this, "¡Nivel " + nivel + "!");
            timer.setDelay(delay);
            timer.start();
        }
    }

    private void verificarVidas() {
        if (vidas <= 0) {
            if (timer != null) timer.stop();

            double precision = 0.0;
            if (totalToques > 0) precision = (aciertos * 100.0) / totalToques;

            int resp = JOptionPane.showOptionDialog(this,
                    "Fin del juego\n\n" +
                            "Puntos: " + puntos + "\n" +
                            "Nivel alcanzado: " + nivel + "\n" +
                            "Toques acertados: " + aciertos + "/" + totalToques + "\n" +
                            "Precisión: " + String.format("%.2f", precision) + "%",
                    "Juego terminado",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[]{"Reiniciar"},
                    "Reiniciar");

            if (resp == 0) reiniciarJuego();
        }
    }

    private void reiniciarJuego() {
        puntos = 0;
        vidas = 3;
        nivel = 1;
        delay = 1000;
        topoActual = -1;
        totalToques = 0;
        aciertos = 0;

        lblPuntos.setText("Puntos: 0");
        actualizarVidas();

        for (JButton b : botones) {
            b.setIcon(imgVacio);
        }

        seleccionarModo();
        timer.setDelay(delay);
        timer.start();
    }

    private void actualizarVidas() {
        if (vidas == Integer.MAX_VALUE) {
            lblVidas.setText("Vidas: ∞");
        } else {
            lblVidas.setText("Vidas: " + vidas);
        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblPuntos = new javax.swing.JLabel();
        panelTablero = new javax.swing.JPanel();
        btn0 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        lblVidas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblPuntos.setText("Puntos: 0");

        panelTablero.setLayout(new java.awt.GridLayout(3, 3));
        panelTablero.add(btn0);
        panelTablero.add(btn1);

        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        panelTablero.add(btn2);
        panelTablero.add(btn3);
        panelTablero.add(btn4);
        panelTablero.add(btn5);
        panelTablero.add(btn6);
        panelTablero.add(btn7);
        panelTablero.add(btn8);

        lblVidas.setText("Vidas:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTablero, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPuntos)
                        .addGap(18, 18, 18)
                        .addComponent(lblVidas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 424, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPuntos)
                    .addComponent(lblVidas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTablero, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JuegoTopoVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JuegoTopoVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JuegoTopoVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JuegoTopoVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JuegoTopoVentana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JLabel lblPuntos;
    private javax.swing.JLabel lblVidas;
    private javax.swing.JPanel panelTablero;
    // End of variables declaration//GEN-END:variables
}
