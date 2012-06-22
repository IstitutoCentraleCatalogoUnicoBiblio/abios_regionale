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
				
		<p align="left">			
		<xsl:call-template name="tplPaging">
		<xsl:with-param name="numberOfItems" select="$max"></xsl:with-param>
		<xsl:with-param name="from" select="$from"></xsl:with-param>
		<xsl:with-param name="UrlPage" select="$url_parziale"></xsl:with-param>
		<xsl:with-param name="nentries" select="$entries"></xsl:with-param>
		</xsl:call-template>
		</p>
				
		<xsl:for-each select="//result/doc">
			<!--inizio singolo record -->
			<tr>
				<td align="left" bgcolor="#E8FEE7" colspan="2"><font size="2" color="Black" face="Arial">
					<script language="JavaScript">
						createRef("<xsl:value-of select="str[@name = 'descrizione']"/>");
					</script>
					</font>
				</td>
			</tr>
			
			
		</xsl:for-each>
		
		</xsl:template>
	
	<xsl:template name="tplPaging">
		<!-- Identifies the number of items in the list -->
		<xsl:param name="numberOfItems" />
		<xsl:param name="from" />
	    <xsl:param name="UrlPage" />
    	<xsl:param name="nentries" />
	
		<xsl:variable name="currentPage" select="floor(number($from div $nentries))+1" />
		<!-- Calculate the maximum number of pages to show in the paging component -->
		<xsl:variable name="numberOfPages" select="floor(number(($numberOfItems - 1) div $nentries))+1"/>
		<!-- Calaulate the starting position of the numbers -->
		<xsl:variable name="startPage">
			<xsl:choose>
				<xsl:when test="$currentPage > 3">
					<xsl:choose>
						<xsl:when test="($currentPage + 3 > $numberOfPages) and ($numberOfPages - 5 &gt; 0)">
							<xsl:value-of select="$numberOfPages - 4" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="$currentPage - 2"/>
						</xsl:otherwise>
					</xsl:choose>
		    	</xsl:when>
		    	<xsl:otherwise>
	      			<xsl:value-of select="1"/>
	    		</xsl:otherwise>
	  		</xsl:choose>
	    	
		</xsl:variable>
		
		<!-- Calculate the ending position of the numbers -->
		<xsl:variable name="endPage">
			<xsl:choose>
				<xsl:when test="$startPage + 5 > $numberOfPages">
				<xsl:value-of select="$numberOfPages"/>
				</xsl:when>
	
			    <xsl:otherwise>
					<xsl:value-of select="$startPage + 4"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		
		
			
		<xsl:call-template name="tplNumber">
		<xsl:with-param name="current" select="$currentPage"/>
		<xsl:with-param name="number" select="$startPage"/>
		<xsl:with-param name="max" select="$endPage"/>
		<xsl:with-param name="url" select="$UrlPage"/>
	    <xsl:with-param name="first" select="1"/>
	    <xsl:with-param name="nentries" select="$nentries"/>
	    <xsl:with-param name="tot" select="$numberOfItems"/>
	    <xsl:with-param name="from" select="$from"/>
		</xsl:call-template>			
		
		
	
	
	</xsl:template>
	
	<xsl:template name="tplNumber">
	<xsl:param name="current"/>
	<xsl:param name="number"/>
	<xsl:param name="max"/>
	<xsl:param name="url"/>
	<xsl:param name="first"/>
	<xsl:param name="nentries"/>
	<xsl:param name="from"/>
	<xsl:param name="tot"/>
	
	<xsl:if test="$tot &gt; 0">
			<font size="2" face="Arial">
			Visualizzazione da <b><xsl:value-of select="$from+1"/></b> a
			<b>
			<xsl:if test="$nentries + $from &gt;= $tot"><xsl:value-of select="$tot"/></xsl:if>
			<xsl:if test="$nentries + $from &lt; $tot"><xsl:value-of select="$nentries + $from" /></xsl:if></b> di <b><xsl:value-of select="$tot"/></b> sezioni speciali
			</font><br/><br/>
	</xsl:if>
	<xsl:if test="$tot = 0">
			<font size="2" face="Arial">
			<b>0</b> sezioni speciali trovate.
			</font><br/><br/>
	</xsl:if>

	
	<xsl:if test="$from = 0">
		<!-- Sono alla prima pagina -->
		<font size="2" face="Arial" color="#959595">
			[Prev &lt;&lt;&lt;]<xsl:text disable-output-escaping="yes">&amp;nbsp;&amp;nbsp;&amp;nbsp;</xsl:text>
		</font>
	</xsl:if>
	<xsl:if test="$from > 0">
		<font size="2" face="Arial" color="#959595">
			<a><xsl:attribute name="href"><xsl:value-of select="concat(substring-before($url,'&amp;start'),'&amp;start=',$from - $nentries)"/></xsl:attribute>[Prev &lt;&lt;&lt;]</a>
	   		<xsl:text disable-output-escaping="yes">&amp;nbsp;&amp;nbsp;&amp;nbsp;</xsl:text>
	   	</font>
	</xsl:if>
	
			
	<xsl:choose>
	    <xsl:when test="$number = $current">
	    <!-- Show current page without a link -->
	    <font size="2" face="Arial" color="Black">
	   	<b><xsl:value-of select="$number"/></b><xsl:text disable-output-escaping="yes">&amp;nbsp;&amp;nbsp;&amp;nbsp;</xsl:text>
	   	</font>
	   	</xsl:when>		
		<xsl:otherwise>
		<font size="2" face="Arial" color="#959595">
		<a><xsl:attribute name="href"><xsl:value-of select="concat(substring-before($url,'&amp;start'),'&amp;start=',(($number - 1)*$nentries))"/></xsl:attribute><xsl:value-of select="$number"/></a><xsl:text disable-output-escaping="yes">&amp;nbsp;&amp;nbsp;&amp;nbsp;</xsl:text>
		</font>
		</xsl:otherwise>
	
	</xsl:choose>
	
	<!-- Recursively call the template untill we reach the max number of pages -->
	<xsl:if test="$number &lt; $max">
		<xsl:call-template name="tplNumber">
	    <xsl:with-param name="current" select="$current"/>
	    <xsl:with-param name="number" select="$number+1"/>
	    <xsl:with-param name="max" select="$max"/>
	    <xsl:with-param name="url" select="$url"/>
	    <xsl:with-param name="first" select="0"/>
	    <xsl:with-param name="nentries" select="$nentries"/>
		</xsl:call-template>
	</xsl:if>
	
	<xsl:if test="$from + $nentries >= $tot">
		<!-- Sono all'ultima pagina -->
		<font size="2" face="Arial" color="#959595">
			[Next >>>]<xsl:text disable-output-escaping="yes">&amp;nbsp;&amp;nbsp;&amp;nbsp;</xsl:text></font>
	</xsl:if>
	<xsl:if test="$from + $nentries &lt; $tot">
		<font size="2" face="Arial" color="#959595">
			<a><xsl:attribute name="href"><xsl:value-of select="concat(substring-before($url,'&amp;start'),'&amp;start=',$from + $nentries)"/></xsl:attribute>[Next &gt;&gt;&gt;]</a><xsl:text disable-output-escaping="yes">&amp;nbsp;&amp;nbsp;&amp;nbsp;</xsl:text>
		</font>
	</xsl:if>	
			
	</xsl:template>
	
	
	
</xsl:stylesheet>