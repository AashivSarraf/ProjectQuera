
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.collegequera.dto.Question"%>
<%@page import="java.util.List"%>
<%@page import="com.collegequera.dao.QuestionDao"%>
<%@include file="../blocks/header.jsp" %>
<%@include file="../blocks/facultymenu.jsp" %>

<%
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    QuestionDao qDao=new QuestionDao();
    List<Question> list=qDao.listByBranch(branch);
%>

<script>
    
    function send(evt,ob,qid)
    {
        var txt=ob.value;
        var code=evt.keyCode;
        if(code==13)
        {
            var URL="<%=path%>/faculty/sendans";
            var data={ans:txt,qusid:qid};
            $.post(URL,data,function(data,status)
            {
                alert(data);
                ob.value="";
            });
        }
    }
    
</script>

<div class="tm-right-column">
    <div class="tm-content-div">
        
        
        <h1 class="text-danger" style="background-color: pink">Student Questions</h1>
        <hr>
        <table class="table table-hover">
            <tr>
                <th>Student Name</th>
                <th>Question</th>
                <th>Ask Date</th>
            </tr>
            <% for(Question q:list) { %>
            <tr>
                <td><%=q.getUsername()%></td>
                <td><%=q.getQus()%></td>
                <td><%=sdf.format(q.getAskDate())%></td>
            </tr>
            <tr>
                <td></td>
                <td colspan="2">
                    <textarea value="" onkeyup="send(event,this,<%=q.getQid()%>)">
                    
                    </textarea>
                </td>
            </tr>
            <% } %>
        </table>
        
    </div>
</div>

<%@include file="../blocks/footer.jsp" %>