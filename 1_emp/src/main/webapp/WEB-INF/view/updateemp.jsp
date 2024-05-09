<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Employee</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
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
        
        .card {
            margin-top: 50px;
            border-radius: 15px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        
        .card-header {
            background-color: #007bff;
            color: #fff;
            border-radius: 15px 15px 0 0;
        }
        
        .card-body {
            padding: 30px;
        }
        
        .form-control {
            border-radius: 8px;
        }
        .navbar-nav .nav-link.active {
            background-color: #0056b3;
            border-radius: 5px;
        }
        
        button.btn-primary {
            width: 100%;
            border-radius: 8px;
            padding: 12px;
            font-size: 18px;
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
                    <a class="nav-link" href="admin">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="addemp">Add Employee</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="rem">Remove Employee</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="updateemp">Update Employee</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="reademp">Employee Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="empl">Employee List</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active"style="position: absolute;bottom: 0;right: 0;margin-bottom:10px"  href="home">Log Out</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header text-center">
                    <h3>Update Employee</h3>
                </div>
                <div class="card-body">
                    <form action="chemp" method="post">
                        <div class="mb-3">
                            <label for="email">Enter Username</label>
                            <input type="email" name="email" id="email" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="address">Enter Address</label>
                            <input type="text" name="address" id="address" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="designation">Enter Designation</label>
                            <input type="text" name="designation" id="designation" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="salary">Enter Salary</label>
                            <input type="text" name="salary" id="salary" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="credit_points">Enter Credit Points</label>
                            <input type="number" name="credit_points" id="credit_points" class="form-control">
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
