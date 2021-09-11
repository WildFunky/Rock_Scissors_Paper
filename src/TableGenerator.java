public class TableGenerator {
    String[][] table;
    String [] turns;


    public TableGenerator (String[] turns) {
        this.turns = turns;
        this.table = new String[turns.length][turns.length];
        Court court = new Court();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (court.choiceWinner(turns, i, j).equals("You win")) {
                    table[i][j] = "WIN";
                } else if (court.choiceWinner(turns,i, j).equals("Computer win")){
                    table[i][j] = "LOSE";
                } else {
                    table[i][j] = "DRAW";
                }
            }
        }
    }

    public void drawTable () {
        int maxColLength = 8;

        for (String s: turns
        ) {
            if (s.length() > maxColLength) {
                maxColLength = s.length();
            }
        }
        StringBuilder user = new StringBuilder("\\ User ");
        while (user.length() < maxColLength + 2) {
            user.insert(0, " ");
        }
        user.append("|");
        StringBuilder pc = new StringBuilder("PC  \\");
        while (pc.length() < maxColLength + 2) {
            pc.append(" ");
        }
        pc.append("|");
        StringBuilder delimiter = new StringBuilder();
        while (delimiter.length() < maxColLength + 2) {
            delimiter.append("-");
        }
        delimiter.append("+");

        int colLength = 6;
        for (String s: turns
        ) {
            if (s.length() >= colLength - 1) {
                colLength = s.length() + 2;
            }
            StringBuilder tempUser = new StringBuilder();
            StringBuilder tempPc = new StringBuilder(s);
            StringBuilder tempDelimiter = new StringBuilder();
            while (tempPc.length() < colLength) {
                tempPc.append(" ");
            }
            while (tempUser.length() < colLength){
                tempUser.append(" ");
            }
            while (tempDelimiter.length() < colLength) {
                tempDelimiter.append("-");
            }
            pc.append(tempPc).append("|");
            user.append(tempUser).append("|");
            delimiter.append(tempDelimiter).append("+");
            colLength = 6;
        }

        System.out.println(user);
        System.out.println(pc);
        System.out.println(delimiter);

        for (int i = 0; i < table.length; i++) {
            StringBuilder turnCell = new StringBuilder(turns[i]);
            while (turnCell.length() < maxColLength + 2) {
                turnCell.append(" ");
            }
            System.out.print(turnCell + "|");
            for (int j = 0; j < table.length; j++) {
                StringBuilder tmp = new StringBuilder(table[i][j]);
                if (turns[j].length() >= colLength - 1) {
                    colLength = turns[j].length() + 2;
                }
                while (tmp.length() < colLength) {
                    tmp.append(" ");
                }
                System.out.print(tmp.append("|"));
                colLength = 6;
            }
            System.out.println();
            System.out.println(delimiter);
        }
    }
}
