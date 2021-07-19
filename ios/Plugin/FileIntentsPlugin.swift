import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(FileIntentsPlugin)
public class FileIntentsPlugin: NSObject {
    @objc func checkIntentReceived(_ value: String) -> String {
        return value
    }
}
