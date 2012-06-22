<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="xhtml" indent="yes"  omit-xml-declaration = "yes" />
	<xsl:param name="help"/>
	<xsl:param name="help_res"/>
	<xsl:param name="help_fil"/>
	<xsl:template match="/">
		<xsl:call-template name="schema"/>
	</xsl:template>
	<xsl:template name="schema">
		<xsl:call-template name="content"/>
	</xsl:template>
	<xsl:template name="content"> 
	<xsl:variable name="context_cms" select="'/opencms/opencms'" />
	<xsl:variable name="from" select="//result/@start" />
	<xsl:variable name="entries" select="//lst[@name = 'params']/str[@name = 'rows']" />
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
        <xsl:variable name="query1">
                <xsl:choose>
                <xsl:when test="'keywords'=$field">
                    <xsl:value-of select="translate(substring-before(substring-after(//lst/str[@name = 'q'],':'),')'),'&quot;','')"/>
                </xsl:when>
                 <xsl:otherwise>
                    <xsl:value-of select="translate(substring-before(substring-after(//lst/str[@name = 'q'],':'),' OR'),'&quot;','')"/>
                 </xsl:otherwise>
                </xsl:choose>
        </xsl:variable>
        <xsl:variable name="query">
         <xsl:call-template name="replaceQuery"><xsl:with-param name="stringIn"  select="$query1" /></xsl:call-template>
        </xsl:variable> 
	<xsl:variable name="tipo_1">
	   <xsl:choose>
	   <xsl:when test="//lst[@name='params']/str[@name = 'fq']">
		<xsl:if test="starts-with(//lst[@name='params']/str[@name = 'fq'], 'tipo_1')">
		 <xsl:value-of select="translate(substring-after(//lst[@name='params']/str[@name = 'fq'],':'),'&quot;','')"/>
		</xsl:if>
           </xsl:when>
           <xsl:otherwise>
	   <xsl:for-each select="//lst[@name = 'params']/arr[@name = 'fq']/str">
		<xsl:if test="starts-with(., 'tipo_1')">
		 <xsl:value-of select="translate(substring-after(.,':'),'&quot;','')"/>
		</xsl:if>
	   </xsl:for-each>
           </xsl:otherwise>
	   </xsl:choose>
	</xsl:variable>
	<xsl:variable name="strumento">
	   <xsl:for-each select="//lst[@name = 'params']/arr[@name = 'fq']/str">
		<xsl:if test="starts-with(., 'strumento')">
		 <xsl:value-of select="translate(substring-after(.,':'),'&quot;','')"/>
		</xsl:if>
	   </xsl:for-each>
	</xsl:variable>
	<xsl:variable name="anno">
	   <xsl:for-each select="//lst[@name = 'params']/arr[@name = 'fq']/str">
		<xsl:if test="starts-with(., 'anno_pubblicazione')">
		 <xsl:value-of select="translate(substring-after(.,':'),'&quot;','')"/>
		</xsl:if>
	   </xsl:for-each>
	</xsl:variable>
	<xsl:variable name="filters">
	  <xsl:choose>
	   <xsl:when test="string-length($anno) > 0 and string-length($strumento)">
	   <xsl:value-of select="concat( '&amp;fl=anno_pubblicazione:', $anno,  '&amp;fl=strumento:', $strumento)" />
	   </xsl:when>
	   <xsl:when test="string-length($anno) > 0">
	    <xsl:value-of select="concat( '&amp;fl=anno_pubblicazione:', $anno)" />
	   </xsl:when>
	   <xsl:when test="string-length($strumento) > 0">
	    <xsl:value-of select="concat( '&amp;fl=strumento:', $strumento)" />
	   </xsl:when>
	   <xsl:otherwise>
	    <xsl:value-of select="''" />
	   </xsl:otherwise>
	  </xsl:choose>	
	</xsl:variable>
	<xsl:variable name="url_risorsa" select="concat($context_cms,'/resolveid.jsp?')"/>
	<xsl:variable name="pre_url" select="concat($context_cms,'/risultati.jsp?', $field, '=',$query, '&amp;fl=tipo_1:', $tipo_1)"/>
	<xsl:variable name="pagina_url" select="concat($pre_url,$filters, '&amp;start=')"/>
    <xsl:variable name="previous_url" select="concat($context_cms,'/ricerca.jsp?', $field, '=',$query)"/>
    
