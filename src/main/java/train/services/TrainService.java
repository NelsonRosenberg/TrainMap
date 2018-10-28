package train.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import train.daos.TrainDao;
import train.models.City;
import train.models.Route;

@Service
public class TrainService {

    @Autowired
    TrainDao trainDao;

    private Map<City, List<Route>> trainMap = new HashMap<>();

    /**
     * INIT METHODS
     */
    @PostConstruct
    public void init() {
        trainMap = parseRawData(trainDao.getRawMapData());
    }

    /**
     * PUBLIC METHODS
     */
    public Integer getDistanceBetweenCities(List<City> cities) {
        if (cities.size() < 2) {
            return 0;
        }

        Integer citiesTraveled = 0;
        Integer distance = 0;
        for (int i = 0; i < cities.size(); i++) {
            if (trainMap.containsKey(cities.get(i))) {
                City nextCity = null;
                if (i + 1 >= cities.size()) {
                    //no next city
                    break;
                } else {
                    nextCity = cities.get(i + 1);
                }

                List<Route> routes = trainMap.get(cities.get(i));
                for (int j = 0; j < routes.size(); j++) {
                    Route currentRoute = routes.get(j);
                    if (currentRoute.getDestination().equals(nextCity)) {
                        distance += currentRoute.getDistance();
                        citiesTraveled++;
                        break;
                    }
                }
            }
        }

        if (citiesTraveled != cities.size() - 1) {
            distance = 0;
        }

        return distance;
    }

    public Integer getPossibleRoutesWithMaxStops(City origin, City destination, Integer maxStops) {
        return possibleRoutesWithMaxStops(origin, destination, 0, maxStops);
    }

    public Integer getShortestRoute(City origin, City destination) {
        return shortestRoute(origin, destination, 0, 0);
    }

    public Integer getNumberOfRoutesWithMaxDistance(City origin, City destination, Integer maxDistance) {
        return numberOfRoutesWithMaxDistance(origin, destination, 0, maxDistance);
    }

    /**
     * PRIVATE METHODS
     */
    private Map<City, List<Route>> parseRawData(List<String> rawMapData) {
        Map<City, List<Route>> trainMap = new HashMap<>();
        Map<String, City> cities = new HashMap<>();

        for (String data : rawMapData) {
            String[] dataArray = data.trim().split("");

            String origin = dataArray[0];
            String destination = dataArray[1];
            Integer distance = Integer.parseInt(dataArray[2]);

            if (!cities.containsKey(origin)) {
                String name = origin;
                cities.put(name, new City(name, Boolean.FALSE));
            }

            if (!cities.containsKey(destination)) {
                String name = destination;
                cities.put(name, new City(name, Boolean.FALSE));
            }

            if (!trainMap.containsKey(cities.get(origin))) {
                List<Route> routes = new ArrayList<>();
                trainMap.put(cities.get(origin), routes);
            }
            trainMap.get(cities.get(origin)).add(new Route(cities.get(origin), cities.get(destination), distance));

        }

        return trainMap;
    }

    private Integer possibleRoutesWithMaxStops(City origin, City destination, Integer citiesTraveled, Integer maxStops) {
        Integer possibleRoutes = 0;
        if (trainMap.containsKey(origin) && trainMap.containsKey(destination)) {

            citiesTraveled++;
            if (citiesTraveled > maxStops) {
                return 0;
            }

            origin.setVisited(Boolean.TRUE);
            List<Route> routes = trainMap.get(origin);
            for (Route route : routes) {
                if (route.getDestination().equals(destination)) {
                    possibleRoutes++;
                    continue;
                } else if (!route.getDestination().getVisited()) {
                    possibleRoutes += possibleRoutesWithMaxStops(route.getDestination(), destination, citiesTraveled, maxStops);
                    citiesTraveled--;
                }
            }
        }

        origin.setVisited(Boolean.FALSE);
        return possibleRoutes;
    }

    private Integer shortestRoute(City origin, City destination, Integer distance, Integer shortestRoute) {

        if (trainMap.containsKey(origin) && trainMap.containsKey(destination)) {
            origin.setVisited(Boolean.TRUE);

            List<Route> routes = trainMap.get(origin);
            for (Route route : routes) {

                if (route.getDestination().equals(destination) || !route.getDestination().getVisited()) {
                    distance += route.getDistance();
                }

                if (route.getDestination().equals(destination)) {
                    if (shortestRoute == 0 || distance < shortestRoute) {
                        shortestRoute = distance;
                    }
                    break;

                } else if (!route.getDestination().getVisited()) {
                    shortestRoute = shortestRoute(route.getDestination(), destination, distance, shortestRoute);
                    distance -= route.getDistance();
                }
            }
        }

        origin.setVisited(Boolean.FALSE);
        return shortestRoute;
    }

    private Integer numberOfRoutesWithMaxDistance(City origin, City destination, Integer distance, Integer maxDistance) {
        Integer possibleRoutes = 0;
        if (trainMap.containsKey(origin) && trainMap.containsKey(destination)) {

            List<Route> routes = trainMap.get(origin);
            for (Route route : routes) {
                distance += route.getDistance();

                if (distance < maxDistance) {

                    if (route.getDestination().equals(destination)) {
                        possibleRoutes++;
                        possibleRoutes += numberOfRoutesWithMaxDistance(route.getDestination(), destination, distance, maxDistance);
                        continue;
                    } else {
                        possibleRoutes += numberOfRoutesWithMaxDistance(route.getDestination(), destination, distance, maxDistance);
                        distance -= route.getDistance();
                    }
                } else {
                    distance -= route.getDistance();
                }
            }
        }

        return possibleRoutes;
    }

}
