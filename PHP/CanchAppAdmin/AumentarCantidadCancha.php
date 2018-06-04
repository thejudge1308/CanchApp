<?php
    $con = mysqli_connect("localhost", "root", "", "canchapp");
    
    $cantidadcancha = $_POST["cantidadcancha"];
    $fkAdministrador = $_POST["rut"];
    $statement = mysqli_prepare($con, "UPDATE cantidadcanchas SET cantidadcancha= ? WHERE fkAdministrador = ?");
    mysqli_stmt_bind_param($statement, "is", $cantidadcancha, $fkAdministrador);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>