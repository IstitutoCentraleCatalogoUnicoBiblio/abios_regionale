<?xml version="1.0" encoding="ISO-8859-1"?>
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
<xsl:output method="text" encoding="UTF-8" indent="no"/><xsl:template match="/"><xsl:apply-templates/></xsl:template><xsl:template match="ROW"><xsl:if test="string-length(id1) &gt; 0"><xsl:variable name="sep" select="'£$;£$'"/><xsl:variable name="del" select="'£$'"/><xsl:variable name="id" select="id1"/><xsl:variable name="link" select="concat('http://anagrafe.iccu.sbn.it/iccu/abi?Action=ActionSearchesISIL&amp;Method=SearchesISIL&amp;SessionId=-1&amp;ABLITA=false&amp;ISIL_ST=IT&amp;ISIL_PR=',isil_pr,'&amp;ISIL_NR=',isil_nr)"/><xsl:value-of select="$del"/><xsl:value-of select="isil_st"/>-<xsl:value-of select="isil_pr"/><xsl:value-of select="isil_nr"/><xsl:value-of select="$sep"/><xsl:value-of select="$sep"/><xsl:value-of select="$sep"/><xsl:value-of select="denominazione"/><xsl:value-of select="$sep"/><xsl:value-of select="$sep"/><xsl:value-of select="$sep"/><xsl:value-of select="$sep"/><xsl:value-of select="indirizzo"/><xsl:value-of select="$sep"/><xsl:value-of select="$sep"/><xsl:value-of select="comune"/><xsl:value-of select="$sep"/><xsl:value-of select="cap"/><xsl:value-of select="$sep"/><xsl:value-of select="provincia"/><xsl:value-of select="$sep"/><xsl:value-of select="$sep"/><xsl:value-of select="$sep"/><xsl:value-of select="$sep"/><xsl:value-of select="$sep"/><xsl:value-of select="$link"/><xsl:value-of select="$sep"/><xsl:value-of select="$sep"/><xsl:value-of select="$sep"/><xsl:value-of select="$sep"/><xsl:value-of select="frazione"/><xsl:value-of select="$sep"/><xsl:value-of select="$del"/></xsl:if></xsl:template>
</xsl:stylesheet>