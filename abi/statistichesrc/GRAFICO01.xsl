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



