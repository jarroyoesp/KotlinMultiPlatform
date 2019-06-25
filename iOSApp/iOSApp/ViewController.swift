//
//  ViewController.swift
//  iOSApp
//
//  Created by Javier Arroyo on 17/05/2019.
//  Copyright © 2019 Javier Arroyo. All rights reserved.
//

import UIKit
import SharedCode

class ViewController: UIViewController, UITableViewDataSource, UITableViewDelegate, ProfileView {
    
    @IBOutlet weak var locationTableView: UITableView!
    @IBOutlet weak var responseLabel: UILabel!
    @IBOutlet weak var addLocationButton: UIButton!
    @IBOutlet weak var locationEditText: UITextField!
    @IBOutlet weak var tempMaxLabel: UILabel!
    @IBOutlet weak var tempMinLabel: UILabel!
    @IBOutlet weak var weatherLabel: UILabel!
    
    internal var mLocationList: [LocationModel] = []
    
    private var presenter: ProfilePresenter!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        initPresenter()
        
        locationTableView.dataSource = self
        locationTableView.delegate = self
        
        addLocationButton.addTarget(self, action: #selector(didButtonClick), for: .touchUpInside)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        presenter.attachView(view: self)
        
        presenter.getLocationList()
    }
 
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        presenter.detachView()
    }
    
    private func initPresenter() {
        var dbArgsPar =  DbArgs()
        presenter = InjectorCommon.init().provideProfilePresenter(dbArgs: dbArgsPar)
    }
    
    /**
    * PRESENTATION VIEW
    **/
    func onSuccessGetLocationList(locationList: [LocationModel]) {
        self.update(locationList: locationList)
    }
    
    func onErrorGetLocationList(throwable: KotlinThrowable) {
    
    }
    
    func showHideLoading(visible: Bool) {
        
    }
    
    func onSuccessGetCurrentWeather(currentWeather: CurrentWeather) {
        self.showCurrentWeather(currentWeather)
    }
    
    func onErrorGetCurrentWeather(throwable: KotlinThrowable) {
        
    }
    
    func onSuccessSaveLocation(locationList: [LocationModel]) {
        self.locationEditText.text = ""
        self.update(locationList: locationList)
    }
    
    func onErrorSaveLocation(throwable: KotlinThrowable) {
        
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
            let locationPar = Location(cityName: cityName!, country: "Spain")
            presenter.saveLocation(location: locationPar)
        }
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
        let location = Location(cityName: entryNum, country: "Spain")
        presenter.getCurrentWeather(location: location)
    }

}

