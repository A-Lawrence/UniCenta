//    uniCenta oPOS  - Touch Friendly Point Of Sale
//    Copyright (c) 2009-2012 uniCenta
//    http://www.unicenta.net/unicentaopos
//
//    This file is part of uniCenta oPOS
//
//    uniCenta oPOS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//   uniCenta oPOS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with uniCenta oPOS.  If not, see <http://www.gnu.org/licenses/>.

package com.openbravo.pos.inventory;

import com.openbravo.format.Formats;
import com.openbravo.pos.util.StringUtils;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 *
 * @author adrianromero
 */
public class InventoryRecord {
    
    private Date m_dDate;
    private MovementReason m_reason;
    private LocationInfo m_locationOri;   
    private List<InventoryLine> m_invlines;
    
    /** Creates a new instance of InventoryRecord */
    public InventoryRecord(Date d, MovementReason reason, LocationInfo location, List<InventoryLine> invlines) {
        m_dDate = d;
        m_reason = reason;
        m_locationOri = location;
        m_invlines = invlines;
    }
    
    public Date getDate() {
        return m_dDate;
    }   
    public MovementReason getReason() {
        return m_reason;
    }    
    public LocationInfo getLocation() {
        return m_locationOri;
    }   
    
    public List<InventoryLine> getLines() {
        return m_invlines;
    }  
    
    public boolean isInput() {
        return m_reason.isInput();
    }
    
    public double getSubTotal() {
        double dSuma = 0.0;
        InventoryLine oLine;            
        for (Iterator<InventoryLine> i = m_invlines.iterator(); i.hasNext();) {
            oLine = i.next();
            dSuma += oLine.getSubValue();
        }        
        return dSuma;
    }
    
    public String printDate() {
        return Formats.TIMESTAMP.formatValue(m_dDate);
    }    
    public String printLocation() {
//        return m_locationOri.toString();
        return StringUtils.encodeXML(m_locationOri.toString());
    }
    public String printReason() {
//        return m_reason.toString();
        return StringUtils.encodeXML(m_reason.toString());
    }

    public String printSubTotal() {
        return Formats.CURRENCY.formatValue(new Double(getSubTotal()));
    }    
}
