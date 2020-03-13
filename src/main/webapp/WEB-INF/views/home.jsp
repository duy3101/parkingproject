
<html>
<head>
<title>Home</title>
</head>
<body>
<h1>Hello world!</h1>

<P>The time on the server is ${serverTime}.</p>

<form action="admin" method="post">
    <input type="Submit" value="Admin" name="Admin">
</form>

<form action="user" method="post">

    <label for="Name">Name:</label>
    <input type="text" name="Name"><br> 
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


    <label for="Manu">Manufacturer:</label>
    <input type="text" name="Manu"><br> 
    <label for="Model">Model:</label>
    <input type="text" name="Model"><br> 

    <label for="CarType">Choose a parking type:</label>
        <select id="CarType" name="CarType">
            <option value="Sedan">Sedan</option>
            <option value="Truck">Truck</option>
            <option value="Compact">Compact</option>
            <option value="SUV">SUV</option>
            <option value="Hybird">Hybird</option>
        </select>
    <br>

    <label for="ParkingType">Choose a parking type:</label>
        <select id="ParkingType" name="ParkingType">
            <option value="Normal">Normal</option>
            <option value="Carpool">Carpool</option>
            <option value="Handicapped">Handicapped</option>
            <option value="Electric">Electric</option>
            <option value="Motocycle">Motocycle</option>
        </select>
    <br>

    <input type="submit" value="Login">
</form>
</body>
</html>