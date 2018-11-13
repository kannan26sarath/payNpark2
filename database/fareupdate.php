
<?php

$conn = mysqli_connect("localhost","17lemca049", "1174", "17lemca049");


$park_catgry=$_GET['park_catgry'];
$park_amount=$_GET['park_amount'];

$query = "UPDATE tbl_fair SET `amount`=$park_amount WHERE `catgry`='$park_catgry`';";

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