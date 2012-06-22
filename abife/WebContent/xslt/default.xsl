<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="yes" encoding="UTF-8"/>
	<xsl:template match="/">
		<textarea cols="80" rows="50">
			<xsl:copy-of select="." />
		</textarea>
	</xsl:template>
</xsl:stylesheet>