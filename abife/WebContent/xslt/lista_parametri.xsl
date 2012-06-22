<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="no" encoding="UTF-8"/>
	<xsl:param name="pagina_url"/>
	<xsl:param name="query_url"/>
	<xsl:param name="torna"/>
	<xsl:template match="/">
	<xsl:call-template name="schema"/>
	</xsl:template>
	<xsl:template name="schema">
		<xsl:variable name="from" select="//result/@start" />
		<xsl:variable name="entries" select="//lst[@name = 'params']/str[@name = 'rows']" />
		<xsl:variable name="max" select="//result/@numFound"/>
		<xsl:variable name="url_parziale" select="concat($pagina_url,'?',$query_url,'&amp;start=')"/>
		<tr>
		<td>
			<table border="0" align="center">
				<tr>	
					<td colspan="3">
						<FONT SIZE="2" COLOR="#000099" FACE="Verdana, Arial, Helvetica, sans-serif">
						Numero delle biblioteche che rispondono ai parametri impostati:
						</FONT>					
					</td>
					<td colspan="1" bgcolor="E6E6E6">
						<FONT SIZE="2" COLOR="#000099" FACE="Verdana, Arial, Helvetica, sans-serif"><b>
						<xsl:value-of select="$max" /> <xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></b>
						</FONT>
					</td>
				</tr>
				<tr><td colspan="4"><xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text></td></tr>
			</table>
		</td>
		</tr>
		
	</xsl:template>
</xsl:stylesheet>