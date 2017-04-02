        <c:if test="${theAdmin.getName()==null}">
            <script>
                window.location.replace("login.jsp");
                </script>
        </c:if>