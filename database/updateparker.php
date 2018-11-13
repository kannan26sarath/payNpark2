<?php

$conn = mysqli_connect("localhost","17lemca049", "1174", "17lemca049");


$park_ids=$_GET['park_id'];
$intparkid=(int)$_GET['park_id'];
$amounts=$_GET['amount'];


$query1 = "INSERT INTO `tbl_amount` (`id`, `parkid`, `returndate`, `amount`) VALUES (NULL, '$park_ids', CURRENT_TIMESTAMP, '$amounts');";
$query = "UPDATE `tbl_park` SET `park_status`=1 WHERE `park_id`=$intparkid;";


if(mysqli_query($conn, $query))
{
     echo  "Amount Payed Successfully AND";
}
else
{
     echo "Amount Payed Failed AND ";
}
if(mysqli_query($conn, $query1))
{
     echo  "De allocated Successfully  ";
}
else
{
     echo "De allocated Failed   ";
}

mysqli_close($conn);

?>