<div class="titolo_ricerca">
	<h2 id="contenuto_pagina" class="elemento_nascosto">Ti trovi in: Hai cercato &quot;<xsl:value-of select="$query" />&quot; in &quot;Sezioni informative&quot; - <xsl:value-of select="$tipo_1" />. La ricerca ha prodotto <xsl:value-of select="$max" /> risultati </h2>
	<div class="risultati_ricerca_top">Hai cercato &quot;<xsl:value-of select="$query" />&quot; in &quot;Sezioni informative&quot; - <xsl:value-of select="$tipo_1" />. La ricerca ha prodotto <xsl:value-of select="$max" /> risultati 
	<br /><br />
	<a><xsl:attribute name="href"><xsl:value-of select="$previous_url"/></xsl:attribute>Torna al riepilogo risultati </a><span class="freccia_area_personale"> <xsl:text disable-output-escaping="yes"><![CDATA[&gt;]]></xsl:text></span></div>

	<div class="colonna_help"><a title="Help contestuale">
	  <xsl:attribute name="href">
	     <xsl:value-of select="concat('',$help)" />
	  </xsl:attribute>
	<img alt="Help">
	 <xsl:attribute name="src">
	   <xsl:value-of select="concat($context_cms,'/system/modules/org.opencms.frontend.acquistinrete/resources/img/punto_interrogativo.gif')" />
	 </xsl:attribute>
	 </img><span class="elemento_nascosto">collegamento all'Help contestuale</span>
	 </a></div>

</div>
    <!-- (end)Div body -->
<div class="corpo_centrale">
  <div class="colonna_verticale_sx" id="menu_terzo_livello"><h2 class="elemento_nascosto">Menù di terzo livello</h2>
  <!-- ### RESTRINGI RISULTATI PER -->
		<xsl:if test="string-length($strumento) > 0 or string-length($anno) > 0">
  		<div class="area_box" id="restringi_campo"><!--<h2>Le parole più cercate</h2>-->

			<div class="areabox_titolo_help">
			  <span class="areabox_titolo_restringi_left">FILTRI SELEZIONATI:</span><span class="areabox_titolo_restringi_help">
			  <a  title="Help contestuale Filtri selezionati">
			  <xsl:attribute name="href">
	    			 <xsl:value-of select="concat('',$help_fil)" />
	  		  </xsl:attribute>
			  <img class="punto_interrogativo_2" alt="Help filtri">
			  <xsl:attribute name="src">
	   <xsl:value-of select="concat($context_cms,'/system/modules/org.opencms.frontend.acquistinrete/resources/img/punto_interrogativo_2.gif')" />
	 </xsl:attribute>
	   </img><span class="elemento_nascosto">collegamento all'Help contestuale</span></a></span>
			</div>

			<div class="area_box_contenuto">
					<xsl:if test="string-length($strumento) > 0">
					<form class="restringi_campo" id="elimina_strumento">
				    <xsl:attribute name="action">
				      <xsl:value-of select="concat($context_cms, '/risultati.jsp')" />
				    </xsl:attribute>
				    <fieldset>
				     <input type="hidden">
				    <xsl:attribute name="name">
				      <xsl:value-of select="$field" />
				    </xsl:attribute>
				     <xsl:attribute name="value">
				      <xsl:value-of select="$query" />
				    </xsl:attribute>
				    </input>
				    <input name="fl" type="hidden">
				    <xsl:attribute name="value">
				      <xsl:value-of select="concat('tipo_1:',$tipo_1)" />
				    </xsl:attribute>
				    </input>
				    <xsl:if test="string-length($anno) > 0">
				    <input name="fl" type="hidden">
				    <xsl:attribute name="value">
				      <xsl:value-of select="concat('anno_pubblicazione:',$anno)" />
				    </xsl:attribute>
				    </input>
				    </xsl:if>
						<label class="elimina_label" id="strumento_ins" for="strumento">Strumento: <strong><xsl:value-of select="$strumento"/></strong></label>

<br />
                        <input class="elimina_button" id="strumento" type="image" alt="Elimina" >
                        <xsl:attribute name="src">
	   <xsl:value-of select="concat($context_cms,'/system/modules/org.opencms.frontend.acquistinrete/resources/img/elimina.gif')" />
	 </xsl:attribute>
	  </input>
					</fieldset>
			  	</form>

						
