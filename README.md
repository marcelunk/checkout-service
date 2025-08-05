Das Projekt ist eine Spring Boot Applikation für einen CheckoutService. Kunden- und Produktdaten werden über Clients geladen. Momentan werden diese Daten aus json Dateien gelesen, es kann sich dabei aber auch um andere Services handeln. 
Es gibt eine dto-Schicht die die geladenen Daten und das Model trennen. Die Geschäftslogik ist in Serviceklassen implementiert. 
Über den `CheckoutRestController` lässt sicher der Service ansteuern. Bisher ist nur ein Pfad implementiert.

Zum Starten der Applikation:

1. Im Terminal zur Root der Projektes navigieren 
2. Mit `./mvnw clean install` das Projekt bauen
3. `./mvnw spring-boot:run` starten.



Anschließend kann mit einem weiteren Terminal eine Anfrage an den `CheckoutRestController` gestellt werden (hier mit powershell):

````powershell
# Erstelle das Objekt
$body = @{
    customerId = "C1001"
    items = @(
        @{ productId = "P100"; quantity = 1 }
        @{ productId = "P101"; quantity = 1 }
        @{ productId = "P200"; quantity = 2 }
    )
}

# Konvertiere zu JSON
$jsonBody = $body | ConvertTo-Json -Depth 10

# Führe POST-Request aus (z. B. an localhost:8080/checkout)
Invoke-RestMethod -Uri http://localhost:8080/api/checkout -Method Post -Body $jsonBody -ContentType "application/json" | ConvertTo-Json -Depth 10
````



Die gesamte Testsuite mit Integration Tests kann mit `mvnw verify` ausgeführt werden. Unit Tests mit `mvnw test`. Diese testen den Kern der Geschäftslogik und den `CheckoutRestController`.
