<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="no" encoding="UTF-8"/>
	<xsl:param name="spogli_select"/>
	<xsl:template match="/">
		<xsl:for-each select="//result/doc">
			<OPTION>
				<xsl:choose>
					<xsl:when test="string-length($spogli_select) > 0 and $spogli_select = str[@name = 'descrizione_bibliografica']">
						<xsl:attribute name="VALUE"><xsl:value-of select="$spogli_select"/></xsl:attribute>
						<xsl:attribute name="selected">SELECTED</xsl:attribute>
						<xsl:value-of select="$spogli_select"/>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="VALUE"><xsl:value-of select="str[@name = 'descrizione_bibliografica']"/></xsl:attribute>
						<xsl:value-of select="str[@name = 'descrizione_bibliografica']"/>
					</xsl:otherwise>
				</xsl:choose>
			</OPTION>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>