<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:decimal-format name="european" decimal-separator=',' grouping-separator='.' />
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/">
<table name="TAVOLA04" title="Tavola 4 BIBLIOTECHE FONDATE NEL XX e XXI SECOLO" summary="Biblioteche_per_anno_di_fond">
<ROWS>

<ROW num="1">
<column num="1">anno non disponibile</column>
<column num="2"><xsl:value-of select="format-number(sum(//ROW[fondazione = '20' or fondazione = '21' or contains(fondazione, ' - ')]/count),'###.###.###,##','european')" /></column>
</ROW>
<ROW num="2">
<column num="1">fino al 1948</column>
<column num="2"><xsl:value-of select="format-number(sum(//ROW[string-length(fondazione) = 4 and fondazione &lt; 1948]/count),'###.###.###,##','european')" /></column>
</ROW>
<ROW num="3">
<column num="1">dal 1948 al 1972</column>
<column num="2"><xsl:value-of select="format-number(sum(//ROW[fondazione &gt; 1947 and fondazione &lt; 1972]/count),'###.###.###,##','european')" /></column>
</ROW>
<ROW num="4">
<column num="1">dal 1972</column>
<column num="2"><xsl:value-of select="format-number(sum(//ROW[fondazione &gt; 1971]/count),'###.###.###,##','european')" /></column>
</ROW>
<ROW num="5">
<column num="1">Totale</column>
<column num="2"><xsl:value-of select="format-number(sum(//ROW[fondazione != '19']/count),'###.###.###,##','european')" /></column>
</ROW>
</ROWS>
</table>


							    </xsl:template>


							    </xsl:stylesheet>



