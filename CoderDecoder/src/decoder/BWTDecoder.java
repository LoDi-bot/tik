package decoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BWTDecoder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String codedInputMessage = sc.nextLine();
        int position = sc.nextInt();

        ArrayList<String> strings = new ArrayList<>(Arrays.stream(codedInputMessage.split("")).toList());

        String decoded = decode(strings, position);
        System.out.println(decoded);
    }

    public static String decode(List<String> strings, int position){
        List<String> result = strings.stream().sorted().collect(Collectors.toList());

        for (int k = 0; k < strings.size()-1; k++) {
            for (int i = 0; i < strings.size(); i++) {
                result.set(i, strings.get(i) + result.get(i));
            }
            result = result.stream().sorted().collect(Collectors.toList());
        }

        return (result.get(position));
    }
}