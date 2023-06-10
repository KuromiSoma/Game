import java.util.*;

public class Numeron {
    private static final int CODE_LENGTH = 3; // ヌメロンの桁数
    private static final int MAX_GUESSES = 6; // チャレンジ回数

    private int[] secretCode; // 答え
    private int guessesRemaining; // 残りのチャレンジ回数

    public Numeron() {
        secretCode = generateSecretCode();
        guessesRemaining = MAX_GUESSES;
    }

    public void play() {
        System.out.println("=== ヌメロンを始めます ===");
        System.out.println("ルール: 3桁の数を当てるゲームです。");
        System.out.println("各桁には0から9までの数字が使われます。");
        System.out.println("位置と数字があっている場合は「EAT」、数字だけがあっている場合は「BITE」と表示されます。");
        System.out.println("EATが3になると全桁正解です。");
        System.out.println("========================");

        Scanner scanner = new Scanner(System.in);

        while (guessesRemaining > 0) {
            System.out.println("残りの試行回数: " + guessesRemaining);
            System.out.print("予想する3桁の数を入力してください: ");
            String guessStr = scanner.nextLine();

            // 入力された文字列を数字の配列に変換
            int[] guess = new int[CODE_LENGTH];
            for (int i = 0; i < CODE_LENGTH; i++) {
                guess[i] = Character.getNumericValue(guessStr.charAt(i));
            }

            // EATとBITEの数を計算
            int eat = 0;
            int bite = 0;
            for (int i = 0; i < CODE_LENGTH; i++) {
                if (guess[i] == secretCode[i]) {
                    eat++;
                } else if (containsDigit(guess[i], secretCode)) {
                    bite++;
                }
            }

            System.out.println("EAT: " + eat + ", BITE: " + bite);

            if (eat == CODE_LENGTH) {
                System.out.println("おめでとうございます！正解です！");
                return;
            }

            guessesRemaining--;
        }

        System.out.println("ゲームオーバー！正解は " + Arrays.toString(secretCode) + " でした。");
    }

    private int[] generateSecretCode() {
        Random random = new Random();
        int[] code = new int[CODE_LENGTH];

        for (int i = 0; i < CODE_LENGTH; i++) {
            code[i] = random.nextInt(10); // 0から9までの乱数を生成
        }

        return code;
    }

    private boolean containsDigit(int digit, int[] code) {
        for (int num : code) {
            if (digit == num) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Numeron numeron = new Numeron();
        numeron.play();
    }
}
