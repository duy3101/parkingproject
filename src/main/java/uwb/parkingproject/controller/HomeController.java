
package uwb.parkingproject.controller;

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



	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String admin(Model model) {
		
		// university all payments that have not been paid in >=7 days

		// university can find current % of spots filled in each parking lot

		// university can find current % of spots filled in each spot type

	
		return "admin";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String user(@Validated User user, Model model) {
		System.out.println("User Page Requested");

		//  EnterVehicleInfo, update database
		
		this.user = user;

		model.addAttribute("Name", this.user.getName());

		model.addAttribute("Plate", user.getPlate());
		ParkingLot find_spot_by_lot = new ParkingLot();
		model.addAttribute("ParkingLot", find_spot_by_lot);
		ParkingLot2 find_spot_by_type = new ParkingLot2();
		model.addAttribute("ParkingLot2", find_spot_by_type);

	
		// System.out.println(user.getPlate());
		// System.out.println(user.getColor());
		// System.out.println(user.getManu());
		// System.out.println(user.getModel());


		//  EnterVehicleInfo, update database
		try {
			QueryManager manager = new QueryManager();
			int car_type_to_int = manager.CarTypeToInt(user.getCarType());
			manager.EnterVehicleInfo(user.getPlate(), user.getColor(), user.getManu(), user.getModel(), car_type_to_int);
		}
		catch (Exception e) {
            System.out.println("Fail to add vehicle info");
		}
		



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
	public String vacantspot(@ModelAttribute("ParkingLot") ParkingLot find_spot_by_lot, Model model) {
		model.addAttribute("Name", this.user.getName());

		System.out.println(find_spot_by_lot.getParkingLotName());

		String lot_name = find_spot_by_lot.getParkingLotName();
		model.addAttribute("lot_name", lot_name);


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

	@RequestMapping(value = "/vacantspotbytype", method = RequestMethod.POST)
	public String vacantspotbytype(@ModelAttribute("ParkingLot2") ParkingLot2 find_spot_by_type, Model model) {

		model.addAttribute("Name", this.user.getName());

		System.out.println(find_spot_by_type.getParkingSpotType());

		String type_name = find_spot_by_type.getParkingSpotType();
		model.addAttribute("type_name", type_name);


		try {
			QueryManager manager = new QueryManager();
			ArrayList<ReturnType> return_list = manager.GetVacantSpotFromType(type_name);
			model.addAttribute("type_list", return_list);
		}
		catch (Exception e) {
			System.out.println("Database connection problem");
        }
		return "vacantspotbytype";
	}


}

