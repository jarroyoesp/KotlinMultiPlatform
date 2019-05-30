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
    
    internal var mLocationList: [LocationModel] = []
    
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
            success: { locationListResponse in
                print(locationListResponse)
                self.update(locationList: locationListResponse)
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
            { currentWeather in
                self.showCurrentWeather(currentWeather)
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
    internal func showCurrentWeather(_ currentWeather: CurrentWeather) {
        print(currentWeather)
        self.responseLabel.text = currentWeather.name
        self.tempMaxLabel.text = String(format:"%f ºC", currentWeather.main!.temp_max)
        self.tempMinLabel.text = String(format:"%f ºC", currentWeather.main!.temp_min)
        self.weatherLabel.text = currentWeather.weather!.description
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
            { locationListResponse in
                self.locationEditText.text = ""
                self.update(locationList: locationListResponse)
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
    internal func update(locationList: [LocationModel]) {
        mLocationList.removeAll()
        mLocationList.append(contentsOf: locationList)
        locationTableView.reloadData()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return mLocationList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "locationListCell", for: indexPath)
        let entry = mLocationList[indexPath.row]
        
        cell.textLabel?.text = entry.city_name
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        let entryNum = mLocationList[indexPath.row].city_name
        getCurrentWeather(entryNum)
    }

}

