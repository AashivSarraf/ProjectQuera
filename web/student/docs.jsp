<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.collegequera.dto.Docs"%>
<%@page import="java.util.List"%>
<%@page import="com.collegequera.dao.UserDao"%>
<%@include file="../blocks/header.jsp" %>
<%@include file="../blocks/studentmenu.jsp" %>

<%
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    UserDao uDao=new UserDao();
    List<Docs> list=uDao.listDocs(branch);
%>

<div class="tm-right-column">
    <div class="tm-content-div">
        
        <h1 class='text-danger' style="background-color: pink">View Documents</h1>
        <hr>
        
        <table class="table table-hover">
            <tr>
                <th>Faculty</th>
                <th>Date</th>
                <th>Download</th>
            </tr>
            <% for(Docs d:list) { %>
            <tr>
                <td><%=d.getFacName()%></td>
                <td><%=sdf.format(d.getUploadDate())%></td>
                <td>
                    <a target="_blank" href="<%=path%>/assets/docs/<%=d.getFacid()%>/<%=d.getFileName()%>">
                        Download
                    </a>
                </td>
            </tr>
            <% } %>
        </table>
        
    </div>
</div>    
<%@include file="../blocks/footer.jsp" %>
