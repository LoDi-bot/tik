package decoder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MTFDecoder {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/Users/lodi/Desktop/tik/CoderDecoder/src/decoder/alphabet.txt"));
        String str = reader.readLine();
        String[] strSplit = str.split(" ");
        List<String> alphabet = new ArrayList<>(Arrays.asList(strSplit)).stream().sorted().collect(Collectors.toList());

        Scanner sc = new Scanner(System.in);
        String codedInputMessage = sc.nextLine();

        String decodedMessage = decode(codedInputMessage, alphabet);
        System.out.println(decodedMessage);
    }

    public static String decode(String codedInputMessage, List<String> alphabet){
        // нумеруем буквы алфавита десятичными числами
        List<String> codes = IntStream
                .range(0, alphabet.size())
                .boxed()
                .map(Object::toString)
                .toList();

        StringBuilder decodedMessage = new StringBuilder();
        String tempCode = "";

        for(char letter : codedInputMessage.toCharArray()){
            tempCode = tempCode + letter;
            // если код(текущая считанная последовательность кодов) содержится в массиве кодов алфавита
            if(codes.contains(tempCode)) {
                // находим позицию обнаруженной буквы
                int index = codes.indexOf(tempCode);
                String foundLetter = alphabet.get(index);
                // записываем букву
                decodedMessage.append(foundLetter);
                // переносим букву наверх
                alphabet.remove(index);
                alphabet.add(0, foundLetter);
                tempCode = "";
            }
        }

        return decodedMessage.toString();
    }
}