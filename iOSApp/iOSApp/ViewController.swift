//
//  ViewController.swift
//  iOSApp
//
//  Created by Javier Arroyo on 17/05/2019.
//  Copyright © 2019 Javier Arroyo. All rights reserved.
//

import UIKit
import SharedCode
class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Get Data From Remote
        WeatherApi().getCurrentWeather(
            success:
            { data in
                print(data)
                return KotlinUnit()
        },
            failure: {
                print($0?.message)
                return KotlinUnit()
        })
        
        // Save in DB
        LocationRepository().saveAsync(success:
           { data in
               print(data)
               return KotlinUnit()
           },
           failure: {
               print($0?.message)
               return KotlinUnit()
        })
        
        // Get data From Database
        LocationRepository().getLocationListAsync(success: { data in
            print(data)
            return KotlinUnit()
        }, failure: {
            print($0?.message)
            return KotlinUnit()
        })
        
    }


}

