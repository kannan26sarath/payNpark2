<?php

$conn = mysqli_connect("localhost","17lemca049", "1174", "17lemca049");
$park_cat=$_GET['park_cat'];

$records = mysqli_query($conn,"SELECT * FROM `tbl_fair` WHERE `catgry`='$park_cat'");

$data = array();

while($row = mysqli_fetch_assoc($records))
{
    $data[] = $row; 
}

echo json_encode($data);
mysqli_close($conn);

?>
