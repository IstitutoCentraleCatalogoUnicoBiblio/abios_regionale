<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="no" encoding="UTF-8"/>
	<xsl:param name="dest_soc_select"/>
	<xsl:template match="/">
		<xsl:for-each select="//result/doc">
			<OPTION>
				<xsl:choose>
					<xsl:when test="string-length($dest_soc_select) > 0 and $dest_soc_select = str[@name = 'descrizione']">
						<xsl:attribute name="VALUE"><xsl:value-of select="$dest_soc_select"/></xsl:attribute>
						<xsl:attribute name="selected">SELECTED</xsl:attribute>
						<xsl:value-of select="$dest_soc_select"/>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="VALUE"><xsl:value-of select="str[@name = 'descrizione']"/></xsl:attribute>
						<xsl:value-of select="str[@name = 'descrizione']"/>
					</xsl:otherwise>				
				</xsl:choose>
			</OPTION>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>