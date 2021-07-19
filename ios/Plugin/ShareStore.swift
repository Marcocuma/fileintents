import Foundation

public final class ShareStore {

    public static let store = ShareStore()
    private init() {
        self.url = ""
        self.processed = false
    }

    public var url: String;
    public var processed: Bool;
}
