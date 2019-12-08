<?php
$userid = $_POST['userid'];
$userpassword = $_POST['userpassword'];
$username = $_POST['username'];

$user = "";
$pass = "";
$host= "";
$dbname="";
$con = mysqli_connect($host,$user,$pass,$dbname);
$sql="insert into user(userID,userPW,userNAME) values('".$userid."','".$userpassword."','".$username."');";
if(mysqli_query($con,$sql)){
	echo  "success";
	
}else{
	echo "failed";
}
?>