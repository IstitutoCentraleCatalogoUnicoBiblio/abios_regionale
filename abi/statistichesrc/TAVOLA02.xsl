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
<xsl:variable name="unique-tip-num" select="//ROW/descrizione[not(.=following::descrizione)]" />

<xsl:variable name="max" select="count($unique-tip-num)" />
<table name="TAVOLA02" title="Tavola 2 BIBLIOTECHE SECONDO LA TIPOLOGIA FUNZIONALE" summary="Biblioteche_per_tip_funzionale">
<HEADERS>
<column num="1" colspan="1" rowspan="2">REGIONE</column>
<column num="2" rowspan="1">
	<xsl:attribute name="colspan">
				<xsl:value-of select="$max+1"/>
	</xsl:attribute>
CENSITE
</column>
</HEADERS>
<HEADERS>
<xsl:for-each select="$unique-tip-num">
<xsl:sort select="." order="ascending"/>
<column colspan="1" rowspan="1">
	<xsl:attribute name="num">
				<xsl:value-of select="position()+1"/>
	</xsl:attribute>
<xsl:value-of select="." />
</column>
</xsl:for-each>
<column colspan="1" rowspan="1">
	<xsl:attribute name="num">
				<xsl:value-of select="$max+2"/>
	</xsl:attribute>
Totale</column>
</HEADERS>

<ROWS>

<xsl:variable name="unique-regione-num" select="//ROW/regione[not(.=following::regione)]" />
<xsl:for-each select="$unique-regione-num">

<xsl:variable name="reg" select="." />
<ROW>
	<xsl:attribute name="num">
				<xsl:value-of select="position()"/>
	</xsl:attribute>
<column num="1"><xsl:value-of select="$reg" /></column>
<xsl:for-each select="$unique-tip-num">
<xsl:sort select="." order="ascending"/>
<xsl:variable name="tipo" select="." />
<xsl:variable  name="contatore" select="//ROW[regione = $reg and descrizione = $tipo]/count" />

<column>
	<xsl:attribute name="num">
				<xsl:value-of select="position()+1"/>
	</xsl:attribute>

<xsl:choose>
<xsl:when test="$contatore > 0">

<xsl:value-of select="format-number($contatore,'###.###.###,##','european')" />

</xsl:when>
<xsl:otherwise>
-
</xsl:otherwise>
</xsl:choose>
</column>
</xsl:for-each>

<column>
	<xsl:attribute name="num">
				<xsl:value-of select="count($unique-tip-num)+2"/>
	</xsl:attribute>

<xsl:value-of  select="format-number(sum(//ROW[regione = $reg]/count),'###.###.###,##','european')" />

</column>

</ROW>
</xsl:for-each>

</ROWS>
</table>


							    </xsl:template>


							    </xsl:stylesheet>



