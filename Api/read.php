<?php
require_once('dbconnect.php');
if($_SERVER['REQUEST_METHOD']=='GET') {
  $sql = "SELECT * FROM praktikum ORDER BY npm ASC";
  $res = mysqli_query($con,$sql);
  $result = array();
  while($row = mysqli_fetch_array($res)){
    array_push($result, array('npm'=>$row[0], 'nama'=>$row[1], 'kelas'=>$row[2], 'sesi'=>$row[3]));
  }
  echo json_encode(array("value"=>1,"result"=>$result));
  mysqli_close($con);
}
?>

