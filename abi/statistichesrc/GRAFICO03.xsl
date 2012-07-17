<?xml version="1.0"?>

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



