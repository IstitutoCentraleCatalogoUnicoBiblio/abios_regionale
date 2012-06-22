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
<tr><td class="verdeCenter3">VARIAZIONI D'ORARIO</td></tr>
<tr><td><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
</tbody></table>

<table width="100%" border="0">
<tbody><tr>
	<td class="verdeLeft3" width="25%">Periodo di variazione</td>
	<td width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
</tbody></table>
</td></tr>

<tr><td valign="top" height="0">
<table width="100%" border="0">
<tbody>
<xsl:choose>
	<xsl:when test="string-length(str[@name = 'orario_variazioni_periodo']) = 0">
		<tr><td class="grigioLeft1" width="100%"><font color="Red">Dato non definito</font></td></tr>
	</xsl:when>
	<xsl:otherwise>
		<tr><td class="grigioLeft1" width="100%"><xsl:value-of select="str[@name = 'orario_variazioni_periodo']"/></td></tr>
	</xsl:otherwise>
</xsl:choose>

</tbody></table>	

<tr><td colspan="4"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>

<table width="100%" border="0">
<tbody>	
<xsl:variable name="variazioni">
<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
	<xsl:value-of select="concat(., ';')"/>
</xsl:for-each>
</xsl:variable>
<xsl:choose>
	<xsl:when test="string-length($variazioni) = 0">
		<tr>
		<td class="verdinoRight2" width="25%" height="10%">Fascia oraria<xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
		<td class="grigioLeft3" width="75%"><font color="Red"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>Dato non definito</font></td>
		</tr>
	</xsl:when>
	<xsl:otherwise>
	<xsl:variable name="lunedi">
<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
	<xsl:if test="substring-before(.,'$$$') = 'Lunedi'">
		<xsl:value-of select="concat(substring-after(., '$$$'), ' - ')"/>
	</xsl:if>
</xsl:for-each>
</xsl:variable>
<xsl:variable name="martedi">
<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
	<xsl:if test="substring-before(.,'$$$') = 'Martedi'">
		<xsl:value-of select="concat(substring-after(., '$$$'), ' - ')"/>
	</xsl:if>
</xsl:for-each>
</xsl:variable>
<xsl:variable name="mercoledi">
<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
	<xsl:if test="substring-before(.,'$$$') = 'Mercoledi'">
		<xsl:value-of select="concat(substring-after(., '$$$'), ' - ')"/>
	</xsl:if>
</xsl:for-each>
</xsl:variable>
<xsl:variable name="giovedi">
<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
	<xsl:if test="substring-before(.,'$$$') = 'Giovedi'">
		<xsl:value-of select="concat(substring-after(., '$$$'), ' - ')"/>
	</xsl:if>
</xsl:for-each>
</xsl:variable>
<xsl:variable name="venerdi">
<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
	<xsl:if test="substring-before(.,'$$$') = 'Venerdi'">
		<xsl:value-of select="concat(substring-after(., '$$$'), ' - ')"/>
	</xsl:if>
</xsl:for-each>
</xsl:variable>
<xsl:variable name="sabato">
<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
	<xsl:if test="substring-before(.,'$$$') = 'Sabato'">
		<xsl:value-of select="concat(substring-after(., '$$$'), ' - ')"/>
	</xsl:if>
</xsl:for-each>
</xsl:variable>
<xsl:variable name="domenica">
<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
	<xsl:if test="substring-before(.,'$$$') = 'Domenica'">
		<xsl:value-of select="concat(substring-after(., '$$$'), ' - ')"/>
	</xsl:if>
</xsl:for-each>
</xsl:variable>
	
		<tr>
		<td class="verdinoRight2" width="25%" height="10%">Fascia oraria<xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
		<td class="verdinoCenter1" width="25%" height="10%">mattina (08:00 / 14:00)</td>
		<td class="verdinoCenter1" width="25%" height="10%">pomeriggio (14:00 / 20:00)</td>
		<td class="verdinoCenter1" width="25%" height="10%">sera (20:00 / 24:00)</td>
	</tr>
	
