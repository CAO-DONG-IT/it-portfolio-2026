import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Console Todo v0.1");
        System.out.print("タスクを入力してください: ");
        String task = sc.nextLine().trim();

        // 判断：空文字かどうか
        if (task.isEmpty()) {
            System.out.println("空のタスクは登録できません。");
        } else {
            System.out.println("登録しました: " + task);
        }

        sc.close();
    }
}