</xsl:if>
<xsl:if test="string-length($strumento) > 0 and string-length($anno) > 0">
<br /> <br />
</xsl:if>		

					<xsl:if test="string-length($anno) > 0">
					
					<form class="restringi_campo" id="elimina_anno">
				    <xsl:attribute name="action">
				      <xsl:value-of select="concat($context_cms, '/risultati.jsp')" />
				    </xsl:attribute>
				    <fieldset>
				     <input type="hidden">
				    <xsl:attribute name="name">
				      <xsl:value-of select="$field" />
				    </xsl:attribute>
				     <xsl:attribute name="value">
				      <xsl:value-of select="$query" />
				    </xsl:attribute>
				    </input>
				    <input name="fl" type="hidden">
				    <xsl:attribute name="value">
				      <xsl:value-of select="concat('tipo_1:',$tipo_1)" />
				    </xsl:attribute>
				    </input>
				     <xsl:if test="string-length($strumento) > 0">
				    <input name="fl" type="hidden">
				    <xsl:attribute name="value">
				      <xsl:value-of select="concat('strumento:',$strumento)" />
				    </xsl:attribute>
				    </input>
				    </xsl:if>
						<label class="elimina_label" id="anno_ins" for="anno">Anno pubblicazione: <strong><xsl:value-of select="$anno"/></strong></label>

<br />
                        <input class="elimina_button" id="anno" type="image" alt="Elimina" >
                        <xsl:attribute name="src">
	   <xsl:value-of select="concat($context_cms,'/system/modules/org.opencms.frontend.acquistinrete/resources/img/elimina.gif')" />
	 </xsl:attribute>
	  </input>
					</fieldset>
			  	</form>
	</xsl:if>

		</div>		</div>

		</xsl:if>
		<xsl:if test="string-length($strumento) &lt; 1 or string-length($anno) &lt; 1">
		<div class="area_box" id="restringi_campo2"><!--<h2>Le parole più cercate</h2>-->
			<div class="areabox_titolo_help">
			  <span class="areabox_titolo_restringi_left">RESTRINGI I RISULTATI PER:</span><span class="areabox_titolo_restringi_help">
			  <a  title="Help contestuale restringi i risultati per">
			  <xsl:attribute name="href">
	    			 <xsl:value-of select="concat('',$help_res)" />
	  		  </xsl:attribute>
			  <img class="punto_interrogativo_2" alt="Help filtri da selezionare">
			  <xsl:attribute name="src">
	   <xsl:value-of select="concat($context_cms,'/system/modules/org.opencms.frontend.acquistinrete/resources/img/punto_interrogativo_2.gif')" />
	 </xsl:attribute>
	   </img><span class="elemento_nascosto">collegamento all'Help contestuale</span></a></span>
			</div>
			<div class="area_box_contenuto">
				<form class="restringi_campo">
				    <xsl:attribute name="action">
				      <xsl:value-of select="concat($context_cms, '/risultati.jsp')" />
				    </xsl:attribute>
				    <fieldset>
				     <input type="hidden">
				    <xsl:attribute name="name">
				      <xsl:value-of select="$field" />
				    </xsl:attribute>
				     <xsl:attribute name="value">
				      <xsl:value-of select="$query" />
				    </xsl:attribute>
				    </input>
				    <input name="fl" type="hidden">
				    <xsl:attribute name="value">
				      <xsl:value-of select="concat('tipo_1:',$tipo_1)" />
				    </xsl:attribute>
				    </input>
				    <xsl:if test="string-length($anno) > 0">
				    <input name="fl" type="hidden">
				    <xsl:attribute name="value">
				      <xsl:value-of select="concat('anno_pubblicazione:',$anno)" />
				    </xsl:attribute>
				    </input>
				    </xsl:if>
				    <xsl:if test="string-length($strumento) > 0">
				    <input name="fl" type="hidden">
				    <xsl:attribute name="value">
				      <xsl:value-of select="concat('strumento:',$strumento)" />
				    </xsl:attribute>
				    </input>
				    </xsl:if>
					<xsl:if test="string-length($anno) &lt; 1">
						<label id="anno_ins" for="anno">Anno</label><br />

						<select id="anno" size="1" name="fl">
							<option selected="selected" value="">Tutti gli anni</option>
       <xsl:for-each select="//lst[@name = 'anno_pubblicazione']/int[. > 0]">
			<xsl:sort select="."  order="descending"/>
			<xsl:variable name="count" select="."/>
			<xsl:variable name="name" select="@name"/>
			<option>
			  <xsl:attribute name="value"> 
			    <xsl:value-of select="concat('anno_pubblicazione:',$name)"/>
			  </xsl:attribute>
			  <xsl:value-of select="$name"/>
			</option>
	</xsl:for-each>
						</select>

					</xsl:if>
					<xsl:if test="string-length($strumento) &lt; 1">
						<label id="strumento_ins" for="strumento">Strumento</label><br />

						<select id="strumento" size="1" name="fl">
							<option selected="selected" value="">Tutti gli strumenti</option>
       <xsl:for-each select="//lst[@name = 'strumento']/int[. > 0]">
			<xsl:sort select="."  order="descending"/>
			<xsl:variable name="count" select="."/>
			<xsl:variable name="name" select="@name"/>
			<option>
			  <xsl:attribute name="value"> 
			    <xsl:value-of select="concat('strumento:',$name)"/>
			  </xsl:attribute>
			  <xsl:value-of select="$name"/>
			</option>
	</xsl:for-each>

						</select>

					</xsl:if>
					<div class="fieldset_centrati">
						<input class="button" id="aggiorna" type="submit" value="AGGIORNA I RISULTATI" name="AGGIORNA I RISULTATI" />
					</div>
				</fieldset>
			  	</form>
			</div>
		</div>
		</xsl:if>
  <!-- ### RESTRINGI RISULTATI PER - END -->
