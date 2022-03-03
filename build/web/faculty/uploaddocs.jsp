
<%@include file="../blocks/header.jsp" %>
<%@include file="../blocks/facultymenu.jsp" %>

<div class="tm-right-column">
    <div class="tm-content-div">
      
        <h1 class="text-danger" style="background-color: pink">Upload Documents</h1>
        <hr>
        <form action="<%=path%>/faculty/upload" enctype="multipart/form-data" method="post">
            <input type="file" name="myfile" class="form-control" required>
            <br>
            <button type="submit" class="btn btn-success">Send File</button>
        </form>
        
    </div>
</div>
            
<%@include file="../blocks/footer.jsp" %>