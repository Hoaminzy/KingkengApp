<?php 
    require "connectServer.php";
    $query ="SELECT DISTINCT * FROM tbl_AlBum ORDER BY rand(" . date("ymd") .") LIMIT 4";
    $dataalbum = mysqli_query($con, $query);
    class Album{
    	function Album($idalbum, $tenalbum, $tencasialbum, $hinhalbum){
    		$this -> PK_iMaAlBum  = $isalbum;
    		$this -> sTenAlBum = $tenalbum;
    		$this -> sTenCaSiAB = $tencasialbum;
    		$this -> sHinhAnhAB = $hinhalbum;

    	}
    }
    $arrayalbum= array();
    while($row=mysqli_fetch_assoc($dataalbum)){
    	array_push($arrayalbum, new Album($row['PK_iMaAlBum'], $row['sTenAlBum'], $row['sTenCaSiAB'], $row['sHinhAnhAB']));
    }
    echo json_encode($arrayalbum);
 ?>