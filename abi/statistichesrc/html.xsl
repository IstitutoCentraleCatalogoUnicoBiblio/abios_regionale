<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:decimal-format name="european" decimal-separator=',' grouping-separator='.' />
<xsl:output method="html"/>
<xsl:template match="/">
  <html>
  <head>
  	<title>Statistiche Anagrafe delle Biblioteche Italiane</title>
  	<xsl:choose>
  		<xsl:when test="/table/@name='TAVOLA01' or /table/@name='TAVOLA01a' or /table/@name='TAVOLA01b' 
  		or /table/@name='TAVOLA01c' or /table/@name='TAVOLA02' or /table/@name='TAVOLA02a' 
  		or /table/@name='TAVOLA02b' or /table/@name='TAVOLA02c' or /table/@name='TAVOLA02cp' 
  		or /table/@name='TAVOLA03' or /table/@name='TAVOLA04' or /table/@name='TAVOLA05'
  		or /table/@name='TAVOLA05a' or /table/@name='TAVOLA05b' or /table/@name='TAVOLA06'
  		or /table/@name='TAVOLA07' or /table/@name='TAVOLA08' or/table/@name='TAVOLA09' or /table/@name='TAVOLA10'
  		or /table/@name='GRAFICO01' or /table/@name='GRAFICO02' or /table/@name='GRAFICO03'">
  			<link rel="stylesheet" href="./statistiche/style/styleIccu.css" type="text/css"/>
  		</xsl:when>
  		<xsl:otherwise>
  			<link rel="stylesheet" href="../statistiche/style/styleIccu.css" type="text/css"/>
  		</xsl:otherwise>
  	</xsl:choose>
  </head>
  
  <body>
   <table align="center" width="100%">
    <tr>
    	<xsl:apply-templates/>
    </tr>
   <tr>
   	<td></td>
   	<TD align="center" width="750" height="1%">
	</TD>
   </tr> 
   </table>
  </body>
  </html>
</xsl:template>

