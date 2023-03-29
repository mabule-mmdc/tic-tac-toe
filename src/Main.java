public class Main {
    public static void main(String[] args) {
        try {
            int badNumber = 200/0;
            System.out.println("Success!");
        } catch(ArithmeticException e) {
            ErrorDialog errorDialog = new ErrorDialog();
            errorDialog.pack();
            errorDialog.setVisible(true);
        }
    }
}