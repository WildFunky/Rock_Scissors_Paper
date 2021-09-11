import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {
    public static void main(String[] args)  {

       Set<String> argsSet = new HashSet<>(Arrays.asList(args));
        if (args.length < 3) {
            System.out.println("ERROR! Too few options. You need 3 or more");
            return;
        } else if (args.length % 2 == 0) {
            System.out.println("ERROR! The number of options must be odd");
            return;
        } else if (args.length != argsSet.size()) {
            System.out.println("ERROR! Do not use the same values");
            return;
        }

        KeyGenerator keyGenerator = new KeyGenerator();
        Random random = new Random();
        Court court = new Court();
        TableGenerator table = new TableGenerator(args);

        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            byte[] secretKey = keyGenerator.generateKey();
            int turnAi = random.nextInt(args.length);

            try {
                keyGenerator.generateHMAC(secretKey, String.valueOf(turnAi));
                System.out.println("HMAC: " + keyGenerator.byteToHex(keyGenerator.getHmac()));
            } catch (NoSuchAlgorithmException | InvalidKeyException e) {
                e.printStackTrace();
            }

            StringBuilder menu = new StringBuilder();
            menu.append("Available moves:\n");
            for (int i = 0; i < args.length; i++) {
                menu.append(i + 1).append(" - ").append(args[i]).append("\n");
            }
            menu.append("0 - exit\n").append("? - help\n");
            System.out.print(menu);
            System.out.print("Enter your move: ");
            input = scanner.nextLine();
            if (input.equals("?")){
                table.drawTable();
                continue;
            }

            int turnUser;
            try {
                turnUser = Integer.parseInt(input);
                if (turnUser == 0) {
                    break;
                }
                System.out.println("Your move: " + args[turnUser-1]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e ) {
                System.out.printf("ERROR! Turns must be a numbers 1-%d. Try again.\n\n", args.length);
                continue;
            }
            System.out.println("Computer move: " + args[turnAi]);
            System.out.println(court.choiceWinner(args, turnUser-1, turnAi));
            System.out.println( "HMAC key: " + keyGenerator.byteToHex(keyGenerator.getSecret()) + "\n");


        }

    }

}
