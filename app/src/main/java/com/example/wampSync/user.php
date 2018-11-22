
<?php
    
    include_once 'db-connect.php';
    
    class User{
        
        private $db;
        
        private $db_table = "users";
        public function __construct(){
            $this->db = new DbConnect();
        }
        
        public function isLoginExist($username, $password){
			 
          $query = "select * from users where Email = '$username' AND Pin = '$password' LIMIT 1";
             $result = mysqli_query($this->db->getDb(), $query);
            
            if(mysqli_num_rows($result) > 0){
				
                
                
                return true;
                
            }
            
            mysqli_close($this->db->getDb());
            
            return false;
            
        }
        
        
        public function loginUsers($username, $password){
            
            $json = array();
            
            $canUserLogin = $this->isLoginExist($username, $password);
            
            if($canUserLogin){
				
				
              $json['success'] = 1;
              $json['message'] = "Log in success";
                
            }else{
               
                $json['success'] = 0;
                $json['message'] = "Log in fail";
			   }
        
           return $json;		
        }
    }
    ?>
