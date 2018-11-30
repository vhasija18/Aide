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
			
		case 'update' : update();
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
				$json['result']['message'] = "Log in success";
				$json['result']['id']=$row['id'];
				//$json['result']['first_name'] = $row['first_name'];
				//$json['result']['last_name']= $row['last_name'];
				//$json['result']['email'] = $row['email'];
				//$json['result']['phone'] = $row['phone'];
				//$json['result']['type'] = $row['type'];

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
		$msg = "";
		$dataArr=get_object_vars(json_decode($_GET['dataArr']));
		$firstname = $dataArr['firstname'];
		$lastname  = $dataArr['lastname'];
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
			$emailExistsQuery = "select * from users where email = '$email' LIMIT 1";
			$emailExists = mysqli_query($GLOBALS['conn'], $emailExistsQuery);
			if(mysqli_num_rows($emailExists) == 1)
				$msg = "The email id you have entered already exists, please enter new email id.";
			$phoneExistsQuery = "select * from users where phone = '$phone' LIMIT 1";
			$phoneExists = mysqli_query($GLOBALS['conn'], $phoneExistsQuery);
			if(mysqli_num_rows($phoneExists) == 1)
				$msg = "The phone no you have entered already exists, please enter new phone no.";
			if(mysqli_num_rows($emailExists) == 0 && mysqli_num_rows($phoneExists) == 0){
				$insertQuery = "INSERT INTO `users` (`first_name`,`last_name`, `email`, `phone`, `dob`, `pin`, `gender`, `occupation`, `latitude`, `longitude`, `type`) VALUES ('$firstname','$lastname','$email','$phone',STR_TO_DATE('$dob', '%d/%m/%Y'),'$pin','$gender','$occupation','$latitude','$longitude','$user_type')";
				if(mysqli_query($GLOBALS['conn'], $insertQuery)){
					$statflag = 1;
					echo "Registered successfully! Please login using your details.";
				}else{
					echo mysqli_error($GLOBALS['conn']);
				}
			}else{
				echo $msg;
			}
		}

	}

	return $statflag;
}

function update(){
	if(isset($_GET['dataArr'])){
		$json['result'] = array();
		$json['result'] = false;
		$msg = "";
		$dataArr=get_object_vars(json_decode($_GET['dataArr']));
		$id = $dataArr['id'];
		$firstname = $dataArr['firstname'];
		$lastname  = $dataArr['lastname'];
		$phone = $dataArr['phone'];
		$email = $dataArr['email'];
		$dob = $dataArr['dob'];
		$dbConnection = mysqlConnection();
		if($dbConnection){
			$emailExistsQuery = "select * from users where email = '$email' LIMIT 1";
			$emailExists = mysqli_query($GLOBALS['conn'], $emailExistsQuery);
			if(mysqli_num_rows($emailExists) == 1){
				$json['result'] = "email";
				$msg = "The email id you have entered already exists, please enter new email id.";
			}
			$phoneExistsQuery = "select * from users where phone = '$phone' LIMIT 1";
			$phoneExists = mysqli_query($GLOBALS['conn'], $phoneExistsQuery);
			if(mysqli_num_rows($phoneExists) == 1){
				$json['result'] = "phone";
				$msg = "The phone no you have entered already exists, please enter new phone no.";
			}
			
			if(mysqli_num_rows($emailExists) == 0 && mysqli_num_rows($phoneExists) == 0){
				$insertQuery = "update `users` set `first_name` = '$firstname',`last_name` = '$lastname',`email` = '$email',`phone` = '$phone',`dob` = STR_TO_DATE('$dob', '%d/%m/%Y') where `id` = '$id'";
				if(mysqli_query($GLOBALS['conn'], $insertQuery)){
					$json['result'] = true;
					$msg = "updated";
				}else{
					echo mysqli_error($GLOBALS['conn']);
				}
			}/*else{
				echo $msg;
			}*/
		}
	}
	
	print_r(json_encode($json));
	//return json_encode($json);
}