<xsl:if test="string-length($lunedi) > 0">
	<tr>
		<td class="verdinoRight1" width="25%">Lunedi<xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
		<xsl:choose>
			<xsl:when test="string-length($lunedi) &lt;= 16">
				<xsl:choose>
					<xsl:when test="number(substring($lunedi, 1,2)) &lt; 14">
						<td class="grigioCenter1"><xsl:value-of select="substring-before($lunedi, ' - ')"/></td>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"></td>
					</xsl:when>
					<xsl:when test="number(substring($lunedi, 1,2)) &lt; 20">
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"><xsl:value-of select="substring-before($lunedi, ' - ')"/></td>
						<td class="grigioCenter1"></td>
					</xsl:when>
					<xsl:otherwise>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"><xsl:value-of select="substring-before($lunedi, ' - ')"/></td>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:when test="string-length($lunedi) &gt; 16 and string-length($lunedi) &lt;= 32">
				<xsl:choose>
					<xsl:when test="number(substring($lunedi, 1,2)) &lt; 14">
						<xsl:choose>
							<xsl:when test="number(substring($lunedi, 17,2)) &lt; 20">
								<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
									<xsl:if test="substring-before(.,'$$$') = 'Lunedi'">		
										<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
									</xsl:if>
								</xsl:for-each>
								<td class="grigioCenter1"></td>						
							</xsl:when>
							<xsl:otherwise>
								<td class="grigioCenter1"><xsl:value-of select="substring($lunedi, 1,13)"/></td>
								<td class="grigioCenter1"></td>
								<td class="grigioCenter1"><xsl:value-of select="substring($lunedi, 17,13)"/></td>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:when>
					<xsl:otherwise>
						<td class="grigioCenter1"></td>
						<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
							<xsl:if test="substring-before(.,'$$$') = 'Lunedi'">		
								<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
							</xsl:if>
						</xsl:for-each>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:otherwise>
				<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
					<xsl:if test="substring-before(.,'$$$') = 'Lunedi'">		
						<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
					</xsl:if>
				</xsl:for-each>
			</xsl:otherwise>
		</xsl:choose>	
	</tr>
</xsl:if>
<xsl:if test="string-length($martedi) > 0">
	<tr>
		<td class="verdinoRight1" width="25%">Martedi<xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
		<xsl:choose>
			<xsl:when test="string-length($martedi) &lt;= 16">
				<xsl:choose>
					<xsl:when test="number(substring($martedi, 1,2)) &lt; 14">
						<td class="grigioCenter1"><xsl:value-of select="substring-before($martedi, ' - ')"/></td>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"></td>
					</xsl:when>
					<xsl:when test="number(substring($martedi, 1,2)) &lt; 20">
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"><xsl:value-of select="substring-before($martedi, ' - ')"/></td>
						<td class="grigioCenter1"></td>
					</xsl:when>
					<xsl:otherwise>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"><xsl:value-of select="substring-before($martedi, ' - ')"/></td>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:when test="string-length($martedi) &gt; 16 and string-length($martedi) &lt;= 32">
				<xsl:choose>
					<xsl:when test="number(substring($martedi, 1,2)) &lt; 14">
						<xsl:choose>
							<xsl:when test="number(substring($martedi, 17,2)) &lt; 20">
								<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
									<xsl:if test="substring-before(.,'$$$') = 'Martedi'">		
										<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
									</xsl:if>
								</xsl:for-each>
								<td class="grigioCenter1"></td>						
							</xsl:when>
							<xsl:otherwise>
								<td class="grigioCenter1"><xsl:value-of select="substring($martedi, 1,13)"/></td>
								<td class="grigioCenter1"></td>
								<td class="grigioCenter1"><xsl:value-of select="substring($martedi, 17,13)"/></td>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:when>
					<xsl:otherwise>
						<td class="grigioCenter1"></td>
						<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
							<xsl:if test="substring-before(.,'$$$') = 'Martedi'">		
								<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
							</xsl:if>
						</xsl:for-each>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:otherwise>
				<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
					<xsl:if test="substring-before(.,'$$$') = 'Martedi'">		
						<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
					</xsl:if>
				</xsl:for-each>
			</xsl:otherwise>
		</xsl:choose>
	</tr>
