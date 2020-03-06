
package uwb.parkingproject.controller;

import java.util.ArrayList;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uwb.parkingproject.model.*;
import uwb.parkingproject.service.*;

@Controller
public class HomeController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

        // try {
        //     GenerateParking query = new GenerateParking();
        // }
        // catch (Exception e) {
        //     System.out.println("Database connection problem");
        // }
		return "home";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String user(@Validated User user, Model model) {
		System.out.println("User Page Requested");
        model.addAttribute("userName", user.getUserName());

        try {
            ReadTable query = new ReadTable();
            ArrayList<Fruit> fruitList = query.getResult();
            model.addAttribute("fruitList", fruitList);
        }
        catch (Exception e) {
            System.out.println("Database connection problem");
        }
        
		return "user";
	}
}