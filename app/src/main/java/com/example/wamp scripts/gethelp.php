<?php

$GLOBALS['DB_HOST'] = "localhost";
$GLOBALS['DB_USER'] = "root";
$GLOBALS['DB_PASSWORD'] = "";
$GLOBALS['DB_NAME'] = "aide";
$GLOBALS['conn'] = mysqli_connect($GLOBALS['DB_HOST'],$GLOBALS['DB_USER'],$GLOBALS['DB_PASSWORD'],$GLOBALS['DB_NAME']);

if(isset($_GET['RequestType'])){
	$reqType = $_GET['RequestType'];
	switch($reqType){
		case 'help' : gethelp();
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

function gethelp(){
	
	if(isset($_GET['help_value'])){
		$latitude = $_GET['latitude'];
		$longitude  = $_GET['longitude'];
		$help_value = $_GET['help_value'];
		$dbConnection = mysqlConnection();
		if($dbConnection){
			$json['result'] = array();
			$query = "SELECT *, ( 3959 * acos ( cos ( radians('$latitude') ) * cos( radians( latitude ) ) * cos( radians( longitude ) - radians('$longitude') ) + sin ( radians('$latitude') ) * sin( radians( latitude ) ) ) ) AS distance FROM `users` WHERE occupation = '$help_value' and type = 2  HAVING distance < 30 ORDER BY distance LIMIT 0 , 5";
			$result = mysqli_query($GLOBALS['conn'], $query);
			$row = mysqli_fetch_array($result);

			if(mysqli_num_rows($result) > 0){
				$json['result']['success'] = 1;
				$json['result']['message'] = "Help found";
				$json['result']['first_name']= $row['first_name'];
				$json['result']['last_name']=$row['last_name'];
			  	$json['result']['email'] = $row['email'];
				$json['result']['gender']= $row['gender'];
			 	$json['result']['phone'] = $row['phone'];
				$json['result']['longitude'] = $row['longitude'];
				$json['result']['latitude'] = $row['latitude'];

			}else{
				$json['result']['success'] = 0;
				$json['result']['message'] = "Help not found";

			}
		}
		mysqli_close($GLOBALS['conn']);
		//print_r($json);
		
		print_r(json_encode($json));
		return json_encode($json);
	}

}