</xsl:if>

<xsl:if test="string-length($mercoledi) > 0">
	<tr>
		<td class="verdinoRight1" width="25%">Mercoledi<xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
		<xsl:choose>
			<xsl:when test="string-length($mercoledi) &lt;= 16">
				<xsl:choose>
					<xsl:when test="number(substring($mercoledi, 1,2)) &lt; 14">
						<td class="grigioCenter1"><xsl:value-of select="substring-before($mercoledi, ' - ')"/></td>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"></td>
					</xsl:when>
					<xsl:when test="number(substring($mercoledi, 1,2)) &lt; 20">
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"><xsl:value-of select="substring-before($mercoledi, ' - ')"/></td>
						<td class="grigioCenter1"></td>
					</xsl:when>
					<xsl:otherwise>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"><xsl:value-of select="substring-before($mercoledi, ' - ')"/></td>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:when test="string-length($mercoledi) &gt; 16 and string-length($mercoledi) &lt;= 32">
				<xsl:choose>
					<xsl:when test="number(substring($mercoledi, 1,2)) &lt; 14">
						<xsl:choose>
							<xsl:when test="number(substring($mercoledi, 17,2)) &lt; 20">
								<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
									<xsl:if test="substring-before(.,'$$$') = 'Mercoledi'">		
										<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
									</xsl:if>
								</xsl:for-each>
								<td class="grigioCenter1"></td>						
							</xsl:when>
							<xsl:otherwise>
								<td class="grigioCenter1"><xsl:value-of select="substring($mercoledi, 1,13)"/></td>
								<td class="grigioCenter1"></td>
								<td class="grigioCenter1"><xsl:value-of select="substring($mercoledi, 17,13)"/></td>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:when>
					<xsl:otherwise>
						<td class="grigioCenter1"></td>
						<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
							<xsl:if test="substring-before(.,'$$$') = 'Mercoledi'">		
								<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
							</xsl:if>
						</xsl:for-each>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:otherwise>
				<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
					<xsl:if test="substring-before(.,'$$$') = 'Mercoledi'">		
						<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
					</xsl:if>
				</xsl:for-each>
			</xsl:otherwise>
		</xsl:choose>	
	</tr>
</xsl:if>
<xsl:if test="string-length($giovedi) > 0">
	<tr>
		<td class="verdinoRight1" width="25%">Giovedi<xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
		<xsl:choose>
			<xsl:when test="string-length($giovedi) &lt;= 16">
				<xsl:choose>
					<xsl:when test="number(substring($giovedi, 1,2)) &lt; 14">
						<td class="grigioCenter1"><xsl:value-of select="substring-before($giovedi, ' - ')"/></td>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"></td>
					</xsl:when>
					<xsl:when test="number(substring($giovedi, 1,2)) &lt; 20">
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"><xsl:value-of select="substring-before($giovedi, ' - ')"/></td>
						<td class="grigioCenter1"></td>
					</xsl:when>
					<xsl:otherwise>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"><xsl:value-of select="substring-before($giovedi, ' - ')"/></td>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:when test="string-length($giovedi) &gt; 16 and string-length($giovedi) &lt;= 32">
				<xsl:choose>
					<xsl:when test="number(substring($giovedi, 1,2)) &lt; 14">
						<xsl:choose>
							<xsl:when test="number(substring($giovedi, 17,2)) &lt; 20">
								<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
									<xsl:if test="substring-before(.,'$$$') = 'Giovedi'">		
										<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
									</xsl:if>
								</xsl:for-each>
								<td class="grigioCenter1"></td>						
							</xsl:when>
							<xsl:otherwise>
								<td class="grigioCenter1"><xsl:value-of select="substring($giovedi, 1,13)"/></td>
								<td class="grigioCenter1"></td>
								<td class="grigioCenter1"><xsl:value-of select="substring($giovedi, 17,13)"/></td>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:when>
					<xsl:otherwise>
						<td class="grigioCenter1"></td>
						<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
							<xsl:if test="substring-before(.,'$$$') = 'Giovedi'">		
								<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
							</xsl:if>
						</xsl:for-each>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:otherwise>
				<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
					<xsl:if test="substring-before(.,'$$$') = 'Giovedi'">		
						<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
					</xsl:if>
				</xsl:for-each>
			</xsl:otherwise>
		</xsl:choose>
	</tr>
