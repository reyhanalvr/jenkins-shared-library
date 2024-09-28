import groovy.json.JsonOutput

def sendDiscordNotification(String title, String description, String status, String webhookUrl) {
    def color = (status == "success") ? 65280 : 16711680 // Hijau untuk sukses, Merah untuk error
    def payload = [
        embeds: [
            [
                title: title,
                description: description,
                color: color,
                footer: [
                    text: "Notifikasi dari Jenkins"
                ]
            ]
        ]
    ]
    
    def jsonPayload = JsonOutput.toJson(payload)
    sh """
    curl -H "Content-Type: application/json" -d '${jsonPayload}' ${webhookUrl}
    """
}
