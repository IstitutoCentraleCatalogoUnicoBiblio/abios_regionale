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
<tr><td class="verdeCenter3" colspan="4">SEZIONI SPECIALI - SERVIZI</td></tr>
</tbody></table>
<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" colspan="2" width="50%">Sezioni speciali</td>
	<td width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
</tbody></table>
<xsl:variable name="speciali">
	<xsl:for-each select="arr[@name = 'sezioni_speciali']/str">
		<xsl:value-of select="concat(., '-')"/>
	</xsl:for-each>
</xsl:variable>
<table width="100%" border="0">
<tbody>
<xsl:choose>
	<xsl:when test="string-length($speciali) = 0">
		<tr><td class="grigioLeft1" colspan="4" width="100%"><font color="Red">Dato non definito</font></td></tr>
	</xsl:when>
	<xsl:otherwise>
		<xsl:for-each select="arr[@name = 'sezioni_speciali']/str">
			<tr><td class="grigioLeft1" colspan="4" width="100%"><xsl:value-of select="."/></td></tr>
		</xsl:for-each>
	</xsl:otherwise>
</xsl:choose>
<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>

</tbody></table>

<xsl:variable name="ripro">
	<xsl:for-each select="arr[@name = 'info_riproduzioni']/str">
		<xsl:value-of select="concat(., '-')"/>
	</xsl:for-each>
</xsl:variable>
<xsl:choose>
	<xsl:when test="string-length($ripro) = 0">
		<table width="100%" border="0">
		<tbody>		
			<tr>
				<td class="verdeLeft3" width="50%">Riproduzioni - Forniture documenti</td>
				<td width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
			</tr>
			<tr><td class="grigioLeft1"><font color="Red">Dato non definito</font></td></tr>
			<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
		</tbody></table>
	</xsl:when>
	<xsl:otherwise>
		<table width="100%" border="0">
		<tbody>		
			<tr>
				<td class="verdeLeft3" width="50%">Riproduzioni - Forniture documenti</td>
				<td width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
			</tr>
			
		</tbody></table>
		<table width="100%" border="0">
		<tbody>
			<tr>
				<td class="verdinoLeft2" width="25%">Servizio</td>
				<td class="verdinoLeft2" width="25%">Locale</td>
				<td class="verdinoLeft2" width="25%">Nazionale</td>
				<td class="verdinoLeft2" width="25%">Internazionale</td>
			</tr>
			<xsl:for-each select="arr[@name = 'info_riproduzioni']/str">
				<tr>
					<td class="grigioLeft1" width="25%">
						<xsl:choose>
							<xsl:when test="string-length(substring-before(substring-after(., 'modo:'), ',')) = 0"><font color="Red">Dato non definito</font></xsl:when>
							<xsl:otherwise><xsl:value-of select="substring-before(substring-after(., 'modo:'), ',')"/></xsl:otherwise>
						</xsl:choose>
					</td>					
					<td class="grigioLeft1" width="25%">
						<xsl:choose>
							<xsl:when test="string-length(substring-before(substring-after(., 'locale:'), ',')) = 0"><font color="Red">Dato non definito</font></xsl:when>
							<xsl:otherwise><xsl:value-of select="substring-before(substring-after(., 'locale:'), ',')"/></xsl:otherwise>
						</xsl:choose>
					</td>
					<td class="grigioLeft1" width="25%">
						<xsl:choose>
							<xsl:when test="string-length(substring-before(substring-after(., 'nazionale:'), ',')) = 0"><font color="Red">Dato non definito</font></xsl:when>
							<xsl:otherwise><xsl:value-of select="substring-before(substring-after(., 'nazionale:'), ',')"/></xsl:otherwise>
						</xsl:choose>					
					</td>
					<td class="grigioLeft1" width="25%">
						<xsl:choose>
							<xsl:when test="string-length(substring-after(., 'internazionale:')) = 0"><font color="Red">Dato non definito</font></xsl:when>
							<xsl:otherwise><xsl:value-of select="substring-after(., 'internazionale:')"/></xsl:otherwise>
						</xsl:choose>
					</td>
				</tr>
			</xsl:for-each>
			<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
		</tbody></table>
	</xsl:otherwise>
</xsl:choose>

<xsl:variable name="modalita">
	<xsl:for-each select="arr[@name = 'servizi_informazioni_bibliografiche_modalita']/str">
		<xsl:value-of select="concat(., '-')"/>
	</xsl:for-each>
