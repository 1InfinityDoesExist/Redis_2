package in.kafka.redis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.kafka.redis.entity.CountryDetails;
import in.kafka.redis.exception.CountryDetailsNotFoundException;
import in.kafka.redis.model.request.CountryDetailsCreateRequest;
import in.kafka.redis.model.request.CountryDetailsUpdateReqeust;
import in.kafka.redis.model.response.CountryDetailsResponse;
import in.kafka.redis.service.CountryDetailsService;

@RestController
@RequestMapping(value = "/country_details")
public class CountryDetailsController {

	@Autowired
	private CountryDetailsService countryDetailsService;

	@PostMapping(value = "/persist")
	public ResponseEntity<ModelMap> saveCountryDetialsInDB(@RequestBody CountryDetailsCreateRequest request) {
		countryDetailsService.saveCountryDetails(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ModelMap("response", "Successfully created"));
	}

	@GetMapping(value = "/get/{alphaCode3}")
	public ResponseEntity<ModelMap> getCountryDetails(
			@PathVariable(value = "alphaCode3", required = true) String alphaCode3) throws Exception {
		try {
			CountryDetails responseFromDB = countryDetailsService.getCountryDetails(alphaCode3);
			return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("response", responseFromDB));
		} catch (final CountryDetailsNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ModelMap().addAttribute("error_msg", ex.getMessage()));
		}
	}

	@GetMapping(value = "/getAll")
	public ResponseEntity<ModelMap> getAllCountryDetails() {
		List<CountryDetails> listOfCountryDetails = countryDetailsService.getAllCountry();
		return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("response", listOfCountryDetails));
	}

	@DeleteMapping(value = "/delete/{alpha3Code}")
	public ResponseEntity<ModelMap> deleteCountryDetails(
			@PathVariable(value = "alpha3Code", required = true) String alpha3Code) throws Exception {
		try {
			countryDetailsService.deleteCountryDetails(alpha3Code);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ModelMap().addAttribute("response", "Successfully Deleted"));
		} catch (final CountryDetailsNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ModelMap().addAttribute("error_msg", ex.getMessage()));
		}
	}

	@PutMapping(value = "/update/{alpha3Code}")
	public ResponseEntity<ModelMap> updateCountryDetails(@RequestBody CountryDetailsUpdateReqeust updateRequest,
			@PathVariable(value = "alpha3Code", required = true) String alpha3Code) throws Exception {
		try {
			CountryDetailsResponse response = countryDetailsService.updateCountryDetails(updateRequest, alpha3Code);
			return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("updated_response", response));
		} catch (final CountryDetailsNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ModelMap().addAttribute("error_msg", ex.getMessage()));
		}
	}

	@GetMapping(value = "/neighbourbased")
	public ResponseEntity<ModelMap> getNeighbourCountries(
			@RequestParam(value = "boarders", required = true) String boarders) {
		List<CountryDetails> listOfCountries = countryDetailsService.getAllNeighbourCountries(boarders);
		return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("response", listOfCountries));
	}
}
