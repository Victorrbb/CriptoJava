/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ControllerEntraVendeCripto;
import javax.swing.JButton;
import javax.swing.JLabel;
import model.Investidor;

/**
 *
 * @author victo
 */
public class VendeCripto extends javax.swing.JFrame {

    /**
     * Creates new form VendeCripto
     */
    public VendeCripto(Investidor investidor ) {
        initComponents();
        this.investidor = investidor;
        lblCpf.setText(investidor.getCpf());
        
    }

    public Investidor getInvestidor() {
        return investidor;
    }

    public void setInvestidor(Investidor investidor) {
        this.investidor = investidor;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JButton getJbjBitcoin() {
        return jbjBitcoin;
    }

    public void setJbjBitcoin(JButton jbjBitcoin) {
        this.jbjBitcoin = jbjBitcoin;
    }

    public JButton getJbjEthereum() {
        return jbjEthereum;
    }

    public void setJbjEthereum(JButton jbjEthereum) {
        this.jbjEthereum = jbjEthereum;
    }

    public JButton getJbjRipple() {
        return jbjRipple;
    }

    public void setJbjRipple(JButton jbjRipple) {
        this.jbjRipple = jbjRipple;
    }

    public JLabel getLblCpf() {
        return lblCpf;
    }

    public void setLblCpf(JLabel lblCpf) {
        this.lblCpf = lblCpf;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jbjBitcoin = new javax.swing.JButton();
        jbjEthereum = new javax.swing.JButton();
        jbjRipple = new javax.swing.JButton();
        lblCpf = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Escolha uma dessas moedas para vender:");

        jbjBitcoin.setText("Bitcoin");
        jbjBitcoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbjBitcoinActionPerformed(evt);
            }
        });

        jbjEthereum.setText("Ethereum");
        jbjEthereum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbjEthereumActionPerformed(evt);
            }
        });

        jbjRipple.setText("Ripple");
        jbjRipple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbjRippleActionPerformed(evt);
            }
        });

        lblCpf.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jbjBitcoin)
                        .addGap(27, 27, 27)
                        .addComponent(jbjEthereum)
                        .addGap(32, 32, 32)
                        .addComponent(jbjRipple))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(lblCpf)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCpf)
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbjBitcoin)
                    .addComponent(jbjEthereum)
                    .addComponent(jbjRipple))
                .addGap(114, 114, 114))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbjBitcoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbjBitcoinActionPerformed
        VendeBitcoin bit = new VendeBitcoin(investidor);
        bit.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jbjBitcoinActionPerformed

    private void jbjEthereumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbjEthereumActionPerformed
     VendeEthereum ethe = new VendeEthereum(investidor);
        ethe.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jbjEthereumActionPerformed

    private void jbjRippleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbjRippleActionPerformed
    VendeRipple rip = new VendeRipple(investidor);
        rip.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jbjRippleActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(VendeCripto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(VendeCripto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(VendeCripto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(VendeCripto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new VendeCripto().setVisible(true);
//            }
//        });
//    }
    private Investidor investidor;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jbjBitcoin;
    private javax.swing.JButton jbjEthereum;
    private javax.swing.JButton jbjRipple;
    private javax.swing.JLabel lblCpf;
    // End of variables declaration//GEN-END:variables
}
