def call(String title, String message, String status, String webhookUrl) {
    // Buat payload untuk notifikasi Discord
    def payload = [
        content: "${title}\n${message}\nStatus: ${status}"
    ]

    // Kirim notifikasi ke Discord menggunakan curl
    def response = sh(script: "curl -H 'Content-Type: application/json' -d '${groovy.json.JsonOutput.toJson(payload)}' ${webhookUrl}", returnStatus: true)

    // Cek status pengiriman
    if (response != 0) {
        error "Failed to send Discord notification"
    }
}
