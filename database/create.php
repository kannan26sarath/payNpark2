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



$sql = "SELECT * FROM `tbl_park` WHERE `park_id`=(SELECT max(`park_id`)FROM `tbl_park` )";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
		
		
		$sloteno=$row["slote_id"];
		$parkid=$row["park_id"];
		$username = "bivintechtips@gmail.com";
		
		
		
	$hash = "da8e21eb984cbd8c57bc311ae2fa5074dc4ed67884d350c2bc46263bdbd164c1";

	// Config variables. Consult http://api.textlocal.in/docs for more info.
	$test = "0";

	// Data for text message. This is the text message data.
	$sender = "TXTLCL"; // This is who the message appears to be from.
	
	
	$numbers =$row["park_mob"] ; // A single number or a comma-seperated list of numbers
	$message = "Hi Customer Thank you for parking with us. Parking ID- payNpark-$parkid , Slot No-$sloteno;";
	echo $message;
	
	// 612 chars or less
	// A single number or a comma-seperated list of numbers
	$message = urlencode($message);
	$data = "username=".$username."&hash=".$hash."&message=".$message."&sender=".$sender."&numbers=".$numbers."&test=".$test;
	$ch = curl_init('http://api.textlocal.in/send/?');
	curl_setopt($ch, CURLOPT_POST, true);
	curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$resultmsg = curl_exec($ch); // This is the result from the API
	curl_close($ch);
	echo $resultmsg;
	
	
		
		
		
	echo "message sent";	
    }
} else {
    echo "Error in sending message";
}



mysqli_close($conn);

?>