<xsl:template match="table">
  <td valign="top" width="1">  
  </td>    	
  <td align="center" valign="top">
  <span class="verdeCenter3"><xsl:value-of select="@title"/></span>
  <br/>
  <br/>
  <xsl:choose>
  	<xsl:when test="@name='TAVOLA01' or @name='TAVOLA01a' or @name='TAVOLA01b' 
  		or @name='TAVOLA01c' or @name='TAVOLA02' or @name='TAVOLA02a' 
  		or @name='TAVOLA02b' or @name='TAVOLA02c' or @name='TAVOLA02cp'
  		or @name='TAVOLA03' or @name='TAVOLA04' or @name='TAVOLA05'
  		or @name='TAVOLA05a' or @name='TAVOLA05b' or @name='TAVOLA06'
  		or @name='TAVOLA07' or @name='TAVOLA08' or @name='TAVOLA09'  or @name='TAVOLA10'
  		or @name='GRAFICO01' or @name='GRAFICO02' or @name='GRAFICO03'">
	  	<a>
	    <xsl:attribute name="href">
	    	<xsl:value-of select="concat('statistiche/', @name,'.xls')"/>
	    </xsl:attribute><img src="statistiche/images/excel3.gif" border="0"/>Scarica <xsl:value-of select="concat(@name,'.xls')"/>
	  	</a>
	</xsl:when>
	<xsl:otherwise>
		<a>
	    <xsl:attribute name="href">
	    	<xsl:value-of select="concat('../statistiche/', @name,'.xls')"/>
	    </xsl:attribute><img src="../statistiche/images/excel3.gif" border="0"/>Scarica <xsl:value-of select="concat(@name,'.xls')"/>
	  	</a>
	</xsl:otherwise>
  </xsl:choose>
  <br/>
  <br/>
  <table border="0">
  <xsl:apply-templates/>
  </table>
  <br/>
  <br/>
  <table border="0" width="80%">
    <xsl:choose>
    <xsl:when test="@name='GRAFICO01'">
  	<xsl:variable name="maximum">
  	<xsl:call-template name="max">
  	    <xsl:with-param name="nodes" select="//ROW/column[@num=2]"/>
  	</xsl:call-template>
  	</xsl:variable>
  	<xsl:for-each select="//ROW">
  	<xsl:sort data-type="number" order="descending" select="column[@num=2]"/>
  	<tr>
  	<td class="graficoStatistiche"><xsl:value-of select="column[@num=1]"/></td>
  	<td>
  	<img src="statistiche/images/hp.png" border="0" height="10">	
  	<xsl:attribute name="width">
  		<xsl:value-of select="3 * round((100 * column[@num=2])div $maximum)"/>
  	</xsl:attribute>
  	</img>
  	<xsl:text> </xsl:text>
  	<xsl:value-of select="column[@num=2]"/>
  	</td>
  	</tr>
  	</xsl:for-each>
    </xsl:when>
    
    <xsl:when test="@name='GRAFICO02'">
    	<xsl:for-each select="//ROW">
	  	<tr>
	  	<td class="graficoStatistiche"><xsl:value-of select="column[@num=1]"/></td>
	  	<td width="80%">
	  	<div class="statistiche">
	  	<img src="statistiche/images/hp.png" border="0" height="14">	
	  	<xsl:if test="contains(column[@num=2], ',')">
	  	<xsl:attribute name="width">
	  		<xsl:value-of select="(number(substring-before(column[@num=2], ',')))*4"/>
	  	</xsl:attribute>
	  	</xsl:if>
	  	<xsl:if test="not(contains(column[@num=2], ','))">
	  	<xsl:attribute name="width">
	  		<xsl:value-of select="(number(column[@num=2]))*4"/>
	  	</xsl:attribute>
	  	</xsl:if>
	  	</img>
	  	<span class="graficoStatistiche">
	  	<xsl:value-of select="column[@num=2]"/>
	  	</span>
	  	</div>
	  	<div class="statistiche">
	  	<img src="statistiche/images/hk.png" border="0" height="14">	
	  	<xsl:if test="contains(column[@num=3], ',')">
	  	<xsl:attribute name="width">
	  		<xsl:value-of select="(number(substring-before(column[@num=3], ',')))*4"/>
	  	</xsl:attribute>
	  	</xsl:if>
	  	<xsl:if test="not(contains(column[@num=3], ','))">
	  	<xsl:attribute name="width">
	  		<xsl:value-of select="(number(column[@num=3]))*4"/>
	  	</xsl:attribute>
	  	</xsl:if>
	  	</img>
	  	<span class="graficoStatistiche">
	  	<xsl:value-of select="column[@num=3]"/>
	  	</span>
	  	</div>
		<div class="statistiche">
	  	<img src="statistiche/images/hx.png" border="0" height="14">	
	  	<xsl:if test="contains(column[@num=4], ',')">
	  	<xsl:attribute name="width">
	  		<xsl:value-of select="(number(substring-before(column[@num=4], ',')))*4"/>
	  	</xsl:attribute>
	  	</xsl:if>
	  	<xsl:if test="not(contains(column[@num=4], ','))">
	  	<xsl:attribute name="width">
	  		<xsl:value-of select="(number(column[@num=4]))*4"/>
	  	</xsl:attribute>
	  	</xsl:if>
	  	</img>
	  	<span class="graficoStatistiche">
	  	<xsl:value-of select="column[@num=4]"/>
	  	</span>
	  	</div>	  	</td>
	  	</tr>
  	</xsl:for-each>
    </xsl:when>
    <xsl:when test="@name='GRAFICO03' or @name='GRAFICO03p'">
    <xsl:for-each select="//ROW">
	  	<tr>
	  	<td class="graficoStatistiche"><xsl:value-of select="column[@num=1]"/></td>
	  	<td width="80%">
	  	<div class="statistiche">
	  	<xsl:choose>
	  		<xsl:when test="/table/@name='GRAFICO03p'">
	  			<img src="statistiche/images/hp.png" border="0" height="14">	
	  			<xsl:if test="contains(column[@num=2], ',')">
			  	<xsl:attribute name="width">
			  		<xsl:value-of select="(number(substring-before(column[@num=2], ',')))*4"/>
			  	</xsl:attribute>
			  	</xsl:if>
			  	<xsl:if test="not(contains(column[@num=2], ','))">
			  	<xsl:attribute name="width">
			  		<xsl:value-of select="(number(column[@num=2]))*4"/>
			  	</xsl:attribute>
			  	</xsl:if>
			  	</img>
	  		</xsl:when>
	  		<xsl:otherwise>
	  			<img src="statistiche/images/hp.png" border="0" height="14">
	  			<xsl:if test="contains(column[@num=2], ',')">
			  	<xsl:attribute name="width">
			  		<xsl:value-of select="(number(substring-before(column[@num=2], ',')))*4"/>
			  	</xsl:attribute>
			  	</xsl:if>
			  	<xsl:if test="not(contains(column[@num=2], ','))">
			  	<xsl:attribute name="width">
			  		<xsl:value-of select="(number(column[@num=2]))*4"/>
			  	</xsl:attribute>
			  	</xsl:if>
			  	</img>	
	  		</xsl:otherwise>
	  	</xsl:choose>
	  	<span class="graficoStatistiche">
	  	<xsl:value-of select="column[@num=2]"/>
	  	</span>
	  	</div>
	  	<div class="statistiche">
	  	<xsl:choose>
	  		<xsl:when test="/table/@name='GRAFICO03p'">
	  			<img src="statistiche/images/he.png" border="0" height="14">	
	  			<xsl:if test="contains(column[@num=3], ',')">
			  	<xsl:attribute name="width">
			  		<xsl:value-of select="(number(substring-before(column[@num=3], ',')))*4"/>
			  	</xsl:attribute>
			  	</xsl:if>
			  	<xsl:if test="not(contains(column[@num=3], ','))">
			  	<xsl:attribute name="width">
			  		<xsl:value-of select="(number(column[@num=3]))*4"/>
			  	</xsl:attribute>
			  	</xsl:if>
			  	</img>
	  		</xsl:when>
	  		<xsl:otherwise>
	  			<img src="statistiche/images/he.png" border="0" height="14">	
	  			<xsl:if test="contains(column[@num=3], ',')">
			  	<xsl:attribute name="width">
			  		<xsl:value-of select="(number(substring-before(column[@num=3], ',')))*4"/>
			  	</xsl:attribute>
			  	</xsl:if>
			  	<xsl:if test="not(contains(column[@num=3], ','))">
			  	<xsl:attribute name="width">
			  		<xsl:value-of select="(number(column[@num=3]))*4"/>
			  	</xsl:attribute>
			  	</xsl:if>
			  	</img>
	  		</xsl:otherwise>
	  	</xsl:choose>
	  	<span class="graficoStatistiche">
	  	<xsl:value-of select="column[@num=3]"/>
	  	</span>
	  	</div>
	  	</td>
	  	</tr>
  	</xsl:for-each>
    </xsl:when>
    </xsl:choose>
    </table>
