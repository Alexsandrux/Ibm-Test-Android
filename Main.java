import java.util.HashMap;


class Solution {

    public int noMovesWithOrderedLetters(String keyword, String word) {
        StringBuilder wordBuilder = new StringBuilder(word);
        StringBuilder keywordFromWord = new StringBuilder();

        int noMoves = 0;
        int indexOfKeyword;

        while (!wordBuilder.isEmpty()) {
            indexOfKeyword = 0;
            keywordFromWord.setLength(0);
            for (int i = 0; i < wordBuilder.length(); i++) {
                if (keyword.charAt(indexOfKeyword) == wordBuilder.charAt(i)) {
                    keywordFromWord.append(wordBuilder.charAt(i));
                    wordBuilder.deleteCharAt(i);
                    i--;
                    indexOfKeyword++;
                }
                if (indexOfKeyword == keyword.length()) {
                    break;
                }
            }

            if (!keywordFromWord.toString().equals(keyword)) {
                break;
            } else noMoves++;
        }
        return noMoves;
    }

    public int noMovesWithUnorderedLetters(String keyword, String word) {
        StringBuilder wordBuilder = new StringBuilder(word);
        StringBuilder keywordFromWord = new StringBuilder();

        int noMoves = 0;

        while (!wordBuilder.isEmpty()) {
            keywordFromWord.setLength(0);

            for (int i = 0; i < keyword.length(); i++) {
                String letter = String.
                        valueOf(keyword.charAt(i));
                int index = wordBuilder.indexOf(letter);
                if (index != -1) {
                    keywordFromWord.append(wordBuilder.charAt(index));
                    wordBuilder.deleteCharAt(index);
                } else break;
            }

            if (!keywordFromWord.toString().equals(keyword)) {
                break;
            } else noMoves++;
        }
        return noMoves;
    }

    public boolean hasCorrectIngredients(String[] neededIngredients, String[] ingredients) {
        HashMap<String, Integer> ingredientsExist = new HashMap<>();

        for (String neededIngredient : neededIngredients) {
            ingredientsExist.put(neededIngredient, 0);
        }

        for (String ingredient : neededIngredients) {
            for (String s : ingredients) {
                if (this.noMovesWithUnorderedLetters(ingredient, s) > 0) {
                    ingredientsExist.put(ingredient, 1);
                }
            }
        }

        return ingredientsExist.values().stream().filter(num -> num == 0).toArray().length == 0;

    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("The number of aparitions of the word TIRAMISU in the given word," +
                "TIRAXXMISU , is equal to "
                + new Solution().noMovesWithUnorderedLetters(
                "TIRAMISU",
                "TIRAXXMISU"));

        System.out.println("The number of aparitions of the word TIRAMISU in the given word," +
                "BATIRXXOLAMIXSU , is equal to "
                + new Solution().noMovesWithUnorderedLetters(
                "TIRAMISU",
                "BATIRXXOLAMIXSU"));

        System.out.println("The number of aparitions of the word TIRAMISU in the given word," +
                "TITIRHAUNRAOBGMIXZARBMISUSU , is equal to "
                + new Solution().noMovesWithUnorderedLetters(
                "TIRAMISU",
                "TITIRHAUNRAOBGMIXZARBMISUSU,"));

        System.out.println("The number of aparitions of the word TIRAMISU in the given word," +
                "QWERALE , is equal to "
                + new Solution().noMovesWithUnorderedLetters(
                "TIRAMISU",
                "QWERALE"));


        System.out.println("The number of aparitions where the letters are in order" +
                " where of the word TIRAMISU in the given word," +
                "TIRAXXMISU , is equal to "
                + new Solution().noMovesWithOrderedLetters(
                "TIRAMISU",
                "TIRAXXMISU"));

        System.out.println("The number of aparitions where the letters are in order" +
                " where of the word TIRAMISU in the given word," +
                "ITRAXXMISU , is equal to "
                + new Solution().noMovesWithOrderedLetters(
                "TIRAMISU",
                "ITRAXXMISU"));



        String[] ingredients = new String[]{
                "CAOPCOXOLLA", "BAMASCARXXOLPOXNE",
                "QWCORAFFEE", "PLENLEGXP",
                "RPLSUORLEGXAPG", "QWERALE"
        };
        String[] neededIngredients = new String[]{
                "SUGAR", "MASCARPONE", "COFFEE", "COCOA"
        };

        if (new Solution().hasCorrectIngredients
                (neededIngredients, ingredients)) {
            System.out.println("We have all the ingredients for Tiramisu");
        } else System.out.println("We don't have enough ingredients to make Tiramisu");

        System.out.println(new Solution().hasCorrectIngredients
                (neededIngredients, ingredients));

    }
}