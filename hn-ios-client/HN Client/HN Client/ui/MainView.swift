//
//  MainView.swift
//  HN Client
//
//  Created by Marco Gomiero on 08/09/2020.
//  Copyright © 2020 Marco Gomiero. All rights reserved.
//

import SwiftUI

struct MainView: View {
    
    @ObservedObject var viewModel = MainViewModel()
    @State private var shouldAnimate = true
    
    init() {
        viewModel.loadData()
    }
    
    var body: some View {
        switch self.viewModel.appState.newsState {
        case _ as LoadingState:
            return AnyView(LoadingView(shouldAnimate: self.$shouldAnimate))
        case let error as ErrorState:
            return AnyView(ErrorView(reason: error.reason, viewModel: self.viewModel))
        case let success as SuccessState:
            return AnyView(NewsList(newsList: success.news))
        default:
            return AnyView(EmptyView())
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
    }
}
