<%@ taglib uri="http://jakarta.apache.org/taglibs/c" prefix="c" %>
<script language="javascript">
	var ctxAbife = "<c:url value="/" />";
	
	<c:url value="/home.jsp" var="linkAnagrafe">
		<c:param name="page" value="zero"/>
		<c:param name="torna" value=""/>
	</c:url>
	
	var linkAnagrafe = '<c:out value="${linkAnagrafe}"/>' + '&id_biblioteca=%ID_BIBLIOTECA%';
	
	var id_biblioteca = '<c:out value="${param.id_biblioteca}"/>';
	var id_comune = '<c:out value="${param.id_comune}"/>';
</script>