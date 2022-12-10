package coder.models;

public class Node implements Comparable<Node> {
    public Character content; // символ алфавита
    public float weight; // вероятность или сумма вероятностей
    public Node left; // левый потомок
    public Node right; // правый потомок

    public Node(Character content, float weight) {
        this.content = content;
        this.weight = weight;
    }

    public Node(Character content, float weight, Node left, Node right) {
        this.content = content;
        this.weight = weight;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node node) {
        if (node.weight - weight > 0) {
            return 1;
        } else if (node.weight - weight < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    // получение кода для символа
    public String getCodeForCharacter(Character ch, String codeTreePath) {
        if (content == ch) { // если контент текущего листа и есть наш символ возвращаем codeTreePath
            return codeTreePath;
        } else {
            if (left != null) { // если есть левое поддерево
                String path = left.getCodeForCharacter(ch, codeTreePath + 0); // рекурсивно вызвываем ту же функцию для поддерева и к пути добавляем 0
                if (path != null) {
                    return path;
                }
            }
            if (right != null) { // если есть правое поддерево
                String path = right.getCodeForCharacter(ch, codeTreePath + 1); // рекурсивно вызвываем ту же функцию для поддерева и к пути добавляем 1
                if (path != null) {
                    return path;
                }
            }
        }
        return null;
    }
}
