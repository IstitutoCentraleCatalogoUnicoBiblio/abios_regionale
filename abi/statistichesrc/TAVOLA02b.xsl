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
<xsl:decimal-format name="european" decimal-separator="," grouping-separator="."/>
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/">
<xsl:variable name="unique-tip-num" select="//ROW/tipologia_funzionale[not(.=following::tipologia_funzionale)]"/>

<xsl:variable name="max" select="count($unique-tip-num)"/>
<table name="TAVOLA02b" title="Tavola 2b BIBLIOTECHE SECONDO LA TIPOLOGIA FUNZIONALE ED AMMINISTRATIVA" summary="Biblioteche_per_tip_funz_e_amm">
<HEADERS>
<column num="1" colspan="1" rowspan="2">TIPOLOGIA AMMINISTRATIVA</column>
<column num="2" rowspan="1">
	<xsl:attribute name="colspan">
				<xsl:value-of select="$max+1"/>
	</xsl:attribute>
TIPOLOGIA FUNZIONALE
</column>
</HEADERS>
<HEADERS>
<xsl:for-each select="$unique-tip-num">
<xsl:sort select="." order="ascending"/>
<column colspan="1" rowspan="1">
	<xsl:attribute name="num">
				<xsl:value-of select="position()+1"/>
	</xsl:attribute>
<xsl:value-of select="."/>
</column>
</xsl:for-each>
<column colspan="1" rowspan="1">
	<xsl:attribute name="num">
				<xsl:value-of select="$max+2"/>
	</xsl:attribute>
Totale</column>
</HEADERS>

<ROWS>
<xsl:variable name="unique-tipologia_amministrativa-num" select="//ROW/id_amm[not(.=following::id_amm)]"/>
<xsl:for-each select="$unique-tipologia_amministrativa-num">
<xsl:variable name="amm" select="."/>
<xsl:variable name="amm_title" select="//ROW[id_amm = $amm]/des_amm"/>
<xsl:variable name="amm_tot" select="count(//ROW[(floor(tipologia_amministrativa_id div 100) = ($amm div 100))])"/>
<xsl:if test="$amm_tot > 0">
<ROW>
	<xsl:attribute name="num">
				<xsl:value-of select="position()"/>
	</xsl:attribute>
<column num="1"><xsl:value-of select="$amm_title"/></column>
<xsl:for-each select="$unique-tip-num">
<xsl:sort select="." order="ascending"/>
<xsl:variable name="tipo" select="."/>
<xsl:variable name="contatore" select="sum(//ROW[(floor(tipologia_amministrativa_id div 100) = ($amm div 100)) and tipologia_funzionale = $tipo]/count)"/>

<column>
	<xsl:attribute name="num">
				<xsl:value-of select="position()+1"/>
	</xsl:attribute>

<xsl:choose>
<xsl:when test="$contatore &gt; 0">

<xsl:value-of select="format-number($contatore,'###.###.###,##','european')"/>

</xsl:when>
<xsl:otherwise/>
</xsl:choose>
</column>
</xsl:for-each>

<column>
	<xsl:attribute name="num">
				<xsl:value-of select="count($unique-tip-num)+2"/>
	</xsl:attribute>

<xsl:value-of select="format-number(sum(//ROW[(floor(tipologia_amministrativa_id div 100) = ($amm div 100))]/count),'###.###.###,##','european')"/>

</column>

</ROW>
</xsl:if>
</xsl:for-each>
<ROW style="strong">
	<xsl:attribute name="num">
				<xsl:value-of select="count($unique-tipologia_amministrativa-num)+1"/>
	</xsl:attribute>

<column num="1">Totale</column>
<xsl:for-each select="$unique-tip-num">
<xsl:sort select="." order="ascending"/>
<xsl:variable name="tipo" select="."/>
<column>
	<xsl:attribute name="num">
				<xsl:value-of select="position()+1"/>
	</xsl:attribute>
<xsl:value-of select="format-number(sum(//ROW[tipologia_funzionale = $tipo]/count),'###.###.###,##','european')"/>
</column>
</xsl:for-each>
<column>
	<xsl:attribute name="num">
				<xsl:value-of select="count($unique-tip-num)+2"/>
	</xsl:attribute>

<xsl:value-of select="format-number(sum(//ROW/count),'###.###.###,##','european')"/></column>
</ROW>
</ROWS>
</table>


							    </xsl:template>


							    </xsl:stylesheet>