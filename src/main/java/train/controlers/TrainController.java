package train.controlers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import train.models.City;
import train.services.TrainService;

@Controller
public class TrainController {

    @Autowired
    TrainService trainService;

    public String getDistanceBetweenCities(List<City> cities) {
        return getResultString(trainService.getDistanceBetweenCities(cities));
    }

    public String getPossibleRoutesWithMaxStops(City origin, City destination, Integer maxStops) {
        return getResultString(trainService.getPossibleRoutesWithMaxStops(origin, destination, maxStops));
    }

    public String getShortestRoute(City origin, City destination) {
        return getResultString(trainService.getShortestRoute(origin, destination));
    }

    public String getNumberOfRoutesWithMaxDistance(City origin, City destination, Integer maxDistance) {
        return getResultString(trainService.getNumberOfRoutesWithMaxDistance(origin, destination, maxDistance));
    }

    private String getResultString(Integer result) {
        String returnString = "NO SUCH ROUTE";

        if (result != 0) {
            returnString = String.valueOf(result);
        }

        return returnString;
    }

}
