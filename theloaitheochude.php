
<?php 
	require "connectServer.php";

	class TheLoai{
		function TheLoai($idtheloai, $Idchude, $tentheloai, $hinhtheloai){
			$this -> PK_iMaTheLoai = $idtheloai;
			$this -> FK_iMaChuDe = $Idchude;
			$this -> sTenTheLoai= $tentheloai;
			$this -> sHinhAnhTL = $hinhtheloai;
		}
	}

	$arraytheloai = array();
	$idchude = $_POST['idchude'];
	$query = "SELECT *FROM tbl_TheLoai WHERE FK_iMaChuDe = '$idchude'";
	$data = mysqli_query($con, $query);

	while($row = mysqli_fetch_assoc($data)){
		array_push($arraytheloai, new TheLoai($row['PK_iMaTheLoai'], $row['FK_iMaChuDe'], $row['sTenTheLoai'], $row['sHinhAnhTL']));
	}

	echo json_encode($arraytheloai);
 ?>
