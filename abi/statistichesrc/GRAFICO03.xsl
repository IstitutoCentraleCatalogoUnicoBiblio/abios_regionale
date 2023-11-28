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
<xsl:decimal-format name="european" decimal-separator=',' grouping-separator='.'/>
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/">

<table name="GRAFICO03" title="GRAFICO 3 BIBLIOTECHE CENSITE PER CATALOGO" summary="Grafico_Biblioteche_censite_per_catalogo">
<HEADERS>
<column num="1" colspan="1" rowspan="1">REGIONE</column>
<column num="2" colspan="1" rowspan="1">Con catalogo</column>
<column num="3" colspan="1" rowspan="1">Con catalogo on line</column>
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
<column num="2">
<xsl:choose>
<xsl:when test="string-length(//ROW[regione = $reg]/cataloghi) > 0" >
<xsl:variable name="val"> 
<xsl:choose>
	<xsl:when test="//ROW[regione3 = $reg]/numero_biblioteche">
		<xsl:value-of select="format-number(((100*(//ROW[regione = $reg]/cataloghi)) div (//ROW[regione3 = $reg]/numero_biblioteche)),'###.###.###,#','european')" /> 
	</xsl:when>
	<xsl:otherwise>
		<xsl:value-of select="format-number('', '#')" />
	</xsl:otherwise>
</xsl:choose>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains($val,',') or $val = 100">
<xsl:value-of select="$val" />
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="concat($val,',0')" />
</xsl:otherwise>
</xsl:choose>
</xsl:when>
<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</column>
<column num="3">
<xsl:choose>
<xsl:when test="string-length(//ROW[regione2 = $reg]/cataloghi_online) > 0" >
<xsl:variable name="val">
<xsl:choose>
	<xsl:when test="//ROW[regione3 = $reg]/numero_biblioteche">
 		<xsl:value-of select="format-number(((100*(//ROW[regione2 = $reg]/cataloghi_online)) div (//ROW[regione3 = $reg]/numero_biblioteche)),'###.###.###,#','european')" />
	</xsl:when>
	<xsl:otherwise>
		<xsl:value-of select="format-number('', '#')" />
	</xsl:otherwise>
</xsl:choose>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains($val,',') or $val = 100">
<xsl:value-of select="$val" />
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="concat($val,',0')" />
</xsl:otherwise>
</xsl:choose>
</xsl:when>
<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</column>
</ROW>
</xsl:for-each>
</ROWS>
</table>
</xsl:template>
</xsl:stylesheet>



