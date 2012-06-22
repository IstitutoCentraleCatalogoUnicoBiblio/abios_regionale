<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:decimal-format name="european" decimal-separator=',' grouping-separator='.' />
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/">
<table name="TAVOLA08" title="Tavola 8 BIBLIOTECHE PER CONSISTENZA DEL PATRIMONIO">
<HEADERS>
<column num="1" colspan="1" rowspan="2">REGIONE</column>
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

<xsl:variable name="unique-regione-num" select="//ROW/regione1[not(.=following::regione1)]" />
<xsl:for-each select="$unique-regione-num">

<xsl:variable name="reg" select="." />
<ROW>
	<xsl:attribute name="num">
				<xsl:value-of select="position()"/>
	</xsl:attribute>
<column num="1"><xsl:value-of select="$reg" /></column>

<xsl:variable name="count_manca">
<xsl:choose>
<xsl:when test="string-length(//ROW[regione = $reg]/count_manca) > 0" >
<xsl:value-of select="//ROW[regione = $reg]/count_manca" />
</xsl:when>
<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</xsl:variable>

<xsl:variable name="count_fino">
<xsl:choose>
<xsl:when test="string-length(//ROW[regione1 = $reg]/count_fino) > 0" >
<xsl:value-of  select="//ROW[regione1 = $reg]/count_fino" />
</xsl:when>
<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</xsl:variable>

<xsl:variable name="count_da_2001" >
<xsl:choose>
<xsl:when test="string-length(//ROW[regione2 = $reg]/count_da_2001) > 0">
<xsl:value-of  select="//ROW[regione2 = $reg]/count_da_2001" />
</xsl:when>
<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</xsl:variable>

<xsl:variable name="count_da_5001">
<xsl:choose>
<xsl:when test="string-length(//ROW[regione3 = $reg]/count_da_5001) > 0">
<xsl:value-of select="//ROW[regione3 = $reg]/count_da_5001" />
</xsl:when>
<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</xsl:variable>

<xsl:variable name="count_da_10001">
<xsl:choose>
<xsl:when test="string-length(//ROW[regione4 = $reg]/count_da_10001) > 0">
<xsl:value-of  select="//ROW[regione4 = $reg]/count_da_10001" />
</xsl:when>
<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</xsl:variable>

<xsl:variable name="count_da_100001">
<xsl:choose>
<xsl:when test="string-length(//ROW[regione5 = $reg]/count_da_100001) > 0">
<xsl:value-of  select="//ROW[regione5 = $reg]/count_da_100001" />
</xsl:when>
<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</xsl:variable>

<xsl:variable name="count_da_500001">
<xsl:choose>
<xsl:when test="string-length(//ROW[regione6 = $reg]/count_da_500001) > 0">
<xsl:value-of  select="//ROW[regione6 = $reg]/count_da_500001" />
</xsl:when>
<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</xsl:variable>

<xsl:variable name="count_oltre">
<xsl:choose>
<xsl:when test="string-length(//ROW[regione7 = $reg]/count_oltre) > 0">
<xsl:value-of select="//ROW[regione7 = $reg]/count_oltre" />
</xsl:when>
<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</xsl:variable>


<column num="2">
<xsl:choose>
<xsl:when test="$count_manca > 0"><xsl:value-of select="format-number($count_manca,'###.###.###,#','european')" />
</xsl:when>
<xsl:otherwise>
-
</xsl:otherwise>
</xsl:choose>
</column>
<column num="3">
<xsl:choose>
<xsl:when test="$count_fino >  0"><xsl:value-of  select="format-number($count_fino,'###.###.###,#','european')" />
</xsl:when>
<xsl:otherwise>
-
</xsl:otherwise>
</xsl:choose>
</column>
<column num="4">
<xsl:choose>
<xsl:when test="$count_da_2001 > 0"><xsl:value-of select="format-number($count_da_2001,'###.###.###,#','european')" />
</xsl:when>
<xsl:otherwise>
-
</xsl:otherwise>
</xsl:choose>
</column>
<column num="5">
<xsl:choose>
<xsl:when test="$count_da_5001 > 0"><xsl:value-of select="format-number($count_da_5001,'###.###.###,#','european')" />
</xsl:when>
<xsl:otherwise>
-
</xsl:otherwise>
</xsl:choose>
</column>
<column num="6">
<xsl:choose>
<xsl:when test="$count_da_10001 > 0"><xsl:value-of select="format-number($count_da_10001,'###.###.###,#','european')" />
</xsl:when>
<xsl:otherwise>
-
</xsl:otherwise>
</xsl:choose>
</column>
<column num="7">
<xsl:choose>
<xsl:when test="$count_da_100001 > 0"><xsl:value-of select="format-number($count_da_100001,'###.###.###,#','european')" />
</xsl:when>
<xsl:otherwise>
-
</xsl:otherwise>
</xsl:choose>
</column>
<column num="8">
<xsl:choose>
<xsl:when test="$count_da_500001 > 0"><xsl:value-of select="format-number($count_da_500001,'###.###.###,#','european')" />
</xsl:when>
<xsl:otherwise>
-
</xsl:otherwise>
</xsl:choose>
</column>
<column num="9">
<xsl:choose>
<xsl:when test="$count_oltre > 0"><xsl:value-of  select="format-number($count_oltre,'###.###.###,#','european')" />
</xsl:when>
<xsl:otherwise>
-
</xsl:otherwise>
</xsl:choose>
</column>
<column num="10">
<xsl:value-of  select="format-number($count_manca+$count_fino+$count_da_2001+$count_da_5001+$count_da_10001+$count_da_100001+$count_da_500001+$count_oltre,'###.###.###,#','european')" />
</column>
</ROW>
</xsl:for-each>

</ROWS>
</table>


							    </xsl:template>


							    </xsl:stylesheet>



