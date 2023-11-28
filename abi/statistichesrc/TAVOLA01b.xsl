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
<xsl:variable name="unique-regione-num" select="//ROW/regione[not(.=following::regione)]" />

<xsl:variable name="max" select="count($unique-regione-num)" />
<table name="TAVOLA01b" title="Tavola 1b BIBLIOTECHE CENSITE" summary="Biblioteche_censite">
<HEADERS>
<column num="1" colspan="1" rowspan="1">REGIONE</column>
<column num="2" colspan="1" rowspan="1">(A) Biblioteche censite</column>
<column num="3" colspan="1" rowspan="1">(B) N. di biblioteche per 100.000 abitanti</column>
</HEADERS>
<ROWS>
<xsl:for-each select="$unique-regione-num">
<xsl:variable name="reg" select="." />
<ROW>
	<xsl:attribute name="num">
				<xsl:value-of select="position()"/>
	</xsl:attribute>
<column num="1"><xsl:value-of select="$reg" /></column>
<column num="2"><xsl:value-of select="format-number(//ROW[regione = $reg]/count,'###.###.###,##','european')" /></column>
<column num="3"><xsl:value-of select="format-number(round((100000 * //ROW[regione = $reg]/count) div //ROW[regione = $reg]/nabitanti),'###.###.###,##','european')" /></column>
</ROW>
</xsl:for-each>

</ROWS>
</table>


							    </xsl:template>


							    </xsl:stylesheet>



