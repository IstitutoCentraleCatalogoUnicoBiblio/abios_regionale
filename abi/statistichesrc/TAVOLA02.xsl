<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:decimal-format name="european" decimal-separator=',' grouping-separator='.' />
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/">
<xsl:variable name="unique-tip-num" select="//ROW/descrizione[not(.=following::descrizione)]" />

<xsl:variable name="max" select="count($unique-tip-num)" />
<table name="TAVOLA02" title="Tavola 2 BIBLIOTECHE SECONDO LA TIPOLOGIA FUNZIONALE">
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



