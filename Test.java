import java.io.*;
class Test{
public static void main(String args[]){
try{
FileOutputStream fout=new FileOutputStream("cct.txt");
String s="Sachin Tendulkar is my favourite player";
byte b[]=s.getBytes();//converting string into byte array 
fout.write(b);
fout.close();
System.out.println("success...");
}catch(Exception e)
{
    System.out.println(e);
}
}
}
