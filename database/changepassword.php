<?php

$conn = mysqli_connect("localhost","17lemca049", "1174", "17lemca049");

$username=$_GET['username'];
$password=$_GET['password'];
$npassword=$_GET['npassword'];


$query1= "SELECT * FROM `tbl_login` WHERE `username`='$username' AND `password`='$password';";
$query2= "UPDATE `tbl_login` SET `password`='$npassword' where `username`='$username';";

if(mysqli_query($conn, $query1))
{
    
	 if(mysqli_query($conn, $query2))
{
     echo "password changed successfully";
	 
}
	 
}
else
{
     echo "password change failed";
	 
}

mysqli_close($conn);

?>