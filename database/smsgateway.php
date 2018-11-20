<?php
	// Authorisation details.
	$username = "bivintechtips@gmail.com";
	$hash = "da8e21eb984cbd8c57bc311ae2fa5074dc4ed67884d350c2bc46263bdbd164c1";

	// Config variables. Consult http://api.textlocal.in/docs for more info.
	$test = "0";

	// Data for text message. This is the text message data.
	$sender = "TXTLCL"; // This is who the message appears to be from.
	$numbers = "919447155495,919072948997,919544971840"; // A single number or a comma-seperated list of numbers
	$message = "Hi Customer Thank you for parking with us. Parking ID- payNpark-21 , Slot No- 2 ,No Of Hours Parked- 5 ,Total Amount- 10.";
	// 612 chars or less
	// A single number or a comma-seperated list of numbers
	$message = urlencode($message);
	$data = "username=".$username."&hash=".$hash."&message=".$message."&sender=".$sender."&numbers=".$numbers."&test=".$test;
	$ch = curl_init('http://api.textlocal.in/send/?');
	curl_setopt($ch, CURLOPT_POST, true);
	curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$result = curl_exec($ch); // This is the result from the API
	curl_close($ch);
	echo $result;
?>