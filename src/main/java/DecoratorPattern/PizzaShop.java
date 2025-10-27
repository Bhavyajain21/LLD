package DecoratorPattern;

import DecoratorPattern.Base.BasePizza;
import DecoratorPattern.Base.Farmhouse;
import DecoratorPattern.decorator.ExtraCheese;
import DecoratorPattern.decorator.Mushroom;

public class PizzaShop {
    public static void main(String[] args) {
        BasePizza basePizza = new Mushroom(new ExtraCheese(new Farmhouse()));
        System.out.println(basePizza.cost());
    }
}
