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
<xsl:variable name="id_bib">
<xsl:value-of select="str[@name = 'id_biblioteca']"/>
</xsl:variable>
<tr><td class="verdeCenter3" colspan="15">CATALOGHI COLLETTIVI</td></tr>
<xsl:variable name="partecipa">
<xsl:for-each select="arr[@name = 'cataloghi_collettivi_partecipa']/str">
<xsl:value-of select="concat(., '-')"/>
</xsl:for-each>
</xsl:variable>
<tr height="20%">
	<td class="verdinoLeft2" colspan="5" width="40%">La biblioteca partecipa a cataloghi collettivi</td>
    <td class="grigioLeft1" colspan="10" width="60%">
    	<xsl:choose>
    		<xsl:when test="string-length($partecipa) = 0">
    			Non partecipa ad alcun tipo di catalogo
    		</xsl:when>
    		<xsl:otherwise>
    			<xsl:for-each select="arr[@name = 'cataloghi_collettivi_partecipa']/str">
    				<xsl:value-of select="concat(substring(., 1, string-length(.) - 1), 'i')"/><br/>
    			</xsl:for-each>
    		</xsl:otherwise>
    	</xsl:choose>    	
    </td>
</tr>

<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>

<xsl:for-each select="arr[@name = 'cataloghi_collettivi_display']/str">
<tr>
	<td class="verdeCenter1" colspan="4" width="30%">DENOMINAZIONE CATALOGO</td>
	<td class="grigioLeft1" colspan="11" width="70%">
	<xsl:choose>
		<xsl:when test="string-length(substring-before(substring-after(., 'catalogo:'), ',')) = 0">
			<font color="Red"><strong>Dato non definito</strong></font>
		</xsl:when>
		<xsl:otherwise>
			<strong><xsl:value-of select="substring-before(substring-after(., 'catalogo:'), ',')"/></strong>
		</xsl:otherwise>
	</xsl:choose>
	</td>
</tr>
<tr>
	<td class="verdeCenter1" colspan="4" width="30%">Zona di espansione:</td>
	<td class="grigioLeft1" colspan="3" width="30%"><strong><xsl:value-of select="substring-before(substring-after(., 'zona:'), ',')"/></strong></td>
	<td class="verdeCenter1" colspan="4" width="15%">Zona:</td>
	<td class="grigioLeft1" colspan="4" width="25%"><strong><xsl:value-of select="substring-before(substring-after(., 'dettaglio_zona:'), ',')"/></strong></td>
</tr>
<tr><td class="verdeCenter1" colspan="15">DETTAGLIO MATERIALI</td></tr>
<tr>
	<td class="verdinoCenter1" rowspan="2" colspan="4" width="30%" align="center">DENOMINAZIONE MATERIALE</td>
	<td class="verdinoCenter1" colspan="9" align="center">FORMATI / ACCESSO</td>
	<td class="verdinoCenter1" colspan="2" width="15%" align="center">COPERTURA BIBLIOGRAFICA</td>
</tr>
<tr>
	<td class="verdinoCenter1" width="5%">Schede</td>
	<td class="verdinoCenter1" width="2%">%</td>
	<td class="verdinoCenter1" width="7%">Volume</td>
	<td class="verdinoCenter1" width="2%">%</td>
	<td class="verdinoCenter1" width="1%">Citazione bibliografica</td>
	<td class="verdinoCenter1" width="6%">Microforme</td>
	<td class="verdinoCenter1" width="2%">%</td>
	<td class="verdinoCenter1" width="7%">Informatizzato</td>
	<td class="verdinoCenter1" width="2%">%</td>
	<td class="verdinoCenter1" width="3%">Da anno</td>
	<td class="verdinoCenter1" width="3%">A anno</td>
</tr>
<tr>
	<td class="grigioLeft1" colspan="4" valign="top"><xsl:value-of select="substring-before(substring-after(., 'patrimonio:'), ',')"/></td>
	<td class="grigioLeft1" valign="top"><xsl:value-of select="substring-before(substring-after(., 'schede:'), '$$$')"/></td>
	<td class="grigioLeft1" valign="top"><xsl:value-of select="substring-after(substring-before(substring-after(., 'schede:'), ','), '$$$')"/></td>
	<td class="grigioLeft1" valign="top"><xsl:value-of select="substring-before(substring-after(., 'volume:'), '$$$')"/></td>
	<td class="grigioLeft1" valign="top"><xsl:value-of select="substring-after(substring-before(substring-after(., 'volume:'), ','), '$$$')"/></td>
	<td class="grigioLeft1" valign="top"><xsl:value-of select="substring-before(substring-after(., 'citazione:'), ',')"/></td>
	<td class="grigioLeft1" valign="top"><xsl:value-of select="substring-before(substring-after(., 'microforme:'), '$$$')"/></td>
	<td class="grigioLeft1" valign="top"><xsl:value-of select="substring-after(substring-before(substring-after(., 'microforme:'), ','), '$$$')"/></td>
	<xsl:if test="substring-before(substring-after(., 'supporto_digitale:'), ',') = 'Online' and string-length(substring-after(substring-before(substring-after(., 'supporto_digitale:'), '$$$'), ',')) > 0"><td class="grigioLeft1" valign="top"><a target="_top"><xsl:attribute name="href"><xsl:value-of select="concat('home.jsp?id_biblioteca=', $id_bib, '&amp;page=curl&amp;type=coll&amp;id_cat_url=',substring-after(substring-before(substring-after(., 'supporto_digitale:'), '$$$'), ','),'&amp;torna=', $torna,'&amp;mappe=', $mappe)"/></xsl:attribute>Online</a></td></xsl:if>
	<xsl:if test="substring-before(substring-after(., 'supporto_digitale:'), ',') = 'Online' and string-length(substring-after(substring-before(substring-after(., 'supporto_digitale:'), '$$$'), ',')) = 0"><td class="grigioLeft1" valign="top"><a target="_top"><xsl:attribute name="href"><xsl:value-of select="concat('home.jsp?id_biblioteca=', $id_bib, '&amp;page=curl&amp;type=coll&amp;id_cat_url=0&amp;torna=', $torna,'&amp;mappe=', $mappe)"/></xsl:attribute>Online</a></td></xsl:if>
	<xsl:if test="substring-before(substring-after(., 'supporto_digitale:'), ',') != 'Online'"><td class="grigioLeft1" valign="top"><xsl:value-of select="substring-before(substring-after(., 'supporto_digitale:'), ',')"/></td></xsl:if>
	<td class="grigioLeft1" valign="top"><xsl:value-of select="substring-before(substring-after(substring-after(., 'supporto_digitale:'), '$$$'), ',')"/></td>
	<td class="grigioLeft1" valign="top"><xsl:value-of select="substring-before(substring-after(., 'da_anno:'), ',')"/></td>
	<td class="grigioLeft1" valign="top"><xsl:value-of select="substring-after(., ',a_anno:')"/></td>



</tr>

<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</xsl:for-each>

</tbody></table>

</td></tr>
</tbody></table>

</xsl:for-each>
</xsl:template>
</xsl:stylesheet>