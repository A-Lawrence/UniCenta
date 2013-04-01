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

package com.openbravo.pos.printer;

import com.openbravo.pos.forms.AppLocal;

public class DevicePrinterNull implements DevicePrinter {
    
    private String m_sName;
    private String m_sDescription;
    
    /** Creates a new instance of DevicePrinterNull */
    public DevicePrinterNull() {
        this(null);
    }
    
    /** Creates a new instance of DevicePrinterNull */
    public DevicePrinterNull(String desc) {
        m_sName = AppLocal.getIntString("Printer.Null");
        m_sDescription = desc;
    }

    @Override
    public String getPrinterName() {
        return m_sName;
    }    
    @Override
    public String getPrinterDescription() {
        return m_sDescription;
    }        
    @Override
    public javax.swing.JComponent getPrinterComponent() {
        return null;
    }
    @Override
    public void reset() {
    }
    
    @Override
    public void beginReceipt() {
    }
    @Override
    public void printBarCode(String type, String position, String code) {        
    }    
    @Override
    public void printImage(java.awt.image.BufferedImage image) {
    }
    @Override
    public void beginLine(int iTextSize) {
    }   
    @Override
    public void printText(int iStyle, String sText) {
    }   
    @Override
    public void endLine() {
    }
    @Override
    public void endReceipt() {
    }
    @Override
    public void openDrawer() {
    }
}
