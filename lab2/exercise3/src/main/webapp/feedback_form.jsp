
  Created by IntelliJ IDEA.
  User: kamil
  Date: 3/7/20
  Time: 8:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Guest book</title>
</head>
<script type="text/javascript">
    function getBookContent()
    {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "GET", "http://localhost:8080/exercise3/guest-book", false );
        xmlHttp.send( null );
        var contentDiv = document.getElementById("content");
        contentDiv.innerHTML = xmlHttp.responseText;
    }
</script>
<body>
<h2>Please send your feedback</h2>
<form method="post" action="/exercise3/guest-book">
    <label for="name">Your name:  </label>
    <input id="name" name="name" /> <br/>

    <label for="email">Your email: </label>
    <input id="email" name="email"/> <br/>

    <label for="comment">Comment: </label>
    <input id="comment" name="comment"/> <br/>
    <input type="submit" value="Submit"/>

</form>

<hr/>
<div id="content"></div>
<script>
    (function() {
        getBookContent();
    })();
</script>
</body>
</html>
