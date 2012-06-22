<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="no" encoding="UTF-8"/>
	<xsl:param name="tip_amm_select"/>
	<xsl:template match="/">
		<xsl:for-each select="//result/doc">
			<xsl:variable name="id_ente_tipologia_amministrativa" select="int[@name = 'id_ente_tipologia_amministrativa']" />
			<xsl:variable name="descrizione" select="str[@name = 'descrizione']" />
			<xsl:variable name="lastChar" select="substring($id_ente_tipologia_amministrativa, string-length($id_ente_tipologia_amministrativa),1)" />
			<OPTION>
				<xsl:choose>
					<xsl:when test="string-length($tip_amm_select) > 0 and substring($tip_amm_select, string-length($tip_amm_select),1) != '*' and $tip_amm_select = $id_ente_tipologia_amministrativa">
						<xsl:attribute name="VALUE"><xsl:value-of select="$tip_amm_select"/></xsl:attribute>
						<xsl:attribute name="selected">SELECTED</xsl:attribute>
						<xsl:value-of select="concat('&#160;&#160;&#160;-&#160;', $descrizione)" />
					</xsl:when>
					<xsl:when test="string-length($tip_amm_select) > 0 and substring($tip_amm_select, string-length($tip_amm_select),1) = '*' and concat(substring($tip_amm_select, 1, string-length($tip_amm_select)-1), '0') = $id_ente_tipologia_amministrativa">
						<xsl:attribute name="VALUE"><xsl:value-of select="$tip_amm_select"/></xsl:attribute>
						<xsl:attribute name="selected">SELECTED</xsl:attribute>
						<xsl:value-of select="$descrizione" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:choose>
							<xsl:when test="$lastChar != '0'">
								<xsl:attribute name="VALUE"><xsl:value-of select="$id_ente_tipologia_amministrativa"/></xsl:attribute>
								<xsl:value-of select="concat('&#160;&#160;&#160;-&#160;', $descrizione)" />
							</xsl:when>
							<xsl:otherwise>
								<xsl:attribute name="VALUE"><xsl:value-of select="substring($id_ente_tipologia_amministrativa, 1, string-length($id_ente_tipologia_amministrativa)-1)"/>*</xsl:attribute>
								<xsl:value-of select="$descrizione" />
							</xsl:otherwise>
						</xsl:choose>
					</xsl:otherwise>
				</xsl:choose>
			</OPTION>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>