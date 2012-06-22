<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:decimal-format name="european" decimal-separator=',' grouping-separator='.' />
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/">
<table name="TAVOLA06" title="Tavola 6 BIBLIOTECHE SECONDO I SERVIZI">
<HEADERS>
<column num="1" colspan="1" rowspan="2">REGIONE</column>
<column num="2" colspan="1" rowspan="2">Informazioni bibliografiche</column>
<column num="3" colspan="1" rowspan="2">Riproduzioni</column>
<column num="4" colspan="2" rowspan="1">Prestito</column>
</HEADERS>
<HEADERS>
<column num="1" colspan="1" rowspan="1">Locale</column>
<column num="2" colspan="1" rowspan="1">Interbibliotecario</column>
</HEADERS>

<ROWS>

<xsl:variable name="unique-regione-num" select="//ROW/regione1[not(.=following::regione1)]" />
<xsl:for-each select="$unique-regione-num">

<xsl:variable name="reg" select="." />
<ROW>
	<xsl:attribute name="num">
				<xsl:value-of select="position()"/>
	</xsl:attribute>
<column num="1"><xsl:value-of select="$reg" /></column>
<column num="2">
<xsl:choose>
<xsl:when test="string-length(//ROW[regione = $reg]/informazioni_bibliografiche) > 0" >
<xsl:variable name="val" select="format-number( (100 * //ROW[regione = $reg]/informazioni_bibliografiche) div (//ROW[regione1 = $reg]/numero_biblioteche),'###.###.###,#','european')" />
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
<xsl:when test="string-length(//ROW[regione = $reg]/riproduzioni) > 0" >
<xsl:value-of  select="format-number((100 * //ROW[regione = $reg]/riproduzioni) div (//ROW[regione1 = $reg]/numero_biblioteche),'###.###.###,#','european')" />
</xsl:when>
<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</column>
<column num="4">
<xsl:choose>
<xsl:when test="string-length(//ROW[regione = $reg]/prestito_locale) > 0" >
<xsl:variable name="val"  select="format-number((100 * //ROW[regione = $reg]/prestito_locale) div (//ROW[regione1 = $reg]/numero_biblioteche),'###.###.###,#','european')" />
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
<column num="5">
<xsl:choose>
<xsl:when test="string-length(//ROW[regione = $reg]/prestito_interbibliotecario) > 0" >
<xsl:variable name="val"  select="format-number((100 * //ROW[regione = $reg]/prestito_interbibliotecario) div (//ROW[regione1 = $reg]/numero_biblioteche),'###.###.###,#','european')" />
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



