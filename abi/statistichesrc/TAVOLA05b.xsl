<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:decimal-format name="european" decimal-separator=',' grouping-separator='.' />
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/">

<table name="TAVOLA05b" title="Tavola 5b BIBLIOTECHE PER NUMERO DI ORE DI APERTURA SETTIMANALE" summary="Biblioteche_per_ore_apertura_sett">
<HEADERS>
<column num="1" colspan="1" rowspan="2">TIPOLOGIA FUNZIONALE</column>
<column num="2" colspan="7" rowspan="1">Fasce orarie</column>
</HEADERS>
<HEADERS>
<column num="1" colspan="1" rowspan="1">Inf. non disponibile</column>
<column num="2" colspan="1" rowspan="1">meno di 6 ore</column>
<column num="3" colspan="1" rowspan="1">da 6 a 12 ore</column>
<column num="4" colspan="1" rowspan="1">da 12 a 18 ore</column>
<column num="5" colspan="1" rowspan="1">da 18 a 40</column>
<column num="6" colspan="1" rowspan="1">da 40 ore e oltre</column>
</HEADERS>


<ROWS>

<xsl:variable name="unique-descrizione-num" select="//ROW/tipo[not(.=following::tipo)]" />

<xsl:for-each select="$unique-descrizione-num">
<xsl:sort select="." order="ascending"/>

<xsl:variable name="des" select="." />
  <xsl:variable name="totBib">
      <xsl:value-of select="//ROW[tipo1 = $des]/tot" />
  </xsl:variable>

<ROW>
<column num="1"><xsl:value-of select="$des" /></column>
<column num="2">
<xsl:variable name="val2">
	<xsl:choose>
		<xsl:when test="$totBib">
 			<xsl:value-of select="format-number(((100*($totBib - count(//ROW[descrizione = $des])))div $totBib),'###.###.###,#','european')" />
		</xsl:when>
		<xsl:otherwise>0</xsl:otherwise>
 	</xsl:choose>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains($val2,',')>0 or $val2 = 100">
<xsl:value-of select="$val2" />
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="concat($val2,',0')" />
</xsl:otherwise>
</xsl:choose>
</column>
<column num="3">
<xsl:variable name="val3">
<xsl:choose>
	<xsl:when test="$totBib">
		<xsl:value-of select="format-number(((100*(count(//ROW[descrizione = $des and sum &lt; 6*60]))) div $totBib),'###.###.###,#','european')" />
	</xsl:when>
	<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains($val3,',')>0 or $val3 = 100">
<xsl:value-of select="$val3" />
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="concat($val3,',0')" />
</xsl:otherwise>
</xsl:choose>
</column>
<column num="4">
<xsl:variable name="val4">
 <xsl:choose>
	<xsl:when test="$totBib">
		<xsl:value-of select="format-number(((100*(count(//ROW[descrizione = $des and sum &gt; (6*60)-1 and sum &lt; 12*60]))) div $totBib),'###.###.###,#','european')" />
	</xsl:when>
	<xsl:otherwise>0</xsl:otherwise>
 </xsl:choose>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains($val4,',')>0 or $val4 = 100">
<xsl:value-of select="$val4" />
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="concat($val4,',0')" />
</xsl:otherwise>
</xsl:choose>
</column>
<column num="5">
<xsl:variable name="val5">
<xsl:choose>
	<xsl:when test="$totBib">
		<xsl:value-of select="format-number(((100*(count(//ROW[descrizione = $des and sum &gt; (12*60)-1 and sum &lt; 18*60]))) div $totBib),'###.###.###,#','european')" />
	</xsl:when>
	<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains($val5,',')>0 or $val5 = 100">
