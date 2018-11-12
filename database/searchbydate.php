
<?php

$conn = mysqli_connect("localhost","17lemca049", "1174", "17lemca049");

$pid=$_GET['park_date'];


//format(cast(CREATED_TS as date), 'MM/dd/yyyy')
//$records = mysqli_query($conn,"SELECT * FROM `tbl_park` WHERE CONVERT(VARCHAR(10),cast(`park_date` as date),101)=$pid");
$records = mysqli_query($conn,"SELECT * FROM `tbl_park` WHERE DATE_FORMAT(park_date,'%m/%d/%y')='$pid'");

$data = array();

while($row = mysqli_fetch_assoc($records))
{
    $data[] = $row; 
}

echo json_encode($data);
mysqli_close($conn);

?>