package org.example;

import org.example.constants.Constants;
import org.example.utils.FileOperation;
import org.example.utils.MapOperation;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Dictionary {
    private Map<String, List<String>> turEngList;
    private Map<String,List<String>> engTurList;

    private final Scanner scanner = new Scanner(System.in);

    private void txtToMap(){
        //first read files and store words
        try {
            //turkish - english
            turEngList = MapOperation.mapOperation.toMap(FileOperation.fileOperation.readFileByJava8(Constants.tureng));
            //english - turkish
            engTurList = MapOperation.mapOperation.toMap(FileOperation.fileOperation.readFileByJava8(Constants.engtur));
        } catch (Exception e) {
            System.out.println("Oops, error!");
            System.out.println(e.getMessage());
        }
    }

    private void mainMenu(){
        System.out.println("Kullanmak istediğiniz sözlük için aşağıdaki seçeneklerden devam ediniz: ");
        System.out.println("1 - Türkçe-İngilizce");
        System.out.println("2 - İngilizce-Türkçe");
        System.out.println("3 - Yeni Kelime Ekle");
        System.out.println("0 - Çıkış");
    }

    private void getTurEngDictionary(){
        System.out.println("##### Türkçe-İngilizce Sözlük #####");
        while(true) {
            System.out.print("Kelime : ");
            String word = scanner.nextLine();
            if(word.equals("0")) break;
            else if(turEngList.containsKey(word)){
                System.out.println("İngilizce Karşılığı : " + turEngList.get(word));
            }
            else{
                System.out.println("Sözlükte bu kelime mevcut değil.");
            }
        }
    }

    private void getEngTurDictionary(){
        System.out.println("##### İngilizce-Türkçe Sözlük #####");
        while(true) {
            System.out.print("Kelime : ");
            String word = scanner.next();
            if(word.equals("0")) break;
            else if (engTurList.containsKey(word)){
                System.out.println("Türkçe Karşılığı : " + engTurList.get(word));
            }
            else{
                System.out.println("Sözlükte bu kelime mevcut değil.");
            }
        }
    }

    private void addNewWordToDictionary(){
        while(true) {
            System.out.println("##### Yeni Kelime Ekle #####");
            System.out.println("Eklemek istediğiniz kelime türkçe ise 1 ingilizce ise 2 ye basınız...");

            int dictionaryType = scanner.nextInt();
            scanner.nextLine();

            if(dictionaryType == 0) break;
            else if (dictionaryType == 1) {
                System.out.print("Eklemek istediğiniz türkçe kelime: ");
                String tWord = scanner.nextLine();
                System.out.print("Eklemek istediğiniz kelimenin ingilizce karşılığı: ");
                String eWord = scanner.nextLine();
                if(turEngList.containsKey(tWord)){
                    System.out.println("Bu kelime sözlükte ekli, "+eWord+" yeni bir ingilizce karşılık olarak eklenecek.");
                }
                try {
                    FileOperation.fileOperation.writeFile(Constants.tureng,tWord+"/"+eWord);
                    System.out.println(tWord + " Sözlüğe Eklendi!");
                } catch (Exception e) {
                    System.out.println("Dosyaya yazma işleminde bir hata oluştu!");
                }
            }
            else if (dictionaryType == 2) {
                System.out.print("Eklemek istediğiniz ingilizce kelime: ");
                String eWord = scanner.nextLine();
                System.out.print("Eklemek istediğiniz kelimenin türkçe karşılığı: ");
                String tWord = scanner.nextLine();
                if(engTurList.containsKey(eWord)){
                    System.out.println("Bu kelime sözlükte ekli, "+tWord+" yeni bir türkçe karşılık olarak eklenecek.");
                }
                try {
                    FileOperation.fileOperation.writeFile(Constants.engtur,eWord+"/"+tWord);
                    System.out.println(eWord + " Sözlüğe Eklendi!");
                } catch (Exception e) {
                    System.out.println("Dosyaya yazma işleminde bir hata oluştu!");
                }
            }
        }
    }

    public void startDictionary() {
        txtToMap();
        while(true) {
            mainMenu();
            int dictionaryType = scanner.nextInt();
            scanner.nextLine();
            if (dictionaryType == 0)
                break;
            switch (dictionaryType) {
                case 1:
                    getTurEngDictionary();
                    break;
                case 2:
                    getEngTurDictionary();
                    break;
                case 3:
                    addNewWordToDictionary();
                    break;
                default:
                    System.out.println("Lütfen geçerli bir seçenek seçiniz...");
                    break;
            }
        }
    }
}
