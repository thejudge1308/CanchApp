<?php
    $con = mysqli_connect("localhost", "root", "", "canchapp");

    $rut = $_POST["rut"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM cancha WHERE fkadministrador = ? ");
    mysqli_stmt_bind_param($statement, "s", $rut);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $id, $nombre, $direccion, $ubicacion, $estado, $fk_administrador);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["nombre"] = $nombre; 
        $response["direccion"] = $direccion;
        $response["ubicacion"] = $ubicacion;
        $response["estado"] = $estado;
        $response["fk_administrador"] = $fk_administrador;
    }
    
    echo json_encode($response);
?>