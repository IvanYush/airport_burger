package it;

import com.javaacademy.burger.Waitress;
import com.javaacademy.burger.PayTerminal;
import com.javaacademy.burger.Kitchen;
import com.javaacademy.burger.Steakhouse;
import com.javaacademy.burger.Paycheck;
import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static com.javaacademy.burger.Currency.RUB;
import static com.javaacademy.burger.Currency.USD;
import static com.javaacademy.burger.Currency.MOZAMBICAN_DOLLARS;
import static com.javaacademy.burger.dish.DishType.BURGER;
import static com.javaacademy.burger.dish.DishType.RIBS;
import static com.javaacademy.burger.dish.DishType.FRIED_POTATO;
import static org.mockito.ArgumentMatchers.any;

/**
 * Тест ресторана
 */
public class RestaurantIT {

    @Test
    @DisplayName("проверка работы ресторана для босса")
    public void restaurantTestForBoss() {
        Waitress waitress = new Waitress();
        PayTerminal payTerminal = new PayTerminal();
        Kitchen kitchen = new Kitchen();
        Steakhouse steakhouse = new Steakhouse(waitress, kitchen, payTerminal);

        //проверка чека
        Paycheck paycheckResult = steakhouse.makeOrder(BURGER, RUB);
        Paycheck paycheckExpected = new Paycheck(BigDecimal.valueOf(300), RUB, BURGER);

        Assertions.assertEquals(paycheckExpected, paycheckResult);

        //проверка блюда
        Dish burgerExpected = new Dish(BURGER);
        Dish dishResult = kitchen.getCompletedDishes().get(BURGER).element();

        Assertions.assertEquals(burgerExpected, dishResult);

    }

    @Test
    @DisplayName("проверка работы ресторана для санэпидемстанции")
    public void restaurantTestForSanepidem() {
        Waitress waitress = new Waitress();
        Kitchen kitchen = new Kitchen();
        PayTerminal payTerminalMock = Mockito.mock(PayTerminal.class);
        Mockito.when(payTerminalMock.pay(RIBS, RUB)).thenReturn(new Paycheck(BigDecimal.valueOf(700), RUB, RIBS));
        Steakhouse steakhouse = new Steakhouse(waitress, kitchen, payTerminalMock);

        Paycheck paycheckResult = steakhouse.makeOrder(RIBS, RUB);
        Paycheck paycheckExpected = new Paycheck(BigDecimal.valueOf(700), RUB, RIBS);

        Assertions.assertEquals(paycheckExpected, paycheckResult);

    }

    @Test
    @DisplayName("проверка работы ресторана для налоговой")
    public void restaurantTestForNalog1() {
        Kitchen kitchenMock = Mockito.mock(Kitchen.class);
        Waitress waitressMock = Mockito.mock(Waitress.class);
        PayTerminal terminalSpy = Mockito.spy(PayTerminal.class);

        Mockito.doReturn(true)
                .when(waitressMock)
                .giveOrderToKitchen(any(DishType.class), any(Kitchen.class));

        Mockito.doReturn(new Paycheck(BigDecimal.valueOf(1), USD, FRIED_POTATO))
                .when(terminalSpy)
                .pay(FRIED_POTATO, USD);

        Mockito.doReturn(new Paycheck(BigDecimal.valueOf(1), MOZAMBICAN_DOLLARS, BURGER))
                .when(terminalSpy)
                .pay(BURGER, MOZAMBICAN_DOLLARS);

        Steakhouse steakhouse = new Steakhouse(waitressMock, kitchenMock, terminalSpy);

        //Ситуация №1: клиент захотел купить ребра за рубли.
        // Заказал ребра, получил чек(сумма - 700, тип заказа - ребра, валюта - рубли).
        Paycheck paycheckResult1 = steakhouse.makeOrder(RIBS, RUB);
        Paycheck paycheckExpected1 = new Paycheck(BigDecimal.valueOf(700), RUB, RIBS);
        Assertions.assertEquals(paycheckExpected1, paycheckResult1);

        //Ситуация №2: клиент захотел купить картошку за доллары.
        // Заказал картошку, получил чек(1, картошка, доллар)
        Paycheck paycheckResult2 = steakhouse.makeOrder(FRIED_POTATO, USD);
        Paycheck paycheckExpected2 = new Paycheck(BigDecimal.valueOf(1), USD, FRIED_POTATO);
        Assertions.assertEquals(paycheckExpected2, paycheckResult2);

        //Ситуация №3: клиент захотел купить бургер за мозамбикские доллары.
        // Заказал бургер, получил чек(1, бургер, мозамбикский доллар)
        Paycheck paycheckResult3 = steakhouse.makeOrder(BURGER, MOZAMBICAN_DOLLARS);
        Paycheck paycheckExpected3 = new Paycheck(BigDecimal.valueOf(1), MOZAMBICAN_DOLLARS, BURGER);
        Assertions.assertEquals(paycheckExpected3, paycheckResult3);

    }

}
