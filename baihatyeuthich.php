<?php  
    require "connectServer.php";
    class BaiHat{
    	function BaiHat($idbaihat, $tenbaihat, $hinhbaihat, $casi, $linkbaihat, $luotthich){
         $this-> PK_iMaBaiHat = $idbaihat;
         $this -> sTenBaiHat = $tenbaihat;
         $this -> sHinhAnhBH= $hinhbaihat;
         $this -> sCaSi = $casi;
         $this -> sLinkBH = $linkbaihat;
         $this -> sLuotThich = $luotthich;
    	}
    }
    $arraycasi = array();
    $query = "SELECT * FROM tbl_BaiHat ORDER BY sLuotThich DESC LIMIT 5";
    $data= mysqli_query($con, $query);
    while ($row = mysqli_fetch_assoc($data)) {
    	array_push($arraycasi, new BaiHat($row['PK_iMaBaiHat'], $row['sTenBaiHat'], $row['sHinhAnhBH'], $row['sCaSi'], $row['sLinkBH'], $row['sLuotThich']));
    }
    echo json_encode($arraycasi);
?>