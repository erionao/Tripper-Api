package com.devfreaks.tripper.dump;

import com.devfreaks.tripper.entities.Airport;
import com.devfreaks.tripper.entities.Country;
import com.devfreaks.tripper.entities.QCountry;
import com.devfreaks.tripper.entities.User;
import com.devfreaks.tripper.entities.enums.UserRole;
import com.devfreaks.tripper.services.AirportService;
import com.devfreaks.tripper.services.CountryService;
import com.devfreaks.tripper.services.UserService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataDump {

    public static void seed(ResourceLoader resourceLoader, UserService userService, CountryService countryService, AirportService airportService) {
        User user = new User();
        user.setFullName("John Doe");
        user.setActive(true);
        user.setLogin("john@doe.com");
        user.setPassword("johny");
        user.setRole(UserRole.ADMINISTRATOR);

        userService.save(user);

        /**
         * Kosovo
         */
        Country country = null;

        /*
        try {

            Resource resource = resourceLoader.getResource("classpath:airports.csv");
            String csvFile = resource.getFile().getAbsolutePath();

            BufferedReader br = null;
            String line = "";
            String cvsSplitBy = ",";

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] data = line.split(cvsSplitBy);

                // Actual saving
                if (countryService.findOne(QCountry.country.name.eq(data[3])) == null) {
                    country = countryService.save(new Country(data[3]));
                } else {
                    country = countryService.findOne(QCountry.country.name.eq(data[3]));
                }

                airportService.save(new Airport(data[4], data[1], country));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        System.out.println("Done");
    }

}
