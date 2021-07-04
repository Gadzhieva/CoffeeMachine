package machine;

public class Coffee {
    int water;
    int milk;
    int beans;
    static final int CUPS = 1;
    int cost;

    public Coffee(String type) {
        switch (type) {
            case "1":
                water = 250;
                milk = 0;
                beans = 16;
                cost = 4;
                break;
            case "2":
                water = 350;
                milk = 75;
                beans = 20;
                cost = 7;
                break;
            case "3":
                water = 200;
                milk = 100;
                beans = 12;
                cost = 6;
                break;
            default:
                System.out.println("error");
                break;
        }
    }
}