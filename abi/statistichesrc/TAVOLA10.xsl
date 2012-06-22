<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:decimal-format name="european" decimal-separator=',' grouping-separator='.' />
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/">
<table name="TAVOLA10" title="Tavola 10 BIBLIOTECHE PER TIPOLOGIA AMMINISTRATIVA E CONSISTENZA DEL PATRIMONIO">
<HEADERS>
<column num="1" colspan="1" rowspan="2">TIPOLOGIA AMMINISTRATIVA</column>
<column num="2" colspan="9" rowspan="1">CONSISTENZA DEL PATRIMONIO (volumi ed opuscoli)</column>
</HEADERS>
<HEADERS>
<column num="1" colspan="1" rowspan="1">manca quantificazione</column>
<column num="2" colspan="1" rowspan="1">fino a 2.000</column>
<column num="3" colspan="1" rowspan="1">da 2.001 a 5.000</column>
<column num="4" colspan="1" rowspan="1">da 5.001 a 10.000</column>
<column num="5" colspan="1" rowspan="1">da 10.001 a 100.000</column>
<column num="6" colspan="1" rowspan="1">da 100.001 a 500.000</column>
<column num="7" colspan="1" rowspan="1">da 500.001 a 1.000.000</column>
<column num="8" colspan="1" rowspan="1">oltre 1.000.000</column>
<column num="9" colspan="1" rowspan="1">Totale</column>
</HEADERS>

<ROWS>

<xsl:variable name="unique-descrizione-num" select="//ROW/id_amm[not(.=following::id_amm)]"/>
<xsl:for-each select="$unique-descrizione-num">
<xsl:variable name="des" select="." />
<xsl:variable name="amm_title" select="//ROW[id_amm = $des]/des_amm"/>
<xsl:variable name="contatore_manca" select="sum(//ROW[(floor(descrizione div 100) = ($des div 100))]/count_manca)"/>
<xsl:variable name="contatore_fino" select="sum(//ROW[(floor(descrizione1 div 100) = ($des div 100))]/count_fino)"/>
<xsl:variable name="contatore_da_2001" select="sum(//ROW[(floor(descrizione2 div 100) = ($des div 100))]/count_da_2001)"/>
<xsl:variable name="contatore_da_5001" select="sum(//ROW[(floor(descrizione3 div 100) = ($des div 100))]/count_da_5001)"/>
<xsl:variable name="contatore_da_10001" select="sum(//ROW[(floor(descrizione4 div 100) = ($des div 100))]/count_da_10001)"/>
<xsl:variable name="contatore_da_100001" select="sum(//ROW[(floor(descrizione5 div 100) = ($des div 100))]/count_da_100001)"/>
<xsl:variable name="contatore_da_500001" select="sum(//ROW[(floor(descrizione6 div 100) = ($des div 100))]/count_da_500001)"/>
<xsl:variable name="contatore_oltre" select="sum(//ROW[(floor(descrizione7 div 100) = ($des div 100))]/count_oltre)"/>
<xsl:variable name="amm_tot" select="format-number($contatore_manca+$contatore_fino+$contatore_da_2001+$contatore_da_5001+$contatore_da_10001+$contatore_da_100001+$contatore_da_500001+$contatore_oltre,'###.###.###,#','european')"/>
<xsl:if test="$amm_tot > 0">
<ROW>
	<xsl:attribute name="num">
				<xsl:value-of select="position()"/>
	</xsl:attribute>

<column num="1"><xsl:value-of select="$amm_title" /></column>
<column num="2">
<xsl:choose>
<xsl:when test="$contatore_manca > 0">
<xsl:value-of select="format-number($contatore_manca,'###.###.###,#','european')" />
</xsl:when>
<xsl:otherwise>-</xsl:otherwise>
</xsl:choose>
</column>
<column num="3">
<xsl:choose>
<xsl:when test="$contatore_fino >  0">
<xsl:value-of select="format-number($contatore_fino,'###.###.###,#','european')" />
</xsl:when>
<xsl:otherwise>-</xsl:otherwise>
</xsl:choose>
</column>
<column num="4">
<xsl:choose>
<xsl:when test="$contatore_da_2001 > 0">
<xsl:value-of select="format-number($contatore_da_2001,'###.###.###,#','european')" />
</xsl:when>
<xsl:otherwise>-</xsl:otherwise>
</xsl:choose>
</column>
<column num="5">
<xsl:choose>
<xsl:when test="$contatore_da_5001 > 0">
<xsl:value-of select="format-number($contatore_da_5001,'###.###.###,#','european')" />
</xsl:when>
<xsl:otherwise>-</xsl:otherwise>
</xsl:choose>
</column>
<column num="6">
<xsl:choose>
<xsl:when test="$contatore_da_10001 > 0">
<xsl:value-of select="format-number($contatore_da_10001,'###.###.###,#','european')" />
</xsl:when>
<xsl:otherwise>-</xsl:otherwise>
</xsl:choose>
</column>
<column num="7">
<xsl:choose>
<xsl:when test="$contatore_da_100001 > 0">
<xsl:value-of select="format-number($contatore_da_100001,'###.###.###,#','european')" />
</xsl:when>
<xsl:otherwise>-</xsl:otherwise>
</xsl:choose>
</column>
<column num="8">
<xsl:choose>
<xsl:when test="$contatore_da_500001 > 0">
<xsl:value-of select="format-number($contatore_da_500001,'###.###.###,#','european')" />
</xsl:when>
<xsl:otherwise>-</xsl:otherwise>
</xsl:choose>
</column>
<column num="9">
<xsl:choose>
<xsl:when test="$contatore_oltre > 0">
<xsl:value-of select="format-number($contatore_oltre,'###.###.###,#','european')" />
</xsl:when>
<xsl:otherwise>-</xsl:otherwise>
</xsl:choose>
</column>
<column num="10">
<xsl:value-of  select="$amm_tot" />
</column>
</ROW>
</xsl:if>
</xsl:for-each>
<ROW>
<column num="1">Totale</column>
<column num="2"><xsl:value-of  select="format-number(sum(//ROW/count_manca),'###.###.###,#','european')" /></column>
<column num="3"><xsl:value-of  select="format-number(sum(//ROW/count_fino),'###.###.###,#','european')" /></column>
<column num="4"><xsl:value-of  select="format-number(sum(//ROW/count_da_2001),'###.###.###,#','european')" /></column>
<column num="5"><xsl:value-of  select="format-number(sum(//ROW/count_da_5001),'###.###.###,#','european')" /></column>
<column num="6"><xsl:value-of  select="format-number(sum(//ROW/count_da_10001),'###.###.###,#','european')" /></column>
<column num="7"><xsl:value-of  select="format-number(sum(//ROW/count_da_100001),'###.###.###,#','european')" /></column>
<column num="8"><xsl:value-of  select="format-number(sum(//ROW/count_da_500001),'###.###.###,#','european')" /></column>
<column num="9"><xsl:value-of  select="format-number(sum(//ROW/count_oltre),'###.###.###,#','european')" /></column>
<column num="10"><xsl:value-of  select="format-number(((sum(//ROW/count_oltre)) + (sum(//ROW/count_manca)) + (sum(//ROW/count_da_2001)) + (sum(//ROW/count_da_5001)) + (sum(//ROW/count_da_10001)) + (sum(//ROW/count_da_100001)) + (sum(//ROW/count_da_500001)) + (sum(//ROW/count_fino))) ,'###.###.###,#','european')" /></column>
</ROW>
</ROWS>
</table>


							    </xsl:template>


							    </xsl:stylesheet>



