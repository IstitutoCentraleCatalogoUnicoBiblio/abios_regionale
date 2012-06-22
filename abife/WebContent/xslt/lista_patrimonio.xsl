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
<tr><td class="verdeCenter3">PATRIMONIO LIBRARIO - SPECIALIZZAZIONI - FONDI SPECIALI</td></tr>
<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>

<table width="100%" border="0">
<tbody><tr>
	<td class="verdeLeft3" width="25%">Patrimonio librario</td>
	<td width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
</tbody></table>
<table width="100%" border="0">
<tbody>
<xsl:variable name="patr_null">
	<xsl:for-each select="arr[@name = 'patrimonio_librario_display']/str">
		<xsl:value-of select="concat(., '-')"/>
	</xsl:for-each>
</xsl:variable>

<xsl:choose>
	<xsl:when test="string-length($patr_null) > 0">
		<tr>
			<td class="verdinoLeft2" width="50%" height="10%">Tipologia</td>
			<td class="verdinoLeft2" width="50%" height="10%">Quantità</td>
		</tr>
		
		<xsl:for-each select="arr[@name = 'patrimonio_librario_tipologia']/str">
		<xsl:variable name="tip" select="." />
		<xsl:choose>
		<xsl:when test="string-length(substring-after($tip, ';')) = 0">
			<tr>
			<td class="grigioLeft1" width="50%"><font color="Navy"><xsl:value-of select="substring-before($tip, ';')" /></font></td>
			<td class="grigioLeft1" width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
			</tr>
		</xsl:when>
		<xsl:otherwise>
			<tr>
			<td class="grigioLeft1" width="50%"><font color="Navy"><xsl:value-of select="substring-before($tip, ';')" /></font></td>
			<td class="grigioLeft1" width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
			</tr>
			<tr>
			<td class="grigioLeft1" width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;</xsl:text><font color="Navy"><xsl:value-of select="concat('- ', substring-after($tip, ';'))" /></font></td>
			<td class="grigioLeft1" width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
		</tr>
		</xsl:otherwise>
		</xsl:choose>
		
				
			<xsl:for-each select="//result/doc/arr[@name = 'patrimonio_librario_display']/str">	
				<xsl:if test="substring-before(., '$$$') = $tip">
					<tr>
						<td class="grigioLeft1" width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;</xsl:text><xsl:value-of select="substring-before(substring-after(., '$$$'), ';')"/></td>
						<xsl:choose>
						<xsl:when test="number(substring-after(substring-after(., '$$$'), ';')) > 0">
							<td class="grigioLeft1" width="50%"><xsl:value-of select="substring-after(substring-after(., '$$$'), ';')"/></td>
						</xsl:when>
						<xsl:otherwise>
							<td class="grigioLeft1" width="50%"><font color="Red">Non quantificato</font></td>
						</xsl:otherwise>
						</xsl:choose>
					</tr>			
				</xsl:if>
			
			</xsl:for-each>
				
		</xsl:for-each>	
	</xsl:when>
	<xsl:otherwise>
		<tr>
			<td class="grigioLeft1" colspan="2" width="100%"><font color="Red">Dato non definito</font></td>
		</tr>
	</xsl:otherwise>
</xsl:choose>
<tr><td colspan="2"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>


<tr>
 <td valign="top">
<table width="100%" border="0">
<tbody><tr><td colspan="2"></td></tr>
<tr>
	<td class="verdeLeft3" width="25%">Inventario</td>
	<xsl:choose>
		<xsl:when test="(string-length(bool[@name = 'inventario_cartaceo']) = 0) and (string-length(bool[@name = 'inventario_informatizzato']) = 0)">
			<td class="grigioLeft1" width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><font color="Red">Dato non definito</font></td>
		</xsl:when>
		<xsl:otherwise>
			<xsl:if test="(string-length(bool[@name = 'inventario_cartaceo']) > 0) and (bool[@name = 'inventario_cartaceo'] = 'true')">
				<td class="grigioLeft1" width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>Cartaceo</td>
			</xsl:if>
			<xsl:if test="(string-length(bool[@name = 'inventario_informatizzato']) > 0) and (bool[@name = 'inventario_informatizzato'] = 'true')">
				<td class="grigioLeft1" width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>Informatizzato</td>
			</xsl:if>	
		</xsl:otherwise>
	</xsl:choose>
