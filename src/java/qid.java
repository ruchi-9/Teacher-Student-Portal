import java.util.Random;
public class qid
{
  static final Random generator = new Random();
  public static  int generatePin() {
  return 1000000000+generator.nextInt(900000000);
}
public static void main(String [] abc)
{
         int z;
	 qid ob=new qid();
	 z=ob.generatePin();
	 System.out.println(z);
}
}