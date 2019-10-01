<?php 
   require "connectServer.php"
   $query="SELECT DISTINCT * FROM tbl_PlayList order by
    rand(" . date("Ymd") . ") LIMIT 3"
    class PlaylistToday{
    	function PlaylistToday($idplaylist, $ten, $hinh, $icon){
    		$this->PK_iMaPlayList=$idplaylist;
    		$this->sTenPlayList=$ten;
    		$this->sHinhAnhPL=$hinh;
    		$this->sHinhIcon=$icon;
    	}
    }
    $arraylistfortoday=array();
    $data=mysqli_query($con, $query);
    while ($row=mysqli_fetch_assoc($data)) {
    	array_push($arraylistfortoday, new PlaylistToday($row['PK_iMaPlayList'], $row['sTenPlayList'], $row['sHinhAnhPL'], $row['sHinhIcon']));
    	
    }
    echo json_encode($arraylistfortoday);
 ?>