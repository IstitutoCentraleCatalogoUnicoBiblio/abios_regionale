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
<tr><td class="verdeCenter3" colspan="2">TIPOLOGIA AMMINISTRATIVA E FUNZIONALE</td></tr>
<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>
<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" colspan="1" width="25%">Ente di appartenenza</td>
</tr>
<tr>
	<td class="verdinoLeft2" width="25%">Denominazione</td>
    <td class="grigioLeft1" width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:value-of select="str[@name = 'ente_denominazione']"/></td>
</tr>
<tr>
	<td class="verdinoLeft2" width="25%">Tipologia amministrativa</td>
    <td class="grigioLeft1" width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:value-of select="str[@name = 'tipologia_amministrativa']"/></td>
</tr>

<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" width="25%">Autonomia amministrativa</td>
	<td class="grigioLeft1" width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>
		<xsl:if test="string-length(string(bool[@name = 'autonomia_amministrativa'])) = 0">
			<font color="Red">Dato non definito</font>
		</xsl:if>
		<xsl:if test="string-length(string(bool[@name = 'autonomia_amministrativa'])) > 0">
			<xsl:choose>
				<xsl:when test="bool[@name = 'autonomia_amministrativa'] = 'true'">Si</xsl:when>
				<xsl:otherwise>NO</xsl:otherwise>
			</xsl:choose> 
		</xsl:if>
	</td>
</tr>
<xsl:if test="string-length(str[@name = 'struttura_gerarchica_sovraordinata']) > 0">
	<tr>
		<td class="verdinoLeft2" width="25%">Struttura gerarchica sovraordinata</td>
		<td class="grigioLeft1" width="75%"><xsl:value-of select="str[@name = 'struttura_gerarchica_sovraordinata']"/><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
	</tr>
</xsl:if>

<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" width="25%">Tipologia funzionale</td>
	<td class="grigioLeft1" width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>
		<xsl:value-of select="str[@name = 'tipologia_funzionale']"/>
	</td>
</tr>

<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" width="25%" colspan="1">Fondazione della biblioteca</td>
</tr>
<tr>
	<td class="verdinoLeft2" width="25%">Data di fondazione</td>
    <td class="grigioLeft1" width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>
    <xsl:choose>
    	<xsl:when test="string-length(str[@name = 'data_fondazione']) = 0"><font color="Red">Dato non definito</font></xsl:when>
    	<xsl:otherwise><xsl:value-of select="str[@name = 'data_fondazione']"/></xsl:otherwise>
    </xsl:choose>
    </td>
</tr>
<tr>
	<td class="verdinoLeft2" width="25%">Data istituzione attuale</td>
    <td class="grigioLeft1" width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:value-of select="str[@name = 'data_istituzione']"/></td>
</tr>

<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>

</td></tr>
</tbody></table>

</xsl:for-each>
</xsl:template>
</xsl:stylesheet>