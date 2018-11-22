
<?php
    
    require_once 'user.php';
    
    $username = "";
    
    $password = "";
    
    
    if(isset($_GET['Email'])){
        
        $username = $_GET['Email'];
        
    }
    
    if(isset($_GET['Pin'])){
        
        $password = $_GET['Pin'];
        
    }
    
	$userObject = new user();
    
    // Login
    
    if(!empty($username) && !empty($password)){
        
        $hashed_password = md5($password);
		
        $json_array = $userObject->loginUsers($username, $password);
        
        echo json_encode($json_array);
    }
	
	
    ?>
