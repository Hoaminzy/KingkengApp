<?php 
   require "connectServer.php";

   class TheLoai{
   		function TheLoai($idtheloai, $idkeychude,$tentheloai, $hinhtheloai){
   			$this->PK_iMaTheLoai=$idtheloai;
   			$this->FK_iMaChuDe=$idkeychude;
   			$this->sTenTheLoai=$tentheloai;
   			$this->sHinhAnhTL=$hinhtheloai;
   		}
   }
  class ChuDe{
  	function ChuDe($idchude, $tenchude, $hinhchude)
  	{
  		$this->PK_iMaChuDe=$idchude;
  		$this->sTenChuDe=$tenchude;
  		$this->sHinhAnhCD=$hinhchude;
  	}
  }

   $querytheloai="SELECT DISTINCT * FROM tbl_TheLoai ORDER BY rand(". date("Ymd"). ") LIMIT  4";
   $datatheloai=mysqli_query($con, $querytheloai);
    
    while ($row =mysqli_fetch_assoc($datatheloai)) {
    	array_push($arraytheloai, new ChuDe($row['PK_iMaTheLoai'], $row['FK_iMaChuDe'], $row['sTenTheLoai'], $row['sHinhAnhTL']));
    }



     $querytheloai="SELECT DISTINCT * FROM tbl_ChuDe ORDER BY rand(". date("Ymd"). ") LIMIT  4";
        $datachude=mysqli_query($con, $querychude);
       while ($row =mysqli_fetch_assoc($datachude)) {
    	array_push($arraychude, new TheLoai($row['PK_iMaChuDe'],$row['sTenChuDe'], $row['sHinhAnhCD']));
    }
    $arraychudetheloai=array('TheLoai'=>$arraytheloai, 'ChuDe'=>$arraychude);
    echo json_encode($arraychudetheloai);

 
 ?>