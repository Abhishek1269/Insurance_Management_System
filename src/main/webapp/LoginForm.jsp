<!DOCTYPE html>

<html lang="en">

<head>

 <meta charset="UTF-8">

 <title>Title</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>

<body style="background-image: url(https://nationalinsurance.nic.co.in/sites/default/files/styles/webp_converter/public/2024-10/banner3_0.png.webp?itok=ZyKpIxiy);">
    <div class ="text-center text-light rounded" style="height:80px; background-color:#483D8B">
    <h1>Insurance Management System</h1>
    </div>
  <form class="p-5 w-50 border text-light mt-5" style="margin:auto; border-radius:20px;background-color:#483D8B" action="LoginController" method="GET"> <!-- Changed to GET -->

    <h1 class="text-center">Login Form</h1>

    <% if (request.getParameter("error") != null) { %>

      <div class="alert alert-danger" role="alert">

        Invalid login credentials.

      </div>

    <% } %>

    <div class="form-group">

      <label class="form-label">Enter Id</label>

      <input type="number" name="id" class="form-control" required>

    </div>

    <div class="form-group">

      <label class="form-label">Enter UserName</label>

      <input type="text" name="username" class="form-control" required>

    </div>

    <div class="form-group">

      <label class="form-label">Enter Password</label>

      <input type="password" name="password" class="form-control" required> <!-- Password field -->

    </div>

    <div class="form-group">

      <label>Select Account Type</label>

      <div><input type="radio" name="userType" value="admin" required>Admin Login</div>

      <div><input type="radio" name="userType" value="employee" required>Employee Login</div>

      <div><input type="radio" name="userType" value="user" required>User Login</div>

    </div>

    <div>

      <input type="submit" class="btn btn-primary">

      <input type="reset" class="btn btn-primary">

    </div>
    <div class="text-center">
    <h6 class = "text-light text-center mt-2">If do not have account please register</h6>
    <a href="Register.html">Register</a>
    </div>

  </form>

</body>

</html>

