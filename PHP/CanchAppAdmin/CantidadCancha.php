<?php
    $con = mysqli_connect("localhost", "root", "", "canchapp");
  
    $fkAdministrador = $_POST["rut"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM cantidadcanchas WHERE fkAdministrador = ? ");
    mysqli_stmt_bind_param($statement, "s", $fkAdministrador);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $id, $cantidadcancha, $fkAdministrador);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["cantidadcancha"] = $cantidadcancha;
    }
    
    echo json_encode($response);
?>