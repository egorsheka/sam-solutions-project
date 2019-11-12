package by.sam.mvc.controllers;

import java.net.Socket;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class B {
    int v2;



        public  static void  main(String[] args) {
            NavigableMap<String, Number> nmap = new TreeMap<String, Number>();
            nmap.put("one",  new  Integer(1)); nmap.put("two", new  Integer(2));
            nmap.put("three", new  Integer(3)); nmap.put("four", new  Integer(4));
            Map<String, Number> map = nmap.headMap("three");
            System.out.println(map);
        }
}
