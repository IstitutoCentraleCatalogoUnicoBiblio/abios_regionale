<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:decimal-format name="european" decimal-separator=',' grouping-separator='.' />
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/">
<xsl:variable name="unique-descrizione-num" select="//ROW/provincia[not(.=following::provincia)]" />

<xsl:variable name="max" select="count($unique-descrizione-num)" />
<table name="TAVOLA01c" title="Tavola 1c BIBLIOTECHE CENSITE DIVISE PER PROVINCIA">
<HEADERS>
<column num="1" colspan="1" rowspan="1">PROVINCE</column>
<column num="2" colspan="1" rowspan="1">(A) Biblioteche censite</column>
<column num="3" colspan="1" rowspan="1">(B) N. di biblioteche per 100.000 abitanti</column>
</HEADERS>

<ROWS>

<xsl:for-each select="$unique-descrizione-num">
<xsl:variable name="position"><xsl:value-of select="position()"/></xsl:variable>
<xsl:variable name="prov" select="." />
<xsl:variable name="reg" select="//ROW[position()=$position]/regione" />


<ROW>
	<xsl:attribute name="num">
				<xsl:value-of select="position()"/>
	</xsl:attribute>
<column num="1"><xsl:value-of select="$prov" /></column>
<column num="2"><xsl:value-of select="format-number(//ROW[provincia = $prov]/count,'###.###.###,##','european')" /></column>
<column num="3"><xsl:value-of select="format-number(round((100000 * //ROW[provincia = $prov]/count) div //ROW[provincia = $prov]/nabitanti),'###.###.###,##','european')" /></column>
</ROW>
</xsl:for-each>

<ROW style="bold">
	<xsl:attribute name="num">
				<xsl:value-of select="$max+1"/>
	</xsl:attribute>
<column num="1">TOTALE</column>
<column num="2"><xsl:value-of select="format-number(sum(//ROW/count),'###.###.###,##','european')" /></column>
<column num="3"><xsl:value-of select="format-number(round((100000 * sum(//ROW/count)) div sum(//ROW/nabitanti)),'###.###.###,##','european')" /></column>
</ROW>
</ROWS>
</table>


							    </xsl:template>


							    </xsl:stylesheet>