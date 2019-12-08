<?php
$userid = $_POST['userid'];
$washer_num = $_POST['washer_num'];

$user = "";
$pass = "";
$host= "localhost";
$dbname="";

if(washerSTATE == 0){
   $con = mysqli_connect($host,$user,$pass,$dbname);
   if($washer_num<9){
      $sql="update washer set userID = '$userid', washerSTATE = 1, washerEND = DATE_ADD(now(), INTERVAL 38 minute) where washerNUM = $washer_num";
   }
   else {
      $sql="update washer set userID = '$userid', washerSTATE = 1, washerEND = DATE_ADD(now(), INTERVAL 45 minute) where washerNUM = $washer_num";
   }
}

echo "$sql";
if(mysqli_query($con,$sql)){
   echo  "success";
   
}else{
   echo "failed";
}
?>
