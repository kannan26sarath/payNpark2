<?php

$conn = mysqli_connect("localhost","17lemca049", "1174", "17lemca049");

$park_vehno=$_GET['park_vehno'];
$park_catgry=$_GET['park_catgry'];
$park_mob=$_GET['park_mob'];
$park_slote=$_GET['slote_id'];

$query = "INSERT INTO `tbl_park` (`park_vehno`, `park_catgry`,`park_mob`,`slote_id`) VALUES ( '$park_vehno', '$park_catgry','$park_mob','$park_slote');";

if(mysqli_query($conn, $query))
{
     echo "success";
}
else
{
     echo "failed";
}

mysqli_close($conn);

?>