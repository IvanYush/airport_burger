package unit;

import com.javaacademy.burger.*;
import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Queue;

import static com.javaacademy.burger.Currency.RUB;
import static com.javaacademy.burger.dish.DishType.BURGER;

public class RestaurantTest {

    @Test
    @DisplayName("проверка работы всего ресторана")
    public void restaurantTest() {
        Waitress waitress = new Waitress();
        PayTerminal payTerminal = new PayTerminal();
        Kitchen kitchen = new Kitchen();
        Steakhouse steakhouse = new Steakhouse(waitress, kitchen, payTerminal);

        Paycheck paycheckResult = steakhouse.makeOrder(BURGER, RUB);

        Paycheck paycheckExpected = new Paycheck(BigDecimal.valueOf(300), RUB, BURGER);

        //проверка чека
        Assertions.assertEquals(paycheckExpected, paycheckResult);

        Dish burgerExpected = new Dish(BURGER);
        Queue<Dish> dishesResult = kitchen.getCompletedDishes().get(BURGER);
        
        //проверка блюда
        Assertions.assertEquals(burgerExpected, dishesResult);

    }
}
