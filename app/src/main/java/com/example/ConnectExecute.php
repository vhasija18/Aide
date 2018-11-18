<?php

require 'C:\wamp64\www\class.phpmailer.php';
require 'C:\wamp64\www\PHPMailerAutoload.php';
require 'C:\wamp64\www\class.smtp.php';
	
if(isset($_GET['phnNo']) && !empty($_GET['phnNo'])) {
	foreach ($_GET as $name => $val)
	{
		 //echo htmlspecialchars($name . ': ' . $val) . "\n";
		 switch($name) {
			case 'phnNo' : sendSms($val);
			break;
			
			case 'emailId' : sendEmail($val);
			break;
			
			// other cases
		}
	}
    
}

function sendSms($phnNo){

	$msg = urlencode ("Welcome to AIDE");
	//$url = "http://sms4power.com/api/swsend.asp?username=t1steno&password=74620606&sender=STENOB&sendto=+$phnNo+&message=+$msg+";
	$url = "https://smsapi.engineeringtgr.com/send/?Mobile=7875529028&Password=miracle&Key=dhiraYkTHMvPd5ysLKn9EflWF&Message=".$msg."&To=".$phnNo;
	$opts = array('http'=>array('header' => "User-Agent:MyAgent/1.0\r\n"));
	$context = stream_context_create($opts);
	$result = file_get_contents($url, false, $context);
	//print $result;
	/*if($result){
	  echo "Your account has been created successffully...";
	}else{
	  echo "Something Went Wrong...";
	}*/
}

function sendEmail($email){
	
	$mail = new PHPMailer(true);

	//$mail->SMTPDebug = 4;                               // Enable verbose debug output

	$mail->isSMTP();                                      // Set mailer to use SMTP
	$mail->Host = 'smtp.gmail.com';  // Specify main and backup SMTP servers
	$mail->SMTPAuth = true;                               // Enable SMTP authentication
	$mail->Username = 'aideapplicationcanada@gmail.com';                 // SMTP username
	$mail->Password = 'aidepassword';                           // SMTP password
	$mail->SMTPSecure = 'tls';                            // Enable TLS encryption, `ssl` also accepted
	$mail->Port = 587;                                    // TCP port to connect to

	$mail->setFrom('aideapplicationcanada@gmail.com', 'AIDE Application');
	$mail->addAddress($email);     // Add a recipient
	//$mail->addAddress('ellen@example.com');               // Name is optional
	//$mail->addReplyTo('info@example.com', 'Information');
	//$mail->addCC('cc@example.com');
	//$mail->addBCC('bcc@example.com');

	//$mail->addAttachment('/var/tmp/file.tar.gz');         // Add attachments
	//$mail->addAttachment('/tmp/image.jpg', 'new.jpg');    // Optional name
	$mail->isHTML(true);                                  // Set email format to HTML

	$mail->Subject = 'Details';
	$mail->Body = 'Your service details are:';
	$mail->Body .= '<br><br><br>';
	$mail->Body .= 'Thanks for choosing AIDE';
	$mail->AltBody = 'This is the body in plain text for non-HTML mail clients';

	/*if(!$mail->send()) {
		echo 'Message could not be sent.';
		echo 'Mailer Error: ' . $mail->ErrorInfo;
	}else {
		echo 'Message has been sent';
	}*/

  
}

?>
