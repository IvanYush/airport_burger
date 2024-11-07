import com.javaacademy.burger.*;
import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class KitchenTest {

    @Test
    @DisplayName("Успешное приготовление бургера")
    public void cookBurger() {
        Kitchen kitchen = new Kitchen();
        Kitchen kitchenSpy = Mockito.spy(Kitchen.class);

        Assertions.assertEquals();

    }
}
