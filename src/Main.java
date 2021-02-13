public class Main {
    public static void main(String arg[]){
        Window window = new Window();
        Thread thread = new Thread(window);
        thread.start();
    }
}
