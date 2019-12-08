<?php
$user = "";
$pass = "";
$host= "localhost";
$dbname="";

$con = mysqli_connect($host,$user,$pass,$dbname);

if(mysqli_connect_error($con)){
	echo "DB connect error : ". mysqli_connect_error();
}
$sql = "select washerSTATE,userID,washerEND from washer;";

$result = mysqli_query($con, $sql);
$response = array();
while($row = mysqli_fetch_array($result)){
	array_push($response, array("washerSTATE"=>$row[0],"userID"=>$row[1],"washerEND"=>$row[2]));
}
echo json_encode(array("response"=>$response));
mysqli_close($con);
?>