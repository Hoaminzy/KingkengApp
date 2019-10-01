<?php 
	require "connectServer.php";

	$luotthich =$_POST['luotthich'];
	$idbaihat = $_POST['idbaihat'];

	 $query = "SELECT sLuotThich FROM tbl_BaiHat WHERE PK_iMaBaiHat ='$idbaihat";
	 $dataluotthich = mysqli_query($con,$query);
	 $row = mysqli_fetch_assoc($dataluotthich);
	 $tongluotthich = $row['sLuotThich'];

	 if(isset($luotthich)){
	 	$tongluotthich = $tongluotthich + $luotthich;
	 	$querysum= "UPDATE tbl_BaiHat SET sLuotThich = '$tongluotthich' WHERE PK_iMaBaiHat ='idbaihat'";
	 	$dataupdate = mysqli_query($con, $querysum);
	 	if($dataupdate){
	 		echo "yes ";
	 	}else{
	 		echo "no";
	 		}
	 }

 ?>