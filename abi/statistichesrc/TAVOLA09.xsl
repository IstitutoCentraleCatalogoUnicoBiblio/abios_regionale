<?xml version="1.0"?>

<!--
  ~ Author Inera srl https://www.inera.it
  ~ Copyright (C) 2023  Inera srl https://www.inera.it/
  ~
  ~ European Union Public Licence V. 1.2
  ~ EUPL (c) the European Community 2017
  ~
  ~ This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
  ~ Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
  ~ The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
  ~ Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
  ~
  ~ You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
  -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:decimal-format name="european" decimal-separator=',' grouping-separator='.' />
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/">
<table name="TAVOLA09" title="Tavola 9 BIBLIOTECHE PER TIPOLOGIA FUNZIONALE E CONSISTENZA DEL PATRIMONIO" summary="Biblioteche_per_tip_funz_e_consistenza_patr">
<HEADERS>
<column num="1" colspan="1" rowspan="2">TIPOLOGIA FUNZIONALE</column>
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

<xsl:variable name="unique-descrizione-num" select="//ROW/descrizione[not(.=following::descrizione)]" />
<xsl:for-each select="$unique-descrizione-num">

<xsl:variable name="des" select="." />
<ROW>
	<xsl:attribute name="num">
				<xsl:value-of select="position()"/>
	</xsl:attribute>
<column num="1"><xsl:value-of select="$des" /></column>
<xsl:variable name="count_manca">
<xsl:choose>
<xsl:when test="string-length(//ROW[descrizione0 = $des]/count_manca) > 0" >
<xsl:value-of select="//ROW[descrizione0 = $des]/count_manca" />
</xsl:when>
<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</xsl:variable>

<xsl:variable name="count_fino">
<xsl:choose>
<xsl:when test="string-length(//ROW[descrizione1 = $des]/count_fino) > 0" >
<xsl:value-of  select="//ROW[descrizione1 = $des]/count_fino" />
</xsl:when>
<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</xsl:variable>

<xsl:variable name="count_da_2001" >
<xsl:choose>
<xsl:when test="string-length(//ROW[descrizione2 = $des]/count_da_2001) > 0">
<xsl:value-of  select="//ROW[descrizione2 = $des]/count_da_2001" />
</xsl:when>
<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</xsl:variable>

<xsl:variable name="count_da_5001">
<xsl:choose>
<xsl:when test="string-length(//ROW[descrizione3 = $des]/count_da_5001) > 0">
<xsl:value-of select="//ROW[descrizione3 = $des]/count_da_5001" />
</xsl:when>
<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</xsl:variable>

<xsl:variable name="count_da_10001">
<xsl:choose>
<xsl:when test="string-length(//ROW[descrizione4 = $des]/count_da_10001) > 0">
<xsl:value-of  select="//ROW[descrizione4 = $des]/count_da_10001" />
</xsl:when>
<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</xsl:variable>

<xsl:variable name="count_da_100001">
<xsl:choose>
<xsl:when test="string-length(//ROW[descrizione5 = $des]/count_da_100001) > 0">
<xsl:value-of  select="//ROW[descrizione5 = $des]/count_da_100001" />
</xsl:when>
<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</xsl:variable>

<xsl:variable name="count_da_500001">
<xsl:choose>
<xsl:when test="string-length(//ROW[descrizione6 = $des]/count_da_500001) > 0">
<xsl:value-of  select="//ROW[descrizione6 = $des]/count_da_500001" />
</xsl:when>
<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</xsl:variable>

<xsl:variable name="count_oltre">
<xsl:choose>
<xsl:when test="string-length(//ROW[descrizione7 = $des]/count_oltre) > 0">
<xsl:value-of select="//ROW[descrizione7 = $des]/count_oltre" />
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
<column num="10"><xsl:value-of  select="format-number(sum(//ROW/count_manca)+sum(//ROW/count_fino)+sum(//ROW/count_da_2001)+sum(//ROW/count_da_5001)+sum(//ROW/count_da_10001)+sum(//ROW/count_da_100001)+sum(//ROW/count_da_500001)+sum(//ROW/count_oltre),'###.###.###,#','european')" /></column>
</ROW>
</ROWS>
</table>


							    </xsl:template>


							    </xsl:stylesheet>



