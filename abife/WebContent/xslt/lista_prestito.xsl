<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="no" encoding="UTF-8"/>
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
<tr><td class="verdeCenter3" colspan="2">PRESTITO</td></tr>
</tbody></table>
<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" width="35%">Prestito locale</td>
	<td width="65%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>

<xsl:choose>
	<xsl:when test="string-length(str[@name= 'prestito_locale_display']) = 0">
		<tr>
			<td class="grigioLeft1" colspan="2" width="100%"><font color="Red">Dato non definito</font></td>
		</tr>
	</xsl:when>
	<xsl:otherwise>
		<tr>
	<td class="verdinoLeft2" width="35%">Disponibile</td>
	<td class="grigioLeft1" width="65%"><xsl:value-of select="substring-before(substring-after(str[@name= 'prestito_locale_display'], 'disponibile:'), ',')"/></td>
</tr>
<tr>
	<td class="verdinoLeft2" width="35%">Procedure automatizzate</td>
	<td class="grigioLeft1" width="65%">
		<xsl:choose>
			<xsl:when test="string-length(substring-after(str[@name= 'prestito_locale_display'], 'procedure:')) = 0">
				<font color="Red">Dato non definito</font>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="substring-after(str[@name= 'prestito_locale_display'], 'procedure:')" />
			</xsl:otherwise>
		</xsl:choose>
	</td>
</tr>
	</xsl:otherwise>
</xsl:choose>

<xsl:variable name="materiale">
	<xsl:for-each select="arr[@name = 'materiale_escluso']/str">
		<xsl:value-of select="concat(., '-')"/>
	</xsl:for-each>
</xsl:variable>
<xsl:if test="string-length($materiale) > 0">
	<tr>
		<td class="verdinoLeft2" width="35%">E' escluso il prestito di:</td>
		<td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
	</tr>
	<xsl:for-each select="arr[@name = 'materiale_escluso']/str">
		<tr><td class="grigioLeft1" colspan="2" width="100%"><xsl:value-of select="."/></td></tr>
	</xsl:for-each>
</xsl:if>
<xsl:variable name="utenti">
	<xsl:for-each select="arr[@name = 'utenti_ammessi']/str">
		<xsl:value-of select="concat(., '-')"/>
	</xsl:for-each>
</xsl:variable>
<xsl:if test="string-length($utenti) > 0">
	<tr>
		<td class="verdinoLeft2" width="35%">E' limitato il prestito nella zona:</td>
		<td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
	</tr>
	<xsl:for-each select="arr[@name = 'utenti_ammessi']/str">
		<tr><td class="grigioLeft1" colspan="2" width="100%"><xsl:value-of select="."/></td></tr>
	</xsl:for-each>
</xsl:if>
<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>


<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" width="35%">Prestito interbibliotecario</td>
	<td width="65%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
<xsl:choose>
	<xsl:when test="(string-length(string(bool[@name = 'prestito_interbiblio_nazionale'])) = 0) and (string-length(string(bool[@name = 'prestito_interbiblio_internazionale'])) = 0)">
		<tr>
			<td class="verdinoLeft2" width="35%">Disponibile</td>
			<td class="grigioLeft1" width="65%"><font color="Red">Dato non definito</font></td>
		</tr>
	</xsl:when>
	<xsl:otherwise>
		<tr>
			<td class="verdinoLeft2" width="35%">Disponibile</td>
			<td class="grigioLeft1" width="65%">
				<xsl:if test="bool[@name = 'prestito_interbiblio_nazionale'] = 'true'">Nazionale<br/></xsl:if>
				<xsl:if test="bool[@name = 'prestito_interbiblio_internazionale'] = 'true'">Internazionale<br/></xsl:if>
			</td>
		</tr>
	</xsl:otherwise>
</xsl:choose>
<tr>
	<td class="verdinoLeft2" width="35%">Procedure automatizzate</td>
	<td class="grigioLeft1" width="65%">
		<xsl:choose>
			<xsl:when test="string-length(string(bool[@name = 'procedure_ill_automatizzate'])) = 0"><font color="Red">Dato non definito</font></xsl:when>
			<xsl:otherwise>
				<xsl:choose>
					<xsl:when test="bool[@name = 'procedure_ill_automatizzate'] = 'true'">Si</xsl:when>
					<xsl:otherwise>No</xsl:otherwise>
				</xsl:choose>
			</xsl:otherwise>
		</xsl:choose>
	</td>
</tr>


<tr><td colspan="2"></td></tr>
<xsl:variable name="ruolo">
	<xsl:for-each select="arr[@name = 'prestito_interbibliotecario_display']/str">
		<xsl:value-of select="concat(., '-')"/>
	</xsl:for-each>
</xsl:variable>
<tr>
	<td class="verdeLeft2" width="35%">Ruolo della biblioteca</td>
</tr>
<xsl:choose>
	<xsl:when test="string-length($ruolo) = 0">
		<tr>
			<td class="grigioLeft1" colspan="2" width="100%"><font color="Red">Dato non definito</font></td>
		</tr>
	</xsl:when>
	<xsl:otherwise>
		<xsl:for-each select="arr[@name = 'prestito_interbibliotecario_display']/str">
		<tr>
			<td class="verdinoLeft2" width="35%"><xsl:value-of select="substring-before(substring-after(., 'ruolo:'), ',')"/></td>
			<xsl:choose>
				<xsl:when test="(substring-before(substring-after(., 'nazionale:'), ',') = 'Si') or (substring-after(., 'internazionale:') = 'Si')">
					<td class="grigioLeft1" width="65%">
						<xsl:if test="substring-before(substring-after(., 'nazionale:'), ',') = 'Si'">Nazionale<br/></xsl:if>
						<xsl:if test="substring-after(., 'internazionale:') = 'Si'">Internazionale<br/></xsl:if>				
					</td>
				</xsl:when>
				<xsl:otherwise>
					<td class="grigioLeft1" width="65%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
				</xsl:otherwise>
			</xsl:choose>
		</tr>
		</xsl:for-each>
	</xsl:otherwise>
</xsl:choose>

<tr><td colspan="2"></td></tr>
<xsl:variable name="reg">
	<xsl:for-each select="arr[@name = 'sistemi_prestito_interbibliotecario']/str">
		<xsl:value-of select="concat(., '-')"/>
	</xsl:for-each>
</xsl:variable>
<xsl:choose>
	<xsl:when test="string-length($reg) = 0">
		<tr>
			<td class="verdeLeft2" width="35%">Registrazione in un sistema di prestito interbibliotecario</td>
			<td class="grigioLeft1" width="65%">No</td>
		</tr>
	</xsl:when>
	<xsl:otherwise>
		<tr>
			<td class="verdeLeft2" width="35%">Registrazione in un sistema di prestito interbibliotecario</td>
			<td class="grigioLeft1" width="65%">Si</td>
		</tr>
		<tr>
			<td class="verdinoLeft2" width="35%">Denominazione</td>
			<td class="grigioLeft1" width="65%">
				<xsl:for-each select="arr[@name = 'sistemi_prestito_interbibliotecario']/str">
					<xsl:value-of select="."/><br/>
				</xsl:for-each>
			</td>
		</tr>
		
	</xsl:otherwise>
</xsl:choose>


<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>


</td></tr>
</tbody></table>

</xsl:for-each>
</xsl:template>
</xsl:stylesheet>