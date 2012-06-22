<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:decimal-format name="european" decimal-separator=',' grouping-separator='.' />
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/">
<xsl:variable name="unique-descrizione-num" select="//ROW/descrizione[not(.=following::descrizione)]" />

<xsl:variable name="max" select="count($unique-descrizione-num)" />
<table name="TAVOLA07" title="Tavola 7 CONSISTENZA DEL PATRIMONIO">

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



