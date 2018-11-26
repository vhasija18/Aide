<?php

$GLOBALS['DB_HOST'] = "localhost";
$GLOBALS['DB_USER'] = "root";
$GLOBALS['DB_PASSWORD'] = "";
$GLOBALS['DB_NAME'] = "aide";
$GLOBALS['conn'] = mysqli_connect($GLOBALS['DB_HOST'],$GLOBALS['DB_USER'],$GLOBALS['DB_PASSWORD'],$GLOBALS['DB_NAME']);

if(isset($_GET['RequestType'])){
	$reqType = $_GET['RequestType'];
	switch($reqType){
		case 'fetch' : fetchdetails();
			break;

		case 'signup' : signup();
			break;

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

function fetchdetails(){
	
	if(isset($_GET['id'])){
		$id = $_GET['id'];
		$dbConnection = mysqlConnection();
		if($dbConnection){
			$json['result'] = array();
			$query = "select * from users where id = '$id'  LIMIT 1";
			$result = mysqli_query($GLOBALS['conn'], $query);
			$row = mysqli_fetch_array($result);

			if(mysqli_num_rows($result) > 0){
				$json['result']['success'] = true;
				$json['result']['message'] = "Fetched";
				$json['result']['id'] = $row['id'];
				$json['result']['first_name']= $row['first_name'];
				$json['result']['last_name']=$row['last_name'];
			  	$json['result']['email'] = $row['email'];
			 	$json['result']['phone'] = $row['phone'];
			 	//$json['result']['Dob'] = $row['Dob'];
				$json['result']['type'] = $row['type'];
				$json['result']['occupation'] = $row['occupation'];
				$json['result']['longitude'] = $row['longitude'];
				$json['result']['latitude'] = $row['latitude'];

			}else{
				$json['result']['success'] = 0;
				$json['result']['message'] = "Log in fail";

			}
		}
		mysqli_close($GLOBALS['conn']);
		//print_r($json);
		
		print_r(json_encode($json));
		return json_encode($json);
	}

}


