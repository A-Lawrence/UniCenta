package com.openbravo.pos.promotion;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerRead;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aescart1
 */
public class PromoInfo implements SerializableRead, IKeyed {
    
    private static final long serialVersionUID = 8906929819402L;
    private String m_sID;
    private String m_sName;
    private Integer m_iStartHour; 
    private Integer m_iEndHour;
    private String m_sArticle;
    private String m_sArticleCategory;
    private Integer m_iType;
    private Double m_bdAmount;
    private Integer m_iMinQuantity;
    private Integer m_iMaxQuantity;
    private Integer m_iStepQuantity;
    private Integer m_iStepAmount;
    private String m_sArticleBonus;
    private String m_sArticleBonusDescription;
    
    private List<PromoInfo> promos = new ArrayList<PromoInfo>();
    

    public PromoInfo(String sID,String sName,Integer iStartHour,Integer iEndHour, String sArticle,
     String sArticleCategory,Integer iType,Double bdAmount,Integer iMinQuantity,Integer iMaxQuantity,
     Integer iStepQuantity,Integer iStepAmount,String sArticleBonus,String sArticleBonusDescription) {
        
        this.m_sID =sID;
                this.m_sName =sName;
                this.m_iStartHour = iStartHour; 
                this.m_iEndHour = iEndHour;
                this.m_sArticle = sArticle;
                this.m_sArticleCategory = sArticleCategory;
                this.m_iType = iType;
                this.m_bdAmount = bdAmount;
                this.m_iMinQuantity = iMinQuantity;
                this.m_iMaxQuantity = iMaxQuantity;
                this.m_iStepQuantity = iStepQuantity;
                this.m_iStepAmount = iStepAmount;
                this.m_sArticleBonus = sArticleBonus;
                this.m_sArticleBonusDescription = sArticleBonusDescription;
    }
    
   public static SerializerRead getSerializerRead() {
        return new SerializerRead() { public Object readValues(DataRead dr) throws BasicException {

            return new PromoInfo(

                        dr.getString(1), 
                        dr.getString(2),
                        dr.getInt(3),
                        dr.getInt(4),
                        dr.getString(5),
                        dr.getString(6),
                        dr.getInt(7),
                        dr.getDouble(8),
                        dr.getInt(9),
                        dr.getInt(10),
                        dr.getInt(11),
                        dr.getInt(12),
                        dr.getString(13),
                        dr.getString(14)
                        
                    );
        }};
    }
    
    
    public PromoInfo() {
        m_sID = null;
        m_sName = null;
        m_iStartHour = null; 
        m_iEndHour = null;
        m_sArticle = null;
        m_sArticleCategory = null;
        m_iType = 0;
        m_bdAmount = null;
        m_iMinQuantity = null;
        m_iMaxQuantity = null;
        m_iStepQuantity = null;
        m_iStepAmount = null;
        m_sArticleBonus = null;
        m_sArticleBonusDescription = null;
    }
   
    public Object getKey() {
        return m_sID;
    }
    
    
    public void readValues(DataRead dr) throws BasicException {
        m_sID = dr.getString(1);
        m_sName = dr.getString(2);
        m_iStartHour = dr.getInt(3); 
        m_iEndHour = dr.getInt(4);
        m_sArticle = dr.getString(5);
        m_sArticleCategory = dr.getString(6);
        m_iType = dr.getInt(7);
        m_bdAmount = dr.getDouble(8);
        m_iMinQuantity = dr.getInt(9);
        m_iMaxQuantity = dr.getInt(10);
        m_iStepQuantity = dr.getInt(11);
        m_iStepAmount = dr.getInt(12);
        m_sArticleBonus = dr.getString(13); 
        m_sArticleBonusDescription = dr.getString(14); 
    } 
    
    public void setID(String sID) {
        m_sID = sID;
    }
    
    public String getID() {
        return m_sID;
    }

    public String getName() {
        return m_sName;
    }
    
    public void setName(String sName) {
        m_sName = sName;
    } 
    
    public Integer getStartHour() {
        return m_iStartHour;
    }
    
    public void setStartHour(Integer iStartHour) {
        iStartHour = m_iStartHour;
    }     
    
    public Integer getEndHour() {
        return m_iEndHour;
    }
    
    public void setEndHour(Integer iEndHour) {
        iEndHour = m_iEndHour;
    }   
    
    public String getArticle() {
        return m_sArticle;
    }
    
    public void setArticle(String sArticle) {
        m_sArticle = sArticle;
    }     
    
    public String getArticleCategory() {

        return m_sArticleCategory;
    }
    
    public void setArticleCategory(String sArticleCategory) {
        m_sArticleCategory = sArticleCategory;
    }    
    
    public Integer getType() {
        return m_iType;
    }
    
    public void setType(Integer iType) {
        iType = m_iType;
    }       

    public Double getAmount() {
        return m_bdAmount;
    }
    
    public void setAmount(Double bdAmount) {
        bdAmount = m_bdAmount;
    }        
    
    public Integer getMinQuantity() {
        return m_iMinQuantity;
    }
    
    public void setMinQuantity(Integer iMinQuantity) {
        iMinQuantity = m_iMinQuantity;
    }       
    
    public Integer getMaxQuantity() {
        return m_iMaxQuantity;
    }
    
    public void setMaxQuantity(Integer iMaxQuantity) {
        iMaxQuantity = m_iMaxQuantity;
    }        
    
    public Integer getStepQuantity() {
        return m_iStepQuantity;
    }
    
    public void setStepQuantity(Integer iStepQuantity) {
        iStepQuantity = m_iStepQuantity;
    }      
    
    public Integer getStepAmount() {
        return m_iStepAmount;
    }
    
    public void setStepAmount(Integer iStepAmount) {
        iStepAmount = m_iStepAmount;
    }      
    
    public String toString(){
        return m_sName;
    }
    
    public String getArticleBonus() {
        return m_sArticleBonus;
    }
    
    public void setArticleBonus(String sArticleBonus) {
        m_sArticleBonus = sArticleBonus;
    }

    public String getArticleBonusDescription() {
        return m_sArticleBonusDescription;
    }
    
    public void setArticleBonusDescription(String sArticleBonusDescription) {
        m_sArticleBonusDescription = sArticleBonusDescription;
    }    
    
    public void setPromos(List<PromoInfo> l) {
        promos = l;
    }
    
    
}