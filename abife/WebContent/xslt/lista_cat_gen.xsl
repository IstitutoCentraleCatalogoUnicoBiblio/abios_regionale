<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="no" encoding="UTF-8"/>
<xsl:param name="torna"/>
<xsl:param name="mappe"/>
<xsl:template match="/">
<xsl:call-template name="schema"/>
</xsl:template>
<xsl:template name="schema">
<xsl:for-each select="//result/doc">
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" height="0">
<tbody><tr>
<td>
<table width="100%" border="0">
<tbody>
<center>
<font size="2" color="Black" face="Arial">
	<xsl:if test="int[@name = 'isil_numero'] &gt;= 1000">
	<xsl:value-of select="concat(str[@name = 'isil_stato'],'-',str[@name = 'isil_provincia'],int[@name = 'isil_numero'])"/>
	</xsl:if>
	<xsl:if test="int[@name = 'isil_numero'] &gt;= 100 and int[@name = 'isil_numero'] &lt; 1000">
	<xsl:value-of select="concat(str[@name = 'isil_stato'],'-',str[@name = 'isil_provincia'],'0',int[@name = 'isil_numero'])"/>
	</xsl:if>
	<xsl:if test="int[@name = 'isil_numero'] &gt;= 10 and int[@name = 'isil_numero'] &lt; 100">
	<xsl:value-of select="concat(str[@name = 'isil_stato'],'-',str[@name = 'isil_provincia'],'00',int[@name = 'isil_numero'])"/>
	</xsl:if>
	<xsl:if test="int[@name = 'isil_numero'] &gt; 0 and int[@name = 'isil_numero'] &lt; 10">
	<xsl:value-of select="concat(str[@name = 'isil_stato'],'-',str[@name = 'isil_provincia'],'000',int[@name = 'isil_numero'])"/>
	</xsl:if> <xsl:text disable-output-escaping="yes">&amp;nbsp;&amp;nbsp;-&amp;nbsp;</xsl:text> <strong><xsl:value-of select="str[@name = 'denominazione_ufficiale']"/></strong> <xsl:text disable-output-escaping="yes">&amp;nbsp;-&amp;nbsp;&amp;nbsp;</xsl:text> <xsl:value-of select="str[@name = 'comune']"></xsl:value-of></font><hr font="" size="2" width="60%" align="center" color="Green"/>	
</center>
<xsl:variable name="gen">
<xsl:for-each select="arr[@name = 'cataloghi_generali_display']/str">
	<xsl:value-of select="concat(., '-')"/>
</xsl:for-each>
</xsl:variable>
<xsl:variable name="id_bib">
<xsl:value-of select="str[@name = 'id_biblioteca']"/>
</xsl:variable>
<tr><td class="verdeCenter3" colspan="12">CATALOGHI GENERALI</td></tr>

<xsl:choose>
	<xsl:when test="string-length($gen) = 0">
		<tr><td class="grigioCenter2" colspan="12" width="100%"><strong>Nessun dato definito</strong></td></tr>
	</xsl:when>
	<xsl:otherwise>	
	<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
		<tr>
			<td class="verdeCenter1" align="center" width="25%">DESCRIZIONE CATALOGO</td>
			<td class="verdeCenter1" colspan="9" align="center" width="45%">FORMATI / ACCESSO</td>
			<td class="verdeCenter1" colspan="2" align="center" width="30%">COPERTURA BIBLIOGRAFICA</td>
		
		</tr>
		
		<tr>
			<td class="verdinoCenter1" width="25%">Tipo catalogo</td>
			<td class="verdinoCenter1">Schede</td>
			<td class="verdinoCenter1">%</td>
			<td class="verdinoCenter1">Volume</td>
			<td class="verdinoCenter1">%</td>
			<td class="verdinoCenter1">Citazione bibliografica</td>
			<td class="verdinoCenter1">Microforme</td>
			<td class="verdinoCenter1">%</td>
			<td class="verdinoCenter1">Informatiz.</td>
			<td class="verdinoCenter1">%</td>
			<td class="verdinoCenter1" width="15%">Da anno</td>
			<td class="verdinoCenter1" width="15%">A anno</td>
		</tr>
		
		<xsl:for-each select="arr[@name = 'cataloghi_generali_display']/str">
		<tr>
			<td class="grigioLeft1" valign="top" width="25%"><xsl:value-of select="substring-before(substring-after(., 'tipo_catalogo:'), ',')"/></td>
			<td class="grigioLeft1" valign="top"><xsl:value-of select="substring-before(substring-after(., 'schede:'), '$$$')"/></td>
			<td class="grigioLeft1" valign="top"><xsl:value-of select="substring-after(substring-before(substring-after(., 'schede:'), ','), '$$$')"/></td>
			<td class="grigioLeft1" valign="top"><xsl:value-of select="substring-before(substring-after(., 'volume:'), '$$$')"/></td>
			<td class="grigioLeft1" valign="top"><xsl:value-of select="substring-after(substring-before(substring-after(., 'volume:'), ','), '$$$')"/></td>
			<td class="grigioLeft1" valign="top"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
			<td class="grigioLeft1" valign="top"><xsl:value-of select="substring-before(substring-after(., 'microforme:'), '$$$')"/></td>
			<td class="grigioLeft1" valign="top"><xsl:value-of select="substring-after(substring-before(substring-after(., 'microforme:'), ','), '$$$')"/></td>
			<xsl:if test="substring-before(substring-after(., 'supporto_digitale:'), ',') = 'Online' and string-length(substring-after(substring-before(substring-after(., 'supporto_digitale:'), '$$$'), ',')) > 0"><td class="grigioLeft1" valign="top"><a target="_top"><xsl:attribute name="href"><xsl:value-of select="concat('home.jsp?id_biblioteca=', $id_bib, '&amp;page=curl&amp;type=gen&amp;id_cat_url=',substring-after(substring-before(substring-after(., 'supporto_digitale:'), '$$$'), ','),'&amp;torna=', $torna,'&amp;mappe=', $mappe)"/></xsl:attribute>Online</a></td></xsl:if>
			<xsl:if test="substring-before(substring-after(., 'supporto_digitale:'), ',') = 'Online' and string-length(substring-after(substring-before(substring-after(., 'supporto_digitale:'), '$$$'), ',')) = 0"><td class="grigioLeft1" valign="top"><a target="_top"><xsl:attribute name="href"><xsl:value-of select="concat('home.jsp?id_biblioteca=', $id_bib, '&amp;page=curl&amp;type=gen&amp;id_cat_url=0&amp;torna=', $torna,'&amp;mappe=', $mappe)"/></xsl:attribute>Online</a></td></xsl:if>
			<xsl:if test="substring-before(substring-after(., 'supporto_digitale:'), ',') != 'Online'"><td class="grigioLeft1" valign="top"><xsl:value-of select="substring-before(substring-after(., 'supporto_digitale:'), ',')"/></td></xsl:if>
			<td class="grigioLeft1" valign="top"><xsl:value-of select="substring-before(substring-after(substring-after(., 'supporto_digitale:'), '$$$'), ',')"/></td>
			<td class="grigioLeft1" valign="top" width="15%"><xsl:value-of select="substring-before(substring-after(., 'da_anno:'), ',')"/></td>
			<td class="grigioLeft1" valign="top" width="15%"><xsl:value-of select="substring-after(., ',a_anno:')"/></td>
		</tr>
		</xsl:for-each>
		<tr><td colspan="12"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</xsl:otherwise>
</xsl:choose>
</tbody></table>


</td></tr>
</tbody></table>

</xsl:for-each>
</xsl:template>
</xsl:stylesheet>