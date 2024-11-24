import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String list = "차경준\n" + "이재희\n" + "이제무\n" + "백인권\n" + "최성관\n" + "차관호\n" +
                "이찬호\n" + "최영범\n" + "장연식\n" + "김효민\n" + "김진아\n" + "김수민\n" + "장승희\n" +
                "홍윤기\n" + "박형진\n" + "이지수\n" + "이효경\n" + "백종현\n" + "조정하\n";
        String[] split = list.split("\n");
        System.out.println("==============================================================");
        System.out.println("=========================랜밥 시뮬레이터!========================");
        Scanner in = new Scanner(System.in);

        String filePath = "list.txt";
        ArrayList<String> readNames = (ArrayList<String>) readFromFile(filePath);
        if (readNames.isEmpty()) readNames = new ArrayList<>(Arrays.asList(split));

        while (true){
            System.out.println("==============================================================");
            System.out.println("1 : 랜덤 밥 조짜기");
            System.out.println("2 : 인원 추가");
            System.out.println("3 : 인원 삭제");
            System.out.println("4 : 종료");
            ArrayList<ArrayList<Integer>> arr = new ArrayList<>();

            int num = in.nextInt();

            if (num == 1){
                System.out.println("몇명이서 먹을까요?(입력한 숫자보다 한명 적은 조가 생길 수 있습니다)");
                int cnt = in.nextInt();
                in.nextLine();
                int numTeams = (int) Math.ceil((double) readNames.size() / cnt);

                char team = 'A';
                for (int i = 0; i < numTeams; i++) arr.add(new ArrayList<>());
                for (int i = 0; i < readNames.size(); i++) arr.get(i % numTeams).add(i);

                Collections.shuffle(readNames);
                for (ArrayList<Integer> squad : arr){
                    System.out.print(team++ + "조 : ");
                    for (int x : squad) System.out.print(readNames.get(x) + " ");
                    System.out.println();
                }

                System.out.println("식사 맛있게 하세요!");
                System.out.println("계속하려면 r키를 입력하세요");
                while (true){
                    String s = in.nextLine();
                    if (s.equals("r") || s.equals("R")) break;
                    else {
//                        writeToFile(filePath,readNames);
                        System.exit(0);
                    }
                }
            }

            else if (num == 2){
                in.nextLine();
                System.out.println("추가할 사람 이름을 입력하세요");
                String s = in.next();
                readNames.add(s);
                Collections.shuffle(readNames);
            }
            else if (num == 3){
                in.nextLine();
                System.out.println("삭제할 사람 이름을 입력하세요");
                String s = in.next();
                readNames.remove(s);
                Collections.shuffle(readNames);
            }
            else if (num == 4){
//                writeToFile(filePath,readNames);
                System.exit(0);
            }
        }
    }

    public static void writeToFile(String filePath, List<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : data) {
                writer.write(line+'\n');
            }
            System.out.println("저장 완료" + filePath);
        } catch (IOException e) {
            System.err.println("저장 오류" + e.getMessage());
        }
    }

    public static List<String> readFromFile(String filePath) {
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            System.err.println("읽기 오류" + e.getMessage());
        }
        return data;
    }
}
