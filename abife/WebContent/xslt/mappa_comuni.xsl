<xsl:stylesheet version="1.0" xmlns:xhtml="http://www.w3.org/1999/xhtml"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema">
	<xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes" omit-xml-declaration="yes" />
	<xsl:param name="path"/>
	<xsl:template match="/">
		<xsl:for-each select="//lst[@name ='facet_fields']/lst[@name = 'comune_record']/int">
			<xsl:variable name="denominazione">
				<xsl:value-of select="translate(substring-before(@name, '$$$'),
                                'abcdefghijklmnopqrstuvwxyz',
                                'ABCDEFGHIJKLMNOPQRSTUVWXYZ')" />
			</xsl:variable>
			<xsl:variable name="id_comune">
				<xsl:value-of select="substring-after(@name, '$$$')" />
			</xsl:variable>
			<xsl:variable name="count">
				<xsl:value-of select="." />
			</xsl:variable>
			<a>
				<xsl:attribute name="href"><xsl:value-of select="concat($path, 'mappe/mappa.jsp?id_comune=',$id_comune)" /></xsl:attribute>
				<xsl:value-of select="$denominazione" /><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>(<xsl:value-of select="$count" />)
			</a>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>
