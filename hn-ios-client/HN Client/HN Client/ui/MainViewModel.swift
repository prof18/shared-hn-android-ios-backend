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
        self.appState = AppState(newsState: Loading())
        
        let request = AF.request("http://192.168.0.119:8080/hn/topStories")
        
        request.responseDecodable(of: NewsListDecodable.self, queue: DispatchQueue.main) { (response) in
            guard let listResponse = response.value else {
                print("something wrong")
                self.appState = AppState(newsState: Error(reason: "Something wrong during the getting of the news"))
                return
                
            }
            
            let news: [News] = listResponse.items.compactMap {
                
                let newsDTO = $0 as! NewsDTODecodable
                
                return News(id: newsDTO.id, title: newsDTO.title, formattedDate: self.getStringTime(time: newsDTO.timestamp), url: newsDTO.url)
            }
            
            self.appState = AppState(newsState: Success(news: news))
        }
    }
    
    private func getStringTime(time: Int64) -> String {
        let d = Date(timeIntervalSince1970: TimeInterval(time))
        let df = DateFormatter()
        df.dateFormat = "d MMM yyyy"
        return df.string(from: d)
    }
}
