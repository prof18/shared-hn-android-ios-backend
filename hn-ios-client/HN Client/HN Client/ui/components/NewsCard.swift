//
//  NewsCard.swift
//  HN Client
//
//  Created by Marco Gomiero on 08/09/2020.
//  Copyright © 2020 Marco Gomiero. All rights reserved.
//

import SwiftUI

struct NewsCard: View {
    let news: News
    
    var body: some View {
        HStack() {
            VStack(alignment: .leading) {
                Text(self.news.title)
                    .font(.system(size: 18))
                Spacer()
                    .frame(height: 9)
                Text(self.news.formattedDate)
                    .font(.system(size: 14))
                    .italic()
            }
            .layoutPriority(100)
            Spacer()
        }
        .padding()
        .background(Color.white)
        .cornerRadius(15)
        .shadow(color: Color.black.opacity(0.2), radius: 7, x: 0, y: 2)
        .onTapGesture {
            if let url = URL(string: self.news.url), !url.absoluteString.isEmpty {
                UIApplication.shared.open(url, options: [:], completionHandler: nil)
                
            }
        }
    }
}


//struct NewsCard_Previews: PreviewProvider {
//    static var previews: some View {
//        NewsCard(news: newsList[1])
//    }
//}
