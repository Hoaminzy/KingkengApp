<?php 
   require "connectServer.php";


  class ChuDe{
  	function ChuDe($idchude, $tenchude, $hinhchude)
  	{
  		$this->PK_iMaChuDe=$idchude;
  		$this->sTenChuDe=$tenchude;
  		$this->sHinhAnhCD=$hinhchude;
  	}
  }


     $querytheloai="SELECT DISTINCT * FROM tbl_ChuDe ORDER BY rand(". date("Ymd"). ") LIMIT  4";
        $datachude=mysqli_query($con, $querychude);
       while ($row =mysqli_fetch_assoc($datachude)) {
    	array_push($arraychude, new TheLoai($row['PK_iMaChuDe'],$row['sTenChuDe'], $row['sHinhAnhCD']));
    }
 
    echo json_encode($arraychude);

 
 ?>