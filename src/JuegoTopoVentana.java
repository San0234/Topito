import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class JuegoTopoVentana extends javax.swing.JFrame {

    private int puntos = 0;
    private JButton[] botones;
    private int topoActual = -1;
    private javax.swing.Timer timer;
    public JuegoTopoVentana() {
        initComponents();
        botones = new JButton[]{btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8};
        
        for (JButton b : botones) {
            b.setIcon(escalarIcono("/imagenes/vacio.jpg", 220, 220));
        }
        
        for(int i = 0; i < botones.length; i++) {
            int index = i;
            botones[i].addActionListener(e -> pegarTopo(index));
        }
        
        timer = new Timer(1000, e -> mostrarTopo());
        timer.start();
        
        setSize(800, 800);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    private void mostrarTopo() {
        if (topoActual >= 0) {
            botones[topoActual].setIcon(escalarIcono("/imagenes/vacio.jpg", 220, 220));
        }
        topoActual = (int) (Math.random() * 9);
        
        botones[topoActual].setIcon(escalarIcono("/imagenes/topo.jpg", 220, 220));
    }
    
    private void pegarTopo(int index) {
        if (index == topoActual) {
            puntos++;
            lblPuntos.setText("Puntos: " + puntos);
            
            botones[topoActual].setIcon(escalarIcono("/imagenes/vacio.jpg", 220, 220));
            topoActual = -1;
        }
    }
    
    private ImageIcon escalarIcono(String ruta, int ancho, int alto) {
        ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
        Image img = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTablero, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPuntos)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPuntos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTablero, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
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
    private javax.swing.JPanel panelTablero;
    // End of variables declaration//GEN-END:variables
}
