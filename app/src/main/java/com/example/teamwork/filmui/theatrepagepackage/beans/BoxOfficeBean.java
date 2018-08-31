package com.example.teamwork.filmui.theatrepagepackage.beans;

import java.util.List;

/**
 * 票房解析类，Created by Zqy on 2018/8/2
 */

public class BoxOfficeBean {

//    {
//        "error_code":0,
//            "data":[
//        {
//            "Irank":"1",  /*排名*/
//                "MovieName":"西虹市首富",  /*电影名*/
//                "BoxOffice":"7955.57",  /*实时票房（万）*/
//                "sumBoxOffice":"152648.39",  /*累计票房（万）*/
//                "movieDay":"7",  /*上映天数*/
//                "boxPer":"70.53",  /*票房占比*/
//                "time":"2018-08-02 19:7:5"  /*数据获取时间*/
//        },
//        {
//            "Irank":"2",  /*排名*/
//                "MovieName":"狄仁杰之四大天王",  /*电影名*/
//                "BoxOffice":"1886.28",  /*实时票房（万）*/
//                "sumBoxOffice":"42809.58",  /*累计票房（万）*/
//                "movieDay":"7",  /*上映天数*/
//                "boxPer":"16.72",  /*票房占比*/
//                "time":"2018-08-02 19:7:5"  /*数据获取时间*/
//        },
//        {
//            "Irank":"3",  /*排名*/
//                "MovieName":"摩天营救",  /*电影名*/
//                "BoxOffice":"595.70",  /*实时票房（万）*/
//                "sumBoxOffice":"62104.90",  /*累计票房（万）*/
//                "movieDay":"14",  /*上映天数*/
//                "boxPer":"5.28",  /*票房占比*/
//                "time":"2018-08-02 19:7:5"  /*数据获取时间*/
//        },
//        {
//            "Irank":"4",  /*排名*/
//                "MovieName":"我不是药神",  /*电影名*/
//                "BoxOffice":"430.47",  /*实时票房（万）*/
//                "sumBoxOffice":"305356.70",  /*累计票房（万）*/
//                "movieDay":"29",  /*上映天数*/
//                "boxPer":"3.82",  /*票房占比*/
//                "time":"2018-08-02 19:7:5"  /*数据获取时间*/
//        },
//        {
//            "Irank":"5",  /*排名*/
//                "MovieName":"神奇马戏团",  /*电影名*/
//                "BoxOffice":"103.90",  /*实时票房（万）*/
//                "sumBoxOffice":"5418.46",  /*累计票房（万）*/
//                "movieDay":"13",  /*上映天数*/
//                "boxPer":"0.92",  /*票房占比*/
//                "time":"2018-08-02 19:7:5"  /*数据获取时间*/
//        },
//        {
//            "Irank":"6",  /*排名*/
//                "MovieName":"新大头儿子和小头爸爸3：俄罗斯奇遇记",  /*电影名*/
//                "BoxOffice":"95.94",  /*实时票房（万）*/
//                "sumBoxOffice":"15512.45",  /*累计票房（万）*/
//                "movieDay":"28",  /*上映天数*/
//                "boxPer":"0.85",  /*票房占比*/
//                "time":"2018-08-02 19:7:5"  /*数据获取时间*/
//        },
//        {
//            "Irank":"7",  /*排名*/
//                "MovieName":"浴血广昌",  /*电影名*/
//                "BoxOffice":"73.34",  /*实时票房（万）*/
//                "sumBoxOffice":"553.44",  /*累计票房（万）*/
//                "movieDay":"2",  /*上映天数*/
//                "boxPer":"0.65",  /*票房占比*/
//                "time":"2018-08-02 19:7:5"  /*数据获取时间*/
//        },
//        {
//            "Irank":"8",  /*排名*/
//                "MovieName":"邪不压正",  /*电影名*/
//                "BoxOffice":"51.64",  /*实时票房（万）*/
//                "sumBoxOffice":"57693.30",  /*累计票房（万）*/
//                "movieDay":"21",  /*上映天数*/
//                "boxPer":"0.46",  /*票房占比*/
//                "time":"2018-08-02 19:7:5"  /*数据获取时间*/
//        },
//        {
//            "Irank":"9",  /*排名*/
//                "MovieName":"侏罗纪世界2",  /*电影名*/
//                "BoxOffice":"21.54",  /*实时票房（万）*/
//                "sumBoxOffice":"168978.38",  /*累计票房（万）*/
//                "movieDay":"49",  /*上映天数*/
//                "boxPer":"0.19",  /*票房占比*/
//                "time":"2018-08-02 19:7:5"  /*数据获取时间*/
//        },
//        {
//            "Irank":"10",  /*排名*/
//                "MovieName":"超人总动员2",  /*电影名*/
//                "BoxOffice":"13.24",  /*实时票房（万）*/
//                "sumBoxOffice":"35266.12",  /*累计票房（万）*/
//                "movieDay":"42",  /*上映天数*/
//                "boxPer":"0.12",  /*票房占比*/
//                "time":"2018-08-02 19:7:5"  /*数据获取时间*/
//        },
//        {
//            "Irank":"11",  /*排名*/
//                "MovieName":"其它",  /*电影名*/
//                "BoxOffice":"52.69",  /*实时票房（万）*/
//                "sumBoxOffice":"0.00",  /*累计票房（万）*/
//                "movieDay":"0",  /*上映天数*/
//                "boxPer":"1.00",  /*票房占比*/
//                "time":"2018-08-02 19:7:5"  /*数据获取时间*/
//        }
//    ],
//        "reason":"success"
//    }

    private int error_code;
    private String reason;
    private List<data> datas;

    public void setError_code(int error_code){
        this.error_code = error_code;
    }

    public void setReason(String reason){
        this.reason = reason;
    }

    public void setDatas(List<data> datas){
        this.datas = datas;
    }

    public int getError_code(){
        return error_code;
    }

    public String getReason(){
        return reason;
    }

    public List<data> getDatas(){
        return datas;
    }

    public static class data{

//        {
//            "Irank":"1",  /*排名*/
//                "MovieName":"西虹市首富",  /*电影名*/
//                "BoxOffice":"7955.57",  /*实时票房（万）*/
//                "sumBoxOffice":"152648.39",  /*累计票房（万）*/
//                "movieDay":"7",  /*上映天数*/
//                "boxPer":"70.53",  /*票房占比*/
//                "time":"2018-08-02 19:7:5"  /*数据获取时间*/
//        }
        private int Irank;
        private String  MovieName;
        private Double BoxOffice;
        private Double sumBoxOffice;
        private int movieDay;
        private Double boxPer;
        private String time;

        public void setIrank(int Irank){
            this.Irank = Irank;
        }

        public void setMovieName(String MovieName){
            this.MovieName = MovieName;
        }

        public void setBoxOffice(Double BoxOffice){
            this.BoxOffice = BoxOffice;
        }

        public void setSumBoxOffice(Double sumBoxOffice){
            this.sumBoxOffice = sumBoxOffice;
        }

        public void setMovieDay(int movieDay) {
            this.movieDay = movieDay;
        }

        public void setBoxPer(Double boxPer) {
            this.boxPer = boxPer;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Double getBoxOffice() {
            return BoxOffice;
        }

        public Double getBoxPer() {
            return boxPer;
        }

        public Double getSumBoxOffice() {
            return sumBoxOffice;
        }

        public int getIrank() {
            return Irank;
        }

        public int getMovieDay() {
            return movieDay;
        }

        public String getMovieName() {
            return MovieName;
        }

        public String getTime() {
            return time;
        }

    }

}
