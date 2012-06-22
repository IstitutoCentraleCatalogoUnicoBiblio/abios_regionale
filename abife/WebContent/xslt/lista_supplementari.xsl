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
<tr><td class="verdeCenter3" colspan="4">INFORMAZIONI SUPPLEMENTARI</td></tr>
</tbody></table>
<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" colspan="2" width="50%">Sistemi di indicizzazione</td>
	<td colspan="2" width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
<xsl:variable name="class">
	<xsl:for-each select="arr[@name = 'indicizzazione_classificata']/str">
		<xsl:value-of select="concat(., '-')"/>
	</xsl:for-each>
</xsl:variable>
<xsl:variable name="sogg">
	<xsl:for-each select="arr[@name = 'indicizzazione_soggetto']/str">
		<xsl:value-of select="concat(., '-')"/>
	</xsl:for-each>
</xsl:variable>
<xsl:choose>
	<xsl:when test="(string-length($class) = 0) and (string-length($sogg) = 0)">
		<tr><td class="grigioLeft1" colspan="4" width="100%"><font color="Red">Dato non definito</font></td></tr>
	</xsl:when>
	<xsl:otherwise>
		<xsl:if test="string-length($class) > 0">
			<tr><td class="verdinoLeft2" colspan="2">Sistemi di indicizzazione classificata</td></tr>
			<xsl:for-each select="arr[@name = 'indicizzazione_classificata']/str">
				<tr><td class="grigioLeft1" colspan="4" width="100%"><xsl:value-of select="."/></td></tr>
			</xsl:for-each>
			<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
		</xsl:if>
		<xsl:if test="string-length($sogg) > 0">
			<tr><td class="verdinoLeft2" colspan="2">Sistemi di indicizzazione per soggetto</td></tr>
			<xsl:for-each select="arr[@name = 'indicizzazione_soggetto']/str">
				<tr><td class="grigioLeft1" colspan="4" width="100%"><xsl:value-of select="."/></td></tr>
			</xsl:for-each>
		</xsl:if>
	</xsl:otherwise>
</xsl:choose>

<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>

</tbody></table>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" colspan="2" width="50%">Norme di catalogazione</td>
	<td colspan="2" width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
<xsl:variable name="norme">
	<xsl:for-each select="arr[@name = 'norme_catalogazione']/str">
		<xsl:value-of select="concat(., '-')"/>
	</xsl:for-each>
</xsl:variable>
<xsl:choose>
	<xsl:when test="string-length($norme) = 0">
		<tr><td class="grigioLeft1" colspan="4" width="100%"><font color="Red">Dato non definito</font></td></tr>
	</xsl:when>
	<xsl:otherwise>
		<xsl:for-each select="arr[@name = 'norme_catalogazione']/str">
			<tr><td class="grigioLeft1" colspan="4" width="100%"><xsl:value-of select="."/></td></tr>
		</xsl:for-each>
	</xsl:otherwise>
</xsl:choose>

<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>

</tbody></table>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" colspan="2" width="50%">Pubblicazioni</td>
	<td colspan="2" width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
<xsl:variable name="pubbl">
	<xsl:for-each select="arr[@name = 'pubblicazioni']/str">
		<xsl:value-of select="concat(., '-')"/>
	</xsl:for-each>
</xsl:variable>
<xsl:choose>
	<xsl:when test="string-length($pubbl) = 0">
		<tr><td class="grigioLeft1" colspan="4" width="100%"><font color="Red">Dato non definito</font></td></tr>
	</xsl:when>
	<xsl:otherwise>
		<xsl:for-each select="arr[@name = 'pubblicazioni']/str">
			<tr><td class="grigioLeft1" colspan="4" width="100%"><xsl:value-of select="."/></td></tr>
		</xsl:for-each>
	</xsl:otherwise>
</xsl:choose>

<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>

</tbody></table>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" colspan="2" width="50%">Bibliografia</td>
	<td width="65%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>

<xsl:choose>
	<xsl:when test="string-length(str[@name = 'bibliografia']) = 0">
		<tr><td class="grigioLeft1" colspan="4" width="100%"><font color="Red">Dato non definito</font></td></tr>
	</xsl:when>
	<xsl:otherwise>
		<tr><td class="grigioLeft1" colspan="4" width="100%"><xsl:value-of select="str[@name = 'bibliografia']"/></td></tr>
	</xsl:otherwise>
</xsl:choose>
<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" colspan="2" width="50%">Locali</td>
	<td colspan="2" width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
