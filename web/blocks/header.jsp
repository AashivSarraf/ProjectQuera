<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.collegequera.dto.User"%>

<%
    String path = application.getContextPath();//????
    
    User user=null;
    String username="";
    String branch="";
    
    Object obj=session.getAttribute("user");
    if(obj!=null){
        user=(User)obj;
        username=user.getUsername();
        branch=user.getBranch();
    }
%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>University Quera</title>
<!--
Neaty HTML Template
http://www.templatemo.com/tm-501-neaty
-->
    <!-- load stylesheets -->
  
    <link rel="stylesheet" href="<%=path%>/assets/css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <link rel="stylesheet" href="<%=path%>/assets/css/magnific-popup.css">                                <!-- Magnific pop up style, http://dimsemenov.com/plugins/magnific-popup/ -->
    <link rel="stylesheet" href="<%=path%>/assets/css/templatemo-style.css">                                   <!-- Templatemo style -->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements 8and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
          <![endif]-->
</head>
    <body>        
<div class="container">
    <div class="row">   
        <div class="tm-left-righ8t-container" >