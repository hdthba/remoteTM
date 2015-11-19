package map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

 public class mapRTM {

     public static void main(String[] args) {
        testHashMapAPIs();
    }
     
     private static void testHashMapAPIs() {
         // ��ʼ���������
         Random r = new Random();
         // �½�HashMap
         HashMap map = new HashMap();
         // ��Ӳ���
         map.put("1", "user1");
         map.put("2", "user2");
         map.put("3", "user3");
 
         // ��ӡ��map
         System.out.println("map:"+map );
 
         // ͨ��Iterator����key-value
         Iterator iter = map.entrySet().iterator();
         while(iter.hasNext()) {
             Map.Entry entry = (Entry) iter.next();
             System.out.println("next : "+ entry.getKey() +" - "+entry.getValue());
         }
        String value= (String) map.get("1");
        System.out.println(value);
         

         
         // HashMap�ļ�ֵ�Ը���        
         System.out.println("size:"+map.size());
 
         
         
         // containsKey(Object key) :�Ƿ������key
         System.out.println("contains key two : "+map.containsKey("two"));
         System.out.println("contains key five : "+map.containsKey("five"));
 
         // containsValue(Object value) :�Ƿ����ֵvalue
         System.out.println("contains value 0 : "+map.containsValue(new Integer(0)));
 
         // remove(Object key) �� ɾ����key��Ӧ�ļ�ֵ��
         map.remove("three");
 
         System.out.println("map:"+map );
 
         // clear() �� ���HashMap
         map.clear();
 
         // isEmpty() : HashMap�Ƿ�Ϊ��
         System.out.println((map.isEmpty()?"map is empty":"map is not empty") );
         
         
         
     }
 }
