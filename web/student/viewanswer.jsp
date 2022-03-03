
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.collegequera.dto.Answer"%>
<%@page import="java.util.List"%>
<%@page import="com.collegequera.dto.Question"%>
<%@page import="com.collegequera.dao.QuestionDao"%>
<%@page import="com.collegequera.dao.AnswerDao"%>
<%@include file="../blocks/header.jsp" %>
<%@include file="../blocks/studentmenu.jsp" %>

<%
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    
    int qid=Integer.parseInt(request.getParameter("qus"));// request from where//????//might be i got it but then to confirm
    
    AnswerDao aDao=new AnswerDao();
    QuestionDao qDao=new QuestionDao();
    Question qus=qDao.get(qid);
    
    List<Answer> list=aDao.listByQuestion(qid);

%>

<div class="tm-right-column">
    <div class="tm-content-div">
        
        <h1 class="text-danger" style="background-color: pink">View Answer</h1>
        <hr>
        <h3>Question : <%=qus.getQus()%></h3>
        <table class="table table-hover">
            <tr>
                <th>Faculty name</th>
                <th>Date</th>
                <th>Answer</th>
            </tr>
            <% for(Answer ans:list) { %>
            <tr>
                <td><%=ans.getFacultyName()%></td>
                <td><%=sdf.format(ans.getAnsDate())%></td>
                <td><%=ans.getAns()%></td>
            </tr>
            <% } %>
        </table>
    </div>
</div>

<%@include file="../blocks/footer.jsp" %>