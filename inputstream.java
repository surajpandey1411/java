import java.io.*;
class SimpleRead{
public static void main(String args[]){
try{
FileInputStream fin=new FileInputStream("abc.txt");
int i=fin.read();
while(i !=-1){
System.out.println((char)i);
i=fin.read();
}
fin.close();
}
catch(Exception e)
{
    System.out.println(e);
    e.getStackTrace();
}
}
}
