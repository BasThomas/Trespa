/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trespa.View;

import java.util.List;
import trespa.Model.*;
import trespa.Model.Tuples.*;
import trespa.Control.*;

/**
 *
 * @author Bas
 */
public class LaunchGUI extends javax.swing.JFrame
{
    private LaunchGUIViewController lgvc;

    /**
     * Creates new form LaunchGUI
     */
    public LaunchGUI()
    {
        initComponents();
        lgvc = new LaunchGUIViewController();
        
        fillComboBoxes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel1 = new javax.swing.JLabel();
        printButton = new javax.swing.JButton();
        stopsLabel = new javax.swing.JLabel();
        shippingPointComboBox = new javax.swing.JComboBox();
        truckComboBox = new javax.swing.JComboBox();
        countryComboBox = new javax.swing.JComboBox();
        placementsLabel = new javax.swing.JLabel();
        manualButton = new javax.swing.JButton();
        automaticButton = new javax.swing.JButton();
        palletsForShipmentLabel = new javax.swing.JLabel();
        heightForShipmentLabel = new javax.swing.JLabel();
        weightForShipmentLabel = new javax.swing.JLabel();
        loadingMetersLabel = new javax.swing.JLabel();
        textAreaScrollPane = new javax.swing.JScrollPane();
        generatedIndexTextBox = new javax.swing.JTextArea();
        costLabel = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trespa :: Launch");

        printButton.setText("Print");
        printButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                printButtonActionPerformed(evt);
            }
        });

        stopsLabel.setText("# stops (customers to deliver to)");

        placementsLabel.setText("# orderlines to deliver");

        manualButton.setText("Manual");
        manualButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                manualButtonActionPerformed(evt);
            }
        });

        automaticButton.setText("Automatic");
        automaticButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                automaticButtonActionPerformed(evt);
            }
        });

        palletsForShipmentLabel.setText("Pallets");

        heightForShipmentLabel.setText("Height");

        weightForShipmentLabel.setText("Weight");

        loadingMetersLabel.setText("Loading meters");

        generatedIndexTextBox.setEditable(false);
        generatedIndexTextBox.setColumns(20);
        generatedIndexTextBox.setFont(new java.awt.Font("Menlo", 0, 13)); // NOI18N
        generatedIndexTextBox.setRows(5);
        textAreaScrollPane.setViewportView(generatedIndexTextBox);

        costLabel.setText("Cost");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(shippingPointComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(automaticButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(manualButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(truckComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(printButton)
                                    .addComponent(countryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(placementsLabel)
                                            .addComponent(stopsLabel))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(palletsForShipmentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(heightForShipmentLabel)
                                    .addComponent(weightForShipmentLabel)
                                    .addComponent(loadingMetersLabel)
                                    .addComponent(costLabel)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(textAreaScrollPane)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(shippingPointComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(manualButton)
                        .addComponent(automaticButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(truckComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(countryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(printButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(placementsLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(palletsForShipmentLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(heightForShipmentLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(weightForShipmentLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadingMetersLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(costLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textAreaScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_printButtonActionPerformed
    {//GEN-HEADEREND:event_printButtonActionPerformed
        // Print information based on selected options.
        ShippingPoint s = (ShippingPoint) shippingPointComboBox.getSelectedItem();
        Truck t = (Truck) truckComboBox.getSelectedItem();
        Country c = (Country) countryComboBox.getSelectedItem();
        
        Pair sAndP = lgvc.stopsAndPlacementAmount(c, s);
        stopsLabel.setText(String.format("# stops (customers to deliver to): %d", sAndP.a));
        placementsLabel.setText(String.format("# orderlines to deliver: %d", sAndP.b));
    }//GEN-LAST:event_printButtonActionPerformed
    
    /**
     * Calculates all shipping details with manual configurations.
     * @param evt 
     */
    private void manualButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_manualButtonActionPerformed
    {//GEN-HEADEREND:event_manualButtonActionPerformed
        ShippingPoint s = (ShippingPoint) shippingPointComboBox.getSelectedItem();
        Truck t = (Truck) truckComboBox.getSelectedItem();
        Country c = (Country) countryComboBox.getSelectedItem();
        
        Septet tfs = lgvc.totalForShipment(c, s, t);
        
        // Make sure all textBoxes are emptied before continuing.
        generatedIndexTextBox.setText(null);
        
        palletsForShipmentLabel.setText(String.format("Pallets: %d", tfs.a));
        heightForShipmentLabel.setText(String.format("Height: %.3fm", tfs.b));
        weightForShipmentLabel.setText(String.format("Weight: %.3fkg", tfs.c));
        loadingMetersLabel.setText(String.format("Loading meters: %dm", tfs.d));
        
        /*********************
         **** GEOLOCATION ****
         *********************/
        
        Pair geoStart = new Pair("51.2636378", "5.6439594");
        Pair geoEnd = new Pair("48.8231099", "2.7080427");
        
        int distance = Math.round(lgvc.getDistance(geoStart, geoEnd));
        
        costLabel.setText(String.format("Cost: €%.2f", lgvc.calculateTotalCost((int)tfs.f, (float)distance)));
        
        List<LoadedPallet> pallets = (List<LoadedPallet>) tfs.e;
        
        for (LoadedPallet lp : pallets)
        {
            generatedIndexTextBox.append(lp + "\n");
        }
    }//GEN-LAST:event_manualButtonActionPerformed
    
    /**
     * Calculates all shipping details automatically.
     * @param evt 
     */
    private void automaticButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_automaticButtonActionPerformed
    {//GEN-HEADEREND:event_automaticButtonActionPerformed
        ShippingPoint s = (ShippingPoint) shippingPointComboBox.getSelectedItem();
        Truck t = (Truck) truckComboBox.getSelectedItem();
        Country c = (Country) countryComboBox.getSelectedItem();
        
        Septet tfs = lgvc.totalForShipment(c, s, t);
        
        int palletLength = 7;
        
        // Make sure all textBoxes are emptied before continuing.
        generatedIndexTextBox.setText(null);
        
        palletsForShipmentLabel.setText(String.format("Pallets: %d", tfs.a));
        heightForShipmentLabel.setText(String.format("Height: %.3fm", tfs.b));
        weightForShipmentLabel.setText(String.format("Weight: %.3fkg", tfs.c));
        loadingMetersLabel.setText(String.format("Loading meters: %dm", tfs.d));
        
        List<LoadedPallet> pallets = (List<LoadedPallet>) tfs.e;
        
        for (LoadedPallet lp : pallets)
        {
            // Generate index
            float currentHeight = 0f;
            
            while(currentHeight < t.getHeight())
            {
                generatedIndexTextBox.append("_\n");
                currentHeight += lp.getHeight();
            }
        }
    }//GEN-LAST:event_automaticButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(LaunchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(LaunchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(LaunchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(LaunchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new LaunchGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton automaticButton;
    private javax.swing.JLabel costLabel;
    private javax.swing.JComboBox countryComboBox;
    private javax.swing.JTextArea generatedIndexTextBox;
    private javax.swing.JLabel heightForShipmentLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel loadingMetersLabel;
    private javax.swing.JButton manualButton;
    private javax.swing.JLabel palletsForShipmentLabel;
    private javax.swing.JLabel placementsLabel;
    private javax.swing.JButton printButton;
    private javax.swing.JComboBox shippingPointComboBox;
    private javax.swing.JLabel stopsLabel;
    private javax.swing.JScrollPane textAreaScrollPane;
    private javax.swing.JComboBox truckComboBox;
    private javax.swing.JLabel weightForShipmentLabel;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Fills all comboboxes with available values.
     */
    public final void fillComboBoxes()
    {
        shippingPointComboBox.removeAllItems();
        truckComboBox.removeAllItems();
        countryComboBox.removeAllItems();
        
        List<ShippingPoint> shippingPoints = lgvc.fillComboBoxSP();
        List<Truck> trucks = lgvc.fillComboBoxTruck();
        List<Country> countries = lgvc.fillComboBoxCountry();
        
        for(ShippingPoint sp : shippingPoints)
        {
            shippingPointComboBox.addItem(sp);
        }
        
        for(Truck t : trucks)
        {
            truckComboBox.addItem(t);
        }
        
        for (Country c : countries)
        {
            countryComboBox.addItem(c);
        }
    }
}
