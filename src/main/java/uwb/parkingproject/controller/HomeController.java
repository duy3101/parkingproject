
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


	@RequestMapping(value = "/admin_get_overdue_payments", method = RequestMethod.POST)
	public String admin_get_overdue_payments(Model model) {
		
		try {
			QueryManager manager = new QueryManager();
			ArrayList<ReturnType> return_list = manager.GetOverduePayments();
			model.addAttribute("return_list", return_list);
		}
		catch (Exception e) {
			System.out.println("Database connection problem");
        }
		
		return "admin_get_overdue_payments";
	}


	@RequestMapping(value = "/admin_get_percentage_of_lot", method = RequestMethod.POST)
	public String admin_get_percentage_of_lot(Model model) {
		
		try {
			QueryManager manager = new QueryManager();
			ArrayList<ReturnType> return_list = manager.GetPercentageForLot();
			model.addAttribute("return_list", return_list);
		}
		catch (Exception e) {
			System.out.println("Database connection problem");
        }

		return "admin_get_percentage_of_lot";
	}


	@RequestMapping(value = "/admin_get_percentage_of_type", method = RequestMethod.POST)
	public String admin_get_percentage_of_type(Model model) {
		
		try {
			QueryManager manager = new QueryManager();
			ArrayList<ReturnType> return_list = manager.GetPercentageForType();
			model.addAttribute("return_list", return_list);
		}
		catch (Exception e) {
			System.out.println("Database connection problem");
        }
	
		return "admin_get_percentage_of_type";
	}


	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String user(@Validated User user, Model model) {
		System.out.println("User Page Requested");
		
		this.user = user;


		model.addAttribute("Name", this.user.getName());

		model.addAttribute("Plate", this.user.getPlate());
		ParkingLot find_spot_by_lot = new ParkingLot();
		model.addAttribute("ParkingLot", find_spot_by_lot);
		ParkingLot2 find_spot_by_type = new ParkingLot2();
		model.addAttribute("ParkingLot2", find_spot_by_type);

		if (user.getColor()==null) {
			try {
				QueryManager manager = new QueryManager();
				ArrayList<ReturnType> return_list = manager.GetVehicleInfo(this.user.getPlate());
				this.user.setColor(return_list.get(0).getStr1());
				this.user.setManu(return_list.get(0).getStr2());
				this.user.setModel(return_list.get(0).getStr3());
				String car_type = manager.IntToCarType(Integer.parseInt(return_list.get(0).getStr4()));
				this.user.setCarType(car_type);
			}
			catch (Exception e) {
				System.out.println("Fail to get vehicle info");
			}

			try {
				QueryManager manager = new QueryManager();
				ArrayList<ReturnType> return_list = manager.GetParkedInfo(this.user.getPlate());
				model.addAttribute("parked_list", return_list);
			}
			catch (Exception e) {
				System.out.println("Fail to find vehicle parked info");
			}
		}

		else {
				 //EnterVehicleInfo, update database
			try {
				QueryManager manager = new QueryManager();
				int car_type_to_int = manager.CarTypeToInt(user.getCarType());
				manager.EnterVehicleInfo(user.getPlate(), user.getColor(), user.getManu(), user.getModel(), car_type_to_int);
			}
			catch (Exception e) {
				System.out.println("Fail to add vehicle info");
			}
			
			try {
				QueryManager manager = new QueryManager();
				ArrayList<ReturnType> return_list = manager.GetParkedInfo(this.user.getPlate());
				model.addAttribute("parked_list", return_list);
			}
			catch (Exception e) {
				System.out.println("Fail to find vehicle parked info");
			}
		}
		
		System.out.println(user.getPlate());
		System.out.println(user.getColor());
		System.out.println(user.getManu());
		System.out.println(user.getModel());
		System.out.println(user.getCarType());

		return "user";
	}


	@RequestMapping(value = "/leave_spot", method = RequestMethod.POST)
	public String leave_spot(Model model) {
		
		model.addAttribute("Name", this.user.getName());
		model.addAttribute("Plate", this.user.getPlate());


		try {
			QueryManager manager = new QueryManager();
			manager.LeaveSpot(this.user.getPlate());
		}
		catch (Exception e) {
			System.out.println("Database connection problem");
        }
		return "leave_spot";
	}

	@RequestMapping(value = "/vacantspot", method = RequestMethod.POST)
	public String vacantspot(@ModelAttribute("ParkingLot") ParkingLot find_spot_by_lot, Model model) {
		model.addAttribute("Name", this.user.getName());
		model.addAttribute("Plate", this.user.getPlate());


		System.out.println(find_spot_by_lot.getParkingLotName());

		String lot_name = find_spot_by_lot.getParkingLotName();
		model.addAttribute("lot_name", lot_name);

		UserInput user_input_spot_by_lot = new UserInput();
		model.addAttribute("user_input_by_lot", user_input_spot_by_lot);



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
		model.addAttribute("Plate", this.user.getPlate());


		System.out.println(find_spot_by_type.getParkingSpotType());

		String type_name = find_spot_by_type.getParkingSpotType();
		model.addAttribute("type_name", type_name);

		UserInput user_input_spot_by_lot = new UserInput();
		model.addAttribute("user_input_by_lot", user_input_spot_by_lot);

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




	@RequestMapping(value = "/user_input", method = RequestMethod.POST)
	public String user_input(@ModelAttribute("user_input_by_lot") UserInput user_input_spot_by_lot, Model model) {

		model.addAttribute("Name", this.user.getName());
		model.addAttribute("Plate", this.user.getPlate());

		try {
			QueryManager manager = new QueryManager();

			int spot_number = Integer.parseInt(user_input_spot_by_lot.getSpotNumber());
			int level = Integer.parseInt(user_input_spot_by_lot.getLevel());
			int is_error = manager.ParkCar(this.user.getPlate(), spot_number, level, user_input_spot_by_lot.getParkingLotName());
			if (is_error == 0) {
				model.addAttribute("Status", "Error! Please Try Another Spot");
			}
			else {
				model.addAttribute("Status", "Successful");
			}
		}
		catch (Exception e) {
			System.out.println("Database connection problem");
		}
		
		return "user_input";
	}

}

