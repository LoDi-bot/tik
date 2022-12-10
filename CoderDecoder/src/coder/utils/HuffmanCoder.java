package coder.utils;

import coder.models.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HuffmanCoder {
    public static final String ALPHABET_FILE_PATH = "/Users/lodi/Desktop/tik/CoderDecoder/src/coder/alphabet.txt";

    public static TreeMap<Character, Float> readAlphabetFrequencies() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(ALPHABET_FILE_PATH));
        List<Character> alphabet = Arrays
                .stream(reader.readLine().split(" "))
                .map(elem -> elem.charAt(0))
                .toList();
        List<String> frequencies = Arrays.stream(reader.readLine().split(" ")).toList();
        TreeMap<Character, Float> freqMap = new TreeMap<>();
        for (int i = 0; i < alphabet.size(); i++) {
            Character character = alphabet.get(i);
            Float frequency = Float.parseFloat(frequencies.get(i));
            freqMap.put(character, frequency);
        }
        return freqMap;
    }

    // строим кодовое дерево с помощью алгоритма Хаффмана
    public static Node getCodesTree(TreeMap<Character, Float> frequencies) throws IOException {
        ArrayList<Node> nodes = new ArrayList<>();
        for (Character c : frequencies.keySet()) {
            nodes.add(new Node(c, frequencies.get(c)));
        }

        while (nodes.size() > 1) {
            nodes.sort(Collections.reverseOrder()); // упорядочиваем узлы по возрастанию вероятностей

            // берем два узла с наименьшей вероятностью записываем в переменные и удаляем их
            Node left = nodes.remove(0);
            Node right = nodes.remove(0);
            System.out.println(left.weight + " " + left.content + " : " + right.weight + " " + right.content);

            // создаем новый узел, вероятность которого - сумма вероятностей удаленных узлов
            Node parent = new Node(null, right.weight + left.weight, left, right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    // генерируем таблицу префиксных кодов для кодируемых символов с помощью кодового дерева
    public static TreeMap<Character, String> getCodes() throws IOException {
        TreeMap<Character, Float> frequencies = HuffmanCoder.readAlphabetFrequencies();
        Node tree = HuffmanCoder.getCodesTree(frequencies);

        TreeMap<Character, String> codes = new TreeMap<>();

        for (Character c : frequencies.keySet()) {
            codes.put(c, tree.getCodeForCharacter(c, "")); // ищем код для текущего символа
        }

        return codes;
    }
}
