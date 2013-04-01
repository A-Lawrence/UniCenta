//    uniCenta oPOS  - Touch Friendly Point Of Sale
//    Copyright (C) 2009-2012 uniCenta
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

package com.openbravo.pos.forms;

import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.util.StringUtils;
import java.io.IOException;

/**
 *
 * @author adrianromero
 */
public class BeanFactoryScript implements BeanFactoryApp {
    
    private BeanFactory bean = null;
    private String script;
    
    public BeanFactoryScript(String script) {
        this.script = script;
    }
    
    @Override
    public void init(AppView app) throws BeanFactoryException {
        
        // Resource
        try {
            ScriptEngine eng = ScriptFactory.getScriptEngine(ScriptFactory.BEANSHELL);
            eng.put("app", app);
            
            bean = (BeanFactory) eng.eval(StringUtils.readResource(script));
            if (bean == null) {
                // old definition
                bean = (BeanFactory) eng.get("bean");
            }
            
            // todo // llamar al init del bean
            if (bean instanceof BeanFactoryApp) {
                ((BeanFactoryApp) bean).init(app);
            }
// JG 16 May use multicatch
        } catch (ScriptException | IOException e) {
            throw new BeanFactoryException(e);
        }     
    }

    @Override
    public Object getBean() {
        return bean.getBean();
    }
}
