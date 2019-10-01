<?php 
	require "connectServer.php";

	 class Album{
    	function Album($idalbum, $tenalbum, $tencasialbum, $hinhalbum){
    		$this -> PK_iMaAlBum  = $isalbum;
    		$this -> sTenAlBum = $tenalbum;
    		$this -> sTenCaSiAB = $tencasialbum;
    		$this -> sHinhAnhAB = $hinhalbum;

    	}
    }
     $arrayalbum= array();
     $query = "SELECT * From tbl_AlBum";
     $data = mysqli_query($con, $query);
    while($row=mysqli_fetch_assoc($data)){
    	array_push($arrayalbum, new Album($row['PK_iMaAlBum'], $row['sTenAlBum'], $row['sTenCaSiAB'], $row['sHinhAnhAB']));
    }
    echo json_encode($arrayalbum);
 ?>