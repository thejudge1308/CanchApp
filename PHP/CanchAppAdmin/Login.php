<?php
    $con = mysqli_connect("localhost", "root", "", "canchapp");
    
    $rut = $_POST["rut"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM administrador WHERE rut = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $rut, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $rut, $nombre, $apellido, $password);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["nombre"] = $nombre;
        $response["apellido"] = $apellido;
        $response["password"] = $password;
    }
    
    echo json_encode($response);
?>