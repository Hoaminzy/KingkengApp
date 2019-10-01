<?php 
require "connectServer.php";
$query = "SELECT * FROM tbl_PlayList";
$data = mysqli_query($con, $query);
 class Danhsachplaylist{
    	function Danhsachplaylist($idplaylist, $ten, $hinh, $icon){
    		$this->PK_iMaPlayList=$idplaylist;
    		$this->sTenPlayList=$ten;
    		$this->sHinhAnhPL=$hinh;
    		$this->sHinhIcon=$icon;
    	}
    }
    	$arrayplaylist = array();
    while ($row=mysqli_fetch_assoc($data)) {
    	array_push($arraylistfortoday, new Danhsachplaylist($row['PK_iMaPlayList'], $row['sTenPlayList'], $row['sHinhAnhPL'], $row['sHinhIcon']));
}
echo json_encode($arrayplaylist);
   ?>
