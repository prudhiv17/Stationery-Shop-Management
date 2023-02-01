import java.util.*;

public class ListInterface {
    
    public static void main(String args[]){
        
        List<String> list1 = new ArrayList<>();
        list1.add("Asus");
        list1.add("Dell");
        list1.add("HP");
        
        List<String> list2 = new ArrayList<>();
        list2.add("Lenovo");
        list2.add("Apple");
        
        list1.forEach(product -> {System.out.println(product);});
        list2.forEach(product -> {System.out.println(product);});
        
        list1.addAll(list2);
        System.out.println("\n\n\n");
        list1.forEach(product -> {System.out.println(product);});

        //1st Method
        System.out.println("\nThe Product Name: ");
        String product1 = list1.get(1);
        System.out.println(product1);
        
        //2.Method
        System.out.println();
        String product2 = list1.remove(0);
        System.out.println(product2);
        
        System.out.println("\n");
        System.out.println("Product Aviable: ");
        System.out.println(list1);
        
        System.out.println("\n");
        int size = list1.size();
        System.out.println("Size of List: "+size);
        
        System.out.println("\n");
        list2.clear();       
        System.out.println("Product Avaiable in List2: "+list2);
    }
        
        
    }
