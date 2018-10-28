package train;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import train.controlers.TrainController;
import train.models.City;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShortestRouteTests {

    @Autowired
    TrainController trainController;

    @Test
    public void testFindShortestRouteAC() {
        String result = trainController.getShortestRoute(new City("A", null), new City("C", null));

        System.err.println("-------------------------");
        System.err.println("The length of the shortest route (in terms of distance to travel) from A to C.");
        System.err.println(result);

        assertEquals("9", result);
    }

    @Test
    public void testFindShortestRouteBB() {
        String result = trainController.getShortestRoute(new City("B", null), new City("B", null));

        System.err.println("-------------------------");
        System.err.println("The length of the shortest route (in terms of distance to travel) from B to B.");
        System.err.println(result);

        assertEquals("9", result);
    }

}
