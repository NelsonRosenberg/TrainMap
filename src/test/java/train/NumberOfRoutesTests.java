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
public class NumberOfRoutesTests {

    @Autowired
    TrainController trainController;

    @Test
    public void testFindNumberOfRoutesWithMaxDistanceCC30() {
        String result = trainController.getNumberOfRoutesWithMaxDistance(new City("C", null), new City("C", null), 30);

        System.err.println("-------------------------");
        System.err.println(" The number of different routes from C to C with a distance of less than 30.");
        System.err.println(result);

        assertEquals("7", result);

    }

}
