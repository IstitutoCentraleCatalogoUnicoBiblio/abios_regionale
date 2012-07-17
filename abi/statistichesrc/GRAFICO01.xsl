<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:decimal-format name="european" decimal-separator=',' grouping-separator='.' />
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/">
<xsl:variable name="unique-descrizione-num" select="//ROW/id_amm[not(.=following::id_amm)]"/>
<xsl:variable name="max" select="count($unique-descrizione-num)" />
<table name="GRAFICO01" title="Grafico 1 BIBLIOTECHE SECONDO LA TIPOLOGIA AMMINISTRATIVA" summary="Grafico_Biblioteche_per_tip_amm">
<ROWS>
<xsl:for-each select="$unique-descrizione-num">
<xsl:variable name="des" select="." />
<ROW>
	<xsl:attribute name="num">
				<xsl:value-of select="position()"/>
	</xsl:attribute>
<column num="1"><xsl:value-of select="//ROW[id_amm = $des]/des_amm" /></column>
<column num="2"><xsl:value-of select="sum(//ROW[(floor(tipologia_amministrativa_id div 100) = ($des div 100))]/count)"/></column>
</ROW>
<xsl:if test="$des=600">
<ROW>
	<xsl:attribute name="num">
				<xsl:value-of select="603"/>
	</xsl:attribute>
<column num="1"> - di cui Comuni</column>
<column num="2"><xsl:value-of select="//ROW[tipologia_amministrativa_id = 603]/count"/></column>
</ROW>
</xsl:if>
</xsl:for-each>
</ROWS>
</table>


							    </xsl:template>


							    </xsl:stylesheet>