</tr>
<tr><td colspan="2"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
<tr>
	<td class="verdeLeft3" width="25%">Catalogo topografico</td>
	<xsl:choose>
		<xsl:when test="(string-length(bool[@name = 'catalogo_topografico_cartaceo']) = 0) and (string-length(bool[@name = 'catalogo_topografico_informatizzato']) = 0)">
			<td class="grigioLeft1" width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><font color="Red">Dato non definito</font></td>
		</xsl:when>
		<xsl:otherwise>
			<xsl:if test="(string-length(bool[@name = 'catalogo_topografico_cartaceo']) > 0) and (bool[@name = 'catalogo_topografico_cartaceo'] = 'true')">
				<td class="grigioLeft1" width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>Cartaceo</td>
			</xsl:if>
			<xsl:if test="(string-length(bool[@name = 'catalogo_topografico_informatizzato']) > 0) and (bool[@name = 'catalogo_topografico_informatizzato'] = 'true')">
				<td class="grigioLeft1" width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>Informatizzato</td>
			</xsl:if>			
		</xsl:otherwise>
	</xsl:choose>
</tr>
<tr><td colspan="2"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
<tr>
	<td class="verdeLeft3" width="25%">Fondi antichi (fino al 1830)</td>
	<xsl:choose>
		<xsl:when test="int[@name = 'id_fondi_antichi_consistenza'] = 1">
			<td class="grigioLeft1" width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><font color="Red">Dato non definito</font></td>
		</xsl:when>
		<xsl:when test="int[@name = 'id_fondi_antichi_consistenza'] = 2">
			<td class="grigioLeft1" width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>Fino a 1000 volumi</td>
		</xsl:when>
		<xsl:when test="int[@name = 'id_fondi_antichi_consistenza'] = 3">
			<td class="grigioLeft1" width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>Da 1000 a 5000 volumi</td>
		</xsl:when>
		<xsl:when test="int[@name = 'id_fondi_antichi_consistenza'] = 4">
			<td class="grigioLeft1" width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>Oltre 5000 volumi</td>
		</xsl:when>
	</xsl:choose>
	
</tr>
<tr><td colspan="2"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table></td></tr>


<xsl:variable name="spec">
<xsl:for-each select="arr[@name = 'dewey_libero_display']/str">
	<xsl:value-of select="concat(., ' - ')"/>
</xsl:for-each>
</xsl:variable>

<xsl:if test="string-length($spec) > 0">
<table width="100%" border="0">
<tbody><tr>
	<td class="verdeLeft3" width="25%">Specializzazioni della biblioteca</td>
	<td width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
</tbody></table>

<table width="100%" border="0">
<tbody>	
		<tr>
		<td class="verdinoLeft2" width="25%" height="10%">Cod. Dewey<xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
		<td class="verdinoLeft2" width="37%" height="10%">Descrizione ufficiale</td>
		<td class="verdinoLeft2" width="38%" height="10%">Descrizione libera</td>
		</tr>
		<xsl:for-each select="arr[@name = 'dewey_libero_display']/str">
			<tr>
				<td class="grigioLeft1" width="25%" height="10%">				
					<xsl:choose>
						<xsl:when test="string-length(substring-before(.,'$$$')) > 3">
							<xsl:value-of select="substring(substring-before(.,'$$$'), 1, 3)"/>.<xsl:value-of select="substring(substring-before(.,'$$$'), 4, 6)"/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="substring-before(.,'$$$')"/>
						</xsl:otherwise>
					</xsl:choose>				
				<!-- xsl:value-of select="substring-before(.,'$$$')"/-->				
				</td>
				<td class="grigioLeft1" width="37%"><xsl:value-of select="substring-before(substring-after(.,'$$$'),';')"/></td>
				<xsl:if test="string-length(substring-after(substring-after(.,'$$$'),';')) = 0">
					<td class="grigioLeft1" width="38%"><font color="Red">Dato non definito</font></td>
				</xsl:if>
				<xsl:if test="string-length(substring-after(substring-after(.,'$$$'),';')) > 0">
					<td class="grigioLeft1" width="38%"><xsl:value-of select="substring-after(substring-after(.,'$$$'),';')"/></td>
				</xsl:if>
			</tr>
		</xsl:for-each>
	
