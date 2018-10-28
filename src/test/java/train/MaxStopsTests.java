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
public class MaxStopsTests {

    @Autowired
    TrainController trainController;

    @Test
    public void testFindRouteWithMaxStopsCC3() {
        String result = trainController.getPossibleRoutesWithMaxStops(new City("C", null), new City("C", null), 3);

        System.err.println("-------------------------");
        System.err.println("The number of trips starting at C and ending at C with a maximum of 3 stops.");
        System.err.println(result);

        assertEquals("2", result);
    }

    @Test
    public void testFindRouteWithMaxStopsAC4() {

        String result = trainController.getPossibleRoutesWithMaxStops(new City("A", null), new City("C", null), 4);

        System.err.println("-------------------------");
        System.err.println("The number of trips starting at C and ending at C with a maximum of 3 stops.");
        System.err.println(result);

        assertEquals("4", result);
    }

}
