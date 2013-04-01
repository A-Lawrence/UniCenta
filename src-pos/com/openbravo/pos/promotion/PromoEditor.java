/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.promotion;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.*;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.panels.JProductFinder;
import com.openbravo.pos.ticket.CategoryInfo;
import com.openbravo.pos.ticket.ProductInfoExt;
import com.openbravo.pos.util.PropertyUtils;
import java.awt.Component;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Escartin Aurélien aurelien.escartin@gmail.com
 */
public class PromoEditor extends JPanel implements EditorRecord {

    private SentenceList m_sentPromo;
    private ComboBoxValModel m_jTypeModel;
    private ComboBoxValModel m_jCatModel;
    private DirtyManager m_Dirty;
    DataLogicSales dls;
    String _DescBonusArticle = null;
    private Object m_sID;
    private DataLogicSales m_dlSales;
    private Session s;

    /** Creates new form PlacesEditor */
    public PromoEditor(AppView app, DataLogicSales dlSales, DirtyManager dirty) {
        m_dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSales");
        initComponents();
        jLabel13.setText("%");
        jLabel1.setText("Name");
        jLabel2.setText("Date");
        dls = dlSales;
        m_jArticle.setVisible(false);
        m_jBonusArticle.setVisible(false);
        m_jCategory.setVisible(false);
        //btnValidTo.setVisible(false);

        m_sentPromo = dlSales.getPromoTypeList();
        m_jTypeModel = new ComboBoxValModel();

        m_jName.getDocument().addDocumentListener(dirty);
        m_jStartDate.getDocument().addDocumentListener(dirty);
        m_jEndDate.getDocument().addDocumentListener(dirty);
        m_jStartHour.getDocument().addDocumentListener(dirty);
        m_jEndHour.getDocument().addDocumentListener(dirty);
        m_jArticle.getDocument().addDocumentListener(dirty);
        m_jCategory.getDocument().addDocumentListener(dirty);
        m_jAmount.getDocument().addDocumentListener(dirty);
        m_jMin.getDocument().addDocumentListener(dirty);
        m_jMax.getDocument().addDocumentListener(dirty);
        m_jStepAmount.getDocument().addDocumentListener(dirty);
        m_jStepQty.getDocument().addDocumentListener(dirty);
        m_jBonusArticle.getDocument().addDocumentListener(dirty);

        m_jType.addActionListener(dirty);
        m_jType.addActionListener(new java.awt.event.ActionListener() {
 
        public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSelectPromoType(evt);
            }
        });
        
        /*btnValidTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidToActionPerformed(evt);
            }
        });
        
        btnValidFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidToActionPerformed(evt);
            }
        });*/

