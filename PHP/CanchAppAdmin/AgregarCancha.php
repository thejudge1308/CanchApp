<?php
    $con = mysqli_connect("localhost", "root", "", "canchapp");
    
    $nombre = $_POST["nombre"];
    $direccion = $_POST["direccion"];
    $ubicacion = $_POST["ubicacion"];
    $estado = $_POST["estado"];
    $rutAdmin = $_POST["rut"];
    $statement = mysqli_prepare($con, "INSERT INTO cancha (nombre, direccion, ubicacion, estado, fkadministrador) VALUES (?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sssss", $nombre, $direccion, $ubicacion, $estado, $rutAdmin);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>