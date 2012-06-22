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
<tr><td class="verdeCenter3" colspan="4">ACCESSO - DESTINAZIONE SOCIALE</td></tr>
</tbody></table>
<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
<table width="100%" border="0">
<tbody>
<tr>
	<td colspan="1" width="25%"></td>
	<td class="grigioCenter3" colspan="2" width="50%">
		<xsl:choose>
			<xsl:when test="string-length(string(bool[@name = 'accesso_riservato'])) = 0">
				<font color="Red">Dato non definito</font>
			</xsl:when>
			<xsl:otherwise>
				<xsl:choose>
					<xsl:when test="bool[@name = 'accesso_riservato'] = 'true'">Riservata</xsl:when>
					<xsl:otherwise>Aperta a tutti</xsl:otherwise>
				</xsl:choose>
			</xsl:otherwise>
		</xsl:choose>
	</td>
	<td colspan="1" width="25%"></td>
</tr>

<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" colspan="2" width="50%">Accessibilità portatori di handicap</td>
    <td class="grigioLeft1" colspan="2" width="50%">
    	<xsl:choose>
    		<xsl:when test="string-length(string(bool[@name = 'accesso_handicap'])) = 0">
    			<font color="Red">Dato non definito</font>
    		</xsl:when>
    		<xsl:otherwise>
    			<xsl:choose>
					<xsl:when test="bool[@name = 'accesso_handicap'] = 'true'">Si</xsl:when>
					<xsl:otherwise>No</xsl:otherwise>
				</xsl:choose>
    		</xsl:otherwise>
    	</xsl:choose>									
	</td>
</tr>

<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" colspan="2" width="50%">Modalità di accesso</td>
	<td colspan="2" width="50%"></td>
</tr>
<tr>
	<td class="grigioLeft1" colspan="4">Limite di età:<xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:if test="(string-length(string(int[@name = 'accesso_limite_eta_min'])) = 0) and (string-length(string(int[@name = 'accesso_limite_eta_max'])) = 0)"><font color="Red">Dato non definito</font></xsl:if>	
		<xsl:if test="string-length(string(int[@name = 'accesso_limite_eta_min'])) > 0">maggiore di <xsl:value-of select="int[@name = 'accesso_limite_eta_min']"/> anni <xsl:if test="string-length(string(int[@name = 'accesso_limite_eta_max'])) > 0">e minore di <xsl:value-of select="int[@name = 'accesso_limite_eta_max']"/> anni</xsl:if></xsl:if>	
		<xsl:if test="(string-length(string(int[@name = 'accesso_limite_eta_min'])) = 0) and (string-length(string(int[@name = 'accesso_limite_eta_max'])) > 0)">minore di <xsl:value-of select="int[@name = 'accesso_limite_eta_max']"/> anni</xsl:if>
	</td>
</tr>
<tr>
	<td class="verdinoLeft2" colspan="2" width="50%">Condizioni per l'accesso</td>
</tr>

<xsl:variable name="accesso_mod">
	<xsl:for-each select="arr[@name = 'accesso_modalita']/str">
		<xsl:value-of select="concat(., ';')"/>
	</xsl:for-each>
</xsl:variable>

<xsl:choose>
	<xsl:when test="string-length($accesso_mod) = 0">
		<tr>
			<td class="grigioLeft1" colspan="4"><font color="Red">Dato non definito</font></td>
		</tr>
	</xsl:when>
	<xsl:otherwise>
		<xsl:for-each select="arr[@name = 'accesso_modalita']/str">
			<tr><td class="grigioLeft1" colspan="4"><xsl:value-of select="."/></td></tr>
		</xsl:for-each>
	</xsl:otherwise>
</xsl:choose>

<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" colspan="2" width="50%">Destinazione sociale</td>
	<td colspan="2" width="50%"></td>
</tr>

<xsl:variable name="dest_soc">
	<xsl:for-each select="arr[@name = 'destinazioni_sociali_display']/str">
		<xsl:value-of select="concat(., ';')"/>
	</xsl:for-each>
</xsl:variable>

<xsl:choose>
	<xsl:when test="string-length($dest_soc) = 0">
		<tr><td class="grigioLeft1" colspan="4"><font color="Red">Dato non definito</font></td></tr>
	</xsl:when>
	<xsl:otherwise>
		<tr>
			<td class="verdinoLeft2" colspan="2" width="50%">Descrizione</td>
			<td class="verdinoLeft2" colspan="2" width="50%">Note</td>
		</tr>
		<xsl:for-each select="arr[@name = 'destinazioni_sociali_display']/str">
			<tr>
				<td class="grigioLeft1" colspan="2" width="50%"><xsl:value-of select="substring-before(.,'$$$')"/></td>
				<td class="grigioLeft1" colspan="2" width="50%">
					<xsl:choose>
						<xsl:when test="string-length(substring-after(.,'$$$')) = 0"><font color="Red">Dato non definito</font></xsl:when>
						<xsl:otherwise><xsl:value-of select="substring-after(.,'$$$')"/></xsl:otherwise>
					</xsl:choose>
				</td>
			</tr>
		</xsl:for-each>
	</xsl:otherwise>
</xsl:choose>

<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" colspan="2" width="50%">Regolamento</td>
	<td colspan="2" width="50%"></td>
</tr>

<xsl:variable name="reg">
	<xsl:for-each select="arr[@name = 'regolamento']/str">
		<xsl:value-of select="concat(., ';')"/>
	</xsl:for-each>
</xsl:variable>

<xsl:choose>
	<xsl:when test="string-length($reg) = 0">
		<tr><td class="grigioLeft1" colspan="4"><font color="Red">Dato non definito</font></td></tr>		
	</xsl:when>
	<xsl:otherwise>
		<tr>
			<td class="verdinoLeft2" colspan="2" width="50%">Riferimento normativo</td>
			<td class="verdinoLeft2" colspan="2" width="50%">URL</td>
		</tr>
		<xsl:for-each select="arr[@name = 'regolamento']/str">
			<tr>
				<td class="grigioLeft1" colspan="2" width="50%">
					<xsl:choose>
						<xsl:when test="string-length(substring-before(.,'$$$')) = 0"><font color="Red">Dato non definito</font></xsl:when>
						<xsl:otherwise><xsl:value-of select="substring-before(., '$$$')"/></xsl:otherwise>
					</xsl:choose>
				</td>			
				<td class="grigioLeft1" colspan="2" width="50%">
					<xsl:choose>
						<xsl:when test="string-length(substring-after(.,'$$$')) = 0"><font color="Red">Dato non definito</font></xsl:when>
						<xsl:otherwise><xsl:value-of select="substring-after(., '$$$')"/></xsl:otherwise>
					</xsl:choose>
				</td>			
			</tr>
		</xsl:for-each>
	</xsl:otherwise>
</xsl:choose>

<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>


</td></tr>
</tbody></table>

</xsl:for-each>
</xsl:template>
</xsl:stylesheet>