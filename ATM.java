import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class ATM {
    static HashMap<String, Double> accounts = new HashMap<String, Double>();
    static ArrayList<String> userIDs = new ArrayList<String>();

    public static void openAccount(String userID, double amnt) throws Exception {
        if (accounts.containsKey(userID)) {
            throw new Exception("User " + userID + " is not available, please try again.");
        } else {
            accounts.put(userID, amnt);
            userIDs.add(userID);
        }
    }

    public static void closeAccount(String userID) throws Exception {
        if (!check(userID))
            throw new Exception("User does not exist");
        Double bal = accounts.get(userID);
        if (bal == 0) {
            accounts.remove(userID);
            userIDs.remove(userID);
        } else {
            throw new Exception("Your account still has " + bal + ", please remove balance before closing account.");
        }
    }

    public static Double checkBalance(String userID) throws Exception {
        if (!check(userID))
            throw new Exception("User does not exist");
        return accounts.get(userID);
    }

    public static void depositMoney(String userID, double amnt) throws Exception {
        if (!check(userID))
            throw new Exception("User does not exist. You're also broke AF.");
        Double bal = accounts.get(userID);
        bal += amnt;
        accounts.replace(userID, bal);
        System.out.println("New balance: " + bal);
    }

    public static void withdrawMoney(String userID, double amnt) throws Exception {
        if (!check(userID))
            throw new Exception("User does not exist. You're also broke AF.");
        Double bal = accounts.get(userID);
        if (amnt > bal)
            throw new Exception("Insufficient funds. You're also broke AF.");
        bal -= amnt;
        accounts.replace(userID, bal);
        System.out.println("New balance: " + bal);
    }

    public static boolean transferMoney(String userID1, String userID2, double amnt) throws Exception {
        if ((!check(userID1)) || (!check(userID2)))
            throw new Exception("One or both users do not exist");
        Double bal1 = accounts.get(userID1);
        Double bal2 = accounts.get(userID2);
        if (amnt > bal1) {
            System.out.println("Failed transfer, insufficient Funds. You are broke AF.");
            return false;
        } else {
            bal1 -= amnt;
            bal2 += amnt;
            accounts.replace(userID1, bal1);
            accounts.replace(userID2, bal2);
            return true;
        }
    }

    public static void audit() throws FileNotFoundException {
        File file = new File("AccountAudit.txt");
        file.delete();

        File newFile = new File("AccountAudit.txt");
        PrintWriter pw = new PrintWriter(newFile);

        for (int k = 0; k < userIDs.size(); k++) {
            String input = "" + userIDs.get(k) + ": " + accounts.get(userIDs.get(k));
            pw.print(input + "\n");
        }

        pw.close();
    }

    static private boolean check(String userID) {
        if (accounts.containsKey(userID))
            return true;
        return false;
    }
}