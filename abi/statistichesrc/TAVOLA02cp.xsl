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
<xsl:variable name="unique-tip-num" select="//ROW/id_amm[not(.=following::id_amm)]"/>
<xsl:variable name="max" select="count($unique-tip-num)" />
<table name="TAVOLA02cp" title="Tavola 2cp BIBLIOTECHE SECONDO LA REGIONE E LA TIPOLOGIA AMMINISTRATIVA" summary="Biblioteche_autonome_per_tip_amm">
<HEADERS>
<column num="1" colspan="1" rowspan="1">PROVINCE</column>
<xsl:for-each select="$unique-tip-num">
<xsl:sort select="." order="ascending" data-type="number"/>
<xsl:variable name="tipo" select="." />
<xsl:variable name="amm_tot" select="count(//ROW[(floor(tipologia_amministrativa_id div 100) = ($tipo div 100))])"/>
<xsl:if test="$amm_tot > 0">
<column colspan="1" rowspan="1">
	<xsl:attribute name="num">
				<xsl:value-of select="position()+1"/>
	</xsl:attribute>
<xsl:value-of select="//ROW[id_amm = $tipo]/des_amm" />
</column>
</xsl:if>
</xsl:for-each>
<column colspan="1" rowspan="1">
	<xsl:attribute name="num">
				<xsl:value-of select="$max+2"/>
	</xsl:attribute>
Totale</column>
</HEADERS>

<ROWS>
<xsl:variable name="unique-regione-num" select="//ROW/regione[not(.=following::regione)]" />
<xsl:for-each select="$unique-regione-num">
<xsl:variable name="reg" select="." />
<ROW>
	<xsl:attribute name="num">
				<xsl:value-of select="position()"/>
	</xsl:attribute>
<column num="1"><xsl:value-of select="$reg" /></column>
<xsl:for-each select="$unique-tip-num">
<xsl:sort select="." order="ascending" data-type="number"/>
<xsl:variable name="tipo" select="." />
<xsl:variable name="amm_tot" select="count(//ROW[(floor(tipologia_amministrativa_id div 100) = ($tipo div 100))])"/>
<xsl:variable  name="contatore" select="sum(//ROW[regione = $reg and (floor(tipologia_amministrativa_id div 100) = ($tipo div 100))]/count)" />
<xsl:if test="$amm_tot>0">
<column>
	<xsl:attribute name="num">
				<xsl:value-of select="position()+1"/>
	</xsl:attribute>

<xsl:choose>
<xsl:when test="$contatore > 0">

<xsl:value-of select="format-number($contatore,'###.###.###,##','european')" />

</xsl:when>
<xsl:otherwise>
-
</xsl:otherwise>
</xsl:choose>
</column>
</xsl:if>
</xsl:for-each>

<column>
	<xsl:attribute name="num">
				<xsl:value-of select="count($unique-tip-num)+2"/>
	</xsl:attribute>

<xsl:value-of  select="format-number(sum(//ROW[regione = $reg]/count),'###.###.###,##','european')" />

</column>

</ROW>
</xsl:for-each>
<ROW style="strong">
	<xsl:attribute name="num">
				<xsl:value-of select="count($unique-regione-num)+1"/>
	</xsl:attribute>

<column num="1">TRENTINO ALTO ADIGE</column>
<xsl:for-each select="$unique-tip-num">
<xsl:sort select="." order="ascending" data-type="number"/>
<xsl:variable name="tipo" select="." />
<xsl:variable name="amm_tot" select="count(//ROW[(floor(tipologia_amministrativa_id div 100) = ($tipo div 100))])"/>
<xsl:if test="$amm_tot>0">
<column>
	<xsl:attribute name="num">
				<xsl:value-of select="position()+1"/>
	</xsl:attribute>
<xsl:value-of select="format-number(sum(//ROW[(floor(tipologia_amministrativa_id div 100) = ($tipo div 100))]/count),'###.###.###,##','european')" />
</column>
</xsl:if>
</xsl:for-each>
<column>
	<xsl:attribute name="num">
				<xsl:value-of select="count($unique-tip-num)+2"/>
	</xsl:attribute>

<xsl:value-of select="format-number(sum(//ROW/count),'###.###.###,##','european')" />
</column>
</ROW>
</ROWS>
</table>


							    </xsl:template>


							    </xsl:stylesheet>



