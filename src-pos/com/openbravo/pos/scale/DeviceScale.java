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

package com.openbravo.pos.scale;

import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppProperties;
import com.openbravo.pos.util.StringParser;
import java.awt.Component;

public class DeviceScale {
    
    private Scale m_scale;
    
    /** Creates a new instance of DeviceScale */
    public DeviceScale(Component parent, AppProperties props) {
        StringParser sd = new StringParser(props.getProperty("machine.scale"));
        String sScaleType = sd.nextToken(':');
        String sScaleParam1 = sd.nextToken(',');
        // String sScaleParam2 = sd.nextToken(',');
        switch (sScaleType) {
            case "dialog1":
                m_scale = new ScaleComm(sScaleParam1);
                break;
            case "samsungesp":
                m_scale = new ScaleSamsungEsp(sScaleParam1);
                break;
            case "fake":
                // a fake scale for debugging purposes
                m_scale = new ScaleFake();
                break;
            case "screen":
                // on screen scale
                m_scale = new ScaleDialog(parent);
                break;
            default:
                m_scale = null;
                break;
        }
    }
    
    public boolean existsScale() {
        return m_scale != null;
    }
    
    public Double readWeight() throws ScaleException {
        
        if (m_scale == null) {
            throw new ScaleException(AppLocal.getIntString("scale.notdefined"));
        } else {
            Double result = m_scale.readWeight();
            if (result == null) {
                return null; // Canceled by the user / scale
            } else if (result.doubleValue() < 0.002) {
                // invalid result. nothing on the scale
                throw new ScaleException(AppLocal.getIntString("scale.invalidvalue"));                
            } else {
                // valid result
                return result;
            }
        }
    }    
}
