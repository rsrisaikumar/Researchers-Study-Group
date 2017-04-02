<%-- 
    Document   : terminate
    Created on : Mar 21, 2016, 8:18:57 PM
    Author     : rsrisaikumar
--%>

        <c:if test="${theUser.getName()==null}">
            <script>
                window.location.replace("login.jsp");
                </script>
        </c:if>