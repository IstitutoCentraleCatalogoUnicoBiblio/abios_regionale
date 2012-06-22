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
<tr><td class="verdeCenter3" colspan="2">DATI ANAGRAFICI</td></tr>
</tbody></table>
<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
<table width="100%" border="0">
<tbody>
	<tr>
		<td class="verdeLeft3" colspan="1" width="25%">Denominazione</td>
	</tr>
	<tr>
		<td class="grigioLeft1" colspan="2" width="100%"><xsl:value-of select="str[@name = 'denominazione_ufficiale']"/></td>
	</tr>
	<xsl:variable name="denominazioni_precedenti">
		<xsl:for-each select="arr[@name = 'denominazioni_precedenti']/str">
			<xsl:value-of select="concat(., ';')"/>
		</xsl:for-each>
	</xsl:variable>
	
	<xsl:variable name="denominazioni_alternative">
		<xsl:for-each select="arr[@name = 'denominazioni_alternative']/str">
			<xsl:value-of select="concat(., ';')"/>
		</xsl:for-each>
	</xsl:variable>

	<xsl:if test="string-length($denominazioni_precedenti) > 0">
		<tr><td class="verdinoLeft2" colspan="1" width="25%">Già</td></tr>
	
		<xsl:for-each select="arr[@name = 'denominazioni_precedenti']/str">
			<tr><td class="grigioLeft1" colspan="2" width="100%"><xsl:value-of select="."/></td></tr>
		</xsl:for-each>	
	</xsl:if>

	<xsl:if test="string-length($denominazioni_alternative) > 0">
		<tr><td class="verdinoLeft2" colspan="1" width="25%">Anche</td></tr>

		<xsl:for-each select="arr[@name = 'denominazioni_alternative']/str">
			<tr><td class="grigioLeft1" colspan="2" width="100%"><xsl:value-of select="."/></td></tr>
		</xsl:for-each>	
	</xsl:if>
	<tr><td colspan="2"></td></tr>
	<tr>
		<td class="verdeLeft3" width="25%">Codice Fiscale</td>
	
		<td class="grigioLeft1" width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:value-of select="str[@name = 'codice_fiscale']"/></td>
	</tr>
	<tr><td colspan="2"></td></tr>
	<tr>
		<td class="verdeLeft3">Partita IVA</td>
		<td class="grigioLeft1"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:value-of select="str[@name = 'partita_iva']"/></td>
	</tr>
	<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>

<table width="100%" border="0">
<tbody>
<tr>
	<td class="verdeLeft3" colspan="1" width="25%">Indirizzo</td>
</tr>
<tr>
	<td class="verdinoLeft2" width="25%">Via/Piazza</td>

    <td class="grigioLeft1" width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:value-of select="str[@name = 'indirizzo']"/></td>
</tr>
<tr>
	<td class="verdinoLeft2">Frazione</td>
    <td class="grigioLeft1"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:value-of select="str[@name = 'frazione']"/></td>
</tr>
<tr>
	<td class="verdinoLeft2">C.A.P.</td>
    <td class="grigioLeft1"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:value-of select="str[@name = 'cap']"/></td>

</tr>
<tr>
	<td class="verdinoLeft2">Comune</td>
    <td class="grigioLeft1"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:value-of select="str[@name = 'comune']"/></td>
</tr>
<tr>
	<td class="verdinoLeft2">Provincia</td>
    <td class="grigioLeft1"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:value-of select="str[@name = 'provincia']"/></td>
</tr>

<tr>
	<td class="verdinoLeft2">Regione</td>
	<td class="grigioLeft1"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:value-of select="str[@name = 'regione']"/></td>
</tr>

<xsl:if test="(double[@name = 'latitudine'] != 0.0) and (double[@name = 'longitudine'] != 0.0)">
<tr>	
	<td class="verdinoLeft2" colspan="1" width="25%">
		<a>
			<xsl:attribute name="href">#</xsl:attribute>
			<xsl:attribute name="onclick">
        		viewMap('<xsl:value-of select="str[@name = 'id_biblioteca']"/>');
      		</xsl:attribute>
      		Mappa
		</a>
	</td>
</tr>
<tr>
	<td class="verdinoLeft2" colspan="1" width="25%">
		<a>
			<xsl:attribute name="href">#</xsl:attribute>
			<xsl:attribute name="onclick">
        		viewListaComuneMap('<xsl:value-of select="int[@name = 'id_comune']"/>');
      		</xsl:attribute>
      		Mappa biblioteche comune
		</a>
	</td>
</tr>
</xsl:if>

<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>

<table width="100%" border="0">
<tbody>
<xsl:variable name="telefono">
	<xsl:for-each select="arr[@name = 'contatti']/str">
		<xsl:if test="substring-before(.,'$$$') = 'Telefono'">
			<xsl:value-of select="concat(substring-after(.,'$$$'), ';')"/>
		</xsl:if>
	</xsl:for-each>
