<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="yes" encoding="UTF-8"/>
	<xsl:template match="/">
		<xsl:call-template name="schema"/>
	</xsl:template>
	<xsl:template name="schema">
		<xsl:call-template name="content"/>
	</xsl:template>
	<xsl:template name="content"> 
	<xsl:variable name="max" select="//result/@numFound"/>
	<xsl:variable name="field">
	            <xsl:choose>
	            <xsl:when test="'keywords'=substring-after(substring-before(//lst/str[@name = 'q'],':'),' ')">
                     <xsl:value-of select="'keywords'"/>
                    </xsl:when>
                    <xsl:otherwise>
                     <xsl:value-of select="'cerca'"/>
                    </xsl:otherwise>
                    </xsl:choose>
        </xsl:variable>
        <xsl:variable name="sezione">
	            <xsl:choose>
	            <xsl:when test="'keywords'=substring-after(substring-before(//lst/str[@name = 'q'],':'),' ')">
                     <xsl:value-of select="''"/>
                    </xsl:when>
                    <xsl:otherwise>
                     <xsl:value-of select="'in &quot;sezioni informative&quot;'"/>
                    </xsl:otherwise>
                    </xsl:choose>
        </xsl:variable>
        <xsl:variable name="query">
                <xsl:choose>
                <xsl:when test="'keywords'=$field">
                    <xsl:value-of select="substring-before(substring-after(//lst/str[@name = 'q'],':'), ')')"/>
                </xsl:when>
                 <xsl:otherwise>
                    <xsl:value-of select="translate(substring-before(substring-after(//lst/str[@name = 'q'],':'),' OR'),'&quot;','')"/>
                 </xsl:otherwise>
                </xsl:choose>
        </xsl:variable>
	<xsl:variable name="pre_url" select="concat('','/opencms/opencms/risultati.jsp?', $field, '=',$query)"/>
        <xsl:variable name="filter" select="concat('fl=', //lst[@name = 'facet_fields']/lst/@name,':')"/>
         <p>La ricerca <xsl:value-of select="$sezione"/> ha prodotto <xsl:value-of select="$max"/> risultati in <xsl:value-of select="count(//lst[@name = 'tipo_1']/int[. > 0])"/> categorie</p>

       <xsl:if test="$max > 0">
       <ul>

	<!-- Sezioni informative - start -->
	<li>Sezioni informative
	       <ul>


		<xsl:for-each select="//lst[@name = 'tipo_1']/int">
			<xsl:sort select="@name" data-type="text" order="ascending"/>
			<xsl:variable name="count" select="."/>
			<xsl:variable name="name" select="@name"/>
			<xsl:if test="$count >= 1">
				<li>
					<a>
						<xsl:attribute name="href"><xsl:value-of select="concat($pre_url,'&amp;amp;',$filter, $name)"/></xsl:attribute>
						<xsl:attribute name="title"><xsl:value-of select="concat('Funzione di accesso a ', $name)"/></xsl:attribute>
						<xsl:value-of select="$name" /> (<xsl:value-of select="$count"/> risultati) <span class="freccia_area_personale"><xsl:text disable-output-escaping="yes"><![CDATA[&gt;]]></xsl:text> </span>
					</a>
					<span class="elemento_nascosto">Collegamento alla funzione di accesso a <xsl:value-of select="$name"/> </span>


					
				</li>
			</xsl:if>
		</xsl:for-each>
		</ul>
		</li>
		<!-- Sezioni informative - end -->
	      </ul>
	</xsl:if>
	</xsl:template>
</xsl:stylesheet>