</xsl:variable>
<table width="100%" border="0">
<tbody>		
<tr>
	<td class="verdeLeft3" width="50%">Informazioni bibliografiche</td>
	<td width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
			
</tbody></table>
<table width="100%" border="0">
<tbody>
	<tr>
		<td class="verdinoLeft2" width="25%">Servizio in sede</td>
		<td class="grigioLeft1" width="25%">
		<xsl:choose>
			<xsl:when test="string-length(string(bool[@name = 'gestisce_servizio_bibliografico_interno'])) = 0">
				<font color="Red">Dato non definito</font>
			</xsl:when>
			<xsl:otherwise>
				<xsl:choose>
					<xsl:when test="bool[@name = 'gestisce_servizio_bibliografico_interno'] = 'true'">Si</xsl:when>
					<xsl:otherwise>No</xsl:otherwise>
				</xsl:choose>
			</xsl:otherwise>
		</xsl:choose>
		</td>
		<td class="verdinoLeft2" width="25%">Servizio esterno</td>
		<td class="grigioLeft1" width="25%">
		<xsl:choose>
			<xsl:when test="string-length(string(bool[@name = 'gestisce_servizio_bibliografico_esterno'])) = 0">
				<font color="Red">Dato non definito</font>
			</xsl:when>
			<xsl:otherwise>
				<xsl:choose>
					<xsl:when test="bool[@name = 'gestisce_servizio_bibliografico_esterno'] = 'true'">Si</xsl:when>
					<xsl:otherwise>No</xsl:otherwise>
				</xsl:choose>
			</xsl:otherwise>
		</xsl:choose>
		</td>
	</tr>
	<xsl:variable name="modalita">
		<xsl:for-each select="arr[@name = 'servizi_informazioni_bibliografiche_modalita']/str">
			<xsl:value-of select="concat(., '-')"/>
		</xsl:for-each>
	</xsl:variable>
	<xsl:choose>
		<xsl:when test="string-length($modalita) = 0">
			<tr>
				<td class="verdinoLeft2" width="25%">Modalità di comunicazione</td>
				<td class="grigioLeft1" width="25%"><font color="Red">Dato non definito</font></td>
			</tr>
		</xsl:when>
		<xsl:otherwise>
			<tr>
				<td class="verdinoLeft2" colspan="2" width="50%">Modalità di comunicazione</td>
			</tr>
			<xsl:for-each select="arr[@name = 'servizi_informazioni_bibliografiche_modalita']/str">
				<tr>
					<td class="grigioLeft1" colspan="2" width="50%"><xsl:value-of select="."/></td>
				</tr>
			</xsl:for-each>
		</xsl:otherwise>
	</xsl:choose>
	<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>

<table width="100%" border="0">
<tbody>		
<tr>
	<td class="verdeLeft3" width="50%">Accesso internet</td>
	<td width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
			
</tbody></table>
<table width="100%" border="0">
<tbody>
	<tr>
		<td class="verdinoLeft2" colspan="2" width="50%">Descrizione</td>
		<td class="grigioLeft1" colspan="2" width="50%">
			<xsl:choose>
				<xsl:when test="(string-length(string(bool[@name = 'accesso_internet_pagamento'])) = 0) and (string-length(string(bool[@name = 'accesso_internet_tempo'])) = 0) and (string-length(string(bool[@name = 'accesso_internet_proxy'])) = 0)">
					<font color="Red">Dato non definito</font>
				</xsl:when>
				<xsl:otherwise>
					<xsl:choose>
						<xsl:when test="string-length(string(bool[@name = 'accesso_internet_pagamento'])) > 0">A pagamento</xsl:when>
						<xsl:when test="string-length(string(bool[@name = 'accesso_internet_tempo'])) > 0">A tempo</xsl:when>
						<xsl:otherwise>
							<xsl:choose>
								<xsl:when test="bool[@name = 'accesso_internet_proxy'] = 'true'">Selezionato</xsl:when>
								<xsl:otherwise>Libero</xsl:otherwise>
							</xsl:choose>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:otherwise>
			</xsl:choose>
		</td>
	</tr>
</tbody></table>


</td></tr>
</tbody></table>

</xsl:for-each>
</xsl:template>
</xsl:stylesheet>