</xsl:variable>

<xsl:variable name="fax">
	<xsl:for-each select="arr[@name = 'contatti']/str">
		<xsl:if test="substring-before(.,'$$$') = 'Fax'">
			<xsl:value-of select="concat(substring-after(.,'$$$'), ';')"/>
		</xsl:if>
	</xsl:for-each>
</xsl:variable>

<xsl:variable name="email">
	<xsl:for-each select="arr[@name = 'contatti']/str">
		<xsl:if test="substring-before(.,'$$$') = 'Email'">
			<xsl:value-of select="concat(substring-after(.,'$$$'), ';')"/>
		</xsl:if>
	</xsl:for-each>
</xsl:variable>

<xsl:variable name="url">
	<xsl:for-each select="arr[@name = 'contatti']/str">
		<xsl:if test="substring-before(.,'$$$') = 'Url'">
			<xsl:value-of select="concat(substring-after(.,'$$$'), ';')"/>
		</xsl:if>
	</xsl:for-each>
</xsl:variable>



<xsl:if test="(string-length($telefono) > 0) or (string-length($fax) > 0) or (string-length($email) > 0) or (string-length($url) > 0)">
<tr>
	<td class="verdeLeft3" width="25%">Recapiti</td>
	<td width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
	
<xsl:if test="string-length($telefono) > 0">
<tr>
	<td colspan="1" class="verdinoLeft2" width="25%">Telefono</td>
</tr>	
</xsl:if>
<!-- Se c'è più di un'occorrenza della stringa 'Telefono', crea più righe -->
<xsl:for-each select="arr[@name = 'contatti']/str">
<xsl:if test="substring-before(.,'$$$') = 'Telefono'">
	<tr>
		<td class="grigioLeft1" colspan="2">
			<xsl:choose>
				<xsl:when test="string-length(substring-after(., ';')) = 0"><xsl:value-of select="substring-before(substring-after(.,'$$$'), ';')"/></xsl:when>
				<xsl:otherwise><xsl:value-of select="substring-before(substring-after(.,'$$$'), ';')"/><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>(<xsl:value-of select="substring-after(.,'note:')"/>)</xsl:otherwise>
			</xsl:choose>
		</td>
	</tr>		
</xsl:if>
</xsl:for-each>
	
<xsl:if test="string-length($fax) > 0">
	<tr>
		<td colspan="1" class="verdinoLeft2" width="25%">Fax</td>
	</tr>	
</xsl:if>
<!-- Se c'è più di un'occorrenza della stringa 'Fax', crea più righe -->
<xsl:for-each select="arr[@name = 'contatti']/str">
<xsl:if test="substring-before(.,'$$$') = 'Fax'">
	<tr>
		<td class="grigioLeft1" colspan="2">
			<xsl:choose>
				<xsl:when test="string-length(substring-after(., ';')) = 0"><xsl:value-of select="substring-before(substring-after(.,'$$$'), ';')"/></xsl:when>
				<xsl:otherwise><xsl:value-of select="substring-before(substring-after(.,'$$$'), ';')"/><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>(<xsl:value-of select="substring-after(.,'note:')"/>)</xsl:otherwise>
			</xsl:choose>
		</td>
	</tr>
</xsl:if>
</xsl:for-each>	

<xsl:if test="string-length($email) > 0">
<tr>
	<td colspan="1" class="verdinoLeft2" width="25%">E-mail</td>
</tr>
<!-- Se c'è più di un'occorrenza della stringa 'Email', crea più righe -->
<xsl:for-each select="arr[@name = 'contatti']/str">
	<xsl:if test="substring-before(.,'$$$') = 'Email'">
		<tr>
			<td class="grigioLeft1" colspan="2">
				<xsl:choose>
					<xsl:when test="string-length(substring-after(., ';')) = 0">
						<a><xsl:attribute name="href"><xsl:value-of select="concat('mailto:',substring-before(substring-after(., '$$$'), ';'))"/></xsl:attribute><xsl:value-of select="substring-before(substring-after(., '$$$'), ';')"/></a>
					</xsl:when>
					<xsl:otherwise>
						<a><xsl:attribute name="href"><xsl:value-of select="concat('mailto:',substring-before(substring-after(., '$$$'), ';'))"/></xsl:attribute><xsl:value-of select="substring-before(substring-after(., '$$$'), ';')"/></a><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>(<xsl:value-of select="substring-after(., 'note:')"/>)
					</xsl:otherwise>
				</xsl:choose>
			</td>
		</tr>
	</xsl:if>
</xsl:for-each>
</xsl:if>
	
