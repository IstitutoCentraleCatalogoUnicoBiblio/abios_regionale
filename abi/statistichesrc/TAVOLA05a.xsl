<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:decimal-format name="european" decimal-separator=',' grouping-separator='.' />
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/">
<xsl:variable name="unique-tip-num" select="//ROW/stato_accesso[not(.=following::stato_accesso)]" />

<xsl:variable name="max" select="count($unique-tip-num)" />
<table name="TAVOLA05a" title="Tavola 5a BIBLIOTECHE SECONDO LA MODALITA' D'ACCESSO E LA TIPOLOGIA FUNZIONALE">
<HEADERS>
<column num="1" colspan="1" rowspan="1">TIPOLOGIA FUNZIONALE</column>
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

<xsl:variable name="unique-descrizione-num" select="//ROW/descrizione[not(.=following::descrizione)]" />
<xsl:for-each select="$unique-descrizione-num">

<xsl:variable name="reg" select="." />
<ROW>
	<xsl:attribute name="num">
				<xsl:value-of select="position()"/>
	</xsl:attribute>
<column num="1"><xsl:value-of select="$reg" /></column>

<xsl:for-each select="$unique-tip-num">
<xsl:sort select="." order="ascending"/>
<xsl:variable name="tipo" select="." />
<xsl:variable  name="contatore" select="//ROW[descrizione = $reg and stato_accesso = $tipo]/count" />
<xsl:variable name="val" select="format-number(((100 * $contatore) div sum(//ROW[descrizione = $reg]/count)),'###.###.###,#','european')" />
<column>
	<xsl:attribute name="num">
				<xsl:value-of select="position()+1"/>
	</xsl:attribute>

<xsl:choose>
<xsl:when test="$contatore > 0">
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
<ROW style="bold">
	<xsl:attribute name="num">
				<xsl:value-of select="count($unique-descrizione-num)+1"/>
	</xsl:attribute>

<column num="1">Totale</column>
<xsl:for-each select="$unique-tip-num">
<xsl:variable name="tipo" select="." />
<xsl:variable name="valtot" select="format-number((100 * sum(//ROW[stato_accesso = $tipo]/count) div sum(//ROW/count)),'###.###.###,#','european')" />
<column>
	<xsl:attribute name="num">
				<xsl:value-of select="position()+1"/>
	</xsl:attribute>
<xsl:choose>
<xsl:when test="contains($valtot,',')>0 or $valtot = 100">
<xsl:value-of select="$valtot" />
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="concat($valtot,',0')" />
</xsl:otherwise>
</xsl:choose>
</column>
</xsl:for-each>
</ROW>
</ROWS>
</table>


							    </xsl:template>


							    </xsl:stylesheet>
















