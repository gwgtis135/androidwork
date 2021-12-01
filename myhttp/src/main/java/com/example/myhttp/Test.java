package com.example.myhttp;

import com.google.gson.Gson;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
//        System.out.println("hello");
        Gson gson = new Gson();
        EmpVO vo = new EmpVO();
        vo.setAge(10);
        vo.setFname("chol");
        String result = gson.toJson(vo);
//        System.out.println(result);

        //영화 일 경우 이런식으로 받으면 됨
        String reponse = "{\"fname\":\"홍길동\",\"age\":20}";
        EmpVO emp = gson.fromJson(reponse,EmpVO.class);
        emp.setReponse(reponse);
//        System.out.println(emp.getFname()+emp.getAge()+emp.getReponse());

        //만약 배열 안에 객체가 많다면 map으로 받으면 된다.
        //Object으로 준 이유는 데이터형이 여러가지가 들어올수 있도록 한다.
        //String은 key의 데이터형은 String만 오기 때문이다.
        Map<String, Object> map = gson.fromJson(reponse, Map.class);
//            System.out.println(map.get("fname"));
//            System.out.println(map.get("age"));

        reponse = "{\"data\":[{\"fname\":\"김유신\",\"age\":10}]}";
        map = gson.fromJson(reponse, Map.class);
        //배열로 받아야 하기때문에 list 사용
        List list = (List)map.get("data");
        Map std = (Map)list.get(0);
        System.out.println(std.get("fname"));
        System.out.println(((Map)((List<Object>)map.get("data")).get(0)).get("fname"));

        System.out.println("ListEmp==================");
        ListEmp listEmp = gson.fromJson(reponse, ListEmp.class);

        System.out.println(listEmp.data.get(0));
        System.out.println(listEmp.data.get(0).getFname());


            //map 출력
//        Iterator<String> keys = map.keySet().iterator();
//        while( keys.hasNext() ){
//            String key = keys.next();
//            System.out.println( String.format("키 : %s, 값 : %s", key, map.get(key)) );
//        }






        //Log.d("test","hello");


    }
}
