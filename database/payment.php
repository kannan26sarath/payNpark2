
<?php

$conn = mysqli_connect("localhost","17lemca049", "1174", "17lemca049");
$pids=(int)$_GET['park_id'];

$records = mysqli_query($conn,"SELECT `park_id`,`park_vehno`,`park_catgry`,`park_date`,`park_mob`,`slote_id`,`park_status`, TIMESTAMPDIFF(SECOND, `park_date`, CURRENT_TIMESTAMP) FROM `tbl_park` WHERE `park_id`= $pids AND `park_status`=0");

$data = array();

while($row = mysqli_fetch_assoc($records))
{
    $data[] = $row; 
}

echo json_encode($data);
mysqli_close($conn);

?>
