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
<xsl:variable name="unique-descrizione-num" select="//ROW/descrizione[not(.=following::descrizione)]" />

<xsl:variable name="max" select="count($unique-descrizione-num)" />
<table name="TAVOLA07" title="Tavola 7 CONSISTENZA DEL PATRIMONIO" summary="Consistenza_patrimonio">

<ROWS>

<xsl:for-each select="$unique-descrizione-num">

<xsl:variable name="des" select="." />
<xsl:variable name="val" select="//ROW[descrizione = $des]/id_patc"/>

<ROW>
	<xsl:attribute name="num">
				<xsl:value-of select="position()"/>
	</xsl:attribute>
<column num="1"><xsl:if test="//ROW[descrizione = $des]/id_patc_madre != 'null'"> - </xsl:if><xsl:value-of select="$des" /></column>
<column num="2"><xsl:value-of select="format-number(//ROW[descrizione = $des]/sum + sum(//ROW[id_patc_madre = $val]/sum) ,'###.###.###,##','european')" /></column>
</ROW>
</xsl:for-each>

<ROW style="bold">
	<xsl:attribute name="num">
				<xsl:value-of select="$max+1"/>
	</xsl:attribute>
<column num="1">Totale</column>
<column num="2"><xsl:value-of select="format-number(sum(//ROW/sum),'###.###.###,##','european')" /></column>
</ROW>
</ROWS>
</table>


							    </xsl:template>


							    </xsl:stylesheet>



