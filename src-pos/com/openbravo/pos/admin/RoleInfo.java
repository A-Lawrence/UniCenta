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

package com.openbravo.pos.admin;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;

/**
 *
 * @author adrianromero
 * Created on 27 de febrero de 2007, 23:27
 *
 */
public class RoleInfo implements SerializableRead, IKeyed {
    
    private static final long serialVersionUID = 9110127845966L;
    private String m_sID;
    protected String m_sName;
    
    /** Creates a new instance of RoleInfo */
    public RoleInfo() {
        m_sID = null;
        m_sName = null;
    }
   
    @Override
    public Object getKey() {
        return m_sID;
    }
    
    @Override
    public void readValues(DataRead dr) throws BasicException {
        m_sID = dr.getString(1);
        m_sName = dr.getString(2);
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
    
    public void setName(String sValue) {
        m_sName = sValue;
    }    
    
    @Override
    public String toString() {
        return m_sName;
    }
}
