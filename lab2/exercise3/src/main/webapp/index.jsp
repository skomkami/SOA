<%@ page import="java.util.Vector" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
<script type="text/javascript">

    // var users = [{login: "test", password: "test"}, {login: "kamil", password: "kamil"}].map(function (val) {
    //     return JSON.stringify(val);
    // });

    function validate()
    {
        var login = document.getElementById("login");
        var password = document.getElementById("password");

        if(login.value.length <= 0)
        {
            alert("Enter login!");
            return false;
        }
        if(password.value.length <= 0)
        {
            alert("Enter password!");
            return false;
        }

        // var guest = JSON.stringify({login: login.value, password: password.value});
        //
        // if(!users.includes(guest)) {
        //     alert("Wrong login data");
        //     return false;
        // } else {
        //     return true;
        // }
    };

</script>
<body>
<h2>Zaloguj się</h2>

<form method="post" action="/exercise3/" onsubmit="return validate()">
    <label for="login">Login: </label>
    <input id="login" name="login"/><br/>
    <label for="password">Password: </label>
    <input type="password" id="password" name="password"/>
    <br/>
    <input type="submit" value="Log in"/>
</form>
<%
    String login = request.getParameter("login");
    String password = request.getParameter("password");

    if (login != null && password != null) {
        User current = new User(login, password);

        Vector<User> users = new Vector<User>();
        users.add(new User("test", "test"));
        users.add(new User("soa", "soa"));

        if(users.contains(current)) {
            session.setAttribute("logged", true);
            response.sendRedirect("/exercise3/feedback_form.jsp");
        } else {
            out.println("Invalid data");
        }
    }
%>
</body>
</html>
