//
//  ViewController.swift
//  iOSApp
//
//  Created by Javier Arroyo on 17/05/2019.
//  Copyright © 2019 Javier Arroyo. All rights reserved.
//

import UIKit
import SharedCode
class ViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    @IBOutlet weak var locationTableView: UITableView!
    @IBOutlet weak var responseLabel: UILabel!
    @IBOutlet weak var addLocationButton: UIButton!
    @IBOutlet weak var locationEditText: UITextField!
    @IBOutlet weak var tempMaxLabel: UILabel!
    @IBOutlet weak var tempMinLabel: UILabel!
    @IBOutlet weak var weatherLabel: UILabel!
    
    internal var locationList: [LocationModel] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        locationTableView.dataSource = self
        locationTableView.delegate = self
        
        addLocationButton.addTarget(self, action: #selector(didButtonClick), for: .touchUpInside)

        getLocationList()
    }
    
    /*
     * GET LOCATION LIST
     */
    internal func getLocationList() {
        // Get Locations From Database
        LocationRepository().getLocationListAsync(
            success: { data in
                print(data)
                self.update(data: data)
                return KotlinUnit()
            },
            failure: {
                print($0?.message)
                return KotlinUnit()
            }
        )
    }
    
    /*
     * GET CURRENT WEATHER
     */
    internal func getCurrentWeather(_ cityName: String) {
        let location = Location(cityName: cityName, country: "Spain")
        
        // Get Data From Remote
        let  weatherApi = WeatherApi()
        WeatherRepository(weatherApi: weatherApi).getCurrentWeather(location: location,
            success:
            { data in
                self.showCurrentWeather(data)
                return KotlinUnit()
            },
            failure: {
                print($0?.message)
                return KotlinUnit()
            }
        )
    }
    
    /**
    * SHOW CURRENT WEATHER
    */
    internal func showCurrentWeather(_ data: CurrentWeather) {
        print(data)
        self.responseLabel.text = data.name
        self.tempMaxLabel.text = String(format:"%f ºC", data.main!.temp_max)
        self.tempMinLabel.text = String(format:"%f ºC", data.main!.temp_min)
        self.weatherLabel.text = data.weather!.description
    }
    
    /*
     * ON CLICKS
     */
    @objc func didButtonClick(_ sender: UIButton) {
        let cityName = locationEditText.text
        if (cityName != nil) {
            saveLocationOnDataBase(cityName)
        }
    }
    
    /*
     * SAVE LOCATION ON DATABASE SQLDelight
     */
    internal func saveLocationOnDataBase(_ cityName: String?) {
        let location = Location(cityName: cityName!, country: "Spain")
        // Save in DB
        LocationRepository().saveAsync(location: location,
            success:
            { data in
                self.locationEditText.text = ""
                self.update(data: data)
                return KotlinUnit()
        },
            failure: {
                print($0?.message)
                return KotlinUnit()
        })
    }
    
    /**
     TABLE VIEW
     */
    internal func update(data: [LocationModel]) {
        locationList.removeAll()
        locationList.append(contentsOf: data)
        locationTableView.reloadData()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return locationList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "locationListCell", for: indexPath)
        let entry = locationList[indexPath.row]
        
        cell.textLabel?.text = entry.city_name
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        let entryNum = locationList[indexPath.row].city_name
        getCurrentWeather(entryNum)
    }

}