<xsl:choose>
<xsl:when test="@name='TAVOLA01' or @name='TAVOLA01a' or @name='TAVOLA01b' 
  		or @name='TAVOLA01c' or @name='TAVOLA02' or @name='TAVOLA02a' 
  		or @name='TAVOLA02b' or @name='TAVOLA02c' or @name='TAVOLA02cp'
  		or @name='TAVOLA03' or @name='TAVOLA04' or @name='TAVOLA05'
  		or @name='TAVOLA05a' or @name='TAVOLA05b' or @name='TAVOLA06'
  		or @name='TAVOLA07' or @name='TAVOLA08' or @name='TAVOLA09'  or @name='TAVOLA10'
  		or @name='GRAFICO01' or @name='GRAFICO02' or @name='GRAFICO03'">
	<a>
    <xsl:attribute name="href">
    	<xsl:value-of select="concat('statistiche/', @name,'.xls')"/>
    </xsl:attribute><img src="statistiche/images/excel3.gif" border="0"/>Scarica <xsl:value-of select="concat(@name,'.xls')"/>
  	</a>
</xsl:when>
<xsl:otherwise>
	<a>
    <xsl:attribute name="href">
    	<xsl:value-of select="concat('../statistiche/', @name,'.xls')"/>
    </xsl:attribute><img src="../statistiche/images/excel3.gif" border="0"/>Scarica <xsl:value-of select="concat(@name,'.xls')"/>
  	</a>
</xsl:otherwise>
</xsl:choose>
  </td>
</xsl:template>

<xsl:template match="HEADERS">
  <tr>
  <xsl:for-each select="column">
  <td class="verdeCenter1">
  <xsl:attribute name="rowspan"><xsl:value-of select="@rowspan"/></xsl:attribute>
  <xsl:attribute name="colspan"><xsl:value-of select="@colspan"/></xsl:attribute>
  <xsl:value-of select="."/>
  </td>
  </xsl:for-each>
  </tr>
</xsl:template>

<xsl:template match="ROW">
  <tr>
      <xsl:for-each select="column">
      <xsl:choose>
          <xsl:when test="@num=1">
            <td class="grigioLeft1" valign="top" >
            <xsl:choose>
            <xsl:when test="string-length(.)=0">
      			<xsl:text>-</xsl:text>
      		</xsl:when>
      		<xsl:when test="string-length(@href) &gt; 0">
      		  <a>
      		  <xsl:attribute name="href">
      		     <xsl:value-of select="@href" />
      		  </xsl:attribute>
      		  <xsl:attribute name="target">
      		  <xsl:value-of select="@target" />
      		  </xsl:attribute>
      		  <xsl:value-of select="."/>
      		  </a>
      		</xsl:when>
      		<xsl:otherwise>
      		    <xsl:value-of select="."/>
      		</xsl:otherwise>
      		</xsl:choose>
      		</td>
          </xsl:when>
          <xsl:otherwise>
            <td class="grigioRight1" valign="top" >
            <xsl:if test="string-length(.)=0">
      			<xsl:text>-</xsl:text>
      		</xsl:if>
      		<xsl:value-of select="."/>
      		</td>
          </xsl:otherwise>
     </xsl:choose>      
     </xsl:for-each>
  </tr>  
</xsl:template>

 <xsl:template name="max">
  <xsl:param name="nodes" select="/.." /> 
	 <xsl:choose>
	 <xsl:when test="not($nodes)">NaN</xsl:when> 
	 <xsl:otherwise>
	 <xsl:for-each select="$nodes">
	 <xsl:sort data-type="number" order="descending"/> 
	 <xsl:if test="position() = 1">
	  <xsl:value-of select="number(.)" /> 
	  </xsl:if>
	  </xsl:for-each>
	  </xsl:otherwise>
	  </xsl:choose>
  </xsl:template>
</xsl:stylesheet>

