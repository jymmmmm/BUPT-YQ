
/**
 * @Projectname: 项目前后端架构(1)
 * @Filename: conversion
 * @Author: Jia Yiming
 * @Data:2022/8/24 11:38
 */
package teleDemo.util;

import javafx.util.Pair;
import teleDemo.entities.PolyList;
import teleDemo.entities.PolyPost;
import teleDemo.entities.PolyString;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class conversion {

    public static String poly_to_string(List<Pair<Float, Float>> poly) {
        StringBuilder sb = new StringBuilder();
        for (Pair<Float, Float> item :
                poly) {
            sb.append(item.getKey());
            sb.append("_");
            sb.append(item.getValue());
            sb.append("_");
        }
        return sb.toString();
    }

    public static List<Pair<Float, Float>> string_to_poly(String res){
        StringTokenizer st = new StringTokenizer(res, "_");
        List<Pair<Float, Float>> poly = new LinkedList<>();
        while(st.hasMoreTokens()){
            Float lat = Float.valueOf(st.nextToken());
            Float lon = Float.valueOf(st.nextToken());
            poly.add(new Pair<>(lat,lon));
        }
        return poly;
    }

    public static List<Pair<Float, Float>> post_to_list(List<List<Float>> post){
        int temp=0;
        List<Pair<Float, Float>> poly = new LinkedList<>();
        for(List<Float> item:post){
            for(int i=0;i<item.size()/2;i++)
            {
                temp=i+1;
                Pair<Float,Float> pair=new Pair<>(item.get(i),item.get(temp));
                poly.add(pair);
            }
        }
        return poly;
    }

    public static PolyString pl_to_ps(PolyList pl){
        return new PolyString(pl.getId(),pl.getStatus(),poly_to_string(pl.getList_data()));
    }

    public static PolyList ps_to_pl(PolyString ps){
        return new PolyList(ps.getId(),ps.getStatus(),string_to_poly(ps.getStr_data()));
    }

    public static PolyList pp_to_pl(PolyPost pp){
        return new PolyList(pp.getId(),pp.getStatus(),post_to_list(pp.getList_data()));
    }

    public static void main(String[] args) {
        System.out.println(string_to_poly("122.3_145.3_122.5_145.5"));
    }
}
