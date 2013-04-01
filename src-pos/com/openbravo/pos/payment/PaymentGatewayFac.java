//    uniCenta oPOS  - Touch Friendly Point Of Sale
//    Copyright (C) 2008-2009 Openbravo, S.L.
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

package com.openbravo.pos.payment;

import com.openbravo.pos.forms.AppProperties;

public class PaymentGatewayFac {
    
    /** Creates a new instance of PaymentGatewayFac */
    private PaymentGatewayFac() {
    }
    
    public static PaymentGateway getPaymentGateway(AppProperties props) {
        
        String sReader = props.getProperty("payment.gateway");
// JG 16 May 12 use switch
        switch (sReader) {
            case "external":
                return new PaymentGatewayExt();
            case "PayPoint / SecPay":
                return new PaymentGatewayPayPoint(props);
            case "AuthorizeNet":
                return new PaymentGatewayAuthorizeNet(props);
            case "La Caixa (Spain)":
                return new PaymentGatewayCaixa(props);
            case "Planetauthorize":
                return new PaymentGatewayPlanetauthorize(props);
            case "First Data / LinkPoint / YourPay":
                return new PaymentGatewayLinkPoint(props);
            case "PaymentsGateway.net":
                return new PaymentGatewayPGNET(props);
            default:
                return null;
        }
    }      
}
