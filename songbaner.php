<?php 
   require "connectServer.php";
   $query="SELECT tbl_QuangCao.PK_iMaQuangCao,tbl_QuangCao.sHinhAnhQC, tbl_QuangCao.sNoiDung, tbl_QuangCao.FK_iMaBaiHat, tbl_BaiHat.sTenBaiHat, tbl_BaiHat.sHinhAnhBH FROM tbl_BaiHat INNER JOIN tbl_QuangCao ON tbl_BaiHat.PK_iMaBaiHat=tbl_QuangCao.FK_iMaBaiHat WHERE tbl_BaiHat.PK_iMaBaiHat=tbl_QuangCao.FK_iMaBaiHat";
   $data=mysqli_query($con, $query);
  class QuangCao{
    function QuangCao($MaQuangCao, $HinhAnhQC, $NoiDung, $MaBaiHat, $TenBaiHat, $HinhAnhBH){
      $this->PK_iMaQuangCao=$MaQuangCao;
       $this->sHinhAnhQC=$HinhAnhQC;
        $this->sNoiDung=$NoiDung;
         $this->FK_iMaBaiHat=$MaBaiHat;
          $this->sTenBaiHat=$TenBaiHat;
           $this->sHinhAnhBH= $HinhAnhBH;
           

    }
  }
  $mangquangcao= array();
  while ($row = mysqli_fetch_assoc($data)) {
   array_push($mangquangcao, new QuangCao($row['PK_iMaQuangCao'], $row['sHinhAnhQC'], $row['sNoiDung'], $row['FK_iMaBaiHat'], $row['sTenBaiHat'], $row['sHinhAnhBH']));
  }
  echo json_encode($mangquangcao);
 ?>