</div>

	<div class="colonna_verticale_contenuti_2colonne">
  
<!-- ### RISULTATI RICERCA - START -->			
		<div class="area_box" id="box_risultati_ricerca"><h2 class="elemento_nascosto">Risultati ricerca</h2>
			<div class="areabox_titolo_help">
			  <div class="areabox_titolo_interno_left"><div class="areabox_help_spacer"> </div>RISULTATI DELLA RICERCA:</div>
			</div>
	  <div class="area_box_contenuto">
			  	<div class="area_personale_sezioni">
					<ul>
       <xsl:for-each select="//result/doc">
         <xsl:variable name="id" select="str[@name = 'id']"/>
         
         <xsl:variable name="source" select="str[@name = 'source']"/>
         <xsl:variable name="snippet" select="concat( ' ', //lst[@name='highlighting']/lst[@name = $id]/arr[@name = 'content'])"/>
        
         <xsl:variable name="keywords">
            <xsl:for-each select="arr[@name='keywords']/str">
               <xsl:value-of select="concat('&amp;keywords=',.)"/>
            </xsl:for-each>
         </xsl:variable>
				<li>
				
					<a>
						<xsl:attribute name="href"><xsl:value-of select="concat($url_risorsa,'id=',$id,'&amp;source=', $source, $keywords )"/></xsl:attribute>
						
						<xsl:if test="str[@name = 'tipologia'] = 'documenti' or $source = 'PD'  or $source = 'ND'">
						
						<xsl:attribute name="onclick">window.open('<xsl:value-of select="concat($url_risorsa,'id=',$id,'&amp;source=', $source, $keywords)"/>');return false;</xsl:attribute>
						</xsl:if>
					    <xsl:attribute name="title"><xsl:value-of select="concat('Visualizza risorsa: ', str[@name = 'titolo'], ' ', substring-before(date[@name ='data_risorsa'], 'T'))"/></xsl:attribute>
						<xsl:if test="string-length(str[@name = 'iniziativa']) > 0">
						<xsl:value-of select="concat(str[@name ='iniziativa'], ' -  ')"/> 
						</xsl:if>
						<xsl:if test="string-length(str[@name = 'titolo']) > 0">
						<strong>
						<xsl:value-of select="str[@name = 'titolo']"/> 
						</strong>
						
						</xsl:if>
						<xsl:if test="str[@name = 'tipologia'] = 'documenti' or $source = 'PD'  or $source = 'ND'">
						<xsl:value-of select="concat(' (', str[@name = 'formato'], ' - ' , long[@name = 'dimensione'] , ' byte) ')"/> 
						</xsl:if>
						<xsl:if test="string-length(str[@name = 'titolo']) > 0">
						<xsl:value-of select="' - '"/> 
						</xsl:if>
						<xsl:value-of select="concat(' ', substring-before(date[@name ='data_risorsa'], 'T'), ' ')"/> 
