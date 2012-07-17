<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:decimal-format name="european" decimal-separator=',' grouping-separator='.' />
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/">
<table name="TAVOLA03" title="Tavola 3 BIBLIOTECHE SECONDO IL SECOLO DI FONDAZIONE" summary="Biblioteche_per_secolo_di_fond">
<ROWS>

<ROW num="1">
<column num="1">Non disponibile</column>
<column num="2"><xsl:value-of select="sum(//ROW[fondazione = 'null']/count)" /></column>
</ROW>
<ROW num="2">
<column num="1">Prima dell'XI sec.</column>
<column num="2"><xsl:value-of select="sum(//ROW[(fondazione = '10') or  (string-length(fondazione) = 1 ) or string-length(fondazione) = 3 or (contains(fondazione,' - 10'))  or (contains(fondazione,' - 9')) or (contains(fondazione,' - 8'))]/count)" /></column>
</ROW>
<ROW num="3">
<column num="1">XI sec.</column>
<column num="2"><xsl:value-of select="sum(//ROW[fondazione = '11' or (contains(fondazione,' - 11'))  or (string-length(fondazione) =  4 and (fondazione &gt; 999 and fondazione &lt; 1100))]/count)" /></column>
</ROW>
<ROW num="4">
<column num="1">XII sec.</column>
<column num="2"><xsl:value-of select="sum(//ROW[fondazione = '12' or (contains(fondazione,' - 12'))  or (string-length(fondazione) =  4 and (fondazione &gt; 1099 and fondazione &lt; 1200))]/count)" /></column>
</ROW>
<ROW num="5">
<column num="1">XIII sec.</column>
<column num="2"><xsl:value-of select="sum(//ROW[fondazione = '13' or (contains(fondazione,' - 13'))  or (string-length(fondazione) =  4 and (fondazione &gt; 1199 and fondazione &lt; 1300))]/count)" /></column>
</ROW>
<ROW num="6">
<column num="1">XIV sec.</column>
<column num="2"><xsl:value-of select="sum(//ROW[fondazione = '14' or (contains(fondazione,' - 14'))  or (string-length(fondazione) =  4 and (fondazione &gt; 1299 and fondazione &lt; 1400))]/count)" /></column>
</ROW>
<ROW num="7">
<column num="1">XV sec.</column>
<column num="2"><xsl:value-of select="sum(//ROW[fondazione = '15' or (contains(fondazione,' - 15'))  or (string-length(fondazione) =  4 and (fondazione &gt; 1399 and fondazione &lt; 1500))]/count)" /></column>
</ROW>
<ROW num="8">
<column num="1">XVI sec.</column>
<column num="2"><xsl:value-of select="sum(//ROW[fondazione = '16' or (contains(fondazione,' - 16'))  or (string-length(fondazione) =  4 and (fondazione &gt; 1499 and fondazione &lt; 1600))]/count)" /></column>
</ROW>
<ROW num="9">
<column num="1">XVII sec.</column>
<column num="2"><xsl:value-of select="sum(//ROW[fondazione = '17' or (contains(fondazione,' - 17'))  or (string-length(fondazione) =  4 and (fondazione &gt; 1599 and fondazione &lt; 1700))]/count)" /></column>
</ROW>
<ROW num="10">
<column num="1">XVIII sec.</column>
<column num="2"><xsl:value-of select="sum(//ROW[fondazione = '18' or (contains(fondazione,' - 18'))  or (string-length(fondazione) =  4 and (fondazione &gt; 1699 and fondazione &lt; 1800))]/count)" /></column>
</ROW>
<ROW num="11">
<column num="1">XIX sec.</column>
<column num="2"><xsl:value-of select="sum(//ROW[fondazione = '19' or (contains(fondazione,' - 19'))  or (string-length(fondazione) =  4 and (fondazione &gt; 1799 and fondazione &lt; 1900))]/count)" /></column>
</ROW>
<ROW num="12">
<column num="1">XX sec.</column>
<column num="2"><xsl:value-of select="sum(//ROW[fondazione = '20' or (contains(fondazione,' - 20'))  or (string-length(fondazione) =  4 and (fondazione &gt; 1899 and fondazione &lt; 2000))]/count)" /></column>
</ROW>
<ROW num="13">
<column num="1">XXI sec.</column>
<column num="2"><xsl:value-of select="sum(//ROW[fondazione = '21' or (contains(fondazione,' - 21'))  or (string-length(fondazione) =  4 and (fondazione &gt; 1999 and fondazione &lt; 2100))]/count)" /></column>
</ROW>
<ROW num="14">
<column num="1">Totale</column>
<column num="2"><xsl:value-of select="sum(//ROW/count)" /></column>
</ROW>
</ROWS>
</table>


							    </xsl:template>


							    </xsl:stylesheet>



