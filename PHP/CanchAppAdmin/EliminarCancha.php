<?php
    $con = mysqli_connect("localhost", "root", "", "canchapp");
    
    $id = $_POST["id"];

    $statement = mysqli_prepare($con, "DELETE FROM cancha where id = ?");
    mysqli_stmt_bind_param($statement, "i", $id);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>