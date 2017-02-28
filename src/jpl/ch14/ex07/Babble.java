package jpl.ch14.ex07;


class Babble extends Thread {

    static boolean doYield;
    static int howOften;
    private String word;

    Babble(String whatToSay) {
        word = whatToSay;
    }

    public void run() {
        for(int i = 0; i < howOften; i++) {
            System.out.println(word);
            if(doYield) {
                Thread.yield();
            }
        }
    }

    public static void main ( String[] args) {
        try {
            doYield = new Boolean(args[0]).booleanValue();
            howOften = Integer.parseInt(args[1]);

            for(int i = 2; i < args.length; i++) {
                new Babble(args[i]).start();
            }
        } catch (ArrayIndexOutOfBoundsException e ){
            doYield = true;
            howOften = 2;
            new Babble("incompleteArgsTest1").start();
            new Babble("incompleteArgsTest2").start();
        }
    }

}
