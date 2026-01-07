import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        System.out.println("Console Todo v0.2");

        while(true){
            String menu = showMenuAndRead(sc);

            if(menu.equals("0")){
                System.out.println("終了します。");
                break;
            } else if (menu.equals("1")) {
                addTask(sc,tasks);
            } else if (menu.equals("2")) {
                listTasks(tasks);
            } else {
                System.out.println("不正な入力です。0/1/2を入力してください。");
            }
        }
        sc.close();
    }

    //showMenuAndRead(sc) メニュー表示、入力内容を読み取る(trim)
    private static String showMenuAndRead(Scanner sc){
        System.out.println();
        System.out.println("メニューを選んでください：");
        System.out.println();
        System.out.println("　　　　1)追加  2)一覧  0)終了");
        System.out.print("> ");
        return sc.nextLine().trim();
    }

    //addTask(sc,tasks) タスクの追加
    private static void addTask(Scanner sc, ArrayList<String> tasks){
        System.out.println("タスクを入力してください。");
        String task = sc.nextLine().trim();

        if(task.isEmpty()){
            System.out.println("空のタスクは登録できません。");
            return;
        }
        tasks.add(task);
        System.out.println("登録しました：" + task);
    }

    //listTasks(tasks) 一覧表示（空ならメッセージ、あれば番号付きで表示）
    private static void listTasks(ArrayList<String> tasks){
        if(tasks.isEmpty()){
            System.out.println("タスクはまだありません。");
            return;
        }
        System.out.println("＝＝＝＝＝＝タスク一覧＝＝＝＝＝＝");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i +1) + ")" + tasks.get(i));
        }
    }

}






