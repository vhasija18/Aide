
<?php
    
    include_once 'db-connect.php';
    
    class User{
        
        private $db;
        
        private $db_table = "users";
		public $row;
		public $json;
		public $name,$phone,$type;

		public function __construct(){
            $this->db = new DbConnect();
        }

        public function isLoginExist($username, $password){

          $query = "select * from users where Email = '$username' AND Pin = '$password' LIMIT 1";
             $result = mysqli_query($this->db->getDb(), $query);
            $row = mysqli_fetch_array($result);
			$name = $row['Name'];
			$phone =$row['Phone'];
			$type  = $row['Type'];
            if(mysqli_num_rows($result) > 0){

              $json['success'] = 1;
              $json['message'] = "Log in success";
			  $json['name'] = $row['Name'];
			  $json['phone']= $row['Phone'];
			  $json['type'] = $row['Type'];


            }
            else{
			    $json['success'] = 0;
                $json['message'] = "Log in fail";

			}

            mysqli_close($this->db->getDb());
            return $json;


        }


    }
    ?>
