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
        // Do any additional setup after loading the view.
        
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
        
    }


}

