<?php
    $con = mysqli_connect("localhost", "root", "", "canchapp");
    
    $statement = mysqli_prepare($con,"SELECT * FROM cancha where id in (SELECT MAX(id) as maximo from cancha )");
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $id, $nombre, $direccion, $ubicacion, $estado, $fkadministrador);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["id"] = $id;  
        $response["nombre"] = $nombre; 
        $response["direccion"] = $direccion;
        $response["ubicacion"] = $ubicacion;
        $response["estado"] = $estado;
        $response["fkadministrador"] = $fkadministrador;
    }
    
    echo json_encode($response);
?>