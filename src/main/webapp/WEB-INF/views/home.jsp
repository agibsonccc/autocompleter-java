<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
<script src="/autocompleter/resources/scripts/jquery.autoSuggest.minified.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/autocompleter/resources/css/autoSuggest.css" />
<script type="text/javascript">
$(function(){
	$("#text").autoSuggest("/autocompleter/autocomplete/nextchar", {minChars: 2, matchCase: true});

});
</script>
</head>
<body>
<input type="text" id="text" />
</body>
</html>
