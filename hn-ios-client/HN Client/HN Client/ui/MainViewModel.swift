//
//  MainViewModel.swift
//  HN Client
//
//  Created by Marco Gomiero on 08/09/2020.
//  Copyright © 2020 Marco Gomiero. All rights reserved.
//

import SwiftUI
import Alamofire
import HNFoundation

class MainViewModel: ObservableObject {
    
    @Published private(set) var appState: AppState = AppState()
    
    func loadData() {
        self.appState = AppState(newsState: LoadingState())
        
        AF.request("http://192.168.0.147:8080/hn/topStories")
            .response(responseSerializer: CustomSerializer<NewsListDTO>()) { response in
                if let newsListDTO = response.value {
                    DispatchQueue.main.async {
                        let news: [News] = newsListDTO.news.compactMap {
                            
                            return News(id: $0.id, title: $0.title, formattedDate: self.getStringTime(time: $0.timestamp), url: $0.url)
                        }
                        
                        self.appState = AppState(newsState: SuccessState(news: news))
                    }
                }
            }
    }
    
    private func getStringTime(time: Int64) -> String {
        let d = Date(timeIntervalSince1970: TimeInterval(time))
        let df = DateFormatter()
        df.dateFormat = "d MMM yyyy"
        return df.string(from: d)
    }
}
