<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:decimal-format name="european" decimal-separator=',' grouping-separator='.' />
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/">
<xsl:variable name="unique-tip-num" select="//ROW/stato_accesso[not(.=following::stato_accesso)]" />

<xsl:variable name="max" select="count($unique-tip-num)" />
<table name="GRAFICO02" title="Grafico 2 BIBLIOTECHE CENSITE SECONDO L'ACCESSO">
<HEADERS>
<column num="1" colspan="1" rowspan="1">REGIONE</column>
<xsl:for-each select="$unique-tip-num">
<xsl:sort select="." order="ascending"/>
<column colspan="1" rowspan="1">
	<xsl:attribute name="num">
				<xsl:value-of select="position()+1"/>
	</xsl:attribute>
<xsl:choose>
<xsl:when test=". = 'true'">
riservata
</xsl:when>
<xsl:when test=". = 'false'">
aperta a tutti
</xsl:when>
<xsl:when test=". = 'null'">
Inf. non disponibile
</xsl:when>
<xsl:otherwise>
chiusa
</xsl:otherwise>
</xsl:choose>
</column>
</xsl:for-each>
</HEADERS>

<ROWS>

<xsl:variable name="unique-regione-num" select="//ROW/descrizione_zona[not(.=following::descrizione_zona)]" />
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
<xsl:variable  name="contatore" select="//ROW[descrizione_zona = $reg and stato_accesso = $tipo]/count" />

<column>
	<xsl:attribute name="num">
				<xsl:value-of select="position()+1"/>
	</xsl:attribute>

<xsl:choose>
<xsl:when test="$contatore > 0">

<xsl:variable name="val" select="format-number(((100 * $contatore) div sum(//ROW[descrizione_zona = $reg]/count)),'###.###.###,#','european')" />
<xsl:choose>
<xsl:when test="contains($val,',') or $val = 100">
<xsl:value-of select="$val" />
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="concat($val,',0')" />
</xsl:otherwise>
</xsl:choose>
</xsl:when>
<xsl:otherwise>
-
</xsl:otherwise>
</xsl:choose>
</column>
</xsl:for-each>
</ROW>
</xsl:for-each>
</ROWS>
</table>


							    </xsl:template>


							    </xsl:stylesheet>