<?php 
    require "connectServer.php";
    class BaiHat{ 
        function BaiHat($idbaihat, $tenbaihat, $hinhbaihat, $casi, $linkbaihat, $luotthich){
        $this -> PK_iMaBaiHat = $idbaihat;
        $this -> sTenBaiHat = $tenbaihat;
        $this -> sHinhAnhBH = $hinhbaihat;
        $this -> sCaSi = $casi;
        $this -> sLinkBH = $linkbaihat;
        $this -> sLuotThich = $luotthich;

    }
}
  $arraydanhsachbaihat = array();
 
 
      if(isset($_POST['idtheloai'])){
       $idtheloai =$_POST['idtheloai'] ;
   $query = "SELECT * FROM tbl_BaiHat WHERE FIND_IN_SET('$idtheloai',FK_iMaTheLoai )";
}

   
   if(isset($_POST['idplaylist'])){
    $idplaylist =$_POST['idplaylist'];
    $query ="SELECT * FROM tbl_BaiHat WHERE FIND_IN_SET('$idplaylist', FK_iMaPlayList) ";
 }
    if(isset($_POST['idquangcao'])){
           $idquangcao=$_POST['idquangcao'];
              $queryquangcao = "SELECT * FROM tbl_QuangCao WHERE PK_iMaQuangCao= '$idquangcao'";
          $dataquangcao= mysqli_query($con, $queryquangcao);
         $rowquangcao = mysqli_fetch_assoc($dataquangcao);
        $id = $rowquangcao['FK_iMaBaiHat'];
         $query ="SELECT * FROM tbl_BaiHat WHERE PK_iMaBaiHat= '$id'";
    }
 
    $data= mysqli_query($con, $query);
    while($row = mysqli_fetch_assoc($data)){
        array_push($arraydanhsachbaihat, new BaiHat($row['PK_iMaBaiHat'], $row['sTenBaiHat'], $row['sHinhAnhBH'], $row['sCaSi'], $row['sLinkBH'], $row['sLuotThich']));
    }
    
echo json_encode($arraydanhsachbaihat);

  ?>