<span class="freccia_area_personale"> <xsl:text disable-output-escaping="yes"><![CDATA[&gt;]]></xsl:text></span>
						</a>
						<xsl:choose>
						
						 <xsl:when test="string-length($snippet) > 0">
						 <br />
						  <xsl:call-template name="removeHtmlTags">
        						<xsl:with-param name="html" select="concat($snippet,'')" />
    						  </xsl:call-template>
						  </xsl:when>
						    <xsl:when test="string-length(str[@name = 'content']) > 0">
						 <br />
						  <xsl:call-template name="removeHtmlTags">
        						<xsl:with-param name="html" select="concat(substring(str[@name = 'content'],0,80),'')" />
    						  </xsl:call-template>
    						  </xsl:when>
    						  <xsl:otherwise/>
						</xsl:choose>
						
					
			</li>
		</xsl:for-each>
					</ul>
				</div>
		  </div>
		</div>
       <xsl:if test="($from > 0) or ($max > $from + $entries)">
	 <div class="vetrina_paginazione_ricerca">
               <xsl:if test="$from > 0">
                 <div class="active"><a title="Vai alla prima pagina"> <xsl:attribute name="href"><xsl:value-of select="concat($pagina_url,'1')"/></xsl:attribute>Prima</a></div>
		 <div class="vetrina_paginazione-barretta_vert">/</div>
		 <div class="active"><a title="Vai alla pagina precedente"> <xsl:attribute name="href"><xsl:value-of select="concat($pagina_url,$from - $entries)"/></xsl:attribute>Precedente</a></div>
	         <div class="vetrina_paginazione-barretta_vert"> | </div>
		 
	       </xsl:if>
	       <xsl:if test="$from = 0">
                 <div class="inactive">Prima</div>
		 <div class="vetrina_paginazione-barretta_vert">/</div>
		 <div class="inactive">Precedente</div>
	         <div class="vetrina_paginazione-barretta_vert"> | </div>
		 
	       </xsl:if>	


<xsl:call-template name="tplPaging">

<xsl:with-param name="numberOfItems" select="$max"></xsl:with-param>
<xsl:with-param name="from" select="$from"></xsl:with-param>
<xsl:with-param name="UrlPage" select="$pagina_url"></xsl:with-param>
<xsl:with-param name="nentries" select="$entries"></xsl:with-param>
</xsl:call-template>
			<xsl:if test=" $max > $from +$entries">
			<div class="vetrina_paginazione-barretta_vert"> | </div>
			<xsl:variable name="last" select="floor(number($max div $entries) )*$entries" />

			  <div class="active"><a title="Vai alle pagina successiva"> <xsl:attribute name="href"><xsl:value-of select="concat($pagina_url,$from+$entries)" /></xsl:attribute>Successiva</a></div>
		          <div class="vetrina_paginazione-barretta_vert">/</div>
		           <div class="active"><a title="Vai all'ultima pagina"> <xsl:attribute name="href"><xsl:value-of select="concat($pagina_url,$last)"/></xsl:attribute>Ultima</a></div>
		
		        </xsl:if>
		        <xsl:if test="$from +$entries > $max">
                	 <div class="inactive">Successiva</div>
			 <div class="vetrina_paginazione-barretta_vert">/</div>
			 <div class="inactive">Ultima</div>
		        </xsl:if>
                      </div>

	 </xsl:if>
</div>
	</div>
	</xsl:template>
  <xsl:template name="tplPaging">
  <!-- Identifies the number of items in the list -->
  <xsl:param name="numberOfItems" />
  <!-- Optional parameter identifying the default selected page. Default is 1 -->
    <xsl:param name="from" />
    <xsl:param name="UrlPage" />
    <xsl:param name="nentries" />
<!-- Calculate the maximum number of pages to show in the paging component -->
<xsl:variable name="currentPage" select="floor(number($from div $nentries))+1" />
<xsl:variable name="numberOfPages" select="floor(number($numberOfItems div $nentries))+1" />
<!-- Calaulate the starting position of the numbers -->
<xsl:variable name="startPage">
	  <xsl:choose>
	    <xsl:when test="$currentPage > 3">
              <xsl:choose>
		<xsl:when test="$currentPage +3 > $numberOfPages and $numberOfPages - 5 > 0">
		  <xsl:value-of select="$numberOfPages - 4"/>
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
	  </xsl:call-template>