<xsl:if test="string-length($url) > 0">
	<tr>
		<td colspan="1" class="verdinoLeft2" width="25%">Url</td>
	</tr>
	<!-- Se c'è più di un'occorrenza della stringa 'Url', crea più righe -->
	<xsl:for-each select="arr[@name = 'contatti']/str">
		<xsl:if test="substring-before(.,'$$$') = 'Url'">
			<tr>
				<td class="grigioLeft1" colspan="2">
					<xsl:choose>
						<xsl:when test="string-length(substring-after(., ';')) = 0">
							<a target="_blank"><xsl:attribute name="href"><xsl:value-of select="substring-before(substring-after(., '$$$'), ';')"/></xsl:attribute><xsl:value-of select="substring-before(substring-after(., '$$$'), ';')"/></a>
						</xsl:when>
						<xsl:otherwise>
							<a target="_blank"><xsl:attribute name="href"><xsl:value-of select="substring-before(substring-after(., '$$$'), ';')"/></xsl:attribute><xsl:value-of select="substring-before(substring-after(., '$$$'), ';')"/></a><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>(<xsl:value-of select="substring-after(., 'note:')"/>)
						</xsl:otherwise>
					</xsl:choose>
				</td>
			</tr>
		</xsl:if>
	</xsl:for-each>
</xsl:if>

<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</xsl:if>
</tbody></table>

<table width="100%" border="0">
<tbody>
	<xsl:if test="(string-length(str[@name = 'sbn']) > 0) or (string-length(str[@name = 'rism']) > 0) or (string-length(str[@name = 'acnp']) > 0) or (string-length(str[@name = 'cei']) > 0) or (string-length(str[@name = 'cmbs']) > 0)">
		<tr>
			<td class="verdeLeft3" width="25%">Atri codici identificativi</td>
			<td width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
		</tr>

		<xsl:if test="string-length(str[@name = 'sbn']) > 0">
		<tr>
			<td class="verdinoLeft2" width="25%">SBN</td>
		
		    <td class="grigioLeft1" width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:value-of select="str[@name = 'sbn']"/></td>
		</tr>
		</xsl:if>
		<xsl:if test="string-length(str[@name = 'rism']) > 0">
		<tr>
			<td class="verdinoLeft2">RISM</td>
		    <td class="grigioLeft1"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:value-of select="str[@name = 'rism']"/></td>
		</tr>
		</xsl:if>
		<xsl:if test="string-length(str[@name = 'acnp']) > 0">
		<tr>
			<td class="verdinoLeft2">ACNP</td>
		    <td class="grigioLeft1"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:value-of select="str[@name = 'acnp']"/></td>
		
		</tr>
		</xsl:if>
		<xsl:if test="string-length(str[@name = 'cei']) > 0">
		<tr>
			<td class="verdinoLeft2">CEI</td>
		    <td class="grigioLeft1"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:value-of select="str[@name = 'cei']"/></td>
		</tr>
		</xsl:if>
		<xsl:if test="string-length(str[@name = 'cmbs']) > 0">
		<tr>
			<td class="verdinoLeft2">CMBS</td>
		    <td class="grigioLeft1"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:value-of select="str[@name = 'cmbs']"/></td>
		</tr>
		</xsl:if>
<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</xsl:if>
</tbody></table>

<table width="100%" border="0">
<tbody>
	<xsl:if test="string-length(str[@name = 'stato_catalogazione']) > 0">
		<tr>
			<td class="verdeLeft3" colspan="1" width="40%">Stato della catalogazione</td>
			<td width="60%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
		</tr>
		<tr>
			<td class="grigioLeft1" colspan="2" width="100%"><strong><xsl:value-of select="str[@name = 'stato_catalogazione']"/></strong></td>
		</tr>	
		<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
	</xsl:if>
</tbody></table>

<table width="100%" border="0">
<tbody>
	<xsl:if test="string-length(date[@name = 'catalogazione_data_censimento']) > 0">
		<tr>
			<td class="verdeLeft3" width="50%">Anno di rilevamento dati</td>
			<td class="grigioLeft1" width="50%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:value-of select="substring(date[@name = 'catalogazione_data_censimento'], 1, 4)"/></td>
		</tr>
		<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
	</xsl:if>
</tbody></table>

<table width="100%" border="0">
<tbody>
	<tr>
		<td class="verdeLeft3" width="50%">Data di aggiornamento</td>
        <td class="grigioLeft1" width="50%">
    		<xsl:choose>
    			<xsl:when test="string-length(date[@name = 'catalogazione_data_modifica']) = 0">
    				<xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>31-12-2000
    			</xsl:when>
    			<xsl:otherwise>
    				<xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text><xsl:value-of select="concat(substring(date[@name = 'catalogazione_data_modifica'], 9, 2), '-',substring(date[@name = 'catalogazione_data_modifica'], 6, 2), '-',substring(date[@name = 'catalogazione_data_modifica'], 1, 4))"/>
    			</xsl:otherwise>
    		</xsl:choose>
    	</td>
	</tr>
	
	<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>


</td></tr>
</tbody></table>

</xsl:for-each>
</xsl:template>
</xsl:stylesheet>