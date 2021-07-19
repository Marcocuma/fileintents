import Foundation
import Capacitor


@objc(FileIntents)
public class FileIntents: CAPPlugin {
    let store = ShareStore.store
    
    @objc func checkIntentReceived(_ call: CAPPluginCall) {
        if(store.url != nil){
            let name = (store.url as NSString).lastPathComponent;
    
            let destinationPath = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)[0].path+"/" + name
            
            let fm = FileManager.default
            
            do {
                try FileManager.default.copyItem(atPath: store.url, toPath: destinationPath)
                call.success(["data": name])
            } catch (let error) {
                call.reject("Error. "+error.localizedDescription)
            }
        } else {
            call.reject("Error.")
        }
    }

    public override func load() {
        let nc = NotificationCenter.default
            nc.addObserver(self, selector: #selector(eval), name: Notification.Name("triggerFileIntents"), object: nil)
    }

    @objc open func eval(){
        self.bridge.eval(js: "window.dispatchEvent(new Event('sendIntentReceived'))");
    }
}
