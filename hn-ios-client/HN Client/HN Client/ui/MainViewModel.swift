//
//  MainViewModel.swift
//  HN Client
//
//  Created by Marco Gomiero on 08/09/2020.
//  Copyright © 2020 Marco Gomiero. All rights reserved.
//

import SwiftUI

class MainViewModel: ObservableObject {
    
    @Published private(set) var appState: AppState = AppState()
    
    // TODO: add network calls
    
    func loadData() {
        self.appState = AppState(newsState: Loading())
        DispatchQueue.main.asyncAfter(deadline: .now() + 2.0) {
            self.appState = AppState(newsState: Success(news: newsList))
        }
    }
    
    func generateError() {
        self.appState = AppState(newsState: Loading())
        
        DispatchQueue.main.asyncAfter(deadline: .now() + 2.0) {
            self.appState = AppState(newsState: Error(reason: "This is a generated error only to try an error state"))
        }
    }
    
//    func getStringTime() -> String {
//        let d = Date(timeIntervalSince1970: TimeInterval(time))
//        let df = DateFormatter()
//        df.dateFormat = "d MMM yyyy"
//        return df.string(from: d)
//    }
}
