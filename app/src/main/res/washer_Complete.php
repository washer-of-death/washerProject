<?php 
$washer_num = $_POST['washer_num'];

$user = "";
$pass = "";
$host= "localhost";
$dbname="";

$con = mysqli_connect($host,$user,$pass,$dbname);
$sql="update washer set userID = '', washerSTATE = 0, washerEND='' where washerNUM = $washer_num";
 
 echo "$sql";
 if(mysqli_query($con,$sql)){
	echo  "success";
	
 }else{
	echo "failed";
 }

 ?>