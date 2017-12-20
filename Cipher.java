/**
 * Created by spenc on 12/19/2017.
 */
import java.util.*;
public class Cipher {
    //in this class I am going to play around with my encryption method I intend to use
    //this int is for any char that is not a-z because it doesnt get changed
    static int outside = -1;
    //I am using a frequency alphabet as opposed to the real one it is as follows
    static char [] CIPHALPH = {'e','t','a','o','i','n','s','r','h','d','l','u','c','m','f','y','w','g','p','b','v','k','x','q','j','z'};

    //each client is going to have to pick (p,q which are prime. n = p*q) (r which is co-prime with (p-1)(q-1)) (s which is the inverse of r(mod(p-1)(q-1))

    //creates a random prime by creating random numbers and checking to see if it's prime
    private static int RandPrime( ){
        Random prime = new Random();
        int randPrime = 0, rand =0;
        do {
             rand = prime.nextInt();
        }while(isPrime(rand) == false);
            randPrime = rand;
        return randPrime;
    }
    //the check to see if it's prime brute force checking possible divisors up to the sqrt of the random number
    private static boolean isPrime(int num){
        int start = 2;
        int end =  (int)Math.sqrt((double)num);
        if(num <=1)
            return false;
            while(start < end) {
                if (num % start == 0) {
                    return false;
                }
                start++;
            }
        return true;
    }

    int p = RandPrime();
    int q = RandPrime();

    //random num to change for the cipher
    private static int RandCiph(){
        Random rand = new Random();
        return rand.nextInt();
    }
    //getting everything together to create and cipher the message(str) MAYBE JUST HAVE THIS RETURN A CHAR ARRAY THE STRING DOESNT REALLY MEAN ANYTHING
    private static char[] Cipher(String str,int key){

        if(!str.equalsIgnoreCase(null)) {
            char[] message = str.toCharArray();
            int mLeng = message.length;
            int[] toBeChanged = new int[mLeng];
            for(int i=0;i<mLeng;i++){
               if(getLetter(message[i]) != -1) {
                   toBeChanged[i] = getLetter(message[i]);
                   if (toBeChanged[i] != -1) {
                       toBeChanged[i] += key;
                   }
                   message[i] = (char) toBeChanged[i];
               }
            }

            return message;
        }
        return null;
    }
    private static char[] Decipher(char[] str,int key){
        if(str != null){
            char [] ret = new char[str.length];
            int mLeng = str.length;
            int[] toBeChanged = new int[mLeng];
            for(int i=0;i<mLeng;i++){
               if((int)(str[i] - key)>=0 && (int)(str[i] - key)<=25) {
                   toBeChanged[i] = (str[i] - key);
                   ret[i] = CIPHALPH[toBeChanged[i]];
               }else{
                   toBeChanged[i] = str[i];
                   ret[i] = (char)toBeChanged[i];
               }

            }
            return ret;
        }
        return null;
    }
    //to check and assign values for messages
    private static int getLetter(char c){
        if( (int)c != 0  ){
            for(int i=0; i<26;i++){
                if((int)c == (int)CIPHALPH[i]){
                    return i;
                }
            }
            return outside;
        }
        return -1;
    }




   //testing stuff
    public static void main(String[] args) {
      char[] thing =  Cipher("hey don't forget q j z. The quick brown fox jumped over the lazy dog. ? ! * @ 1 2",3);
        for(int i=0;i<thing.length;i++) {
            System.out.print((int)thing[i] );
        }
        System.out.println("\n"+CIPHALPH[23]+ " "+CIPHALPH[24]+" "+CIPHALPH[25]);
        char[] thi = Decipher(thing,3);
        for(int i=0;i<thing.length;i++) {
           System.out.print(thi[i] );
        }
    }

}
