package machine;

import java.util.Scanner;


public class MachineState {
    int water = 400;    //ml
    int milk = 540;     //ml
    int beans = 120;    //g
    int money = 550;    //$
    int cups = 9;
    boolean isOn = true;
    Scanner in = new Scanner(System.in);

    public void run() {
        while (isOn) {
            interact();
        }
    }
    public void interact() {
        System.out.print("Write action (buy, fill, take, remaining, exit): ");
        String action = in.next();
        System.out.println(action + "\n");
        switch (action) {
            case "buy" :     buy(); break;
            case "fill":    fill(); break;
            case "take":    take(); break;
            case "remaining": remain(water, milk, beans, money, cups); break;
            case "exit":    isOn = false; break;
            default:        System.out.println("Unsupported action.");
        }
    }

    public void remain(int water, int milk, int beans, int money, int cups){
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water\n" +
                milk + " of milk\n" +
                beans + " of coffee beans\n" +
                cups + " of disposable cups\n" +
                money + " of money");
        System.out.println();
    }
    public void  buy(){
        System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String coffee = in.next();
        System.out.println(coffee);
        if (coffee.equals("back")){
            System.out.println();
            return;
        } else if (canMake(water, milk, beans, cups, coffee)){
            System.out.println("I have enough resources, making you a coffee!");
            switch (coffee) {
                case "1":     water -= 250; beans -= 16; cups -=1; money += 4; break;
                case "2":     water -= 350; milk -= 75; beans -= 20; cups -= 1; money += 7; break;
                case "3":     water -= 200; milk -= 100; beans -= 12; cups -= 1; money += 6; break;
                default:    System.out.println("Unknown valueable");
            }
        } else {
            System.out.println("Sorry, not enough resourses!");
        }
        System.out.println();
    }
    public boolean canMake(int water, int milk, int beans, int cups, String coffee) {
        boolean can = true;
        switch (coffee) {
            case "1":     if (water < 250 || beans < 16 || cups < 1) {can = false;} break;
            case "2":     if (water < 350 || milk < 75 || beans < 20 || cups < 1) {can = false;} break;
            case "3":     if (water < 200 || milk < 100 || beans < 12 || cups < 1) {can = false;} break;
        }
        return can;
    }
    public void  fill(){
        System.out.print("Write how many ml of water do you want to add: ");
        water += in.nextInt();
        System.out.println(water);
        System.out.print("Write how many ml of milk do you want to add: ");
        milk += in.nextInt();
        System.out.println(milk);
        System.out.print("Write how many grams of coffee beans do you want to add: ");
        beans += in.nextInt();
        System.out.println(beans);
        System.out.print("Write how many disposable cups of coffee do you want to add: ");
        cups += in.nextInt();
        System.out.println(cups);
        System.out.println();
    }
    public void  take(){
        System.out.println("I gave you $" + money);
        money = 0;
        System.out.println();
    }
}
