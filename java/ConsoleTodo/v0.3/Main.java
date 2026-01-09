import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        System.out.println("Console Todo v0.3");

        while(true){
            String menu = showMenuAndRead(sc);

            if(menu.equals("0")){
                System.out.println("終了します。");
                break;
            } else if (menu.equals("1")) {
                addTask(sc,tasks);
            } else if (menu.equals("2")) {
                listTasks(tasks);
            } else if (menu.equals("3")) {
                delTask(sc,tasks);
            } else {
                System.out.println("不正な入力です。半角数字0/1/2/3を入力してください。");
            }
        }
        sc.close();
    }

    //showMenuAndRead(sc) メニュー表示、入力内容を読み取る(trim)
    private static String showMenuAndRead(Scanner sc){
        System.out.println();
        System.out.println("メニューを選んでください：");
        System.out.println();
        System.out.println("1)追加\t2)一覧\t3)削除\t0)終了");
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

    //delTask(sc,tasks) 入力した該当番号のタスクを削除
    private static void delTask(Scanner sc, ArrayList<String> tasks){
        if(tasks.isEmpty()){
            System.out.println("タスクはまだありません。");
            return;
        }

        // 先に一覧を見せて、番号を間違えにくくする
        listTasks(tasks);

        System.out.print("削除する番号を入力してください（例：1）> ");
        String input = sc.nextLine().trim();

        if(input.isEmpty()){
            System.out.println("空の入力です。数字で入力してください。");
            return;
        }

        int n;
        try{
            n = Integer.parseInt(input);
        }catch(NumberFormatException e){
            System.out.println("数字で入力してください。");
            return;
        }

        if(n < 1 || n > tasks.size()){
            System.out.println("範囲外です。1〜" + tasks.size() + " を入力してください。");
            return;
        }

        int index = n - 1;
        String removed = tasks.remove(index);
        System.out.println("削除しました：" + removed);

        listTasks(tasks);
    }

}
