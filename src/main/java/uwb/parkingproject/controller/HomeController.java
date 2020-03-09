
package uwb.parkingproject.controller;

import java.sql.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import uwb.parkingproject.model.*;
import uwb.parkingproject.service.*;


@Controller
public class HomeController {

	private User user;

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

		return "home";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String user(@Validated User user, Model model) {
		System.out.println("User Page Requested");

		model.addAttribute("ID", user.getID());
		model.addAttribute("Plate", user.getPlate());
		ParkingLot res = new ParkingLot();
		model.addAttribute("ParkingLot", res);

		this.user = user;
		
		// System.out.println(user.getPlate());
		// System.out.println(user.getColor());
		// System.out.println(user.getManu());
		// System.out.println(user.getModel());


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

	@RequestMapping(value = "/vacantspot", method = RequestMethod.POST)
	public String vacantspot(@ModelAttribute("ParkingLot") ParkingLot res, Model model) {
		model.addAttribute("ID", this.user.getID());
		System.out.println(res.getParkingLotName());

		String lot_name = res.getParkingLotName();

		try {
			QueryManager manager = new QueryManager();
			ArrayList<ReturnType> return_list = manager.GetVacantSpotFromLot(lot_name);
			model.addAttribute("spot_list", return_list);

		}
		catch (Exception e) {
			System.out.println("Database connection problem");
        }



		return "vacantspot";
	}

}

