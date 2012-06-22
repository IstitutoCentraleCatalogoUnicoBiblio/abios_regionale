<xsl:stylesheet version="1.0" xmlns:xhtml="http://www.w3.org/1999/xhtml"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema">

	<xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes" omit-xml-declaration="yes" />
	<xsl:param name="path" />
	<xsl:template match="/">
		<xsl:for-each select="//lst[@name ='facet_fields']/lst[@name = 'regione']/int">
			<xsl:variable name="denominazione">
				<xsl:value-of select="@name" />
			</xsl:variable>
			<xsl:variable name="count">
				<xsl:value-of select="." />
			</xsl:variable>
			<div>
				<a style="color: rgb(80, 124, 169);">
					<xsl:attribute name="href"><xsl:value-of select="concat($path, 'mappe/province.jsp?id_biblioteca=*&amp;regione=',$denominazione)" /></xsl:attribute>
					<xsl:value-of select="$denominazione" /><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>(<xsl:value-of select="$count" />)
				</a></div>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>
