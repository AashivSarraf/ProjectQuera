

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.collegequera.dto.Question"%>
<%@page import="com.collegequera.dao.QuestionDao"%>
<%@include file="../blocks/header.jsp" %>
<%@include file="../blocks/studentmenu.jsp" %>

<%
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    QuestionDao qDao=new QuestionDao();
    List<Question> list=qDao.listByStudent(user.getUserid());
%>

<div class="tm-right-column">
    <div class="tm-content-div">
        
        <h1 class="text-danger" style="background-color: pink">Ask Question</h1>
        <hr>
        
        <form action="<%=path%>/student/askqus" method="post">
            <textarea name="qus" cols="45" rows="6"></textarea>
            <br><br>
            <button type="submit" class="btn btn-primary">
                Send Question
            </button>
        </form>
            
            <table class="table table-hover">
                <tr>
                    <th>Question</th>
                    <th>Ask Date</th>
                    <th>Answers</th>
                </tr>
                <% for(Question q:list){ %>
                <tr>
                    <td><%=q.getQus()%></td>
                    <td><%=sdf.format(q.getAskDate())%></td>
                    <td>
                        <a href="<%=path%>/student/viewanswer.jsp?qus=<%=q.getQid()%>">View Answer</a>  <!-- ?=q.getQid()%>  why???
                                                        might be because "?" ke baad apan kuch bhi bhej sakte hai to apan ne
                                                        qus bhej diya aur usne(viewAnswer ne) waha le liya // but then to ask????
                                                        to confirm-->
                    </td>
                </tr>
                <% } %>
            </table>   
    </div>
</div>

<%@include file="../blocks/footer.jsp" %>