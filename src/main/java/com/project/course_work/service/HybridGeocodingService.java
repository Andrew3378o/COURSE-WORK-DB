package com.project.course_work.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class HybridGeocodingService {

    private final Map<String, double[]> staticLocations = new HashMap<>();

    public HybridGeocodingService() {

        // Україна
        staticLocations.put("ЧАЕС", new double[]{51.389, 30.099});
        staticLocations.put("ПРИП'ЯТЬ", new double[]{51.405, 30.053});
        staticLocations.put("КИЇВ", new double[]{50.4501, 30.5234});
        staticLocations.put("ЛЬВІВ", new double[]{49.8397, 24.0297});
        staticLocations.put("ОДЕСА", new double[]{46.4825, 30.7233});
        staticLocations.put("ХАРКІВ", new double[]{49.9935, 36.2304});
        staticLocations.put("ДНІПРО", new double[]{48.4685, 34.9944});
        staticLocations.put("ЗАПОРІЖЖЯ", new double[]{47.8510, 35.1000});
        staticLocations.put("СУМИ", new double[]{50.9216, 34.8021});
        staticLocations.put("РІВНЕ", new double[]{50.6199, 26.2516});
        staticLocations.put("УМАНЬ", new double[]{48.7500, 30.2200});

        // США (Ключові міста)
        staticLocations.put("НЬЮ-ЙОРК", new double[]{40.7128, -74.0060});
        staticLocations.put("ЛОС-АНДЖЕЛЕС", new double[]{34.0522, -118.2437});
        staticLocations.put("ЧІКАГО", new double[]{41.8781, -87.6298});
        staticLocations.put("Х'ЮСТОН", new double[]{29.7604, -95.3698});
        staticLocations.put("ФІЛАДЕЛЬФІЯ", new double[]{39.9526, -75.1652});
        staticLocations.put("ВАШИНГТОН", new double[]{38.8951, -77.0364});
        staticLocations.put("США", new double[]{40.1533, -76.7253});

        // Європа
        staticLocations.put("ЛОНДОН", new double[]{51.5074, 0.1278});
        staticLocations.put("ПАРИЖ", new double[]{48.8566, 2.3522});
        staticLocations.put("БЕРЛІН", new double[]{52.5200, 13.4050});
        staticLocations.put("РИМ", new double[]{41.9028, 12.4964});
        staticLocations.put("МАДРИД", new double[]{40.4168, -3.7038});
        staticLocations.put("ВАРШАВА", new double[]{52.2297, 21.0122});
        staticLocations.put("ПРАГА", new double[]{50.0755, 14.4378});
        staticLocations.put("НІМЕЧЧИНА", new double[]{51.1657, 10.4515});
        staticLocations.put("ВЕЛИКОБРИТАНІЯ", new double[]{54.4247, -3.5000});
        staticLocations.put("ФРАНЦІЯ", new double[]{47.733, 1.633});
        staticLocations.put("ІСПАНІЯ", new double[]{41.1827, 0.5671});
        staticLocations.put("ШВЕЦІЯ", new double[]{60.3710, 18.1546});
        staticLocations.put("ШВЕЙЦАРІЯ", new double[]{46.722, 6.840});

        // Азія/Світ
        staticLocations.put("ТОКІО", new double[]{35.6895, 139.6917});
        staticLocations.put("СЕУЛ", new double[]{37.5665, 126.9780});
        staticLocations.put("ПЕКІН", new double[]{39.9042, 116.4074});
        staticLocations.put("ДЕЛІ", new double[]{28.7041, 77.1025});
        staticLocations.put("КАЇР", new double[]{30.0444, 31.2357});
        staticLocations.put("БРАЗИЛІЯ", new double[]{-16.6747, -49.2642});
        staticLocations.put("КАНАДА", new double[]{43.6601, -79.3951});
        staticLocations.put("ФУКУСІМА", new double[]{37.3167, 141.0333});
        staticLocations.put("ЯПОНІЯ", new double[]{37.3167, 141.0333});
    }

    public double[] getCoordinates(String locationName) {
        if (locationName == null || locationName.trim().isEmpty()) {
            return null;
        }

        String normalizedKey = locationName.trim().toUpperCase();

        for (Map.Entry<String, double[]> entry : staticLocations.entrySet()) {
            if (normalizedKey.contains(entry.getKey()) || entry.getKey().contains(normalizedKey)) {
                System.out.println("GEOCODE STATIC HIT: " + entry.getKey());
                return entry.getValue();
            }
        }

        System.out.println("GEOCODE STATIC FAIL: " + locationName + ". Координати не знайдені.");
        return null;
    }
}