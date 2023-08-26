package com.dataproviderservice.javaPracticeQuestions;

public class oop {
    public static void main(String[] args) {
//        final Person p1 = new Person("Ahsan");
//        p1.setName("Ali"); //run sucessfully
//        System.out.println(p1.getName());
     //   p1 = new Person("Khan");  //compile time error because of final object

//
//        A a = new A();
//        a.print();    //A
//        // Case 2
//        A ab = new B();
//        ab.print();   //B
//        // Case 3
//        B b = new B();
//        b.print();    //B
//        // Case 4
////        B ba = new A(); // compile time error
////        ba.print();
//        // Case 5
//        A aab = (A) new B();
//        aab.print(); //B
//        // Case 6
//        B baa = (B) new A(); //run time error
//        baa.print();



//        C c = new C();
//        C cd = new D();
//        D d = new D();
//        D dc = new C();

   //     call(null);

        G gObj =new G();
        gObj.setI(10);
        System.out.println(gObj.getI());
        System.out.println(G.getI());

        String s1 = "ABC";
        String s2 = "ABC";
        String s3 = new String("ABC");
     

        if(s1==s2){
            System.out.println("S1 EQUAL TO S2\n");


        }
        if(s1==s3){
            System.out.println("S1 EQUAL TO S3");
        }
        if(s2.equals(s3)){
            System.out.println("S2 EQUAL TO S3");
        }
    }



//    public static void call(Object obj){
//        System.out.println("OBJ");
//    }
//    public static void call(String str){
//        System.out.println("STR");
//    }




}

