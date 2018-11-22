<?php 
$host="localhost";
$User="root";
$password="";
$con = mysqli_connect($host,$User,$password);
if($con)
{
echo '<h1> Connected</h1>';
}
else
{
echo '<h1>Not Connected</h1>';
}
?>