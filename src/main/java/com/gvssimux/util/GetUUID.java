package com.gvssimux.util;

public final class GetUUID {


    public static String get(){

        String UUID;
        UUID = java.util.UUID.randomUUID().toString().replace("-", "" );

        return UUID;
    }

    public static void main(String[] args) {
        System.out.println(get());
    }



}
