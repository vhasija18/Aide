<?php

$GLOBALS['DB_HOST'] = "localhost";
$GLOBALS['DB_USER'] = "root";
$GLOBALS['DB_PASSWORD'] = "";
$GLOBALS['DB_NAME'] = "aide";
$GLOBALS['conn'] = mysqli_connect($GLOBALS['DB_HOST'],$GLOBALS['DB_USER'],$GLOBALS['DB_PASSWORD'],$GLOBALS['DB_NAME']);

if(isset($_GET['RequestType'])){
	$reqType = $_GET['RequestType'];
	switch($reqType){
		case 'login' : login();
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

function login(){
	
	if(isset($_GET['EmailorPhone']) && isset($_GET['Pin'])){
		$emailorphone = $_GET['EmailorPhone'];
		$pin = md5($_GET['Pin']);
		$dbConnection = mysqlConnection();
		if($dbConnection){
			$json['result'] = array();
			$query = "select * from users where email = '$emailorphone' OR phone = '$emailorphone' AND pin = '$pin' LIMIT 1";
			$result = mysqli_query($GLOBALS['conn'], $query);
			$row = mysqli_fetch_array($result);

			if(mysqli_num_rows($result) > 0){
				$json['result']['success'] = true;
				//$json['message'] = "Log in success";
				$json['result']['first_name'] = $row['first_name'];
				$json['result']['last_name']= $row['last_name'];
				$json['result']['email'] = $row['email'];
				$json['result']['phone'] = $row['phone'];
				$json['result']['type'] = $row['type'];

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

function signup(){
	if(isset($_GET['dataArr'])){
		$statflag = 0;
		$dataArr=get_object_vars(json_decode($_GET['dataArr']));
		$name = $dataArr['name'];
		$phone = $dataArr['phone'];
		$email = $dataArr['email'];
		$gender = $dataArr['gender'];
		$occupation = $dataArr['occupation'];
		$dob = $dataArr['dob'];
		$user_type = $dataArr['user_type'];
		$latitude = $dataArr['latitude'];
		$longitude = $dataArr['longitude'];
		$pin = md5($dataArr['pin']);
		$dbConnection = mysqlConnection();
		if($dbConnection){
			$insertQuery = "INSERT INTO `users` (`first_name`, `email`, `phone`, `pin`, `gender`, `occupation`, `latitude`, `longitude`, `type`) VALUES ('$name','$email','$phone','$pin','$gender','$occupation','$latitude','$longitude','$user_type')";
			if(mysqli_query($GLOBALS['conn'], $insertQuery)){
				$statflag = 1;
			}else
				echo mysqli_error($GLOBALS['conn']);
		}
		
	}
	echo "Registered successfully!";
	return $statflag;
}
