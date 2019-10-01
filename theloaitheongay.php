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

   $querytheloai="SELECT DISTINCT * FROM tbl_TheLoai ORDER BY rand(". date("Ymd"). ") LIMIT  4";
   $datatheloai=mysqli_query($con, $querytheloai);
    
    while ($row =mysqli_fetch_assoc($datatheloai)) {
    	array_push($arraytheloai, new ChuDe($row['PK_iMaTheLoai'], $row['FK_iMaChuDe'], $row['sTenTheLoai'], $row['sHinhAnhTL']));
    }
    echo json_encode($arraytheloai);

 
 ?>