<xsl:value-of select="$val5" />
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="concat($val5,',0')" />
</xsl:otherwise>
</xsl:choose>
</column>
<column num="6">
<xsl:variable name="val6">
<xsl:choose>
	<xsl:when test="$totBib">
		<xsl:value-of select="format-number(((100*(count(//ROW[descrizione = $des and sum &gt; (18*60)-1 and sum &lt; 40*60]))) div $totBib),'###.###.###,#','european')" />
	</xsl:when>
	<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains($val6,',')>0 or $val6 = 100">
<xsl:value-of select="$val6" />
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="concat($val6,',0')" />
</xsl:otherwise>
</xsl:choose>
</column>
<column num="7">
<xsl:variable name="val7">
<xsl:choose>
	<xsl:when test="$totBib">
		<xsl:value-of select="format-number(((100*(count(//ROW[descrizione = $des and sum &gt; (40*60)-1]))) div $totBib),'###.###.###,#','european')" />
	</xsl:when>
	<xsl:otherwise>0</xsl:otherwise>
</xsl:choose>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains($val7,',')>0 or $val7 = 100">
<xsl:value-of select="$val7" />
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="concat($val7,',0')" />
</xsl:otherwise>
</xsl:choose>
</column>
</ROW>
</xsl:for-each>
<xsl:variable name="totBib">
      <xsl:value-of select="sum(//ROW/tot)" />
  </xsl:variable>
<ROW>
<column num="1">Totale</column>
<column num="2">
<xsl:variable name="valtot" select="format-number(((100*($totBib - count(//ROW[descrizione!='']))) div $totBib) ,'###.###.###,#','european')" />
<xsl:choose>
<xsl:when test="contains($valtot,',')>0 or $valtot = 100">
<xsl:value-of select="$valtot" />
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="concat($valtot,',0')" />
</xsl:otherwise>
</xsl:choose>
</column>
<column num="3"><xsl:variable name="valtot" select="format-number(((100*(count(//ROW[sum &lt; 6*60]))) div $totBib),'###.###.###,#','european')" />
<xsl:choose>
<xsl:when test="contains($valtot,',')>0 or $valtot = 100">
<xsl:value-of select="$valtot" />
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="concat($valtot,',0')" />
</xsl:otherwise>
</xsl:choose>
</column>
<column num="4"><xsl:variable name="valtot" select="format-number(((100*(count(//ROW[sum &gt; (6*60)-1 and sum &lt; 12*60]))) div $totBib),'###.###.###,#','european')" />
<xsl:choose><xsl:when test="contains($valtot,',')>0 or $valtot = 100">
<xsl:value-of select="$valtot" />
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="concat($valtot,',0')" />
</xsl:otherwise>
</xsl:choose>
</column>
<column num="5"><xsl:variable name="valtot" select="format-number(((100*(count(//ROW[sum &gt; (12*60)-1 and sum &lt; 18*60]))) div $totBib),'###.###.###,#','european')" />
<xsl:choose><xsl:when test="contains($valtot,',')>0 or $valtot = 100">
<xsl:value-of select="$valtot" />
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="concat($valtot,',0')" />
</xsl:otherwise>
</xsl:choose>
</column>
<column num="6"><xsl:variable name="valtot" select="format-number(((100*(count(//ROW[sum &gt; (18*60)-1 and sum &lt; 40*60]))) div $totBib),'###.###.###,#','european')" />
<xsl:choose><xsl:when test="contains($valtot,',')>0 or $valtot = 100">
<xsl:value-of select="$valtot" />
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="concat($valtot,',0')" />
</xsl:otherwise>
</xsl:choose>
</column>
<column num="7">
<xsl:variable name="valtot" select="format-number(((100*(count(//ROW[sum &gt; (40*60)-1]))) div $totBib),'###.###.###,#','european')" />
<xsl:choose><xsl:when test="contains($valtot,',')>0 or $valtot = 100">
<xsl:value-of select="$valtot" />
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="concat($valtot,',0')" />
</xsl:otherwise>
</xsl:choose>
</column>
</ROW>
</ROWS>
</table>
</xsl:template>
</xsl:stylesheet>
