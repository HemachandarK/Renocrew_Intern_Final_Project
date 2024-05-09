<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }
        
        .navbar {
            background-color: #007bff;
            padding: 10px 0;
        }
        
        .navbar-brand {
            color: #fff;
            font-size: 24px;
        }
        
        .navbar-nav .nav-link {
            color: #fff;
            font-size: 18px;
            padding: 0.5rem 1rem;
            margin-right: 10px;
        }
        
        .navbar-nav .nav-link.active {
            background-color: #0056b3;
            border-radius: 5px;
        }
        
        h1 {
            text-align: center;
            margin-top: 50px;
            color: #333;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="home">Emp Management System</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="reserv">Reserve Table</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="prof">My Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="notf">Notifications</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="rel">Release Table</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="mytab">My Tables</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" style="position: absolute;bottom: 0;right: 0;margin-bottom:10px" href="home">Log Out</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<h1>Welcome Employee</h1>
</body>
</html>
