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

package com.openbravo.pos.epm;

/**
 *
 * @author Ali Safdar & Aneeqa Baber
 */
public class Break {

    private String m_sId;
    private String m_sName;
    private String m_sNotes;
    private boolean m_sVisible;

    public Break(String id, String name, String notes,  boolean visible) {
        m_sId = id;
        m_sName = name;
        m_sNotes = notes;
        m_sVisible = visible;
    }

    public String getId() {
        return m_sId;
    }

    public void setId(String Id) {
        this.m_sId = Id;
    }

    public String getName() {
        return m_sName;
    }

    public void setName(String Name) {
        this.m_sName = Name;
    }

    public String getNotes() {
        return m_sNotes;
    }

    public void setNotes(String Notes) {
        this.m_sNotes = Notes;
    }

    public boolean isVisible() {
        return m_sVisible;
    }

    public void setVisible(boolean Visible) {
        this.m_sVisible = Visible;
    }
}
