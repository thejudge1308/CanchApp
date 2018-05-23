<?php
    $con = mysqli_connect("localhost", "root", "", "canchapp");

    $id = $_POST["id"];    
    $fkadministrador = $_POST["rut"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM cancha WHERE id = ? AND fkadministrador = ? ");
    mysqli_stmt_bind_param($statement, "is", $id, $fkadministrador);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $id, $nombre, $direccion, $ubicacion, $estado, $fkadministrador);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["nombre"] = $nombre; 
        $response["direccion"] = $direccion;
        $response["ubicacion"] = $ubicacion;
        $response["estado"] = $estado;
        $response["fkadministrador"] = $fkadministrador;
    }
    
    echo json_encode($response);
?>