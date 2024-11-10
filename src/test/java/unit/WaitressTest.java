package unit;

import com.javaacademy.burger.Kitchen;
import com.javaacademy.burger.Waitress;
import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.javaacademy.burger.dish.DishType.*;

public class WaitressTest {

    @Test
    @DisplayName("заказ официанту на бургер")
    public void orderOnBurger() {
        Kitchen kitchenMock = Mockito.mock(Kitchen.class);
        Waitress waitress = new Waitress();
        boolean result = waitress.giveOrderToKitchen(BURGER, kitchenMock);
        boolean expected = true;
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("заказ официанту на фуагра")
    public void orderOnFuagra() {
        Kitchen kitchenMock = Mockito.mock(Kitchen.class);
        Waitress waitress = new Waitress();
        boolean result = waitress.giveOrderToKitchen(FUAGRA, kitchenMock);
        boolean expected = false;
        Assertions.assertEquals(expected, result);
    }

}