</xsl:if>
<xsl:if test="string-length($venerdi) > 0">
	<tr>
		<td class="verdinoRight1" width="25%">Venerdi<xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
		<xsl:choose>
			<xsl:when test="string-length($venerdi) &lt;= 16">
				<xsl:choose>
					<xsl:when test="number(substring($venerdi, 1,2)) &lt; 14">
						<td class="grigioCenter1"><xsl:value-of select="substring-before($venerdi, ' - ')"/></td>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"></td>
					</xsl:when>
					<xsl:when test="number(substring($venerdi, 1,2)) &lt; 20">
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"><xsl:value-of select="substring-before($venerdi, ' - ')"/></td>
						<td class="grigioCenter1"></td>
					</xsl:when>
					<xsl:otherwise>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"><xsl:value-of select="substring-before($venerdi, ' - ')"/></td>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:when test="string-length($venerdi) &gt; 16 and string-length($venerdi) &lt;= 32">
				<xsl:choose>
					<xsl:when test="number(substring($venerdi, 1,2)) &lt; 14">
						<xsl:choose>
							<xsl:when test="number(substring($venerdi, 17,2)) &lt; 20">
								<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
									<xsl:if test="substring-before(.,'$$$') = 'Venerdi'">		
										<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
									</xsl:if>
								</xsl:for-each>
								<td class="grigioCenter1"></td>						
							</xsl:when>
							<xsl:otherwise>
								<td class="grigioCenter1"><xsl:value-of select="substring($venerdi, 1,13)"/></td>
								<td class="grigioCenter1"></td>
								<td class="grigioCenter1"><xsl:value-of select="substring($venerdi, 17,13)"/></td>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:when>
					<xsl:otherwise>
						<td class="grigioCenter1"></td>
						<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
							<xsl:if test="substring-before(.,'$$$') = 'Venerdi'">		
								<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
							</xsl:if>
						</xsl:for-each>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:otherwise>
				<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
					<xsl:if test="substring-before(.,'$$$') = 'Venerdi'">		
						<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
					</xsl:if>
				</xsl:for-each>
			</xsl:otherwise>
		</xsl:choose>
	</tr>
</xsl:if>


<xsl:if test="string-length($sabato) > 0">
	<tr>
		<td class="verdinoRight1" width="25%">Sabato<xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
		<xsl:choose>
			<xsl:when test="string-length($sabato) &lt;= 16">
				<xsl:choose>
					<xsl:when test="number(substring($sabato, 1,2)) &lt; 14">
						<td class="grigioCenter1"><xsl:value-of select="substring-before($sabato, ' - ')"/></td>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"></td>
					</xsl:when>
					<xsl:when test="number(substring($sabato, 1,2)) &lt; 20">
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"><xsl:value-of select="substring-before($sabato, ' - ')"/></td>
						<td class="grigioCenter1"></td>
					</xsl:when>
					<xsl:otherwise>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"><xsl:value-of select="substring-before($sabato, ' - ')"/></td>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:when test="string-length($sabato) &gt; 16 and string-length($sabato) &lt;= 32">
				<xsl:choose>
					<xsl:when test="number(substring($sabato, 1,2)) &lt; 14">
						<xsl:choose>
							<xsl:when test="number(substring($sabato, 17,2)) &lt; 20">
								<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
									<xsl:if test="substring-before(.,'$$$') = 'Sabato'">		
										<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
									</xsl:if>
								</xsl:for-each>
								<td class="grigioCenter1"></td>						
							</xsl:when>
							<xsl:otherwise>
								<td class="grigioCenter1"><xsl:value-of select="substring($sabato, 1,13)"/></td>
								<td class="grigioCenter1"></td>
								<td class="grigioCenter1"><xsl:value-of select="substring($sabato, 17,13)"/></td>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:when>
					<xsl:otherwise>
						<td class="grigioCenter1"></td>
						<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
							<xsl:if test="substring-before(.,'$$$') = 'Sabato'">		
								<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
							</xsl:if>
						</xsl:for-each>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:otherwise>
				<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
					<xsl:if test="substring-before(.,'$$$') = 'Sabato'">		
						<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
					</xsl:if>
				</xsl:for-each>
			</xsl:otherwise>
		</xsl:choose>
	</tr>
