public class Court {

    public String choiceWinner (String[] turns, int turnUser, int turnAi) {
        int x = turns.length / 2;
        int difference = Math.abs(turnUser - turnAi);

        if (difference == 0) {
            return "DRAW";
        } else if (difference <= x) {
            return turnUser < turnAi ? "You win" : "Computer win";
        } else {
            return turnUser > turnAi ? "You win" : "Computer win";
        }
    }
}
