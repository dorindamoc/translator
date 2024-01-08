import SwiftUI
import shared

struct ContentView: View {

	var body: some View {
		TranslateScreen(
            historyDataSource: <#T##HistoryDataSource#>,
            translateUseCase: <#T##Translate#>,
            viewModel: <#T##TranslateScreen.IOSTranslateViewModel#>
        )
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
