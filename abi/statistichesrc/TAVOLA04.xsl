<?xml version="1.0"?>

<!--
  ~ Author Inera srl https://www.inera.it
  ~ Copyright (C) 2023  Inera srl https://www.inera.it/
  ~
  ~ European Union Public Licence V. 1.2
  ~ EUPL (c) the European Community 2017
  ~
  ~ This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
  ~ Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
  ~ The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
  ~ Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
  ~
  ~ You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
  -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:decimal-format name="european" decimal-separator=',' grouping-separator='.' />
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/">
<table name="TAVOLA04" title="Tavola 4 BIBLIOTECHE FONDATE NEL XX e XXI SECOLO" summary="Biblioteche_per_anno_di_fond">
<ROWS>

<ROW num="1">
<column num="1">anno non disponibile</column>
<column num="2"><xsl:value-of select="format-number(sum(//ROW[fondazione = '20' or fondazione = '21' or contains(fondazione, ' - ')]/count),'###.###.###,##','european')" /></column>
</ROW>
<ROW num="2">
<column num="1">fino al 1948</column>
<column num="2"><xsl:value-of select="format-number(sum(//ROW[string-length(fondazione) = 4 and fondazione &lt; 1948]/count),'###.###.###,##','european')" /></column>
</ROW>
<ROW num="3">
<column num="1">dal 1948 al 1972</column>
<column num="2"><xsl:value-of select="format-number(sum(//ROW[fondazione &gt; 1947 and fondazione &lt; 1972]/count),'###.###.###,##','european')" /></column>
</ROW>
<ROW num="4">
<column num="1">dal 1972</column>
<column num="2"><xsl:value-of select="format-number(sum(//ROW[fondazione &gt; 1971]/count),'###.###.###,##','european')" /></column>
</ROW>
<ROW num="5">
<column num="1">Totale</column>
<column num="2"><xsl:value-of select="format-number(sum(//ROW[fondazione != '19']/count),'###.###.###,##','european')" /></column>
</ROW>
</ROWS>
</table>


							    </xsl:template>


							    </xsl:stylesheet>



