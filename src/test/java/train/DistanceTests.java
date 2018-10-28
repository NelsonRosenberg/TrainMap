package train;

import java.util.Arrays;
import java.util.List;
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
public class DistanceTests {

    @Autowired
    TrainController trainController;

    @Test
    public void testDistanceBetweenABC() {
        List<City> route = Arrays.asList(new City("A", null), new City("B", null), new City("C", null));
        String result = trainController.getDistanceBetweenCities(route);

        System.err.println("-------------------------");
        System.err.println("The distance of the route A-B-C.");
        System.err.println(result);

        assertEquals("9", result);
    }

    @Test
    public void testDistanceBetweenAD() {
        List<City> route = Arrays.asList(new City("A", null), new City("D", null));
        String result = trainController.getDistanceBetweenCities(route);

        System.err.println("-------------------------");
        System.err.println("The distance of the route A-D");
        System.err.println(result);

        assertEquals("5", trainController.getDistanceBetweenCities(route));
    }

    @Test
    public void testDistanceBetweenADC() {
        List<City> route = Arrays.asList(new City("A", null), new City("D", null), new City("C", null));
        String result = trainController.getDistanceBetweenCities(route);

        System.err.println("-------------------------");
        System.err.println("The distance of the route A-D-C");
        System.err.println(result);

        assertEquals("13", trainController.getDistanceBetweenCities(route));
    }

    @Test
    public void testDistanceBetweenAEBCD() {
        List<City> route = Arrays.asList(new City("A", null), new City("E", null), new City("B", null),
                new City("C", null), new City("D", null));
        String result = trainController.getDistanceBetweenCities(route);

        System.err.println("-------------------------");
        System.err.println("The distance of the route A-E-B-C-D");
        System.err.println(result);

        assertEquals("22", trainController.getDistanceBetweenCities(route));
    }

    @Test
    public void testDistanceBetweenAED() {
        List<City> route = Arrays.asList(new City("A", null), new City("E", null), new City("D", null));
        String result = trainController.getDistanceBetweenCities(route);

        System.err.println("-------------------------");
        System.err.println("The distance of the route A-E-D");
        System.err.println(result);

        assertEquals("NO SUCH ROUTE", trainController.getDistanceBetweenCities(route));
    }

}
