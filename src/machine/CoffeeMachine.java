package machine;

import java.util.Scanner;

import static machine.MachineState.*;

public class CoffeeMachine {

    int water;
    int milk;
    int beans;
    int cups;
    int money;

    MachineState state;
    private final Scanner scanner = new Scanner(System.in);

    public CoffeeMachine() {
        water = 400;
        milk = 540;
        beans = 120;
        cups = 9;
        money = 550;
        state = START;
    }

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();
        machine.run();
    }

    private void run() {
        while (!EXIT.equals(state)) {
            String command = readCommand();
            doCommand(command);
        }
    }

    public String readCommand() {
        switch (state) {
            case START:
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                state = CHOOSE_ACTION;
                return scanner.nextLine();
            case CHOOSE_COFFEE:
                System.out.println();
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, " +
                        "back - to main menu:");
                return scanner.nextLine();
            case ADD_WATER:
                System.out.println();
                System.out.println("Write how many ml of water you want to add:");
                return scanner.nextLine();
            case ADD_MILK:
                System.out.println("Write how many ml of milk you want to add:");
                return scanner.nextLine();
            case ADD_COFFEE:
                System.out.println("Write how many grams of coffee beans you want to add:");
                return scanner.nextLine();
            case ADD_CUPS:
                System.out.println("Write how many disposable cups of coffee you want to add:");
                return scanner.nextLine();
            case CHOOSE_ACTION:
            case EXIT:
            default:
                return null;
        }
    }

    public void doCommand(String command) {
        switch (state) {
            case CHOOSE_ACTION:
                applyAction(command);
                break;
            case CHOOSE_COFFEE:
                if (!"back".equals(command)) {
                    makeCoffee(command);
                }
                state = START;
                break;
            case ADD_WATER:
                water += Integer.parseInt(command);
                state = ADD_MILK;
                break;
            case ADD_MILK:
                milk += Integer.parseInt(command);
                state = ADD_COFFEE;
                break;
            case ADD_COFFEE:
                beans += Integer.parseInt(command);
                state = ADD_CUPS;
                break;
            case ADD_CUPS:
                cups += Integer.parseInt(command);
                state = START;
                System.out.println();
                break;
            case START:
            case EXIT:
        }
    }

    public void applyAction(String action) {
        switch (action) {
            case "buy":
                state = CHOOSE_COFFEE;
                break;
            case "fill":
                state = ADD_WATER;
                break;
            case "take":
                takeTheMoney();
                state = START;
                break;
            case "remaining":
                printRemainingSupplies();
                state = START;
                break;
            case "exit":
                state = EXIT;
                break;
            default:
                System.out.println("error");
                break;
        }
    }

    public void takeTheMoney() {
        System.out.println("I gave you $" + money);
        System.out.println();
        money = 0;
    }

    public void printRemainingSupplies() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(beans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + money + " of money");
        System.out.println();
    }

    public void makeCoffee(String command) {
        Coffee coffee = new Coffee(command);
        if (this.water < coffee.water) {
            System.out.println("Sorry, not enough water!");
            System.out.println();
            return;
        }
        if (this.beans < coffee.beans) {
            System.out.println("Sorry, not enough coffee!");
            System.out.println();
            return;
        }
        if (this.cups < Coffee.CUPS) {
            System.out.println("Sorry, not enough cups!");
            System.out.println();
            return;
        }
        if (this.milk < coffee.milk) {
            System.out.println("Sorry, not enough milk!");
            System.out.println();
            return;
        }
        this.cups -= Coffee.CUPS;
        this.water -= coffee.water;
        this.beans -= coffee.beans;
        this.milk -= coffee.milk;
        this.money += coffee.cost;
        System.out.println("I have enough resources, making you a coffee!");
        System.out.println();
    }
}


