package com.cebem.rickandmorty.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.io.File;

public class Utils {
    


public static boolean isPalindrome(String word){

    StringBuilder sb = new StringBuilder(word);
String reversed = sb.reverse().toString();

return reversed.equalsIgnoreCase(word);

}

public static void writeOnDisk(String fileName, String info) throws IOException{
    FileWriter fw = null;

try {
    fw = new FileWriter(fileName, true);
    fw.write(info);
}  finally{
if (fw != null) fw.close();
    

}



}
public static void writeOnDiskBlank(String fileName, String info) throws IOException{
    FileWriter fw = null;

try {
    fw = new FileWriter(fileName, false);
    fw.write(info);
}  finally{
if (fw != null) fw.close();
    

}



}
public static boolean deleteFile(String filename) {
    File fileToDelete = new File(filename);

    return fileToDelete.delete();

}



public static float getHighestNumber(float ... f) throws NumberFormatException{
    
    if (f == null || f.length==0) {

        throw new NumberFormatException("Numeros incorrectos");
    }
    float elMayor = f[0];
    for (int i = 1; i < f.length; i++) {
        if (f[i]>elMayor) {
            elMayor = f[i];
        }
    }
    return elMayor;
}


public static String capitalizeText(String text){

    String[]words = text.split(" ");
    String capitalizedText = "";
    for (int i = 0; i < words.length; i++) {
    char firstLetter = Character.toUpperCase(words[i].charAt(0));
      String rest = words[i].substring(1).toLowerCase();
      capitalizedText += firstLetter + rest + " ";
    }
    return capitalizedText;


}
public static int getRandomValue(int max){
    return (int) Math.floor(Math.random()*max);
}
public static int getRandom(int max){
    Random r = new Random();
    int number = r.nextInt(max);
    return number;
}
// public static String addOne(String url){

//     char number = url.charAt(url.length()-1);
//     int num = Character.getNumericValue(number);
//     num += 1;

// }

}
