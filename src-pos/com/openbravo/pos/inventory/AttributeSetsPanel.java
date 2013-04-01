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

package com.openbravo.pos.inventory;

import com.openbravo.data.loader.Datas;
import com.openbravo.data.model.*;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.panels.JPanelTable2;

/**
 *
 * @author adrianromero
 */
public class AttributeSetsPanel extends JPanelTable2 {

    private EditorRecord editor;

    /** Creates a new instance of JPanelCategories */
    public AttributeSetsPanel() {
    }

    @Override
    protected void init() {

        row = new Row(
                new Field("ID", Datas.STRING, Formats.STRING),
                new Field(AppLocal.getIntString("Label.Name"), Datas.STRING, Formats.STRING, true, true, true)
        );

        Table table = new Table(
                "ATTRIBUTESET",
                new PrimaryKey("ID"),
                new Column("NAME"));

        lpr = row.getListProvider(app.getSession(), table);
        spr = row.getSaveProvider(app.getSession(), table);

        editor = new AttributeSetsEditor(dirty);
    }

    @Override
    public EditorRecord getEditor() {
        return editor;
    }

    @Override
    public String getTitle() {
        return AppLocal.getIntString("Menu.AttributeSets");
    }
}