<tr><td colspan="2"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>
</xsl:if>

<table width="100%" border="0">
<tbody><tr>
	<td class="verdeLeft3" width="25%">Fondi speciali</td>
	<td width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>



</tbody></table>

<table width="100%" border="0">
<tbody>	

<xsl:variable name="speciali">
<xsl:for-each select="arr[@name = 'fondi_speciali_display']/str">
	<xsl:value-of select="concat(., ' - ')"/>
</xsl:for-each>
</xsl:variable>
<xsl:variable name="id_bib">
<xsl:value-of select="str[@name = 'id_biblioteca']"/>
</xsl:variable>

<xsl:choose>
	<xsl:when test="string-length($speciali) = 0">
		<tr><td class="grigioLeft1"><font color="Red">Dato non definito</font></td></tr>
		<tr><td colspan="2"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
	</xsl:when>
	<xsl:otherwise>
		<tr>
		<td class="verdinoLeft2" width="25%" height="10%">Denominazione</td>
		<td class="verdinoLeft2" width="25%" height="10%">Descrizione</td>
		<td class="verdinoLeft2" width="10%" height="10%">Codice Dewey</td>
		<td class="verdinoLeft2" width="20%" height="10%">Catalogo / Inventario</td>
		<td class="verdinoLeft2" width="20%" height="10%">Fondo depositato</td>
		</tr>
		<xsl:for-each select="arr[@name = 'fondi_speciali_display']/str">
		<tr>
			<td class="grigioLeft1" width="25%" height="10%"><xsl:value-of select="substring-before(.,'$$$')"/></td>
			<td class="grigioLeft1" width="25%"><xsl:value-of select="substring-before(substring-after(.,'$$$'),';')"/></td>
			<td class="grigioLeft1" width="10%"><xsl:if test="string-length(substring-after(substring-after(substring-after(., '$$$'), '$$$'), '$$$')) > 0"><a target="_top"><xsl:attribute name="href"><xsl:value-of select="concat('home.jsp?id_biblioteca=', $id_bib, '&amp;page=dew&amp;id_dewey=',substring-after(substring-after(substring-after(., '$$$'), '$$$'), '$$$'),'&amp;torna=', $torna,'&amp;mappe=', $mappe)"/></xsl:attribute><font size="1.5" color="Blue" face="Verdana, Arial, Helvetica, sans-serif"><xsl:value-of select="substring-after(substring-after(substring-after(., '$$$'), '$$$'), '$$$')"/></font></a></xsl:if></td>
			<td class="grigioLeft1" width="20%"><center><xsl:value-of select="substring-before(substring-after(substring-after(.,'$$$'),'$$$'),';')"/><xsl:if test="string-length(substring-after(substring-before(substring-after(substring-after(.,'$$$'),'$$$'),'$$$'), ';')) > 0"><br/><a target="_blank"><xsl:attribute name="href"><xsl:value-of select="substring-after(substring-before(substring-after(substring-after(.,'$$$'),'$$$'),'$$$'), ';')"/></xsl:attribute><xsl:value-of select="substring-after(substring-before(substring-after(substring-after(.,'$$$'),'$$$'),'$$$'), ';')"/></a></xsl:if></center></td>
			<td class="grigioLeft1" width="20%"><xsl:value-of select="substring-after(substring-before(substring-after(.,'$$$'),'$$$'),';')"/></td>			
		</tr>
		</xsl:for-each>
		<tr><td colspan="2"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
	</xsl:otherwise>
</xsl:choose>

</tbody></table>

</td></tr></tbody></table>

</xsl:for-each>
</xsl:template>
</xsl:stylesheet>