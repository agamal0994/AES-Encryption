import java.io.File;
import java.io.FileNotFoundException;
import java.security.PublicKey;
import java.util.*;
import java.util.Scanner;

import static java.lang.Math.pow;

public class AES {
    public static String read_handle_input() throws FileNotFoundException {
        String plaintext = "" ;
        File myObj = new File("Original.txt");
        Scanner myReader = new Scanner(myObj);
        String data = "";
        while (myReader.hasNextLine()) {
            data += myReader.nextLine();
        }
        myReader.close();
        while(data.length()%16 != 0){
            data+= "#" ;
        }
        for (int i = 0 ; i < data.length() ; i++) {
            String intString = String.format("%08d", Integer.parseInt(Integer.toBinaryString((int) data.charAt(i))));
            //System.out.println(intString);
            plaintext+=intString;
        }
        System.out.println("plain text length :" + plaintext.length());
        System.out.println("plaintext:" + plaintext);
        return plaintext;
    }//Done
    public static String read_handle_key(){
        String key ="" ;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter key(Note : it should be 16 char ) :");
        String s = in.nextLine();
        while(s.length()!=16){
            System.out.println("wrong key ... try again :");
            s = in.nextLine();
        }
        for (int i = 0 ; i < s.length() ; i++) {
            String intString = String.format("%08d", Integer.parseInt(Integer.toBinaryString((int) s.charAt(i))));
            key+=intString;
        }
        System.out.println("Key in binary :"+key);
        System.out.println("key length :"+key.length());

        return key;
    }//Done
    public static int bin2int(String bin) {
        int ascii = 0;
        for(int i = bin.length()-1, j = 0 ; i>= 0 ; j++,i--)
        {
            int a = bin.charAt(i) - '0';
            ascii += a * pow(2,j);
        }

        return ascii;
    }//Done
    public static String int2bin(int num) {
        String bin = "";

        while(num > 0)
        {
            if(num % 2 == 1)
                bin="1" + bin ;
            else
                bin="0"+ bin;

            num /= 2;
        }

        for(int i = bin.length() ; i < 8; i++)
        {
            bin="0"+bin;
        }

        return bin;
    }//Done
    public static String confromhexpartII(String x){
        String lol = "" ;
        for(int i = 0 ; i<x.length() ; i++){
            lol += String.format("%04d", Integer.parseInt(Integer.toBinaryString(Integer.parseInt(String.valueOf(x.charAt(i)),16))));
        }
        return lol ;
    }
    public static String  convFromHex(String x){
        String part1 = x.substring(0,1);
        String part2 = x.substring(1,2);
        part1= String.format("%04d", Integer.parseInt(Integer.toBinaryString(Integer.parseInt(part1,16))));
        part2= String.format("%04d", Integer.parseInt(Integer.toBinaryString(Integer.parseInt(part2,16))));
        return part1+part2 ;
    }//Done
    public static String convtoHex(String x ){
        String hex = "" ;
        for(int i = 0 ; i < x.length() ; i+=4){
            hex+=Integer.toHexString(bin2int(x.substring(i,i+4)));
        }
        return hex;
    }//Done
    public static String ByteSubstituation(String x){
        int temp1 ,temp2;
        String after = "" ;
        String[][] Byte_Sub_Table={
                {"63","7C","77","7B","F2","6B","6F","C5","30","01","67","2B","FE","D7","AB","76"},
                {"CA","82","C9","7D","FA","59","47","F0","AD","D4","A2","AF","9C","A4","72","C0"},
                {"B7","FD","93","26","36","3F","F7","CC","34","A5","E5","F1","71","D8","31","15"},
                {"04","C7","23","C3","18","96","05","9A","07","12","80","E2","EB","27","B2","75"},
                {"09","83","2C","1A","1B","6E","5A","A0","52","3B","D6","B3","29","E3","2F","84"},
                {"53","D1","00","ED","20","FC","B1","5B","6A","CB","BE","39","4A","4C","58","CF"},
                {"D0","EF","AA","FB","43","4D","33","85","45","F9","02","7F","50","3C","9F","A8"},
                {"51","A3","40","8F","92","9D","38","F5","BC","B6","DA","21","10","FF","F3","D2"},
                {"CD","0C","13","EC","5F","97","44","17","C4","A7","7E","3D","64","5D","19","73"},
                {"60","81","4F","DC","22","2A","90","88","46","EE","B8","14","DE","5E","0B","DB"},
                {"E0","32","3A","0A","49","06","24","5C","C2","D3","AC","62","91","95","E4","79"},
                {"E7","C8","37","6D","8D","D5","4E","A9","6C","56","F4","EA","65","7A","AE","08"},
                {"BA","78","25","2E","1C","A6","B4","C6","E8","DD","74","1F","4B","BD","8B","8A"},
                {"70","3E","B5","66","48","03","F6","0E","61","35","57","B9","86","C1","1D","9E"},
                {"E1","F8","98","11","69","D9","8E","94","9B","1E","87","E9","CE","55","28","DF"},
                {"8C","A1","89","0D","BF","E6","42","68","41","99","2D","0F","B0","54","BB","16"}
        };
        for(int i = 0 ; i < x.length() ; i+=8){
            temp1= bin2int(x.substring(i,i+4));
            temp2 =bin2int(x.substring(i+4,i+8));
            after+=convFromHex(Byte_Sub_Table[temp1][temp2]);
        }
        return after ;
    }//Done
    public static String inverseByteSubstituation(String x){
        int temp1 ,temp2;
        String after = ""  , lol = "";
        String[][] inverseByteTable={
                {"52","09","6A","D5","30","36","A5","38","BF","40","A3","9E","81","F3","D7","FB"},
                {"7C","E3","39","82","9B","2F","FF","87","34","8E","43","44","C4","DE","E9","CB"},
                {"54","7B","94","32","A6","C2","23","3D","EE","4C","95","0B","42","FA","C3","4E"},
                {"08","2E","A1","66","28","D9","24","B2","76","5B","A2","49","6D","8B","D1","25"},
                {"72","F8","F6","64","86","68","98","16","D4","A4","5C","CC","5D","65","B6","92"},
                {"6C","70","48","50","FD","ED","B9","DA","5E","15","46","57","A7","8D","9D","84"},
                {"90","D8","AB","00","8C","BC","D3","0A","F7","E4","58","05","B8","B3","45","06"},
                {"D0","2C","1E","8F","CA","3F","0F","02","C1","AF","BD","03","01","13","8A","6B"},
                {"3A","91","11","41","4F","67","DC","EA","97","F2","CF","CE","F0","B4","E6","73"},
                {"96","AC","74","22","E7","AD","35","85","E2","F9","37","E8","1C","75","DF","6E"},
                {"47","F1","1A","71","1D","29","C5","89","6F","B7","62","0E","AA","18","BE","1B"},
                {"FC","56","3E","4B","C6","D2","79","20","9A","DB","C0","FE","78","CD","5A","F4"},
                {"1F","DD","A8","33","88","07","C7","31","B1","12","10","59","27","80","EC","5F"},
                {"60","51","7F","A9","19","B5","4A","0D","2D","E5","7A","9F","93","C9","9C","EF"},
                {"A0","E0","3B","4D","AE","2A","F5","B0","C8","EB","BB","3C","83","53","99","61"},
                {"17","2B","04","7E","BA","77","D6","26","E1","69","14","63","55","21","0C","7D"},
        };
        for(int i = 0 ; i < x.length() ; i+=8){
            temp1= bin2int(x.substring(i,i+4));
            temp2 =bin2int(x.substring(i+4,i+8));
            after+=convFromHex(inverseByteTable[temp1][temp2]);
        }
        return after ;
    }//Done
    public static String Shiftrow(String x){
        int[] aftershift = {0,5,10,15,4,9,14,3,8,13,2,7,12,1,6,11};
        String x_after_shift = "";
        for(int i = 0 ;i<16 ;i++){
            x_after_shift+=x.substring(aftershift[i]*8,aftershift[i]*8+8);
        }
        return x_after_shift;
    }//Done
    public static String inv_Shiftrow(String x){
        int[] aftershift = {0,13,10,7,4,1,14,11,8,5,2,15,12,9,6,3};
        String x_after_shift = "";
        for(int i = 0 ;i<16 ;i++){
            x_after_shift+=x.substring(aftershift[i]*8,aftershift[i]*8+8);
        }
        System.out.println("x_reverse:"+x_after_shift);
        return x_after_shift;
    }//Done
    public  static String XOR(String x ,String y){
        String Xored = "" ;
        for(int i = 0 ; i < x.length() ; i++){
            if(x.charAt(i)==y.charAt(i))
                Xored+="0" ;
            else
                Xored+="1";
        }
        return  Xored ;
    }//Done
    public static String shiftleft(String x){
        String temp = "";
        for(int i = 1 ; i < x.length();i++){
            temp+=x.charAt(i);
        }
        temp+="0";
        return temp ;
    }//Done
    public static String Multiply(String x ,int scale){
        if(scale ==1)
             x = x ;
        if(scale ==2){
            if(x.charAt(0)=='1'){
                x= XOR(shiftleft(x),"00011011");
            }
            else if(x.charAt(0)=='0'){
                x=  shiftleft(x);
            }
        }
        if(scale ==3 ){
            x=  XOR(Multiply(x,2),x);
        }
        if(scale ==9){
            x=XOR(Multiply(Multiply(Multiply(x,2),2),2),x);
        }
        if(scale ==11){
            x=XOR(Multiply(XOR(Multiply(Multiply(x,2),2),x),2),x);
        }
        if(scale ==13){
            x=XOR(Multiply(Multiply(XOR(Multiply(x,2),x),2),2),x);
        }
        if(scale ==14){
            x=Multiply(XOR(Multiply(XOR(Multiply(x,2),x),2),x),2);
        }
        return x;
    }//Done
    public static String Mixcolumn(String x){
        String result = "";

        for(int i = 0 ; i < x.length() ; i+=32){
            result+=XOR(XOR(XOR(Multiply(x.substring(i,i+8),2),Multiply(x.substring(i+8,i+16),3)),x.substring(i+16,i+24)),x.substring(i+24,i+32));
            result+=XOR(XOR(XOR(x.substring(i,i+8),Multiply(x.substring(i+8,i+16),2)),Multiply(x.substring(i+16,i+24),3)),x.substring(i+24,i+32));
            result+=XOR(XOR(XOR(x.substring(i,i+8),x.substring(i+8,i+16)),Multiply(x.substring(i+16,i+24),2)),Multiply(x.substring(i+24,i+32),3));
            result+=XOR(XOR(XOR(Multiply(x.substring(i,i+8),3),x.substring(i+8,i+16)),x.substring(i+16,i+24)),Multiply(x.substring(i+24,i+32),2));
        }
        return result;
    }//Done
    public static String g_function(String x , String RC){
        String temp = "", temp2 ="";
        temp+=x.substring(8,32) + x.substring(0,8);
        temp=ByteSubstituation(temp);
        temp2=XOR(temp.substring(0,8),RC) + temp.substring(8,32);
        return temp2 ;
    }//Done
    public static String H_function(String x ){
        return ByteSubstituation(x);
    }//Done
    public static String generate_Key(String key,int Round_Version){
        String [] RC ={"00000001","00000010","00000100","00001000","00010000","00100000","01000000","10000000","00011011","00110110"};
        String temp="";
        ArrayList<String> Subkeys =new ArrayList<String>();
        Subkeys.add(key.substring(0,32)); //W[0]
        Subkeys.add(key.substring(32,64)); //W[1]
        Subkeys.add(key.substring(64,96)); //W[2]
        Subkeys.add(key.substring(96,128)); //W[3]
        for(int i = 4 ;  i < (Round_Version+1)*4 ; i++){
            if(i%4==0){
                Subkeys.add(XOR(Subkeys.get(i-4),g_function(Subkeys.get(i-1),RC[(i/4)-1])));
            }
            else{
                Subkeys.add(XOR(Subkeys.get(i-1),Subkeys.get(i-4)));
            }
        }
        for(int i = 0 ; i < Subkeys.size() ; i++){
            temp+=Subkeys.get(i);
        }
        //to print the Subkeys
        /*for(int i = 0 ; i <Subkeys.size();i+=4){
            System.out.println(convtoHex(Subkeys.get(i)+Subkeys.get(i+1)+Subkeys.get(i+2)+Subkeys.get(i+3)));
        }
        */
        return temp ;
    }
    public static String Encrypt(String x,String key){
        String subkeys = generate_Key(key,10);
        String Encrypted = XOR(x,subkeys.substring(0,128));
        for(int i = 0 ; i < 9 ; i++){
                Encrypted = XOR(Mixcolumn(Shiftrow(ByteSubstituation(Encrypted))),subkeys.substring((i+1)*128,(i+2)*128));
        }
        Encrypted = XOR(Shiftrow(ByteSubstituation(Encrypted)),subkeys.substring((10)*128,(11)*128));
        Encrypted = convtoHex(Encrypted);
        //System.out.println("Encrypted text is :" + Encrypted);
        return Encrypted;
    }
    public static void main(String arg[]) throws FileNotFoundException {
        //for Discussion Time
        String Plaintext = read_handle_input() , Encrypted_text = "";
        String Key =read_handle_key();
        for(int i = 0 ; i<Plaintext.length()/128;i++) {
            Encrypted_text += Encrypt(Plaintext.substring(i*128,(i+1)*128),Key);
        }
        System.out.println(Encrypted_text);


    }

}
