package Problems;
import java.io.*;
import java.util.*;

public class TRMS {

    public static void main(String args[] ) throws Exception {
        ArrayList<int[]> moves = new ArrayList<>();

        int[] moveA = {0, 1};
        int[] moveB = {0, 1, 2};
        int[] moveC = {1, 4, 5, 6};
        int[] moveD = {2, 5};
        int[] moveE = {3, 5};
        int[] moveF = {3, 7};
        int[] moveG = {5, 7};
        int[] moveH = {6, 7};
        moves.add(moveA);
        moves.add(moveB);
        moves.add(moveC);
        moves.add(moveD);
        moves.add(moveE);
        moves.add(moveF);
        moves.add(moveG);
        moves.add(moveH);

        Scanner sc = new Scanner(System.in);

        String initialPos = sc.nextLine();
        HashSet<String> visited = new HashSet<>();

        Queue<String> q = new LinkedList<>();
        Queue<Integer> m = new LinkedList<>();
        q.add(initialPos);
        m.add(0);

        while (!q.isEmpty()) {
            String cursor = q.poll();
            Integer cursorMove = m.poll();

            if (visited.contains(cursor))
                continue;
            else
                visited.add(cursor);

            if (sameFace(cursor)) {
                System.out.println(cursorMove);
                return;
            } else {
                cursorMove++;
                for (int i = 0; i < 8; i++) {
                    q.add(executeMove(cursor, i, moves));
                    m.add(cursorMove);
                }
            }
        }
        System.out.println(-1);

    }

    public static String executeMove(String cursor, int move, ArrayList<int[]> moves) {
        int[] curMove = moves.get(move);
        StringBuilder sb = new StringBuilder(cursor);

        for (int i = 0; i < curMove.length; i++) {
            int tmp = curMove[i];

            switch (sb.charAt(tmp)) {
                case 'N':
                    sb.setCharAt(tmp, 'E');
                    break;
                case 'E':
                    sb.setCharAt(tmp, 'S');
                    break;
                case 'S':
                    sb.setCharAt(tmp, 'W');
                    break;
                case 'W':
                    sb.setCharAt(tmp, 'N');
                    break;
            }
        }
        return sb.toString();
    }

    public static boolean sameFace(String s) {
        char first = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != first)
                return false;
        }
        return true;
    }
}
