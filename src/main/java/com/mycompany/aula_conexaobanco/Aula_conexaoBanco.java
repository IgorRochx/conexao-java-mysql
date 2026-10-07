/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.aula_conexaobanco;

import view.cadastroClienteView;

public class Aula_conexaoBanco {

    public static void main(String[] args) {
        // Define o visual Nimbus (opcional, para manter o padrão Swing moderno)
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Aula_conexaoBanco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Instancia e exibe a tela de cadastro
        java.awt.EventQueue.invokeLater(() -> {
            new cadastroClienteView().setVisible(true);
        });
    }
}
