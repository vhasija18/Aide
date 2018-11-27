<?php

$GLOBALS['DB_HOST'] = "localhost";
$GLOBALS['DB_USER'] = "root";
$GLOBALS['DB_PASSWORD'] = "";
$GLOBALS['DB_NAME'] = "aide";
$GLOBALS['conn'] = mysqli_connect($GLOBALS['DB_HOST'],$GLOBALS['DB_USER'],$GLOBALS['DB_PASSWORD'],$GLOBALS['DB_NAME']);

if(isset($_GET['RequestType'])){
	$reqType = $_GET['RequestType'];
	switch($reqType){
		case 'change' : changepassword();
			break;

        case 'tag'    : updatelocation();
		// other cases
	}
}

function mysqlConnection(){
	$flag = false;
	if(mysqli_connect_errno($GLOBALS['conn'])){
		$mysqlErr = "Unable to connect to Mysql Database:".mysqli_connect_errno();
	}else{
		$flag = true;
	}
	return $flag;
}

function changepassword(){

	if(isset($_GET['data'])){
		$data = get_object_vars(json_decode($_GET['data']));
		$old_pin = md5($data['old_pin']);
		$new_pin = md5($data['new_pin']);
		$id      = $data['id'];

		$dbConnection = mysqlConnection();
		if($dbConnection){
			$query = "select * from users where id = '$id'  LIMIT 1";
			$result = mysqli_query($GLOBALS['conn'], $query);
			$row = mysqli_fetch_array($result);

			if(mysqli_num_rows($result) > 0){
				if($old_pin == $row['pin'])
				{
				  $query = "UPDATE users SET PIN = '$new_pin' where  id = '$id' ";
				  $res = mysqli_query($GLOBALS['conn'], $query);
				  echo 'updated';
				}
				else{
					echo 'No match';
				}
				}

			}else{
			echo 'error';
			}
		}
		mysqli_close($GLOBALS['conn']);
		//print_r($json);

	}

function updatelocation(){

	if(isset($_GET['lati']) && isset($_GET['longi'])){
		$latitude = $_GET['lati'];
		$id = $_GET['id'];
		$longitude = $_GET['longi'];
		$dbConnection = mysqlConnection();
		if($dbConnection){

			$query = "UPDATE users SET longitude = '$longitude' , latitude ='$latitude' where id = '$id'";
			$result = mysqli_query($GLOBALS['conn'], $query);

               if(!$result)
			   {
				   die('Could not update' .mysqli_error());
			   }
			   else
			   {
				  echo 'updated';
			   }
			}
			}
		mysqli_close($GLOBALS['conn']);
		//print_r($json);

		
	}




