<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:decimal-format name="european" decimal-separator=',' grouping-separator='.' />
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/">
<xsl:variable name="unique-regione-num" select="//ROW/regione[not(.=following::regione)]" />

<xsl:variable name="max" select="count($unique-regione-num)" />
<table name="TAVOLA01" title="Tavola 1 BIBLIOTECHE REGISTRATE IN ANAGRAFE">
<HEADERS>
<column num="1" colspan="1" rowspan="1">REGIONE</column>
<column num="2" colspan="1" rowspan="1">(A) Biblioteche presenti in anagrafe</column>
<column num="3" colspan="1" rowspan="1">(B) N. di biblioteche per 100.000 abitanti</column>
</HEADERS>
<ROWS>
<xsl:for-each select="$unique-regione-num">
<xsl:variable name="reg" select="." />
<ROW>
	<xsl:attribute name="num">
				<xsl:value-of select="position()"/>
	</xsl:attribute>
<column num="1"><xsl:value-of select="$reg" /></column>
<column num="2"><xsl:value-of select="format-number(//ROW[regione = $reg]/count,'###.###.###,##','european')" /></column>
<column num="3"><xsl:value-of select="format-number(round((100000 * //ROW[regione = $reg]/count) div //ROW[regione = $reg]/nabitanti),'###.###.###,##','european')" /></column>
</ROW>
</xsl:for-each>

</ROWS>
</table>


							    </xsl:template>


							    </xsl:stylesheet>



