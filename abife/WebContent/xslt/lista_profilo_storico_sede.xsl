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
<tr><td class="verdeCenter3" colspan="2">PROFILO STORICO E SEDE - SISTEMI DI BIBLIOTECHE</td></tr>
<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" colspan="1" width="40%">Sede</td>
</tr>
<tr>
	<td class="verdinoLeft2" width="40%">Edificio monumentale</td>
    <td class="grigioLeft1" width="60%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:if test="string-length(string(bool[@name = 'edificio_monumentale'])) = 0"><font color="Red">Dato non definito</font></xsl:if>
	<xsl:if test="string-length(string(bool[@name = 'edificio_monumentale'])) > 0">
		<xsl:choose>
			<xsl:when test="bool[@name = 'edificio_monumentale'] = 'true'">SI</xsl:when>
			<xsl:otherwise>NO</xsl:otherwise>
		</xsl:choose> 
	</xsl:if>
	</td>
</tr>
<tr>
	<td class="verdinoLeft2" width="40%">Denominazione dell'edificio</td>
    <td class="grigioLeft1" width="60%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:if test="string-length(str[@name = 'edificio_denominazione']) = 0"><font color="Red">Dato non definito</font></xsl:if>
    <xsl:if test="string-length(str[@name = 'edificio_denominazione']) > 0"><xsl:value-of select="str[@name = 'edificio_denominazione']"/></xsl:if></td>
</tr>
<tr>
	<td class="verdinoLeft2" width="40%">Edificio appositamente costruito</td>

    <td class="grigioLeft1" width="60%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:if test="string-length(string(bool[@name = 'edificio_appositamente_costruito'])) = 0"><font color="Red">Dato non definito</font></xsl:if>
	<xsl:if test="string-length(string(bool[@name = 'edificio_appositamente_costruito'])) > 0">
		<xsl:choose>
			<xsl:when test="bool[@name = 'edificio_appositamente_costruito'] = 'true'">SI</xsl:when>
			<xsl:otherwise>NO</xsl:otherwise>
		</xsl:choose> 
	</xsl:if>
	</td>
</tr>
<tr>
	<td class="verdinoLeft2" width="40%">Data</td>
    <td class="grigioLeft1" width="60%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>
    <xsl:if test="string-length(str[@name = 'edificio_data_costruzione']) = 0">
    	<font color="Red">Dato non definito</font>
    </xsl:if>
    <xsl:if test="string-length(str[@name = 'edificio_data_costruzione']) > 0">
    	<xsl:choose>
    		<xsl:when test="string-length(str[@name = 'edificio_data_costruzione']) = 2">
    			<xsl:value-of select="concat('Secolo ', str[@name = 'edificio_data_costruzione'])"/>
    		</xsl:when>
    		<xsl:otherwise>
    			<xsl:value-of select="str[@name = 'edificio_data_costruzione']"/>
    		</xsl:otherwise>
    	</xsl:choose>
    	
    </xsl:if>
    </td>
</tr>

<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>

<table width="100%" border="0" height="10">
<tbody>
<xsl:variable name="sistemi_biblioteche">
	<xsl:for-each select="arr[@name = 'sistemi_biblioteche']/str">
		<xsl:value-of select="concat(., ';')"/>
	</xsl:for-each>
</xsl:variable>

<xsl:if test="string-length($sistemi_biblioteche) > 0">
<tr>
	<td class="verdeLeft3" width="40%">Collegamenti con altre biblioteche</td>
	<td width="60%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>	
<tr>
	<td class="verdinoLeft2" width="40%">Partecipa a sistemi di biblioteche</td>
	<td width="60%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>	

<!-- Se c'è più di un'occorrenza della stringa 'sistemi_biblioteche', crea più righe -->
<xsl:for-each select="arr[@name = 'sistemi_biblioteche']/str">
<tr>
	<td class="grigioLeft1" colspan="2" width="100%"><xsl:value-of select="."/></td>
</tr>		
</xsl:for-each>

<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</xsl:if>
</tbody></table>


</td></tr>
</tbody></table>

</xsl:for-each>
</xsl:template>
</xsl:stylesheet>