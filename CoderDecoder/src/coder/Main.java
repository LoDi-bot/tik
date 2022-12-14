package coder;

import coder.utils.HuffmanCoder;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // генерируем таблицу префиксных кодов
        TreeMap<Character, String> codes;
        try {
            codes = HuffmanCoder.getCodes();
        } catch (RuntimeException exception) {
            if  (exception.getClass().getName().equals("java.lang.NullPointerException")) {
                System.out.println("Алфавит не найден - похоже файл пустой, пожалуйста проверьте файл с алфавитом!!!");
            }
            if (exception.getClass().getName().equals("java.lang.ArrayIndexOutOfBoundsException")) {
                System.out.println("В алфавите не хватает вероятностей, , пожалуйста проверьте файл с алфавитом!!!");
            }
            return;
        }

        // вывод кодов для символов алфавита
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.println("Symbol: " + entry.getKey() + ", Code: " + entry.getValue());
        }

        System.out.println("Enter text to encode");
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();

        // кодируем сообщение
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            encoded.append(codes.get(message.charAt(i)));
        }
        System.out.println("Encoded message: " + encoded);
    }
}