</xsl:if>


<xsl:if test="string-length($domenica) > 0">
	<tr>
		<td class="verdinoRight1" width="25%">Domenica<xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
		<xsl:choose>
			<xsl:when test="string-length($domenica) &lt;= 16">
				<xsl:choose>
					<xsl:when test="number(substring($domenica, 1,2)) &lt; 14">
						<td class="grigioCenter1"><xsl:value-of select="substring-before($domenica, ' - ')"/></td>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"></td>
					</xsl:when>
					<xsl:when test="number(substring($domenica, 1,2)) &lt; 20">
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"><xsl:value-of select="substring-before($domenica, ' - ')"/></td>
						<td class="grigioCenter1"></td>
					</xsl:when>
					<xsl:otherwise>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"></td>
						<td class="grigioCenter1"><xsl:value-of select="substring-before($domenica, ' - ')"/></td>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:when test="string-length($domenica) &gt; 16 and string-length($domenica) &lt;= 32">
				<xsl:choose>
					<xsl:when test="number(substring($domenica, 1,2)) &lt; 14">
						<xsl:choose>
							<xsl:when test="number(substring($domenica, 17,2)) &lt; 20">
								<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
									<xsl:if test="substring-before(.,'$$$') = 'Domenica'">		
										<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
									</xsl:if>
								</xsl:for-each>
								<td class="grigioCenter1"></td>						
							</xsl:when>
							<xsl:otherwise>
								<td class="grigioCenter1"><xsl:value-of select="substring($domenica, 1,13)"/></td>
								<td class="grigioCenter1"></td>
								<td class="grigioCenter1"><xsl:value-of select="substring($domenica, 17,13)"/></td>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:when>
					<xsl:otherwise>
						<td class="grigioCenter1"></td>
						<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
							<xsl:if test="substring-before(.,'$$$') = 'Domenica'">		
								<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
							</xsl:if>
						</xsl:for-each>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:otherwise>
				<xsl:for-each select="arr[@name = 'orario_variazioni']/str">
					<xsl:if test="substring-before(.,'$$$') = 'Domenica'">		
						<td class="grigioCenter1"><xsl:value-of select="substring-after(., '$$$')"/></td>
					</xsl:if>
				</xsl:for-each>
			</xsl:otherwise>
		</xsl:choose>
	</tr>
</xsl:if>
	
	
	</xsl:otherwise>
</xsl:choose>
	

</tbody></table>

<tr><td colspan="4"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>

</td></tr>

<table width="100%" border="0">
<tbody><tr>
	<td class="verdeLeft3" width="25%">Periodi di chiusura</td>
	<td width="75%"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td>
</tr>
</tbody></table>

<tr><td valign="top" height="0">
<table width="100%" border="0">
<tbody>
<xsl:variable name="chiusure">
<xsl:for-each select="arr[@name = 'orario_chiusura']/str">
	<xsl:value-of select="concat(., ';')"/>
</xsl:for-each>
</xsl:variable>
<xsl:choose>
	<xsl:when test="string-length($chiusure) = 0">
		<tr><td class="grigioLeft1" width="100%"><font color="Red">Dato non definito</font></td></tr>
	</xsl:when>
	<xsl:otherwise>
		<xsl:for-each select="arr[@name = 'orario_chiusura']/str">
			<tr><td class="grigioLeft1" width="100%"><xsl:value-of select="."/></td></tr>
		</xsl:for-each>		
	</xsl:otherwise>
</xsl:choose>

</tbody></table>
</td></tr>


</tbody></table>

</xsl:for-each>
</xsl:template>
</xsl:stylesheet>