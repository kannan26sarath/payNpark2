
<?php

$conn = mysqli_connect("localhost","17lemca049", "1174", "17lemca049");


$records = mysqli_query($conn,"SELECT `slote_id` FROM `tbl_park` WHERE cast(`park_date` as date)=CURRENT_DATE");

$data = array();

while($row = mysqli_fetch_assoc($records))
{
    $data[] = $row; 
}

echo json_encode($data);
mysqli_close($conn);

?>