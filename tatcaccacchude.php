<?php 


require "connectServer.php";

class ChuDe{
	function ChuDe($idchude, $tenchude, $hinhchude){
		$this -> PK_iMaChuDe = $idchude;
		$this -> sTenChuDe = $tenchude;
		$this -> sHinhAnhCD = $hinhchude;
	}
}
$arraychude = array();

$query = "SELECT * FROM tbl_ChuDe";
$data = mysqli_query($con, $query);

while($row = mysqli_fetch_assoc($data)){
array_push($arraychude, new ChuDe($row['PK_iMaChuDe'], $row['sTenChuDe'], $row['sHinhAnhCD']));

}
echo json_encode($arraychude);
 ?>
