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
<tr><td class="verdeCenter3" colspan="4">PERSONALE - BILANCIO - DEPOSITO LEGALE</td></tr>
</tbody></table>
<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" colspan="2" width="50%">Personale</td>
	<td colspan="2" width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
<tr>
	<td class="verdinoLeft2" colspan="2" width="50%">Totale</td>
	<td class="grigioLeft1" colspan="2" width="50%">
		<xsl:choose>
			<xsl:when test="(string-length(string(int[@name = 'personale_totale'])) = 0) or (int[@name = 'personale_totale'] = 0)"><font color="Red">Non quantificato</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'personale_totale']"/></xsl:otherwise>
		</xsl:choose>
	</td>
</tr>
<tr><td colspan="2"></td></tr>
<tr>
	<td class="verdeLeft2" colspan="2" width="50%">di cui:</td>
	<td colspan="2" width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
<tr>
	
	<td class="verdinoLeft2" width="25%">Personale part-time</td>
	<td class="grigioLeft1" width="25%">
		<xsl:choose>
			<xsl:when test="(string-length(string(int[@name = 'personale_part_time'])) = 0) or (int[@name = 'personale_part_time'] = 0)"><font color="Red">Non quantificato</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'personale_part_time']"/></xsl:otherwise>
		</xsl:choose>
	</td>
	<td class="verdinoLeft2" width="25%">Personale temporaneo (obiettori, volontari, etc.)</td>
	<td class="grigioLeft1" width="25%">
		<xsl:choose>
			<xsl:when test="(string-length(string(int[@name = 'personale_temporaneo'])) = 0) or (int[@name = 'personale_temporaneo'] = 0)"><font color="Red">Non quantificato</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'personale_temporaneo']"/></xsl:otherwise>
		</xsl:choose>
	</td>
</tr>
<tr>
	<td class="verdinoLeft2" width="25%">Personale esterno</td>
	<td class="grigioLeft1" width="25%">
		<xsl:choose>
			<xsl:when test="(string-length(string(int[@name = 'personale_esterno'])) = 0) or (int[@name = 'personale_esterno'] = 0)"><font color="Red">Non quantificato</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'personale_esterno']"/></xsl:otherwise>
		</xsl:choose>
	</td>
	<td colspan="2" width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>


<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" colspan="2" width="50%">Bilancio (in euro)</td>
	<td colspan="2" width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
<tr>
	<td class="verdinoLeft2" colspan="2" width="50%">Totale uscite</td>
	<td class="grigioLeft1" colspan="2" width="50%">
		<xsl:choose>
			<xsl:when test="(string-length(string(int[@name = 'bilancio_uscite'])) = 0) or (int[@name = 'bilancio_uscite'] = 0)"><font color="Red">Non quantificato</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'bilancio_uscite']"/></xsl:otherwise>
		</xsl:choose>
	</td>
</tr>
<tr><td colspan="2"></td></tr>
<tr>
	<td class="verdeLeft2" colspan="2" width="50%">di cui:</td>
	<td colspan="2" width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
<tr>
	<td class="verdinoLeft2" width="25%">Spese per il personale<br/>(se gestito dalla biblioteca)</td>
	<td class="grigioLeft1" width="25%">
		<xsl:choose>
			<xsl:when test="(string-length(string(long[@name = 'bilancio_uscite_personale'])) = 0) or (long[@name = 'bilancio_uscite_personale'] = 0)"><font color="Red">Non quantificato</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="long[@name = 'bilancio_uscite_personale']"/></xsl:otherwise>
		</xsl:choose>
	</td>
	<td class="verdinoLeft2" width="25%">Spese correnti per il funzionamento</td>
	<td class="grigioLeft1" width="25%">
		<xsl:choose>
			<xsl:when test="(string-length(string(int[@name = 'bilancio_uscite_funzionamento'])) = 0) or (int[@name = 'bilancio_uscite_funzionamento'] = 0)"><font color="Red">Non quantificato</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'bilancio_uscite_funzionamento']"/></xsl:otherwise>
		</xsl:choose>
	</td>
</tr>
<tr>
	<td class="verdinoLeft2" width="25%">Acquisto per incremento patrimonio librario e documentario</td>
	<td class="grigioLeft1" width="25%">
		<xsl:choose>
			<xsl:when test="(string-length(string(int[@name = 'bilancio_uscite_incremento_patrimonio'])) = 0) or (int[@name = 'bilancio_uscite_incremento_patrimonio'] = 0)"><font color="Red">Non quantificato</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'bilancio_uscite_incremento_patrimonio']"/></xsl:otherwise>
		</xsl:choose>
	</td>
	<td class="verdinoLeft2" width="25%">Spese per automazione</td>
	<td class="grigioLeft1" width="25%">
		<xsl:choose>
			<xsl:when test="(string-length(string(int[@name = 'bilancio_uscite_automazione'])) = 0) or (int[@name = 'bilancio_uscite_automazione'] = 0)"><font color="Red">Non quantificato</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'bilancio_uscite_automazione']"/></xsl:otherwise>
		</xsl:choose>
	</td>
</tr>
<tr>
	<td class="verdinoLeft2" width="25%">Altre spese</td>
	<td class="grigioLeft1" width="25%">
		<xsl:choose>
			<xsl:when test="(string-length(string(int[@name = 'bilancio_uscite_varie'])) = 0) or (int[@name = 'bilancio_uscite_varie'] = 0)"><font color="Red">Non quantificato</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'bilancio_uscite_varie']"/></xsl:otherwise>
		</xsl:choose>
	</td>
	<td colspan="2" width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
<tr>
	<td class="verdinoLeft2" colspan="2" width="50%">Totale entrate</td>
	<td class="grigioLeft1" colspan="2" width="50%">
		<xsl:choose>
			<xsl:when test="(string-length(string(int[@name = 'bilancio_entrate'])) = 0) or (int[@name = 'bilancio_entrate'] = 0)"><font color="Red">Non quantificato</font></xsl:when>
			<xsl:otherwise><xsl:value-of select="int[@name = 'bilancio_entrate']"/></xsl:otherwise>
		</xsl:choose>
	</td>
</tr>
<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" colspan="2" width="50%">Deposito legale</td>
	<td colspan="2" width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
<xsl:variable name="dep">
	<xsl:for-each select="arr[@name = 'deposito_legale']/str">
		<xsl:value-of select="concat(.,'-')"/>
	</xsl:for-each>
</xsl:variable>
<xsl:choose>
	<xsl:when test="string-length($dep) = 0">
		<tr><td class="grigioLeft1" colspan="4" width="100%"><font color="Red">Dato non definito</font></td></tr>
	</xsl:when>
	<xsl:otherwise>
		<tr>
			<td class="verdinoLeft2" colspan="2" width="50%">Riceve il deposito legale</td>
			<td class="verdinoLeft2" colspan="2" width="50%">Dall'anno</td>
		</tr>
		<xsl:for-each select="arr[@name = 'deposito_legale']/str">
			<tr>
				<td class="grigioLeft1" colspan="2" width="50%"><xsl:value-of select="substring-before(., '$$$')"/></td>
				<td class="grigioLeft1" colspan="2" width="50%">
					<xsl:choose>
						<xsl:when test="string-length(substring-after(., '$$$')) = 0"><font color="Red">Dato non definito</font></xsl:when>
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