</xsl:template>
<xsl:template name="tplNumber">
	  <xsl:param name="current"/>
	  <xsl:param name="number"/>
	  <xsl:param name="max"/>
	  <xsl:param name="url"/>
	  <xsl:param name="first"/>
	  <xsl:param name="nentries"/>

	  <xsl:choose>
	    <xsl:when test="$number = $current">
	      <!-- Show current page without a link -->
	      <div class="active">
	      <a href="#" class="voce_2liv_selezionata"> 
	       <xsl:attribute name="title">
		    <xsl:value-of select="concat('Vai alla pagina ', $number)"/>
	       </xsl:attribute>
	        <xsl:value-of select="$number"/>
	      </a>
	     </div>
	    </xsl:when>
	    <xsl:otherwise>
	      <div class="active">
	      <a> 
	       <xsl:attribute name="href">
		    <xsl:value-of select="concat($url, (($number - 1)*$nentries))"/>
	       </xsl:attribute>
	       <xsl:attribute name="title">
		    <xsl:value-of select="concat('Vai alla pagina ', $number)"/>
	       </xsl:attribute>
	        <xsl:value-of select="$number"/>
	      </a>
	     </div>
	    </xsl:otherwise>
	  </xsl:choose>
	 
	  <!-- Recursively call the template untill we reach the max number of pages -->

	  <xsl:if test="$max > $number">
	    <div class="vetrina_paginazione-barretta_vert"> | </div>
	    <xsl:call-template name="tplNumber">
	      <xsl:with-param name="current" select="$current"/>
	      <xsl:with-param name="number" select="$number+1"/>
	      <xsl:with-param name="max" select="$max"/>
	      <xsl:with-param name="url" select="$url"/>
	      <xsl:with-param name="first" select="0"/>
	      <xsl:with-param name="nentries" select="$nentries"/>
	    </xsl:call-template>
	  </xsl:if>
</xsl:template>

<xsl:template name="removeHtmlTags" >
    <xsl:param name="html"/>
    <xsl:choose>
        <xsl:when test="contains($html, '&lt;')">
            <xsl:value-of select="substring-before($html, '&lt;')" disable-output-escaping="yes"/>
            <!-- Recurse through HTML -->
            <xsl:call-template name="removeHtmlTags">
                <xsl:with-param name="html" select="substring-after($html, '&gt;')"/>
            </xsl:call-template>
        </xsl:when>
        <xsl:otherwise>
            <xsl:value-of select="$html" disable-output-escaping="yes"/>
        </xsl:otherwise>
    </xsl:choose>
</xsl:template>
<xsl:template name="replaceQuery">
   <xsl:param name="stringIn"/>
   <xsl:variable name="primor" >
      <xsl:call-template name="replaceCharsInString">
        <xsl:with-param name="stringIn" select="$stringIn"/>
        <xsl:with-param name="charsIn" select="'%25'"/>
        <xsl:with-param name="charsOut" select="'%'"/>
      </xsl:call-template>
    </xsl:variable>
    <xsl:variable name="secr" >
      <xsl:call-template name="replaceCharsInString">
        <xsl:with-param name="stringIn" select="$primor"/>
        <xsl:with-param name="charsIn" select="'%23'"/>
        <xsl:with-param name="charsOut" select="'#'"/>
      </xsl:call-template>
    </xsl:variable>
      <xsl:variable name="terzor" >
      <xsl:call-template name="replaceCharsInString">
        <xsl:with-param name="stringIn" select="$secr"/>
        <xsl:with-param name="charsIn" select="'%2B'"/>
        <xsl:with-param name="charsOut" select="'+'"/>
      </xsl:call-template>
    </xsl:variable>
      <xsl:call-template name="replaceCharsInString">
        <xsl:with-param name="stringIn" select="$terzor"/>
        <xsl:with-param name="charsIn" select="'%26'"/>
        <xsl:with-param name="charsOut" select="'&amp;'"/>
      </xsl:call-template>
</xsl:template>
<xsl:template name="replaceCharsInString">
  <xsl:param name="stringIn"/>
  <xsl:param name="charsIn"/>
  <xsl:param name="charsOut"/>
  <xsl:choose>
    <xsl:when test="contains($stringIn,$charsIn)">
      <xsl:value-of select="concat(substring-before($stringIn,$charsIn),$charsOut)"/>
      <xsl:call-template name="replaceCharsInString">
        <xsl:with-param name="stringIn" select="substring-after($stringIn,$charsIn)"/>
        <xsl:with-param name="charsIn" select="$charsIn"/>
        <xsl:with-param name="charsOut" select="$charsOut"/>
      </xsl:call-template>
    </xsl:when>
    <xsl:otherwise>
      <xsl:value-of select="$stringIn"/>
    </xsl:otherwise>
  </xsl:choose>
</xsl:template>


</xsl:stylesheet>
