package com.example.teamwork.filmui.purchasing;

import java.io.Serializable;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class SoldAndCheck implements Serializable {
   public boolean[][]  isSold;
   public boolean[][]  isCheck;
   public SoldAndCheck(){
       isSold =new boolean[10][15];
       isCheck = new boolean[10][15];
   }
   public void  cleanCheck(){
       isCheck = new boolean[10][15];
   }
}
