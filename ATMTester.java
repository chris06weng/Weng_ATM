public class ATMTester {
    public static void main(String[] args) throws Exception {
        ATM.openAccount("Chris", 100);
        ATM.openAccount("Chris", 200);
        ATM.openAccount("Ronan", 10);
        ATM.openAccount("Aidan", 10000);
        ATM.closeAccount("Ronan");
        ATM.withdrawMoney("Ronan", 10);
        ATM.closeAccount("Ronan");
        ATM.depositMoney("Ronan", 100);
        ATM.depositMoney("Chris", 1000);
        ATM.transferMoney("Aidan", "Chris", 5000);
        ATM.transferMoney("Chris", "Aidan", 100000);
        ATM.audit();
    }
}