<%@page import="java.util.ArrayList"%>
<%@page import="com.collegequera.dao.UserDao"%>
<!-- REMAINING-->

<%@include file="../blocks/header.jsp" %>
<%@include file="../blocks/studentmenu.jsp" %>

<%
    UserDao uDao=new UserDao();
    ArrayList<User> list=uDao.selfliststudents(branch);//,email);
%>

<div class="tm-right-column">
    <div class="tm-content-div">
        
        <h1 class='text-danger' style="background-color: pink">Welcome Student</h1>
        <hr>
        <table class="table table-hover">
            <tr>
                <th>Student Name</th>
                <th>Email</th>
            </tr>
            <% for(User u:list) { %>
            <tr>
                <td><%=u.getUsername()%></td>
                <td><%=u.getEmail()%></td>
            </tr>
            <% } %>
        </table>
        
    </div>
</div>    

<%@include file="../blocks/footer.jsp" %>