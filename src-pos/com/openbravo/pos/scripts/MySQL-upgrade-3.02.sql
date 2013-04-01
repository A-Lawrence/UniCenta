--    uniCenta oPOS - Touch Friendly Point Of Sale
--    Copyright (C) 2009-2012 uniCenta
--    http://www.unicenta.net
--
--    This file is part of uniCenta oPOS.
--
--    uniCenta oPOS is free software: you can redistribute it and/or modify
--    it under the terms of the GNU General Public License as published by
--    the Free Software Foundation, either version 3 of the License, or
--    (at your option) any later version.
--
--    uniCenta oPOS is distributed in the hope that it will be useful,
--    but WITHOUT ANY WARRANTY; without even the implied warranty of
--    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
--    GNU General Public License for more details.
--
--    You should have received a copy of the GNU General Public License
--    along with uniCenta oPOS.  If not, see <http://www.gnu.org/licenses/>.

-- Database upgrade script for MySQL
-- v3.02 - v3.03

-- RESOURCES
-- ABSTRACTED TO SEPARATE DOWNLOAD SO AS NOT TO OVERWRITE EXISTING USER SETTINGS
-- UPDATE RESOURCES SET CONTENT = $FILE{/com/openbravo/pos/templates/Printer.TicketKitchen.xml} WHERE ID = '35';
-- UPDATE RESOURCES SET CONTENT = $FILE{/com/openbravo/pos/templates/Printer.TicketLine.xml} WHERE ID = '36';
-- UPDATE RESOURCES SET CONTENT = $FILE{/com/openbravo/pos/templates/Printer.TicketPreview.xml} WHERE ID = '37';
-- UPDATE RESOURCES SET CONTENT = $FILE{/com/openbravo/pos/templates/script.ReceiptConsolidate.xml} WHERE ID = '43';

-- HOSTS
CREATE TABLE HOSTS (
    ID INT NOT NULL AUTO_INCREMENT,
    REFERENCE VARCHAR(60) NOT NULL,
    NAME VARCHAR(50) NOT NULL,
    PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX HOSTS_REFERENCE ON HOSTS(REFERENCE);

INSERT INTO HOSTS (REFERENCE, NAME) VALUES ('Carried over from previous install - $APP_HOST{}', $APP_HOST{});

-- APP' VERSION
UPDATE APPLICATIONS SET NAME = $APP_NAME{}, VERSION = $APP_VERSION{} WHERE ID = $APP_ID{};