package com.example.teamwork.filmui;

public class UriStorage {

    /**
     * 参数movie_title cinema_title date_time shuxing place seat_location imageUrl username num
     */
    public static String ticketpush_uri="http://47.106.95.140:8080/tpp//addticket";


    /**
     * 参数username
     */
    public static String ticketget_uri="http://47.106.95.140:8080/tpp//allticket";


    /**
     * 想看的电影集合,参数username,成功返回list，失败无返回
     */
    public static final String wannaget_uri="http://47.106.95.140:8080/tpp/allwant";

    /**
     * 看过的电影集合,参数username 成功返回list，失败无返回
     */
    public static final String alreadyget_uri="http://47.106.95.140:8080/tpp/allwatched";


    /**
     * 想看的电影,参数username，want（电影ID）返回参数success或者false
     */
    public static final String wannapush_uri="http://47.106.95.140:8080/tpp/want";

    /**
     * 看过的电影,参数username，watched（电影ID）返回参数success或者false
     */
    public static final String alreadypush_uri="http://47.106.95.140:8080/tpp/watched";

    /**
     * 删除想看的电影,参数username,want 成功返回success失败返回false want即想看电影id
     */
    public static final String wannadelete_uri="http://47.106.95.140:8080/tpp/deletewant";

    /**
     * 删除看过电影,参数username,watched成功返回success失败返回false want即想看电影id
     */
    public static final String alreadydelete_uri="http://47.106.95.140:8080/tpp/deletewatched";
}
