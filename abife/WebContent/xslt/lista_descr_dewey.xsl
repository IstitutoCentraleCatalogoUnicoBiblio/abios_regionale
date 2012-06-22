<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="no" encoding="UTF-8"/>
<xsl:param name="id_dewey"/>
<xsl:param name="torna"/>
<xsl:param name="mappe"/>
<xsl:template match="/">
<xsl:call-template name="schema"/>
</xsl:template>
<xsl:template name="schema">
<xsl:for-each select="//result/doc">
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
	<td class="verdeLeft3" width="50%">Fondi speciali - Codice Dewey</td>
	<td width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
</tbody></table>

<table width="100%" border="0">
<tbody>
<xsl:variable name="dewey_descr">
	<xsl:for-each select="arr[@name = 'dewey_display']/str">
		<xsl:if test="substring-before(., '$$$') = $id_dewey">
			<xsl:value-of select="substring-after(., '$$$')"/>
		</xsl:if>
	</xsl:for-each>
</xsl:variable>
<tr>
	<td class="verdinoLeft2" width="30%" height="10%">Codice Dewey</td>
	<td class="verdinoLeft2" width="70%" height="10%">Descrizione</td>
</tr>
<tr>
	<td class="grigioLeft1" width="30%"><xsl:value-of select="$id_dewey"/></td>
	<td class="grigioLeft1" width="70%"><xsl:value-of select="$dewey_descr"/></td>
</tr>
<tr><td colspan="2"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>

</tbody></table>
<xsl:variable name="id_bib">
<xsl:value-of select="str[@name = 'id_biblioteca']"/>
</xsl:variable>
<a target="_top"><xsl:attribute name="href"><xsl:value-of select="concat('home.jsp?id_biblioteca=', $id_bib, '&amp;page=patr&amp;torna=', $torna,'&amp;mappe=', $mappe)"/></xsl:attribute><font size="2" color="#ff6500" face="Arial, Helvetica, Comic Sans MS">Indietro</font></a>
<br/>

</xsl:for-each>
</xsl:template>
</xsl:stylesheet>