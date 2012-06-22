<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="no" encoding="UTF-8"/>
<xsl:param name="type"/>
<xsl:param name="id_cat_url"/>
<xsl:param name="torna"/>
<xsl:param name="mappe"/>
<xsl:template match="/">
<xsl:call-template name="schema"/>
</xsl:template>
<xsl:template name="schema">
<xsl:for-each select="//result/doc">
<xsl:choose>
<xsl:when test="$type = 'gen'">
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
<tr>
	<td class="verdeLeft3" width="100%">Cataloghi generali - URL</td>
	<td width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
</tbody></table>

<table width="100%" border="0">
<tbody>
<xsl:variable name="url">
<xsl:for-each select="arr[@name = 'cataloghi_generali_url']/str">
	<xsl:if test="number(substring-before(., '$$$')) = number($id_cat_url)">
		<xsl:value-of select="substring-after(., '$$$')"/>
	</xsl:if>
</xsl:for-each>
</xsl:variable>

	<xsl:choose>
		<xsl:when test="string-length($url) = 0">
<tr>			<td class="grigioLeft1" width="100%"><center><strong>Nessuna url disponibile</strong></center></td></tr>
		</xsl:when>
		<xsl:otherwise>
<tr>
	<td class="verdinoLeft2" width="100%" height="10%">Descrizione url</td>
</tr>
<tr>
			<td class="grigioLeft1" width="100%"><xsl:value-of select="$url"/></td>
</tr>		</xsl:otherwise>
	</xsl:choose>
	

<tr><td colspan="2"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>

</tbody></table>

<xsl:variable name="id_bib">
<xsl:value-of select="str[@name = 'id_biblioteca']"/>
</xsl:variable>
<a target="_top"><xsl:attribute name="href"><xsl:value-of select="concat('home.jsp?id_biblioteca=', $id_bib, '&amp;page=catgen&amp;torna=', $torna,'&amp;mappe=', $mappe)"/></xsl:attribute><font size="2" color="#ff6500" face="Arial, Helvetica, Comic Sans MS">Indietro</font></a>
<br/>
</xsl:when>
<xsl:when test="$type = 'spec'">
<table width="100%" border="0">
<tbody><tr>
	<td class="verdeLeft3" width="100%">Cataloghi speciali - URL</td>
	<td width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
</tbody></table>

<table width="100%" border="0">
<tbody>
<xsl:variable name="url">
<xsl:for-each select="arr[@name = 'cataloghi_speciali_url']/str">
	<xsl:if test="number(substring-before(., '$$$')) = number($id_cat_url)">
		<xsl:value-of select="substring-after(., '$$$')"/>
	</xsl:if>
</xsl:for-each>
</xsl:variable>

	<xsl:choose>
		<xsl:when test="string-length($url) = 0">
<tr>			<td class="grigioLeft1" width="100%"><center><strong>Nessuna url disponibile</strong></center></td></tr>
		</xsl:when>
		<xsl:otherwise>
<tr>
	<td class="verdinoLeft2" width="100%" height="10%">Descrizione url</td>
</tr>
<tr>
			<td class="grigioLeft1" width="100%"><xsl:value-of select="$url"/></td>
</tr>		</xsl:otherwise>
	</xsl:choose>
	

<tr><td colspan="2"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>

</tbody></table>

<xsl:variable name="id_bib">
<xsl:value-of select="str[@name = 'id_biblioteca']"/>
</xsl:variable>
<a target="_top"><xsl:attribute name="href"><xsl:value-of select="concat('home.jsp?id_biblioteca=', $id_bib, '&amp;page=catspec&amp;torna=', $torna,'&amp;mappe=', $mappe)"/></xsl:attribute><font size="2" color="#ff6500" face="Arial, Helvetica, Comic Sans MS">Indietro</font></a>
<br/>
</xsl:when>
<xsl:when test="$type = 'coll'">
<table width="100%" border="0">
<tbody><tr>
	<td class="verdeLeft3" width="100%">Cataloghi collettivi - URL</td>
	<td width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
</tbody></table>

<table width="100%" border="0">
<tbody>
<xsl:variable name="url">
<xsl:for-each select="arr[@name = 'cataloghi_collettivi_materiale_url']/str">
	<xsl:if test="number(substring-before(., '$$$')) = number($id_cat_url)">
		<xsl:value-of select="substring-after(., '$$$')"/>
	</xsl:if>
</xsl:for-each>
</xsl:variable>

	<xsl:choose>
		<xsl:when test="string-length($url) = 0">
<tr>			<td class="grigioLeft1" width="100%"><center><strong>Nessuna url disponibile</strong></center></td></tr>
		</xsl:when>
		<xsl:otherwise>
<tr>
	<td class="verdinoLeft2" width="100%" height="10%">Descrizione url</td>
</tr>
<tr>
			<td class="grigioLeft1" width="100%"><xsl:value-of select="$url"/></td>
</tr>		</xsl:otherwise>
	</xsl:choose>
	

<tr><td colspan="2"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>

</tbody></table>

<xsl:variable name="id_bib">
<xsl:value-of select="str[@name = 'id_biblioteca']"/>
</xsl:variable>
<a target="_top"><xsl:attribute name="href"><xsl:value-of select="concat('home.jsp?id_biblioteca=', $id_bib, '&amp;page=catcoll&amp;torna=', $torna,'&amp;mappe=', $mappe)"/></xsl:attribute><font size="2" color="#ff6500" face="Arial, Helvetica, Comic Sans MS">Indietro</font></a>
<br/>
</xsl:when>
</xsl:choose>

</xsl:for-each>
</xsl:template>
</xsl:stylesheet>