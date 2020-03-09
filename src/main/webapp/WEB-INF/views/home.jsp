
<html>
<head>
<title>Home</title>
</head>
<body>
<h1>Hello world!</h1>

<P>The time on the server is ${serverTime}.</p>

<form action="user" method="post">

    <label for="Name">Name:</label>
    <input type="text" name="Name"><br> 
    <label for="ID">Student ID:</label>
    <input type="text" name="ID"><br> 
    <label for="Plate">Plate Number:</label>
    <input type="text" name="Plate"><br> 

    <label for="Color">Choose a car color:</label>
    <select id="Color" name="Color">
        <option value="White">White</option>
        <option value="Black">Black</option>
        <option value="Blue">Blue</option>
        <option value="Red">Red</option>
    </select>
    <br>

    <label for="Manu">Choose a car manufacturer:</label>
    <select id="Manu" name="Manu">
        <option value="Volvo">Volvo</option>
        <option value="Saab">Saab</option>
        <option value="Fiat">Fiat</option>
        <option value="Audi">Audi</option>
    </select>
    <br>

    <label for="Model">Choose a car model:</label>
        <select id="Model" name="Model">
            <option value="Sedan">Sedan</option>
            <option value="SUV">SUV</option>
            <option value="Truck">Truck</option>
        </select>
    <br>

    <input type="submit" value="Login">
</form>
</body>
</html>