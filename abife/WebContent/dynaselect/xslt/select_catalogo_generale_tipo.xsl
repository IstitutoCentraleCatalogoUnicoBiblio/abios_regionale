<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="no" encoding="UTF-8"/>
	<xsl:param name="cat_gen_select"/>
	<xsl:template match="/">
		<xsl:for-each select="//result/doc">
			<OPTION>
				<xsl:choose>
					<xsl:when test="string-length($cat_gen_select) > 0 and $cat_gen_select = str[@name = 'descrizione']">
						<xsl:attribute name="VALUE"><xsl:value-of select="$cat_gen_select"/></xsl:attribute>
						<xsl:attribute name="selected">SELECTED</xsl:attribute>
						<xsl:value-of select="$cat_gen_select"/>
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