<tr>
	<td class="verdinoLeft2" width="25%">Mq. superficie biblioteca</td>
	<td class="grigioLeft1" width="25%">
		<xsl:choose>
			<xsl:when test="int[@name = 'mq_totali'] = 0"><font color="Red">Dato non definito</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'mq_totali']"/></xsl:otherwise>
		</xsl:choose>
	</td>
	<td class="verdinoLeft2" width="25%">Mq. servizi / sale al pubblico</td>
	<td class="grigioLeft1" width="25%">
		<xsl:choose>
			<xsl:when test="int[@name = 'mq_pubblici'] = 0"><font color="Red">Dato non definito</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'mq_pubblici']"/></xsl:otherwise>
		</xsl:choose>
	</td>
</tr>
<tr>
	<td class="verdinoLeft2" width="25%">Metri lineari scaffali magazzini</td>
	<td class="grigioLeft1" width="25%">
		<xsl:choose>
			<xsl:when test="int[@name = 'ml_magazzini'] = 0"><font color="Red">Dato non definito</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'ml_magazzini']"/></xsl:otherwise>
		</xsl:choose>
	</td>
	<td class="verdinoLeft2" width="25%">Metri lineari scaffali aperti</td>
	<td class="grigioLeft1" width="25%">
		<xsl:choose>
			<xsl:when test="int[@name = 'ml_aperti'] = 0"><font color="Red">Dato non definito</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'ml_aperti']"/></xsl:otherwise>
		</xsl:choose>
	</td>
</tr>
<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>

</tbody></table>
<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" colspan="2" width="50%">Postazioni</td>
	<td colspan="2" width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
<tr>
	<td class="verdinoLeft2" colspan="2" width="50%">Posti di lettura</td>
	<td class="grigioLeft1" colspan="2" width="50%">
		<xsl:choose>
			<xsl:when test="int[@name = 'posti_lettura'] = 0"><font color="Red">Dato non definito</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'posti_lettura']"/></xsl:otherwise>
		</xsl:choose>
	</td>
</tr>
<tr>
	<td class="verdinoLeft2" colspan="2" width="50%">Posti attrezzati video</td>
	<td class="grigioLeft1" colspan="2" width="50%">
		<xsl:choose>
			<xsl:when test="int[@name = 'posti_video'] = 0"><font color="Red">Dato non definito</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'posti_video']"/></xsl:otherwise>
		</xsl:choose>
	</td>
</tr>
<tr>
	<td class="verdinoLeft2" colspan="2" width="50%">Posti attrezzati ascolto</td>
	<td class="grigioLeft1" colspan="2" width="50%">
		<xsl:choose>
			<xsl:when test="int[@name = 'posti_audio'] = 0"><font color="Red">Dato non definito</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'posti_audio']"/></xsl:otherwise>
		</xsl:choose>
	</td>
</tr>
<tr>
	<td class="verdinoLeft2" colspan="2" width="50%">Posti attrezzati internet</td>
	<td class="grigioLeft1" colspan="2" width="50%">
		<xsl:choose>
			<xsl:when test="int[@name = 'posti_internet'] = 0"><font color="Red">Dato non definito</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'posti_internet']"/></xsl:otherwise>
		</xsl:choose>
	</td>
</tr>
<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>

</tbody></table>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" colspan="2" width="50%">Utenti</td>
	<td colspan="2" width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
<tr>
	<td class="verdinoLeft2" width="25%">Ingressi registrati ultimi 12 mesi</td>
	<td class="grigioLeft1" width="25%">
		<xsl:choose>
			<xsl:when test="int[@name = 'utenti'] = 0"><font color="Red">Dato non definito</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'utenti']"/></xsl:otherwise>
		</xsl:choose>
	</td>
	<td class="verdinoLeft2" width="25%">Iscritti prestito ultimi 12 mesi</td>
	<td class="grigioLeft1" width="25%">
		<xsl:choose>
			<xsl:when test="int[@name = 'utenti_iscritti'] = 0"><font color="Red">Dato non definito</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'utenti_iscritti']"/></xsl:otherwise>
		</xsl:choose>
	</td>
</tr>
<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>

</tbody></table>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" colspan="2" width="50%">Comunicazioni</td>
	<td width="65%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
<xsl:choose>
	<xsl:when test="string-length(str[@name = 'comunicazioni']) = 0">
		<tr><td class="grigioLeft1" colspan="4" width="100%"><font color="Red">Dato non definito</font></td></tr>
	</xsl:when>
	<xsl:otherwise>
		<tr><td class="grigioLeft1" colspan="4" width="100%"><xsl:value-of select="str[@name = 'comunicazioni']"/></td></tr>
	</xsl:otherwise>
</xsl:choose>

<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>

</tbody></table>

</td></tr>
</tbody></table>

</xsl:for-each>
</xsl:template>
</xsl:stylesheet>