        writeValueEOF();
    }

    public void activate() throws BasicException {
        m_jCatModel = new ComboBoxValModel(m_dlSales.getCategoriesList().list());
        m_jCatName.setModel(m_jCatModel);
        m_jTypeModel = new ComboBoxValModel(m_sentPromo.list());
        m_jType.setModel(m_jTypeModel);
        onSelectPromoType(null);
    }

    public void refresh() {
    }

    public void writeValueEOF() {


        m_sID = null;
/*        m_jName.setText(null);
        m_jTypeModel.setSelectedKey(null);
        m_jStartDate.setText(null);
        m_jEndDate.setText(null);
        m_jStartHour.setText(null);
        m_jEndHour.setText(null);
        m_jArticle.setText(null);
        m_jCategory.setText(null);
        m_jAmount.setText(null);
        m_jMin.setText(null);
        m_jMax.setText(null);
        m_jStepAmount.setText(null);
        m_jStepQty.setText(null);
        m_jBonusArticle.setText(null);

        m_jName.setEnabled(false);
        m_jStartDate.setEnabled(false);
        m_jEndDate.setEnabled(false);
        //m_jStartHour.setEnabled(false);
        //m_jEndHour.setEnabled(false);
        //m_jArticle.setEnabled(false);
        m_jCategory.setEnabled(false);
        m_jAmount.setEnabled(false);
        m_jMin.setEnabled(false);
        m_jMax.setEnabled(false);
        m_jStepAmount.setEnabled(false);
        //m_jStepQty.setEnabled(false);
        m_jBonusArticle.setEnabled(false);*/
        
        m_jPanName.setVisible(false);
        m_jPanDate.setVisible(false);
        m_jPanTime.setVisible(false);
        m_jPanDiscount.setVisible(false);
        m_jPanProduct.setVisible(false);
        m_jPanCat.setVisible(false);
        m_jPanAmount.setVisible(false);
        m_jPanMinMax.setVisible(false);
        m_jPanSAmount.setVisible(false);
        m_jPanQuan.setVisible(false);
        m_jPanBonus.setVisible(false);

    }

    public void writeValueInsert() {

        m_sID = UUID.randomUUID().toString();
        m_jName.setText(null);
        m_jTypeModel.setSelectedKey(null);
        m_jStartDate.setText(null);
        m_jEndDate.setText(null);
        m_jStartHour.setText(null);
        m_jEndHour.setText(null);
        m_jArticle.setText(null);
        m_jCategory.setText(null);
        m_jAmount.setText(null);
        m_jMin.setText(null);
        m_jMax.setText(null);
        m_jStepAmount.setText(null);
        m_jStepQty.setText(null);
        m_jBonusArticle.setText(null);
        m_jProdName.setText(null);
        m_jBonusProd.setText(null);
        
        m_jPanName.setVisible(false);
        m_jPanDate.setVisible(false);
        m_jPanTime.setVisible(false);
        m_jPanDiscount.setVisible(true);
        m_jPanProduct.setVisible(false);
        m_jPanCat.setVisible(false);
        m_jPanAmount.setVisible(false);
        m_jPanMinMax.setVisible(false);
        m_jPanSAmount.setVisible(false);
        m_jPanQuan.setVisible(false);
        m_jPanBonus.setVisible(false);

/*        m_jName.setEnabled(false);
        m_jStartDate.setEnabled(false);
        m_jEndDate.setEnabled(false);
        m_jStartHour.setEnabled(false);
        m_jEndHour.setEnabled(false);
        m_jArticle.setEnabled(false);
        m_jCategory.setEnabled(false);
        m_jAmount.setEnabled(false);
        m_jMin.setEnabled(false);
        m_jMax.setEnabled(false);
        m_jStepAmount.setEnabled(false);
        m_jStepQty.setEnabled(false);
        m_jBonusArticle.setEnabled(false);
        m_jType.setEnabled(true);
        * 
        */

    }

    public void writeValueDelete(Object value) {

        Object[] promo = (Object[]) value;
        m_sID = Formats.STRING.formatValue(promo[0]);
        m_jName.setText(Formats.STRING.formatValue(promo[1]));
        m_jStartDate.setText(MessageFormat.format("{0,number,#}", promo[2]));
        m_jEndDate.setText(MessageFormat.format("{0,number,#}", promo[3]));
        //m_jStartDate.setText(Formats.STRING.formatValue(promo[2]));
        //m_jEndDate.setText(Formats.STRING.formatValue(promo[3]));
        m_jStartHour.setText(Formats.INT.formatValue(promo[4]));
        m_jEndHour.setText(Formats.INT.formatValue(promo[5]));
        m_jArticle.setText(Formats.STRING.formatValue(promo[6]));
        m_jCategory.setText(Formats.STRING.formatValue(promo[7]));
        m_jTypeModel.setSelectedKey(Formats.INT.formatValue(promo[8]));
        m_jAmount.setText(Formats.INT.formatValue(promo[9]));
        m_jMin.setText(Formats.INT.formatValue(promo[10]));
        m_jMax.setText(Formats.INT.formatValue(promo[11]));
        m_jStepAmount.setText(Formats.INT.formatValue(promo[12]));
        m_jStepQty.setText(Formats.INT.formatValue(promo[13]));
        m_jBonusArticle.setText(Formats.STRING.formatValue(promo[14]));
        try {
            getProdName(m_jArticle.getText());
        } catch (BasicException ex) {
            Logger.getLogger(PromoEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            getBonusName(m_jBonusArticle.getText());
        } catch (BasicException ex) {
            Logger.getLogger(PromoEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            getCatName(m_jCategory.getText());
        } catch (BasicException ex) {
            Logger.getLogger(PromoEditor.class.getName()).log(Level.SEVERE, null, ex);
        }


/*        m_jName.setEnabled(true);
        m_jStartDate.setEnabled(false);
        m_jEndDate.setEnabled(false);
        //m_jStartHour.setEnabled(false);
        //m_jEndHour.setEnabled(false);
        //m_jArticle.setEnabled(false);
        m_jCategory.setEnabled(false);
        m_jAmount.setEnabled(false);
        m_jMin.setEnabled(false);
        m_jMax.setEnabled(false);
        m_jStepAmount.setEnabled(false);
        //m_jStepQty.setEnabled(false);
        m_jBonusArticle.setEnabled(false);
        m_jType.setEnabled(true);*/
        
        m_jPanName.setVisible(true);
        m_jPanDate.setVisible(true);
        m_jPanTime.setVisible(true);
        m_jPanDiscount.setVisible(true);
        m_jPanProduct.setVisible(true);
        m_jPanCat.setVisible(false);
        m_jPanAmount.setVisible(true);
        m_jPanMinMax.setVisible(false);
        m_jPanSAmount.setVisible(false);
        m_jPanQuan.setVisible(false);
        m_jPanBonus.setVisible(false);

    }

    public void writeValueEdit(Object value) {
        
        Object[] promo = (Object[]) value;
        m_sID = Formats.STRING.formatValue(promo[0]);
        m_jName.setText(Formats.STRING.formatValue(promo[1]));
        m_jStartDate.setText(MessageFormat.format("{0,number,#}", promo[2]));
        m_jEndDate.setText(MessageFormat.format("{0,number,#}", promo[3]));
        //m_jStartDate.setText(Formats.STRING.formatValue(promo[2]));
        //m_jEndDate.setText(Formats.STRING.formatValue(promo[3]));
        m_jStartHour.setText(Formats.INT.formatValue(promo[4]));
        m_jEndHour.setText(Formats.INT.formatValue(promo[5]));
        m_jArticle.setText(Formats.STRING.formatValue(promo[6]));
        m_jCategory.setText(Formats.STRING.formatValue(promo[7]));
        m_jTypeModel.setSelectedKey(Formats.INT.formatValue(promo[8]));
        m_jAmount.setText(Formats.INT.formatValue(promo[9]));
        m_jMin.setText(Formats.INT.formatValue(promo[10]));
        m_jMax.setText(Formats.INT.formatValue(promo[11]));
        m_jStepAmount.setText(Formats.INT.formatValue(promo[12]));
        m_jStepQty.setText(Formats.INT.formatValue(promo[13]));
        m_jBonusArticle.setText(Formats.STRING.formatValue(promo[14]));
        try {
            getProdName(m_jArticle.getText());
        } catch (BasicException ex) {
            Logger.getLogger(PromoEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            getBonusName(m_jBonusArticle.getText());
        } catch (BasicException ex) {
            Logger.getLogger(PromoEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            getCatName(m_jCategory.getText());
        } catch (BasicException ex) {
            Logger.getLogger(PromoEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
/*        m_jName.setEnabled(true);
        m_jStartDate.setEnabled(false);
        m_jEndDate.setEnabled(false);
        //m_jStartHour.setEnabled(false);
        //m_jEndHour.setEnabled(false);
        m_jMax.setEnabled(false);
        m_jMin.setEnabled(false);
        m_jName.setEnabled(false);
        m_jStartDate.setEnabled(false);
        //m_jStartHour.setEnabled(false);
        m_jStepAmount.setEnabled(false);
        //m_jStepQty.setEnabled(false);
        m_jType.setEnabled(true);*/
        
        m_jPanName.setVisible(true);
        m_jPanDate.setVisible(true);
        m_jPanTime.setVisible(true);
        m_jPanDiscount.setVisible(true);
        m_jPanProduct.setVisible(true);
        m_jPanCat.setVisible(true);
        m_jPanAmount.setVisible(true);
        m_jPanMinMax.setVisible(true);
        m_jPanSAmount.setVisible(true);
        m_jPanQuan.setVisible(true);
        m_jPanBonus.setVisible(true);

        Integer _type = new Integer(promo[8].toString());
        
        switch (_type) {

            // discount in %
            case 1:
                jLabel13.setText("%");
                
                //m_jName.setText(null);
                //m_jStartDate.setText(null);
                //m_jEndDate.setText(null);
                //m_jStartHour.setText(null);
                //m_jEndHour.setText(null);
                //m_jTypeModel.setSelectedKey(null);
                //m_jArticle.setText(null);
                m_jCategory.setText(null);
                //m_jAmount.setText(null);
                m_jMin.setText(null);
                m_jMax.setText(null);
                m_jStepAmount.setText(null);
                m_jStepQty.setText(null);
                //m_jBonusArticle.setText(null);
                m_jBonusProd.setText(null);
                
                m_jPanName.setVisible(true);
                m_jPanDate.setVisible(true);
                m_jPanTime.setVisible(true);
                m_jPanDiscount.setVisible(true);
                m_jPanProduct.setVisible(true);
                m_jPanCat.setVisible(false);
                m_jPanAmount.setVisible(true);
                m_jPanMinMax.setVisible(false);
                m_jPanSAmount.setVisible(false);
                m_jPanQuan.setVisible(false);
                m_jPanBonus.setVisible(false);
                break;

            // discount in $
            case 2:
                jLabel13.setText("$");
                
                //m_jName.setText(null);
                //m_jStartDate.setText(null);
                //m_jEndDate.setText(null);
                //m_jStartHour.setText(null);
                //m_jEndHour.setText(null);
                //m_jTypeModel.setSelectedKey(null);
                //m_jArticle.setText(null);
                m_jCategory.setText(null);
                //m_jAmount.setText(null);
                m_jMin.setText(null);
                m_jMax.setText(null);
                m_jStepAmount.setText(null);
                m_jStepQty.setText(null);
                m_jBonusArticle.setText(null);
                m_jBonusProd.setText(null);
                
                m_jPanName.setVisible(true);
                m_jPanDate.setVisible(true);
                m_jPanTime.setVisible(true);
                m_jPanDiscount.setVisible(true);
                m_jPanProduct.setVisible(true);
                m_jPanCat.setVisible(false);
                m_jPanAmount.setVisible(true);
                m_jPanMinMax.setVisible(false);
                m_jPanSAmount.setVisible(false);
                m_jPanQuan.setVisible(false);
                m_jPanBonus.setVisible(false);
                break;

            // Gift / Coupon
            case 3:
                jLabel13.setText("");
                
                //m_jName.setText(null);
                //m_jStartDate.setText(null);
                //m_jEndDate.setText(null);
                //m_jStartHour.setText(null);
                //m_jEndHour.setText(null);
                //m_jTypeModel.setSelectedKey(null);
                //m_jArticle.setText(null);
                m_jCategory.setText(null);
                m_jAmount.setText(null);
                m_jMin.setText(null);
                m_jMax.setText(null);
                //m_jStepAmount.setText(null);
                //m_jStepQty.setText(null);
                //m_jBonusArticle.setText(null);
                //m_jBonusProd.setText(null);
                
                m_jPanName.setVisible(true);
                m_jPanDate.setVisible(true);
                m_jPanTime.setVisible(true);
                m_jPanDiscount.setVisible(true);
                m_jPanProduct.setVisible(true);
                m_jPanCat.setVisible(false);
                m_jPanAmount.setVisible(false);
                m_jPanMinMax.setVisible(false);
                m_jPanSAmount.setVisible(true);
                m_jPanQuan.setVisible(true);
                m_jPanBonus.setVisible(true);
                break;

            //Get X% of discount on the cheapest article of a category
            case 4:
                jLabel13.setText("%");
                
                //m_jName.setText(null);
                //m_jStartDate.setText(null);
                //m_jEndDate.setText(null);
                //m_jStartHour.setText(null);
                //m_jEndHour.setText(null);
                //m_jTypeModel.setSelectedKey(null);
                m_jArticle.setText(null);
                //m_jCategory.setText(null);
                //m_jAmount.setText(null);
                m_jMin.setText(null);
                m_jMax.setText(null);
                m_jStepAmount.setText(null);
                m_jStepQty.setText(null);
                m_jBonusArticle.setText(null);
                m_jBonusProd.setText(null);
                
                m_jPanName.setVisible(true);
                m_jPanDate.setVisible(true);
                m_jPanTime.setVisible(true);
                m_jPanDiscount.setVisible(true);
                m_jPanProduct.setVisible(false);
                m_jPanCat.setVisible(true);
                m_jPanAmount.setVisible(true);
                m_jPanMinMax.setVisible(false);
                m_jPanSAmount.setVisible(false);
                m_jPanQuan.setVisible(false);
                m_jPanBonus.setVisible(false);
                break;

            //Mix'n'Match (Buy 2 get 3)  
            case 5:
                jLabel13.setText("");
                
                //m_jName.setText(null);
                //m_jStartDate.setText(null);
                //m_jEndDate.setText(null);
                //m_jStartHour.setText(null);
                //m_jEndHour.setText(null);
                //m_jTypeModel.setSelectedKey(null);
                //m_jArticle.setText(null);
                m_jCategory.setText(null);
                m_jAmount.setText(null);
                //m_jMin.setText(null);
                //m_jMax.setText(null);
                m_jStepAmount.setText(null);
                //m_jStepQty.setText(null);
                m_jBonusArticle.setText(null);
                m_jBonusProd.setText(null);
                
                m_jPanName.setVisible(true);
                m_jPanDate.setVisible(true);
                m_jPanTime.setVisible(true);
                m_jPanDiscount.setVisible(true);
                m_jPanProduct.setVisible(true);
                m_jPanCat.setVisible(false);
                m_jPanAmount.setVisible(false);
                m_jPanMinMax.setVisible(true);
                m_jPanSAmount.setVisible(false);
                m_jPanQuan.setVisible(true);
                m_jPanBonus.setVisible(false);
                break;
                
            // discount in % by category
            case 6:
                jLabel13.setText("%");
                
                //m_jName.setText(null);
                //m_jStartDate.setText(null);
                //m_jEndDate.setText(null);
                //m_jStartHour.setText(null);
                //m_jEndHour.setText(null);
                //m_jTypeModel.setSelectedKey(null);
                //m_jArticle.setText(null);
                //m_jCategory.setText(null);
                //m_jAmount.setText(null);
                m_jMin.setText(null);
                m_jMax.setText(null);
                m_jStepAmount.setText(null);
                m_jStepQty.setText(null);
                //m_jBonusArticle.setText(null);
                m_jBonusProd.setText(null);
                
                m_jPanName.setVisible(true);
                m_jPanDate.setVisible(true);
                m_jPanTime.setVisible(true);
                m_jPanDiscount.setVisible(true);
                m_jPanProduct.setVisible(false);
                m_jPanCat.setVisible(true);
                m_jPanAmount.setVisible(true);
                m_jPanMinMax.setVisible(false);
                m_jPanSAmount.setVisible(false);
                m_jPanQuan.setVisible(false);
                m_jPanBonus.setVisible(false);
                break;

        }

    }

    public Object createValue() throws BasicException {


        // Here we will do the integrity check of the promotion, depending on the selected discount value

        if (m_jTypeModel.getSelectedKey() != null) {

            Integer _type = new Integer(m_jTypeModel.getSelectedKey().toString());

            if (m_jName.getText().equals("")) {
                JOptionPane.showConfirmDialog(this, "Discount Name is mandatory", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
                return null;
            }
            if ((m_jStartDate.getText().equals("")) || (m_jEndDate.getText().equals(""))) {
                JOptionPane.showConfirmDialog(this, "A period is Mandatory", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
                return null;
            }

            if (!(m_jStartDate.getText().equals("")) && !(m_jEndDate.getText().equals(""))) {
                Integer _start = new Integer(m_jStartDate.getText());
                Integer _end = new Integer(m_jEndDate.getText());

                if (_start > _end) {
                    JOptionPane.showConfirmDialog(this, "End Date must greater than Start date", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
                    return null;
                }
            }

            if ((m_jArticle.getText().equals("")) && (m_jCategory.getText().equals(""))) {
                JOptionPane.showConfirmDialog(this, "Please fill in a Product or a Category ", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
                return null;
            }
            if ((!m_jArticle.getText().equals("")) && (!m_jCategory.getText().equals(""))) {
                JOptionPane.showConfirmDialog(this, "You have to fill in a Product or a Category, but not both", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
                return null;
            }

            switch (_type) {

                //1 --> Discount in %
                case 1:

                    if (m_jAmount.getText().equals("")) {
                        JOptionPane.showConfirmDialog(this, "Please add amount of Discount", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
                        return null;
                    }
                    break;

                // Discount in money
                case 2:
                    if (m_jAmount.getText().equals("")) {
                        JOptionPane.showConfirmDialog(this, "Please add amount of Discount", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
                        return null;
                    }

                    break;

                // Gift / Coupon
                case 3:
                    if (m_jBonusArticle.getText().equals("")) {
                        JOptionPane.showConfirmDialog(this, "Please add the Product for the Gift", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
                        return null;
                    }
                    if (m_jStepQty.getText().equals("")) {
                        JOptionPane.showConfirmDialog(this, "Please add the Quantity step", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
                        return null;
                    }

                    break;

                //Get X% of discount on the cheapest product of a category
                case 4:
                    if ((m_jCategory.getText().equals(""))) {
                        JOptionPane.showConfirmDialog(this, "Please add a category", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
                        return null;
                    }

                    break;

                //Mix'n'Match (Buy 2 get 3)  
                case 5:
                    if (m_jMin.getText().equals("")) {
                        JOptionPane.showConfirmDialog(this, "Please complete Minimum Threshold", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
                        return null;
                    }
                    if (m_jMax.getText().equals("")) {
                        JOptionPane.showConfirmDialog(this, "Please complete Minimum Threshold", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
                        return null;
                    }
                    if (m_jStepQty.getText().equals("")) {
                        JOptionPane.showConfirmDialog(this, "Please add a Quantity step", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
                        return null;
                    }
                    break;
                    
                //6 --> Discount in %
                case 6:

                    if (m_jAmount.getText().equals("")) {
                        JOptionPane.showConfirmDialog(this, "Please add the amount of Discount", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
                        return null;
                    }
                    break;


            }
        } else {
            JOptionPane.showConfirmDialog(this, "You Must select a Discount Type", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
            return null;
        }

        String _cle = m_jTypeModel.getSelectedKey().toString();

        Object[] promo = new Object[16];
        promo[0] = m_sID == null ? UUID.randomUUID().toString() : m_sID;
        promo[1] = Formats.STRING.parseValue(m_jName.getText());
        promo[2] = Formats.INT.parseValue(m_jStartDate.getText());
        promo[3] = Formats.INT.parseValue(m_jEndDate.getText());
        promo[4] = Formats.INT.parseValue(m_jStartHour.getText());
        promo[5] = Formats.INT.parseValue(m_jEndHour.getText());
        promo[6] = Formats.STRING.parseValue(m_jArticle.getText());
        promo[7] = Formats.STRING.parseValue(m_jCategory.getText());
        promo[8] = Formats.INT.parseValue(_cle);
        promo[9] = Formats.DOUBLE.parseValue(m_jAmount.getText());
        promo[10] = Formats.INT.parseValue(m_jMin.getText());
        promo[11] = Formats.INT.parseValue(m_jMax.getText());
        promo[12] = Formats.INT.parseValue(m_jStepAmount.getText());
        promo[13] = Formats.INT.parseValue(m_jStepQty.getText());
        promo[14] = Formats.STRING.parseValue(m_jBonusArticle.getText());
        promo[15] = Formats.STRING.parseValue(m_jBonusProd.getText());
        //promo[15] = Formats.STRING.parseValue(_DescBonusArticle);


        return promo;


    }

    public Component getComponent() {
        return this;
    }
    

private void onSelectPromoType(java.awt.event.ActionEvent evt) {                                   

    if (m_jTypeModel.getSelectedKey() != null) {

        Integer _type = new Integer(m_jTypeModel.getSelectedKey().toString());
        
        if(m_jCategory.getText().isEmpty()) {
            m_jCatName.setSelectedIndex(-1);
        }

        m_jAmount.setText("");
        m_jBonusArticle.setText("");
        //m_jCategory.setText("");
        m_jStepQty.setText("");
        m_jMin.setText("");
        m_jMax.setText("");
        m_jProdName.setText("");
        m_jBonusProd.setText("");
        m_jProdName.setText(null);
        m_jBonusProd.setText(null);

        switch (_type) {

            // discount in %
            case 1:
                jLabel13.setText("%");
                
                //m_jName.setText(null);
                //m_jStartDate.setText(null);
                //m_jEndDate.setText(null);
                //m_jStartHour.setText(null);
                //m_jEndHour.setText(null);
                //m_jTypeModel.setSelectedKey(null);
                //m_jArticle.setText(null);
                m_jCategory.setText(null);
                //m_jAmount.setText(null);
                m_jMin.setText(null);
                m_jMax.setText(null);
                m_jStepAmount.setText(null);
                m_jStepQty.setText(null);
                m_jBonusArticle.setText(null);
                m_jBonusProd.setText(null);
                
                m_jPanName.setVisible(true);
                m_jPanDate.setVisible(true);
                m_jPanTime.setVisible(true);
                m_jPanDiscount.setVisible(true);
                m_jPanProduct.setVisible(true);
                m_jPanCat.setVisible(false);
                m_jPanAmount.setVisible(true);
                m_jPanMinMax.setVisible(false);
                m_jPanSAmount.setVisible(false);
                m_jPanQuan.setVisible(false);
                m_jPanBonus.setVisible(false);
                break;

            // discount value
            case 2:
                jLabel13.setText("Value");
                
                //m_jName.setText(null);
                //m_jStartDate.setText(null);
                //m_jEndDate.setText(null);
                //m_jStartHour.setText(null);
                //m_jEndHour.setText(null);
                //m_jTypeModel.setSelectedKey(null);
                //m_jArticle.setText(null);
                m_jCategory.setText(null);
                //m_jAmount.setText(null);
                m_jMin.setText(null);
                m_jMax.setText(null);
                m_jStepAmount.setText(null);
                m_jStepQty.setText(null);
                m_jBonusArticle.setText(null);
                m_jBonusProd.setText(null);
                
                m_jPanName.setVisible(true);
                m_jPanDate.setVisible(true);
                m_jPanTime.setVisible(true);
                m_jPanDiscount.setVisible(true);
                m_jPanProduct.setVisible(true);
                m_jPanCat.setVisible(false);
                m_jPanAmount.setVisible(true);
                m_jPanMinMax.setVisible(false);
                m_jPanSAmount.setVisible(false);
                m_jPanQuan.setVisible(false);
                m_jPanBonus.setVisible(false);
                break;

            // Gift / Coupon
            case 3:
                jLabel13.setText("");
                
                //m_jName.setText(null);
                //m_jStartDate.setText(null);
                //m_jEndDate.setText(null);
                //m_jStartHour.setText(null);
                //m_jEndHour.setText(null);
                //m_jTypeModel.setSelectedKey(null);
                //m_jArticle.setText(null);
                m_jCategory.setText(null);
                m_jAmount.setText(null);
                //m_jMin.setText(null);
                //m_jMax.setText(null);
                //m_jStepAmount.setText(null);
                //m_jStepQty.setText(null);
                //m_jBonusArticle.setText(null);
                //m_jBonusProd.setText(null);
                
                m_jPanName.setVisible(true);
                m_jPanDate.setVisible(true);
                m_jPanTime.setVisible(true);
                m_jPanDiscount.setVisible(true);
                m_jPanProduct.setVisible(true);
                m_jPanCat.setVisible(false);
                m_jPanAmount.setVisible(false);
                m_jPanMinMax.setVisible(true);
                m_jPanSAmount.setVisible(true);
                m_jPanQuan.setVisible(true);
                m_jPanBonus.setVisible(true);
                break;

            //Get X% of discount on the cheapest article of a category
            case 4:
                jLabel13.setText("%");

                //m_jName.setText(null);
                //m_jStartDate.setText(null);
                //m_jEndDate.setText(null);
                //m_jStartHour.setText(null);
                //m_jEndHour.setText(null);
                //m_jTypeModel.setSelectedKey(null);
                m_jArticle.setText(null);
                //m_jCategory.setText(null);
                //m_jAmount.setText(null);
                //m_jMin.setText(null);
                //m_jMax.setText(null);
                m_jStepAmount.setText(null);
                m_jStepQty.setText(null);
                m_jBonusArticle.setText(null);
                m_jBonusProd.setText(null);
                
                m_jPanName.setVisible(true);
                m_jPanDate.setVisible(true);
                m_jPanTime.setVisible(true);
                m_jPanDiscount.setVisible(true);
                m_jPanProduct.setVisible(false);
                m_jPanCat.setVisible(true);
                m_jPanAmount.setVisible(true);
                m_jPanMinMax.setVisible(true);
                m_jPanSAmount.setVisible(false);
                m_jPanQuan.setVisible(false);
                m_jPanBonus.setVisible(false);
                break;

            //Mix'n'Match (Buy 2 get 3)  
            case 5:
                jLabel13.setText("");
                
                //m_jName.setText(null);
                //m_jStartDate.setText(null);
                //m_jEndDate.setText(null);
                //m_jStartHour.setText(null);
                //m_jEndHour.setText(null);
                //m_jTypeModel.setSelectedKey(null);
                //m_jArticle.setText(null);
                m_jCategory.setText(null);
                m_jAmount.setText(null);
                //m_jMin.setText(null);
                //m_jMax.setText(null);
                m_jStepAmount.setText(null);
                //m_jStepQty.setText(null);
                m_jBonusArticle.setText(null);
                m_jBonusProd.setText(null);
                
                m_jPanName.setVisible(true);
                m_jPanDate.setVisible(true);
                m_jPanTime.setVisible(true);
                m_jPanDiscount.setVisible(true);
                m_jPanProduct.setVisible(true);
                m_jPanCat.setVisible(false);
                m_jPanAmount.setVisible(false);
                m_jPanMinMax.setVisible(true);
                m_jPanSAmount.setVisible(false);
                m_jPanQuan.setVisible(true);
                m_jPanBonus.setVisible(false);
                break;
                
            // discount in % by category
            case 6:
                jLabel13.setText("%");
                
                //m_jName.setText(null);
                //m_jStartDate.setText(null);
                //m_jEndDate.setText(null);
                //m_jStartHour.setText(null);
                //m_jEndHour.setText(null);
                //m_jTypeModel.setSelectedKey(null);
                //m_jArticle.setText(null);
                //m_jCategory.setText(null);
                //m_jAmount.setText(null);
                m_jMin.setText(null);
                m_jMax.setText(null);
                m_jStepAmount.setText(null);
                m_jStepQty.setText(null);
                //m_jBonusArticle.setText(null);
                m_jBonusProd.setText(null);
                
                m_jPanName.setVisible(true);
                m_jPanDate.setVisible(true);
                m_jPanTime.setVisible(true);
                m_jPanDiscount.setVisible(true);
                m_jPanProduct.setVisible(false);
                m_jPanCat.setVisible(true);
                m_jPanAmount.setVisible(true);
                m_jPanMinMax.setVisible(false);
                m_jPanSAmount.setVisible(false);
                m_jPanQuan.setVisible(false);
                m_jPanBonus.setVisible(false);
                break;

        }
    }


}                                  

private void OnCheckAmount(java.awt.event.FocusEvent evt) {                               

    if (!m_jAmount.getText().equals("")) {
        if (!isNumeric(m_jAmount.getText())) {
            JOptionPane.showConfirmDialog(this, "The Amount is not correct", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
            m_jAmount.setText(null);
        }
    }
}                              

private void OnCheckMin(java.awt.event.FocusEvent evt) {                            

    if (!m_jMin.getText().equals("")) {
        if (!isNumeric(m_jMin.getText())) {
            JOptionPane.showConfirmDialog(this, "The minimum is not correct", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
            m_jMin.setText(null);
            return;
        }
    }
    if ((!m_jMin.getText().equals("")) && (!m_jMax.getText().equals(""))) {
        Integer _min = new Integer(m_jMin.getText());
        Integer _max = new Integer(m_jMax.getText());

        if (_min > _max) {
            JOptionPane.showConfirmDialog(this, "The minimum can't be greater than the maximum", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
            m_jMin.setText(null);
            return;
        }
    }

}                           

private void OnCheckMax(java.awt.event.FocusEvent evt) {                            


    if (!m_jMax.getText().equals("")) {
        if (!isNumeric(m_jMax.getText())) {
            JOptionPane.showConfirmDialog(this, "The maximum is not correct", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
            m_jMax.setText(null);
            return;
        }
    }
    if ((!m_jMin.getText().equals("")) && (!m_jMax.getText().equals(""))) {
        Integer _min = new Integer(m_jMin.getText());
        Integer _max = new Integer(m_jMax.getText());

        if (_max < _min) {
            JOptionPane.showConfirmDialog(this, "The minimum can't be greater than the maximum", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
            m_jMin.setText(null);
            return;
        }
    }


}                           

private void OnCheckQuantityStep(java.awt.event.FocusEvent evt) {                                     

    if (!m_jStepAmount.getText().equals("")) {
        if (!isNumeric(m_jStepAmount.getText())) {
            JOptionPane.showConfirmDialog(this, "The step is not correct", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
            m_jStepAmount.setText(null);
            return;
        }
    }


}                                    

private void OnCheckDiscountStep(java.awt.event.FocusEvent evt) {                                     

    if (!m_jStepQty.getText().equals("")) {
        if (!isNumeric(m_jStepQty.getText())) {
            JOptionPane.showConfirmDialog(this, "The step is not correct", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
            m_jStepQty.setText(null);
            return;
        }
    }

}                                    

private void OnCheckBonus(java.awt.event.FocusEvent evt) {
    if (!m_jBonusArticle.getText().equals("")) {

        try {
            try {
                ProductInfoExt _pie = dls.getProductInfoByReference(m_jBonusArticle.getText());
                m_jBonusArticle.setText(_pie.getID());
                _DescBonusArticle = _pie.getName();
            } catch (NullPointerException e) {
                JOptionPane.showConfirmDialog(this, "This Product doesn't exist", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
            }

        } catch (BasicException e) {
            JOptionPane.showConfirmDialog(this, "This Product doesn't exist", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
        }

    }
}

private void OnCheckArticle(java.awt.event.FocusEvent evt) {

    if (!m_jArticle.getText().equals("")) {
        try {
            try {
                ProductInfoExt _pie = dls.getProductInfoByReference(m_jArticle.getText());
                m_jArticle.setText(_pie.getID());
            } catch (NullPointerException e) {
                JOptionPane.showConfirmDialog(this, "This Product doesn't exist", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
            }
        } catch (BasicException e) {
            JOptionPane.showConfirmDialog(this, "This Product doesn't exist", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
        }

    }

}

private void OnCheckCategory(java.awt.event.FocusEvent evt) {
    if (!m_jCategory.getText().equals("")) {
        try {
            try {
                CategoryInfo _ci = dls.getCategoryInfo(m_jCategory.getText());
            } catch (NullPointerException e) {
                JOptionPane.showConfirmDialog(this, "This Category doesn't exist", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
            }
        } catch (BasicException e) {
            JOptionPane.showConfirmDialog(this, "This Category doesn't exist", AppLocal.getIntString("Invalid Entry"), JOptionPane.WARNING_MESSAGE);
        }

    }
}

    private boolean isNumeric(String test) {
        try {
            new java.math.BigInteger(test);
        } catch (NumberFormatException ex) {
            return false;
        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        m_jArticle = new javax.swing.JTextField();
        m_jPanName = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        m_jName = new javax.swing.JTextField();
        m_jPanProduct = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        m_jSearch = new javax.swing.JButton();
        m_jProdName = new javax.swing.JTextField();
        m_jPanDate = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        m_jEndDate = new javax.swing.JTextField();
        m_jStartDate = new javax.swing.JTextField();
        btnValidFrom = new javax.swing.JButton();
        btnValidTo = new javax.swing.JButton();
        m_jPanCat = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        m_jCatName = new javax.swing.JComboBox();
        m_jPanDiscount = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        m_jType = new javax.swing.JComboBox();
        m_jPanTime = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        m_jStartHour = new javax.swing.JTextField();
        m_jEndHour = new javax.swing.JTextField();
        m_jPanAmount = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        m_jAmount = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        m_jPanMinMax = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        m_jMax = new javax.swing.JTextField();
        m_jMin = new javax.swing.JTextField();
        m_jPanSAmount = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        m_jStepAmount = new javax.swing.JTextField();
        m_jPanQuan = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        m_jStepQty = new javax.swing.JTextField();
        m_jPanBonus = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        m_jBonusProd = new javax.swing.JTextField();
        m_jSearch1 = new javax.swing.JButton();
        m_jBonusArticle = new javax.swing.JTextField();
        m_jCategory = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        m_jArticle.setPreferredSize(new java.awt.Dimension(110, 28));
        jPanel1.add(m_jArticle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, 28));

        m_jPanName.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("<html><b>Name</b>");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 28));

        m_jName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        m_jName.setMaximumSize(new java.awt.Dimension(110, 28));
        m_jName.setMinimumSize(new java.awt.Dimension(110, 28));
        m_jName.setPreferredSize(new java.awt.Dimension(110, 28));

        javax.swing.GroupLayout m_jPanNameLayout = new javax.swing.GroupLayout(m_jPanName);
        m_jPanName.setLayout(m_jPanNameLayout);
        m_jPanNameLayout.setHorizontalGroup(
            m_jPanNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanNameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(m_jName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
        );
        m_jPanNameLayout.setVerticalGroup(
            m_jPanNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jPanNameLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(m_jPanNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.add(m_jPanName, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, -1));

        m_jPanProduct.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Product");

        m_jSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/search24.png"))); // NOI18N
        m_jSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout m_jPanProductLayout = new javax.swing.GroupLayout(m_jPanProduct);
        m_jPanProduct.setLayout(m_jPanProductLayout);
        m_jPanProductLayout.setHorizontalGroup(
            m_jPanProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanProductLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(m_jProdName, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(m_jSearch)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        m_jPanProductLayout.setVerticalGroup(
            m_jPanProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jPanProductLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(m_jPanProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jProdName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.add(m_jPanProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 500, -1));

        m_jPanDate.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        m_jPanDate.setPreferredSize(new java.awt.Dimension(500, 37));

        jLabel2.setText("Date");

        btnValidFrom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        btnValidFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidFromActionPerformed(evt);
            }
        });

        btnValidTo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        btnValidTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidToActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout m_jPanDateLayout = new javax.swing.GroupLayout(m_jPanDate);
        m_jPanDate.setLayout(m_jPanDateLayout);
        m_jPanDateLayout.setHorizontalGroup(
            m_jPanDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanDateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(m_jStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnValidFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_jEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnValidTo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
        m_jPanDateLayout.setVerticalGroup(
            m_jPanDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jPanDateLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(m_jPanDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnValidFrom)
                    .addComponent(m_jEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnValidTo)))
        );

        jPanel1.add(m_jPanDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 500, -1));

        m_jPanCat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Product Category");

        m_jCatName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        m_jCatName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jCatNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout m_jPanCatLayout = new javax.swing.GroupLayout(m_jPanCat);
        m_jPanCat.setLayout(m_jPanCatLayout);
        m_jPanCatLayout.setHorizontalGroup(
            m_jPanCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanCatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_jCatName, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );
        m_jPanCatLayout.setVerticalGroup(
            m_jPanCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanCatLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(m_jPanCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jCatName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.add(m_jPanCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 500, 32));

        m_jPanDiscount.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Discount");

        m_jType.setPreferredSize(new java.awt.Dimension(210, 28));

        javax.swing.GroupLayout m_jPanDiscountLayout = new javax.swing.GroupLayout(m_jPanDiscount);
        m_jPanDiscount.setLayout(m_jPanDiscountLayout);
        m_jPanDiscountLayout.setHorizontalGroup(
            m_jPanDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanDiscountLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(m_jType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );
        m_jPanDiscountLayout.setVerticalGroup(
            m_jPanDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jPanDiscountLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(m_jPanDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.add(m_jPanDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 500, -1));

        m_jPanTime.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Time");

        m_jStartHour.setPreferredSize(new java.awt.Dimension(110, 28));

        m_jEndHour.setPreferredSize(new java.awt.Dimension(110, 28));

        javax.swing.GroupLayout m_jPanTimeLayout = new javax.swing.GroupLayout(m_jPanTime);
        m_jPanTime.setLayout(m_jPanTimeLayout);
        m_jPanTimeLayout.setHorizontalGroup(
            m_jPanTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanTimeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(m_jStartHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(m_jEndHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );
        m_jPanTimeLayout.setVerticalGroup(
            m_jPanTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jPanTimeLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(m_jPanTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jStartHour, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jEndHour, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.add(m_jPanTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 500, -1));

        m_jPanAmount.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Amount");

        m_jAmount.setPreferredSize(new java.awt.Dimension(110, 28));

        javax.swing.GroupLayout m_jPanAmountLayout = new javax.swing.GroupLayout(m_jPanAmount);
        m_jPanAmount.setLayout(m_jPanAmountLayout);
        m_jPanAmountLayout.setHorizontalGroup(
            m_jPanAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanAmountLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(m_jPanAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(m_jPanAmountLayout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(m_jAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(228, Short.MAX_VALUE))
        );
        m_jPanAmountLayout.setVerticalGroup(
            m_jPanAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jPanAmountLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(m_jPanAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.add(m_jPanAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 500, -1));

        m_jPanMinMax.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setText("Min / Max");

        m_jMin.setPreferredSize(new java.awt.Dimension(68, 28));

        javax.swing.GroupLayout m_jPanMinMaxLayout = new javax.swing.GroupLayout(m_jPanMinMax);
        m_jPanMinMax.setLayout(m_jPanMinMaxLayout);
        m_jPanMinMaxLayout.setHorizontalGroup(
            m_jPanMinMaxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanMinMaxLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(m_jPanMinMaxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(m_jPanMinMaxLayout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(m_jMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addComponent(m_jMax, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(181, Short.MAX_VALUE))
        );
        m_jPanMinMaxLayout.setVerticalGroup(
            m_jPanMinMaxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jPanMinMaxLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(m_jPanMinMaxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jMin, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jMax, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.add(m_jPanMinMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 500, -1));

        m_jPanSAmount.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("Step Amount");

        m_jStepAmount.setPreferredSize(new java.awt.Dimension(110, 28));

        javax.swing.GroupLayout m_jPanSAmountLayout = new javax.swing.GroupLayout(m_jPanSAmount);
        m_jPanSAmount.setLayout(m_jPanSAmountLayout);
        m_jPanSAmountLayout.setHorizontalGroup(
            m_jPanSAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanSAmountLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(m_jPanSAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(m_jPanSAmountLayout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(m_jStepAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(228, Short.MAX_VALUE))
        );
        m_jPanSAmountLayout.setVerticalGroup(
            m_jPanSAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jPanSAmountLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(m_jPanSAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_jStepAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.add(m_jPanSAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 500, -1));

        m_jPanQuan.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setText("Quantity");

        m_jStepQty.setPreferredSize(new java.awt.Dimension(110, 28));

        javax.swing.GroupLayout m_jPanQuanLayout = new javax.swing.GroupLayout(m_jPanQuan);
        m_jPanQuan.setLayout(m_jPanQuanLayout);
        m_jPanQuanLayout.setHorizontalGroup(
            m_jPanQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanQuanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(m_jPanQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(m_jPanQuanLayout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(m_jStepQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(228, Short.MAX_VALUE))
        );
        m_jPanQuanLayout.setVerticalGroup(
            m_jPanQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jPanQuanLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(m_jPanQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_jStepQty, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.add(m_jPanQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 500, -1));

        m_jPanBonus.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setText("Bonus Article");
        jLabel11.setMaximumSize(new java.awt.Dimension(62, 18));
        jLabel11.setMinimumSize(new java.awt.Dimension(62, 18));
        jLabel11.setPreferredSize(new java.awt.Dimension(62, 18));

        m_jSearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/search24.png"))); // NOI18N
        m_jSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jSearch1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout m_jPanBonusLayout = new javax.swing.GroupLayout(m_jPanBonus);
        m_jPanBonus.setLayout(m_jPanBonusLayout);
        m_jPanBonusLayout.setHorizontalGroup(
            m_jPanBonusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanBonusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(m_jBonusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(m_jSearch1)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        m_jPanBonusLayout.setVerticalGroup(
            m_jPanBonusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanBonusLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(m_jPanBonusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jPanBonusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(m_jBonusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(m_jSearch1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.add(m_jPanBonus, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 500, -1));

        m_jBonusArticle.setPreferredSize(new java.awt.Dimension(110, 28));
        jPanel1.add(m_jBonusArticle, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, -1, -1));

        m_jCategory.setPreferredSize(new java.awt.Dimension(110, 28));
        m_jCategory.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                m_jCategoryPropertyChange(evt);
            }
        });
        jPanel1.add(m_jCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, -1, -1));

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnValidFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidFromActionPerformed

    Date date;
    try {
        date = (Date) Formats.TIMESTAMP.parseValue(m_jStartDate.getText());
    } catch (BasicException e) {
        date = null;
    }
    date = JCalendarDialog.showCalendarTimeHours(this, date);
    if (date != null) {


        DateFormat formatter_date;
        formatter_date = new SimpleDateFormat("yyyyMMdd");

        DateFormat formatter_heure;
        formatter_heure = new SimpleDateFormat("HH");

        String _date = formatter_date.format(date);
        String _heure = formatter_heure.format(date);
        m_jStartDate.setText(Formats.STRING.formatValue(_date));
        m_jStartHour.setText(Formats.STRING.formatValue(_heure));
    }

    }//GEN-LAST:event_btnValidFromActionPerformed

    private void btnValidToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidToActionPerformed

    Date date;
    try {
        date = (Date) Formats.TIMESTAMP.parseValue(m_jEndDate.getText());
    } catch (BasicException e) {
        date = null;
    }
    date = JCalendarDialog.showCalendarTimeHours(this, date);
    if (date != null) {


        DateFormat formatter_date;
        formatter_date = new SimpleDateFormat("yyyyMMdd");

        DateFormat formatter_heure;
        formatter_heure = new SimpleDateFormat("HH");

        String _date = formatter_date.format(date);
        String _heure = formatter_heure.format(date);

        m_jEndDate.setText(Formats.STRING.formatValue(_date));
        m_jEndHour.setText(Formats.STRING.formatValue(_heure));
    }
    }//GEN-LAST:event_btnValidToActionPerformed

    private void m_jSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jSearchActionPerformed
        ProductInfoExt prod = JProductFinder.showMessage(PromoEditor.this, m_dlSales);
        if (prod != null) {
            //buttonTransition(prod);
            assignProduct(prod);
            try {
                getProdName(prod.getID());
            } catch (BasicException ex) {
                Logger.getLogger(PromoEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_m_jSearchActionPerformed

    private void m_jSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jSearch1ActionPerformed
        ProductInfoExt prod = JProductFinder.showMessage(PromoEditor.this, m_dlSales);
        if (prod != null) {
            //buttonTransition(prod);
            assignProduct1(prod);
            try {
                getBonusName(prod.getID());
            } catch (BasicException ex) {
                Logger.getLogger(PromoEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_m_jSearch1ActionPerformed

    private void m_jCatNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jCatNameActionPerformed
        try {
            getCatID(m_jCatModel.getSelectedText());
        } catch (BasicException ex) {
            Logger.getLogger(PromoEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_m_jCatNameActionPerformed

    private void m_jCategoryPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_m_jCategoryPropertyChange
        if(m_jCategory.getText().isEmpty()) {
            m_jCatName.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_m_jCategoryPropertyChange
   
    private void assignProduct(ProductInfoExt prod) {

            if (prod == null) {
                //m_jArticle.setText(null);
                //Jt_articleid.setText(null);
            } else {
                m_jArticle.setText(prod.getID());
            }

    }
    
    private void assignProduct1(ProductInfoExt prod) {

            if (prod == null) {
                //m_jBonusArticle.setText(null);
                //jt_bonusid.setText(null);
            } else {
                m_jBonusArticle.setText(prod.getID());
            }

    }
    
    private void getCatID(String name) throws BasicException {
        Connection connection;// = null;
        Statement statement;// = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;// = null;
        String prodname = null;

        PropertyUtils properties = new PropertyUtils();

        try {

            String driverName = properties.getDriverName();
            Class.forName(driverName);

            String url = properties.getUrl();
            String username = properties.getDBUser();
            String password = properties.getDBPassword();
            connection = (Connection) DriverManager.getConnection(url, username, password);

            statement = (Statement) connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM CATEGORIES WHERE NAME = '" + name + "'");
            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                
                    m_jCategory.setText(id);
                
            }

            connection.close();
        } catch (ClassNotFoundException e) {
            // Could not find the database driver
        } catch (SQLException e) {
            // Could not connect to the database
            System.out.println(e);


        }
}
    
    private void getCatName(String Id) throws BasicException {
        Connection connection;// = null;
        Statement statement;// = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;// = null;
        String prodname = null;

        PropertyUtils properties = new PropertyUtils();

        try {

            String driverName = properties.getDriverName();
            Class.forName(driverName);

            String url = properties.getUrl();
            String username = properties.getDBUser();
            String password = properties.getDBPassword();
            connection = (Connection) DriverManager.getConnection(url, username, password);

            statement = (Statement) connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM CATEGORIES WHERE ID = '" + Id + "'");
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                
                int ii=m_jCatName.getItemCount();
                for(int i=1; i<ii; i++){
                    String a = m_jCatModel.getElementAt(i).toString();
                    String b = name;
                    if(a.equals(b)){
                        //m_jCatModel.setSelectedKey(i);
                        m_jCatModel.setSelectedItem(name);
                    }
                }
                
            }

            connection.close();
        } catch (ClassNotFoundException e) {
            // Could not find the database driver
        } catch (SQLException e) {
            // Could not connect to the database
            System.out.println(e);


        }
}
    
    private void getProdName(String Id) throws BasicException {
        Connection connection;// = null;
        Statement statement;// = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;// = null;
        String prodname = null;

        PropertyUtils properties = new PropertyUtils();

        try {

            String driverName = properties.getDriverName();
            Class.forName(driverName);

            String url = properties.getUrl();
            String username = properties.getDBUser();
            String password = properties.getDBPassword();
            connection = (Connection) DriverManager.getConnection(url, username, password);

            statement = (Statement) connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM PRODUCTS WHERE ID = '" + Id + "'");
            while (resultSet.next()) {
                String id = resultSet.getString("NAME");
                m_jProdName.setText(id);
            }

            connection.close();
        } catch (ClassNotFoundException e) {
            // Could not find the database driver
        } catch (SQLException e) {
            // Could not connect to the database
            System.out.println(e);


        }
}
    
    private void getBonusName(String Id) throws BasicException {
        Connection connection;// = null;
        Statement statement;// = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;// = null;
        String prodname = null;

        PropertyUtils properties = new PropertyUtils();

        try {

            String driverName = properties.getDriverName();
            Class.forName(driverName);

            String url = properties.getUrl();
            String username = properties.getDBUser();
            String password = properties.getDBPassword();
            connection = (Connection) DriverManager.getConnection(url, username, password);

            statement = (Statement) connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM PRODUCTS WHERE ID = '" + Id + "'");
            while (resultSet.next()) {
                String id = resultSet.getString("NAME");
                m_jBonusProd.setText(id);
            }

            connection.close();
        } catch (ClassNotFoundException e) {
            // Could not find the database driver
        } catch (SQLException e) {
            // Could not connect to the database
            System.out.println(e);


        }
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnValidFrom;
    private javax.swing.JButton btnValidTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField m_jAmount;
    private javax.swing.JTextField m_jArticle;
    private javax.swing.JTextField m_jBonusArticle;
    private javax.swing.JTextField m_jBonusProd;
    private javax.swing.JComboBox m_jCatName;
    private javax.swing.JTextField m_jCategory;
    private javax.swing.JTextField m_jEndDate;
    private javax.swing.JTextField m_jEndHour;
    private javax.swing.JTextField m_jMax;
    private javax.swing.JTextField m_jMin;
    private javax.swing.JTextField m_jName;
    private javax.swing.JPanel m_jPanAmount;
    private javax.swing.JPanel m_jPanBonus;
    private javax.swing.JPanel m_jPanCat;
    private javax.swing.JPanel m_jPanDate;
    private javax.swing.JPanel m_jPanDiscount;
    private javax.swing.JPanel m_jPanMinMax;
    private javax.swing.JPanel m_jPanName;
    private javax.swing.JPanel m_jPanProduct;
    private javax.swing.JPanel m_jPanQuan;
    private javax.swing.JPanel m_jPanSAmount;
    private javax.swing.JPanel m_jPanTime;
    private javax.swing.JTextField m_jProdName;
    private javax.swing.JButton m_jSearch;
    private javax.swing.JButton m_jSearch1;
    private javax.swing.JTextField m_jStartDate;
    private javax.swing.JTextField m_jStartHour;
    private javax.swing.JTextField m_jStepAmount;
    private javax.swing.JTextField m_jStepQty;
    private javax.swing.JComboBox m_jType;
    // End of variables declaration//GEN-END:variables
}
