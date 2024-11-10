package unit;

import com.javaacademy.burger.Kitchen;
import com.javaacademy.burger.exception.KitchenHasNoGasException;
import com.javaacademy.burger.exception.UnsupportedDishTypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.javaacademy.burger.dish.DishType.BURGER;
import static com.javaacademy.burger.dish.DishType.FUAGRA;

/**
 * Тест кухни
 */
public class KitchenTest {

    @Test
    @DisplayName("Успешное приготовление бургера")
    public void cookBurger() {
        Kitchen kitchen = new Kitchen();
        kitchen.cook(BURGER);
        Assertions.assertNotNull(kitchen.getCompletedDishes());
    }

    @Test
    @DisplayName("Бургер с ошибкой")
    public void cookBurgerFailure() {
        Kitchen kitchen = new Kitchen();
        kitchen.setHasGas(false);
        Assertions.assertThrows(KitchenHasNoGasException.class, () -> kitchen.cook(BURGER));
    }

    @Test
    @DisplayName("Фуагра с ошибкой")
    public void cookFuagraFailure() {
        Kitchen kitchen = new Kitchen();
        Assertions.assertThrows(UnsupportedDishTypeException.class, () -> kitchen.cook(FUAGRA));
    }
}
