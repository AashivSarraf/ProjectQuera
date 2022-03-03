<%@include file="blocks/header.jsp" %>
<%@include file="blocks/menu.jsp" %>
           
<%
    String msg="" ;
    
    Object err=request.getAttribute("err");
    if(err!=null){
        msg="Verification Failed!";
    }
    
%>


<div class="tm-right-column">
    <div class="tm-content-div">
        
        <h1 class="text-danger bg-info">User Verification</h1>     
        <hr>
        <a href=""><b>Resend OTP</b></a>
        <hr>
        
        <form action="verify" method="post">
            <div class="row form-group">
                <div class="col-lg-6">
                    <input type="email" class="form-control" placeholder="Your Email" name="email" required>
                </div>
                <div class="col-lg-6">
                    <input type="password" class="form-control" placeholder="OTP" name="otp" required>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-lg-6">
                    <button type="submit" class="btn btn-success">Verify User</button>
                </div>
                <div class="col-lg-6">
                    <%=msg%>
                </div>
            </div>
        </form>
        
    </div>
</div>

<%@include file="blocks/footer.jsp" %>