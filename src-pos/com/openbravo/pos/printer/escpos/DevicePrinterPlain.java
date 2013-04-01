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

package com.openbravo.pos.printer.escpos;

import com.openbravo.pos.printer.DevicePrinter;
import com.openbravo.pos.printer.TicketPrinterException;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

public class DevicePrinterPlain implements DevicePrinter  {
    
    private static final byte[] NEW_LINE = {0x0D, 0x0A}; // Print and carriage return
      
    private PrinterWritter out;
    private UnicodeTranslator trans;
    
    // Creates new TicketPrinter
    public DevicePrinterPlain(PrinterWritter CommOutputPrinter) throws TicketPrinterException {

        out = CommOutputPrinter;
        trans = new UnicodeTranslatorStar(); // The star translator stands for the 437 int char page
    }
   
    @Override
    public String getPrinterName() {
        return "Plain";
    }
    @Override
    public String getPrinterDescription() {
        return null;
    }   
    @Override
    public JComponent getPrinterComponent() {
        return null;
    }
    @Override
    public void reset() {
    }
    
    @Override
    public void beginReceipt() {
    }
    
    @Override
    public void printImage(BufferedImage image) {
    }
    
    @Override
    public void printBarCode(String type, String position, String code) {        
        if (! DevicePrinter.POSITION_NONE.equals(position)) {                
            out.write(code);
            out.write(NEW_LINE);
        }
    }
    
    @Override
    public void beginLine(int iTextSize) {
    }
    
    @Override
    public void printText(int iStyle, String sText) {
        out.write(trans.transString(sText));
    }
    
    @Override
    public void endLine() {
        out.write(NEW_LINE);
    }
    
    @Override
    public void endReceipt() {       
        out.write(NEW_LINE);
        out.write(NEW_LINE);
        out.write(NEW_LINE);
        out.write(NEW_LINE);
        out.write(NEW_LINE);
        out.flush();
    }
    
    @Override
    public void openDrawer() {
    }
}

